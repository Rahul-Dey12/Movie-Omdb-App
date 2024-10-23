package com.example.movieapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.movieapplication.ui.MovieViewModel
import com.example.movieapplication.ui.screens.MovieDetailScreen
import com.example.movieapplication.ui.screens.MovieListScreenWithPaging
import com.example.movieapplication.ui.theme.MovieApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "search") {
                        composable("search") {
                            val searchString = viewModel.searchText
                            val movieListPaging = viewModel.moviePagingData.collectAsLazyPagingItems()
                            MovieListScreenWithPaging(
                                searchString = searchString.value,
                                movieList = movieListPaging,
                                onSearch = { viewModel.search(it) },
                                onItemClick = {
                                    if(it!=null) {
                                        viewModel.setMovieSelected(it)
                                        navController.navigate("details")
                                    }
                                },
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                        composable("details") {
                            MovieDetailScreen(
                                viewModel.selectedMovie,
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                    }
                }
            }
        }
    }
}