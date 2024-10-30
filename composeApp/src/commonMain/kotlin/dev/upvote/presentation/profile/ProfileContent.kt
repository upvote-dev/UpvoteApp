package dev.upvote.presentation.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

import dev.upvote.api.first_party.Profile

@Composable
fun ProfileContent(
    component: ProfileComponent,
    viewModel: ProfileContentViewModel = viewModel { ProfileContentViewModel() },
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize()
    ) {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                Icons.Outlined.Face,
                modifier = Modifier.size(128.dp),
                contentDescription = "Profile photo"
            )
            Text("My name here", fontSize = 35.sp)
            Button(
                onClick = {
                    println("updateProfile clicked")
                    viewModel.updateProfile(Profile(role = "foo"))
                },
                modifier = modifier.fillMaxWidth().padding(),
            ) {
                Text("Update profile")
            }
            LinearProgressIndicator(
                progress = { 33F },
                modifier = Modifier.fillMaxWidth(0.5F).height(15.dp),
            )
            Row(Modifier.fillMaxWidth(0.5F)) {
                CircularProgressIndicator(
                    progress = { 33F },
                )
                CircularProgressIndicator(
                    progress = { 50F },
                )
            }
            Spacer(Modifier.fillMaxSize(0.2F))
            Spacer(Modifier.defaultMinSize(minHeight = 128.dp))
            Text(
                "Thank you!",
                modifier = modifier
                    .fillMaxWidth()
                    .padding()
            )
            Spacer(Modifier.fillMaxSize(0.2F))
            Button(onClick = { println("[contrib] clicked") }, modifier = Modifier.fillMaxWidth()) {
                Text("Contribute more")
            }

            Spacer(Modifier.fillMaxSize(0.2F))
            Button(
                onClick = { println("[homepage] clicked") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Return to homepage")
            }
        }
    }
}
