package dev.upvote.presentation.profile

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import kotlinx.coroutines.flow.update

import dev.upvote.api.first_party.Credentials
import dev.upvote.api.first_party.ProfileOptional
import dev.upvote.globalGlobalState
import dev.upvote.globalMutableStateFlow
import kotlinx.serialization.SerialName


@Composable
fun ProfileEditScreen(
    viewModel: ProfileContentViewModel = androidx.lifecycle.viewmodel.compose.viewModel { ProfileContentViewModel() },
    onDismissRequest: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    Surface(
        modifier = Modifier.wrapContentWidth().wrapContentHeight(),
        shape = MaterialTheme.shapes.large,
        tonalElevation = AlertDialogDefaults.TonalElevation
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                    )
                ) {
                    append(uiState.userId ?: globalGlobalState.value.token?.getUsername().toString())
                    append("'s profile")
                }
            })
            Spacer(modifier = Modifier.height(24.dp))


            var alias: String? by rememberSaveable { mutableStateOf(null) }
            val profileImageUrl: String? by rememberSaveable { mutableStateOf(null) }

            val focusManager = LocalFocusManager.current

            val onSubmit = {
                println("Edit profile")
                viewModel.updateProfile(ProfileOptional(
                    alias = if (alias.isNullOrEmpty()) null else alias,
                    profileImageUrl = if (profileImageUrl.isNullOrEmpty()) null else profileImageUrl
                ))
                onDismissRequest()
            }
            OutlinedTextField(
                value = alias ?: "",
                label = { Text("Alias") },
                onValueChange = { alias = it },
                modifier = Modifier
                    .onPreviewKeyEvent {
                        if (it.key == Key.Tab) {
                            focusManager.moveFocus(FocusDirection.Down)
                            true
                        } else {
                            false
                        }
                    },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )
            OutlinedTextField(
                value = profileImageUrl ?: "",
                label = { Text("profile image url") },
                onValueChange = { alias = it },
                modifier = Modifier
                    .onPreviewKeyEvent {
                        if (it.key == Key.Tab) {
                            focusManager.moveFocus(FocusDirection.Down)
                            true
                        } else {
                            false
                        }
                    },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Enter) }
                )
            )

            AnimatedVisibility(!uiState.lastErrorStr.isNullOrEmpty()) {
                Text(buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = Color.Red
                        )
                    ) {
                        append(uiState.lastErrorStr ?: "lastErrorStr")
                    }
                })
            }

            Button(onClick = onSubmit, modifier = Modifier.fillMaxWidth()) {
                Text(buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                    ) {
                        append("Edit profile")
                    }
                })
            }
        }
    }
}
