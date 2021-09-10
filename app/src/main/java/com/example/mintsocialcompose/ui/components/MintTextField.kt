package com.example.mintsocialcompose.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mintsocialcompose.ui.theme.Invalid

@Composable
fun MintTextField(
    placeholder: String,
    inputValue: String,
    onInputValueChange: (String) -> Unit,
    isError: Boolean,
    errorText: String,
    singleLine: Boolean,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        TextField(
            value = inputValue,
            onValueChange = onInputValueChange,
            placeholder = { Text(text = placeholder, color = Color.Gray) },
            modifier = Modifier
                .clip(shape = CircleShape),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color.White,
                textColor = Color.Black,
            ),
            singleLine = singleLine,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            isError = isError,
            trailingIcon = {
                if (isError) Icon(Icons.Filled.Error, null)
            }
        )
        if (isError) {
            Text(
                text = errorText,
                color = Invalid,
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .padding(start = 20.dp, top = 2.dp)
            )
        }
    }
}