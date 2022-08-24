//package com.lloyd.photos.data.network
//
//import com.lloyd.photos.BuildConfig
//import okhttp3.Interceptor
//import okhttp3.Request
//import okhttp3.Response
//
//const val HEADER_CLIENT_ID = "client_id"
//
//class HeadersInterceptor : Interceptor {
//    override fun intercept(chain: Interceptor.Chain): Response {
//        val request: Request = chain.request()
////        val newRequest: Request.Builder = request.newBuilder().addHeader(
////            HEADER_CLIENT_ID, BuildConfig.CLIENT_ID
////        )
//        return chain.proceed(newRequest.build())
//    }
//}