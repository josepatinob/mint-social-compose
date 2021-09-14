package com.example.mintsocialcompose.ui.bloglist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mintsocialcompose.model.Blog
import com.example.mintsocialcompose.type.BlogFilter
import com.example.mintsocialcompose.type.Status
import com.example.mintsocialcompose.ui.components.BlogCard
import com.example.mintsocialcompose.ui.components.MintChipButton
import com.example.mintsocialcompose.ui.components.MintProgressIndicator
import com.example.mintsocialcompose.ui.theme.MainLight
import com.example.mintsocialcompose.ui.theme.Mint

@Composable
fun BlogListBody(
    onItemClick: (String) -> Unit,
    blogList: List<Blog>,
    blogFilterList: List<BlogFilter>,
    status: Status,
    currentFilter: BlogFilter,
    onFilterChange: (BlogFilter) -> Unit
) {
    Column {
        LazyRow(
            modifier = Modifier
                .background(Mint)
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            items(blogFilterList) { filter ->
                MintChipButton(
                    filter = filter,
                    isSelected = currentFilter == filter,
                    setSelected = onFilterChange
                )
            }
        }
        if (status == Status.Loading) {
            MintProgressIndicator()
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .background(color = MainLight)
                    .padding(top = 5.dp)
            ) {
                items(blogList) { blog ->
                    BlogCard(blog = blog, onItemClick = onItemClick)
                }
            }
        }
    }
}