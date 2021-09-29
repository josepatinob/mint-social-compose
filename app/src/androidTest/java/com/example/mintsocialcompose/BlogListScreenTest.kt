package com.example.mintsocialcompose

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mintsocialcompose.model.Blog
import com.example.mintsocialcompose.type.BlogFilter
import com.example.mintsocialcompose.type.Status
import com.example.mintsocialcompose.ui.bloglist.BlogListBody
import com.example.mintsocialcompose.ui.theme.MintSocialComposeTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDate

@RunWith(AndroidJUnit4::class)
class BlogListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun threeFiltersVisibleTest() {
        composeTestRule.setContent {
            MintSocialComposeTheme {
                BlogListBody(
                    onItemClick = {},
                    blogList = emptyList(),
                    isSignedIn = true,
                    status = Status.Empty,
                    currentFilter = BlogFilter.All,
                    onFilterChange = {}
                )
            }
        }

        composeTestRule.onNodeWithText("All").assertIsDisplayed()
        composeTestRule.onNodeWithText("Mine").assertIsDisplayed()
        composeTestRule.onNodeWithText("Others").assertIsDisplayed()
    }

    @Test
    fun filterAllIsCheckedTest() {
        composeTestRule.setContent {
            MintSocialComposeTheme {
                BlogListBody(
                    onItemClick = {},
                    blogList = emptyList(),
                    isSignedIn = true,
                    status = Status.Empty,
                    currentFilter = BlogFilter.All,
                    onFilterChange = {}
                )
            }
        }

        composeTestRule.onNodeWithText("All").assertIsDisplayed()
            .assert(hasContentDescription("check icon")).assertIsOn()

        composeTestRule.onNodeWithText("Mine").assertIsDisplayed().assertIsOff()
        composeTestRule.onNodeWithText("Others").assertIsDisplayed().assertIsOff()

        composeTestRule.onRoot().printToLog("BLOG_LIST_TEST")
    }


    @Test
    fun onlyAllFilterVisibleTest() {
        composeTestRule.setContent {
            MintSocialComposeTheme {
                BlogListBody(
                    onItemClick = {},
                    blogList = emptyList(),
                    isSignedIn = false,
                    status = Status.Empty,
                    currentFilter = BlogFilter.All,
                    onFilterChange = {}
                )
            }
        }

        composeTestRule.onNodeWithText("All").assertIsDisplayed()
            .assert(hasContentDescription("check icon")).assertIsOn()

        composeTestRule.onNodeWithText("Mine").assertDoesNotExist()
        composeTestRule.onNodeWithText("Others").assertDoesNotExist()
    }

    @Test
    fun blogCardVisibleTest() {
        val blogList = listOf(
            Blog(
                "123",
                "Blog Title Number 1",
                "Some content for blog 1",
                "https://image-url-blog-1.com",
                LocalDate.now(),
                "1234567",
                "testEmail@gmail.com",
                false
            ),
            Blog(
                "456",
                "Blog Title Number 2",
                "Some content for blog 2",
                "https://image-url-blog-2.com",
                LocalDate.now(),
                "989898",
                "email99@gmail.com",
                false
            )
        )

        composeTestRule.setContent {
            MintSocialComposeTheme {
                BlogListBody(
                    onItemClick = {},
                    blogList = blogList,
                    isSignedIn = true,
                    status = Status.Empty,
                    currentFilter = BlogFilter.All,
                    onFilterChange = {}
                )
            }
        }

        composeTestRule.onRoot().printToLog("BLOG_CARD_TEST")
        composeTestRule.onNodeWithText("Blog Title Number 1").assertIsDisplayed()
            .assertHasClickAction()

        composeTestRule.onNodeWithText("Blog Title Number 2").assertIsDisplayed()
            .assertHasClickAction()

        composeTestRule.onNodeWithText("Blog Title Number 1").performClick()
    }

    @Test
    fun progressIndicatorDisplayedTest() {
        composeTestRule.setContent {
            MintSocialComposeTheme {
                BlogListBody(
                    onItemClick = {},
                    blogList = emptyList(),
                    isSignedIn = true,
                    status = Status.Loading,
                    currentFilter = BlogFilter.All,
                    onFilterChange = {}
                )
            }
        }
        composeTestRule.onNodeWithContentDescription("Progress indicator").assertIsDisplayed()
    }
}