package hr.tumiljanovic.mojerezije.ui.home.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import hr.tumiljanovic.mojerezije.ui.theme.GraniteGray
import hr.tumiljanovic.mojerezije.R
import hr.tumiljanovic.mojerezije.ui.state.UpcomingBill

@Composable
fun UpcomingBillCell(
    modifier: Modifier = Modifier,
    navigateToBillForm: (String, String) -> Unit,
    bill: UpcomingBill
) {
    Surface(
        modifier = modifier
            .width(340.dp)
            .padding(8.dp)
            .clickable { navigateToBillForm(bill.utility.name, bill.id) },
        elevation = 2.dp,
        shape = RoundedCornerShape(24f, 0f, 24f, 0f)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(color = GraniteGray) {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 14.dp, vertical = 8.dp)
                            .width(40.dp),
                        text = bill.date.uppercase().split(" ").joinToString("\n"),
                        style = MaterialTheme.typography.subtitle2,
                        textAlign = TextAlign.Center
                    )
                }

                Box(
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 8.dp)
                        .size(28.dp)
                ) {
                    Icon(
                        modifier = Modifier
                            .size(18.dp)
                            .align(Alignment.BottomCenter),
                        painter = painterResource(R.drawable.ic_dot),
                        contentDescription = " bkg circle",
                        tint = bill.utility.color
                    )

                    Icon(
                        modifier = Modifier.align(Alignment.TopEnd),
                        painter = painterResource(id = bill.utility.iconId),
                        contentDescription = bill.utility.name
                    )

                }

                Text(
                    text = stringResource(id = bill.utility.titleId),
                    style = MaterialTheme.typography.body2
                )
            }
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = "${bill.amount} kn",
                style = MaterialTheme.typography.body2,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
