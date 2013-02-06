package se.prolore;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: me
 * Date: 2013-02-05
 * Time: 23:29
  */
public class FluentWaitDemo {

    private static WebDriver driver;
    WebElement countdown;

    @BeforeClass
    public static void setup(){
        driver = new FirefoxDriver();
        driver.get("http://www.frokenur.nu/");
    }


    @Before
    public void setupTest(){

        driver.navigate().refresh();

        countdown = driver.findElement(By.id("seconds"));

        new WebDriverWait(driver,10).
                until(ExpectedConditions.visibilityOf(countdown));
    }

    @Test
    public void waitForWebElementFluently(){

        new FluentWait<WebElement>(countdown).
                withTimeout(10, TimeUnit.SECONDS).
                pollingEvery(100,TimeUnit.MILLISECONDS).
                until(new Function<WebElement, Boolean>() {
                    @Override
                    public Boolean apply(WebElement element) {
                        return element.getText().endsWith("4");
                    }
                }
                );
    }

    @Test
    public void waitForWebElementFluentlyPredicate(){

        new FluentWait<WebElement>(countdown).
                withTimeout(10, TimeUnit.SECONDS).
                pollingEvery(100,TimeUnit.MILLISECONDS).
                until(new Predicate<WebElement>() {
                    @Override
                    public boolean apply(WebElement element) {
                        return element.getText().endsWith("4");
                    }
                }
                );
    }

    @AfterClass
    public static void tearDown(){
        driver.quit();
    }
}
