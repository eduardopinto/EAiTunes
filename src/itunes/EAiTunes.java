package itunes;

import classes.Content;
import classes.User;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Eduardo Pinto PG27732
 * @author Nuno Dionísio PG27712
 * @author Nuno Gomes PG27766
 * @author Nuno Santos PG27728
 *
 */
public class EAiTunes {

    private final static String DATABASE_ERROR = "Cannot connect the database!";

    private Map<Integer, User> users;
    private Map<Integer, Content> contents;
    private DBConnector databaseConnection;

    public EAiTunes(DBConnector connector) {
        this.databaseConnection = connector;
        this.users = new HashMap<>();
        this.contents = new HashMap<>();
    }

    //users
    public User getUserById(int userId) {
        return null;
    }

    public boolean createUser(String firstName, String lastName) throws Exception {
        boolean added = false;
        if (databaseConnection.isConnected()) {
            try {
                User newUser = new User(firstName, lastName);
                String query = " insert into User (firstName, lastName) values (?, ?)";
                PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(query);
                preparedStatement.setString(1, newUser.getFirstName());
                preparedStatement.setString(2, newUser.getLastName());
                preparedStatement.execute();
                databaseConnection.getConnection().close();
                added = true;
            } catch (Exception e) {
               throw new Exception(e); 
            }
        } else {
            throw new Exception(DATABASE_ERROR);
        }
        return added;
    }

    public User deleteUser(int userId) {
        return null;
    }

    public boolean updateUser(int userId, String firstName, String lastName) {
        return true;
    }

    public HashMap<Integer, User> getAllUsers() {
        return null;
    }

    //music
    public Content getMusicById(int musicId) {
        return null;
    }
    
    public boolean createMusic() {
        return true;
    }
    
    public boolean updateMusic(int musicId) {
        return true;
    }
    
    //app
    public Content getAppById(int appId) {
        return null;
    }
    
     public boolean createApp() {
        return true;
    }
     
    public boolean updateApp(int appId) {
        return true;
    }
    
    //video
    public Content getVideoById(int videoId) {
        return null;
    }
     
    public boolean createVideo() {
        return true;
    }
    
    public boolean updateVideo(int videoId) {
        return true;
    }

    //all conntents - cascade delete
    public Content deleteContent(int contentId) {
        return null;
    }

    //buys
    public Content getBuyById(int id) {
        return null;
    }

    public boolean createBuy(int id, Content content) {
        return true;
    }

}
