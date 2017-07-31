import POM.Queue;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Дмитрий on 12.06.2017.
 */
public class TestUser extends BaseTest{
    @Test(priority = 1)
    public void checkDisplayedAddButtonUser() throws InterruptedException {
        streamPage = loginPage.logIn(loginUser, passwordUser);
        cargoTransportationPage = streamPage.goToCargoTransportationPage();
        cargoTransportationPage = cargoTransportationPage.setTheNumberOfOtputList("200");
        Thread.sleep(2000);
        Assert.assertFalse(cargoTransportationPage.checkDisplayedAddButton());
    }

    @Test(priority = 2)
    public void checkDisplayedExportExcelButtonUser() throws InterruptedException {
        Assert.assertTrue(cargoTransportationPage.checkDisplayedExportExcelButton());
    }
    @Test(priority = 3)
    public void checkFilterUser() throws InterruptedException {
        System.out.println(queue);
        System.out.println(queue2);
        cargoTransportationPage.setFilterByName(queue);
        cargoTransportationPage.applyFilter();
        Assert.assertTrue(cargoTransportationPage.checkDisplayedQueue(queue));
        cargoTransportationPage.resetFilter();
        cargoTransportationPage.setFilterByAllProperties(queue);
        cargoTransportationPage.applyFilter();
        Assert.assertTrue(cargoTransportationPage.checkDisplayedQueue(queue));
        cargoTransportationPage.resetFilter();
        cargoTransportationPage.resetFilter();
    }

    @Test(priority = 4)
    public void checkHistoryUser() throws InterruptedException {
        reviewOrEditQueuePage = cargoTransportationPage.openQueue(queue);
        reviewOrEditQueuePage.showHistoryWindow();
        Thread.sleep(1500);
        System.out.println("   " + reviewOrEditQueuePage.getLastChangeOfStatus());
        System.out.println("   " + queue.getStatus());
        System.out.println("   " + reviewOrEditQueuePage.getLastChangeOfRecipient());
        System.out.println("   " + queue.getRecipient());
        Assert.assertTrue(reviewOrEditQueuePage.getLastChangeOfStatus().equals(queue.getStatus()));
        Assert.assertTrue(reviewOrEditQueuePage.getLastChangeOfRecipient().equals(queue.getRecipient()));
        reviewOrEditQueuePage.showMainWindow();
        cargoTransportationPage = reviewOrEditQueuePage.goToListOfQueue();
    }

    @Test(priority = 5)
    public void checkSortingDoubleUser() throws InterruptedException {
        cargoTransportationPage = cargoTransportationPage.sortingTonnage();
        Thread.sleep(1500);
        cargoTransportationPage = cargoTransportationPage.sortingTonnageDown();
        Thread.sleep(1500);
        System.out.println(cargoTransportationPage.checkSortingTonnage("down"));
        cargoTransportationPage = cargoTransportationPage.sortingTonnageUp();
        Thread.sleep(1500);
        System.out.println(cargoTransportationPage.checkSortingTonnage("up"));
        Thread.sleep(1500);
    }

    @Test(priority = 6)
    public void checkSortingStringUser() throws InterruptedException {
        cargoTransportationPage = cargoTransportationPage.sortingTitle();
        Thread.sleep(1500);
        cargoTransportationPage = cargoTransportationPage.sortingTitleDown();
        Thread.sleep(1500);
        Assert.assertTrue(cargoTransportationPage.checkSortingTitle("down"));
        cargoTransportationPage = cargoTransportationPage.sortingTitleUp();
        Thread.sleep(1500);
        Assert.assertTrue(cargoTransportationPage.checkSortingTitle("up"));
    }

    @Test (priority = 7)
    public void checkDisplayedAddingButtonUser(){
        Assert.assertFalse(cargoTransportationPage.checkDisplayedAddButton());
    }

    @Test (priority = 8)
    public void checkEnabledAllFieldsForChangingUser() throws InterruptedException {
        Assert.assertFalse(cargoTransportationPage.checkDisplayedAddButton());
        reviewOrEditQueuePage = cargoTransportationPage.openQueue(queueForPriority);
        Assert.assertTrue(reviewOrEditQueuePage.checkButtonComeBackAvailability());
        cargoTransportationPage = reviewOrEditQueuePage.PressButtonComeBack();
        loginPage = cargoTransportationPage.logOut();
    }

}
