package com.lloyd.photos.data.network

import com.lloyd.photos.BuildConfig
import com.lloyd.photos.data.models.PhotosResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoService {
    @GET("/photos")
    suspend fun getPhotos(@Query("client_id") client_id: String = BuildConfig.CLIENT_ID): PhotosResponse
}