package comp3350.habittracker.Logic;

import comp3350.habittracker.Persistence.UserPersistence;

public class UserManager {

    private static UserPersistence userPersistence;

    public UserManager(UserPersistence db){
        userPersistence = db;
    }

    //return if login was a success
    public static boolean login(String username, String password){
        return userPersistence.getUser(username,password);
    }

    //register user
    public static boolean register(String username, String password){
        return userPersistence.addUser(username, password);
    }

    //change password
    public static void changePassword(String username, String password){
        userPersistence.changePassword(username, password);
    }
}
