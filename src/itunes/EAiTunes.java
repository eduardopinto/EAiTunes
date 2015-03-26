package itunes;

import classes.Content;
import classes.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    public User getUserById(int userId) throws Exception {
        User user = null;
        if (this.users.containsKey(userId)) {
            user = this.users.get(userId);
        } else {
            if (databaseConnection.isConnected()) {
                try {
                    User tmp = new User(userId);
                    if (tmp.load(databaseConnection)) {
                        this.users.put(tmp.getUserId(), tmp);
                        user = tmp;
                    }
                } catch (Exception e) {
                    throw new Exception(e);
                }
            } else {
                throw new Exception(DATABASE_ERROR);
            }
        }
        return user;
    }

    public boolean createUser(String firstName, String lastName) throws Exception {
        boolean added = false;
        if (databaseConnection.isConnected()) {
            try {
                User newUser = new User(firstName, lastName);
                if (newUser.persist(databaseConnection)) {
                    this.users.put(newUser.getUserId(), newUser);
                    added = true;
                }
            } catch (Exception e) {
                throw new Exception(e);
            }
        } else {
            throw new Exception(DATABASE_ERROR);
        }
        return added;
    }

    public boolean deleteUser(int userId) throws Exception {
        boolean deleted = false;
        if (databaseConnection.isConnected()) {
            try {
                if (loadUser(userId)) {
                    if (this.users.get(userId).delete(databaseConnection)) {                    
                        this.users.remove(userId);
                        deleted = true;
                    }
                }
            } catch (Exception e) {
                throw new Exception(e);
            }
        } else {
            throw new Exception(DATABASE_ERROR);
        }
        return deleted;
    }

    public boolean updateUser(int userId, String firstName, String lastName) throws Exception {
        boolean updated = false;
        if (databaseConnection.isConnected()) {
            try {
                if (loadUser(userId)) {
                    this.users.get(userId).setFirstName(firstName);
                    this.users.get(userId).setLastName(lastName);
                    if (this.users.get(userId).update(databaseConnection)) {
                        updated = true;
                    }
                }
            } catch (Exception e) {
                throw new Exception(e);
            }
        } else {
            throw new Exception(DATABASE_ERROR);
        }
        return updated;
    }

    private boolean loadUser(int userId) throws Exception {
        boolean loaded = false;
        if (!this.users.containsKey(userId)) {
            if (this.getUserById(userId) != null) {
                loaded = true;
            }
        }
        return loaded;
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
