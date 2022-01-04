package hr.tumiljanovic.mojerezije.ui.home.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import hr.tumiljanovic.mojerezije.common.model.Utility

@ExperimentalMaterialApi
@Composable
fun UtilityItemButton(
    modifier: Modifier = Modifier,
    utility: Utility,
    navigateToUtilityDetails: (Utility) -> Unit
) {
    Button(onClick = { navigateToUtilityDetails.invoke(utility) },
            modifier = modifier
                .width(120.dp)
                .height(120.dp),
            colors = ButtonDefaults.buttonColors(utility.color),
            shape = RoundedCornerShape(24f, 0f, 24f, 0f)
        ) {
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(painter = painterResource(id = utility.iconId), contentDescription = utility.name)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = stringResource(id = utility.titleId),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.subtitle1, fontWeight = FontWeight.Medium)
            }
    }
}