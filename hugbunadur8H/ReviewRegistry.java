package hugbunadur8H;

import java.util.ArrayList;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Date;

public class ReviewRegistry {
	
    public static Review getReview(int id) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch(ClassNotFoundException e) {
            return null;
        }		
        Connection conn = null;
        try {
        	conn = DriverManager.getConnection("jdbc:sqlite:data.db");
        	PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM ReviewRegistry WHERE id = ?");
        	pstmt.setInt(1, id);
        	ResultSet r = pstmt.executeQuery();
            if(!r.next()) {
                return null;
            }
            int _id = r.getInt(1);
            User _author = UserRegistry.getUser(_id);
            int _userId = r.getInt(2);
            int _hotelId = r.getInt(3);
            Hotel _aboutHotel = HotelRegistry.getHotel(_hotelId); 
            int _stars = r.getInt(4);
            String _content = r.getString(5);
            LocalDate _leftAt = r.getDate(6).toLocalDate();
            Review result = new Review(_id, _userId, _hotelId, _stars, _content, _leftAt,  _author, _aboutHotel);
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
	
    public static ArrayList<Review> search(Integer id, Integer userId, Integer hotelId, Integer stars, 
			String content, LocalDate leftAt, User author, Hotel aboutHotel) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch(ClassNotFoundException e) {
            return null;
        }		

        String sqlSearchString = "SELECT * FROM ReviewRegistry WHERE ";
        boolean addOr = false;
        String[] paramStrings = {"userId", "hotelId", "stars", "content", "leftAt"};
        Object[] paramObs = {userId, hotelId, stars, content, leftAt};
        
        if(id != null) {
        	sqlSearchString += "id = ?";
        	addOr = true;
        }
        
        for (int i = 0; i < paramObs.length; i++) {
            if(paramObs[i] != null) {
                if(addOr) {
                    sqlSearchString += " OR " + paramStrings[i] + " = ?";
                } else {
                    sqlSearchString += paramStrings[i] + " = ?";
                    addOr = true;
                }
            }
        }
        
        ArrayList<Review> reviewL = new ArrayList<Review>();
        Connection conn = null;
        try {
        	conn = DriverManager.getConnection("jdbc:sqlite:data.db");
        	PreparedStatement pstmt = conn.prepareStatement(sqlSearchString);
        	pstmt.setInt(1, id);
        	pstmt.setInt(2, userId);
        	pstmt.setInt(3, hotelId);
        	pstmt.setInt(4, stars);
        	pstmt.setString(5, content);
        	pstmt.setDate(6, java.sql.Date.valueOf(leftAt));
        	ResultSet r = pstmt.executeQuery();
        	while(r.next()) {
        		int _id = r.getInt(1);
        		User _author = UserRegistry.getUser(_id);
        		int _userId = r.getInt(2);
        		int _hotelId = r.getInt(3);
        		Hotel _aboutHotel = HotelRegistry.getHotel(_hotelId);
        		int _stars = r.getInt(4);
        		String _content = r.getString(5);
        		LocalDate _leftAt = r.getDate(6).toLocalDate();
        		Review result = new Review(_id, _userId, _hotelId, _stars, _content, _leftAt,  _author, _aboutHotel);
        		reviewL.add(result);
        	}
        } catch(SQLException e) {
        	reviewL = null;
        } finally {
            try {
                if(conn != null) {
                    conn.close();
                }
            } catch(SQLException e) {
            	reviewL = null;
            } finally {
                return reviewL;
            }
        }		
    }

    public static boolean removeReview(int id) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch(ClassNotFoundException e) {
            return false;
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:data.db");
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM ReviewRegistry WHERE id = ?");
            pstmt.setInt(1, id);
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

    public static boolean addReview(Review review) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch(ClassNotFoundException e) {
            return false;
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:data.db");
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ReviewRegistry VALUES (?,?,?,?,?,?)");
            pstmt.setInt(1, review.getId());
            pstmt.setInt(2, review.getUserId());
            pstmt.setInt(3, review.getHotelId());
            pstmt.setInt(4, review.getStars());
            pstmt.setString(5, review.getContent());
            pstmt.setDate(6, java.sql.Date.valueOf(review.getLeftAt()));
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
