package dev.upvote.presentation.product

import org.jetbrains.compose.resources.painterResource

import kotlinx.coroutines.flow.update

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import coil3.compose.SubcomposeAsyncImage

import dev.upvote.api.first_party.NewReview
import dev.upvote.globalGlobalState
import dev.upvote.globalMutableStateFlow
import dev.upvote.presentation.auth.signinup.SignInUpViewModel
import dev.upvote.resources.Res
import dev.upvote.resources.thumb_down_24px
import dev.upvote.resources.thumb_up_24px

@Composable
fun ProductDetailScreen(
    viewModel: ProductDetailViewModel,
    signInUpViewModel: SignInUpViewModel,
    uiState: ProductDetailState
) {
    val authUiState by signInUpViewModel.uiState.collectAsState()
    val globalGlobalGlobalStateLocal by globalGlobalState.collectAsState()
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
                    println("Clicked upvote, authUiState: ${authUiState}")
                    if (globalGlobalGlobalStateLocal.token != null) {
                        viewModel.postReview(
                            NewReview(
                                vote = 1,
                                reviewee = globalGlobalGlobalStateLocal.barcode!!,
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
                    println("Clicked downvote, authUiState: ${authUiState}")

                    viewModel.postReview(
                        NewReview(
                            vote = -1,
                            reviewee = globalGlobalGlobalStateLocal.barcode!!,
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
}
