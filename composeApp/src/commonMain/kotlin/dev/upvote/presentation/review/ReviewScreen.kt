package dev.upvote.presentation.review

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.upvote.resources.Res
import dev.upvote.resources.barcode_scanner_24px
import dev.upvote.resources.thumb_down_24px
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ReviewScreen(
    component: ReviewComponent,
    viewModel: ReviewViewModel = androidx.lifecycle.viewmodel.compose.viewModel { ReviewViewModel() },
    modifier: Modifier = Modifier
) {
    LaunchedEffect("product") {
        viewModel.getReviews(revieweeKind="product")
    }
    val uiState by viewModel.uiState.collectAsState()
    Scaffold(
        modifier = modifier.fillMaxSize()
    ) {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Row {
                Column {
                    Row {
                        // uiState.reviews.reviews[0].username
                        Icon(
                            Icons.Rounded.Menu, contentDescription = "Localized description",
                            modifier = Modifier
                                .weight(1f)
                                .wrapContentWidth()
                                .border(1.dp, Color.Red)
                        )
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
                                            style = MaterialTheme.typography.titleMedium,
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
                                        Text(
                                            "20 ratings",
                                            style = MaterialTheme.typography.labelSmall,
                                            modifier = Modifier.padding(6.dp, 0.dp)
                                        )
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
                                painter = painterResource(Res.drawable.thumb_down_24px),
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
