# Movie Hunter 🎬

**Movie Hunter** is a modern, high-performance Android application built in **Java** that allows users to explore the world of cinema. The app leverages the [TMDB API](https://www.themoviedb.org/documentation/api) to provide real-time data on popular and upcoming movies, featuring a robust "Favourite" system for offline access.

This project was developed with a strong focus on **Clean Architecture** and **Scalability**, strictly following the **MVVM (Model-View-ViewModel)** design pattern.

## 🚀 Key Features

- **Explore Movies**: Browse live lists of "Popular" and "Upcoming" films directly from TMDB.
- **Search**: Instantly find movies by title using a fast, reactive search interface.
- **Detailed Insights**: View comprehensive movie details including high-resolution posters (Backdrop & Poster), ratings, runtimes, genres, and plot summaries.
- **Watchlist (Favourites)**: Save movies to a local watchlist. This feature uses **Room Database** to ensure your favourites are accessible even without an internet connection.
- **Responsive UI**: A polished interface featuring Glide for smooth image loading and modern Material Design components.

## 🛠 Technical Stack

- **Language**: Java
- **Architecture**: MVVM (Model-View-ViewModel) with LiveData
- **Networking**: [Retrofit 2](https://square.github.io/retrofit/) & OkHttp for REST API communication
- **Local Storage**: [Room Persistence Library](https://developer.android.com/training/data-storage/room) (SQLite abstraction)
- **Image Loading**: [Glide](https://github.com/bumptech/glide) for efficient caching and display
- **Dependency Management**: Gradle (Kotlin DSL)
- **UI Components**: RecyclerView, ConstraintLayout, Material Design 3

## 🏗 Architecture Overview

The app is divided into three distinct layers to ensure separation of concerns:

- **View**: Activities handle the UI and user interactions, observing data changes via LiveData.
- **ViewModel**: Manages UI-related data and survives configuration changes (like screen rotation).
- **Model**: Includes the **Repository** which acts as the single source of truth, coordinating data between the API and the local Room database.

## 🔧 How to Run

1. **Get an API Key**: Sign up for a free account at [The Movie Database (TMDB)](https://www.themoviedb.org/settings/api).
2. **Clone the project**:
   ```sh
   git clone https://github.com/KraivenR/Movie_Hunter_App.git
   ```
3. **Add your API Key**:
   Open `app/src/main/java/com/example/moviehunter/Repository/MovieRepository.java` and replace the placeholder:
   ```java
   private static final String API_KEY = "YOUR_API_KEY_HERE";
   ```
4. **Build & Run**: Open the project in Android Studio and run it on an emulator or physical device.

---

### Author
Developed by **KraivenR** 
- [GitHub](https://github.com/KraivenR)
- Email: ribeirokraiven@gmail.com
