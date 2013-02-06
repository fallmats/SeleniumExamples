package se.prolore;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import se.prolore.pageobjects.BingSearch;
import se.prolore.pageobjects.GoogleSearch;
import se.prolore.pageobjects.ResultsPage;
import se.prolore.pageobjects.SearchPage;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: me
 * Date: 2013-02-04
 * Time: 22:57
 */
public class PageObjectsCheeseSearchTest {

    private WebDriver driver;

    @Before
    public void createDriver() {
        driver = new FirefoxDriver();

    }

    @After
    public void quitDriver() {
        driver.quit();
    }

    @Test
    public void searchUsingGoogle() {
        SearchPage page = new GoogleSearch(driver);
        ResultsPage results = page.searchFor("cheddar");

        assertThat(results.getCount(), is(greaterThan(0)));
    }

    @Test
    public void searchUsingBing() {
        SearchPage page = new BingSearch(driver);
        ResultsPage results = page.searchFor("cheddar");

        System.out.println("results.getCount() = " + results.getCount());
        assertThat(results.getCount(), is(greaterThan(0)));

    }

    @Test
    public void searchUsingGoogleInject() {
        SearchPage page = new GoogleSearch(driver);
        searchAndCheckDomainResults(page);
    }

    @Test
    public void searchUsingBingInject() {
        SearchPage page = new BingSearch(driver);
        searchAndCheckDomainResults(page);
    }

    private void searchAndCheckDomainResults(SearchPage page) {

        ResultsPage resultsPage = page.searchFor("cheddar");

        assertThat(resultsPage.getCount(), is(greaterThan(0)));
    }
}
