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
import org.jetbrains.compose.ui.tooling.preview.Preview

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

/*
 * Copyright 2024 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.apps.sunflower.compose.gallery

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.ThumbUp
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun debugPlaceholder(@DrawableRes debugPreview: Int) =
    if (LocalInspectionMode.current) {
        painterResource(id = debugPreview)
    } else {
        null
    }


@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun ProductDetailScreenPreview() {
    MaterialTheme {
        Surface {
            Column {
                Row {
                    Column {
                        Row {
                            Icon(
                                Icons.Rounded.Menu, contentDescription = "Localized description",
                                modifier = Modifier
                                    .weight(1f)
                                    //.height(200.dp) // Set height same as the text
                                    .wrapContentWidth()
                                    .border(1.dp, Color.Red)
                            )
//                        Box(
//                            modifier = Modifier
//                                .height(100.dp)
//                                .drawWithContent {
//                                    drawContent()
//                                    val rect = Rect(
//                                        offset = Offset(x = size.width / 2f, y = 0f),
//                                        size = Size(width = 300.dp.value, height = size.height),
//                                    )
//
//                                    drawRect(
//                                        color = Color.Red,
//                                        topLeft = rect.topLeft,
//                                        size = rect.size,
//                                    )
//                                }
//                        )
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    "Product name",
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.titleLarge,
                                )
                                Text(
                                    "Description, size",
                                )
                                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                    OutlinedButton(
                                        onClick = { /*TODO*/ },
                                        modifier = Modifier.size(75.dp),  //avoid the oval shape
                                        shape = CircleShape,
                                        border = BorderStroke(1.dp, Color.Blue),
                                        contentPadding = PaddingValues(0.dp),  //avoid the little icon
                                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Blue)
                                    ) {
                                        Column(
                                            verticalArrangement = Arrangement.Center,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .wrapContentSize(Alignment.Center)
                                                .wrapContentHeight()
                                        ) {
                                            Text(
                                                "4.6",
                                                textAlign = TextAlign.Center,
                                                modifier = Modifier
                                                    .wrapContentHeight()
                                                    .wrapContentHeight(Alignment.Bottom)
                                                    .padding(22.dp, 0.dp)
                                            )
                                            Row {
                                                Icon(
                                                    Icons.Default.Star,
                                                    contentDescription = "content description",
                                                    modifier = Modifier.size(15.dp)
                                                )
                                                Icon(
                                                    Icons.Default.Star,
                                                    contentDescription = "content description",
                                                    modifier = Modifier.size(15.dp)
                                                )
                                                Icon(
                                                    Icons.Default.Star,
                                                    contentDescription = "content description",
                                                    modifier = Modifier.size(15.dp)
                                                )
                                                Icon(
                                                    Icons.Default.Star,
                                                    contentDescription = "content description",
                                                    modifier = Modifier.size(15.dp)
                                                )
                                            }
                                        }
                                    }
                                    OutlinedButton(
                                        onClick = { /*TODO*/ },
                                        modifier = Modifier.size(75.dp),  //avoid the oval shape
                                        shape = CircleShape,
                                        border = BorderStroke(1.dp, Color.Blue),
                                        contentPadding = PaddingValues(0.dp),  //avoid the little icon
                                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Blue)
                                    ) {
                                        Column(verticalArrangement = Arrangement.Center) {
                                            Icon(
                                                Icons.Default.Add,
                                                contentDescription = "content description",
                                                modifier = Modifier.absolutePadding(7.dp)
                                            )
                                            Text("Rate it!")
                                        }
                                    }
                                }
                            }
                        }
                        HorizontalDivider()
                        Column {
                            FlowRow(
                                modifier = Modifier.padding(4.dp),
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(
                                    // painter = painterResource(id = R.drawable.thumb_down_24px),
                                    Icons.Rounded.ThumbUp,
                                    contentDescription = "Localized description",
                                    modifier = Modifier
                                        .padding(12.dp),
                                )
                                AssistChip(
                                    onClick = { },
                                    label = { Text("Natural") },
                                    leadingIcon = {
                                        Icon(
                                            Icons.Filled.Face,
                                            contentDescription = "Localized description",
                                            Modifier.size(AssistChipDefaults.IconSize)
                                        )
                                    }
                                )
                                AssistChip(
                                    onClick = { },
                                    label = { Text("Vegan") },
                                    leadingIcon = {
                                        Icon(
                                            Icons.Filled.Face,
                                            contentDescription = "Localized description",
                                            Modifier.size(AssistChipDefaults.IconSize)
                                        )
                                    }
                                )
                                AssistChip(
                                    onClick = { },
                                    label = { Text("Gluten-Free") },
                                    leadingIcon = {
                                        Icon(
                                            Icons.Filled.Face,
                                            contentDescription = "Localized description",
                                            Modifier.size(AssistChipDefaults.IconSize)
                                        )
                                    }
                                )
                            }
                            FlowRow(
                                modifier = Modifier.padding(4.dp),
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(
                                    // painter = painterResource(id = R.drawable.thumb_down_24px),
                                    Icons.Rounded.ThumbUp,
                                    contentDescription = "Localized description",
                                    modifier = Modifier
                                        .padding(12.dp),
                                )
                                AssistChip(
                                    onClick = { },
                                    label = { Text("Non-organic") },
                                    leadingIcon = {
                                        Icon(
                                            Icons.Filled.Face,
                                            contentDescription = "Localized description",
                                            Modifier.size(AssistChipDefaults.IconSize)
                                        )
                                    }
                                )
                            }
                            Column {
                                val state by remember { mutableStateOf(0) }
                                SecondaryTabRow(selectedTabIndex = state) {
                                    Tab(selected = false,
                                        onClick = {},
                                        text = { Text("Overview") })
                                    Tab(selected = false,
                                        onClick = {},
                                        text = { Text("Ingredient") })
                                    Tab(selected = false,
                                        onClick = {},
                                        text = { Text("Nutrition") })
                                    Tab(selected = false,
                                        onClick = {},
                                        text = { Text("Specs") })
                                }
                            }
                            Surface(
                                color = MaterialTheme.colorScheme.surfaceTint,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .absolutePadding(bottom = 5.dp, top = 0.7.dp)
                            ) {
                                Text("Overview goes here")
                            }
                        }
                        Row {
                            Column {
                                Row {
                                    Icon(
                                        Icons.Default.Star,
                                        contentDescription = "content description",
                                        modifier = Modifier.size(15.dp)
                                    )
                                    Icon(
                                        Icons.Default.Star,
                                        contentDescription = "content description",
                                        modifier = Modifier.size(15.dp)
                                    )
                                    Icon(
                                        Icons.Default.Star,
                                        contentDescription = "content description",
                                        modifier = Modifier.size(15.dp)
                                    )
                                }
                                Icon(
                                    // painter = painterResource(id = R.drawable.thumb_down_24px),
                                    Icons.Rounded.AccountCircle,
                                    contentDescription = "Localized description",
                                    modifier = Modifier
                                        .padding(12.dp),
                                )
                            }
                            Column {
                                Row {
                                    Text(
                                        "Reviewer name",
                                        style = MaterialTheme.typography.labelLarge
                                    )
                                    Spacer(Modifier.weight(1f))
                                    Icon(
                                        Icons.Default.KeyboardArrowUp,
                                        contentDescription = "content description",
                                        modifier = Modifier.size(15.dp)
                                    )
                                    Text("256")
                                    Icon(
                                        Icons.Default.KeyboardArrowDown,
                                        contentDescription = "content description",
                                        modifier = Modifier.size(15.dp)
                                    )
                                }
                                Text("Review content goes here")
                            }
                        }
                        HorizontalDivider()
                        Row {
                            Column {
                                Row {
                                    Icon(
                                        Icons.Default.Star,
                                        contentDescription = "content description",
                                        modifier = Modifier.size(15.dp)
                                    )
                                    Icon(
                                        Icons.Default.Star,
                                        contentDescription = "content description",
                                        modifier = Modifier.size(15.dp)
                                    )
                                    Icon(
                                        Icons.Default.Star,
                                        contentDescription = "content description",
                                        modifier = Modifier.size(15.dp)
                                    )
                                }
                                Icon(
                                    // painter = painterResource(id = R.drawable.thumb_down_24px),
                                    Icons.Rounded.AccountCircle,
                                    contentDescription = "Localized description",
                                    modifier = Modifier
                                        .padding(12.dp),
                                )
                            }
                            Column {
                                Row {
                                    Text(
                                        "Reviewer name",
                                        style = MaterialTheme.typography.labelLarge
                                    )
                                    Spacer(Modifier.weight(1f))
                                    Icon(
                                        Icons.Default.KeyboardArrowUp,
                                        contentDescription = "content description",
                                        modifier = Modifier.size(15.dp)
                                    )
                                    Text("256")
                                    Icon(
                                        Icons.Default.KeyboardArrowDown,
                                        contentDescription = "content description",
                                        modifier = Modifier.size(15.dp)
                                    )
                                }
                                Text("Review content goes here")
                            }
                        }
                        HorizontalDivider()
                        Row {
                            Column {
                                Row {
                                    Icon(
                                        Icons.Default.Star,
                                        contentDescription = "content description",
                                        modifier = Modifier.size(15.dp)
                                    )
                                    Icon(
                                        Icons.Default.Star,
                                        contentDescription = "content description",
                                        modifier = Modifier.size(15.dp)
                                    )
                                    Icon(
                                        Icons.Default.Star,
                                        contentDescription = "content description",
                                        modifier = Modifier.size(15.dp)
                                    )
                                }
                                Icon(
                                    // painter = painterResource(id = R.drawable.thumb_down_24px),
                                    Icons.Rounded.AccountCircle,
                                    contentDescription = "Localized description",
                                    modifier = Modifier
                                        .padding(12.dp),
                                )
                            }
                            Column {
                                Row {
                                    Text(
                                        "Reviewer name",
                                        style = MaterialTheme.typography.labelLarge
                                    )
                                    Spacer(Modifier.weight(1f))
                                    Icon(
                                        Icons.Default.KeyboardArrowUp,
                                        contentDescription = "content description",
                                        modifier = Modifier.size(15.dp)
                                    )
                                    Text("256")
                                    Icon(
                                        Icons.Default.KeyboardArrowDown,
                                        contentDescription = "content description",
                                        modifier = Modifier.size(15.dp)
                                    )
                                }
                                Text("Review content goes here")
                            }
                        }
                        HorizontalDivider()
                        Row {
                            Column {
                                Row {
                                    Icon(
                                        Icons.Default.Star,
                                        contentDescription = "content description",
                                        modifier = Modifier.size(15.dp)
                                    )
                                    Icon(
                                        Icons.Default.Star,
                                        contentDescription = "content description",
                                        modifier = Modifier.size(15.dp)
                                    )
                                    Icon(
                                        Icons.Default.Star,
                                        contentDescription = "content description",
                                        modifier = Modifier.size(15.dp)
                                    )
                                }
                                Icon(
                                    // painter = painterResource(id = R.drawable.thumb_down_24px),
                                    Icons.Rounded.AccountCircle,
                                    contentDescription = "Localized description",
                                    modifier = Modifier
                                        .padding(12.dp),
                                )
                            }
                            Column {
                                Row {
                                    Text(
                                        "Reviewer name",
                                        style = MaterialTheme.typography.labelLarge
                                    )
                                    Spacer(Modifier.weight(1f))
                                    Icon(
                                        Icons.Default.KeyboardArrowUp,
                                        contentDescription = "content description",
                                        modifier = Modifier.size(15.dp)
                                    )
                                    Text("256")
                                    Icon(
                                        Icons.Default.KeyboardArrowDown,
                                        contentDescription = "content description",
                                        modifier = Modifier.size(15.dp)
                                    )
                                }
                                Text("Review content goes here")
                            }
                        }
                    }

                }
            }
        }
    }
}
