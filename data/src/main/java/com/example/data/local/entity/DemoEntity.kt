package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class DemoEntity(
    @PrimaryKey(autoGenerate = false)
    val _id: String,
    val name: String?,
)