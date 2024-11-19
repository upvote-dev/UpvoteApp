package dev.upvote.presentation.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

import dev.upvote.OnLifecycleEvent
import dev.upvote.api.first_party.ProfileOptional
import dev.upvote.globalGlobalState
import dev.upvote.globalMutableStateFlow
import dev.upvote.presentation.auth.AuthScreen
import kotlinx.coroutines.flow.update

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    component: ProfileComponent,
    viewModel: ProfileContentViewModel = viewModel { ProfileContentViewModel() },
    modifier: Modifier = Modifier
) {
    OnLifecycleEvent { _, event ->
        when (event) {
            Lifecycle.Event.ON_RESUME -> {
                viewModel.getProfile()
            }
            else -> {}
        }
    }
    val uiState by viewModel.uiState.collectAsState()
    val globalGlobalGlobalStateLocal by globalGlobalState.collectAsState()

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
    } else {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            "Welcome, ${uiState.profile?.alias ?: "Alias"}!",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { /* doSomething() */ }) {
                            Icon(
                                imageVector = Icons.Filled.AccountCircle,
                                contentDescription = "Localized description"
                            )
                        }
                    },
                    actions = {
                        // RowScope here, so these icons will be placed horizontally
                        IconButton(onClick = { /* doSomething() */ }) {
                            Icon(
                                imageVector = Icons.Filled.ThumbUp,
                                contentDescription = "Localized description"
                            )
                        }
                    },
                    scrollBehavior = scrollBehavior,
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.secondary,
                    )
                )
            },
            content = { innerPadding ->
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    // verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = {
                            println("updateProfile clicked")
                            if (globalGlobalState.value.token != null) {
                                viewModel.updateProfile(
                                    ProfileOptional(
                                        username = globalGlobalState.value.token?.getUsername()
                                            ?: "Username",
                                        alias = "foo"
                                    )
                                )
                            } else
                                globalMutableStateFlow.update { it.copy(showAuth = true) }
                        },
                        modifier = modifier.fillMaxWidth().padding(),
                    ) {
                        Text("Update profile [change alias to `foo`]")
                    }

                    Text(
                        buildAnnotatedString {
                            append("You are an Upvote ")
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("Captain")
                            }
                            append("\uD83C\uDF96\uFE0F")
                        }
                    )
                    LinearProgressIndicator(
                        progress = { .66F },
                        strokeCap = StrokeCap.Round,
                        trackColor = ProgressIndicatorDefaults.circularColor,
                        gapSize = 0.dp,
                        modifier = Modifier
                            .fillMaxWidth(0.5F)
                            .height(15.dp),
                    )
                    Text(
                        buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("145/200")
                            }
                            append(" upvotes to become an ")
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("Admiral")
                            }
                            append("!")
                        }
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(10.dp, 10.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .padding(16.dp)
                                .drawBehind {
                                    drawCircle(
                                        color = Color.hsl(270F, .47F, .93F, 1F),
                                        radius = this.size.maxDimension / 1.7F
                                    )
                                }) {
                            Text("You have")
                            Text("8,328", style = MaterialTheme.typography.displayMedium)
                            Text("upcoins!")
                        }
                        Spacer(Modifier.weight(1f))
                        Column(verticalArrangement = Arrangement.spacedBy(7.dp)) {
                            Text(
                                buildAnnotatedString {
                                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                        append("\uD83D\uDC65 97")
                                    }
                                    append(" Followers")
                                }
                            )
                            Text(
                                buildAnnotatedString {
                                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                        append("⛓\uFE0F 48")
                                    }
                                    append(" Following")
                                }
                            )
                            Text(
                                buildAnnotatedString {
                                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                        append("\uD83D\uDCDD 115")
                                    }
                                    append(" Reviews")
                                }
                            )
                        }
                    }
                    Row(
                        //horizontalArrangement = Arrangement.Start,
                        //verticalAlignment = Alignment.Top,
                        //modifier = Modifier.weight(1f)
                        modifier = Modifier.absolutePadding(0.dp, 0.dp, 0.dp, 0.dp)
                    ) {
                        Column {
                            Text("Your Activity", style = MaterialTheme.typography.titleMedium)
                            HorizontalDivider(
                                modifier = Modifier
                                    .fillMaxSize(.9F)
                            )
                        }
                    }
                    Row {
                        Button(
                            onClick = {},
                            modifier = Modifier.fillMaxWidth(0.8F).offset(x = 0.dp, y = (-12).dp)
                        ) {
                            Text("Earn more upcoins")
                        }
                    }
                }
            }
        )
    }
}
/*
@Composable
fun old() {
    Scaffold(
        modifier = modifier.fillMaxSize()
    ) {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                Icons.Outlined.Face,
                modifier = Modifier.size(128.dp),
                contentDescription = "Profile photo"
            )
            Text(
                uiState.profile?.alias ?: "Alias",
                style = MaterialTheme.typography.headlineMedium
            )
            Button(
                onClick = {
                    println("updateProfile clicked")
                    if (globalGlobalState.value.token != null) {
                        viewModel.updateProfile(
                            ProfileOptional(
                                username = globalGlobalState.value.token?.getUsername()
                                    ?: "Username",
                                alias = "foo"
                            )
                        )
                    } else
                        globalMutableStateFlow.update { it.copy(showAuth = true) }
                },
                modifier = modifier.fillMaxWidth().padding(),
            ) {
                Text("Update profile [change alias to `foo`]")
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
            Button(
                onClick = { println("[contrib] clicked") },
                modifier = Modifier.fillMaxWidth()
            ) {
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
*/