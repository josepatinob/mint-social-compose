package com.example.mintsocialcompose.ui.profile

import android.text.Layout
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mintsocialcompose.R
import com.example.mintsocialcompose.model.Blog
import com.example.mintsocialcompose.ui.blogdetail.BlogDetailProfile
import com.example.mintsocialcompose.ui.components.BlogCard
import com.example.mintsocialcompose.ui.theme.*
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ProfileBody(onItemClick: (String) -> Unit) {
    var state by remember { mutableStateOf(0) }
    val tabs = listOf("Info", "Posts")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MainLight)
    ) {
        TabRow(
            selectedTabIndex = state,
            backgroundColor = Mint,
            contentColor = Color.White,
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = {
                        Text(title, fontSize = 30.sp, fontWeight = FontWeight.W400)
                    },
                    selected = state == index,
                    onClick = { state = index }
                )
            }
        }
        when (state) {
            0 -> InfoSection()
            1 -> PostSection(onItemClick = onItemClick)
        }
    }
}

@Composable
fun InfoSection() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MainLight)
    ) {
        Surface(
            modifier = Modifier
                .padding(8.dp)
                .clip(shape = RoundedCornerShape(30.dp))
        ) {
            Box {
                Image(
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = null
                )
                Column(
                    modifier = Modifier
                        .height(300.dp)
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                listOf(Color.Transparent, Color.White),
                                0f, 650f
                            )
                        )
                ) {}
                Column(
                    modifier = Modifier
                        .padding(
                            top = 250.dp,
                            start = 15.dp,
                            end = 15.dp,
                            bottom = 15.dp
                        ),
                    horizontalAlignment = CenterHorizontally
                ) {
                    Divider(
                        color = Accent,
                        modifier = Modifier
                            .padding(0.dp, 15.dp)
                            .fillMaxWidth(),
                        thickness = 1.dp
                    )
                    Text(text = "3 posts", fontSize = 20.sp, color = Color.Gray)
                    Text(text = "williams.karaAX@gmail.com", fontSize = 20.sp)
                }
            }
        }
    }
}

@Composable
fun PostSection(onItemClick: (String) -> Unit) {
    val blogList = Blog.getBlogList()
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

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    ProfileBody({})
}