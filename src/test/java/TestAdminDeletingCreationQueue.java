import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Дмитрий on 12.06.2017.
 */
public class TestAdminDeletingCreationQueue extends BaseTest{
    @Test
    public void checkDeletingQueueWhichCreatedByOperator() throws InterruptedException {
        streamPage = loginPage.logIn(loginAdmin, passwordAdmin);
        cargoTransportationPage = streamPage.goToCargoTransportationPage();
        cargoTransportationPage = cargoTransportationPage.setTheNumberOfOtputList("200");
        Thread.sleep(2000);
        cargoTransportationPage.delateQueue(queueForPriority);
        cargoTransportationPage.delateQueue(queueForPriority2);
        cargoTransportationPage.delateQueue(queue);
        Assert.assertFalse(cargoTransportationPage.checkDisplayedQueue(queueForPriority2));
        Assert.assertFalse(cargoTransportationPage.checkDisplayedQueue(queueForPriority));
        Assert.assertFalse(cargoTransportationPage.checkDisplayedQueue(queue));
        queueForPriority2 = null;
        queueForPriority = null;
    }
}
