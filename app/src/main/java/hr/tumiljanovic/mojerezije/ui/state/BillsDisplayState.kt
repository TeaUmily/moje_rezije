package hr.tumiljanovic.mojerezije.ui.state

import androidx.compose.ui.graphics.Color
import hr.tumiljanovic.mojerezije.common.model.Utility
import hr.tumiljanovic.mojerezije.domain.bill.model.Bill
import hr.tumiljanovic.mojerezije.ui.theme.SnowPea

data class BillsDisplayState (
    val title: String = "",
    val bills: List<Bill> = listOf(),
    val dotsColor: Color = SnowPea,
    val utilityName: String = ""
)
