package hr.tumiljanovic.mojerezije.ui.billForm.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import hr.tumiljanovic.mojerezije.ui.theme.LightHouse
import hr.tumiljanovic.mojerezije.ui.theme.SnowPea
import hr.tumiljanovic.mojerezije.R
import hr.tumiljanovic.mojerezije.ui.theme.Carnation

@Composable
fun FormPaidNotPaidOptionsSection(
    modifier: Modifier = Modifier,
    isBillPaid: Boolean,
    changePaidStatusTo: (Boolean) -> Unit,
) {
    Text(
        text = stringResource(id = R.string.this_bill_is)+":",
        style = MaterialTheme.typography.body1,
        fontWeight = FontWeight.Medium
    )
    Spacer(modifier = Modifier.height(16.dp))
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            modifier = Modifier
            .width(140.dp)
            .height(54.dp),
            shape = RoundedCornerShape(24f, 0f, 24f, 0f),
            colors = if(isBillPaid) ButtonDefaults.buttonColors(SnowPea) else ButtonDefaults.buttonColors(LightHouse),
            onClick = { changePaidStatusTo(true) }) {
            Text(text = stringResource(id = R.string.paid).uppercase(),
                color = if(isBillPaid) Color.White else Color.Black,
                style = MaterialTheme.typography.subtitle2)
        }
        Spacer(modifier = Modifier.width(20.dp))

        Button(
            modifier = Modifier
            .width(140.dp)
            .height(54.dp),
            shape = RoundedCornerShape(24f, 0f, 24f, 0f),
            colors = if(isBillPaid) ButtonDefaults.buttonColors(LightHouse) else ButtonDefaults.buttonColors(Carnation) ,
            onClick = { changePaidStatusTo(false)  }) {
            Text(text = stringResource(id = R.string.not_paid).uppercase(),
                color = if(isBillPaid) Color.Black else Color.White,
                style = MaterialTheme.typography.subtitle2)
        }
    }
}