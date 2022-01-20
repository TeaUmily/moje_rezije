package hr.tumiljanovic.mojerezije.ui.utilityDetails

import android.content.SharedPreferences
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mikephil.charting.data.Entry
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.tumiljanovic.mojerezije.R
import hr.tumiljanovic.mojerezije.common.constants.EMPTY_STRING
import hr.tumiljanovic.mojerezije.common.constants.UTILITY_NAME_KEY
import hr.tumiljanovic.mojerezije.common.model.Utility
import hr.tumiljanovic.mojerezije.common.model.ChartYear
import hr.tumiljanovic.mojerezije.common.repository.ResourceRepository
import hr.tumiljanovic.mojerezije.common.utils.DateUtils
import hr.tumiljanovic.mojerezije.domain.bill.model.Bill
import hr.tumiljanovic.mojerezije.domain.bill.usecase.GetBillsForUtilityUseCase
import hr.tumiljanovic.mojerezije.ui.state.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject


@HiltViewModel
class UtilityDetailsViewModel @Inject constructor(
    private val resourceRepository: ResourceRepository,
    private val getBillsForUtilityUseCase: GetBillsForUtilityUseCase,
    private val sharedPreferences: SharedPreferences,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    var utilityDetailsState by mutableStateOf(UtilityDetailsState())
        private set

    var bottomSheetState by mutableStateOf(emptyList<YearChartData>())
        private set

    private val utilityName: String? = savedStateHandle.get(UTILITY_NAME_KEY)
    private lateinit var allBills: List<Bill>

    fun loadUtility() {
        if(utilityName != null) {
            val utility = Utility.valueOf(utilityName)
            viewModelScope.launch {
                val bills = getBillsForUtilityUseCase.invoke(resourceRepository.getString(utility.titleId))
                allBills = bills
                updateUiState(bills, utility)
            }
        }
    }

    private fun updateUiState(bills: List<Bill>, utility: Utility) {
        val minAmountBill = bills.minByOrNull { it.amount }
        val maxAmountBill = bills.maxByOrNull { it.amount }
        val average = provideAverageBillAmount(bills)
        val paidBills = provideBillsForUi(bills, true)
        val notPaidBills = provideBillsForUi(bills, false)

        utilityDetailsState = utilityDetailsState.copy(
            utility = utility,
            minExtreme = ExtremeData(
                value = minAmountBill?.amount?.toString() ?: 0.0f.toString(),
                date = provideExtremeBillDateInUiFormat(minAmountBill?.dueDate)
            ),
            maxExtreme = ExtremeData(
                value = maxAmountBill?.amount?.toString() ?: 0.0f.toString(),
                date = provideExtremeBillDateInUiFormat(maxAmountBill?.dueDate)
            ),
            average = String.format("%.2f", average),
            paidBills = paidBills.take(5),
            notPaidBills = notPaidBills.take(5),
            displayShowAllBtnForNotPaidBills = notPaidBills.size > 5,
            displayShowAllBtnForPaidBills = paidBills.size > 5,
            isReminderOn = sharedPreferences.getBoolean(utility.name, false)
        )
    }

    private fun provideBottomSheetData(billList: List<Bill>): List<YearChartData> {
        val billsGroupedByYears = billList.sortedBy { it.dueDate }.groupBy { DateUtils.getYearFromDate(it.dueDate) }.values
        val yearsData = billsGroupedByYears.map { bills ->
            val year = DateUtils.getYearFromDate(bills[0].dueDate)
            YearChartData(
                value = year,
                color = ChartYear.values().find {  it.value == year }?.color ?: Color.Black,
                entries = provideLineDataPoints(bills)
            )
        }
        return  yearsData
    }

    private fun provideLineDataPoints(bills: List<Bill>) = bills.map { Entry(DateUtils.getMonthFromDate(it.dueDate).toFloat(), it.amount) }

    private fun provideAverageBillAmount(bills: List<Bill>)  = if(bills.isNotEmpty()) bills.map { it.amount }.average() else 0.0

    private fun provideBillsForUi(bills: List<Bill>, isPaid: Boolean) = bills
            .filter { it.isPaid == isPaid }
            .sortedBy { it.dueDate }
            .asReversed()

    private fun provideExtremeBillDateInUiFormat(date: Date?) : String {
        return if(date != null) {
            val month = resourceRepository.getStringArray(R.array.months)[DateUtils.getMonthFromDate(date) - 1]
            "$month ${DateUtils.getYearFromDate(date)}."
        } else {
            EMPTY_STRING
        }
    }

    fun onReminderStateChanged(state: Boolean) {
        changeUtilityReminderState(state)
        utilityDetailsState = utilityDetailsState.copy(isReminderOn = state)
    }

    private fun changeUtilityReminderState(state: Boolean) {
        with(sharedPreferences.edit()){
            putBoolean(utilityName, state)
            apply()
        }
    }

    fun loadBottomSheetData() {
        bottomSheetState = provideBottomSheetData(allBills)
    }

    fun onYearCheckboxSelected(year: Int, isChecked: Boolean) {
        val oldBottomSheetState = bottomSheetState
        val newBottomSheetState = oldBottomSheetState.map { YearChartData(it.value, it.color, it.entries, if(it.value == year) isChecked else it.isSelected) }

        bottomSheetState = newBottomSheetState
      }

}