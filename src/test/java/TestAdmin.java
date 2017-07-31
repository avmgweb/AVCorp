import POM.Corp.AddingQueuePage;
import POM.Corp.CargoTransportationPage;
import POM.Corp.ReviewOrEditQueuePage;
import POM.Corp.StreamPage;
import org.testng.annotations.Test;
import POM.Queue;
import org.testng.Assert;



/**
 * Created by Дмитрий on 19.05.2017.
 */
public class TestAdmin extends BaseTest {

    @Test (priority = 1)
    public void checkCreationQueue() throws InterruptedException {
        streamPage = loginPage.logIn(loginAdmin, passwordAdmin);
        cargoTransportationPage = streamPage.goToCargoTransportationPage();
        cargoTransportationPage = cargoTransportationPage.setTheNumberOfOtputList("200");
        Thread.sleep(2000);
        queue = Queue.generate();
        queueForPriority = Queue.generate();
        addingQueuePage = cargoTransportationPage.addQueue();
        cargoTransportationPage = addingQueuePage.fillQueue(queueForPriority).saveQueue();
        addingQueuePage = cargoTransportationPage.addQueue();
        cargoTransportationPage = addingQueuePage.fillQueue(queue).saveQueue();
        Queue rearQueue = cargoTransportationPage.returnQueueByTitle(queue.getTitle());
        System.out.println(rearQueue);
        System.out.println(queue);
        Assert.assertTrue(queue.getTitle().equals(rearQueue.getTitle()));
        Assert.assertTrue(queue.getTonnage().equals(rearQueue.getTonnage()));
        Assert.assertTrue(queue.getTransaction().equals(rearQueue.getTransaction()));
        Assert.assertTrue(queue.getPositions().equals(rearQueue.getPositions()));
        Assert.assertTrue(queue.getSender().equals(rearQueue.getSender()));
        Assert.assertTrue(queue.getStatus().equals(rearQueue.getStatus()));
    }

    @Test(priority = 2)
    public void checkRecipient() throws InterruptedException {
        reviewOrEditQueuePage = cargoTransportationPage.openQueue(queue);
        Assert.assertTrue(reviewOrEditQueuePage.checkRecipient(queue.getSender()));

    }

    @Test(priority = 3)
    public void checkChangeQueueAndHistory() throws InterruptedException {
        queue.generateStatus(true);
        queue.generateRecipient();
        reviewOrEditQueuePage.fillStatusOfQueue(queue).fillRecipientOfQueue(queue).apply();
        reviewOrEditQueuePage.showHistoryWindow();

        Assert.assertTrue(reviewOrEditQueuePage.getLastChangeOfStatus().equals(queue.getStatus()));
        Assert.assertTrue(reviewOrEditQueuePage.getLastChangeOfRecipient().equals(queue.getRecipient()));
        reviewOrEditQueuePage.showMainWindow();
    }

    @Test(priority = 4)
    public void checkChangeStatusAndUnavailableForm() throws InterruptedException {
        queue.generateStatus(false);
        reviewOrEditQueuePage.fillStatusOfQueue(queue).apply();;
        reviewOrEditQueuePage.showHistoryWindow();
        Assert.assertTrue(reviewOrEditQueuePage.getLastChangeOfStatus().equals(queue.getStatus()));
        reviewOrEditQueuePage.showMainWindow();
        Assert.assertTrue(reviewOrEditQueuePage.checkButtonComeBackAvailability());
    }

    @Test(priority = 5)
    public void checkDeletingQueue() throws InterruptedException {
        cargoTransportationPage = reviewOrEditQueuePage.goToCargoTransportationPage();
        cargoTransportationPage.delateQueue(queue);
        Assert.assertFalse(cargoTransportationPage.checkDisplayedQueue(queue));
        queue = null;
    }

    @Test(priority = 6)
        public void checkCancelQueue() throws InterruptedException {
            queue = Queue.generate();
            addingQueuePage = cargoTransportationPage.addQueue();
            addingQueuePage.fillQueue(queue);
            cargoTransportationPage = addingQueuePage.cancelQueue();
            Assert.assertFalse(cargoTransportationPage.checkDisplayedQueue(queue));
            addingQueuePage = cargoTransportationPage.addQueue();
            addingQueuePage.fillQueue(queue);
            addingQueuePage.saveQueue();
            Assert.assertTrue(cargoTransportationPage.checkDisplayedQueue(queue));
            reviewOrEditQueuePage = cargoTransportationPage.openQueue(queue);
            queue.generateStatus(false);
            queue.generateRecipient();
            reviewOrEditQueuePage.fillStatusOfQueue(queue).fillRecipientOfQueue(queue);
            cargoTransportationPage = reviewOrEditQueuePage.saveQueue();
    }

    @Test(priority = 7)
    public void checkExportExcelButton(){
        Assert.assertTrue(cargoTransportationPage.checkDisplayedExportExcelButton());
    }

    @Test(priority = 8)
    public void checkFilter() throws InterruptedException {
        cargoTransportationPage.setFilterByName(queue);
        cargoTransportationPage.applyFilter();
        Assert.assertTrue(cargoTransportationPage.checkDisplayedQueue(queue));
        cargoTransportationPage.resetFilter();
        cargoTransportationPage.setFilterByDecree(queue).setFilterByLicensePlate(queue).setFilterByPositions(queue);
        cargoTransportationPage.applyFilter();
        Assert.assertTrue(cargoTransportationPage.checkDisplayedQueue(queue));
        cargoTransportationPage.resetFilter();
        cargoTransportationPage.setFilterByAllProperties(queue);
        cargoTransportationPage.applyFilter();
        Assert.assertTrue(cargoTransportationPage.checkDisplayedQueue(queue));
        cargoTransportationPage.resetFilter();
    }

    @Test(priority = 9)
    public void checkSortingDouble() throws InterruptedException {
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

    @Test(priority = 10)
    public void checkSortingString() throws InterruptedException {
        cargoTransportationPage = cargoTransportationPage.sortingTitle();
        Thread.sleep(1500);
        cargoTransportationPage = cargoTransportationPage.sortingTitleDown();
        Thread.sleep(1500);
        Assert.assertTrue(cargoTransportationPage.checkSortingTitle("down"));
        cargoTransportationPage = cargoTransportationPage.sortingTitleUp();
        Thread.sleep(1500);
        Assert.assertTrue(cargoTransportationPage.checkSortingTitle("up"));
        loginPage = cargoTransportationPage.logOut();
    }

}