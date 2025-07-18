# StockBuddy - Android Stock Analysis App

<p align="center">
  <img src="https://img.shields.io/badge/Platform-Android-green.svg" alt="Platform">
  <img src="https://img.shields.io/badge/Language-Kotlin-blue.svg" alt="Language">
  <img src="https://img.shields.io/badge/UI-Jetpack%20Compose-orange.svg" alt="UI">
  <img src="https://img.shields.io/badge/Architecture-MVVM-red.svg" alt="Architecture">
  <img src="https://img.shields.io/badge/Design-Material%203-purple.svg" alt="Design">
</p>

## Overview

StockBuddy is a clean, user-friendly Android application that provides comprehensive stock analysis and portfolio tracking capabilities. The app focuses on delivering real-time stock information, detailed reports, and personalized watchlist management through an intuitive interface built with Jetpack Compose and Material 3 design principles.

## Features

### 🔍 **Stock Search & Analysis**
- **Smart Search Bar**: Prominently displayed search functionality on the main screen
- **Multi-format Support**: Accept both ticker symbols (AAPL, GOOGL) and company names (Apple, Google)
- **Auto-suggestions**: Real-time search suggestions as users type
- **Popular Stocks**: Quick access to trending and popular stocks
- **Visual Indicators**: Yellow star icons (⭐) identify stocks already in your watchlist

### 📊 **Detailed Stock Reports**
When you select a stock, get comprehensive information including:

#### Real-time Data
- Current stock price with live updates
- Price change (absolute and percentage) with color coding
- Day's high/low prices
- Opening price and previous close
- Volume traded
- Market capitalization

#### Technical Analysis
- 52-week high/low ranges
- P/E ratio and key financial metrics
- Comprehensive market data display
- Professional-grade financial information

#### News Integration
- Latest news articles related to the stock
- Article summaries and full details
- Source attribution and publication dates
- Clean, readable news presentation

### ⭐ **Watchlist Management**
- **Personal Watchlist**: Dedicated tab for tracking your favorite stocks
- **One-tap Addition**: Easy addition from stock detail screens with heart icon (❤️)
- **Visual Feedback**: Heart turns red when stock is added to watchlist
- **Quick Overview**: Compact view showing key metrics for all watchlisted stocks
- **Easy Removal**: Delete functionality with confirmation
- **Star Indicators**: Yellow stars identify watchlisted stocks across the app

### 🎨 **Material 3 Design**
- **Clean & Minimal**: Focus on essential information without clutter
- **Custom Theme**: Stock-specific color scheme with financial theming
- **Responsive Layout**: Optimized for different screen sizes
- **Color-coded Data**: Green for gains, red for losses, gold for watchlist items
- **Professional UI**: Card-based layout with proper elevation and spacing

## Technical Architecture

### **Tech Stack**
- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Design System**: Material 3
- **Architecture**: MVVM (Model-View-ViewModel)
- **Build System**: Gradle with Kotlin DSL
- **Navigation**: Navigation Compose
- **State Management**: StateFlow and Compose State
- **Async Operations**: Kotlin Coroutines
- **HTTP Client**: Retrofit2 (configured for future API integration)

### **Project Structure**
```
app/src/main/java/com/example/stockbuddy/
├── data/
│   ├── Stock.kt                 # Data models
│   ├── StockRepository.kt       # Data layer with mock implementation
│   └── WatchlistRepository.kt   # Watchlist state management
├── navigation/
│   └── StockBuddyNavigation.kt  # Navigation setup
├── ui/
│   ├── screens/
│   │   ├── HomeScreen.kt        # Search and popular stocks
│   │   ├── StockDetailScreen.kt # Detailed stock information
│   │   └── WatchlistScreen.kt   # Watchlist management
│   └── theme/
│       ├── Color.kt             # Custom color scheme
│       ├── Theme.kt             # Material 3 theme
│       └── Type.kt              # Typography
├── viewmodel/
│   └── StockViewModel.kt        # Business logic and state management
└── MainActivity.kt              # Entry point with navigation
```

### **Key Components**

#### Data Layer
- **Stock**: Data class representing stock information
- **StockRepository**: Handles stock data fetching with mock implementation
- **WatchlistRepository**: Manages watchlist state with reactive updates

#### UI Layer
- **HomeScreen**: Search functionality and popular stocks display
- **StockDetailScreen**: Comprehensive stock analysis with news
- **WatchlistScreen**: Personal portfolio management

#### Business Logic
- **StockViewModel**: Coordinates data flow and business logic
- **Navigation**: Seamless navigation between screens
- **State Management**: Reactive UI updates using StateFlow

## Installation & Setup

### Prerequisites
- Android Studio Arctic Fox or later
- Android SDK 24+ (Android 7.0)
- Kotlin 1.9.0+
- Gradle 8.0+

### Building the App

1. **Clone the Repository**
   ```bash
   git clone <repository-url>
   cd stock_buddy
   ```

2. **Build the Project**
   ```bash
   ./gradlew build
   ```

3. **Build Debug APK**
   ```bash
   ./gradlew assembleDebug
   ```

4. **Install to Device**
   ```bash
   ./gradlew installDebug
   ```

### Development Commands

```bash
# Clean build
./gradlew clean

# Run unit tests
./gradlew test

# Run instrumented tests
./gradlew connectedAndroidTest

# Generate lint report
./gradlew lintDebug

# Build release APK
./gradlew assembleRelease
```

## App Flow & User Experience

### Primary User Journey
1. **Launch**: Clean home screen with prominent search bar
2. **Search**: Type stock symbol or company name for instant suggestions
3. **Select**: Choose from search results or popular stocks
4. **Analyze**: View comprehensive stock data, charts, and news
5. **Track**: Add to watchlist with heart icon (turns red when added)
6. **Manage**: Switch to watchlist tab for portfolio overview

### Navigation Structure
```
├── Home/Search (Primary Tab)
│   ├── Search Bar with Auto-suggestions
│   ├── Popular Stocks Grid
│   └── Yellow Stars for Watchlisted Items
├── Stock Detail (Detail View)
│   ├── Real-time Price Data
│   ├── Technical Analysis
│   ├── News Feed
│   └── Watchlist Toggle (Red Heart)
└── Watchlist (Secondary Tab)
    ├── Portfolio Overview
    ├── Stock Cards with Stars
    └── Quick Remove Actions
```

## Design Principles

### **Visual Hierarchy**
- **Primary Actions**: Prominent search bar and navigation
- **Data Presentation**: Clear price displays with color coding
- **Visual Cues**: Stars for watchlisted items, red hearts for favorites
- **Information Density**: Balanced content without overwhelming users

### **Color Scheme**
- **Primary**: StockBuddy Blue (#1976D2) for branding
- **Success**: Green (#4CAF50) for positive price changes
- **Error**: Red (#F44336) for negative price changes
- **Warning**: Gold (#FFD700) for watchlist indicators
- **Neutral**: Material 3 surface colors for backgrounds

### **Typography**
- **Headers**: Bold, high contrast for stock symbols and prices
- **Body**: Medium weight for descriptions and details
- **Captions**: Light weight for metadata and timestamps

## Data & Mock Implementation

### **Mock Data Features**
- **Realistic Stock Data**: Popular stocks with simulated prices
- **Dynamic Updates**: Price variations to simulate real-time data
- **News Integration**: Sample news articles for each stock
- **Search Functionality**: Supports both symbol and company name searches

### **Included Mock Stocks**
- Apple Inc. (AAPL)
- Alphabet Inc. (GOOGL)
- Microsoft Corporation (MSFT)
- Amazon.com Inc. (AMZN)
- Tesla Inc. (TSLA)
- NVIDIA Corporation (NVDA)
- Meta Platforms Inc. (META)
- Netflix Inc. (NFLX)

## Future Enhancements

### **Planned Features**
- **Real API Integration**: Connect to financial data providers
- **Advanced Charts**: Interactive price charts with technical indicators
- **Portfolio Tracking**: Full portfolio management with transactions
- **Price Alerts**: Push notifications for significant price movements
- **Social Features**: Community insights and shared watchlists
- **AI Insights**: Machine learning-powered stock recommendations
- **Export Options**: Share reports and export data
- **Offline Mode**: Cache data for offline viewing

### **Technical Improvements**
- **Database Integration**: Local storage with Room
- **Background Sync**: Periodic data updates
- **Performance**: Lazy loading and data pagination
- **Testing**: Comprehensive unit and integration tests
- **Security**: API key management and data encryption

## Contributing

### **Development Guidelines**
1. Follow MVVM architecture patterns
2. Use Jetpack Compose for all UI components
3. Implement proper error handling and loading states
4. Write unit tests for business logic
5. Follow Material 3 design guidelines
6. Use Kotlin coroutines for async operations

### **Code Style**
- Follow Kotlin coding conventions
- Use meaningful variable and function names
- Add comments for complex business logic
- Maintain consistent formatting

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgments

- **Material Design**: Google's Material 3 design system
- **Jetpack Compose**: Modern Android UI toolkit
- **Kotlin**: Primary programming language
- **Android Architecture Components**: MVVM implementation

---

**StockBuddy** - Your smart companion for stock market analysis and portfolio management. Built with modern Android development practices and designed for the future of mobile finance applications.

*Created with ❤️ using Jetpack Compose and Material 3*