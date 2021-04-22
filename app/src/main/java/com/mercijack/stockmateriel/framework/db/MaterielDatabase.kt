package com.mercijack.stockmateriel.framework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MaterielEntity::class], version = 1, exportSchema = false)
abstract class MaterielDatabase : RoomDatabase() {
    abstract val materielDao: MaterielDao
}

private lateinit var INSTANCE: MaterielDatabase

fun getDatabase(context: Context): MaterielDatabase {
    synchronized(MaterielDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext, MaterielDatabase::class.java, "Stock").build()
        }
    }
    return INSTANCE
}
