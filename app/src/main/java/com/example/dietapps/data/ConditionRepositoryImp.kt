package com.example.dietapps.data

import kotlinx.coroutines.flow.Flow

class ConditionRepositoryImp (
    private val dao:ConditionDao
        ):ConditionRepository {
    override suspend fun generateCondition(condition: Condition) {
        dao.generateCondition(condition)
    }

    override suspend fun getConditionByID(id: Int): Condition? {
        return dao.getConditionByID(id)
    }

    override fun getCondition(): Flow<List<Condition>> {
       return dao.getCondition()
    }
}