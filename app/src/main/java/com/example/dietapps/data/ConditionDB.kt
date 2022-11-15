package com.example.dietapps.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Condition::class],
    version = 1
)
abstract class ConditionDB: RoomDatabase() {
    abstract val dao: ConditionDao
}