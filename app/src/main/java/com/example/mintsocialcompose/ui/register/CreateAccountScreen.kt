package com.example.mintsocialcompose.ui.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Photo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mintsocialcompose.R
import com.example.mintsocialcompose.ui.components.MintTextField
import com.example.mintsocialcompose.ui.components.PasswordTextField
import com.example.mintsocialcompose.ui.theme.MainLight

@Composable
fun CreateAccountBody() {
    val email = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }
    val confirmPassword = rememberSaveable { mutableStateOf("") }
    Surface(
        color = MainLight,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(
                top = 65.dp, start = 32.dp, end = 32.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.padding(bottom = 15.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.profile_image),
                    contentDescription = null,
                    modifier = Modifier.clip(shape = CircleShape)
                )
            }
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
            PasswordTextField(
                placeholder = "Enter password",
                inputValue = password
            )
            Button(
                onClick = {},
                enabled = false,
                modifier = Modifier.padding(bottom = 21.dp)
            ) {
                Icon(Icons.Filled.Create, contentDescription = null)
                Text("Create Account", fontSize = 20.sp)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewLogin() {
    CreateAccountBody()
}
