package com.runitrut.ruts_recipe_app

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun RecipeScreen(
    modifier: Modifier = Modifier,
    viewState:RecipeState,
    navigateToDetails: (Category) -> Unit
){
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        when {
            // Displays Loading
            viewState.loading -> {
                CircularProgressIndicator(modifier.align(Alignment.Center))
            }
            // Displays Error
            viewState.error != null -> {
                Text(text = "Error Occurred")
            }
            else -> {
                // if RecipeScreen isn't loading or didn't have error, Displays Categories
                CategoryScreen(
                    categories = viewState.list,
                    navigateToDetails)
            }
        }
    }
}

// Displays Categories is a lazy grid
@Composable
fun CategoryScreen(categories: List<Category>,
                   navigateToDetails: (Category) -> Unit
){
    // Displays Categories in a lazy grid, 2 columns.
    LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
        // sets items of lazy grid with categories, the parameter of this composable function
        items(categories){
            category ->
            CategoryItem(category = category, navigateToDetails)
        }
    }
}

// How each item looks like
@Composable
fun CategoryItem(category: Category,
                 navigateToDetails: (Category) -> Unit
){
    Column(
        modifier = Modifier
        .padding(8.dp)
        .fillMaxSize()
        .clickable { navigateToDetails(category) },
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        // Displays image of category
        Image(painter = rememberAsyncImagePainter(category.strCategoryThumb),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
        )
        // Displays name's of category
        Text(
            text = category.strCategory,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}