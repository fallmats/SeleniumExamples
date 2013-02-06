package se.prolore;

import com.google.common.base.Supplier;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import se.prolore.pageobjects.BingSearch;
import se.prolore.pageobjects.GoogleSearch;
import se.prolore.pageobjects.ResultsPage;
import se.prolore.pageobjects.SearchPage;
import se.prolore.tools.BrowserSuppliers;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: me
 * Date: 2013-02-06
 * Time: 00:05
 */
public class DifferentDriversTest {
    private WebDriver driver;

    private BrowserSuppliers browserSuppliers;

    @Before
    public void createDriver() {
        browserSuppliers = new BrowserSuppliers();

    }

    @After
    public void quitDriver() {
        browserSuppliers.quit();
    }

    @Test
    public void runBingSearchOnSeveralBrowsersTest() {
        for (Supplier<WebDriver> supplier : browserSuppliers.getWebDrivers()) {
            driver = supplier.get();

            SearchPage page = new BingSearch(driver);
            searchAndCheckDomainResults(page);
        }
    }

    @Test
    public void runGoogleSearchOnSeveralBrowsersTest() {
        for (Supplier<WebDriver> supplier : browserSuppliers.getWebDrivers()) {
            driver = supplier.get();

            SearchPage page = new GoogleSearch(driver);
            searchAndCheckDomainResults(page);
        }
    }

    private void searchAndCheckDomainResults(SearchPage page) {

        ResultsPage resultsPage = page.searchFor("cheddar");
        assertThat(resultsPage.getCount(), is(greaterThan(0)));
    }
}
