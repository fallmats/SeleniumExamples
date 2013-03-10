package se.prolore;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created with IntelliJ IDEA.
 * User: me
 * Date: 2013-02-05
 * Time: 23:29
  */
public class AssertTime {

    private static WebDriver driver;
    WebElement hour;
    WebElement minute;

    @BeforeClass
    public static void setup() {
        driver = new FirefoxDriver();
        driver.get("http://www.frokenur.nu/");
    }

    @Test
    public void timeMoreThan() {
    	hour = driver.findElement(By.id("hours"));
        minute = driver.findElement(By.id("minutes"));
        
        Integer hourValue = Integer.parseInt(hour.getText());
        Integer minuteValue = Integer.parseInt(minute.getText());
        
        assertThat("Hour is: " + hourValue, hourValue, greaterThan(17));
        assertThat("Minute is: " + minuteValue, minuteValue, greaterThan(1));
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
