package com.mercijack.stockmateriel;

import com.mercijack.stockmateriel.framework.db.MaterielEntityTest;
import com.mercijack.stockmateriel.presentation.MainViewModelTest;
import com.mercijack.stockmateriel.presentation.addmateriel.AddMaterielViewModelTest;
import com.mercijack.stockmateriel.presentation.home.HomeViewModelTest;
import com.mercijack.stockmateriel.presentation.listmateriels.MaterielSearchFilterTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

// Runs all unit tests.
@RunWith(Suite.class)
// @formatter:off
@Suite.SuiteClasses({
        MaterielEntityTest.class,
        MaterielSearchFilterTest.class,
        MainViewModelTest.class,
        HomeViewModelTest.class,
        AddMaterielViewModelTest.class,
})
// @formatter:on
public class AllUnitTests {
}
