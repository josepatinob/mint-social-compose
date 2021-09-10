package com.example.mintsocialcompose.ui.createblog

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mintsocialcompose.type.Status
import com.example.mintsocialcompose.ui.components.MintProgressIndicator
import com.example.mintsocialcompose.ui.components.MintTextField
import com.example.mintsocialcompose.ui.theme.Correct
import com.example.mintsocialcompose.ui.theme.Invalid
import com.example.mintsocialcompose.ui.theme.MainDark
import com.example.mintsocialcompose.ui.theme.MainLight

@Composable
fun CreateBlogBody(
    imageUrl: String,
    onUrlChange: (String) -> Unit,
    imageUrlError: Boolean,
    blogTitle: String,
    onBlogTitleChange: (String) -> Unit,
    blogTitleError: Boolean,
    content: String,
    onContentChange: (String) -> Unit,
    charCount: Int,
    maxCharCount: Int,
    contentError: Boolean,
    onSubmit: () -> Unit,
    status: Status
) {
    val onSubmitEnabled =
        !contentError
                && !imageUrlError
                && imageUrl.isNotEmpty()
                && blogTitle.isNotEmpty()
                && content.isNotEmpty()
    Surface(
        color = MainLight,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        if (status == Status.Loading) {
            MintProgressIndicator()
        } else {
            Column(
                modifier = Modifier.padding(
                    10.dp
                ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CreateBlogTextField(
                    placeholder = "Cover image url...",
                    inputValue = imageUrl,
                    onInputValueChange = onUrlChange,
                    isError = imageUrlError, //update this later
                    errorText = "Must enter a valid image url!",
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Uri
                    ),
                    keyboardActions = KeyboardActions(onNext = { /* Investigate this further */ }),
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(10.dp))
                        .fillMaxWidth()
                )
                CreateBlogTextField(
                    placeholder = "Article title...",
                    inputValue = blogTitle,
                    onInputValueChange = onBlogTitleChange,
                    isError = blogTitleError, //update this later
                    errorText = "Title must not be empty!",
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Text
                    ),
                    keyboardActions = KeyboardActions(onNext = { /* Investigate this further */ }),
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(10.dp))
                        .fillMaxWidth()
                )
                Box(
                    modifier = Modifier
                        .padding(bottom = 20.dp)
                        .border(
                            width = 3.dp,
                            color = if (contentError) Invalid else Correct,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .height(300.dp)
                        .fillMaxWidth()
                ) {
                    TextField(
                        value = content,
                        onValueChange = onContentChange,
                        placeholder = {
                            Text(
                                text = "Write out your article here... Don't be shy!",
                                color = Color.Gray
                            )
                        },
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(10.dp))
                            .fillMaxWidth()
                            .height(300.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = Color.White,
                            textColor = Color.Black,
                        ),
                        singleLine = false,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Text
                        ),
                        keyboardActions = KeyboardActions(onNext = { /* Investigate this further */ }),
                        isError = contentError,
                        trailingIcon = {}
                    )
                    WordCount(
                        maxNumber = maxCharCount,
                        currentCount = charCount,
                        isError = contentError,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .clip(
                                shape = RoundedCornerShape(
                                    topStart = 10.dp,
                                    topEnd = 0.dp,
                                    bottomEnd = 10.dp,
                                    bottomStart = 0.dp
                                )
                            )
                    )
                }
                Button(
                    onClick = { onSubmit() },
                    enabled = onSubmitEnabled
                ) {
                    Icon(Icons.Filled.Save, contentDescription = null)
                    Text("Post", fontSize = 20.sp)
                }
            }
        }
    }
}

@Composable
fun WordCount(
    maxNumber: Int,
    currentCount: Int,
    isError: Boolean,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        color = if (isError) Invalid else Correct
    ) {
        Text(
            text = "$currentCount / $maxNumber",
            color = Color.White,
            modifier = Modifier.padding(5.dp)
        )
    }
}

@Composable
fun CreateBlogTextField(
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
    Column(
        modifier = Modifier
            .padding(
                bottom = 20.dp
            )
    ) {
        TextField(
            value = inputValue,
            onValueChange = onInputValueChange,
            placeholder = { Text(text = placeholder, color = Color.Gray) },
            modifier = modifier,
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