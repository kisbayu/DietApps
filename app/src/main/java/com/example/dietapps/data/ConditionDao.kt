package com.example.dietapps.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ConditionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun generateCondition(condition: Condition)

    @Query("SELECT * FROM Condition WHERE id= :id")
    suspend fun getConditionByID(id: Int): Condition?

    @Query("SELECT * FROM Condition")
    fun getCondition(): Flow<List<Condition>>
}