package com.example.data.util

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import retrofit2.Invocation


class AuthInterceptor(
    private val settingsPref: SettingsPref
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val invocation = chain.request().tag(Invocation::class.java)
            ?: return chain.proceed(chain.request())

        val shouldAttachAuthHeader = invocation
            .method()
            .annotations
            .any { it.annotationClass == Authenticated::class }

        return if (shouldAttachAuthHeader) {
            chain.proceed(
                chain.request()
                    .newBuilder().also { builder: Request.Builder ->
                        val token = settingsPref.getToken()
                        builder.addHeader("Authorization", "Bearer $token")
                    }
                    .build()
            )
        } else chain.proceed(chain.request())
    }
}


@Target(AnnotationTarget.FUNCTION)
annotation class Authenticated