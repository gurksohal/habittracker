package comp3350.habittracker.Persistence;

import java.util.ArrayList;

import comp3350.habittracker.DomainObjects.Note;
import comp3350.habittracker.DomainObjects.User;

public class UserStub {
    private ArrayList<User> user = new ArrayList<>();

    public UserStub(){
        user.add(new User("userA", "Gordon Ramsey"));
        user.add(new User("userB", "Mr Beast"));
        user.add(new User("userC", "Dr Mike"));
    }
}
