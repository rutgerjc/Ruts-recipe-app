package com.runitrut.ruts_recipe_app

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun CategoryDetailScreen(
    category: Category
    ){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            // Displays name of category
            text = category.strCategory,
            // Aligns text to center
            textAlign = TextAlign.Center)
        // Displays image of category
        Image(
            // Loads image from URL
            painter = rememberAsyncImagePainter(category.strCategoryThumb),
            // Displays content description
            contentDescription = "${category.strCategory} Thumbnail",
            modifier = Modifier
                .wrapContentSize()
                .aspectRatio(1f)
        )
        Text(
            // Displays description of category
            text = category.strCategoryDescription,
            // Aligns text to center
            textAlign = TextAlign.Justify,
            // Allows scrolling of text if the text is too long
            modifier = Modifier.verticalScroll(rememberScrollState())
        )
    }
}