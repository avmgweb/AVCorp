package POM.Corp;

import POM.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Дмитрий on 19.05.2017.
 */
public class LoginPage extends BasePage{
    @FindBy(css = "input[name=\"USER_LOGIN\"]")
    private WebElement login;

    @FindBy(css = "input[name=\"USER_PASSWORD\"]")
    private WebElement password;

    @FindBy (css = "input[name=\"Login\"]")
    private WebElement submit;

    public static String url = "https://corp.avmg.com.ua";

    public LoginPage(String browser) {
        super(browser);
        goToUrl(url);
    }

    public LoginPage(WebDriver driver) {
        super(driver);
    }


    public StreamPage logIn(String login, String password){
        getDriver().findElement(By.cssSelector("input[name=\"USER_LOGIN\"]")).clear();
        getDriver().findElement(By.cssSelector("input[name=\"USER_PASSWORD\"]")).clear();
        type(By.cssSelector("input[name=\"USER_LOGIN\"]"),"write", login);
        type(By.cssSelector("input[name=\"USER_PASSWORD\"]"),"write", password);
        submit.click();
        return new StreamPage(getDriver());
    }




}
