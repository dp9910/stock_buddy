package com.example.stockbuddy.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

class StockRepository {
    
    private val mockStocks = listOf(
        Stock("AAPL", "Apple Inc.", 182.52, 2.34, 1.30),
        Stock("GOOGL", "Alphabet Inc.", 2750.23, -15.67, -0.57),
        Stock("MSFT", "Microsoft Corporation", 415.26, 8.91, 2.19),
        Stock("AMZN", "Amazon.com Inc.", 3380.47, 42.15, 1.26),
        Stock("TSLA", "Tesla Inc.", 742.83, -12.45, -1.65),
        Stock("NVDA", "NVIDIA Corporation", 875.34, 23.67, 2.78),
        Stock("META", "Meta Platforms Inc.", 485.72, 7.89, 1.65),
        Stock("NFLX", "Netflix Inc.", 654.21, -3.45, -0.52)
    )
    
    private val popularStocks = listOf(
        StockSearchResult("AAPL", "Apple Inc."),
        StockSearchResult("GOOGL", "Alphabet Inc."),
        StockSearchResult("MSFT", "Microsoft Corporation"),
        StockSearchResult("AMZN", "Amazon.com Inc."),
        StockSearchResult("TSLA", "Tesla Inc.")
    )
    
    fun searchStocks(query: String): Flow<List<StockSearchResult>> = flow {
        delay(300) // Simulate network delay
        
        if (query.isEmpty()) {
            emit(popularStocks)
            return@flow
        }
        
        val results = mockStocks.filter { stock ->
            stock.symbol.contains(query, ignoreCase = true) || 
            stock.name.contains(query, ignoreCase = true)
        }.map { stock ->
            StockSearchResult(stock.symbol, stock.name)
        }
        
        emit(results)
    }
    
    fun getStock(symbol: String): Flow<Stock?> = flow {
        delay(500) // Simulate network delay
        
        val baseStock = mockStocks.find { it.symbol.equals(symbol, ignoreCase = true) }
        
        if (baseStock != null) {
            // Add some random variation to simulate real-time updates
            val priceVariation = Random.nextDouble(-5.0, 5.0)
            val newPrice = baseStock.price + priceVariation
            val change = newPrice - baseStock.price
            val changePercent = (change / baseStock.price) * 100
            
            val detailedStock = baseStock.copy(
                price = newPrice,
                change = change,
                changePercent = changePercent,
                volume = Random.nextLong(1000000, 50000000),
                marketCap = Random.nextLong(500000000000, 3000000000000),
                high = newPrice + Random.nextDouble(0.0, 10.0),
                low = newPrice - Random.nextDouble(0.0, 10.0),
                open = newPrice + Random.nextDouble(-5.0, 5.0),
                previousClose = newPrice - change,
                pe = Random.nextDouble(15.0, 45.0),
                week52High = newPrice + Random.nextDouble(50.0, 200.0),
                week52Low = newPrice - Random.nextDouble(50.0, 200.0)
            )
            
            emit(detailedStock)
        } else {
            emit(null)
        }
    }
    
    fun getStockNews(symbol: String): Flow<List<StockNews>> = flow {
        delay(400) // Simulate network delay
        
        val news = listOf(
            StockNews(
                title = "$symbol Reports Strong Q4 Earnings",
                summary = "Company exceeds analyst expectations with robust revenue growth and positive outlook for next quarter.",
                url = "https://example.com/news/1",
                timePublished = "2024-01-15T10:30:00Z",
                authors = listOf("Financial Reporter"),
                source = "Stock News Daily"
            ),
            StockNews(
                title = "$symbol Announces New Product Launch",
                summary = "Revolutionary product expected to capture significant market share in the coming year.",
                url = "https://example.com/news/2",
                timePublished = "2024-01-14T14:15:00Z",
                authors = listOf("Tech Analyst"),
                source = "Market Watch"
            ),
            StockNews(
                title = "Analyst Upgrades $symbol Price Target",
                summary = "Leading investment firm raises price target citing strong fundamentals and market position.",
                url = "https://example.com/news/3",
                timePublished = "2024-01-13T09:45:00Z",
                authors = listOf("Investment Analyst"),
                source = "Financial Times"
            )
        )
        
        emit(news)
    }
}