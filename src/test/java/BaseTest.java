import POM.Corp.*;
import POM.Queue;
import driver.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

/**
 * Created by Дмитрий on 19.05.2017.
 */
public abstract class BaseTest {
    LoginPage loginPage;
    public static Queue queue;
    public static Queue queue2;
    public static Queue queueForPriority;
    public static Queue queueForPriority2;
    StreamPage streamPage;
    CargoTransportationPage cargoTransportationPage;
    AddingQueuePage addingQueuePage;
    ReviewOrEditQueuePage reviewOrEditQueuePage;
    String loginAdmin = "awe5040";
    String passwordAdmin = "qwerty5040";
    String loginUser = "av_cargo_test";
    String passwordUser = "av_cargo_test";
    String loginOperator = "av_cargo_test2";
    String passwordOperator = "av_cargo_test2";

    private WebDriver driver;

    @BeforeClass
    @Parameters({"browser"})
    public void setUp(@Optional("chrome") String browser){
        loginPage = new LoginPage(browser);
        Driver.maximize();
    }

    @AfterClass
    public void tearDown() {
        Driver.tearDown();
        Driver.nullDriver();
    }


}
