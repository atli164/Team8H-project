package hugbunadur8H;

import java.util.ArrayList;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class ReviewRegistry {
	
    public static User getReview(int id) {
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
        	while(r.next()) {
        		int _id = r.getInt(1);
        		User _author = UserRegistry.getUser(_id);
        		int _userId = r.getInt(2);
        		int _hotelId = r.getInt(3);
        		Hotel _aboutHotel = HotelRegistry.getHotel(_hotelId);
        		int _stars = r.getInt(4);
        		String _content = r.getString(5);
        		Date _leftAt = r.getObject(6, LocalDate.class);
        		Review result = new Review(_id, _userId, _hotelId, _stars, _content, _leftAt,  _author, _aboutHotel);
        	}
        } catch(SQLException e) {
            result = null;
        } finally {
            try {
                if(conn != null) {
                    conn.close();
                }
            } catch(SQLException e) {
                result = null;
            } finally {
                return result;
            }
        }		
	}
	
	public static ArrayList<Review> search(int id, int userId, int hotelId, int stars, 
			String content, LocalDate leftAt, User author, Hotel aboutHotel) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch(ClassNotFoundException e) {
            return null;
        }		

        String sqlSearchString = "SELECT * FROM ReviewRegistry WHERE ";
        boolean addOr = false;
        
        if(id != null) {
        	sqlSearchString += "id = ?";
        	addOr = true;
        }
        
        if(userId != null) {
        	if(addOr) {
        		sqlSearchString += " OR userId = ?";
        	} else {
        		sqlSearchString += "userId = ?";
        		addOr = true;
        	}
        }
        
        if(hotelId != null) {
        	if(addOr) {
        		sqlSearchString += " OR hotelId = ?";
        	} else {
        		sqlSearchString += "hotelId = ?";
        		addOr = true;
        	}
        }        

        if(stars != null) {
        	if(addOr) {
        		sqlSearchString += " OR stars = ?";
        	} else {
        		sqlSearchString += "stars = ?";
        		addOr = true;
        	}
        }        
        
        if(content != null) {
        	if(addOr) {
        		sqlSearchString += " OR content = ?";
        	} else {
        		sqlSearchString += "content = ?";
        		addOr = true;
        	}
        } 
        
        if(leftAt != null) {
        	if(addOr) {
        		sqlSearchString += " OR leftAt = ?";
        	} else {
        		sqlSearchString += "leftAt = ?";
        		addOr = true;
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
        		Date _leftAt = r.getObject(6, LocalDate.class);
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
            pstmt.setDate(6, java.sql.valueOf(review.getLeftAt()));
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