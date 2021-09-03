package com.example.mintsocialcompose.ui.components

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.example.mintsocialcompose.MintScreen
import com.example.mintsocialcompose.ui.theme.MainMaroon

@Composable
fun AnnotatedClickableText(
    nonClickText: String,
    clickableText: String,
    onCreateAccountClick: () -> Unit = {}
) {
    val annotatedText = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color.White)) {
            append("$nonClickText ")
        }
        // We attach this *URL* annotation to the following content
        // until `pop()` is called
        pushStringAnnotation(
            tag = "LINK",
            annotation = MintScreen.CreateAccount.name
        )
        withStyle(
            style = SpanStyle(color = MainMaroon, textDecoration = TextDecoration.Underline)
        ) {
            append(clickableText)
        }
        pop()
    }

    ClickableText(
        text = annotatedText,
        style = TextStyle(fontSize = 40.sp),
        onClick = { offset ->
            // We check if there is an *URL* annotation attached to the text
            // at the clicked position
            annotatedText.getStringAnnotations(
                tag = "LINK", start = offset,
                end = offset
            ).firstOrNull()?.let {
                onCreateAccountClick()
            }
        },
    )
}