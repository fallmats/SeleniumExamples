package se.prolore.pageobjects;

//import se.prolore.domain.SearchResult;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GoogleResults implements ResultsPage {

    private final WebDriver driver;

    public GoogleResults(WebDriver driver) {
        this.driver = driver;
    }

    public int getCount() {
        WebElement resultsList = driver.findElement(By.id("res"));
        List<WebElement> allResults = resultsList.findElements(By.tagName("li"));
        return allResults.size();
    }

    public int getQueryDropdownCount() {
        WebElement resultsList = driver.findElement(By.id(""));
        List<WebElement> allQuerys = resultsList.findElements(By.tagName(""));
        return allQuerys.size();
    }


}
