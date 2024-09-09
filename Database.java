
package neslerp;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author DS
 */
public class Database {
    public static Connection connectDb(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/nesl","root","sire");
            return connect;
        }catch(Exception e){e.printStackTrace();}
                return null;
    }
}
