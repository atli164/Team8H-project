package hugbunadur8H;
import java.time.LocalDate;

public class Booking{
    private int id;
    private int userId;
    private User bookerUser;
    private int roomId;
    private Room bookedRoom;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String requests;
    
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
    
    public LocalDate getFromDate(){
        return fromDate;
    }
    
    public LocalDate getToDate(){
        return toDate;
    }
    
    public String getRequests(){
        return requests;
    }

    public Booking(int id, int userId, int roomId, LocalDate fromDate, LocalDate toDate, String requests, User bookerUser, Room bookedRoom){
        this.id = id;
        this.userId = userId;
        this.roomId = roomId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.requests = requests;
        this.bookerUser = bookerUser;
        this.bookedRoom = bookedRoom;
    }
}
