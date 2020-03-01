package comp3350.habittracker.Persistence;

import java.util.ArrayList;

import comp3350.habittracker.DomainObjects.Note;
import comp3350.habittracker.DomainObjects.User;

public interface UserPersistance {
    boolean addUser(User user);
    boolean deleteUser(String username);
    boolean modifyUser(User user);
    Note getUser(String username);
}
