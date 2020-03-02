package comp3350.habittracker.Persistence.stub;

import java.util.ArrayList;

import comp3350.habittracker.DomainObjects.Note;
import comp3350.habittracker.DomainObjects.User;
import comp3350.habittracker.Persistence.UserPersistance;

public class UserStub implements UserPersistance {
    private ArrayList<User> users = new ArrayList<>();

    public UserStub(){
        users.add(new User("userA", "Gordon Ramsey"));
        users.add(new User("userB", "Mr Beast"));
        users.add(new User("userC", "Dr Mike"));
    }

    public boolean addUser(User user){
        boolean returnValue = false;
        //todo: habit must check for name and uID of each item not just the habit object itself

        if(!users.contains(user)){
            users.add(user);
            returnValue = true;
        }
        return returnValue;
    };
    public boolean deleteUser(String username){
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                users.remove(user);
                return true;
            }
        }

        return false;
    }
    public boolean modifyUser(User user){
        for (User useri : users) {
            if (user.getUsername().equalsIgnoreCase(useri.getUsername())) {
                users.remove(useri);
                users.add(user);
                return true;
            }
        }


        return false;
    }
    public User getUser(String username){
        for(User user : users){
            if(user.getUsername().equalsIgnoreCase(username)){
                return user;
            }
        }
        return null;
    }
    public ArrayList<User> getAllUsers(){
        return users;
    }
}
