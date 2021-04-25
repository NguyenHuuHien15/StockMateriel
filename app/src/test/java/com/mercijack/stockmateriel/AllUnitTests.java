package com.mercijack.stockmateriel;

import com.mercijack.stockmateriel.framework.db.MaterielEntityTest;
import com.mercijack.stockmateriel.presentation.MainViewModelTest;
import com.mercijack.stockmateriel.presentation.addmateriel.AddMaterielViewModelUnitTest;
import com.mercijack.stockmateriel.presentation.home.HomeViewModelUnitTest;
import com.mercijack.stockmateriel.presentation.listmateriels.MaterielDiffCallbackTest;
import com.mercijack.stockmateriel.presentation.listmateriels.MaterielSearchFilterTest;
import com.mercijack.stockmateriel.presentation.listmateriels.MaterielsListViewModelTest;
import com.mercijack.stockmateriel.presentation.materielinfo.MaterielInfoViewModelUnitTest;

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
        HomeViewModelUnitTest.class,
        AddMaterielViewModelUnitTest.class,
        MaterielsListViewModelTest.class,
        MaterielInfoViewModelUnitTest.class,
})
// @formatter:on
public class AllUnitTests {
}
