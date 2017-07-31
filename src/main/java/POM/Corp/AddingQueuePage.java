package POM.Corp;

import POM.BaseLogPage;
import POM.Queue;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

/**
 * Created by Дмитрий on 22.05.2017.
 */
public class AddingQueuePage extends BaseLogPage {
    @FindBy(css = "input[title=\"Название\"]")
    private WebElement title;

    @FindBy(css = "input[title=\"Тоннаж\"]")
    private WebElement tonnage;

    @FindBy(css = "input[title=\"Номер автомобиля\"]")
    private WebElement licensePlate;

    public AddingQueuePage(String browser) {
        super(browser);
    }

    public AddingQueuePage(WebDriver driver) {
        super(driver);
    }


    public CargoTransportationPage saveQueue(){
        getDriver().findElement(By.cssSelector("input[name=\"save\"]")).click();
        if (!isAlertPresent())
            return new CargoTransportationPage(getDriver());
        else return null;
    }

    public CargoTransportationPage  cancelQueue(){
        type(By.cssSelector("a[class=\"av-form-button-corp-alt4\"]"),"click","");
        return new CargoTransportationPage(getDriver());
    }

    public AddingQueuePage fillQueue(Queue queue) throws InterruptedException {
        type(By.cssSelector("input[title=\"Название\"]"), "write", queue.getTitle());
        type(By.cssSelector("input[title=\"Тоннаж\"]"), "write", queue.getTonnage().toString());
        type(By.cssSelector("input[title=\"Номер автомобиля\"]"), "write", queue.getLicensePlate());
        type(By.id("bx-html-editor-iframe-cnt-id_PROPERTY_1186__0:1186_"), "wait", "");
        WebElement message = getDriver().findElement(By.id("bx-html-editor-iframe-cnt-id_PROPERTY_1186__0:1186_"));
        Actions builder = new Actions(getDriver());
        builder.sendKeys(message, queue.getMessage()).build().perform();
        setSelectField("select[data-avat=\"form-select-PROPERTY_1178\"]", queue.getTransaction());
        setSelectField("select[data-avat=\"form-select-PROPERTY_1181\"]", queue.getSender());
        setSelectField("select[data-avat=\"form-select-PROPERTY_1184\"]", queue.getPositions().toString());
        setSelectField("select[data-avat=\"form-select-PROPERTY_1185\"]", queue.getDecree());
        return this;
    }

    public void setSelectField(String css, String value){
        setAttribute(getDriver(), getDriver().findElement(By.cssSelector(css)), "style", "display: block !important");
        Select sel = new Select(getDriver().findElement(By.cssSelector(css)));
        sel.selectByVisibleText(value);
    }


    public void closeAllert(){
        getDriver().findElement(By.className("close-form-button")).click();
    }

    public boolean isAlertPresent() {
        try {
            getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            getDriver().findElement(By.className("av-alert-popup"));
            getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}