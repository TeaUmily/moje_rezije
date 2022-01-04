package hr.tumiljanovic.mojerezije.ui.billsDisplay

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import hr.tumiljanovic.mojerezije.common.constants.EMPTY_BILL_ID
import hr.tumiljanovic.mojerezije.common.constants.IN_APP_DATE_FORMAT
import hr.tumiljanovic.mojerezije.common.utils.DateUtils
import hr.tumiljanovic.mojerezije.ui.elements.BillCell
import hr.tumiljanovic.mojerezije.ui.elements.TopBar
import hr.tumiljanovic.mojerezije.ui.theme.LightHouse

@Composable
fun BillsDisplayScreen(
    upPressCallback: () -> Unit,
    navigateToBillForm: (String, String) -> Unit,
    viewModel: BillsDisplayViewModel = hiltViewModel(),
) {
    val billsDisplayState = viewModel.billsDisplayState
    viewModel.loadScreenData()

    Scaffold(
        topBar = {
            TopBar(title = billsDisplayState.title,
                onBackPressed = upPressCallback
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(onClick = { navigateToBillForm(billsDisplayState.utilityName, EMPTY_BILL_ID) }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "fab icon")
            }
        },
        content = {
            LazyColumn(
                modifier = Modifier.padding(horizontal = 40.dp),
                content = {
                    itemsIndexed(billsDisplayState.bills) {index, bill ->
                        BillCell(
                            amount = bill.amount.toString(),
                            date = DateUtils.dateToFormat(IN_APP_DATE_FORMAT, bill.dueDate),
                            dotColor = billsDisplayState.dotsColor,
                            onBillCellClick = { navigateToBillForm(bill.utilityTitle, bill.id) }
                        )
                        if (index < billsDisplayState.bills.size - 1) {
                            Divider(color = LightHouse)
                        }
                    }
                }
            )
        }
    )
}