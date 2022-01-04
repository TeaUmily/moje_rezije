package hr.tumiljanovic.mojerezije.ui.reminderSettings.ui

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import hr.tumiljanovic.mojerezije.ui.theme.LightHouse
import hr.tumiljanovic.mojerezije.ui.theme.MineShaft

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    label: String,
    onSelected: () -> Unit
) {
    TextButton(
        modifier = modifier
            .width(72.dp)
            .height(40.dp),
        onClick = onSelected,
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = if(isSelected) MineShaft else LightHouse)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold,
            color = if(isSelected) White else MineShaft
        )
    }

}