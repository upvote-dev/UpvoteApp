package dev.upvote.presentation.product

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import dev.upvote.GlobalState
import dev.upvote.globalGlobalState
import dev.upvote.presentation.auth.AuthScreen
import dev.upvote.presentation.auth.signinup.SignInUpViewModel
import kotlinx.coroutines.launch
import dev.upvote.globalMutableStateFlow
import dev.upvote.ui.ExpandableSearchView
import kotlinx.coroutines.flow.update

@Composable
fun ProductScreen(
    component: ProductComponent,
    viewModel: ProductDetailViewModel = viewModel { ProductDetailViewModel() },
    signInUpViewModel: SignInUpViewModel = viewModel { SignInUpViewModel() },
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val globalState by globalGlobalState.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    NavHost(navController, startDestination = "home") {
        composable("home") {
            ProductHomeScreen(
                component = component,
                navController = navController,
                viewModel = viewModel,
                signInUpViewModel = signInUpViewModel,
                globalState = globalState,
                uiState = uiState,
                modifier = modifier
            )
        }
        composable("auth") {
            AuthScreen(
                onDismissRequest = { navController.popBackStack() }
            )
        }
        composable("product/{barcode}") { backStackEntry ->
            val barcode = backStackEntry.arguments?.getString("barcode")
            barcode?.let {
                viewModel.getProductById(it)
                ProductDetailScreen(
                    viewModel = viewModel,
                    signInUpViewModel = signInUpViewModel,
                    navController = navController,
                    uiState = uiState
                )
            }
        }
    }
}

@Composable
fun ProductHomeScreen(
    component: ProductComponent,
    navController: NavHostController,
    viewModel: ProductDetailViewModel,
    signInUpViewModel: SignInUpViewModel,
    globalState: GlobalState,
    uiState: ProductDetailState,
    modifier: Modifier
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val reset = {
        globalMutableStateFlow.update { it.copy(barcode = null) }
        viewModel.clearError()
    }

    LaunchedEffect(globalState.lastErrorStr, uiState.lastErrorStr) {
        val errorMessage = globalState.lastErrorStr ?: uiState.lastErrorStr
        if (!errorMessage.isNullOrBlank()) {
            coroutineScope.launch {
                val result = snackbarHostState.showSnackbar(
                    message = errorMessage,
                    actionLabel = "OK"
                )
                if (result == SnackbarResult.ActionPerformed) {
                    reset()
                }
            }
        }
    }

    Scaffold(
        topBar = {
            ExpandableSearchView(
                searchDisplay = globalState.barcode ?: "",
                onSearchDisplayChanged = {},
                onSearchDisplayClosed = {}
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = modifier
    ) { innerPadding ->
        Column(Modifier.padding(innerPadding)) {
            when {
                globalState.barcode != null && globalState.barcode!!.length > 3 -> {
                    navController.navigate("product/${globalState.barcode}")
                }
                else -> LoadingScreen()
            }
        }
    }
}

@Composable
fun LoadingScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
        )
    }
}
