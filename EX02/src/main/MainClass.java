package main;

import database.DatabaseFunction;

public class MainClass {
    public static void main(String[] args) {

        DatabaseFunction a = new DatabaseFunction();
        a.CREATE_QUEUE_RESERVED();
        a.CREATE_DOCTOR_TABLE();
        a.CREATE_PATIENTS_TABLE();
        a.CREATE_QUEUE_TABLE();
        //a.waitingListByTime();
        a.updateQueue();
    }


}
