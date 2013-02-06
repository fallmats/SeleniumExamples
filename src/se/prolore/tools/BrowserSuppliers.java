package se.prolore.tools;

import com.google.common.base.Supplier;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: me
 * Date: 2013-02-06
 * Time: 00:14
 */

public class BrowserSuppliers {

    public BrowserSuppliers() {
        suppliers.add(FIREFOX_SUPPLIER);
        suppliers.add(CHROME_SUPPLIER);
    }

    private  Supplier<WebDriver> FIREFOX_SUPPLIER = new Supplier<WebDriver>() {
        private WebDriver firefoxDriver;

        @Override
        public WebDriver get() {
            if (firefoxDriver == null) {
                firefoxDriver = new FirefoxDriver();
            }
            return firefoxDriver;
        }
    };

    private Supplier<WebDriver> CHROME_SUPPLIER = new Supplier<WebDriver>() {
        private WebDriver chromeDriver;

        @Override
        public WebDriver get() {
            if (chromeDriver == null) {
                System.setProperty("webdriver.chrome.driver", "C:\\dev\\chromedriver_win_26.0.1383.0\\chromedriver.exe");
                chromeDriver = new ChromeDriver();
            }
            return chromeDriver;
        }
    };

    private Supplier<WebDriver> INTERNET_EXPLORER_SUPPLIER = new Supplier<WebDriver>() {
        private WebDriver ieDriver;

        @Override
        public WebDriver get() {
            if (ieDriver == null) {
                ieDriver = new InternetExplorerDriver();
            }
            return ieDriver;
        }

    };

    private List<Supplier<WebDriver>> suppliers = new ArrayList<Supplier<WebDriver>>();

//    static {
        /* if (System.getProperty("os.name").startsWith("Windows")) {
            suppliers.add(INTERNET_EXPLORER_SUPPLIER);
        }*/
//        suppliers.add(FIREFOX_SUPPLIER);
//        suppliers.add(CHROME_SUPPLIER);
//    }

    public  List<Supplier<WebDriver>> getWebDrivers() {
        return suppliers;
    }

    public void quit() {
        WebDriver driver;

        for (Supplier<WebDriver> supplier : suppliers ) {
                driver = supplier.get();
                driver.quit();
        }
    }
}
