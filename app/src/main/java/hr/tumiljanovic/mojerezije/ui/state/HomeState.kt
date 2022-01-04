package hr.tumiljanovic.mojerezije.ui.state

import hr.tumiljanovic.mojerezije.R
import hr.tumiljanovic.mojerezije.common.model.Utility
import hr.tumiljanovic.mojerezije.common.repository.ResourceRepository
import hr.tumiljanovic.mojerezije.common.utils.DateUtils
import hr.tumiljanovic.mojerezije.domain.bill.model.Bill
import java.util.*

data class HomeState (
    val upcomingBills: List<UpcomingBill> = listOf(),
    val utilityToNotPaidBillsCount: SortedMap<Utility, Int> = sortedMapOf()
)

data class UpcomingBill(
    val id: String,
    val date: String,
    val utility: Utility,
    val amount: String
)

fun Date.toUpcomingBillDateFormat(resourceRepository: ResourceRepository): String {
    val monthNumber =  DateUtils.getMonthFromDate(this)
    val monthString = resourceRepository.getStringArray(R.array.months_abbreviations)[monthNumber - 1]
    val day = DateUtils.getDayFromDate(this)
    return "$day $monthString"
}