package hr.tumiljanovic.mojerezije.ui.billForm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.mlkit.vision.barcode.Barcode
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.tumiljanovic.mojerezije.R
import hr.tumiljanovic.mojerezije.common.constants.*
import hr.tumiljanovic.mojerezije.common.model.BillFormMode
import hr.tumiljanovic.mojerezije.common.model.Utility
import hr.tumiljanovic.mojerezije.ui.state.BillFormState
import hr.tumiljanovic.mojerezije.common.repository.ResourceRepository
import hr.tumiljanovic.mojerezije.common.utils.DateUtils
import hr.tumiljanovic.mojerezije.domain.bill.model.Bill
import hr.tumiljanovic.mojerezije.domain.bill.usecase.DeleteBillUseCase
import hr.tumiljanovic.mojerezije.domain.bill.usecase.EditBillUseCase
import hr.tumiljanovic.mojerezije.domain.bill.usecase.GetBillByIdUseCase
import hr.tumiljanovic.mojerezije.domain.bill.usecase.StoreBillUseCase
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class BillFormViewModel @Inject constructor(
    private val resourceRepository: ResourceRepository,
    private val storeBillUseCase: StoreBillUseCase,
    private val getBillByIdUseCase: GetBillByIdUseCase,
    private val editBillUseCase: EditBillUseCase,
    private val deleteBillUseCase: DeleteBillUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    var billFormState by mutableStateOf(BillFormState(
        utilityList = Utility.values().map { resourceRepository.getString(it.titleId) }
    ))
        private set

    private val preselectedUtilityName: String? = savedStateHandle.get(PRESELECTED_UTILITY_KEY)
    private val billId: String? = savedStateHandle.get(BILL_ID_KEY)

    init {
        setUp()
    }

    private lateinit var formMode: BillFormMode

    private fun setUp() {
        if (billId != null && billId != EMPTY_BILL_ID) {
           setUpEditMode(billId)
        } else {
           setUpCreateMode(preselectedUtilityName)
        }
    }

    private fun setUpCreateMode(preselectedUtilityName: String?) {
        formMode = BillFormMode.CREATE

        billFormState = billFormState.copy(
            selectedUtility = provideUtilityName(preselectedUtilityName),
            dueDate = DateUtils.getCurrentDateByFormat(IN_APP_DATE_FORMAT),
            title = resourceRepository.getString(R.string.new_bill),
            actionButtonText = resourceRepository.getString(R.string.save),
            showDeleteButton = false
        )
    }

    private fun provideUtilityName(preselectedUtilityName: String?) = if (preselectedUtilityName != null) {
        resourceRepository.getString(Utility.valueOf(preselectedUtilityName).titleId)
    } else {
        resourceRepository.getString(Utility.ELECTRICITY.titleId)
    }

    private fun setUpEditMode(billId: String) {
        formMode = BillFormMode.EDIT

        viewModelScope.launch {
            val bill = getBillByIdUseCase.invoke(billId)

            billFormState = billFormState.copy(
                id = bill.id,
                selectedUtility = bill.utilityTitle,
                amount = bill.amount.toString(),
                dueDate = DateUtils.dateToFormat(IN_APP_DATE_FORMAT, bill.dueDate),
                note = bill.note,
                isPaid = bill.isPaid,
                title = resourceRepository.getString(R.string.edit_bill),
                actionButtonText = resourceRepository.getString(R.string.save_changes),
                showDeleteButton = true,
                showQrCodeScannerButton = false
            )
        }
    }

    fun onAmountChanged(amount: String) {
        billFormState = billFormState.copy(amount = amount, showAmountError = amount.isEmpty())
    }

    fun onUtilitySelected(utility: String) {
        billFormState = billFormState.copy(selectedUtility = utility)
    }

    fun onDatePicked(date: String) {
        billFormState = billFormState.copy(dueDate = date)
    }

    fun changePaidStatusTo(isPaid: Boolean) {
        billFormState = billFormState.copy(isPaid = isPaid)
    }

    fun onNoteChanged(note: String) {
        billFormState = billFormState.copy(note = note)
    }

    fun onDeleteConfirmed() {
        viewModelScope.launch {
            deleteBillUseCase.invoke(createBillFromState(billFormState.id))

            billFormState = billFormState.copy(popBackStack = true)
        }
    }

    fun onBarCodeScanned(barcode: Barcode) {
        val barcodeValues = barcode.rawValue?.split("\n")
        if(barcodeValues != null && barcodeValues[0] == CROATIAN_UTILITY_BILL_CODE) {
            val amount = barcodeValues[2].toDouble() / 100
            val note = barcodeValues.dropLast(1).last()
            billFormState = billFormState.copy(
                amount = amount.toString(),
                showAmountError = false,
                note = note
            )
        }
    }

    fun onSaveClick() {
        if(billFormState.amount.isEmpty()){
            billFormState = billFormState.copy(showAmountError = true)
            return
        }

        viewModelScope.launch {
            if(formMode == BillFormMode.CREATE) {
                storeBillUseCase.invoke(createBillFromState(UUID.randomUUID().toString()))
            } else {
                editBillUseCase.invoke(createBillFromState(billFormState.id))
            }

            billFormState = billFormState.copy(popBackStack = true)
        }
    }

    private fun createBillFromState(billId: String) =
        Bill(id = billId,
            note = billFormState.note,
            amount = String.format("%.2f", billFormState.amount.toFloat()).replace(",", ".").toFloat(),
            dueDate = provideDueDateInUiFormat(),
            utilityTitle = billFormState.selectedUtility,
            isPaid = billFormState.isPaid
        )

    private fun provideDueDateInUiFormat() =
        DateUtils.convertStringToDateByFormat(IN_APP_DATE_FORMAT, billFormState.dueDate) ?: Date()

}
