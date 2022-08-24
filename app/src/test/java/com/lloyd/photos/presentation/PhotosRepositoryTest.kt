package com.lloyd.photos.presentation

import com.lloyd.photos.data.network.PhotoService
import com.lloyd.photos.presentation.photos.PhotosRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

@DisplayName("Photos Repository Test cases")
@OptIn(ExperimentalCoroutinesApi::class)
class PhotosRepositoryTest {
    private lateinit var photosRepository: PhotosRepository
    private lateinit var photoService: PhotoService
    private lateinit var mockWebServer: MockWebServer
    private lateinit var baseUrl: HttpUrl

    @BeforeEach
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        baseUrl = mockWebServer.url("/photos/")
        photoService = Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(PhotoService::class.java)
        photosRepository = PhotosRepository(photoService)
    }

    @Test
    fun getPhotoSuccessful() = runTest {
        //Given
        mockWebServer.enqueue(
            MockResponse().setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(ClassLoader.getSystemResource("photos_response.json").readText())
        )
        //When
        val flowItems = photosRepository.getPhotos().toList()
        val response = flowItems[0].data

        //Then
        Assertions.assertNotNull(response)
    }

    @Test
    fun getPhotosFailure() = runTest {
        //Given
        mockWebServer.enqueue(
            MockResponse().setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST)
        )

        //When
        val flowItems = photosRepository.getPhotos().toList()
        val response = flowItems[0].data

        //Then
        Assertions.assertNull(response)
    }

    @AfterEach
    fun tearDown() {
        mockWebServer.shutdown()
    }
}