package com.example.mintsocialcompose

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.example.mintsocialcompose.model.Blog
import com.example.mintsocialcompose.type.BlogFilter
import com.example.mintsocialcompose.type.Status
import com.example.mintsocialcompose.ui.blogdetail.BlogDetailBody
import com.example.mintsocialcompose.ui.blogdetail.BlogDetailViewModel
import com.example.mintsocialcompose.ui.bloglist.BlogListBody
import com.example.mintsocialcompose.ui.bloglist.BlogListViewModel
import com.example.mintsocialcompose.ui.createblog.CreateBlogBody
import com.example.mintsocialcompose.ui.createblog.CreateBlogViewModel
import com.example.mintsocialcompose.ui.login.LoginBody
import com.example.mintsocialcompose.ui.login.LoginViewModel
import com.example.mintsocialcompose.ui.profile.ProfileBody
import com.example.mintsocialcompose.ui.register.RegisterBody
import com.example.mintsocialcompose.ui.register.RegisterViewModel
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MintSocialNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = MintScreen.Login.name,
        modifier = modifier
    ) {
        composable(MintScreen.Login.name) {
            val loginViewModel: LoginViewModel = hiltViewModel()
            val email: String by loginViewModel.email.observeAsState("")
            val emailError: Boolean by loginViewModel.emailError.observeAsState(false)
            val password: String by loginViewModel.password.observeAsState("")
            val status: Status by loginViewModel.status.observeAsState(Status.Empty)

            LoginBody(
                onLoginClick = {
                    loginViewModel.signIn(email, password).invokeOnCompletion {
                        when (status) {
                            Status.Success -> navController.navigate(MintScreen.BlogList.name)
                            else -> {
                                Log.d("TESTING", status.toString())
                            }
                        }
                    }
                },
                onGuestClick = { navController.navigate(MintScreen.BlogList.name) },
                onCreateAccountClick = { navController.navigate(MintScreen.CreateAccount.name) },
                email = email,
                onEmailChange = { loginViewModel.onEmailChange(it) },
                emailError = emailError,
                password = password,
                onPasswordChange = { loginViewModel.onPasswordChange(it) },
                loginEnabled = !emailError && password.isNotEmpty(),
                status = status
            )
        }
        composable(MintScreen.BlogList.name) {
            val blogListViewModel: BlogListViewModel = hiltViewModel()
            val blogList: List<Blog> by blogListViewModel.blogList.observeAsState(emptyList())
            val status: Status by blogListViewModel.status.observeAsState(Status.Empty)
            val isSignedIn: Boolean by blogListViewModel.isSignedIn.observeAsState(false)
            val filter: BlogFilter by blogListViewModel.filter.observeAsState(BlogFilter.All)

            BlogListBody(
                onItemClick = { blogId ->
                    navController.navigate(
                        "${MintScreen.BlogDetail.name}/$blogId"
                    )
                },
                blogList = blogList,
                status = status,
                currentFilter = filter,
                onFilterChange = { blogListViewModel.onFilterChange(it) }
            )
        }
        composable(MintScreen.CreateAccount.name) {
            val registerViewModel: RegisterViewModel = hiltViewModel()
            val email: String by registerViewModel.email.observeAsState("")
            val emailError: Boolean by registerViewModel.emailError.observeAsState(false)

            val password: String by registerViewModel.password.observeAsState("")
            val passwordError: Boolean by registerViewModel.passwordError.observeAsState(false)

            val confirmPassword: String by registerViewModel.confirmPassword.observeAsState("")
            val confirmPasswordError: Boolean by registerViewModel.confirmPasswordError.observeAsState(
                false
            )

            val emailPasswordValid: Boolean by registerViewModel.allCriteriaMet.observeAsState(false)

            RegisterBody(
                email = email,
                onEmailChange = { registerViewModel.onEmailChange(it) },
                emailError = emailError,
                password = password,
                onPasswordChange = { registerViewModel.onPasswordChange(it) },
                passwordError = passwordError,
                confirmPassword = confirmPassword,
                onConfirmPasswordChange = { registerViewModel.onConfirmPasswordChange(it) },
                confirmPasswordError = confirmPasswordError,
                emailPasswordValid = emailPasswordValid
            )
        }
        composable(
            "${MintScreen.BlogDetail.name}/{blogId}",
            arguments = listOf(
                navArgument("blogId") {
                    type = NavType.StringType
                },
            ),
        ) { entry ->
            val blogId = entry.arguments?.getString("blogId")
            val blogDetailViewModel: BlogDetailViewModel = hiltViewModel()
            val blog: Blog? by blogDetailViewModel.blog.observeAsState()
            val status: Status by blogDetailViewModel.status.observeAsState(Status.Loading)

            if (blogId != null) {
                blogDetailViewModel.getBlogById(blogId)
            }

            BlogDetailBody(
                blog,
                status = status,
                onProfileClick = {
                    navController.navigate(MintScreen.Profile.name)
                }
            )
        }
        composable(MintScreen.Profile.name) {
            ProfileBody(onItemClick = { blogId ->
                navController.navigate(
                    "${MintScreen.BlogDetail.name}/$blogId"
                )
            })
        }
        composable(MintScreen.CreateBlog.name) {
            val createBlogViewModel: CreateBlogViewModel = hiltViewModel()
            val imageUrl: String by createBlogViewModel.imageUrl.observeAsState("")
            val imageUrlError: Boolean by createBlogViewModel.imageUrlError.observeAsState(false)

            val blogTitle: String by createBlogViewModel.blogTitle.observeAsState("")
            val blogTitleError: Boolean by createBlogViewModel.blogTitleError.observeAsState(false)

            val content: String by createBlogViewModel.content.observeAsState("")

            val status: Status by createBlogViewModel.status.observeAsState(Status.Empty)
            val charCount: Int by createBlogViewModel.charCount.observeAsState(0)
            val contentError: Boolean by createBlogViewModel.contentError.observeAsState(false)

            CreateBlogBody(
                imageUrl = imageUrl,
                onUrlChange = { createBlogViewModel.onUrlChange(it) },
                imageUrlError = imageUrlError,
                blogTitle = blogTitle,
                onBlogTitleChange = { createBlogViewModel.onBlogTitleChange(it) },
                blogTitleError = blogTitleError,
                content = content,
                onContentChange = { createBlogViewModel.onContentChange(it) },
                maxCharCount = createBlogViewModel.maxCharCount,
                charCount = charCount,
                contentError = contentError,
                onSubmit = {
                    createBlogViewModel.postBlog().invokeOnCompletion {
                        when (status) {
                            Status.Success -> navController.navigate(MintScreen.BlogList.name)
                            else -> {
                                Log.d("TESTING", status.toString())
                            }
                        }
                    }
                },
                status = status
            )
        }
    }
}