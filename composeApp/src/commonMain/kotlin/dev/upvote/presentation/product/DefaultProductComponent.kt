package dev.upvote.presentation.product

import com.arkivanov.decompose.ComponentContext

class DefaultProductComponent(
    componentContext: ComponentContext,
    private val onShowWelcome: () -> Unit,
) : ProductComponent, ComponentContext by componentContext {
    override fun onBackClicked() {
        TODO("Not yet implemented")
    }
}
