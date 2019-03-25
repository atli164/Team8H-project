package hugbunadur8H;

import java.util.Date;

public class Review {

  private int id;
  private int userId;
  private int hotelId;
  private int stars;
  private String content;
  private Date leftAt;

  public int getId() {
    return id;
  }
  public int getUserId() {
    return userId;
  }
  public int getHotelId() {
    return hotelId;
  }
  public int getStars() {
    return stars;
  }
  public String getContent() {
    return content;
  }
  public Date getLeftAt() {
    return leftAt;
  }

  public Review(int id, int userId, int hotelId, int stars, String content, Date leftAt) {
    this.id = id;
    this.userId = userId;
    this.hotelId = hotelId;
    this.stars = stars;
    this.content = content;
    this.leftAt = leftAt;
  }


}
