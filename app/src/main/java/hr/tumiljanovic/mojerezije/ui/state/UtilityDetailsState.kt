package hr.tumiljanovic.mojerezije.ui.state

import androidx.compose.ui.graphics.Color
import com.madrapps.plot.line.DataPoint
import hr.tumiljanovic.mojerezije.common.model.Utility
import hr.tumiljanovic.mojerezije.domain.bill.model.Bill

data class UtilityDetailsState(
    val utility: Utility = Utility.ELECTRICITY,
    val minExtreme: ExtremeData = ExtremeData(),
    val maxExtreme: ExtremeData = ExtremeData(),
    val average: String = "",
    val paidBills: List<Bill> = emptyList(),
    val notPaidBills: List<Bill> = emptyList(),
    val displayShowAllBtnForPaidBills: Boolean = false,
    val displayShowAllBtnForNotPaidBills: Boolean = false,
    val isReminderOn: Boolean = false,
)

data class ExtremeData(
    val value: String = "",
    val date: String = ""
)

data class YearChartData(
    val value: Int,
    var color: Color = Color.Black,
    val dataPoints: List<DataPoint> = listOf(),
    var isSelected: Boolean = false,
    val isCurrentYear: Boolean = false
)