package hugbunadur8H;

import java.util.ArrayList;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class UserRegistry {
	
	public static ArrayList<User> search(int userId, String userName) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch(ClassNotFoundException e) {
            return null;
        }		
	
        ArrayList<User> userL = new ArrayList<User>();
        Connection conn = null;
        try {
        	conn = DriverManager.getConnection("jdbc:sqlite:data.db");
        	PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM UserRegistry WHERE userId = ? OR userName = ?");
        	pstmt.setInt(1, userId);
        	pstmt.setString(2, userName);
        	while(r.next()) {
        		int _userId = r.getInt(1);
        		int _userName = r.getString(2);
        		User result = new User(_userId, _userName);
        		res.add(result);
        	}
        } catch(SQLException e) {
            res = null;
        } finally {
            try {
                if(conn != null) {
                    conn.close();
                }
            } catch(SQLException e) {
                res = null;
            } finally {
                return res;
            }
        }
    }
}