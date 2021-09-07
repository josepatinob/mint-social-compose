package com.example.mintsocialcompose.ui.bloglist

import android.view.RoundedCorner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mintsocialcompose.R
import com.example.mintsocialcompose.model.Blog
import com.example.mintsocialcompose.ui.components.BlogCard
import com.example.mintsocialcompose.ui.components.MintChipButton
import com.example.mintsocialcompose.ui.theme.MainLight
import com.example.mintsocialcompose.ui.theme.Mint

@Composable
fun BlogListBody(onItemClick: (String) -> Unit) {
    val chipFilterList = listOf("All", "My blogs", "Other blogs")
    val blogList = Blog.getBlogList()
    Column {
        LazyRow(
            modifier = Modifier
                .background(Mint)
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            items(chipFilterList) {
                MintChipButton(text = it)
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(color = MainLight)
                .padding(top = 5.dp)
        ) {
            items(blogList) {
                BlogCard(blog = it, onItemClick = onItemClick)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun BlogListPreview() {
    BlogListBody({})
}