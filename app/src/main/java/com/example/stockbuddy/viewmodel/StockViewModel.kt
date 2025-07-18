package com.example.stockbuddy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockbuddy.data.Stock
import com.example.stockbuddy.data.StockNews
import com.example.stockbuddy.data.StockRepository
import com.example.stockbuddy.data.StockSearchResult
import com.example.stockbuddy.data.WatchlistRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StockViewModel : ViewModel() {
    
    private val stockRepository = StockRepository()
    private val watchlistRepository = WatchlistRepository()
    
    private val _searchResults = MutableStateFlow<List<StockSearchResult>>(emptyList())
    val searchResults: StateFlow<List<StockSearchResult>> = _searchResults.asStateFlow()
    
    private val _selectedStock = MutableStateFlow<Stock?>(null)
    val selectedStock: StateFlow<Stock?> = _selectedStock.asStateFlow()
    
    private val _stockNews = MutableStateFlow<List<StockNews>>(emptyList())
    val stockNews: StateFlow<List<StockNews>> = _stockNews.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _watchlistStocks = MutableStateFlow<List<Stock>>(emptyList())
    val watchlistStocks: StateFlow<List<Stock>> = _watchlistStocks.asStateFlow()
    
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()
    
    init {
        loadInitialData()
        observeWatchlist()
    }
    
    private fun loadInitialData() {
        viewModelScope.launch {
            stockRepository.searchStocks("").collect { results ->
                _searchResults.value = results
            }
        }
    }
    
    private fun observeWatchlist() {
        viewModelScope.launch {
            watchlistRepository.watchlist.collect { symbols ->
                val stocks = mutableListOf<Stock>()
                symbols.forEach { symbol ->
                    stockRepository.getStock(symbol).collect { stock ->
                        stock?.let { stocks.add(it) }
                    }
                }
                _watchlistStocks.value = stocks
            }
        }
    }
    
    fun searchStocks(query: String) {
        _searchQuery.value = query
        viewModelScope.launch {
            stockRepository.searchStocks(query).collect { results ->
                _searchResults.value = results
            }
        }
    }
    
    fun selectStock(symbol: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                stockRepository.getStock(symbol).collect { stock ->
                    _selectedStock.value = stock
                    _isLoading.value = false
                }
                
                stockRepository.getStockNews(symbol).collect { news ->
                    _stockNews.value = news
                }
            } catch (e: Exception) {
                _isLoading.value = false
            }
        }
    }
    
    fun addToWatchlist(symbol: String) {
        watchlistRepository.addToWatchlist(symbol)
    }
    
    fun removeFromWatchlist(symbol: String) {
        watchlistRepository.removeFromWatchlist(symbol)
    }
    
    fun isInWatchlist(symbol: String): Boolean {
        return watchlistRepository.isInWatchlist(symbol)
    }
    
    fun refreshStock(symbol: String) {
        selectStock(symbol)
    }
    
    fun clearSelectedStock() {
        _selectedStock.value = null
        _stockNews.value = emptyList()
    }
}