package hr.tumiljanovic.mojerezije.ui.utilityDetails.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import hr.tumiljanovic.mojerezije.R
import hr.tumiljanovic.mojerezije.ui.state.ExtremeData

@Composable
fun StatisticSection(
    modifier: Modifier = Modifier,
    extremeMinData: ExtremeData,
    extremeMaxData: ExtremeData,
    average: String,
) {
    Row(modifier = modifier
        .height(60.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        ExtremeValueDisplay(
            extremeTitle = stringResource(id = R.string.min),
            extremeValue = extremeMinData.value,
            date = extremeMinData.date,
        )
        AverageValueDisplay(
            averageValue = average,
        )
        ExtremeValueDisplay(
            extremeTitle = stringResource(id = R.string.max),
            extremeValue = extremeMaxData.value,
            date = extremeMaxData.date,
        )
    }
}