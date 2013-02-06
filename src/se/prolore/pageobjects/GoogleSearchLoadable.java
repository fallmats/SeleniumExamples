package se.prolore.pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


import static junit.framework.Assert.assertTrue;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class GoogleSearchLoadable extends LoadableComponent<GoogleSearchLoadable> implements SearchPageLoadable  {

  private final WebDriver driver;
  private final Wait<WebDriver> wait;

  public GoogleSearchLoadable(WebDriver driver) {
    this.driver = driver;
    wait = new WebDriverWait(driver, 30);
  }

  public ResultsPage searchFor(String term) {

    WebElement searchBox = driver.findElement(By.name("q"));
    searchBox.sendKeys(term);

    wait.until(visibilityOfElementLocated(By.id("res")));

    return new GoogleResults(driver);
  }

     @Override
    public void load() {
        // Implementation of the extended class function load.

        // We use "complete=1" to force Google Instant to be on
        driver.get("http://www.google.com?complete=1");
    }

    @Override
    public void isLoaded() throws Error {
        // Implementation of the extended class function isLoaded.
        String url = driver.getCurrentUrl();
        assertTrue("Not on the issue entry page: " + url, url.endsWith("/?complete=1"));
    }
}
