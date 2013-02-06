package se.prolore;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import se.prolore.pageobjects.*;
import se.prolore.tools.ScreenShots;

import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: me
 * Date: 2013-02-06
 * Time: 00:01
 */
public class LoadablePageObjectsCustomExpectedConditions {
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
    public void customExpectedConditionIFeelLuckySearch() {
        // In this test we will show how to use a custom Expected condition
        SearchPageLoadable page = new GoogleSearchLoadable(driver);
        page.load();
        Wait<WebDriver> wait = new WebDriverWait(driver, 30);

        WebElement feelingLucky = driver.findElement(By.name("btnI"));
        feelingLucky.click();

        List<WebElement> allImages = wait.until(
                presenceOfManyElementsLocated(By.className("doodle-image")));
        System.out.println("allImages.size() = " + allImages.size());
    }

    private ExpectedCondition<List<WebElement>> presenceOfManyElementsLocated(final By locator) {
        return new ExpectedCondition<List<WebElement>>() {
            public List<WebElement> apply(WebDriver driver) {
                List<WebElement> elements = driver.findElements(locator);
                if (elements.size() > 0) {
                    return elements;
                }

                return null;
            }
        };
    }

}
