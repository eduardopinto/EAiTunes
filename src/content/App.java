package content;

import classes.Content;
import itunes.DBConnector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Eduardo Pinto PG27732
 * @author Nuno Dionísio PG27712
 * @author Nuno Gomes PG27766
 * @author Nuno Santos PG27728
 *
 */
public class App extends Content {

    private String details;

    public App(int contentId) {
        super(contentId);
    }

    public App(String details, String publisher, String name, float price) throws Exception {
        super(publisher, name, price);
        this.setDetails(details);
    }

    public String getDetails() {
        return details;
    }

    public final void setDetails(String details) {
        this.details = details;
    }    

    @Override
    public boolean load(DBConnector databaseConnection) throws Exception {
        boolean loaded = false;
        try {
            String query = "select * from app a, content c where a.appId = ?";
            PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, this.getContentId());
            preparedStatement.execute();
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                this.publisher = result.getString("publisher");
                this.name = result.getString("name");
                this.price = result.getFloat("price");
                this.details = result.getString("description");
                loaded = true;
                result.close();
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
        return loaded;
    }

    @Override
    public boolean persist(DBConnector databaseConnection) throws Exception {
        boolean added = false;
        try {
            databaseConnection.getConnection().setAutoCommit(false);
            super.persist(databaseConnection);
            String queryApp = " INSERT INTO App (appId, description) VALUES (?, ?)";
            PreparedStatement preparedStatementApp = databaseConnection.getConnection().prepareStatement(queryApp);
            preparedStatementApp.setInt(1, this.getContentId());
            preparedStatementApp.setString(2, this.getDetails());
            preparedStatementApp.execute();
            databaseConnection.getConnection().commit();
            added = true;
        } catch (Exception e) {
            throw new Exception(e);
        }
        return added;
    }
    
    @Override
    public boolean update(DBConnector databaseConnection) throws Exception {
        boolean updated = false;
        try {
            databaseConnection.getConnection().setAutoCommit(false);
            super.update(databaseConnection);
            String queryApp = " UPDATE App SET description = ? WHERE appId = ?";
            PreparedStatement preparedStatementApp = databaseConnection.getConnection().prepareStatement(queryApp);
            preparedStatementApp.setString(1, this.getDetails());
            preparedStatementApp.setInt(2, this.getContentId());
            preparedStatementApp.execute();
            databaseConnection.getConnection().commit();
            updated = true;
        } catch (Exception e) {
            throw new Exception(e);
        }
        return updated;
    }

    @Override
    public String toString() {
        return "App{" + super.toString() + ", details=" + details + '}';
    }

    
}
