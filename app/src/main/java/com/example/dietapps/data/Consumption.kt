package com.example.dietapps.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Consumption(
    val title: String,
    val description: String?,
    @PrimaryKey val id: Int? = null
)
