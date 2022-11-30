package com.example.dietapps.di

import android.app.Application
import androidx.room.Room
import com.example.dietapps.data.AppDatabase
import com.example.dietapps.data.ConsumptionRepository
import com.example.dietapps.data.ConsumptionRepositoryImplementation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideAppDatabase(app:Application): AppDatabase{
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "app_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideConsumptionRepository(db:AppDatabase):ConsumptionRepository{
        return ConsumptionRepositoryImplementation(db.dao)
    }

}