package com.mercijack.stockmateriel

import com.mercijack.stockmateriel.framework.db.MaterielDaoTest
import com.mercijack.stockmateriel.presentation.home.HomeFragmentTest
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
    AppTest::class,
)
// @formatter:on
class AllInstrumentedTests {
}