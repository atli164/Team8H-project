package hugbunadur8H;

import java.util.ArrayList;
import java.util.StringJoiner;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class RoomRegistry {

    public static ArrayList<Room> searchRooms(RoomQuery rq, int maxReturn) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch(ClassNotFoundException e) {
            return null;
        }
        ArrayList<Room> res = new ArrayList<Room>();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:hotelData.db");
            ArrayList<String> req = new ArrayList<String>();
            ArrayList<Integer> vals = new ArrayList<Integer>();
            if(rq.limitToHotel) {
                req.add("hotelId = ?");
                vals.add(rq.limitedTo.getId());
            }
            if(rq.minBedNum > 0) {
                req.add("(singleBeds + doubleBeds + doubleBeds + foldedBeds) >= ?");
                vals.add(rq.minBedNum);
            }
            if(rq.maxBedNum < 100) {
                req.add("(singleBeds + doubleBeds + doubleBeds + foldedBeds) <= ?");
                vals.add(rq.maxBedNum);
            }
            if(rq.mustHaveWifi) {
                req.add("hasWifi = 'true'");
                vals.add(null);
            }
            if(rq.mustHaveBreakfast) {
                req.add("hasBreakfast = 'true'");
                vals.add(null);
            }
            if(rq.mustHaveParking) {
                req.add("hasParking = 'true'");
                vals.add(null);
            }
            if(rq.mustBeSmoking) {
                req.add("isSmoking = 'true'");
                vals.add(null);
            }
            if(rq.mustHaveView) {
                req.add("withAView = 'true'");
                vals.add(null);
            }
            if(rq.mustHaveBath) {
                req.add("hasBath = 'true'");
                vals.add(null);
            }
            if(rq.mustHaveAC) {
                req.add("hasAC = 'true'");
                vals.add(null);
            }
            if(rq.mustHaveMinibar) {
                req.add("hasMinibar = 'true'");
                vals.add(null);
            }
            if(rq.mustHaveDailyCleaning) {
                req.add("dailyCleaning = 'true'");
                vals.add(null);
            }
            if(rq.minCostPerNight > 0) {
                req.add("costPerNight >= ?");
                vals.add(rq.minCostPerNight);
            }
            if(rq.maxCostPerNight < 1000000000) {
                req.add("costPerNight <= ?");
                vals.add(rq.maxCostPerNight);
            }
            String qry = "SELECT * FROM RoomRegistry";
            StringJoiner spaceJoin = new StringJoiner(" ");
            StringJoiner andJoin = new StringJoiner(" AND ");
            for(int i = 0; i < req.size(); ++i) {
                andJoin.add(req.get(i));
            }
            spaceJoin.add(qry);
            if(!andJoin.toString().isEmpty()) {
                spaceJoin.add("WHERE");
                spaceJoin.add(andJoin.toString());
            }
            PreparedStatement pstmt = conn.prepareStatement(spaceJoin.toString());
            int pos = 1;
            for(int i = 0; i < req.size(); ++i) {
                if(vals.get(i) != null) {
                    pstmt.setInt(pos, vals.get(i));
                    pos++;
                }
            }
            ResultSet r = pstmt.executeQuery();
            while(r.next() && res.size() < maxReturn) {
                int _id = r.getInt(1);
                int _hotelId = r.getInt(2);
                int _singleBeds = r.getInt(3);
                int _doubleBeds = r.getInt(4);
                int _foldedBeds = r.getInt(5);
                boolean _hasWifi = r.getString(6).equals("true");
                boolean _freeWifi = r.getString(7).equals("true");
                boolean _hasBreakfast = r.getString(8).equals("true");
                boolean _breakfastPrePaid = r.getString(9).equals("true");
                boolean _hasParking = r.getString(10).equals("true");
                boolean _isSmoking = r.getString(11).equals("true");
                boolean _withAView = r.getString(12).equals("true");
                boolean _hasBath = r.getString(13).equals("true");
                boolean _hasAC = r.getString(14).equals("true");
                boolean _hasMinibar = r.getString(15).equals("true");
                boolean _dailyCleaning = r.getString(16).equals("true");
                int _costPerNight = r.getInt(17);
                Hotel _h = HotelRegistry.getHotel(_hotelId);
                Room result = new Room(_h, _id, _hotelId, _singleBeds, _doubleBeds, _foldedBeds,
                        _hasWifi, _freeWifi, _hasBreakfast, _breakfastPrePaid, _isSmoking,
                        _withAView, _hasBath, _hasAC, _hasMinibar, _dailyCleaning, _costPerNight);
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

    public static Room getRoom(int id) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch(ClassNotFoundException e) {
            return null;
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:hotelData.db");
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM RoomRegistry WHERE id = ?");
            pstmt.setInt(1, id);
            ResultSet r = pstmt.executeQuery();
            if(!r.next()) {
                return null;
            }
            int _id = r.getInt(1);
            int _hotelId = r.getInt(2);
            int _singleBeds = r.getInt(3);
            int _doubleBeds = r.getInt(4);
            int _foldedBeds = r.getInt(5);
            boolean _hasWifi = r.getString(6).equals("true");
            boolean _freeWifi = r.getString(7).equals("true");
            boolean _hasBreakfast = r.getString(8).equals("true");
            boolean _breakfastPrePaid = r.getString(9).equals("true");
            boolean _hasParking = r.getString(10).equals("true");
            boolean _isSmoking = r.getString(11).equals("true");
            boolean _withAView = r.getString(12).equals("true");
            boolean _hasBath = r.getString(13).equals("true");
            boolean _hasAC = r.getString(14).equals("true");
            boolean _hasMinibar = r.getString(15).equals("true");
            boolean _dailyCleaning = r.getString(16).equals("true");
            int _costPerNight = r.getInt(17);
            Hotel _h = HotelRegistry.getHotel(_hotelId);
            Room result = new Room(_h, _id, _hotelId, _singleBeds, _doubleBeds, _foldedBeds,
                    _hasWifi, _freeWifi, _hasBreakfast, _breakfastPrePaid, _isSmoking,
                    _withAView, _hasBath, _hasAC, _hasMinibar, _dailyCleaning, _costPerNight);
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

    public static boolean removeRoom(int id) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch(ClassNotFoundException e) {
            return false;
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:hotelData.db");
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM RoomRegistry WHERE id = ?");
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

    public static boolean addRoom(Room rm) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch(ClassNotFoundException e) {
            return false;
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:hotelData.db");
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO RoomRegistry VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pstmt.setInt(1, rm.getId());
            pstmt.setInt(2, rm.getHotelId());
            pstmt.setInt(3, rm.getSingleBeds());
            pstmt.setInt(4, rm.getDoubleBeds());
            pstmt.setInt(5, rm.getFoldedBeds());
            pstmt.setBoolean(6, rm.getHasWifi());
            pstmt.setBoolean(7, rm.getFreeWifi());
            pstmt.setBoolean(8, rm.getHasBreakfast());
            pstmt.setBoolean(9, rm.getBreakfastPrePaid());
            pstmt.setBoolean(10, rm.getHasParking());
            pstmt.setBoolean(11, rm.getIsSmoking());
            pstmt.setBoolean(12, rm.getWithAView());
            pstmt.setBoolean(13, rm.getHasBath());
            pstmt.setBoolean(14, rm.getHasAC());
            pstmt.setBoolean(15, rm.getHasMinibar());
            pstmt.setBoolean(16, rm.getDailyCleaning());
            pstmt.setInt(17, rm.getCostPerNight());
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
