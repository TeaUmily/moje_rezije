package hr.tumiljanovic.mojerezije.ui.utilityDetails.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import hr.tumiljanovic.mojerezije.common.constants.IN_APP_DATE_FORMAT
import hr.tumiljanovic.mojerezije.common.utils.DateUtils
import hr.tumiljanovic.mojerezije.domain.bill.model.Bill
import hr.tumiljanovic.mojerezije.ui.theme.GraniteGray
import hr.tumiljanovic.mojerezije.ui.theme.LightHouse
import hr.tumiljanovic.mojerezije.R
import hr.tumiljanovic.mojerezije.ui.elements.BillCell


@Composable
fun BillListWithLabel(
    modifier: Modifier = Modifier,
    label: String,
    billList: List<Bill>,
    dotColor: Color,
    displayShowAllBtn: Boolean,
    onShowAllClick: () -> Unit,
    navigateToBillForm: (String, String) -> Unit
) {
    Surface(
        modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 80.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(24f, 0f, 24f, 0f)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 10.dp)
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.subtitle1,
                color = GraniteGray
            )
            Spacer(modifier = Modifier.height(10.dp))
            if(billList.isNotEmpty()) {
                for (index in billList.indices) {
                    val bill = billList[index]
                    BillCell(
                        amount = bill.amount.toString(),
                        date = DateUtils.dateToFormat(IN_APP_DATE_FORMAT, bill.dueDate),
                        dotColor = dotColor,
                        onBillCellClick = { navigateToBillForm(bill.utilityTitle, bill.id) }
                    )
                    if (index < billList.size - 1) {
                        Divider(color = LightHouse)
                    }
                }
                if (displayShowAllBtn) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(modifier = modifier.fillMaxWidth()
                    ) {
                        TextButton(
                            modifier = Modifier.align(Alignment.CenterEnd),
                            onClick = onShowAllClick
                        ) {
                            Text(
                                style = MaterialTheme.typography.body2,
                                text = stringResource(id = R.string.show_all).uppercase()
                            )
                        }
                    }
                }
            } else {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.still_no_input),
                    style = MaterialTheme.typography.subtitle2,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}