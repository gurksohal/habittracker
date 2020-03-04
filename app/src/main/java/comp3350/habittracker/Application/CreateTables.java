package comp3350.habittracker.Application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateTables {

    public static void createHabitsTable(){
        String habitsTable = "CREATE MEMORY TABLE IF NOT EXISTS PUBLIC.HABITS(NAME VARCHAR(25), WEEKAMT INTEGER,COMPLETEAMT INTEGER, LASTCOMPLETEDATE VARCHAR(14), TIME VARCHAR(20), SORTTIME INTEGER, USER VARCHAR(20), PRIMARY KEY (NAME,USER), FOREIGN KEY (USER) REFERENCES USERS(USERNAME))\n";
        try{
            Connection c = DriverManager.getConnection("jdbc:hsqldb:file:" + Main.getDBPathName() + ";shutdown=true", "SA", "");
            c.createStatement().executeUpdate(habitsTable);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void createUsersTable(){
        String userTable = "CREATE MEMORY TABLE IF NOT EXISTS PUBLIC.USERS(USERNAME VARCHAR(20),PASSWORD VARCHAR(20), PRIMARY KEY (USERNAME))\n";
        try{
            Connection c = DriverManager.getConnection("jdbc:hsqldb:file:" + Main.getDBPathName() + ";shutdown=true", "SA", "");
            c.createStatement().executeUpdate(userTable);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void createNotesTable(){
        String notesTable = "CREATE MEMORY TABLE IF NOT EXISTS PUBLIC.NOTES(HABITNAME VARCHAR(50),USER VARCHAR(20), TEXT VARCHAR(255), FEELING INTEGER, DATE VARCHAR(14), PRIMARY KEY (HABITNAME, USER, TEXT), FOREIGN KEY (HABITNAME, USER) REFERENCES HABITS(NAME, USER))\n";
        try{
            Connection c = DriverManager.getConnection("jdbc:hsqldb:file:" + Main.getDBPathName() + ";shutdown=true", "SA", "");
            c.createStatement().executeUpdate(notesTable);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
