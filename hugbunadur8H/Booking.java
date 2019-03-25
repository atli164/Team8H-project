package hugbunadur8H;
import java.util.Date;

public class Booking{
    private int id;
    private int userId;
    private User bookerUser;
    private int roomId;
    private Room bookedRoom;
    private Date fromDate;
    private Date toDate;
    private Date requests;
    
    public int getUserId(){
        return userId;
    }
    
    public User getBookerUser() {
        return bookerUser;
    }
    
    public int getroomId(){
        return roomId;
    }
    
    public Room getBookedRoom() {
        return bookedRoom;
    }
    
    public Date getFromDate(){
        return fromDate;
    }
    
    public Date getToDate(){
        return toDate;
    }
    
    public String getRequests(){
        return requests;
    }

    public Booking(int id, int roomId, Date fromDate, Date toDate, String requests, User bookerUser, Room bookedRoom){
        this.id = id;
        this.roomId = roomId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.requests = requests;
        this.bookerUser = bookerUser;
        this.bookedRoom = bookedRoom;
    }
}
