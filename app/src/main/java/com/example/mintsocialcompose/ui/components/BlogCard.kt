package com.example.mintsocialcompose.ui.components

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
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mintsocialcompose.R
import com.example.mintsocialcompose.model.Blog
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun BlogCard(blog: Blog, onItemClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .padding(top = 5.dp, start = 10.dp, end = 10.dp, bottom = 5.dp)
            .height(120.dp)
            .fillMaxWidth()
            .clickable { onItemClick(blog.blogId) },
        elevation = 10.dp
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize()
        ) {
            GlideImage(
                imageModel = blog.imageUrl,
                // Crop, Fit, Inside, FillHeight, FillWidth, None
                contentScale = ContentScale.Crop,
                // shows an image with a circular revealed animation.
                circularReveal = CircularReveal(duration = 550),
                // shows a placeholder ImageBitmap when loading.
                placeHolder = ImageBitmap.imageResource(R.drawable.loading_image),
                // shows an error ImageBitmap when the request failed.
                error = ImageBitmap.imageResource(R.drawable.network_error),
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .width(100.dp)
                    .height(100.dp)
            )
            Column(
                modifier = Modifier
                    .padding(start = 15.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = blog.title,
                    fontSize = 21.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    modifier = Modifier
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
                            text = blog.authorName,
                            fontSize = 18.sp,
                            color = Color.Gray
                        )
                    }
                    Text(
                        text = blog.createdDate.toString(),
                        fontSize = 18.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}