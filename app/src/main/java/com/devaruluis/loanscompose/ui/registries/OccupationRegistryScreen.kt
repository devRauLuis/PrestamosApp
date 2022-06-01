package com.devaruluis.loanscompose.ui.registries

import IdSearchField
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devaruluis.loanscompose.ui.components.form.RegistryActionButtons

data class OccupationForm(
    var id: TextFieldValue = TextFieldValue(""),
    var desc: TextFieldValue = TextFieldValue("")
)

@Composable
fun OccupationRegistryBody() {
    var form by remember { mutableStateOf(OccupationForm()) }

    val textFieldModifier = Modifier
        .fillMaxWidth()
        .defaultMinSize(minHeight = 50.dp)
        .padding(top = 10.dp)

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Column() {
            Text(text = "Ocupación", fontSize = 24.sp)

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            ) {
                IdSearchField(
                    value = form.id,
                    handleValueChange = { form.id = it },
                    handleSearchClick = {})
                TextField(
                    value = form.desc, onValueChange = { form.desc = it },
                    label = { Text(text = "Descripción") },
                    modifier = textFieldModifier
                )
                RegistryActionButtons(
                    handleNewClick = { /*TODO*/ },
                    handleSaveClick = { /*TODO*/ }, handleDeleteClick = {/*TODO*/ })
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OccupationRBodyPreview() {
    Surface {
        OccupationRegistryBody()
    }
}