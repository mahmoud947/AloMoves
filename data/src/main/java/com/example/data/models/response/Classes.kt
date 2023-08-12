package com.example.data.models.response


import com.google.gson.annotations.SerializedName

data class Classes(
    @SerializedName("data")
    val `data`: ClassData,
    @SerializedName("status")
    val status: String
)

data class ClassData(
    @SerializedName("videos")
    val videos: List<Video>
)


data class Video(
    @SerializedName("description")
    val description: String,
    @SerializedName("time")
    val time: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("video")
    val video: String
)