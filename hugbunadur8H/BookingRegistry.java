package hugbunadur8H;

import java.util.ArrayList;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class BookingRegistry {

    public static ArrayList<Booking> getBookings(LocalDate fromDate, LocalDate toDate, Room room) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch(ClassNotFoundException e) {
            return null;
        }
        ArrayList<Booking> res = new ArrayList<Booking>();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:hotelData.db");
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM BookingRegistry WHERE (fromDate " +
                    "<= ? OR toDate >= ?) AND roomId = ?");
            pstmt.setDate(1, java.sql.Date.valueOf(toDate));
            pstmt.setDate(2, java.sql.Date.valueOf(fromDate));
            pstmt.setInt(3, room.getId());
            ResultSet r = pstmt.executeQuery();
            while(r.next()) {
                int _id = r.getInt(1);
                int _userId = r.getInt(2);
                int _roomId = r.getInt(3);
                LocalDate _fromDate = r.getDate(4).toLocalDate();
                LocalDate _toDate = r.getDate(5).toLocalDate();
                String _requests = r.getString(6);
                User booker = UserRegistry.getUser(_userId);
                Room booked = RoomRegistry.getRoom(_roomId);
                Booking result = new Booking(_id, _userId, _roomId, _fromDate, _toDate, _requests, booker, booked);
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

    public static Booking getBooking(int id) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch(ClassNotFoundException e) {
            return null;
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:hotelData.db");
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM BookingRegistry WHERE id = ?");
            pstmt.setInt(1, id);
            ResultSet r = pstmt.executeQuery();
            if(!r.next()) {
                return null;
            }
            int _id = r.getInt(1);
            int _userId = r.getInt(2);
            int _roomId = r.getInt(3);
            LocalDate _fromDate = r.getDate(4).toLocalDate();
            LocalDate _toDate = r.getDate(5).toLocalDate();
            String _requests = r.getString(6);
            User booker = UserRegistry.getUser(_userId);
            Room booked = RoomRegistry.getRoom(_roomId);
            Booking result = new Booking(_id, _userId, _roomId, _fromDate, _toDate, _requests, booker, booked);
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

    public static Boolean isCurrentlyBooked(Room room) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch(ClassNotFoundException e) {
            return null;
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:hotelData.db");
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM BookingRegistry WHERE fromDate " +
                    "<= ? AND toDate >= ? AND roomId = ?");
            pstmt.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
            pstmt.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
            pstmt.setInt(3, room.getId());
            ResultSet r = pstmt.executeQuery();
            boolean ans = r.next();
            try {
                if(conn != null) {
                    conn.close();
                }
            } catch(SQLException e) {
                return null;
            }
            return ans;
        } catch(SQLException e) {
            return null;
        }
    }

    public static boolean removeBooking(int id) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch(ClassNotFoundException e) {
            return false;
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:hotelData.db");
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM BookingRegistry WHERE id = ?");
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

    public static boolean addBooking(Booking book) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch(ClassNotFoundException e) {
            return false;
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:hotelData.db");
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO BookingRegistry VALUES (?,?,?,?,?,?)");
            pstmt.setInt(1, book.getId());
            pstmt.setInt(2, book.getUserId());
            pstmt.setInt(3, book.getRoomId());
            pstmt.setDate(4, java.sql.Date.valueOf(book.getFromDate()));
            pstmt.setDate(5, java.sql.Date.valueOf(book.getToDate()));
            pstmt.setString(6, book.getRequests());
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

    public static Integer getNewId() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch(ClassNotFoundException e) {
            return null;
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:hotelData.db");
            PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(id) from BookingRegistry");
            ResultSet r = pstmt.executeQuery();
            Integer res;
            if(!r.next()) {
                res = null;
            } else {
                res = r.getInt(1) + 1;
            }
            try {
                if(conn != null) {
                    conn.close();
                }
            } catch(SQLException e) {
                return null;
            }
            return res;
        } catch(SQLException e) {
            return null;
        }
    }
}
