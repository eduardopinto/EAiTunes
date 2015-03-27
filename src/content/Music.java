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
public class Music extends Content {

    private int duration;

    public Music(int contentId) {
        super(contentId);
    }

    public Music(int duration, String publisher, String name, float price) throws Exception {
        super(publisher, name, price);
        this.setDuration(duration);
    }

    public int getDuration() {
        return duration;
    }

    public final void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public boolean load(DBConnector databaseConnection) throws Exception {
        boolean loaded = false;
        try {
            String query = "select * from music a, content c where a.musicId = ?";
            PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, this.getContentId());
            preparedStatement.execute();
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                this.publisher = result.getString("publisher");
                this.name = result.getString("name");
                this.price = result.getFloat("price");
                this.duration = result.getInt("duration");
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
            String queryApp = " INSERT INTO Music (musicId, duration) VALUES (?, ?)";
            PreparedStatement preparedStatementApp = databaseConnection.getConnection().prepareStatement(queryApp);
            preparedStatementApp.setInt(1, this.getContentId());
            preparedStatementApp.setInt(2, this.getDuration());
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
            String queryApp = " UPDATE Music SET duration = ? WHERE musicId = ?";
            PreparedStatement preparedStatementApp = databaseConnection.getConnection().prepareStatement(queryApp);
            preparedStatementApp.setInt(1, this.getDuration());
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
        return "Music{" + super.toString() + ", duration=" + duration + '}';
    }
}
