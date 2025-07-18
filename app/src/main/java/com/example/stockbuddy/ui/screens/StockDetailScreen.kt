package com.example.stockbuddy.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stockbuddy.data.Stock
import com.example.stockbuddy.data.StockNews
import com.example.stockbuddy.viewmodel.StockViewModel
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockDetailScreen(
    symbol: String,
    viewModel: StockViewModel,
    onNavigateBack: () -> Unit
) {
    val stock by viewModel.selectedStock.collectAsState()
    val stockNews by viewModel.stockNews.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val watchlistStocks by viewModel.watchlistStocks.collectAsState()
    val isInWatchlist = watchlistStocks.any { it.symbol == symbol }
    
    LaunchedEffect(symbol) {
        viewModel.selectStock(symbol)
    }
    
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Top App Bar
        TopAppBar(
            title = { Text(symbol) },
            navigationIcon = {
                IconButton(onClick = onNavigateBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            },
            actions = {
                IconButton(
                    onClick = {
                        if (isInWatchlist) {
                            viewModel.removeFromWatchlist(symbol)
                        } else {
                            viewModel.addToWatchlist(symbol)
                        }
                    }
                ) {
                    Icon(
                        if (isInWatchlist) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = if (isInWatchlist) "Remove from watchlist" else "Add to watchlist",
                        tint = if (isInWatchlist) Color.Red else MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        )
        
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            stock?.let { stockData ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        StockPriceCard(stock = stockData)
                    }
                    
                    item {
                        StockDetailsCard(stock = stockData)
                    }
                    
                    item {
                        Text(
                            text = "Recent News",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                    
                    items(stockNews) { news ->
                        NewsCard(news = news)
                    }
                }
            } ?: run {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Stock not found",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}

@Composable
fun StockPriceCard(stock: Stock) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = stock.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            
            Text(
                text = stock.symbol,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Column {
                    Text(
                        text = "$${DecimalFormat("#,##0.00").format(stock.price)}",
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${if (stock.change >= 0) "+" else ""}${DecimalFormat("#,##0.00").format(stock.change)}",
                            fontSize = 16.sp,
                            color = if (stock.change >= 0) Color.Green else Color.Red,
                            fontWeight = FontWeight.SemiBold
                        )
                        
                        Text(
                            text = " (${if (stock.changePercent >= 0) "+" else ""}${DecimalFormat("#,##0.00").format(stock.changePercent)}%)",
                            fontSize = 16.sp,
                            color = if (stock.changePercent >= 0) Color.Green else Color.Red,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun StockDetailsCard(stock: Stock) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Market Data",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                stock.high?.let { high ->
                    DetailRow("Day High", "$${DecimalFormat("#,##0.00").format(high)}")
                }
                
                stock.low?.let { low ->
                    DetailRow("Day Low", "$${DecimalFormat("#,##0.00").format(low)}")
                }
                
                stock.open?.let { open ->
                    DetailRow("Open", "$${DecimalFormat("#,##0.00").format(open)}")
                }
                
                stock.previousClose?.let { close ->
                    DetailRow("Previous Close", "$${DecimalFormat("#,##0.00").format(close)}")
                }
                
                stock.volume?.let { volume ->
                    DetailRow("Volume", NumberFormat.getInstance().format(volume))
                }
                
                stock.marketCap?.let { marketCap ->
                    DetailRow("Market Cap", "$${NumberFormat.getInstance().format(marketCap)}")
                }
                
                stock.pe?.let { pe ->
                    DetailRow("P/E Ratio", DecimalFormat("#,##0.00").format(pe))
                }
                
                stock.week52High?.let { high ->
                    DetailRow("52W High", "$${DecimalFormat("#,##0.00").format(high)}")
                }
                
                stock.week52Low?.let { low ->
                    DetailRow("52W Low", "$${DecimalFormat("#,##0.00").format(low)}")
                }
            }
        }
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun NewsCard(news: StockNews) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = news.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            Text(
                text = news.summary,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = news.source,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.primary
                )
                
                Text(
                    text = news.timePublished.take(10), // Show just the date
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}