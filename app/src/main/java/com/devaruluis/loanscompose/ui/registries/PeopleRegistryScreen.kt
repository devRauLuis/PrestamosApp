package com.devaruluis.loanscompose.ui.registries

import IdSearchField
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devaruluis.loanscompose.model.Occupation
import com.devaruluis.loanscompose.model.Person
import com.devaruluis.loanscompose.ui.components.form.RegistryActionButtons

data class PeopleForm(
    var id: TextFieldValue = TextFieldValue(""),
    var names: TextFieldValue = TextFieldValue(""),
    var surnames: TextFieldValue = TextFieldValue(""),
    var income: TextFieldValue = TextFieldValue(""),
    var occupation: Occupation = Occupation(0, "")
)

fun PeopleForm.toPerson() = Person(
    id = if (id.text.isEmpty()) 0 else id.text.toLong(),
    names = names.text,
    surnames = surnames.text,
    income = income.text.toFloat(), occupationId = 0
)


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PeopleRegistryBody() {

    var form by remember { mutableStateOf(PeopleForm()) }
    val occupationList = listOf<Occupation>(
        Occupation(id = 1, description = "Developer"),
        Occupation(id = 2, description = "QA")
    )

    var showOccupationDropdown by remember { mutableStateOf(false) }

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
            Text(text = "Personas", fontSize = 24.sp)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            ) {
                IdSearchField(value = form.id,
                    handleValueChange = { form.id = it },
                    handleSearchClick = {
//                            personViewModel.find(form.id.text.toLong())
                    }
                )

                TextField(
                    value = form.names,
                    onValueChange = { form.names = it },
                    label = { Text(text = "Nombres") },
                    modifier = textFieldModifier
                )
                TextField(
                    value = form.surnames,
                    onValueChange = { form.surnames = it },
                    label = { Text(text = "Apellidos") },
                    modifier = textFieldModifier
                )
                TextField(
                    value = form.income,
                    onValueChange = { form.income = it },
                    label = { Text(text = "Ingresos") },
                    modifier = textFieldModifier,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                )

                ExposedDropdownMenuBox(
                    expanded = showOccupationDropdown,
                    onExpandedChange = { showOccupationDropdown = !showOccupationDropdown },

                    ) {
                    TextField(
                        readOnly = true,
                        value = form.occupation.description.toString(),
                        onValueChange = {},
                        label = { Text(text = "Ocupación") },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(
                                expanded = showOccupationDropdown
                            )
                        },
                        colors = ExposedDropdownMenuDefaults.textFieldColors(),
                        modifier = textFieldModifier
                    )
                    ExposedDropdownMenu(
                        expanded = showOccupationDropdown,
                        onDismissRequest = { showOccupationDropdown = !showOccupationDropdown }) {
                        occupationList.forEach {
                            DropdownMenuItem(onClick = {
                                form.occupation = it
                                showOccupationDropdown = false
                            }) {
                                Text(text = it.description.toString())
                            }
                        }
                    }
                }

                RegistryActionButtons(
                    handleNewClick = {
                        form = PeopleForm()
                    },
                    handleSaveClick = {
//                        personViewModel.save(
//form.toPerson()
//                        )
                    }, handleDeleteClick = {
//                        personViewModel.deleteCurrentPerson()
                    })
            }

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PeopleRBodyPreview() {
    Surface {
        PeopleRegistryBody()
    }
}