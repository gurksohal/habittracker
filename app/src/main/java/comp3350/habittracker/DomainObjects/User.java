package comp3350.habittracker.DomainObjects;

public class User {
    private String username;

    public User(String uname){
        username = uname;
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
