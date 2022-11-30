package com.example.dietapps.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Consumption::class],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {
    abstract val dao: ConsumptionDao

}