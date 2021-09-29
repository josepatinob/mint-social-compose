package com.example.mintsocialcompose

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mintsocialcompose.ui.theme.MintSocialComposeTheme
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class MintSocialAppTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            MintSocialComposeTheme {
                MintSocialApp()
            }
        }
    }

    @Test
    fun failedLoginTest() {

        composeTestRule.onNode(hasText("Hey! Sign in below or Create a new account!"))
            .assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Clickable text").assertIsDisplayed()
        composeTestRule.onNodeWithTag("Clickable text test tag").assertExists()
        composeTestRule.onNodeWithText("Login").assertIsDisplayed().assertIsNotEnabled()

        // show the semantics tree in logs
        composeTestRule.onRoot().printToLog("APP_TEST")

        // Note: You cannot chain actions inside a perform function. Instead, make multiple perform() calls.

        composeTestRule.onNodeWithText("Login").assertIsNotEnabled()

        composeTestRule.onNodeWithText("Enter email").performTextInput("test@gmail.co")
        composeTestRule.onNodeWithText("Enter password").performTextInput("Test")

        composeTestRule.onNodeWithText("Login").assertIsEnabled()
        composeTestRule.onNodeWithText("Login").performClick()

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithContentDescription("progress indicator", ignoreCase = true)
            .assertIsDisplayed()

        composeTestRule.onNodeWithContentDescription("dialog")
    }

    @Test
    fun proceedAsGuestTest() {
        composeTestRule.onNodeWithText("Proceed as Guest").assertIsDisplayed().performClick()

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithContentDescription("progress indicator", ignoreCase = true)
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("All").assertContentDescriptionContains("check icon")
            .assertIsDisplayed().assertIsOn()
    }
}