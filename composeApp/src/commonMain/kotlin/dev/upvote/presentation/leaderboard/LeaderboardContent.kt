package dev.upvote.presentation.leaderboard

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeaderboardContent(
    component: LeaderboardComponent,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = { Text(text = "Leaderboard", fontSize = 15.sp) })
        }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
            item {
                Spacer(modifier = Modifier.padding(innerPadding))
            }
            item {
                Text(
                    text = "Person 1",
                    fontSize = 12.sp,
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }
            item {
                Spacer(modifier = Modifier.padding(innerPadding))
            }
            item {
                Text(
                    text = "Person 55",
                    fontSize = 12.sp,
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }
        }
    }
}
