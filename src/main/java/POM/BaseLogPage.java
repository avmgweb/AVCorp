package POM;

import POM.Corp.CargoTransportationPage;
import POM.Corp.LoginPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.plaf.synth.SynthDesktopIconUI;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * Created by Дмитрий on 19.05.2017.
 */
public class BaseLogPage extends  BasePage{
    public BaseLogPage(String browser) {
        super(browser);
    }

    public BaseLogPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[text()=\"АВ Грузоперевозки\" and @class=\"menu-item-link-text\"]")
    private WebElement cargoTransportationButton;

    @FindBy(id = "bx-panel-logout")
    private WebElement logOutButton;


    public CargoTransportationPage goToCargoTransportationPage() throws InterruptedException {
        (new WebDriverWait(getDriver(), 10))
                .until(ExpectedConditions.visibilityOf(cargoTransportationButton));
        cargoTransportationButton.click();
        (new WebDriverWait(getDriver(), 10))
                .until(ExpectedConditions.visibilityOf(getDriver().findElement(By.id("workarea-content"))));

        return new CargoTransportationPage(getDriver());
    }

    public LoginPage logOut(){
        type(By.xpath("//*[@class=\"user-name\"]"), "click", "");
        type(By.xpath("//a/*[text()=\"Выйти\"]"), "click", "");
        return new LoginPage(getDriver());
    }

    public void setAttribute(WebDriver driver, WebElement element, String attributeName, String value) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, attributeName, value);
    }

    public  void wait(WebDriver driver, int timeout, final By locator){
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(timeout, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);

        wait.until(new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver driver) {
                WebElement element = driver.findElement(locator);
                System.out.println( !element.isDisplayed());
                return element.isDisplayed();
            }
        });
    }



}
