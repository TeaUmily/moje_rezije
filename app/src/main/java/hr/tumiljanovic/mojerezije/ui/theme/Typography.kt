package hr.tumiljanovic.mojerezije.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import hr.tumiljanovic.mojerezije.R

private val Quicksand = FontFamily(
    Font(R.font.quicksand_regular),
    Font(R.font.quicksand_medium, FontWeight.W500),
    Font(R.font.quicksand_bold, FontWeight.W700)
)

val AppTypography = Typography (defaultFontFamily = Quicksand)