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
	
	public static User getUser(int userId) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch(ClassNotFoundException e) {
            return null;
        }		
	
        Connection conn = null;
        try {
        	conn = DriverManager.getConnection("jdbc:sqlite:hotelData.db");
        	PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM UserRegistry WHERE userId = ?");
        	pstmt.setInt(1, userId);
        	ResultSet r = pstmt.executeQuery();
            if(!r.next()) {
                return null;
            }
            int _userId = r.getInt(1);
            String _userName = r.getString(2);
            User result = new User(_userId, _userName);
            try {
                if(conn != null) {
                    conn.close();
                }
            } catch(SQLException e) {
                return null;
            }
            return result;
        } catch(SQLException e) {
            return null;
        } 
	}
	
	public static ArrayList<User> search(String userName) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch(ClassNotFoundException e) {
            return null;
        }		
	
        ArrayList<User> userL = new ArrayList<User>();
        Connection conn = null;
        try {
        	conn = DriverManager.getConnection("jdbc:sqlite:hotelData.db");
        	PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM UserRegistry WHERE userName LIKE ?");
        	pstmt.setString(1, userName);
            ResultSet r = pstmt.executeQuery();
        	while(r.next()) {
        		int _userId = r.getInt(1);
        		String _userName = r.getString(2);
        		User result = new User(_userId, _userName);
        		userL.add(result);
        	}
        } catch(SQLException e) {
            userL = null;
        } finally {
            try {
                if(conn != null) {
                    conn.close();
                }
            } catch(SQLException e) {
                userL = null;
            } finally {
                return userL;
            }
        }
    }

    public static boolean removeUser(int userId) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch(ClassNotFoundException e) {
            return false;
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:hotelData.db");
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM BookingRegistry WHERE userId = ?");
            pstmt.setInt(1, userId);
            pstmt.executeUpdate();
            try {
                if(conn != null) {
                    conn.close();
                }
            } catch(SQLException e) {
                return false;
            }
            return true;
        } catch(SQLException e) {
            return false;
        }        
    }

    public static boolean addUser(User user) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch(ClassNotFoundException e) {
            return false;
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:hotelData.db");
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO UserRegistry VALUES (?,?)");
            pstmt.setInt(1, user.getId());
            pstmt.setString(2, user.getUserName());
            pstmt.executeUpdate();
            try {
                if(conn != null) {
                    conn.close();
                }
            } catch(SQLException e) {
                return false;
            }
            return true;
        } catch(SQLException e) {
            return false;
        }        
    }
}
