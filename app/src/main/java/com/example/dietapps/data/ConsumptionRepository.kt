package com.example.dietapps.data

import kotlinx.coroutines.flow.Flow

interface ConsumptionRepository {
    suspend fun insertConsumption(consumption: Consumption)

    suspend fun deleteConsumption(consumption: Consumption)

    suspend fun getConsumptionById(id:Int):Consumption?

    fun getConsumption(): Flow<List<Consumption>>
}