package comp3350.habittracker.Persistence.HSQLDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import comp3350.habittracker.DomainObjects.User;
import comp3350.habittracker.Persistence.UserPersistence;

public class UserHSQLDB implements UserPersistence {

    private String path;

    public UserHSQLDB(String db){
        path = db;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + path + ";shutdown=true", "SA", "");
    }

    @Override
    public boolean addUser(String username, String password) {
        boolean returnValue = false;
        try(Connection c = connection()){
            PreparedStatement st = c.prepareStatement("INSERT INTO users VALUES(?,?)");
            st.setString(1, username);
            st.setString(2, password);
            returnValue = st.execute();
            st.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return returnValue;
    }

    @Override
    public boolean isValidUser(String username, String password) {
        boolean returnValue = false;
        try(Connection c = connection()){
            PreparedStatement st = c.prepareStatement("SELECT * FROM users WHERE USERNAME=? AND password=?");
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            returnValue = rs.next();
            rs.close();
            st.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return returnValue;
    }

    @Override
    public User getUser(String username) {
        User user = null;
        try(Connection c = connection()){
            PreparedStatement st = c.prepareStatement("SELECT * FROM users WHERE username=?");
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            //if a user was found
            if(!rs.next()){
                user = new User(rs.getString("username"));
            }
            rs.close();
            st.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }
}
