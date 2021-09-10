package com.example.mintsocialcompose.ui.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mintsocialcompose.R
import com.example.mintsocialcompose.ui.components.MintTextField
import com.example.mintsocialcompose.ui.components.PasswordTextField
import com.example.mintsocialcompose.ui.theme.MainLight

@Composable
fun RegisterBody(
    email: String,
    onEmailChange: (String) -> Unit,
    emailError: Boolean,
    password: String,
    onPasswordChange: (String) -> Unit,
    passwordError: Boolean,
    confirmPassword: String,
    onConfirmPasswordChange: (String) -> Unit,
    confirmPasswordError: Boolean,
    emailPasswordValid: Boolean,
) {
    Surface(
        color = MainLight,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier.padding(
                top = 65.dp, start = 32.dp, end = 32.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_image),
                contentDescription = null,
                modifier = Modifier
                    .padding(bottom = 15.dp)
                    .clip(shape = CircleShape)
            )
            Button(
                onClick = {},
                enabled = false,
                modifier = Modifier.padding(bottom = 21.dp)
            ) {
                Icon(Icons.Filled.Photo, contentDescription = null)
                Text("Upload Photo", fontSize = 20.sp)
            }
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
                isError = passwordError,
                errorText = "Please enter stronger password!",
                modifier = Modifier.padding(bottom = 20.dp)
            )
            PasswordTextField(
                placeholder = "Confirm password",
                inputValue = confirmPassword,
                onInputValueChange = onConfirmPasswordChange,
                isError = confirmPasswordError,
                errorText = "Passwords do not match!",
                modifier = Modifier.padding(bottom = 20.dp)
            )
            Button(
                onClick = {

                },
                enabled = emailPasswordValid,
                modifier = Modifier.padding(bottom = 21.dp)
            ) {
                Icon(Icons.Filled.Add, contentDescription = null)
                Text("Create Account", fontSize = 20.sp)
            }
        }
    }
}
