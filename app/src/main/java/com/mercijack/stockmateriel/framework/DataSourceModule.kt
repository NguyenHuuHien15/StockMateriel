package com.mercijack.stockmateriel.framework

import com.mercijack.stockmateriel.data.IMaterielDataSource
import com.mercijack.stockmateriel.framework.db.MaterielDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataSourceModule {

    @Provides
    @Singleton
    fun providesLocalDataSource(materielDao: MaterielDao): IMaterielDataSource {
        return RoomMaterielDataSource(materielDao)
    }

}