package hr.tumiljanovic.mojerezije.ui.utilityDetails.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import hr.tumiljanovic.mojerezije.R
import hr.tumiljanovic.mojerezije.ui.state.YearChartData
import com.github.mikephil.charting.data.LineData


@Composable
fun ChartBottomSheet(
    modifier: Modifier = Modifier,
    chartBottomSheetData: List<YearChartData>,
    onYearCheckboxSelected: (Int, Boolean) -> Unit
) {
    Column(
        modifier = modifier
            .height(320.dp)
            .fillMaxWidth()
    ) {

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = stringResource(id = R.string.bottom_sheet_tittle),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(12.dp))
        if(chartBottomSheetData.isNotEmpty()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    chartBottomSheetData.forEach { year ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = year.isSelected,
                                onCheckedChange = { onYearCheckboxSelected(year.value, it) }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = year.value.toString(),
                                color = year.color,
                                style = MaterialTheme.typography.h6,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                yearOverYearChart(chartBottomSheetData = chartBottomSheetData)
        } else {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                text = stringResource(id = R.string.still_no_input),
                style = MaterialTheme.typography.subtitle1,
                textAlign = TextAlign.Center
            )
        }
    }
}


@Composable
fun yearOverYearChart(
    chartBottomSheetData: List<YearChartData>,
){
    val dataSets = chartBottomSheetData
        .filter { it.isSelected }
        .map {
            LineDataSet(it.entries, "year-it.value.toString()").apply {
                color = it.color.toArgb()
                circleColors = listOf(it.color.toArgb())
                circleHoleColor = it.color.toArgb()
                valueTextColor = it.color.toArgb()
                valueTextSize = 0F
        }
    }

    AndroidView(modifier = Modifier
        .padding(horizontal = 16.dp, vertical = 12.dp)
        .height(280.dp)
        .fillMaxWidth(),
        factory = { context ->
            val chart = LineChart(context)
            chart.setTouchEnabled(false)
            chart.setPinchZoom(false)
            val xAxis: XAxis = chart.xAxis
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.textSize = 12f
            xAxis.axisMaximum = 12f
            xAxis.isGranularityEnabled = true
            xAxis.granularity = 1f
            xAxis.labelCount = 12
            chart.axisRight.isEnabled = false
            chart.description.isEnabled = false
            chart.legend.isEnabled = false
            chart.isAutoScaleMinMaxEnabled = true
            val lineData = LineData(dataSets)
            chart.data = lineData
            chart.animateY(500)
            chart
        },
        update = { view ->
            if(view.data.dataSetCount != dataSets.size) {
                view.data = LineData(dataSets)
                view.invalidate()
            }
        }
    )

}
