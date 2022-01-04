package hr.tumiljanovic.mojerezije.ui.utilityDetails

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import hr.tumiljanovic.mojerezije.ui.state.NotificationIconState
import hr.tumiljanovic.mojerezije.common.model.Utility
import hr.tumiljanovic.mojerezije.ui.elements.GraphButton
import hr.tumiljanovic.mojerezije.ui.elements.NotificationButton
import hr.tumiljanovic.mojerezije.ui.elements.TopBar
import hr.tumiljanovic.mojerezije.ui.utilityDetails.ui.BillListWithLabel
import hr.tumiljanovic.mojerezije.ui.utilityDetails.ui.StatisticSection
import hr.tumiljanovic.mojerezije.R
import hr.tumiljanovic.mojerezije.common.constants.EMPTY_BILL_ID
import hr.tumiljanovic.mojerezije.ui.theme.Jasper
import hr.tumiljanovic.mojerezije.ui.theme.SnowPea
import hr.tumiljanovic.mojerezije.ui.utilityDetails.ui.ChartBottomSheet
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun UtilityDetailsScreen(
    onBackPressedCallback: () -> Unit,
    navigateToBillsDisplay: (Utility, Boolean) -> Unit,
    navigateToBillForm: (String, String) -> Unit,
    viewModel: UtilityDetailsViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    val utilityDetailsState = viewModel.utilityDetailsState
    val bottomState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    viewModel.loadUtility()
    BackHandler(enabled = bottomState.isVisible) {
        coroutineScope.launch { bottomState.hide() }
    }
    ModalBottomSheetLayout(
        sheetState = bottomState,
        sheetShape = Shapes().medium.copy(CornerSize(10.dp), CornerSize(10.dp), CornerSize(0.dp), CornerSize(0.dp)),
        sheetContent = {
            ChartBottomSheet(
                chartBottomSheetData = viewModel.bottomSheetState,
                onYearCheckboxSelected = viewModel::onYearCheckboxSelected
            )
        }) {
        Scaffold(
            topBar = {
                TopBar(title = stringResource(id = utilityDetailsState.utility.titleId),
                    onBackPressed = onBackPressedCallback,
                    actions = {
                        NotificationButton(
                            onClickAction = { viewModel.onReminderStateChanged(!utilityDetailsState.isReminderOn) },
                            notificationIconState = if (utilityDetailsState.isReminderOn)
                                NotificationIconState.NOTIFICATION_ON else NotificationIconState.NOTIFICATION_OFF
                        )
                        GraphButton(onClickAction = {
                            coroutineScope.launch {
                                viewModel.loadBottomSheetData()
                                bottomState.show()
                            }
                        })
                    }
                )
            },
            floatingActionButtonPosition = FabPosition.End,
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    navigateToBillForm(
                        utilityDetailsState.utility.name,
                        EMPTY_BILL_ID
                    )
                }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "fab icon")
                }
            },
            content = {
                Surface(
                    modifier = Modifier
                        .verticalScroll(scrollState)
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(start = 6.dp, end = 6.dp, bottom = 90.dp, top = 10.dp),
                    color = utilityDetailsState.utility.color,
                    shape = RoundedCornerShape(24f, 0f, 24f, 0f)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp, 20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        StatisticSection(
                            modifier = Modifier.fillMaxWidth(),
                            extremeMinData = utilityDetailsState.minExtreme,
                            extremeMaxData = utilityDetailsState.maxExtreme,
                            average = utilityDetailsState.average
                        )
                        Spacer(modifier = Modifier.height(26.dp))
                        BillListWithLabel(
                            label = stringResource(id = R.string.not_paid_bills),
                            billList = utilityDetailsState.notPaidBills,
                            dotColor = Jasper,
                            displayShowAllBtn = utilityDetailsState.displayShowAllBtnForNotPaidBills,
                            onShowAllClick = {
                                navigateToBillsDisplay(
                                    utilityDetailsState.utility,
                                    false
                                )
                            },
                            navigateToBillForm = navigateToBillForm
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        BillListWithLabel(
                            label = stringResource(id = R.string.paid_bills),
                            billList = utilityDetailsState.paidBills,
                            dotColor = SnowPea,
                            displayShowAllBtn = utilityDetailsState.displayShowAllBtnForPaidBills,
                            onShowAllClick = {
                                navigateToBillsDisplay(
                                    utilityDetailsState.utility,
                                    true
                                )
                            },
                            navigateToBillForm = navigateToBillForm
                        )
                    }
                }
            }
        )
    }
}