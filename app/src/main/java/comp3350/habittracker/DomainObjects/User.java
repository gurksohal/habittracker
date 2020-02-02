package comp3350.habittracker.DomainObjects;

import java.io.Serializable;

public class User implements Serializable {
    private String username;

    public User(String userName){
        username = userName;
    }

    public String getUsername() {
        return username;
    }

    public boolean equals(User otherUser){ //two users are equal if they have the same username
        boolean returnValue = false;
        if(otherUser.username.equalsIgnoreCase(username)){
            returnValue = true;
        }
        return returnValue;
    }
}
