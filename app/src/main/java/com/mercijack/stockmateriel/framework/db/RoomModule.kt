package com.mercijack.stockmateriel.framework.db

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RoomModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): MaterielDatabase {
        return getDatabase(context)
    }

    @Provides
    @Singleton
    fun providesDao(database: MaterielDatabase): MaterielDao {
        return database.materielDao
    }
}