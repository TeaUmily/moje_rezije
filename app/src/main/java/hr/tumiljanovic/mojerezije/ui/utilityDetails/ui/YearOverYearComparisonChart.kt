package hr.tumiljanovic.mojerezije.ui.utilityDetails.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.madrapps.plot.line.LineGraph
import com.madrapps.plot.line.LinePlot
import hr.tumiljanovic.mojerezije.ui.state.YearChartData
import hr.tumiljanovic.mojerezije.ui.theme.BoboGray

@Composable
fun YearOverYearComparisonChart(
    data: List<YearChartData>
) {
    LineGraph(
        plot = LinePlot(
            lines = data
                .filter { it.isSelected }
                .map { LinePlot.Line(
                    dataPoints = it.dataPoints,
                    connection = LinePlot.Connection(color = it.color),
                    intersection = LinePlot.Intersection(color = it.color)
            ) },
            grid = LinePlot.Grid(BoboGray, steps = 5),
            yAxis = LinePlot.YAxis(
                content = { min, offset, _ ->
                    for (it in 0 until 5) {
                        val value = it * offset + min
                        Text(
                            text = value.toInt().toString(),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.body2,
                            color = MaterialTheme.colors.onSurface
                        )
                    }
                }
            ),
            xAxis = LinePlot.XAxis(
                steps = 12,
                content = { min, offset, _ ->
                    for (it in 0 until 12) {
                        val value = it * offset + min
                        Text(
                            text = value.toInt().toString(),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.body1,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colors.onSurface
                        )
                    }
                }
            ),
            paddingRight = 12.dp,
            isZoomAllowed = true
        ),
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth(),
        onSelection = { xLine, points -> }
    )
}