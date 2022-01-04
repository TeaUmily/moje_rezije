package hr.tumiljanovic.mojerezije.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import hr.tumiljanovic.mojerezije.ui.elements.NotificationButton
import hr.tumiljanovic.mojerezije.ui.elements.TopBar
import hr.tumiljanovic.mojerezije.R
import hr.tumiljanovic.mojerezije.common.constants.EMPTY_BILL_ID
import hr.tumiljanovic.mojerezije.ui.state.NotificationIconState
import hr.tumiljanovic.mojerezije.common.model.Utility
import hr.tumiljanovic.mojerezije.ui.home.ui.GradientBox
import hr.tumiljanovic.mojerezije.ui.home.ui.UtilityItemWithBadge
import hr.tumiljanovic.mojerezije.ui.home.ui.UpcomingBillCell
import hr.tumiljanovic.mojerezije.ui.theme.GraniteGray

@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    navigateToBillForm: (String, String) -> Unit,
    navigateToNotificationSettings: () -> Unit,
    navigateToUtilityDetails: (Utility) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val scrollState = rememberScrollState()
    val homeState = viewModel.homeState
    viewModel.loadData()

    Scaffold(
        topBar = {
            TopBar(title = stringResource(id = R.string.app_name),
            actions = {
                NotificationButton(onClickAction = navigateToNotificationSettings,
                    notificationIconState = NotificationIconState.NOTIFICATION_NORMAL)
            }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(onClick = { navigateToBillForm(Utility.ELECTRICITY.name, EMPTY_BILL_ID) }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "fab icon")
            }
        },
        content = {
            Box {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (homeState.upcomingBills.isNotEmpty()) {
                        Text(
                            modifier = Modifier
                                .align(Alignment.Start)
                                .padding(start = 24.dp, bottom = 8.dp, top = 10.dp),
                            text = stringResource(id = R.string.upcoming_bills),
                            style = MaterialTheme.typography.subtitle1,
                            color = GraniteGray,
                            textAlign = TextAlign.Center,
                        )

                        for (bill in homeState.upcomingBills) {
                            UpcomingBillCell(bill = bill, navigateToBillForm = navigateToBillForm)
                        }
                    }

                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier
                                .verticalScroll(scrollState)
                                .padding(bottom = 24.dp, top = 16.dp)
                        ) {
                            for (i in homeState.utilityToNotPaidBillsCount.keys.indices step 2) {
                                val utility =
                                    homeState.utilityToNotPaidBillsCount.keys.toTypedArray()[i]
                                val nextUtility =
                                    homeState.utilityToNotPaidBillsCount.keys.toTypedArray()[i + 1]
                                Row(modifier = Modifier.padding(top = 12.dp)) {
                                    UtilityItemWithBadge(
                                        utility = utility,
                                        navigateToUtilityDetails = navigateToUtilityDetails,
                                        numberOfNonPaidBills = homeState.utilityToNotPaidBillsCount.getValue(
                                            utility
                                        )
                                    )
                                    UtilityItemWithBadge(
                                        utility = nextUtility,
                                        navigateToUtilityDetails = navigateToUtilityDetails,
                                        numberOfNonPaidBills = homeState.utilityToNotPaidBillsCount.getValue(
                                            nextUtility
                                        )
                                    )
                                }
                            }
                        }

                        GradientBox(
                            modifier = Modifier.align(Alignment.TopCenter),
                            topColor = Color.White,
                            bottomColor = Color.Transparent)
                    }
                }

                GradientBox(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    topColor = Color.Transparent,
                    bottomColor = Color.White)
            }
        }

    )
}
