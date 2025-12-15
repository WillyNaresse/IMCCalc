package com.willy.imccalc.ui.features.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel,
    id: Long,
    onNavigateBack: () -> Unit
) {
    LaunchedEffect(id) {
        viewModel.load(id)
    }

    val item by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalhes da medição") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Voltar"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        item?.let {
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                Text("IMC: ${"%.2f".format(it.imc)}")
                Text(it.imcClassification)
                Text("TMB: ${"%.2f".format(it.tmb)}")
                Text("Peso ideal: ${"%.2f".format(it.idealWeight)}")
                Text("Calorias diárias: ${"%.2f".format(it.dailyCalories)}")
            }
        }
    }
}