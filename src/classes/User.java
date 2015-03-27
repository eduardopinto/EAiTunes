package classes;

import content.App;
import content.Music;
import content.Video;
import itunes.DBConnector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public final class User {

    private final static String INVALID_FIRST_NAME = "Invalid first name";
    private final static String INVALID_LAST_NAME = "Invalid last name";

    private int userId;
    private String firstName;
    private String lastName;
    private Map<Integer, Content> contents;

    public User(int userId) {
        this.userId = userId;
        this.contents = new HashMap<>();
    }

    public User(String firstName, String lastName) throws Exception {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.contents = new HashMap<>();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws Exception {
        if (isValidName(firstName)) {
            this.firstName = firstName;
        } else {
            throw new Exception(INVALID_FIRST_NAME);
        }

    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws Exception {
        if (isValidName(lastName)) {
            this.lastName = lastName;
        } else {
            throw new Exception(INVALID_LAST_NAME);
        }
    }

    public Content getBuyById(int id) {
        Content content = null;

        if (this.contents.isEmpty()) {
            content = this.contents.get(id);
        }
        return content;
    }

    private boolean isValidName(String name) {
        return name != null && !name.trim().equals("");
    }

    public boolean persist(DBConnector databaseConnection) throws SQLException {
        boolean added = false;
        String query = " INSERT INTO User (firstName, lastName) VALUES (?, ?)";

        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, this.getFirstName());
        preparedStatement.setString(2, this.getLastName());
        preparedStatement.execute();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        if (rs.next()) {
            this.userId = rs.getInt(1);
            added = true;

        }
        return added;
    }

    public boolean update(DBConnector databaseConnection) throws SQLException {
        boolean updated = false;
        String query = "UPDATE User SET firstName = '" + this.getFirstName() + "', lastName = '" + this.getLastName() + "' WHERE userId=" + this.userId;

        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(query);
        preparedStatement.execute();
        updated = true;

        return updated;
    }

    public boolean delete(DBConnector databaseConnection) throws SQLException {
        boolean deleted = false;
        String query = "delete from User where userId = '" + this.userId + "'";

        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(query);
        preparedStatement.execute();
        deleted = true;

        return deleted;
    }

    public boolean load(DBConnector databaseConnection) throws SQLException, Exception {
        boolean loaded = false;
        String query = "select * from user where userId = " + userId;
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(query);
        preparedStatement.execute();
        ResultSet result = preparedStatement.executeQuery();
        if (result.next()) {
            this.firstName = result.getString("firstName");
            this.lastName = result.getString("lastName");
            loadContents(databaseConnection);
            loaded = true;
            result.close();
        }
        return loaded;
    }


    public boolean buyContent(DBConnector databaseConnection, int contentId) throws SQLException {
        boolean added = false;
        String query = " INSERT INTO Buy (userId, contentId) VALUES (?, ?)";

        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(query);
        preparedStatement.setInt(1, this.getUserId());
        preparedStatement.setInt(2, contentId);
        preparedStatement.execute();
        added = true;

        return added;
    }

    public Map<Integer, Content> getContents() {
        return contents;
    }

    
    
    public boolean getAllAppsByUser(DBConnector databaseConnection) throws Exception {
        boolean loaded = false;
        String query = "SELECT * from app a, content c, buy b where b.userId = " + this.userId + " and b.contentId = c.contentId and c.contentId = a.appId";

        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(query);
        ResultSet result = preparedStatement.executeQuery();
        
        while (result.next()) {
            Content c = new App(result.getString("description"), result.getString("publisher"), result.getString("name"), result.getFloat("price"));
            this.contents.put(result.getInt("appId"), c);
        }
        loaded = true;
        result.close();

        return loaded;
    }
    
    public boolean getAllMusicsByUser(DBConnector databaseConnection) throws Exception {
        boolean loaded = false;
        String query = "SELECT * from music a, content c, buy b where b.userId = " + this.userId + " and b.contentId = c.contentId and c.contentId = a.musicId";

        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(query);
        ResultSet result = preparedStatement.executeQuery();
        
        while (result.next()) {
            Content c = new Music(result.getInt("duration"), result.getString("publisher"), result.getString("name"), result.getFloat("price"));
            this.contents.put(result.getInt("musicId"), c);
        }
        loaded = true;
        result.close();

        return loaded;
    }
    
     public boolean getAllVideosByUser(DBConnector databaseConnection) throws Exception {
        boolean loaded = false;
        String query = "SELECT * from video a, content c, buy b where b.userId = " + this.userId + " and b.contentId = c.contentId and c.contentId = a.videoId";

        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(query);
        ResultSet result = preparedStatement.executeQuery();
        
        while (result.next()) {
            Content c = new Video(result.getString("resolution"), result.getString("publisher"), result.getString("name"), result.getFloat("price"));
            this.contents.put(result.getInt("videoId"), c);
        }
        loaded = true;
        result.close();

        return loaded;
    }

    private void loadContents(DBConnector databaseConnection) throws Exception {
        getAllAppsByUser(databaseConnection);
        getAllMusicsByUser(databaseConnection);
        getAllVideosByUser(databaseConnection);
    }
}
