package com.example.dietapps.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Condition(
    val saturation: Int,
    val heartRate: Int,
    val preassureMm: Int,
    val preassureHg: Int,
    val temperature: Int,
    @PrimaryKey val id: Int? = null
)
