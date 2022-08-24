package com.lloyd.photos.presentation.photos

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.lloyd.photos.data.models.PhotosResponseItem

@Composable
fun PhotoScreen(photosViewModel: PhotosViewModel) {
    LaunchedEffect(key1 = Unit) {
        photosViewModel.getPhotos()
    }
    LazyColumn(modifier = Modifier.padding(10.dp)) {
        items(items = photosViewModel.photos.value) { photo ->
            PhotoCard(photo = photo)
        }
    }
}

@Composable
fun PhotoCard(photo: PhotosResponseItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(8.dp)
    ) {
        AsyncImage(model = photo.user?.profile_image?.large, contentDescription = "photo",
        contentScale = ContentScale.Crop)
    }
}