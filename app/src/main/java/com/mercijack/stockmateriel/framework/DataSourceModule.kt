package com.mercijack.stockmateriel.framework

import com.mercijack.stockmateriel.data.IMaterielDataSource
import com.mercijack.stockmateriel.data.MaterielRepository
import com.mercijack.stockmateriel.framework.db.MaterielDao
import com.mercijack.stockmateriel.interactors.AddMateriel
import com.mercijack.stockmateriel.interactors.GetAllMateriels
import com.mercijack.stockmateriel.interactors.RemoveMateriel
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

    @Provides
    @Singleton
    fun providesInteractors(repository: MaterielRepository): Interactors {
        return Interactors(AddMateriel(repository), RemoveMateriel(repository), GetAllMateriels(repository))
    }

}