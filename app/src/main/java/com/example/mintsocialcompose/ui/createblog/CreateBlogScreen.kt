package com.example.mintsocialcompose.ui.createblog

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mintsocialcompose.ui.components.MintTextField
import com.example.mintsocialcompose.ui.theme.Correct
import com.example.mintsocialcompose.ui.theme.MainDark
import com.example.mintsocialcompose.ui.theme.MainLight

@Composable
fun CreateBlogBody() {
    val imageUrl = rememberSaveable { mutableStateOf("") }
    val articleTitle = rememberSaveable { mutableStateOf("") }
    val content = rememberSaveable { mutableStateOf("") }

    Surface(
        color = MainLight,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier.padding(
                10.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MintTextField(
                placeholder = "Cover image url...",
                inputValue = imageUrl,
                singleLine = true,
                keyboardType = KeyboardType.Uri,
                imeAction = ImeAction.Next,
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .fillMaxWidth()
            )
            MintTextField(
                placeholder = "Article title...",
                inputValue = articleTitle,
                singleLine = true,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .fillMaxWidth()
            )
            Box(
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .border(
                        width = 3.dp, color = Correct, shape = RoundedCornerShape(10.dp)
                    )
                    .height(300.dp)
                    .fillMaxWidth()
            ) {
                MintTextField(
                    placeholder = "Write out your article here... Don't be shy!",
                    inputValue = content,
                    singleLine = false,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done,
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(10.dp))
                        .fillMaxSize()
                )
            }
            Button(
                onClick = { /* TODO */ }
            ) {
                Icon(Icons.Filled.Save, contentDescription = null)
                Text("Post", fontSize = 20.sp)
            }
        }
    }
}