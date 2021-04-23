package com.mercijack.stockmateriel.framework.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mercijack.stockmateriel.materielEntity1
import com.mercijack.stockmateriel.materielEntity2
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class MaterielDaoTest {

    @get:Rule
    var rule = RuleChain.outerRule(HiltAndroidRule(this)).around(InstantTaskExecutorRule())

    private lateinit var materielDao: MaterielDao
    private lateinit var db: MaterielDatabase

    @Before
    fun createDb() {
        db = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), MaterielDatabase::class.java).build()
        materielDao = db.materielDao
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun addMateriel() = runBlockingTest {
        materielDao.addMateriel(materielEntity1)

        val materielFromDb = materielDao.getMaterielByCode(materielEntity1.code)

        assertEquals(materielEntity1, materielFromDb)
    }

    @Test
    fun removeMateriel() = runBlockingTest {
        materielDao.addMateriel(materielEntity1)
        materielDao.addMateriel(materielEntity2)

        materielDao.removeMateriel(materielEntity1)

        val allMaterielsFromDb = materielDao.getMateriels()

        assertEquals(listOf(materielEntity2), allMaterielsFromDb)
    }

    @Test
    fun getMateriels() = runBlockingTest {
        materielDao.addMateriel(materielEntity1)
        materielDao.addMateriel(materielEntity2)

        val allMaterielsFromDb = materielDao.getMateriels()

        assertEquals(listOf(materielEntity1, materielEntity2), allMaterielsFromDb)
    }

    @Test
    fun getMaterielByCode() = runBlockingTest {
        materielDao.addMateriel(materielEntity1)
        materielDao.addMateriel(materielEntity2)

        val materielFromDb = materielDao.getMaterielByCode(materielEntity2.code)

        assertEquals(materielEntity2, materielFromDb)
    }

}