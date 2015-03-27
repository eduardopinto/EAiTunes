package classes;

import itunes.DBConnector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Eduardo Pinto PG27732
 * @author Nuno Dionísio PG27712
 * @author Nuno Gomes PG27766
 * @author Nuno Santos PG27728
 *
 */
public abstract class Content {

    private final static String INVALID_PUBLISHER_NAME = "Invalid publisher name";
    private final static String INVALID_CONTENT_NAME = "Invalid content name";
    private final static String INVALID_PRICE = "Invalid price";

    protected int contentId;
    protected String publisher;
    protected String name;
    protected float price;

    public Content(int contentId) {
        this.contentId = contentId;
    }

    public Content(String publisher, String name, float price) throws Exception {
        this.setPublisher(publisher);
        this.setName(name);
        this.setPrice(price);
    }

    public int getContentId() {
        return contentId;
    }

    public String getPublisher() {
        return publisher;
    }

    public final void setPublisher(String publisher) throws Exception {
        if (isValidName(publisher)) {
            this.publisher = publisher;
        } else {
            throw new Exception(INVALID_PUBLISHER_NAME);
        }
    }

    public String getName() {
        return name;
    }

    public final void setName(String name) throws Exception {
        if (isValidName(publisher)) {
            this.name = name;
        } else {
            throw new Exception(INVALID_CONTENT_NAME);
        }
    }

    public float getPrice() {
        return price;
    }

    public final void setPrice(float price) throws Exception {
        if (isValidPrice(price)) {
            this.price = price;
        } else {
            throw new Exception(INVALID_PRICE);
        }
    }

    private boolean isValidName(String name) {
        return name != null && !name.trim().equals("");
    }

    private boolean isValidPrice(float price) {
        return price >= 0.0f;
    }

    @Override
    public String toString() {
        return "publisher=" + publisher + ", name=" + name + ", price=" + price;
    }

    public abstract boolean load(DBConnector databaseConnection) throws Exception;

    public boolean persist(DBConnector databaseConnection) throws Exception {
        boolean added = false;
        try {
            String queryContent = " INSERT INTO Content (publisher, name, price) VALUES (?, ?, ?)";

            PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(queryContent, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, this.getPublisher());
            preparedStatement.setString(2, this.getName());
            preparedStatement.setFloat(3, this.getPrice());
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                this.contentId = rs.getInt(1);
            }
            added = true;
        } catch (Exception e) {
            throw new Exception(e);
        }
        return added;
    }

    public boolean update(DBConnector databaseConnection) throws Exception {
        boolean updated = false;
        try {
            String queryContent = " UPDATE Content SET publisher = ?, name = ?, price = ? WHERE contentId = ?";
            PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(queryContent);
            preparedStatement.setString(1, this.getPublisher());
            preparedStatement.setString(2, this.getName());
            preparedStatement.setFloat(3, this.getPrice());
            preparedStatement.setInt(4, this.getContentId());
            preparedStatement.execute();
            updated = true;
        } catch (Exception e) {
            throw new Exception(e);
        }
        return updated;
    }

    public boolean delete(DBConnector databaseConnection) throws Exception {
        boolean deleted = false;
        try {
            String query = "delete from content where contentId = ?";
            PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, this.getContentId());
            preparedStatement.execute();
            deleted = true;
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
        return deleted;
    }

}
