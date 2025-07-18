package com.example.stockbuddy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.stockbuddy.navigation.StockBuddyNavigation
import com.example.stockbuddy.ui.theme.StockBuddyTheme
import com.example.stockbuddy.viewmodel.StockViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StockBuddyTheme {
                StockBuddyApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockBuddyApp() {
    val navController = rememberNavController()
    val viewModel: StockViewModel = viewModel()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    
    // Only show bottom navigation on main screens
    val showBottomNav = currentRoute in listOf("home", "watchlist")
    
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (showBottomNav) {
                NavigationBar {
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                        label = { Text("Search") },
                        selected = currentRoute == "home",
                        onClick = {
                            navController.navigate("home") {
                                popUpTo("home") { inclusive = true }
                            }
                        }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.List, contentDescription = "Watchlist") },
                        label = { Text("Watchlist") },
                        selected = currentRoute == "watchlist",
                        onClick = {
                            navController.navigate("watchlist") {
                                popUpTo("home")
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        StockBuddyNavigation(
            navController = navController,
            viewModel = viewModel
        )
    }
}