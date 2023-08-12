package com.example.data.util

import com.example.core.data.Resource

object NetworkCallUtils{
    inline fun <T> safeCall(action: () -> Resource<T>): Resource<T> {
        return try {
            action()
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }
}