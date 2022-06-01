import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun IdSearchField(value: TextFieldValue, handleValueChange: (TextFieldValue) -> Unit, handleSearchClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = value,
            onValueChange = handleValueChange,
            label = { Text(text = "ID") },
            modifier = Modifier
                .fillMaxWidth(.8f)
                .defaultMinSize(minHeight = 50.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
        Spacer(modifier = Modifier.fillMaxWidth(.1f))
        Button(
            onClick = handleSearchClick,
            modifier = Modifier
                .defaultMinSize(minHeight = 50.dp)
                .fillMaxWidth(),
        ) {
            Icon(Icons.Filled.Search, "Search")
        }
    }
}