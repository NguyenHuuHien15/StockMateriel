package com.mercijack.stockmateriel

import com.mercijack.stockmateriel.framework.db.MaterielDaoTest
import com.mercijack.stockmateriel.presentation.MainActivityTest
import com.mercijack.stockmateriel.presentation.addmateriel.AddMaterielFragmentTest
import com.mercijack.stockmateriel.presentation.home.HomeFragmentTest
import com.mercijack.stockmateriel.presentation.listmateriels.MaterielsListFragmentTest
import com.mercijack.stockmateriel.presentation.materielinfo.MaterielInfoFragmentTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.Suite

@ExperimentalCoroutinesApi
@RunWith(Suite::class)
// @formatter:off
@Suite.SuiteClasses(
    MaterielDaoTest::class,
    AppContextTest::class,
    HomeFragmentTest::class,
    AddMaterielFragmentTest::class,
    MaterielsListFragmentTest::class,
    MaterielInfoFragmentTest::class,
    MainActivityTest::class,
)
// @formatter:on
class AllInstrumentedTests {
}