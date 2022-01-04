package hr.tumiljanovic.mojerezije.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.tumiljanovic.mojerezije.common.model.Utility
import hr.tumiljanovic.mojerezije.common.repository.ResourceRepository
import hr.tumiljanovic.mojerezije.domain.bill.model.Bill
import hr.tumiljanovic.mojerezije.domain.bill.usecase.GetBillsUseCase
import hr.tumiljanovic.mojerezije.ui.state.HomeState
import hr.tumiljanovic.mojerezije.ui.state.UpcomingBill
import hr.tumiljanovic.mojerezije.ui.state.toUpcomingBillDateFormat
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getBillsUseCase: GetBillsUseCase,
    private val resourceRepository: ResourceRepository
) :ViewModel() {

    var homeState by mutableStateOf(HomeState())
        private set

    fun loadData() {
        viewModelScope.launch {
            val bills = getBillsUseCase.invoke()
            updateUiState(bills, provideUtilityToNotPaidBillsSortedMap(bills))
        }
    }

    private fun provideUtilityToNotPaidBillsSortedMap(bills: List<Bill>): SortedMap<Utility, Int> {
        val sortedMap = sortedMapOf<Utility, Int>()
        Utility.values().forEach { utility ->
            sortedMap[utility] =
                bills.filter { it.utilityTitle == resourceRepository.getString(utility.titleId) &&!it.isPaid }.size
        }
        return sortedMap
    }

    private fun updateUiState(bills: List<Bill>, sortedMap: SortedMap<Utility, Int>) {
        homeState = homeState.copy(
            utilityToNotPaidBillsCount = sortedMap,
            upcomingBills = bills
                .filter { !it.isPaid && it.dueDate >= Date()}
                .sortedBy { it.dueDate }
                .take(2)
                .map {  bill ->
                    UpcomingBill(
                        id = bill.id,
                        amount = bill.amount.toString(),
                        date = bill.dueDate.toUpcomingBillDateFormat(resourceRepository),
                        utility = Utility.values().find { resourceRepository.getString(it.titleId) == bill.utilityTitle } ?: Utility.ELECTRICITY
                    )
                }
        )
    }
}