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

    /*
     * isSameDay
     * return true if two users are equal
     * two users are equal if they have the same username
     *
     * Input: other user
     */
    @Override
    public boolean equals(Object otherUser){
        boolean returnValue = false;

        if(otherUser instanceof User) {
            if (((User) otherUser).username.equalsIgnoreCase(username)) {
                returnValue = true;
            }
        }
        return returnValue;
    }
}
