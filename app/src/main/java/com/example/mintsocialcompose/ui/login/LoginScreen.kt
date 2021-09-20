package com.example.mintsocialcompose.ui.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import com.example.mintsocialcompose.type.Status
import com.example.mintsocialcompose.ui.components.AnnotatedClickableText
import com.example.mintsocialcompose.ui.components.MintProgressIndicator
import com.example.mintsocialcompose.ui.components.MintTextField
import com.example.mintsocialcompose.ui.components.PasswordTextField
import com.example.mintsocialcompose.ui.theme.MainLight
import com.example.mintsocialcompose.ui.theme.MainMaroon

@Composable
fun LoginBody(
    onLoginClick: () -> Unit,
    onGuestClick: () -> Unit,
    onCreateAccountClick: () -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    emailError: Boolean,
    password: String,
    onPasswordChange: (String) -> Unit,
    loginEnabled: Boolean,
    status: Status,
    isSignedIn: Boolean?,
    onAlreadyLoggedIn: () -> Unit
) {

    if (isSignedIn == true) {
        LaunchedEffect(isSignedIn) {
            onAlreadyLoggedIn()
        }
    }

    if (status == Status.Loading) {
        MintProgressIndicator()
    } else {
        Surface(
            color = MainLight,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
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
                    onInputValueChange = onEmailChange,
                    isError = emailError,
                    errorText = "Please enter a valid email!",
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Email
                    ),
                    keyboardActions = KeyboardActions(onNext = { /* Investigate this further */ }),
                    modifier = Modifier
                        .padding(bottom = 20.dp)
                )
                PasswordTextField(
                    placeholder = "Enter password",
                    inputValue = password,
                    onInputValueChange = onPasswordChange,
                    isError = false,
                    errorText = "Please enter stronger password!",
                    modifier = Modifier.padding(bottom = 20.dp)
                )
                Button(
                    onClick = onLoginClick,
                    modifier = Modifier.padding(bottom = 25.dp),
                    enabled = loginEnabled
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
}