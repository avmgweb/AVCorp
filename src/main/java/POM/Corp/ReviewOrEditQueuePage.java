package POM.Corp;

import POM.BaseLogPage;
import POM.Queue;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by Дмитрий on 24.05.2017.
 */
public class ReviewOrEditQueuePage  extends BaseLogPage {
    public ReviewOrEditQueuePage(String browser) {
        super(browser);
    }

    public ReviewOrEditQueuePage(WebDriver driver) {
        super(driver);
    }

    public void apply() {
        type(By.cssSelector("input[value=\"Применить\"]"),"click","");
        (new WebDriverWait(getDriver(), 10))
                .until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.xpath("//*[text()=\"История изменений\"]"))));
    }

    public void showHistoryWindow() {
        type(By.xpath("//*[text()=\"История изменений\"]"), "click", "");
    }

    public void showMainWindow() {
        getDriver().findElement(By.xpath("//*[text()=\"Заявка\"]")).click();
    }

    public String getLastChangeOfStatus() throws InterruptedException {
        String xpath = ".//*[@id='workarea-content']//tbody/tr[2]/td[" + getNumberInHistory("Статус") + "]";
        (new WebDriverWait(getDriver(), 10))
                .until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath(xpath))));
        return getDriver().findElement(By.xpath(xpath)).getText();
    }

    public String getLastChangeOfRecipient() {
        String xpath = ".//*[@id='workarea-content']//tbody/tr[2]/td[" + getNumberInHistory("Склад получатель") + "]";
        (new WebDriverWait(getDriver(), 10))
                .until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath(xpath))));
        return getDriver().findElement(By.xpath(xpath)).getText();
    }

    public String getNumberInHistory(String str){
        Integer count = 0;
        WebElement header = getDriver().findElement(By.xpath(".//*[@id='workarea-content']/div/form/table/tbody/tr[1]"));
        List<WebElement> colums = header.findElements(By.xpath("th"));
        for (WebElement colum : colums){
            count++;
            if (colum.getText().equals(str)){
                return count.toString();
            }
        }
        return  "";
    }


    public ReviewOrEditQueuePage fillRecipientOfQueue(Queue queue) throws InterruptedException {
        setSelectField("select[data-avat=\"form-select-PROPERTY_1182\"]",queue.getRecipient());
        return this;
    }

    public ReviewOrEditQueuePage fillStatusOfQueue(Queue queue) throws InterruptedException {
        setSelectField("select[data-avat=\"form-select-PROPERTY_1179\"]",queue.getStatus());
        return this;
    }


    public boolean checkRecipient(String sender) throws InterruptedException {
        boolean flag = true;
        Thread.sleep(3000);
        setAttribute(getDriver(), getDriver().findElement(By.cssSelector("select[data-avat=\"form-select-PROPERTY_1182\"]")), "style", "display: block !important");
        (new WebDriverWait(getDriver(), 10))
                .until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.cssSelector("select[data-avat=\"form-select-PROPERTY_1182\"]"))));
        Select sel = new Select(getDriver().findElement(By.cssSelector("select[data-avat=\"form-select-PROPERTY_1182\"]")));
        List<WebElement> options = sel.getOptions();
        for (WebElement opt  : options){
            System.out.println(opt.getText());
            if (opt.getText().equals(sender)){
                flag = false;
            }
        }
        return flag;
    }


    public boolean checkButtonComeBackAvailability() {
        return isElementDisplayed(By.cssSelector("a[title=\"Вернуться к списку\"]"));
    }

    public CargoTransportationPage PressButtonComeBack(){
        type(By.cssSelector("a[title=\"Вернуться к списку\"]"), "click", "");
        return new CargoTransportationPage(getDriver());
    }

    public void setSelectField(String css, String value) throws InterruptedException {
        setAttribute(getDriver(), getDriver().findElement(By.cssSelector(css)), "style", "display: block !important");
        type(By.cssSelector(css), "wait", "");
        Select sel = new Select(getDriver().findElement(By.cssSelector(css)));
        sel.selectByVisibleText(value);
    }

    public CargoTransportationPage saveQueue() {
        getDriver().findElement(By.cssSelector("input[name=\"save\"]")).click();
        return new CargoTransportationPage(getDriver());
    }

    public CargoTransportationPage goToListOfQueue() {
        getDriver().findElement(By.className("av-form-button-corp-alt")).click();
        return new CargoTransportationPage(getDriver());
    }
}

