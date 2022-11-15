package com.example.dietapps.data


import kotlinx.coroutines.flow.Flow

interface ConditionRepository {

    suspend fun generateCondition(condition: Condition)

    suspend fun getConditionByID(id: Int): Condition?

    fun getCondition(): Flow<List<Condition>>
}