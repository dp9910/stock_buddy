package com.example.stockbuddy.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class WatchlistRepository {
    
    private val _watchlist = MutableStateFlow<List<String>>(emptyList())
    val watchlist: Flow<List<String>> = _watchlist.asStateFlow()
    
    fun addToWatchlist(symbol: String) {
        val currentList = _watchlist.value.toMutableList()
        if (!currentList.contains(symbol)) {
            currentList.add(symbol)
            _watchlist.value = currentList
        }
    }
    
    fun removeFromWatchlist(symbol: String) {
        val currentList = _watchlist.value.toMutableList()
        currentList.remove(symbol)
        _watchlist.value = currentList
    }
    
    fun isInWatchlist(symbol: String): Boolean {
        return _watchlist.value.contains(symbol)
    }
    
    fun getWatchlistStocks(): Flow<List<String>> = watchlist
}