package com.willy.imccalc.ui.features.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    navigateToHistory: () -> Unit
) {
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var sex by remember { mutableStateOf("M") }
    var activity by remember { mutableStateOf(1.2) }

    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        OutlinedTextField(
            value = height,
            onValueChange = { height = it },
            label = { Text("Altura (cm)") }
        )

        OutlinedTextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text("Peso (kg)") }
        )

        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("Idade") }
        )

        Button(onClick = {
            viewModel.calculate(
                height = height.toLong(),
                weight = weight.toDouble(),
                age = age.toInt(),
                sex = sex,
                activityFactor = activity
            )
        }) {
            Text("Calcular")
        }

        Button(onClick = navigateToHistory) {
            Text("Ver histórico")
        }

        state.result?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text("IMC: ${"%.2f".format(it.imc)}")
            Text(it.imcClassification)
            Text("TMB: ${"%.2f".format(it.tmb)}")
            Text("Peso ideal: ${"%.2f".format(it.idealWeight)}")
            Text("Calorias/dia: ${"%.2f".format(it.dailyCalories)}")
        }
    }
}