package hugbunadur8H;
import java.util.Date;

public class Booking{
    private int id;
    private int userId;
    private int roomId;
    private Date start_date;
    private Date end_date;
    private Date requests;
    
    public int getUserId(){
        return userId;
    }
    public int getroomId(){
        return roomId;
    }
    public Date getStart_date(){
        return start_date;
    }
    public Date getEnd_date(){
        return end_date;
    }
    public String getRequests(){
        return requests;
    }

    public Booking(int id, int roomId, Date start_date, Date end_date, String requests){
        this.id = id;
        this.roomId = roomId;
        this.start_date = start_date;
        this.end_date = end_date;
        this.requests = requests;
    }
}