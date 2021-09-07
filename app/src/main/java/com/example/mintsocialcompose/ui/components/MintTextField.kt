package com.example.mintsocialcompose.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun MintTextField(
    placeholder: String,
    inputValue: MutableState<String>,
    singleLine: Boolean,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = inputValue.value,
        onValueChange = { inputValue.value = it },
        placeholder = { Text(text = placeholder) },
        modifier = modifier,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = Color.White,
            textColor = Color.Black,
        ),
        singleLine = singleLine,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        )
    )
}