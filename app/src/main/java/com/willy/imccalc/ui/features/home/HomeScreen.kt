package com.willy.imccalc.ui.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.willy.imccalc.data.Calculations
import com.willy.imccalc.data.IMCCalcDatabaseProvider
import com.willy.imccalc.data.IMCCalcRepositoryImpl
import com.willy.imccalc.navigation.HistoryRoute
import com.willy.imccalc.ui.UiEvent
import com.willy.imccalc.ui.theme.Blue
import com.willy.imccalc.ui.theme.White

@Composable
fun HomeScreen(
    navigateToHistoryScreen: () -> Unit
) {
    val context = LocalContext.current.applicationContext
    val database = IMCCalcDatabaseProvider.provide(context)
    val repository = IMCCalcRepositoryImpl(database.imcCalcDao)
    val viewModel = viewModel<HomeViewModel> {
        HomeViewModel(
            repository = repository
        )
    }

    val height = viewModel.height
    val weight = viewModel.weight
    var resultMessage by remember { mutableStateOf("") }

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = uiEvent.message,
                    )
                }

                is UiEvent.Navigate<*> -> {
                    when (uiEvent.route) {
                        is HistoryRoute -> {
                            navigateToHistoryScreen()
                        }
                    }
                }

                is UiEvent.ShowResult -> {
                    resultMessage = uiEvent.result
                }
            }
        }
    }

    HomeContent(
        height = height,
        weight = weight,
        resultMessage = resultMessage,
        snackbarHostState = snackbarHostState,
        onEvent = viewModel::onEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    height: String,
    weight: String,
    resultMessage: String,
    snackbarHostState: SnackbarHostState,
    onEvent: (HomeEvent) -> Unit = {},
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(HomeEvent.goToHistory(null))
            }) {
                Icon(Icons.Default.Menu, contentDescription = "History")
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "IMC CALCULATOR",
                        textAlign = TextAlign.Center,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Blue,
                    titleContentColor = White
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(color = White)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier
                        .padding(20.dp, 100.dp, 0.dp, 0.dp),
                    text = "Altura (cm)",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    modifier = Modifier
                        .padding(0.dp, 100.dp, 20.dp, 0.dp),
                    text = "Peso (kg)",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(0.4f)
                        .padding(20.dp, 0.dp, 0.dp, 20.dp),
                    value = height,
                    onValueChange = {
                        if (it.length <= 3) {
                            onEvent(HomeEvent.HeightChanged(it))
                        }
                    },
                    label = {
                        Text(text = "Ex.: 174")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.NumberPassword
                    ),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = White,
                        focusedContainerColor = White,
                        focusedLabelColor = Blue,
                        focusedIndicatorColor = Blue,
                        cursorColor = Blue,
                        errorContainerColor = White
                    ),
                )

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .padding(20.dp, 0.dp, 20.dp, 20.dp),
                    value = weight,
                    onValueChange = {
                        if (it.length <= 6) {
                            onEvent(HomeEvent.WeightChanged(it))
                        }
                    },
                    label = {
                        Text(text = "Ex.: 80.65")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal
                    ),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = White,
                        focusedContainerColor = White,
                        focusedLabelColor = Blue,
                        focusedIndicatorColor = Blue,
                        cursorColor = Blue,
                        errorContainerColor = White
                    ),
                )
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue
                ),
                onClick = {
                    onEvent(HomeEvent.Save)
                },
            ) {
                Text(
                    text = "Calcular",
                    fontSize = 18.sp,
                    color = White,
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                text = resultMessage,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Blue,
                textAlign = TextAlign.Center
            )
        }
    }
}