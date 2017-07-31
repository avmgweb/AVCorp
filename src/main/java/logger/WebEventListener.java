package logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

/**
 * Created by Дмитрий on 19.05.2017.
 */
public class WebEventListener implements WebDriverEventListener {
    @Override
    public void afterClickOn(WebElement arg0, WebDriver arg1) {

        System.out.println("After click "+arg0.toString());

    }

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {

    }

    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {

    }

    @Override
    public void afterFindBy(By arg0, WebElement arg1, WebDriver arg2) {

        System.out.println("After FindBy "+arg0.toString());
    }

    @Override
    public void afterNavigateBack(WebDriver arg0) {

        System.out.println("After navigating back "+arg0.toString());

    }

    @Override
    public void afterNavigateForward(WebDriver arg0) {

        System.out.println("After navigating forword "+arg0.toString());

    }

    @Override
    public void beforeNavigateRefresh(WebDriver driver) {

    }

    @Override
    public void afterNavigateRefresh(WebDriver driver) {

    }

    @Override
    public void afterNavigateTo(String arg0, WebDriver arg1) {

        System.out.println("After navigating "+arg0.toString());

        System.out.println("After navigating "+arg1.toString());

    }

    @Override
    public void afterScript(String arg0, WebDriver arg1) {

    }


    @Override
    public void beforeClickOn(WebElement arg0, WebDriver arg1) {

        System.out.println("before click "+arg0.toString());

    }

    @Override
    public void beforeFindBy(By arg0, WebElement arg1, WebDriver arg2) {

        System.out.println("before FindBY "+arg0.toString());

    }

    @Override
    public void beforeNavigateBack(WebDriver arg0) {

        System.out.println("Before navigating back "+arg0.toString());
    }

    @Override
    public void beforeNavigateForward(WebDriver arg0) {
        System.out.println("Before navigating Forword "+arg0.toString());

    }

    @Override
    public void beforeAlertAccept(WebDriver driver) {

    }

    @Override
    public void afterAlertAccept(WebDriver driver) {

    }

    @Override
    public void afterAlertDismiss(WebDriver driver) {

    }

    @Override
    public void beforeAlertDismiss(WebDriver driver) {

    }

    @Override
    public void beforeNavigateTo(String arg0, WebDriver arg1) {

        System.out.println("Before navigating "+arg0.toString());
        System.out.println("Before navigating "+arg1.toString());

    }

    @Override
    public void beforeScript(String arg0, WebDriver arg1) {

    }

    @Override
    public void onException(Throwable arg0, WebDriver arg1) {

        System.out.println("Testcase done"+arg0.toString());
        System.out.println("Testcase done"+arg1.toString());
    }


}
