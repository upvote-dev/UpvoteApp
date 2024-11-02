package dev.upvote.presentation.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel

import kotlinx.coroutines.flow.update

import dev.upvote.globalGlobalState
import dev.upvote.globalMutableStateFlow
import dev.upvote.presentation.auth.AuthScreen
import dev.upvote.presentation.auth.signinup.SignInUpViewModel
import dev.upvote.presentation.error.ShowError
import dev.upvote.ui.ExpandableSearchView


@Composable
fun ProductScreen(
    component: ProductComponent,
    viewModel: ProductDetailViewModel = viewModel { ProductDetailViewModel() },
    signInUpViewModel: SignInUpViewModel = viewModel { SignInUpViewModel() },
    modifier: Modifier = Modifier
) {
    val globalGlobalGlobalStateLocal by globalGlobalState.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    globalGlobalGlobalStateLocal.token?.also { token ->
        signInUpViewModel.setToken(token)
    }

    val reset = {
        globalMutableStateFlow.update { it.copy(barcode = null) }
        viewModel.clearError()
    }

    if (globalGlobalGlobalStateLocal.showAuth) {
        val onDismissRequest = {
            globalMutableStateFlow.update { it.copy(showAuth = false) }
        }
        Dialog(
            onDismissRequest = onDismissRequest,
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        ) {
            AuthScreen(onDismissRequest = onDismissRequest)
        }
    } else
        Scaffold(
            topBar = {
                ExpandableSearchView(
                    searchDisplay = globalGlobalGlobalStateLocal.barcode ?: "",
                    onSearchDisplayChanged = {},
                    onSearchDisplayClosed = {},
                )
            },
            modifier = modifier
        ) { innerPadding ->
            Column(Modifier.padding(innerPadding)) {
                if (!globalGlobalGlobalStateLocal.lastErrorStr.isNullOrBlank()) {
                    ShowError(globalGlobalGlobalStateLocal.lastErrorStr!!, reset)
                } else if (!uiState.lastErrorStr.isNullOrBlank()) {
                    ShowError(uiState.lastErrorStr!!, reset)
                } else if (globalGlobalGlobalStateLocal.barcode != null &&
                    globalGlobalGlobalStateLocal.barcode!!.length > 3
                ) {
                    viewModel.getProductById(globalGlobalGlobalStateLocal.barcode!!)
                    if (!uiState.productName.isNullOrEmpty()) {
                        ProductDetailScreen(
                            viewModel = viewModel,
                            signInUpViewModel = signInUpViewModel,
                            uiState = uiState
                        )
                    } else if (!globalGlobalGlobalStateLocal.lastErrorStr.isNullOrBlank()) {
                        ShowError(globalGlobalGlobalStateLocal.lastErrorStr!!, reset)
                    } else if (!uiState.lastErrorStr.isNullOrBlank()) {
                        ShowError(uiState.lastErrorStr!!, reset)
                    } else {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.width(64.dp),
                            )
                        }
                    }
                }
            }
        }
}
