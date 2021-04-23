package com.mercijack.stockmateriel;

import com.mercijack.stockmateriel.framework.db.MaterielEntityTest;
import com.mercijack.stockmateriel.presentation.listmateriels.MaterielSearchFilterTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

// Runs all unit tests.
@RunWith(Suite.class)
// @formatter:off
@Suite.SuiteClasses({
        MaterielEntityTest.class,
        MaterielSearchFilterTest.class,
})
// @formatter:on
public class AllUnitTests {
}
