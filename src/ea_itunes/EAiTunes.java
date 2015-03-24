package ea_itunes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Eduardo Pinto PG27732
 * @author Nuno Dionísio PG27712
 * @author Nuno Gomes PG27766
 * @author Nuno Santos PG27728
 *
 */
public class EAiTunes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String url = "jdbc:mysql://localhost:3306/mydb";
        String username = "root";
        String password = "N699819a";
        Connection connection = null;
        
        try {
            System.out.println("Connecting database...");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connected!");
        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect the database!", e);
        } finally {
            System.out.println("Closing the connection.");
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignore) {
                }
            }
        }
    }

}
