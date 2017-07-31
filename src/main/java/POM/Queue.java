package POM;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Дмитрий on 19.05.2017.
 */
public class Queue {
    private String title;
    private Integer tonnage;
    private String transaction;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTonnage(Integer tonnage) {
        this.tonnage = tonnage;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setPositions(Integer positions) {
        this.positions = positions;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setDecree(String decree) {
        this.decree = decree;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String licensePlate;
    private Integer positions;
    private String sender;
    private String decree;
    private String message;
    private String recipient;


    private String status;

    public String random;

    public Queue() {

    }

    public Queue(String s1, Integer s2, String s3, String s4, Integer s5, String s6, String s7, String s8, String s9) {
        this.title = s1;
        this.tonnage = s2;
        this.transaction = s3;
        this.licensePlate = s4;
        this.positions = s5;
        this.sender = s6;
        this.decree = s7;
        this.message = s8;
        this.status = s9;
    }

    public String getTitle() {
        return title;
    }

    public Integer getTonnage() {
        return tonnage;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getTransaction() {
        return transaction;
    }

    public Integer getPositions() {
        return positions;
    }

    public String getSender() {
        return sender;
    }

    public String getDecree() {
        return decree;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static String generateRandomNumber(int col){
        if (col<=0) return "";
        Random random = new Random();
        int[] arr = new int[col];
        for (int i=0; i<= arr.length-1; i++){
            arr[i] = random.nextInt(10);
        }
        String rand =  Arrays.toString(arr).replace(",","").replace("[", "").replace(" ","").replace("]","");
        return rand;
    }

    public static String generateRandomChars(int col){
        if (col<=0) return "";
        Random random = new Random();
        char[] arr = new char[col];
        for (int i=0; i<= arr.length-1; i++){
            arr[i] =  (char)(random.nextInt(26) + 'a');
        }
        String rand =  Arrays.toString(arr).replace(",","").replace("[", "").replace(" ","").replace("]","");
        return rand;
    }

    public static String  getRandomTransaction() {
        String[] transaction = {"Загрузка", "Выгрузка", "Загрузка/Выгрузка"};
        int a = (int) (Math.random() * 3);
        return transaction[a];
    }

    public static String  getRandomSender() {
        //String[] sender = {"Store1", "Store2", "Store3"};
        String[] sender = {"Склад1", "Склад2", "Склад3"};
        int a = (int) (Math.random() * 3);
        return sender[a];
    }

    public   String  getRandomStatus1() {
        String[] sender = {"Зарегистрирован", "На территории", "Под краном", "Недогружен", "Отправлен"};
        int a = (int) (Math.random() * 5);
        return sender[a];
    }

    public   String  getRandomStatus2() {
        String[] sender = {"Загружен/Выгружен", "Ушел пустой"};
        int a = (int) (Math.random() * 2);
        return sender[a];
    }

    public static String  getRandomDecree() {
        String[] decree = {"Да", "Нет"};
        int a = (int) (Math.random() * 2);
        return decree[a];
    }

    public static Integer  getRandomPositions() {
        int a = 1 + (int) (Math.random() * 40);
        return a;
    }

    public static Integer  getRandomTonnage() {
        int a = (int) (Math.random() * 1000);
        return a;
    }

    public static Queue generate() {
        return new Queue(
                generateRandomChars(10), getRandomTonnage(), getRandomTransaction(),   generateRandomNumber(4), getRandomPositions(), getRandomSender(),
                getRandomDecree(), generateRandomChars(20), "Зарегистрирован");
    }


    public void generateStatus(boolean edit){
        if (edit)  setStatus(getRandomStatus1());
        if (!edit) setStatus(getRandomStatus2());
    }


    public String getMessage() {
        return message;
    }


    @Override
    public String toString() {
        return "Queue{" +
                "title='" + title + '\'' +
                ", tonnage=" + tonnage +
                ", transaction='" + transaction + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", positions=" + positions +
                ", sender='" + sender + '\'' +
                ", decree='" + decree + '\'' +
                ", message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", recipient='" + recipient + '\'' +
                '}';
    }

    public void generateRecipient() {
        boolean flag = true;
        String[] recipient = {"Склад1", "Склад2", "Склад3"};
        int a = 0;
        while (flag) {
            a = (int) (Math.random() * 3);
            if (!(recipient[a].equals(this.sender))){
                flag = false;
            }
        }
        setRecipient(recipient[a]);
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
}
