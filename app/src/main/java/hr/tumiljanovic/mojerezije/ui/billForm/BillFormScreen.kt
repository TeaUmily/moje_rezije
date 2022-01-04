package hr.tumiljanovic.mojerezije.ui.billForm

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import hr.tumiljanovic.mojerezije.R
import hr.tumiljanovic.mojerezije.ui.elements.*
import hr.tumiljanovic.mojerezije.ui.billForm.ui.*
import hr.tumiljanovic.mojerezije.ui.theme.Jasper
import hr.tumiljanovic.mojerezije.ui.theme.LightHouse
import kotlinx.coroutines.launch

@ExperimentalPermissionsApi
@ExperimentalComposeUiApi
@Composable
fun BillFormScreen(
    upPressCallback: () -> Unit,
    viewModel: BillFormViewModel = hiltViewModel(),
) {
    val scrollState = rememberScrollState()
    var showConfirmDeleteDialog by remember { mutableStateOf(false) }
    var showQrScannerDialog by remember { mutableStateOf(false) }
    val scaffoldState = rememberScaffoldState()
    val snackbarCoroutineScope = rememberCoroutineScope()

    if(viewModel.billFormState.popBackStack){
        upPressCallback.invoke()
    }

    val billFormState = viewModel.billFormState
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(
                title = billFormState.title,
                onBackPressed = upPressCallback,
                actions = {
                    if(billFormState.showDeleteButton) DeleteButton(onDeleteClicked = { showConfirmDeleteDialog = true })
                    if(billFormState.showQrCodeScannerButton) QrCodeScannerButton(onButtonClick = { showQrScannerDialog = true })
                }
            )
        },
        content = {
            val sectionHeight = 60.dp
            val focusRequester = remember { FocusRequester() }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(16.dp, 16.dp)
            ) {
                Column(
                    modifier = Modifier.verticalScroll(scrollState)
                ) {
                    DropDownWithTitle(
                        modifier = Modifier.height(sectionHeight),
                        onOptionSelected = { viewModel.onUtilitySelected(it) },
                        options = billFormState.utilityList,
                        selectedOption = billFormState.selectedUtility,
                        title = stringResource(id = R.string.item)
                    )
                    Divider(color = LightHouse)
                    Spacer(modifier = Modifier.height(8.dp))

                    FormDueDateSection(
                        modifier = Modifier.height(sectionHeight),
                        onDatePicked = viewModel::onDatePicked,
                        selectedDate = billFormState.dueDate
                    )
                    Divider(color = LightHouse)
                    Spacer(modifier = Modifier.height(16.dp))

                    FormAmountSection(
                        value = billFormState.amount,
                        onStateChanged = { newValue -> viewModel.onAmountChanged(newValue) },
                        focusRequester = focusRequester
                    )
                    if (billFormState.showAmountError) {
                        Text(
                            modifier = Modifier.height(18.dp),
                            text = stringResource(id = R.string.empty_field),
                            color = Color.Red,
                            style = MaterialTheme.typography.caption
                        )
                    } else {
                        Spacer(modifier = Modifier.height(18.dp))
                    }
                    Spacer(modifier = Modifier.height(20.dp))

                    FormNoteSection(
                        value = billFormState.note,
                        onStateChanged = viewModel::onNoteChanged,
                        focusRequester = focusRequester
                    )
                    Spacer(modifier = Modifier.height(26.dp))

                    FormPaidNotPaidOptionsSection(
                        isBillPaid = billFormState.isPaid,
                        changePaidStatusTo = viewModel::changePaidStatusTo,
                    )
                    Spacer(modifier = Modifier.height(90.dp))
                }

                SaveButton(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    onSaveClick = viewModel::onSaveClick ,
                    text = billFormState.actionButtonText
                )
            }

            if(showConfirmDeleteDialog) {
                AlertDialog(
                    onDismissRequest = { showConfirmDeleteDialog = false },
                    title = { Text(text = stringResource(id = R.string.do_you_want_to_delete_bill)) },
                    confirmButton = {
                        TextButton(
                            modifier = Modifier.padding(4.dp),
                            onClick = {
                                showConfirmDeleteDialog = false
                                viewModel.onDeleteConfirmed()
                            }
                        ) {
                            Text(
                                text = stringResource(id = R.string.yes).uppercase(),
                                color = Jasper,
                                style = MaterialTheme.typography.body2,
                                fontWeight = FontWeight.Bold
                            )
                        }
                                    },
                    dismissButton = {
                        TextButton(
                            modifier = Modifier.padding(4.dp),
                            onClick = { showConfirmDeleteDialog = false }
                        ) {
                            Text(
                                text = stringResource(id = R.string.no).uppercase(),
                                style = MaterialTheme.typography.body2,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    },
                )
            }

            if(showQrScannerDialog) {
                val context = LocalContext.current
                val snackBarMsg = stringResource(id = R.string.to_use_barcode_scanner_enable_camera_permission)
                var hasCamPermission by remember {
                    mutableStateOf(
                        ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.CAMERA
                        ) == PackageManager.PERMISSION_GRANTED
                    )
                }
                val launcher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.RequestPermission(),
                    onResult = { granted ->
                        hasCamPermission = granted
                        if (!granted) {
                            snackbarCoroutineScope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = snackBarMsg,
                                    duration = SnackbarDuration.Short
                                )
                            }
                        }
                    }
                )
                LaunchedEffect(key1 = true) {
                    launcher.launch(Manifest.permission.CAMERA)
                }

                if(hasCamPermission) {
                    Dialog(
                        onDismissRequest = { showQrScannerDialog = false }) {
                        BarCodeScannerDialog(
                            onCloseClicked = { showQrScannerDialog = false },
                            onBarCodeScanned = { barCode ->
                                showQrScannerDialog = false
                                viewModel.onBarCodeScanned(barCode)
                            }
                        )
                    }
                }
            }
        }
    )
}
