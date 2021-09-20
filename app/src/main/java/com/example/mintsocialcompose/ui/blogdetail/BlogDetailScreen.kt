package com.example.mintsocialcompose.ui.blogdetail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mintsocialcompose.R
import com.example.mintsocialcompose.model.Blog
import com.example.mintsocialcompose.type.Status
import com.example.mintsocialcompose.ui.components.MintProgressIndicator
import com.example.mintsocialcompose.ui.theme.Accent
import com.example.mintsocialcompose.ui.theme.MainLight
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun BlogDetailBody(
    blog: Blog?,
    isLoading: Boolean,
    onProfileClick: (String, String) -> Unit,
    errorMessages: List<String>,
    onNetworkError: (String) -> Unit
) {

    if (errorMessages.isNotEmpty()) {
        LaunchedEffect(errorMessages) {
            onNetworkError(errorMessages[0])
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MainLight)
    ) {
        if (isLoading) {
            MintProgressIndicator()
        } else {
            Surface(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(shape = RoundedCornerShape(30.dp))
            ) {
                Box {
                    GlideImage(
                        imageModel = blog?.imageUrl ?: "",
                        // Crop, Fit, Inside, FillHeight, FillWidth, None
                        contentScale = ContentScale.Crop,
                        // shows an image with a circular revealed animation.
                        circularReveal = CircularReveal(duration = 550),
                        // shows a placeholder ImageBitmap when loading.
//            placeHolder = ImageBitmap.imageResource(R.drawable.loading),
//            // shows an error ImageBitmap when the request failed.
//            error = ImageBitmap.imageResource(R.drawable.ic_connection_error)
                        modifier = Modifier
                            .height(300.dp)
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
                                top = 190.dp,
                                start = 15.dp,
                                end = 15.dp,
                                bottom = 15.dp
                            )
                            .verticalScroll(rememberScrollState()),
                    ) {
                        Text(
                            text = blog?.title ?: "",
                            fontSize = 31.sp,
                            fontWeight = FontWeight.W400,
                            color = Color.DarkGray
                        )
                        BlogDetailProfile(
                            onProfileClick = onProfileClick,
                            authorId = blog?.authorId ?: "",
                            authorEmail = blog?.authorEmail ?: "",
                            authorName = blog?.authorName ?: "",
                            createdDate = blog?.createdDate.toString() ?: ""
                        )
                        Divider(
                            color = Accent,
                            modifier = Modifier
                                .padding(0.dp, 15.dp)
                                .fillMaxWidth(),
                            thickness = 1.dp
                        )
                        Text(text = blog?.content ?: "")
                    }
                }
            }
        }
    }
}

@Composable
fun BlogDetailProfile(
    onProfileClick: (String, String) -> Unit,
    authorEmail: String,
    authorId: String,
    authorName: String,
    createdDate: String,
) {
    Row(
        modifier = Modifier.padding(5.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile_image),
            contentDescription = null,
            modifier = Modifier
                .clip(shape = CircleShape)
                .width(65.dp)
                .height(65.dp)
                .clickable {
                    onProfileClick(authorId, authorEmail)
                }
        )
        Column(
            modifier = Modifier.padding(start = 5.dp)
        ) {
            Text(
                text = authorName,
                fontSize = 28.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.clickable {
                    onProfileClick(authorId, authorEmail)
                }
            )
            Row {
                Text(
                    text = "Posted",
                    fontSize = 18.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.size(3.dp))
                Text(
                    text = createdDate,
                    fontSize = 18.sp,
                    color = Color.Gray
                )
            }
        }
    }
}