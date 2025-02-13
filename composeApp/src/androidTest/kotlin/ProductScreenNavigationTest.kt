package dev.upvote.presentation.product

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test
import org.junit.Rule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.NavHostController
import dev.upvote.GlobalState
import dev.upvote.globalGlobalState
import dev.upvote.presentation.auth.signinup.SignInUpViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class ProductScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testNavigationToProductDetail() {
        val mockNavController = mockk<NavHostController>(relaxed = true)
        val component = mockk<ProductComponent>(relaxed = true)
        val viewModel = mockk<ProductDetailViewModel>(relaxed = true)
        val signInUpViewModel = mockk<SignInUpViewModel>(relaxed = true)

        val fakeGlobalState = MutableStateFlow(GlobalState(barcode = "123456"))

        every { globalGlobalState } returns fakeGlobalState // TODO: globalGlobalState mock

        composeTestRule.setContent {
            ProductScreen(
                component = component,
                viewModel = viewModel,
                signInUpViewModel = signInUpViewModel
            )
        }

        verify { mockNavController.navigate("product/123456") }
    }
}