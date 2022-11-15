package com.example.dietapps.di

import android.app.Application
import androidx.room.Room
import com.example.dietapps.data.ConditionDB
import com.example.dietapps.data.ConditionRepository
import com.example.dietapps.data.ConditionRepositoryImp
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
    fun provideConditionDB(app: Application): ConditionDB{
        return Room.databaseBuilder(
            app,
            ConditionDB::class.java,
            "condition_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideConditionRepository(db: ConditionDB): ConditionRepository{
        return ConditionRepositoryImp(db.dao)
    }
}