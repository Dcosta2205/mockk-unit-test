package com.lloyd.photos.presentation.photos

import com.lloyd.photos.data.models.PhotosResponse
import com.lloyd.photos.data.network.PhotoService
import com.ssup.composeandcleanarchitecture.domain.state.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PhotosRepository(private val photoService: PhotoService) {
    fun getPhotos(): Flow<DataState<PhotosResponse>> = flow {
        emit(DataState.success(photoService.getPhotos()))
    }.catch {
        emit(DataState.error(it.message ?: "Unknown error"))
    }.flowOn(Dispatchers.IO)
}