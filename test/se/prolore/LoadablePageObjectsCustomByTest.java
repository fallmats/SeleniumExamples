package se.prolore;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.internal.FindsByXPath;
import se.prolore.pageobjects.*;
import se.prolore.tools.ScreenShots;

import java.util.List;

import static org.junit.Assert.assertThat;
import static org.junit.internal.matchers.StringContains.containsString;

/**
 * Created with IntelliJ IDEA.
 * User: me
 * Date: 2013-02-05
 * Time: 23:59
  */
public class LoadablePageObjectsCustomByTest {
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
    public void customLocatorDemo() {
        // In this test we will use a custom By operator to find the object we are looking for
        SearchPageLoadable page = new GoogleSearchLoadable(driver);
        page.load();
        ResultsPage results = page.searchFor("cheddar");

        // Lets use our own custom By (declared below) to find
        WebElement button = driver.findElement(byAriaLabel("Sök på Google"));
        assertThat(button.getAttribute("aria-label"),containsString("Sök på Google"));
        System.out.println("Value: " + button.getAttribute("aria-label"));
    }

    private By byAriaLabel(final String s) {
        // Our custom By locator which can be used instead of any of the 8 supplied locators
        // This can be useful if the page (application) to lower maintenance if an advanced locator needs to be used often
        return new By() {
            @Override
            public List<WebElement> findElements(final SearchContext context)
            {
                final String xpath =
                        "//*[@aria-label=\"" + s + "\"]";
                return ((FindsByXPath) context).findElementsByXPath(xpath);
            }

            @Override
            public WebElement findElement(final SearchContext context)
            {
                String xpath =
                        "//*[@aria-label=\"" + s + "\"]";
                return ((FindsByXPath) context).findElementByXPath(xpath);
            }

            @Override
            public String toString()
            {
                return "ByLabel: " + s;
            }
        };
    }


}
