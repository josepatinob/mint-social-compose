package com.example.mintsocialcompose

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mintsocialcompose.type.Status
import com.example.mintsocialcompose.ui.login.LoginBody
import com.example.mintsocialcompose.ui.theme.MintSocialComposeTheme

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class LoginScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    // use createAndroidComposeRule<MainActivity>() if you need access to an activity
    // would also need this if you need access to string resources

    @Test
    fun loginBodyTest() {
        // start the app
        composeTestRule.setContent {
            MintSocialComposeTheme {
                LoginBody(
                    onLoginClick = {},
                    onGuestClick = {},
                    onCreateAccountClick = {},
                    email = "",
                    onEmailChange = {},
                    emailError = false,
                    password = "",
                    onPasswordChange = {},
                    loginEnabled = false,
                    status = Status.Empty,
                    isSignedIn = false,
                    onAlreadyLoggedIn = {}
                )
            }
        }

        composeTestRule.onNode(hasText("Hey! Sign in below or Create a new account!"))
            .assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Clickable text").assertIsDisplayed()
        composeTestRule.onNodeWithTag("Clickable text test tag").assertExists()
        composeTestRule.onNodeWithText("Hey! Sign in below or Create a new account").assertExists()
        composeTestRule.onNodeWithText("Login").assertIsNotEnabled()

        // show the semantics tree in logs
        // The useUnmergedTree parameter is available in all finders. For example, here it's used in an onNodeWithText finder.
        composeTestRule.onRoot(useUnmergedTree = true).printToLog("LOGIN_SCREEN_TEST")

        // Note: You cannot chain actions inside a perform function. Instead, make multiple perform() calls.

    }

    @Test
    fun loginBodyLoadingTest() {
        composeTestRule.setContent {
            LoginBody(
                onLoginClick = {},
                onGuestClick = {},
                onCreateAccountClick = {},
                email = "",
                onEmailChange = {},
                emailError = false,
                password = "",
                onPasswordChange = {},
                loginEnabled = false,
                status = Status.Loading,
                isSignedIn = false,
                onAlreadyLoggedIn = {}
            )
        }

        composeTestRule.onRoot(useUnmergedTree = false).printToLog("LOADING_TEST")
        composeTestRule.onNodeWithContentDescription("Progress indicator").assertIsDisplayed()

        /*
        * Compose tests use a structure called the semantics tree to look for elements on the screen and read their properties.
        * This is the structure that accessibility services use as well, as they're meant to be read by a service such as TalkBack.
        * Warning: Layout Inspector support for Semantics properties is not available yet.
        * You can print the Semantics tree using the printToLog function on a node.
        * */

        /* Warning: Composables don't have IDs and you can't use the Node numbers shown in the tree to match them.
         * If matching a node with its semantics properties is impractical or impossible, you can use the testTag
         * modifier with the hasTestTag matcher as a last resort.
         * */
    }
}