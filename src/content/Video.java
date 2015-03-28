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
public class Video extends Content {

    private final static String INVALID_VIDEO_RESOLUTION = "Invalid Video Resolution";
    
    private String resolution;

    public Video(String resolution, String publisher, String name, float price) throws Exception {
        super(publisher, name, price);
        this.setResolution(resolution);
    }

    public Video(int contentId) {
        super(contentId);
    }

    public String getResolution() {
        return resolution;
    }

    public final void setResolution(String resolution) throws Exception {
        if (resolution.isEmpty() || resolution == null){
            throw new Exception(INVALID_VIDEO_RESOLUTION);
        } else {
            this.resolution = resolution;
        }
    }

    @Override
    public boolean load(DBConnector databaseConnection) throws Exception {
        boolean loaded = false;
        try {
            String query = "select * from video a, content c where a.videoId = ?";
            PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, this.getContentId());
            preparedStatement.execute();
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                this.publisher = result.getString("publisher");
                this.name = result.getString("name");
                this.price = result.getFloat("price");
                this.resolution = result.getString("resolution");
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
            String queryApp = " INSERT INTO Video (videoId, resolution) VALUES (?, ?)";
            PreparedStatement preparedStatementApp = databaseConnection.getConnection().prepareStatement(queryApp);
            preparedStatementApp.setInt(1, this.getContentId());
            preparedStatementApp.setString(2, this.getResolution());
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
            String queryApp = " UPDATE Video SET resolution = ? WHERE videoId = ?";
            PreparedStatement preparedStatementApp = databaseConnection.getConnection().prepareStatement(queryApp);
            preparedStatementApp.setString(1, this.getResolution());
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
        return "Video{" + super.toString() + ", resolution=" + resolution + '}';
    }

}
