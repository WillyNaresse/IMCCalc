package com.willy.imccalc.ui.features.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.willy.imccalc.data.IMCCalcDatabaseProvider
import com.willy.imccalc.data.IMCCalcRepositoryImpl
import com.willy.imccalc.ui.components.IMCCalcItem
import com.willy.imccalc.ui.theme.Blue
import com.willy.imccalc.ui.theme.White

@Composable
fun HistoryScreen(
    id: Long?,
    navigateBack: () -> Unit
) {
    val context = LocalContext.current.applicationContext
    val database = IMCCalcDatabaseProvider.provide(context)
    val repository = IMCCalcRepositoryImpl(database.imcCalcDao)

    val viewModel = viewModel<HistoryViewModel> {
        HistoryViewModel(repository)
    }

    val historyList by viewModel.historyState.collectAsState()

    HistoryContent(
        historyList = historyList,
        onBackClick = navigateBack,
        onEvent = viewModel::onEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryContent(
    historyList: List<com.willy.imccalc.domain.IMC>,
    onBackClick: () -> Unit,
    onEvent: (HistoryEvent) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Histórico de IMC",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Voltar"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Blue,
                    titleContentColor = White,
                    navigationIconContentColor = White
                )
            )
        }
    ) { paddingValues ->
        if (historyList.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(White),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Nenhuma medição encontrada.", color = Blue)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(White),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(items = historyList, key = { it.id }) { imc ->
                    IMCCalcItem(
                        imc = imc,
                        onItemClick = { },
                        onDeleteClick = {
                            onEvent(HistoryEvent.DeleteImc(imc))
                        }
                    )
                }
            }
        }
    }
}