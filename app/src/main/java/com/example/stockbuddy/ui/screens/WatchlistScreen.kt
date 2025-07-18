package com.example.stockbuddy.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stockbuddy.data.Stock
import com.example.stockbuddy.viewmodel.StockViewModel
import java.text.DecimalFormat

@Composable
fun WatchlistScreen(
    viewModel: StockViewModel,
    onStockClick: (String) -> Unit
) {
    val watchlistStocks by viewModel.watchlistStocks.collectAsState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        Text(
            text = "My Watchlist",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        
        if (watchlistStocks.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Your watchlist is empty",
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center
                    )
                    
                    Text(
                        text = "Search for stocks and add them to your watchlist",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(watchlistStocks) { stock ->
                    WatchlistStockCard(
                        stock = stock,
                        onClick = { onStockClick(stock.symbol) },
                        onRemove = { viewModel.removeFromWatchlist(stock.symbol) }
                    )
                }
            }
        }
    }
}

@Composable
fun WatchlistStockCard(
    stock: Stock,
    onClick: () -> Unit,
    onRemove: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stock.symbol,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Icon(
                        Icons.Default.Star,
                        contentDescription = "In Watchlist",
                        tint = Color(0xFFFFD700), // Gold/Yellow color
                        modifier = Modifier
                            .size(20.dp)
                            .padding(start = 8.dp)
                    )
                }
                
                Text(
                    text = stock.name,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }
            
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "$${DecimalFormat("#,##0.00").format(stock.price)}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${if (stock.change >= 0) "+" else ""}${DecimalFormat("#,##0.00").format(stock.change)}",
                        fontSize = 14.sp,
                        color = if (stock.change >= 0) Color.Green else Color.Red,
                        fontWeight = FontWeight.Medium
                    )
                    
                    Text(
                        text = " (${if (stock.changePercent >= 0) "+" else ""}${DecimalFormat("#,##0.00").format(stock.changePercent)}%)",
                        fontSize = 14.sp,
                        color = if (stock.changePercent >= 0) Color.Green else Color.Red,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
            
            IconButton(
                onClick = onRemove,
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = "Remove from watchlist",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}