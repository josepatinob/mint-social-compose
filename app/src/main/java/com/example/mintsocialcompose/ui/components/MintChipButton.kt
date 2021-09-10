package com.example.mintsocialcompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mintsocialcompose.model.Blog
import com.example.mintsocialcompose.type.BlogFilter
import com.example.mintsocialcompose.ui.theme.MintLight

@Composable
fun MintChipButton(
    filter: BlogFilter,
    isSelected: Boolean,
    setSelected: (BlogFilter) -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(5.dp)
            .clip(shape = CircleShape)
            .toggleable(
                value = isSelected,
                onValueChange = { setSelected(filter) }
            )
    ) {
        Row(
            Modifier
                .background(color = if (isSelected) MintLight else Color.White)
                .padding(start = 15.dp, top = 5.dp, bottom = 0.dp, end = 15.dp)
                .height(28.dp)
        ) {
            if (isSelected) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = null,
                )
            }
            Text(
                text = filter.name,
                style = TextStyle(color = Color.Gray),
                fontSize = 16.sp,
                modifier = Modifier.padding(0.dp)
            )
        }
    }
}