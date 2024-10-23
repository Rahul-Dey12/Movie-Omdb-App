package com.example.movieapplication.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapplication.data.dto.Movie


@Composable
fun MovieDetailScreen(movie: Movie?, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            "Title: ${movie?.Title ?: ""}",
            modifier = Modifier.padding(32.dp)
        )
        Text(
            "Title: ${movie?.Type ?: ""}",
            modifier = Modifier.padding(32.dp)
        )
    }
}

@Preview
@Composable
private fun MovieDetailsPreview() {

}