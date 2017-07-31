import POM.Queue;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Дмитрий on 12.06.2017.
 */
public class TestOperator extends BaseTest{
    @Test(priority = 1)
    public void checkDisplayedAddButtonOperator() throws InterruptedException {
        streamPage = loginPage.logIn(loginOperator, passwordOperator);
        cargoTransportationPage = streamPage.goToCargoTransportationPage();
        cargoTransportationPage = cargoTransportationPage.setTheNumberOfOtputList("200");
        Thread.sleep(2000);
        Assert.assertTrue(cargoTransportationPage.checkDisplayedAddButton());
    }

    @Test(priority = 2)
    public void checkExportExcelButtonOperator(){
        Assert.assertTrue(cargoTransportationPage.checkDisplayedExportExcelButton());
    }

    @Test(priority = 3)
    public void checkEnabledAllFieldsForChangingOperator() throws InterruptedException {
        reviewOrEditQueuePage = cargoTransportationPage.openQueue(queueForPriority);
        Assert.assertTrue(reviewOrEditQueuePage.checkButtonComeBackAvailability());
        cargoTransportationPage = reviewOrEditQueuePage.PressButtonComeBack();
    }

    @Test(priority = 4)
    public void checkCreationQueueOperator() throws InterruptedException {
        queue2 = Queue.generate();
        queueForPriority2 = Queue.generate();
        addingQueuePage = cargoTransportationPage.addQueue();
        cargoTransportationPage = addingQueuePage.fillQueue(queueForPriority2).saveQueue();
        addingQueuePage = cargoTransportationPage.addQueue();
        cargoTransportationPage = addingQueuePage.fillQueue(queue2).saveQueue();
        Queue rearQueue = cargoTransportationPage.returnQueueByTitle(queueForPriority2.getTitle());
        System.out.println(rearQueue);
    }

    @Test(priority = 5)
    public void checkRecipientOperator() throws InterruptedException {
        reviewOrEditQueuePage = cargoTransportationPage.openQueue(queue2);
        Assert.assertTrue(reviewOrEditQueuePage.checkRecipient(queue2.getSender()));

    }

    @Test(priority = 6)
    public void checkChangeQueueAndHistoryOperator() throws InterruptedException {
        queue2.generateStatus(true);
        queue2.generateRecipient();
        reviewOrEditQueuePage.fillStatusOfQueue(queue2).fillRecipientOfQueue(queue2).apply();
        reviewOrEditQueuePage.showHistoryWindow();
        Assert.assertTrue(reviewOrEditQueuePage.getLastChangeOfStatus().equals(queue2.getStatus()));
        Assert.assertTrue(reviewOrEditQueuePage.getLastChangeOfRecipient().equals(queue2.getRecipient()));
        reviewOrEditQueuePage.showMainWindow();
    }

    @Test(priority = 7)
    public void checkChangeStatusAndUnavailableFormOperator() throws InterruptedException {
        queue2.generateStatus(false);
        reviewOrEditQueuePage.fillStatusOfQueue(queue2).apply();;
        reviewOrEditQueuePage.showHistoryWindow();
        Assert.assertTrue(reviewOrEditQueuePage.getLastChangeOfStatus().equals(queue2.getStatus()));
        reviewOrEditQueuePage.showMainWindow();
        Assert.assertTrue(reviewOrEditQueuePage.checkButtonComeBackAvailability());
    }

    @Test(priority = 8)
    public void checkDeletingQueueOperator() throws InterruptedException {
        cargoTransportationPage = reviewOrEditQueuePage.goToCargoTransportationPage();
        cargoTransportationPage.delateQueue(queue2);
        Assert.assertFalse(cargoTransportationPage.checkDisplayedQueue(queue2));
        loginPage = cargoTransportationPage.logOut();
    }

}
