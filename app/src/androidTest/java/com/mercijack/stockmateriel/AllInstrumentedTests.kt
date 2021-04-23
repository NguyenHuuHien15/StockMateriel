package com.mercijack.stockmateriel

import com.mercijack.stockmateriel.framework.db.MaterielDaoTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.Suite

@ExperimentalCoroutinesApi
@RunWith(Suite::class)
// @formatter:off
@Suite.SuiteClasses(
    MaterielDaoTest::class,
    AppContextTest::class,
)
// @formatter:on
class AllInstrumentedTests {
}