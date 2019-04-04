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

public class HotelRegistry {

    public static ArrayList<Hotel> searchHotels(HotelQuery hq, int maxReturn) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch(ClassNotFoundException e) {
            return null;
        }
        ArrayList<Hotel> res = new ArrayList<Hotel>();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:hotelData.db");
            ArrayList<String> req = new ArrayList<String>();
            ArrayList<Integer> vals = new ArrayList<Integer>();
            if(hq.mustHavePool) {
                req.add("hasPool = TRUE");
                vals.add(null);
            }
            if(hq.mustHaveGym) {
                req.add("hasGym = TRUE");
                vals.add(null);
            }
            if(hq.mustHaveClub) {
                req.add("hasClub = TRUE");
                vals.add(null);
            }
            if(hq.minStars > 0) {
                req.add("stars >= ?");
                vals.add(hq.minStars);
            }
            String qry = "SELECT * FROM HotelRegistry WHERE";
            StringJoiner spaceJoin = new StringJoiner(" ");
            StringJoiner andJoin = new StringJoiner(" AND ");
            for(int i = 0; i < req.size(); ++i) {
                andJoin.add(req.get(i));
            }
            if(hq.orderByName) {
                andJoin.add("ORDER BY name ASC");
            } else if(hq.orderByStars) {
                andJoin.add("ORDER BY stars DESC");
            } else if(hq.orderByPos) {
                andJoin.add("ORDER BY ABS(? - latiPos) + ABS(? - longPos) ASC");
            }
            spaceJoin.add(qry);
            spaceJoin.add(andJoin.toString());
            PreparedStatement pstmt = conn.prepareStatement(spaceJoin.toString());
            int pos = 1;
            for(int i = 0; i < req.size(); ++i) {
                if(vals.get(i) != null) {
                    pstmt.setInt(pos, vals.get(i));
                    pos++;
                }
            }
            if(!hq.orderByName && !hq.orderByStars && hq.orderByPos) {
                pstmt.setDouble(pos, hq.latiNear);
                pstmt.setDouble(pos + 1, hq.longNear);
            }
            ResultSet r = pstmt.executeQuery();
            while(r.next() && res.size() < maxReturn) {
                int _id = r.getInt(1);
                String _name = r.getString(2);
                int _postNum = r.getInt(3);
                double _latiPos = r.getDouble(4);
                double _longPos = r.getDouble(5);
                int _stars = r.getInt(6);
                boolean _hasPool = r.getBoolean(7);
                boolean _hasGym = r.getBoolean(8);
                boolean _hasClub = r.getBoolean(9);
                Hotel result = new Hotel(_id, _name, _postNum, _latiPos, _longPos, _stars,
                        _hasPool, _hasGym, _hasClub);
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

    public static Hotel getHotel(int id) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch(ClassNotFoundException e) {
            return null;
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:hotelData.db");
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM HotelRegistry WHERE id = ?");
            pstmt.setInt(1, id);
            ResultSet r = pstmt.executeQuery();
            if(!r.next()) {
                return null;
            }
            int _id = r.getInt(1);
            String _name = r.getString(2);
            int _postNum = r.getInt(3);
            double _latiPos = r.getDouble(4);
            double _longPos = r.getDouble(5);
            int _stars = r.getInt(6);
            boolean _hasPool = r.getBoolean(7);
            boolean _hasGym = r.getBoolean(8);
            boolean _hasClub = r.getBoolean(9);
            Hotel result = new Hotel(_id, _name, _postNum, _latiPos, _longPos, _stars,
                    _hasPool, _hasGym, _hasClub);
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

    public static boolean removeHotel(int id) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch(ClassNotFoundException e) {
            return false;
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:hotelData.db");
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM HotelRegistry WHERE id = ?");
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

    public static boolean addHotel(Hotel h) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch(ClassNotFoundException e) {
            return false;
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:hotelData.db");
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO RoomRegistry VALUES (?,?,?,?,?,?,?,?,?)");
            pstmt.setInt(1, h.getId());
            pstmt.setString(2, h.getName());
            pstmt.setInt(3, h.getPostNum());
            pstmt.setDouble(4, h.getLatiPos());
            pstmt.setDouble(5, h.getLongPos());
            pstmt.setInt(6, h.getStars());
            pstmt.setBoolean(7, h.getHasPool());
            pstmt.setBoolean(8, h.getHasGym());
            pstmt.setBoolean(9, h.getHasClub());
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
