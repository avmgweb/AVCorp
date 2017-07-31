package POM.Corp;

import POM.BaseLogPage;
import POM.BasePage;
import org.openqa.selenium.WebDriver;

/**
 * Created by Дмитрий on 19.05.2017.
 */
public class StreamPage extends BaseLogPage {
    public StreamPage(String browser) {
        super(browser);
    }

    public StreamPage(WebDriver driver) {
        super(driver);
    }

    public static String url = "https://corp.avmg.com.ua/stream/";
}
