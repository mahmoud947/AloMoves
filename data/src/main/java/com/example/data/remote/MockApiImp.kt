package com.example.data.remote

import android.content.Context
import com.example.data.models.response.OverView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MockApiImp (private val context: Context): MockApi {
    private suspend fun readMockResponse(fileName: String): String {
        return withContext(Dispatchers.IO) {
            context.assets.open(fileName).bufferedReader().use { it.readText() }
        }
    }

    private suspend fun mockApiCall(endpoint: String): String {
        return withContext(Dispatchers.Default) {
            try {
                val mockResponseJson = readMockResponse("response.json")
                val mockResponseMap = Gson().fromJson(mockResponseJson, Map::class.java) as Map<String, Any>
                val endpointResponse = mockResponseMap[endpoint] as? Map<*, *>

                Gson().toJson(
                    endpointResponse ?: mapOf(
                        "status" to "error",
                        "message" to "Endpoint not found"
                    )
                )
            } catch (e: Exception) {
                e.printStackTrace()
                Gson().toJson(
                    mapOf(
                        "status" to "error",
                        "message" to "Error parsing mock response"
                    )
                )
            }
        }
    }
    override suspend fun getOverView(): OverView {
       val response = mockApiCall( endpoint = "overview")
       val overView:OverView = Gson().fromJson(response,OverView::class.java)
        return overView
    }
}