package travel.website.utilities;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
 
public class ConnectionUtil {
    private static final String DBNAME = "";
    private static final String DB_USERNAME = "";
    private static final String DB_PASSWORD = "";
     
     
    public static Connection getConnection() throws Exception {
           Connection conn = null;
           try {
             String url = "jdbc:mysql://104.130.88.118:3306/"+DBNAME+"?user="+DB_USERNAME+"&password="+DB_PASSWORD;
             Class.forName("com.mysql.jdbc.Driver");
             conn = DriverManager.getConnection(url);
           } catch (SQLException sqle) {
              System.out.println("SQLException: Unable to open connection to db: "+sqle.getMessage());
              throw sqle;
           } catch(Exception e) {
              System.out.println("Exception: Unable to open connection to db: "+e.getMessage());
              throw e;
           }
           return conn;
        }
     
    public static void executeUpdate(String strQuery) throws Exception {
        Connection conn = null;
         
        try {
            conn = getConnection();
            Statement stmt  = conn.createStatement();
            stmt.executeUpdate(strQuery);
             
        } catch (SQLException sqle) {
            System.out.println("SQLException: Unable to execute query : "+strQuery);
            throw sqle;
        } catch (Exception e) {
            System.out.println("Exception: Unable to execute query: "+strQuery);
            throw e;
        } finally {
            closeConnection(conn);
        }
    }
    

 
    public static void closeConnection(Connection conn) {
        try {
            if(conn!=null && !conn.isClosed()) {
                conn.close();
            }
        } catch(SQLException sqle) {
            System.out.println("Error while closing connection.");
        }
    }
 
}
