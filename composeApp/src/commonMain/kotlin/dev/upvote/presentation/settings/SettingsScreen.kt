package dev.upvote.presentation.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import kotlinx.coroutines.flow.update

import io.ktor.client.plugins.auth.authProvider
import io.ktor.client.plugins.auth.providers.BearerAuthProvider

import dev.upvote.globalMutableStateFlow
import dev.upvote.httpClient
import dev.upvote.invalidateBearerTokens

fun clearToken() {
    globalMutableStateFlow.update {
        it.copy(
            token = null
        )
    }
    httpClient.authProvider<BearerAuthProvider>()?.clearToken()
    httpClient.invalidateBearerTokens()
}

@Composable
fun SettingsScreen(
    onDismissRequest: () -> Unit,
    onLogout: () -> Unit
) {
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
                    append("Settings")
                }
            })
            Spacer(modifier = Modifier.height(24.dp))

            val onSubmitLogout = {
                clearToken()
                onLogout()
                onDismissRequest()
            }

            Button(onClick = onSubmitLogout, modifier = Modifier.fillMaxWidth()) {
                Text(buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                    ) {
                        append("Logout")
                    }
                })
            }
        }
    }
}
