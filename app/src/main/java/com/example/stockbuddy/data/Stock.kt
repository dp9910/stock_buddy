package com.example.stockbuddy.data

data class Stock(
    val symbol: String,
    val name: String,
    val price: Double,
    val change: Double,
    val changePercent: Double,
    val volume: Long? = null,
    val marketCap: Long? = null,
    val high: Double? = null,
    val low: Double? = null,
    val open: Double? = null,
    val previousClose: Double? = null,
    val pe: Double? = null,
    val week52High: Double? = null,
    val week52Low: Double? = null
)

data class StockSearchResult(
    val symbol: String,
    val name: String,
    val type: String = "Common Stock",
    val region: String = "United States",
    val marketOpen: String = "09:30",
    val marketClose: String = "16:00",
    val timezone: String = "UTC-04"
)

data class StockNews(
    val title: String,
    val summary: String,
    val url: String,
    val timePublished: String,
    val authors: List<String>,
    val source: String,
    val bannerImage: String? = null
)