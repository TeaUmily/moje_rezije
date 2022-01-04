package hr.tumiljanovic.mojerezije.ui.utilityDetails.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import hr.tumiljanovic.mojerezije.R

@Composable
fun AverageValueDisplay(
    modifier: Modifier = Modifier,
    averageValue: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.average),
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Normal
        )
        Text(
            text = "$averageValue kn",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold
        )
    }
}