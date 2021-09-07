package com.example.mintsocialcompose.ui.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mintsocialcompose.ui.components.AnnotatedClickableText
import com.example.mintsocialcompose.ui.components.MintTextField
import com.example.mintsocialcompose.ui.components.PasswordTextField
import com.example.mintsocialcompose.ui.theme.MainLight
import com.example.mintsocialcompose.ui.theme.MainMaroon

@Composable
fun LoginBody(
    onLoginClick: () -> Unit = {},
    onGuestClick: () -> Unit = {},
    onCreateAccountClick: () -> Unit = {},
) {
    val email = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }

    Surface(
        color = MainLight,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(
                top = 95.dp, start = 27.dp, end = 27.dp, bottom = 55.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnnotatedClickableText(
                nonClickText = "Hey! Sign in below or",
                clickableText = "Create a new account!",
                onCreateAccountClick = onCreateAccountClick
            )
            Spacer(Modifier.size(55.dp))
            MintTextField(
                placeholder = "Enter email",
                inputValue = email,
                singleLine = true,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next,
                modifier = Modifier
                    .padding(bottom = 25.dp)
                    .clip(shape = CircleShape)
            )
            PasswordTextField(
                placeholder = "Enter password",
                inputValue = password
            )
            Button(
                onClick = onLoginClick, modifier = Modifier.padding(bottom = 25.dp)
            ) {
                Icon(Icons.Filled.Person, contentDescription = null)
                Text("Login", fontSize = 20.sp)
            }
            TextButton(onClick = onGuestClick) {
                Text(
                    text = "Proceed as Guest",
                    style = TextStyle(textDecoration = TextDecoration.Underline),
                    fontSize = 20.sp,
                    color = MainMaroon
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewLogin() {
    LoginBody()
}
