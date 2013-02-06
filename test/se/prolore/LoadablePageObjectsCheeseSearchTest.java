package se.prolore;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import se.prolore.pageobjects.*;
import se.prolore.tools.ScreenShots;

import java.util.List;

import static junit.framework.Assert.fail;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.internal.matchers.StringContains.containsString;

/**
 * Created with IntelliJ IDEA.
 * User: me
 * Date: 2013-02-04
 * Time: 22:57
  */
public class LoadablePageObjectsCheeseSearchTest {

    private WebDriver driver;
    private ScreenShots screen;

    @Before
    public void createDriver() {
        driver = new FirefoxDriver();
        screen = new ScreenShots(driver);
    }

    @After
    public void quitDriver() {
        driver.quit();

    }

    @Test
    public void searchUsingGoogle() {
        SearchPageLoadable page = new GoogleSearchLoadable(driver);
        page.load();
        ResultsPage results = page.searchFor("cheddar");

        System.out.println("results.getCount() = " + results.getCount()); // This is purely for making it visible
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
    public void searchAndExplicitWaitForResults() {

        SearchPageLoadable page = new GoogleSearchLoadable(driver);
        page.load();
        ResultsPage results = page.searchFor("cheddar");

        // Set the timeout
        WebDriverWait wait = new WebDriverWait(driver,20);

        // wait for our expected condition after the search for cheddar
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.gsq_a > table > tbody > tr > td")));
        // now this css selector part should probably be refactored to the PageObjects, but we keep it here for ease of demo

        // Now lets do an assertion and check that our search is visible in the ajax dropdown
        assertThat(driver.findElement(By.cssSelector("div.gsq_a > table > tbody > tr > td")).getText().toLowerCase(),containsString("cheddar"));

        // For fun lets take a screenshot of how things looked (here we are using a small tools class 'screen' to placce the picture on the file system)
        screen.takeScreenShot(this.toString());

    }

    @Test
    public void searchUsingGoogleInject() {
        // Using injection to perform the test
        SearchPageLoadable page = new GoogleSearchLoadable(driver);
        page.load();
        searchAndCheckDomainResults(page);
    }

    private void searchAndCheckDomainResults(SearchPageLoadable page) {
        ResultsPage resultsPage = page.searchFor("cheddar");
        assertThat(resultsPage.getCount(), is(greaterThan(0)));
    }


}
