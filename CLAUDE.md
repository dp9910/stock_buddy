# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

# StockBuddy - Android Stock Analysis App

## Overview
StockBuddy is a clean, user-friendly Android application that provides comprehensive stock analysis and portfolio tracking capabilities. The app focuses on delivering real-time stock information, detailed reports, and personalized watchlist management through an intuitive interface.

## Core Features

### 1. Stock Search & Analysis
- **Primary Input**: Clean search bar prominently displayed on the main screen
- **Stock Symbol Support**: Accept both ticker symbols (AAPL, GOOGL) and company names (Apple, Google)
- **Auto-suggestions**: Provide real-time suggestions as users type
- **Quick Access**: Recent searches and popular stocks for easy selection

### 2. Detailed Stock Reports
When a user selects a stock, generate a comprehensive report including:

#### Real-time Data
- Current stock price with live updates
- Price change (absolute and percentage)
- Day's high/low prices
- Opening price
- Previous close
- Volume traded
- Market capitalization

#### Technical Analysis
- 52-week high/low
- Moving averages (50-day, 200-day)
- P/E ratio and other key metrics
- Support and resistance levels
- Price charts (1D, 1W, 1M, 3M, 1Y views)

#### News & Sentiment
- Latest news articles related to the stock
- Earnings reports and announcements
- Analyst ratings and price targets
- Social sentiment indicators
- Market-moving events

### 3. Watchlist Management
- **Personal Watchlist Tab**: Dedicated section for user's tracked stocks
- **Easy Addition**: One-tap addition from stock reports
- **Quick Overview**: Compact view showing key metrics for all watchlisted stocks
- **Custom Organization**: Ability to reorder, group, or categorize stocks
- **Price Alerts**: Optional push notifications for significant price movements

## User Interface Design

### Design Principles
- **Clean & Minimal**: Focus on essential information without clutter
- **Material Design**: Follow Android Material Design guidelines
- **Dark/Light Theme**: Support both themes with user preference
- **Responsive Layout**: Optimize for different screen sizes

### Navigation Structure
```
├── Home/Search (Primary Tab)
│   ├── Search Bar
│   ├── Recent Searches
│   └── Popular Stocks
├── Stock Report (Detail View)
│   ├── Price Overview
│   ├── Charts
│   ├── News Feed
│   └── Technical Data
└── Watchlist (Secondary Tab)
    ├── Portfolio Overview
    ├── Stock Cards
    └── Quick Actions
```

### Key UI Components
- **Search Bar**: Prominent, with search icon and placeholder text
- **Stock Cards**: Clean cards showing essential info (symbol, price, change)
- **Price Charts**: Interactive charts with zoom and time range selection
- **News Cards**: Image thumbnails with headlines and source attribution
- **Floating Action Button**: Quick add to watchlist functionality

## Technical Requirements

### Data Sources
- **Real-time Prices**: Integration with financial APIs (Alpha Vantage, Yahoo Finance, or similar)
- **News Data**: Financial news aggregators and RSS feeds
- **Company Info**: Fundamental data providers

### Performance
- **Fast Loading**: Implement caching for frequently accessed data
- **Offline Support**: Cache recent data for offline viewing
- **Smooth Navigation**: Implement proper loading states and transitions

### User Experience
- **Quick Search**: Instant results with minimal typing
- **Smart Defaults**: Remember user preferences and frequently viewed stocks
- **Error Handling**: Graceful handling of network issues and invalid symbols

## App Flow

### Primary User Journey
1. User opens app → Clean home screen with search bar
2. User types stock symbol/name → Auto-suggestions appear
3. User selects stock → Detailed report loads with real-time data
4. User reviews price, charts, and news → Comprehensive analysis view
5. User adds to watchlist → One-tap addition with confirmation
6. User switches to watchlist tab → Overview of all tracked stocks

### Secondary Features
- **Historical Data**: Access to historical price movements
- **Comparison Tool**: Side-by-side stock comparison
- **Export Options**: Share reports or export data
- **Settings**: Customization options for refresh intervals, themes, etc.

## Success Metrics
- **User Engagement**: Time spent analyzing stocks
- **Watchlist Usage**: Number of stocks added and actively monitored
- **Search Efficiency**: Speed from search to actionable insights
- **Data Accuracy**: Real-time price updates and news relevance

## Future Enhancements
- **Portfolio Tracking**: Full portfolio management with buy/sell tracking
- **AI Insights**: Machine learning-powered stock recommendations
- **Social Features**: Community insights and shared watchlists
- **Advanced Charting**: More sophisticated technical analysis tools

## Development Notes
- Use modern Android development practices (Kotlin, Jetpack Compose)
- Implement proper state management for real-time data
- Ensure API rate limiting compliance
- Follow Android security best practices for data handling
- Implement proper error handling and user feedback mechanisms

This is an Android application called "Stock Buddy" built with Kotlin and Jetpack Compose. The project follows standard Android project structure with Gradle as the build system.

## Architecture

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose with Material 3
- **Build System**: Gradle with Kotlin DSL
- **Package Structure**: `com.example.stockbuddy`
- **Min SDK**: 24, Target SDK: 36, Compile SDK: 36

## Common Commands

### Build Commands
```bash
# Build the project
gradlew.bat build

# Build debug APK
gradlew.bat assembleDebug

# Build release APK
gradlew.bat assembleRelease

# Clean build
gradlew.bat clean
```

### Testing Commands
```bash
# Run unit tests
gradlew.bat test

# Run instrumented tests
gradlew.bat connectedAndroidTest

# Run specific test
gradlew.bat testDebugUnitTest
```

### Development Commands
```bash
# Install debug APK to connected device
gradlew.bat installDebug

# Check for lint issues
gradlew.bat lint

# Generate lint report
gradlew.bat lintDebug
```

## Project Structure

- `app/src/main/java/com/example/stockbuddy/` - Main application code
- `app/src/main/java/com/example/stockbuddy/ui/theme/` - Compose theme definitions
- `app/src/test/` - Unit tests
- `app/src/androidTest/` - Instrumented tests
- `gradle/libs.versions.toml` - Version catalog for dependencies

## Key Dependencies

- AndroidX Core KTX
- Compose BOM (Bill of Materials)
- Material 3 for Compose
- Activity Compose
- Lifecycle Runtime KTX
- JUnit for testing
- Espresso for UI testing

## Development Notes

- Uses Gradle version catalog for dependency management
- Compose is enabled with the latest stable version
- Proguard is configured but disabled for debug builds
- Java 11 compatibility level
- Uses AndroidX libraries throughout