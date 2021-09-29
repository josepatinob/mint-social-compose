package com.example.mintsocialcompose

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.mintsocialcompose.ui.blogdetail.BlogDetailUiState
import com.example.mintsocialcompose.ui.blogdetail.BlogDetailViewModel
import com.example.mintsocialcompose.ui.bloglist.BlogListBody
import com.example.mintsocialcompose.ui.bloglist.BlogListViewModel
import com.example.mintsocialcompose.ui.components.MintAlertDialog
import com.example.mintsocialcompose.ui.createblog.CreateBlogBody
import com.example.mintsocialcompose.ui.createblog.CreateBlogViewModel
import com.example.mintsocialcompose.ui.login.LoginBody
import com.example.mintsocialcompose.ui.login.LoginViewModel
import com.example.mintsocialcompose.ui.profile.ProfileBody
import com.example.mintsocialcompose.ui.profile.ProfileViewModel
import com.example.mintsocialcompose.ui.register.RegisterBody
import com.example.mintsocialcompose.ui.register.RegisterViewModel
import com.example.mintsocialcompose.ui.splash.SplashBody

@Composable
fun MintSocialNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onNetworkError: (String) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = MintScreen.Splash.name,
        modifier = modifier
    ) {
        composable(MintScreen.Splash.name) {
            SplashBody() {
                navController.navigate(MintScreen.Login.name) {
                    popUpTo(MintScreen.Splash.name) { inclusive = true }
                }
            }
        }
        composable(MintScreen.Login.name) {
            val loginViewModel: LoginViewModel = hiltViewModel()
            val email: String by loginViewModel.email.observeAsState("")
            val emailError: Boolean by loginViewModel.emailError.observeAsState(false)
            val password: String by loginViewModel.password.observeAsState("")
            val status: Status by loginViewModel.status.observeAsState(Status.Empty)
            val isSignedIn: Boolean? by loginViewModel.isSignedIn.observeAsState()

            val openDialog = remember { mutableStateOf(false) }

            if (openDialog.value) {
                MintAlertDialog(
                    title = "Login Failed",
                    body = "Incorrect credentials provided, please try again.",
                    onDismiss = {
                        openDialog.value = false
                    },
                    dismissText = "Retry",
                    dismissOnClickAway = true
                )
            }

            LoginBody(
                onLoginClick = {
                    loginViewModel.signIn(email, password).invokeOnCompletion {
                        when (status) {
                            Status.Success -> navController.navigate(MintScreen.BlogList.name) {
                                popUpTo(MintScreen.Login.name) { inclusive = true }
                            }
                            else -> {
                                openDialog.value = true
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
                status = status,
                isSignedIn = isSignedIn,
                onAlreadyLoggedIn = {
                    navController.navigate(MintScreen.BlogList.name) {
                        popUpTo(MintScreen.Login.name) { inclusive = true }
                    }
                }
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
                isSignedIn = isSignedIn,
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

            val status: Status by registerViewModel.status.observeAsState(Status.Empty)
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
                emailPasswordValid = emailPasswordValid,
                onRegister = {
                    registerViewModel.signUp(email, password).invokeOnCompletion {
                        when (status) {
                            Status.Success -> navController.navigate(MintScreen.Login.name)
                            else -> {
                                Log.d("TESTING", status.toString())
                            }
                        }
                    }
                },
                status = status
            )
        }
        composable(
            "${MintScreen.BlogDetail.name}/{blogId}",
            arguments = listOf(
                navArgument("blogId") {
                    type = NavType.StringType
                    nullable = true
                },
            ),
        ) { entry ->
            val blogId = entry.arguments?.getString("blogId")
            val blogDetailViewModel: BlogDetailViewModel = hiltViewModel()
            val uiState: BlogDetailUiState by blogDetailViewModel.uiState.observeAsState(
                BlogDetailUiState(isLoading = true)
            )
            blogDetailViewModel.getBlogById(blogId ?: "")

            BlogDetailBody(
                blog = uiState.blog,
                isLoading = uiState.isLoading,
                onProfileClick = { authorId, authorEmail ->
                    navController.navigate("${MintScreen.Profile.name}?userId=${authorId}&userEmail=${authorEmail}")
                },
                errorMessages = uiState.errorMessages,
                onNetworkError = onNetworkError
            )
        }
        composable("${MintScreen.Profile.name}?userId={userId}&userEmail={userEmail}") { entry ->
            val userId: String? = entry.arguments?.getString("userId")
            val userEmail: String? = entry.arguments?.getString("userEmail")
            val profileViewModel: ProfileViewModel = hiltViewModel()

            val status: Status by profileViewModel.status.observeAsState(Status.Loading)
            val blogList: List<Blog> by profileViewModel.blogList.observeAsState(emptyList())
            val isLoggedInUserProfile: Boolean by profileViewModel.isLoggedInUserProfile.observeAsState(
                true
            )

            val isSignedIn: Boolean? by profileViewModel.isSignedIn.observeAsState()

            val loggedInUserEmail = profileViewModel.loggedInUserEmail()
            val loggedInUserId = profileViewModel.loggedInUserId()

            if (userId.isNullOrEmpty()) {
                profileViewModel.getBlogs(loggedInUserId)
            } else {
                profileViewModel.getBlogs(userId)
                profileViewModel.checkForMyProfile(userId)
            }

            val email = if (userEmail.isNullOrBlank()) {
                loggedInUserEmail ?: ""
            } else {
                userEmail
            }

            ProfileBody(
                onItemClick = { blogId ->
                    navController.navigate(
                        "${MintScreen.BlogDetail.name}/$blogId"
                    )
                },
                status = status,
                blogList = blogList,
                userEmail = email,
                isLoggedInUserProfile = isLoggedInUserProfile,
                onSignOut = {
                    profileViewModel.signOut().invokeOnCompletion {
                        navController.navigate(MintScreen.Login.name)
                    }
                },
                isSignedIn = isSignedIn,
                onLoginClick = {
                    navController.navigate(MintScreen.Login.name) {
                        popUpTo(MintScreen.Profile.name) { inclusive = true }
                    }
                }
            )
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

            val isSignedIn: Boolean? by createBlogViewModel.isSignedIn.observeAsState()

            val openDialog = remember { mutableStateOf(false) }

            if (isSignedIn == false) {
                openDialog.value = true
            }

            if (openDialog.value) {
                MintAlertDialog(
                    title = "Not logged in",
                    body = "Guest users have limited access",
                    onConfirm = {
                        openDialog.value = false
                        navController.navigate(MintScreen.Login.name)
                    },
                    confirmText = "Login",
                    onDismiss = {
                        openDialog.value = false
                        navController.navigate(MintScreen.BlogList.name)
                    },
                    dismissText = "Back to Blog List",
                    dismissOnClickAway = false
                )
            }

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