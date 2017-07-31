package POM.Corp;

import POM.BaseLogPage;
import POM.Queue;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by Дмитрий on 19.05.2017.
 */
public class CargoTransportationPage extends BaseLogPage {

    @FindBy(css = "a[data-avat=\"cargo-traffic-list-export-excel-link\"]")
    private WebElement exportExcelButton;

    @FindBy(css = "input[data-avat=\"apply-button\"]")
    private WebElement applyFilterButton;

    @FindBy(css = "span[data-avat=\"cancel-button\"]")
    private WebElement resetFilterButton;

    @FindBy(css = "span[data-avat=\"slide-button\"]")
    private WebElement showFullFilterButton;

    public boolean filterMode = false;


    public CargoTransportationPage(String browser) {
        super(browser);
        goToUrl(url);
    }

    public AddingQueuePage addQueue(){
        type(By.cssSelector("a[href=\"/cargo_traffic/element_edit.php?element_id=0&list_section_id=\"]"), "click", "");
        return new AddingQueuePage(getDriver());
    }

    public ReviewOrEditQueuePage openQueue(Queue queue) throws InterruptedException {
        boolean flag = false;
        List<WebElement> queues = getDriver().findElements(By.xpath("//tbody/tr[@class=\"body-row\"]"));
        for (WebElement qq : queues){
            WebElement qTitle = qq.findElement(By.xpath((returnXpath("NAME"))));
            if (qTitle.getText().equals(queue.getTitle())){
                Actions builder = new Actions(getDriver());
                builder.doubleClick(qTitle).build().perform();
                flag = true;
            }
            if (flag) break;
        }
        return new ReviewOrEditQueuePage(getDriver());
    }



    public void delateQueue(Queue queue) throws InterruptedException {
        boolean flag = false;
        Thread.sleep(2000);
        List<WebElement> queues = getDriver().findElements(By.xpath("//tbody/tr[@class=\"body-row\"]"));
        for (WebElement qq : queues){
            WebElement qTitle = qq.findElement(By.xpath((returnXpath("NAME"))));
            WebElement qDelate = qq.findElement(By.xpath((returnXpath("delete-col"))));
            if (qTitle.getText().equals(queue.getTitle())){
                qDelate.click();
                getDriver().findElement(By.cssSelector("button[name=\"delete\"]")).click();
                flag = true;
            }
            if (flag) break;
        }
    }


    public CargoTransportationPage(WebDriver driver) {
        super(driver);
    }

    public static String url = "https://corp.avmg.com.ua/cargo_traffic/";

    public Queue returnQueueByTitle(String title) {
        boolean flag = false;
        Queue queue = new Queue();
        List<WebElement> queues = getDriver().findElements(By.xpath("//tbody/tr[@class=\"body-row\"]"));
        for (WebElement qq : queues){
            WebElement qTitle = qq.findElement(By.xpath((returnXpath("NAME"))));
            System.out.println(qTitle.getText());
            if (qTitle.getText().equals(title)){
                System.out.println("dwaddawawawawawawawawawawawawawawawawawawawawawawawawawawawawawawawawawawawawawawaw");
                queue.setTitle(qTitle.getText());
                queue.setTransaction(qq.findElement(By.xpath(returnXpath("PROPERTY_1178"))).getAttribute("title"));
                queue.setStatus(qq.findElement(By.xpath((returnXpath("PROPERTY_1179")))).getText());
                queue.setSender(qq.findElement(By.xpath((returnXpath("PROPERTY_1181")))).getText());
                queue.setPositions(Integer.parseInt((qq.findElement(By.xpath((returnXpath("PROPERTY_1184")))).getText())));
                queue.setTonnage(Integer.parseInt((qq.findElement(By.xpath((returnXpath("PROPERTY_1183")))).getText())));
                flag = true;
            }
            if (flag) break;
        }
        return queue;
    }

    public String returnXpath(String str){
        int count = 0;
        WebElement header = getDriver().findElement(By.xpath("//tbody/tr[@class=\"header-row\"]"));
        List<WebElement> colums = header.findElements(By.xpath("th"));
        for (WebElement colum : colums){
            count++;
            if (colum.getAttribute("data-avat-field").contains(str)){
                if (str.equals("PROPERTY_1178")){
                    return "td["+ count + "]/div";
                }
               return "td["+ count + "]";
            }
        }
    return  "";
    }

    public boolean checkDisplayedQueue(Queue queue) {
            boolean flag = false;
            List<WebElement> queues = getDriver().findElements(By.xpath("//tbody/tr[@class=\"body-row\"]"));
            for (WebElement qq : queues) {
                WebElement qTitle = qq.findElement(By.xpath((returnXpath("NAME"))));
                if (qTitle.getText().equals(queue.getTitle())) {
                    flag = true;
                }
                if (flag) break;
            }
            return flag;
    }

    public boolean checkDisplayedExportExcelButton() {
        return exportExcelButton.isDisplayed();
    }

    public void applyFilter() {
        type(By.cssSelector("input[data-avat=\"apply-button\"]"),"click","");
    }

    public CargoTransportationPage setFilterByName(Queue queue){
        return setFilter(By.cssSelector("input[data-avat=\"form-input-NAME\"]"),queue.getTitle(), "simple");
    }

    public CargoTransportationPage setFilterByTransaction(Queue queue){
        checkFilterOptions();
        return setFilter(By.cssSelector("select[name=\"PROPERTY_1178[]\"]"),queue.getTransaction(), "select");
    }

    public CargoTransportationPage setFilterByStatus(Queue queue){
        checkFilterOptions();
        return setFilter(By.cssSelector("select[name=\"PROPERTY_1179[]\"]"), queue.getStatus(), "select");
    }

    public CargoTransportationPage setFilterByLicensePlate(Queue queue){
        checkFilterOptions();
        return setFilter(By.cssSelector("input[data-avat=\"form-input-PROPERTY_1180\"]"), queue.getLicensePlate(), "simple");
    }

    public CargoTransportationPage setFilterBySender(Queue queue){
        checkFilterOptions();
        return setFilter(By.cssSelector("select[data-avat=\"form-select-PROPERTY_1181\"]"),queue.getSender(), "select");
    }

    public CargoTransportationPage setFilterByRecipient(Queue queue){
        checkFilterOptions();
        return setFilter(By.cssSelector("select[data-avat=\"form-select-PROPERTY_1182\"]"), queue.getRecipient(), "select");
    }

    public CargoTransportationPage setFilterByTonnage(Integer tonnage){
        checkFilterOptions();
        Double a = tonnage - 0.1;
        Double b = tonnage + 0.1;
        setAttribute(getDriver(), getDriver().findElement(By.cssSelector("input[data-avat=\"form-input-number-PROPERTY_1183_from\"]")), "style", "display: block !important");
        getDriver().findElement(By.cssSelector("input[data-avat=\"form-input-number-PROPERTY_1183_from\"]")).clear();
        getDriver().findElement(By.cssSelector("input[data-avat=\"form-input-number-PROPERTY_1183_from\"]")).sendKeys(a.toString());
        setAttribute(getDriver(), getDriver().findElement(By.cssSelector("input[data-avat=\"form-input-number-PROPERTY_1183_to\"]")), "style", "display: block !important");
        getDriver().findElement(By.cssSelector("input[data-avat=\"form-input-number-PROPERTY_1183_to\"]")).clear();
        getDriver().findElement(By.cssSelector("input[data-avat=\"form-input-number-PROPERTY_1183_to\"]")).sendKeys(b.toString());
        return this;
    }

    public CargoTransportationPage setFilterByPositions(Queue queue){
        checkFilterOptions();
        return setFilter(By.cssSelector("select[data-avat=\"form-select-PROPERTY_1184[]\"]"), queue.getPositions().toString(), "select");
    }

    public CargoTransportationPage setFilterByDecree(Queue queue){
        checkFilterOptions();
         return setFilter(By.cssSelector("select[data-avat=\"form-select-PROPERTY_1185[]\"]"), queue.getDecree(), "select");
    }

    public CargoTransportationPage setFilterByMessage(Queue queue){
        checkFilterOptions();
        return setFilter(By.cssSelector("input[data-avat=\"form-input-PROPERTY_1186\"]"), queue.getMessage(), "simple");
    }

    public CargoTransportationPage setFilter(By by, String value, String status){
        setAttribute(getDriver(), getDriver().findElement(by),"style", "display: block !important");
        switch (status){
            case "simple" :     type(by, "click", "");
                                type(by, "write", value);
                                break;
            case "select" :     type(by, "wait", "");
                                Select sel = new Select(getDriver().findElement(by));
                                sel.selectByVisibleText(value);
                                break;
        }
        return this;
    }

    public CargoTransportationPage setFilterByAllProperties(Queue queue) {
        checkFilterOptions();
        return  setFilter(By.cssSelector("input[data-avat=\"form-input-NAME\"]"),queue.getTitle(), "simple").
                setFilter(By.cssSelector("select[data-avat=\"form-select-PROPERTY_1178[]\"]"),queue.getTransaction(), "select").
                setFilter(By.cssSelector("select[data-avat=\"form-select-PROPERTY_1179[]\"]"), queue.getStatus(), "select").
                setFilter(By.cssSelector("input[data-avat=\"form-input-PROPERTY_1180\"]"), queue.getLicensePlate(), "simple").
                setFilter(By.cssSelector("select[data-avat=\"form-select-PROPERTY_1181\"]"),queue.getSender(), "select").
                setFilter(By.cssSelector("select[data-avat=\"form-select-PROPERTY_1182\"]"), queue.getRecipient(), "select").
                setFilter(By.cssSelector("select[data-avat=\"form-select-PROPERTY_1185[]\"]"), queue.getDecree(), "select").
                setFilter(By.cssSelector("input[data-avat=\"form-input-PROPERTY_1186\"]"), queue.getMessage(), "simple").
                setFilterByTonnage(queue.getTonnage()).
                setFilter(By.cssSelector("select[data-avat=\"form-select-PROPERTY_1184[]\"]"), queue.getPositions().toString(), "select");
    }

    public void checkFilterOptions(){
        if (showFullFilterButton.getAttribute("class").equals("open-button"))
            showFullFilterButton.click();
    }

    public  CargoTransportationPage sortingTonnage(){
        WebElement tonnageSort = getDriver().findElement(By.xpath("//th[contains(@data-avat-field,\"PROPERTY_1183\")]"));
        String classTonnage = tonnageSort.getAttribute("data-avat-field");
        if (classTonnage.equals("PROPERTY_1183")){
            type(By.xpath("//th[contains(@data-avat-field,\"PROPERTY_1183\")]/div"), "click", "");
        }
        return new CargoTransportationPage(getDriver());
    }

    public CargoTransportationPage sortingTonnageDown() throws InterruptedException {
        WebElement tonnageSort = getDriver().findElement(By.xpath("//th[contains(@data-avat-field,\"PROPERTY_1183\")]"));
        String classTonnage = tonnageSort.getAttribute("data-avat-field");
        if (classTonnage.contains("up")){
            type(By.xpath("//th[contains(@data-avat-field,\"PROPERTY_1183\")]/div"), "click", "");
        }
        return new CargoTransportationPage(getDriver());
    }

    public CargoTransportationPage sortingTonnageUp() throws InterruptedException {
        //Thread.sleep(10000);
        WebElement tonnageSort = getDriver().findElement(By.xpath("//th[contains(@data-avat-field,\"PROPERTY_1183\")]"));
        String classTonnage = tonnageSort.getAttribute("data-avat-field");
        if (classTonnage.contains("down")){
            type(By.xpath("//th[contains(@data-avat-field,\"PROPERTY_1183\")]/div"), "click", "");
        }
        return new CargoTransportationPage(getDriver());
    }

    public boolean checkSortingTonnage(String value) throws InterruptedException {
        List <Double> tonnages1 = new ArrayList<Double>();
        List <Double> tonnages2 = new ArrayList<Double>();
        String xpath = "//tbody/tr[@class=\"body-row\"]";
        String xpathFull;
        System.out.println("dede"  + returnXpath("PROPERTY_1183"));
        int count = 0;
        List<WebElement> queues = getDriver().findElements(By.xpath("//tbody/tr[@class=\"body-row\"]"));
        for (WebElement qq : queues){
            xpathFull = returnXpath("PROPERTY_1183");
            System.out.println(xpathFull);
            WebElement q = qq.findElement(By.xpath(xpathFull));
            tonnages1.add(Double.parseDouble(q.getText()));
        }
        for (Double a : tonnages1){
            tonnages2.add(a);
        }
        if (value.equals("up"))
            Collections.sort(tonnages1);
        else
            Collections.sort(tonnages1, Collections.reverseOrder());
        System.out.println(tonnages1);
        return tonnages1.equals(tonnages2);
    }


    public  CargoTransportationPage sortingTitle(){
        WebElement titleSort = getDriver().findElement(By.xpath("//th[contains(@data-avat-field,\"NAME\")]"));
        String classTitle = titleSort.getAttribute("data-avat-field");
        System.out.println("11111111111111111111" + classTitle);
        if (classTitle.equals("NAME")){
            type(By.xpath("//th[contains(@data-avat-field,\"NAME\")]/div"), "click", "");
        }
        return new CargoTransportationPage(getDriver());
    }


    public CargoTransportationPage sortingTitleDown() throws InterruptedException {
        WebElement titleSort = getDriver().findElement(By.xpath("//th[contains(@data-avat-field,\"NAME\")]"));
        String classTitle = titleSort.getAttribute("data-avat-field");
        if (classTitle.contains("up")){
            {
                type(By.xpath("//th[contains(@data-avat-field,\"NAME\")]/div"), "click", "");
            }
        }
        return new CargoTransportationPage(getDriver());
    }

    public CargoTransportationPage sortingTitleUp() throws InterruptedException {

        WebElement titleSort = getDriver().findElement(By.xpath("//th[contains(@data-avat-field,\"NAME\")]"));
        String classTitle = titleSort.getAttribute("data-avat-field");
        if (classTitle.contains("down")){
            {
                type(By.xpath("//th[contains(@data-avat-field,\"NAME\")]/div"), "click", "");
            }

        }
        return new CargoTransportationPage(getDriver());
    }

    public boolean checkSortingTitle(String value) throws InterruptedException {
        List <String> title1 = new ArrayList<String>();
        List <String> title2 = new ArrayList<String>();
        String xpath = "//tbody/tr[@class=\"body-row\"]";
        String xpathFull;
        int count = 0;
        List<WebElement> queues = getDriver().findElements(By.xpath("//tbody/tr[@class=\"body-row\"]"));
        for (WebElement qq : queues){
            xpathFull = returnXpath("NAME");
            System.out.println(xpathFull);
            WebElement q = qq.findElement(By.xpath(xpathFull));
            title1.add((q.getText()));
        }
        for (String a : title1){
            title2.add(a);
        }
        if (value.equals("up"))
            Collections.sort(title2);
        if (value.equals("down"))
            Collections.sort(title2, Collections.reverseOrder());
        System.out.println(title1);
        System.out.println(title2);
        return title1.equals(title2);
    }

    public void waitSorting(String value, String xpath) {
        String fullXpath = "th[data-avat-field=\"" + xpath + value + "\"]";
        System.out.println(fullXpath);
        switch (value) {
            case "":
                wait(getDriver(), 500, By.cssSelector(fullXpath));
                break;
            case "_sort_up":
                wait(getDriver(), 500, By.cssSelector(fullXpath));
                break;
            case "_sort_down":
                wait(getDriver(), 500, By.cssSelector(fullXpath));
                break;
            default:
                break;
        }
    }
    //10, 20, 50, 100, 200
    public CargoTransportationPage setTheNumberOfOtputList(String value){
        type(By.xpath("//*[@class=\"edit-form-button\"]"), "click", "");
        type(By.xpath("//td[@class=\"item default\"]"), "click","");
        type(By.cssSelector("select[name=\"view_page_size\"]"), "wait", "");
        Select sel = new Select(getDriver().findElement(By.cssSelector("select[name=\"view_page_size\"]")));
        sel.selectByValue(value);
        type(By.id("undefined"), "click", "");
        return new CargoTransportationPage(getDriver());
    }

    public void resetFilter() {
        resetFilterButton.click();
    }

    public boolean checkDisplayedAddButton() {
        return isElementDisplayed(By.cssSelector("a[href=\"/cargo_traffic/element_edit.php?element_id=0&list_section_id=\"]"));
    }
}
