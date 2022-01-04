package hr.tumiljanovic.mojerezije.ui.elements

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hr.tumiljanovic.mojerezije.ui.theme.GraniteGray

@Composable 
fun SaveButton(
    modifier : Modifier = Modifier,
    onSaveClick: () -> Unit,
    text: String
){
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(54.dp),
        colors = ButtonDefaults.buttonColors(GraniteGray),
        shape = RoundedCornerShape(24f, 0f, 24f, 0f),
        onClick = { onSaveClick() }
    ) {
        Text(
            text = text.uppercase(),
            style = MaterialTheme.typography.h6)
    }
}
