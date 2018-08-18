package main;

import database.DatabaseFunction;

import java.sql.ResultSet;

public class MainClass {
    public static void main(String[] args) {

        DatabaseFunction a = new DatabaseFunction();
        a.initialDB();
        ResultSet rs = a.waitingListByTime();
        a.printResult(rs);
        a.updateQueue();
        rs = a.doctorRelativesView();
        a.printResult(rs);
    }
}
