package hr.tumiljanovic.mojerezije.ui.reminderSettings.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import hr.tumiljanovic.mojerezije.R
import hr.tumiljanovic.mojerezije.common.model.Utility
import hr.tumiljanovic.mojerezije.ui.theme.GraniteGray
import hr.tumiljanovic.mojerezije.ui.theme.SnowPea

@Composable
fun UtilityReminderToggle(
    modifier: Modifier = Modifier,
    utility: Utility,
    isReminderOn: Boolean,
    onStateChanged: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
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
                    tint = utility.color
                )

                Icon(
                    modifier = Modifier.align(Alignment.TopEnd),
                    painter = painterResource(id = utility.iconId),
                    contentDescription = utility.name
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = stringResource(id = utility.titleId),
                style = MaterialTheme.typography.body1
            )
        }
        Switch(
            checked = isReminderOn,
            onCheckedChange = onStateChanged,
            colors = SwitchDefaults.colors(
                checkedThumbColor = SnowPea,
                checkedTrackColor = SnowPea,
                uncheckedThumbColor = GraniteGray,
                uncheckedTrackColor = GraniteGray
            )
        )
    }

}