package hr.tumiljanovic.mojerezije.ui.billsDisplay

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.tumiljanovic.mojerezije.R
import hr.tumiljanovic.mojerezije.common.constants.DISPLAY_PAID_BILLS_KEY
import hr.tumiljanovic.mojerezije.common.constants.UTILITY_NAME_KEY
import hr.tumiljanovic.mojerezije.common.model.Utility
import hr.tumiljanovic.mojerezije.common.repository.ResourceRepository
import hr.tumiljanovic.mojerezije.domain.bill.model.Bill
import hr.tumiljanovic.mojerezije.domain.bill.usecase.GetBillsForUtilityUseCase
import hr.tumiljanovic.mojerezije.ui.state.BillsDisplayState
import hr.tumiljanovic.mojerezije.ui.theme.Jasper
import hr.tumiljanovic.mojerezije.ui.theme.SnowPea
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BillsDisplayViewModel @Inject constructor(
    private val resourceRepository: ResourceRepository,
    private val getBillsForUtilityUseCase: GetBillsForUtilityUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    var billsDisplayState by mutableStateOf(BillsDisplayState())
        private set

    private val utilityName: String? = savedStateHandle.get(UTILITY_NAME_KEY)
    private val displayPaidBills: Boolean = savedStateHandle.get<String>(DISPLAY_PAID_BILLS_KEY).toBoolean()

    fun loadScreenData() {
        if(utilityName != null) {
            val utility = Utility.valueOf(utilityName)
            viewModelScope.launch {
                val bills = getBillsForUtilityUseCase.invoke(resourceRepository.getString(utility.titleId))
                updateUiState(utility, bills)
            }
        }
    }

    private fun updateUiState(utility: Utility, bills: List<Bill>) {
        billsDisplayState = billsDisplayState.copy(
            title = provideTitle(utility, displayPaidBills),
            bills = bills
                .filter { it.isPaid == displayPaidBills }
                .sortedBy { it.dueDate }
                .asReversed(),
            dotsColor = if (displayPaidBills) SnowPea else Jasper,
            utilityName = utility.name
        )
    }

    private fun provideTitle (utility: Utility, displayPaidBills: Boolean) : String {
        val billsStateStringId = if(displayPaidBills) R.string.paid_bills else R.string.not_paid_bills
        return resourceRepository.getString(utility.titleId) + " - " +
                resourceRepository.getString(billsStateStringId)
    }

}