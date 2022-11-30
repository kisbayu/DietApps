package com.example.dietapps.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ConsumptionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConsumption(consumption: Consumption)

    @Delete
    suspend fun deleteConsumption(consumption:Consumption)

    @Query("SELECT * FROM Consumption WHERE id= :id")
    suspend fun getConsumptionById(id:Int):Consumption?

    @Query("SELECT * FROM Consumption")
    fun getConsumption(): Flow<List<Consumption>>
}