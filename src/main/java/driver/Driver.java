package driver;

import logger.WebEventListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by Дмитрий on 19.05.2017.
 */
public class Driver {

    private static WebDriver driver;

    public static WebDriver getInstance(String browser) {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        System.setProperty("phantomjs.binary.path", "src/main/resources/phantomjs.exe");
        System.setProperty("webdriver.ie.driver", "src/main/resources/IEDriverServer.exe");
        DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
        dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);

        System.setProperty("webdriver.opera.driver", "src/main/resources/operadriver.exe");
        System.setProperty("webdriver.edge.driver",  "src/main/resources/MicrosoftWebDriver.exe");
        if (driver==null)
        {
            switch (browser)
            {
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "phantomjs":
                    driver = new PhantomJSDriver();
                    break;
                case "ie":
                    driver = new InternetExplorerDriver(dc);
                    break;
                case "opera":
                    driver = new OperaDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                default: driver = new ChromeDriver();
            }
            driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
            EventFiringWebDriver eventDriver = new EventFiringWebDriver(driver);
            eventDriver.register(new WebEventListener());
            return eventDriver.register(new WebEventListener());
        }
        driver.manage().window().maximize();
        return driver;
    }

    public static void tearDown(){
        driver.quit();
    }

    public static void maximize(){
        driver.manage().window().maximize();
    }

    public static void nullDriver(){
        driver = null;
    }
}