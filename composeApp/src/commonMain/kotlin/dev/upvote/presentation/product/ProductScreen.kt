package dev.upvote.presentation.product

import org.jetbrains.compose.resources.painterResource

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel

import kotlinx.coroutines.flow.update

import coil3.compose.SubcomposeAsyncImage

import dev.upvote.api.first_party.NewReview
import dev.upvote.globalGlobalState
import dev.upvote.globalMutableStateFlow
import dev.upvote.presentation.auth.AuthScreen
import dev.upvote.presentation.auth.signinup.SignInUpViewModel
import dev.upvote.presentation.error.ShowError
import dev.upvote.resources.Res
import dev.upvote.resources.thumb_down_24px
import dev.upvote.resources.thumb_up_24px
import dev.upvote.ui.ExpandableSearchView


@Composable
fun ProductScreen(
    component: ProductComponent,
    viewModel: ProductDetailViewModel = viewModel { ProductDetailViewModel() },
    signInUpViewModel: SignInUpViewModel = viewModel { SignInUpViewModel() },
    modifier: Modifier = Modifier
) {
    val globalGlobalGlobalStateLocal = globalGlobalState.collectAsState()
    val uiState by viewModel.uiState.collectAsState()
    val authUiState by signInUpViewModel.uiState.collectAsState()

    val reset = {
        globalMutableStateFlow.update { it.copy(barcode = null) }
        viewModel.clearError()
    }

    if (globalGlobalGlobalStateLocal.value.showAuth) {
        Text("Showing auth, token: ${globalGlobalGlobalStateLocal.value.token}")
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
                    searchDisplay = globalGlobalGlobalStateLocal.value.barcode ?: "",
                    onSearchDisplayChanged = {},
                    onSearchDisplayClosed = {},
                )
            },
            modifier = modifier
        ) { innerPadding ->
            Column(Modifier.padding(innerPadding)) {
                Text("My token: ${globalGlobalGlobalStateLocal.value.token}")
                if (!globalGlobalGlobalStateLocal.value.showAuth) {
                    Button(onClick = { globalMutableStateFlow.update { it.copy(showAuth = true) } }) {
                        Text(buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 24.sp
                                )
                            ) {
                                append("Show auth")
                            }
                        })
                    }
                }
                if (!globalGlobalGlobalStateLocal.value.lastErrorStr.isNullOrBlank()) {
                    ShowError(globalGlobalGlobalStateLocal.value.lastErrorStr!!, reset)
                } else if (!uiState.lastErrorStr.isNullOrBlank()) {
                    ShowError(uiState.lastErrorStr!!, reset)
                } else if (globalGlobalGlobalStateLocal.value.barcode != null &&
                    globalGlobalGlobalStateLocal.value.barcode!!.length > 3
                ) {
                    viewModel.getProductById(globalGlobalGlobalStateLocal.value.barcode!!)
                    if (!uiState.productName.isNullOrEmpty()) {
                        Column {
                            Text(buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 24.sp
                                    )
                                ) {
                                    append(uiState.productName)
                                }
                            })
                            SubcomposeAsyncImage(
                                model = uiState.imageFrontUrl,
                                loading = {
                                    CircularProgressIndicator()
                                },
                                contentDescription = "${uiState.productName} image",
                                onError = { e -> println("img e $e") }
                            )
                            Spacer(Modifier.defaultMinSize(20.dp))
                            Row(modifier = Modifier.padding(20.dp)) {
                                IconButton(
                                    onClick = {
                                        println("Clicked upvote")
                                        if (authUiState.isLoggedIn()) {
                                            viewModel.postReview(
                                                NewReview(
                                                    vote = 1,
                                                    reviewee = globalGlobalGlobalStateLocal.value.barcode!!,
                                                    message = null,
                                                    photoUrl = null,
                                                    videoUrl = null
                                                )
                                            )
                                        } else {
                                            globalMutableStateFlow.update { it.copy(showAuth = true) }
                                        }
                                        // TODO: Add counter showing number of likes / dislikes
                                    },
                                    Modifier.scale(3F)
                                ) {
                                    Icon(
                                        painter = painterResource(Res.drawable.thumb_up_24px),
                                        contentDescription = "Upvote",
                                    )
                                }
                                Spacer(Modifier.fillMaxSize(0.2F))
                                IconButton(
                                    onClick = {
                                        println("Clicked downvote")
                                        viewModel.postReview(
                                            NewReview(
                                                vote = -1,
                                                reviewee = globalGlobalGlobalStateLocal.value.barcode!!,
                                                message = null,
                                                photoUrl = null,
                                                videoUrl = null
                                            )
                                        )
                                    },
                                    Modifier.scale(3F)
                                ) {
                                    Icon(
                                        painter = painterResource(Res.drawable.thumb_down_24px),
                                        contentDescription = "Downvote"
                                    )
                                }
                            }
                        }
                    } else if (!globalGlobalGlobalStateLocal.value.lastErrorStr.isNullOrBlank()) {
                        ShowError(globalGlobalGlobalStateLocal.value.lastErrorStr!!, reset)
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
