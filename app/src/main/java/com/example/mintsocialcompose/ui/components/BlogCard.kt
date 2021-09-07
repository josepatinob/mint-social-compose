package com.example.mintsocialcompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mintsocialcompose.R
import com.example.mintsocialcompose.model.Blog

@Composable
fun BlogCard(blog: Blog, onItemClick: (String) -> Unit = {}) {
    Card(
        modifier = Modifier
            .padding(top = 5.dp, start = 10.dp, end = 10.dp, bottom = 5.dp)
            .fillMaxWidth()
            .clickable { onItemClick(blog.blogId) },
        elevation = 10.dp
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_image),
                contentDescription = null,
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .width(100.dp)
                    .height(100.dp)
            )
            Column(
                modifier = Modifier
                    .padding(start = 15.dp)
                    .fillMaxHeight()
            ) {
                Text(
                    text = "Kim Kardashian's Top Picks because its never enough",
                    fontSize = 21.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    modifier = Modifier
                        .padding(top = 23.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = null,
                            modifier = Modifier.clip(CircleShape),
                            tint = Color.Gray
                        )
                        Text(
                            text = "Kris Jenner",
                            fontSize = 18.sp,
                            color = Color.Gray
                        )
                    }
                    Text(
                        text = "6/10/2021",
                        fontSize = 18.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}