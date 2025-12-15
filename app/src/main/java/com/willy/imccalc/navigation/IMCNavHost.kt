package com.willy.imccalc.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.willy.imccalc.ui.features.history.HistoryScreen
import com.willy.imccalc.ui.features.home.HomeScreen
import kotlinx.serialization.Serializable

@Serializable
object RoutesList

@Serializable
data class HistoryRoute(val id: Long? = null)

@Composable
fun IMCNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = RoutesList) {
        composable<RoutesList> {
            HomeScreen(
                navigateToHistoryScreen = {
                    navController.navigate(HistoryRoute(null))
                }
            )
        }

        composable<HistoryRoute> {
            val historyRoute = it.toRoute<HistoryRoute>()
            HistoryScreen(
                id = historyRoute.id,
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}