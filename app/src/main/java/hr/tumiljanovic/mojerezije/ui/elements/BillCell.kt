package hr.tumiljanovic.mojerezije.ui.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import hr.tumiljanovic.mojerezije.R
import hr.tumiljanovic.mojerezije.ui.theme.Jasper

@Composable
fun BillCell(
    modifier: Modifier = Modifier,
    dotColor: Color = Jasper,
    amount: String,
    date: String,
    onBillCellClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onBillCellClick.invoke() }
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painterResource(R.drawable.ic_dot),
            contentDescription = "red dot",
            tint = dotColor
        )
        Text(
            text = date,
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.End
        )
        Text(
            modifier = Modifier.width(80.dp),
            text = "$amount kn",
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.End
        )
    }
}