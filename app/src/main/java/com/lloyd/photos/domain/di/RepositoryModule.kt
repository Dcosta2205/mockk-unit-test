package com.lloyd.photos.domain.di

import com.lloyd.photos.data.network.PhotoService
import com.lloyd.photos.presentation.photos.PhotosRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @ViewModelScoped
    @Provides
    fun provideMainRepository(photoService: PhotoService): PhotosRepository {
        return PhotosRepository(photoService = photoService)
    }
}