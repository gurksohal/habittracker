package comp3350.habittracker.Persistence;

import comp3350.habittracker.DomainObjects.User;

public interface UserPersistence {
    boolean addUser(String username, String password);
    boolean isValidUser(String username, String password);
    User getUser(String username);
}
