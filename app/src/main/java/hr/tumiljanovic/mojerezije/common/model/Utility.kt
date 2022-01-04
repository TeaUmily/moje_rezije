package hr.tumiljanovic.mojerezije.common.model

import androidx.compose.ui.graphics.Color
import hr.tumiljanovic.mojerezije.R

enum class Utility(val titleId: Int, val iconId: Int, val color: Color, val value: Int) {
    ELECTRICITY( R.string.electricity, R.drawable.ic_electricity, Color(0xFFFFF1AC), 0),
    WATER(R.string.water, R.drawable.ic_water, Color(0xFFA6DCEF), 1),
    GAS(R.string.gas, R.drawable.ic_gas, Color(0xFFEA907A), 2),
    RESERVE(R.string.reserve, R.drawable.ic_reserve, Color(0xFFF5CDAA), 3),
    GARBAGE_COLLECTION(R.string.garbage_collection, R.drawable.ic_garbage_collection, Color(0xFFBCCC9A), 4),
    COMMUNAL( R.string.communal, R.drawable.ic_communal, Color(0xFFCDBBA7), 5),
    HRT_SUBSCRIPTION(R.string.hrt_subscription, R.drawable.ic_hrt_subscription, Color(0xFF8BCDCD), 6),
    TELECOMMUNICATIONS( R.string.telecommunications, R.drawable.ic_telecommunications, Color(0xFFCE97B0), 7)
}