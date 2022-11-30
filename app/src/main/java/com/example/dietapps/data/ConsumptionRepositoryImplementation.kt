package com.example.dietapps.data

import kotlinx.coroutines.flow.Flow

class ConsumptionRepositoryImplementation(
    private val dao:ConsumptionDao
):ConsumptionRepository {
    override suspend fun insertConsumption(consumption: Consumption) {
        dao.insertConsumption(consumption)
    }

    override suspend fun deleteConsumption(consumption: Consumption) {
        dao.deleteConsumption(consumption)
    }

    override suspend fun getConsumptionById(id: Int): Consumption? {
        return dao.getConsumptionById(id)
    }

    override fun getConsumption(): Flow<List<Consumption>> {
        return dao.getConsumption()
    }

}