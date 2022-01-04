package hr.tumiljanovic.mojerezije.ui.home.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import hr.tumiljanovic.mojerezije.R
import hr.tumiljanovic.mojerezije.common.model.Utility
import hr.tumiljanovic.mojerezije.ui.theme.darkRed

@ExperimentalMaterialApi
@Composable
fun UtilityItemWithBadge(
    modifier: Modifier = Modifier,
    utility: Utility,
    numberOfNonPaidBills: Int = 0,
    navigateToUtilityDetails: (Utility) -> Unit
) {
    if(numberOfNonPaidBills > 0) {
        Box(
            modifier = Modifier.padding(16.dp).size(128.dp)
        ) {
            UtilityItemButton(
                modifier = Modifier.align(Alignment.BottomStart),
                utility = utility,
                navigateToUtilityDetails = navigateToUtilityDetails
            )

            Box(
                modifier = Modifier.align(Alignment.TopEnd),
                contentAlignment = Alignment.Center
            ){
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(R.drawable.ic_dot),
                    contentDescription = " bkg circle",
                    tint = darkRed
                )
                Text(
                    modifier = Modifier.padding(bottom = 2.dp),
                    text = numberOfNonPaidBills.toString(),
                    fontWeight = FontWeight.Medium,
                    style = MaterialTheme.typography.body1,
                    color = Color.White
                )
            }

        }
    } else {
        UtilityItemButton(modifier.padding(18.dp),
            utility = utility,
            navigateToUtilityDetails = navigateToUtilityDetails)
    }
}