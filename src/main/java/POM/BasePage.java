package POM;

import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Дмитрий on 19.05.2017.
 */
public class BasePage {
    private WebDriver driver;
    public  static String pageUrl;

    public BasePage(String browser)
    {
        driver = Driver.getInstance(browser);
        PageFactory.initElements(driver, this);
    }

    public BasePage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void goToUrl(String url)
    {
        driver.navigate().to(url);
    }

    public String getUrl(){
        return getDriver().getCurrentUrl();
    }

    public WebDriver  getDriver()
    {
        return this.driver;
    }

    public boolean isElementDisplayed(By by){
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        List<WebElement> list = driver.findElements(by);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        if (list.size() == 0) {
            return false;
        } else {
            return list.get(0).isDisplayed();
        }
    }

    public void type (By locator, String value, String text){
        switch (value){
            case "click" : (new WebDriverWait(getDriver(), 10))
                    .until(ExpectedConditions.elementToBeClickable(getDriver().findElement(locator))).click();
                break;
            case "write" : (new WebDriverWait(getDriver(), 10))
                    .until(ExpectedConditions.visibilityOf(getDriver().findElement(locator))).sendKeys(text);
                break;
            case "clear" : (new WebDriverWait(getDriver(), 10))
                    .until(ExpectedConditions.visibilityOf(getDriver().findElement(locator))).clear();
                break;
            case "wait" : (new WebDriverWait(getDriver(), 10))
                    .until(ExpectedConditions.visibilityOf(getDriver().findElement(locator)));
                break;
            default:        System.out.println("ERROR");
                break;
        }

    }

}
