package hr.tumiljanovic.mojerezije.ui.utilityDetails.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import hr.tumiljanovic.mojerezije.R
import hr.tumiljanovic.mojerezije.ui.state.YearChartData

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
            if(chartBottomSheetData.size > 1) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    chartBottomSheetData.forEach { year ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (!year.isCurrentYear) {
                                Checkbox(
                                    checked = year.isSelected,
                                    onCheckedChange = { onYearCheckboxSelected(year.value, it) }
                                )
                            }
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
                YearOverYearComparisonChart(chartBottomSheetData)
            } else {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp, start = 16.dp, end = 16.dp),
                    text = stringResource(id = R.string.more_then_one_bill_required),
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.Center
                )
            }
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

