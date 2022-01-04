package hr.tumiljanovic.mojerezije.ui.utilityDetails.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import hr.tumiljanovic.mojerezije.ui.theme.GraniteGray

@Composable
fun ExtremeValueDisplay(
    modifier: Modifier = Modifier,
    extremeTitle: String,
    extremeValue: String,
    date: String
) {
    Column(
        modifier  = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = extremeTitle,
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Normal
        )
        Text(
            text = "$extremeValue kn",
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = date,
            style = MaterialTheme.typography.caption,
            fontWeight = FontWeight.Medium,
            color = GraniteGray
        )
    }
}