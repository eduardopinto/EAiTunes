package itunes;

import classes.Content;
import classes.User;
import java.sql.SQLException;
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

    public boolean createUser() {
        return true;
    }

    public User deleteUser(int userId) {
        return null;
    }

    public boolean updateUser() {
        return true;
    }

    public HashMap<Integer, User> getAllUsers() {
        return null;
    }

    //contents
    public Content getContentById(int contentId) {
        return null;
    }

    public boolean createContent() {
        return true;
    }

    public Content deleteContent(int contentId) {
        return null;
    }

    public boolean updateContent() {
        return true;
    }

    public HashMap<Integer, User> getAllContents() {
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
