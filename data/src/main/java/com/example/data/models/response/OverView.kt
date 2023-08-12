package com.example.data.models.response


import androidx.annotation.Keep

@Keep
data class OverView(
    val `data`: Data,
    val status: String
)

@Keep
data class Data(
    val coachesName: String,
    val coverPhoto: String,
    val instructor: Instructor,
    val overView: String,
    val seriesName: String
)

@Keep
data class Instructor(
    val about: String,
    val name: String,
    val status: Status,
    val video:String
)

@Keep
data class Status(
    val difficulty: String,
    val intensity: Int,
    val runTime: String
)