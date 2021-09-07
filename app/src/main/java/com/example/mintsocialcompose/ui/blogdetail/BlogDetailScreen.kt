package com.example.mintsocialcompose.ui.blogdetail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mintsocialcompose.R
import com.example.mintsocialcompose.ui.theme.Accent
import com.example.mintsocialcompose.ui.theme.MainLight
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun BlogDetailBody(blogId: String, onProfileClick: () -> Unit) {
    val imageUrl =
        "https://images.unsplash.com/photo-1630630910992-46f7656ab211?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=633&q=80"

    val longText = """
        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam malesuada augue ipsum, vel commodo nisl varius vel. Mauris non felis a velit ullamcorper porta in a lacus. Vivamus vehicula nisl tellus. Vestibulum faucibus sagittis lectus et porta. Etiam ultrices nibh nibh, ac auctor nunc dignissim eu. Etiam non enim a dolor maximus facilisis et consequat odio. Integer consectetur et metus non aliquam. Aliquam erat volutpat. Duis vel mi diam.

        Etiam a tempor tellus. In hac habitasse platea dictumst. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Sed bibendum augue nec orci aliquam, eget luctus nunc pulvinar. Nunc vulputate pharetra placerat. Sed et metus nec leo hendrerit sodales imperdiet sit amet ipsum. Proin dictum a turpis eu euismod. Nulla tristique, lectus vel imperdiet dapibus, ligula tortor lacinia augue, non rhoncus tellus diam nec augue. Mauris maximus neque id erat tincidunt rhoncus. Maecenas tristique hendrerit pharetra. Integer id elementum risus, sit amet pretium justo. Vivamus congue rhoncus nunc, et lobortis velit dictum ut. Donec porta quam ut sem rhoncus, non commodo dui venenatis. Maecenas quis efficitur lectus. Duis lectus tortor, tempus tristique commodo vehicula, eleifend quis enim. Curabitur suscipit cursus nunc.

        Integer eleifend mi quam. Nunc elit elit, commodo finibus efficitur ac, sodales sed felis. Curabitur sit amet sapien mauris. Vivamus vulputate justo vitae finibus ultrices. Quisque finibus mollis faucibus. Duis eu lectus ut eros aliquet tempor vel vel diam. Mauris ultrices tortor accumsan, feugiat velit vitae, pulvinar arcu. Suspendisse egestas tincidunt justo, at posuere leo. Nam porttitor neque at felis commodo, ut convallis erat facilisis. Sed sodales maximus enim, vitae efficitur augue tristique non. Nullam ultrices placerat orci eu molestie. Suspendisse potenti. Ut finibus neque nec lorem consectetur, vitae eleifend ante condimentum. Proin accumsan feugiat ipsum, non suscipit sem porttitor non. Curabitur sollicitudin massa sit amet luctus iaculis. Phasellus et enim eu mauris viverra tincidunt vitae vulputate nunc.

        Integer ut metus tortor. Duis venenatis odio nisi, eu blandit urna laoreet sed. Mauris ac nulla vitae lectus tempus ullamcorper at et est. Mauris pellentesque, tellus id semper pellentesque, est est euismod mauris, id fringilla est lorem et nibh. Donec posuere, nulla vel congue accumsan, ligula nisi ullamcorper risus, et pretium libero ipsum in magna. Etiam auctor nec nunc at lobortis. Donec vel molestie ex. Sed euismod fringilla vulputate. Quisque fringilla arcu mi. Suspendisse vitae purus id leo interdum molestie. Maecenas varius, mi accumsan vestibulum viverra, libero urna pellentesque elit, nec cursus libero nisl at sapien. Nullam ut neque id tortor lobortis finibus non nec ex. Vestibulum tincidunt condimentum porta.

        In fringilla fermentum quam. Duis quis magna ut magna vehicula accumsan. Nunc nec augue a elit molestie porttitor. Praesent suscipit luctus ex, ac euismod lectus cursus non. Donec ultrices ipsum dui, id venenatis turpis tempor eu. In sed sapien ac mauris volutpat luctus at a felis. In ultrices ante id elit egestas tempus. Praesent vel scelerisque ex. Nulla ut tempor est, non dapibus velit. Phasellus lobortis cursus dapibus. Donec faucibus molestie varius. Vivamus est neque, lobortis quis consectetur at, vehicula id dui.
    """.trimIndent()

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
                GlideImage(
                    imageModel = imageUrl,
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
                            top = 170.dp,
                            start = 15.dp,
                            end = 15.dp,
                            bottom = 15.dp
                        )
                        .verticalScroll(rememberScrollState()),
                ) {
                    Text(
                        text = "The Benefits of Rose Quartz",
                        fontSize = 31.sp,
                        fontWeight = FontWeight.W400,
                        color = Color.DarkGray
                    )
                    BlogDetailProfile(onProfileClick = onProfileClick)
                    Divider(
                        color = Accent,
                        modifier = Modifier
                            .padding(0.dp, 15.dp)
                            .fillMaxWidth(),
                        thickness = 1.dp
                    )
                    Text(text = longText)
                }
            }
        }
    }
}

@Composable
fun BlogDetailProfile(onProfileClick: () -> Unit) {
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
                    onProfileClick()
                }
        )
        Column(
            modifier = Modifier.padding(start = 5.dp)
        ) {
            Text(
                text = "Debra Rose",
                fontSize = 28.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.clickable {
                    onProfileClick()
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
                    text = "6/10/2021 2:00am",
                    fontSize = 18.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BlogDetailBodyPreview() {
    BlogDetailProfile({})
}
