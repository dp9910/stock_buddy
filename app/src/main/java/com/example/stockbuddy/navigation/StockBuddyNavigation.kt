package com.example.stockbuddy.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.stockbuddy.ui.screens.HomeScreen
import com.example.stockbuddy.ui.screens.StockDetailScreen
import com.example.stockbuddy.ui.screens.WatchlistScreen
import com.example.stockbuddy.viewmodel.StockViewModel

@Composable
fun StockBuddyNavigation(
    navController: NavHostController,
    viewModel: StockViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                viewModel = viewModel,
                onStockClick = { symbol ->
                    navController.navigate("stock_detail/$symbol")
                }
            )
        }
        
        composable("watchlist") {
            WatchlistScreen(
                viewModel = viewModel,
                onStockClick = { symbol ->
                    navController.navigate("stock_detail/$symbol")
                }
            )
        }
        
        composable("stock_detail/{symbol}") { backStackEntry ->
            val symbol = backStackEntry.arguments?.getString("symbol") ?: ""
            StockDetailScreen(
                symbol = symbol,
                viewModel = viewModel,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}