package dev.upvote.presentation.auth.signinup

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun SignInUp() {
    Button(
        onClick = { println("clicked") }
    ) {
        Text("Signin")
    }
}
