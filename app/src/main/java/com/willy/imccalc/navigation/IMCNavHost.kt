package com.willy.imccalc.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.willy.imccalc.data.IMCCalcDatabaseProvider
import com.willy.imccalc.data.IMCCalcRepositoryImpl
import com.willy.imccalc.ui.features.details.DetailsScreen
import com.willy.imccalc.ui.features.details.DetailsViewModel
import com.willy.imccalc.ui.features.history.HistoryScreen
import com.willy.imccalc.ui.features.history.HistoryViewModel
import com.willy.imccalc.ui.features.home.HomeScreen
import com.willy.imccalc.ui.features.home.HomeViewModel
import kotlinx.serialization.Serializable

@Serializable
object HomeRoute

@Serializable
object HistoryRoute

@Serializable
data class DetailsRoute(val id: Long)

@Composable
fun IMCNavHost() {
    val navController = rememberNavController()
    val context = LocalContext.current

    val database = remember {
        IMCCalcDatabaseProvider.provide(context)
    }

    val repository = remember {
        IMCCalcRepositoryImpl(database.dao)
    }

    NavHost(
        navController = navController,
        startDestination = HomeRoute
    ) {

        // ---------- HOME ----------
        composable<HomeRoute> {
            val viewModel = remember {
                HomeViewModel(repository)
            }

            HomeScreen(
                viewModel = viewModel,
                navigateToHistory = {
                    navController.navigate(HistoryRoute)
                }
            )
        }

        // ---------- HISTORY ----------
        composable<HistoryRoute> {
            val viewModel = remember {
                HistoryViewModel(repository)
            }

            HistoryScreen(
                viewModel = viewModel,
                onItemClick = { id ->
                    navController.navigate(DetailsRoute(id))
                },
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        // ---------- DETAILS ----------
        composable<DetailsRoute> {
            val route = it.toRoute<DetailsRoute>()

            val viewModel = remember {
                DetailsViewModel(repository)
            }

            DetailsScreen(
                viewModel = viewModel,
                id = route.id,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}