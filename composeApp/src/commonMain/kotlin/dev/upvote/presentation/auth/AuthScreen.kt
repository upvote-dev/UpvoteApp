package dev.upvote.presentation.auth

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.remember
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
import androidx.lifecycle.viewmodel.compose.viewModel

import kotlinx.coroutines.flow.update

import dev.upvote.api.first_party.Credentials
import dev.upvote.globalMutableStateFlow
import dev.upvote.presentation.auth.signinup.SignInUpViewModel
import kotlinx.coroutines.flow.StateFlow

@Composable
fun AuthScreen(
    viewModel: SignInUpViewModel = viewModel { SignInUpViewModel() },
    onDismissRequest: () -> Unit
) {
    val uiState: AuthState by viewModel.uiState.collectAsState()
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
                    append("Auth")
                }
            })
            Spacer(modifier = Modifier.height(24.dp))

            var username by rememberSaveable { mutableStateOf("") }
            var password by rememberSaveable { mutableStateOf("") }
            val focusManager = LocalFocusManager.current

            val onSubmit = {
                println("Auth")
                viewModel.clearError()
                viewModel.acquireToken(Credentials(username = username, password = password))
                if (/*uiState.token != null &&*/uiState.lastErrorStr == null) {
                    globalMutableStateFlow.update { it.copy(token = uiState.token) }
                    onDismissRequest()
                }
                // uiState.userId = username
            }

            OutlinedTextField(
                value = username,
                label = { Text("Email") },
                onValueChange = { username = it },
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
                value = password,
                onValueChange = { password = it },
                visualTransformation = PasswordVisualTransformation(),
                label = { Text("Password") },
                modifier = Modifier
                    .onPreviewKeyEvent {
                        if (it.key == Key.Enter) {
                            focusManager.moveFocus(FocusDirection.Down)
                            onSubmit()
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

            Button(onClick = onSubmit) {
                Text(buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                    ) {
                        append("Sign in up")
                    }
                })
            }
        }
    }
}
