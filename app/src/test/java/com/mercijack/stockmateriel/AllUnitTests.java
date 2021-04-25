package com.mercijack.stockmateriel;

import com.mercijack.stockmateriel.framework.db.MaterielEntityTest;
import com.mercijack.stockmateriel.presentation.MainViewModelTest;
import com.mercijack.stockmateriel.presentation.addmateriel.AddMaterielViewModelTest;
import com.mercijack.stockmateriel.presentation.home.HomeViewModelTest;
import com.mercijack.stockmateriel.presentation.listmateriels.MaterielDiffCallbackTest;
import com.mercijack.stockmateriel.presentation.listmateriels.MaterielSearchFilterTest;
import com.mercijack.stockmateriel.presentation.listmateriels.MaterielsListViewModelTest;
import com.mercijack.stockmateriel.presentation.materielinfo.MaterielInfoViewModelTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

// Runs all unit tests.
@RunWith(Suite.class)
// @formatter:off
@Suite.SuiteClasses({
        MaterielEntityTest.class,
        MaterielSearchFilterTest.class,
        MaterielDiffCallbackTest.class,
        MainViewModelTest.class,
        HomeViewModelTest.class,
        AddMaterielViewModelTest.class,
        MaterielsListViewModelTest.class,
        MaterielInfoViewModelTest.class,
})
// @formatter:on
public class AllUnitTests {
}
