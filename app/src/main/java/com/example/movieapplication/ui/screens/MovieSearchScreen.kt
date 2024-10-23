package com.example.movieapplication.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.movieapplication.data.dto.Movie


@Composable
fun MovieListScreenWithPaging(
    searchString: String,
    movieList: LazyPagingItems<Movie>,
    onSearch: (String) -> Unit,
    onItemClick: (Movie?) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            value = searchString,
            onValueChange = {
                onSearch.invoke(it)
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(16.dp))
        LazyColumn {
            items(movieList.itemCount) {
                Text(
                    text = movieList[it]?.Title ?: " ",
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable {
                            onItemClick(movieList[it])
                        }
                )
                HorizontalDivider()
            }
        }
    }
}