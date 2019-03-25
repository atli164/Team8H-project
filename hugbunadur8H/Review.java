package hugbunadur8H;

import java.time.LocalDate;

public class Review {

  private int id;
  private int userId;
  private User author;
  private int hotelId;
  private Hotel aboutHotel;
  private int stars;
  private String content;
  private LocalDate leftAt;

  public int getId() {
    return id;
  }
  
  public int getUserId() {
    return userId;
  }
  
  public User getAuthor() {
    return author;
  }
  
  public int getHotelId() {
    return hotelId;
  }
  
  public Hotel getAboutHotel() {
    return aboutHotel;
  }
  
  public int getStars() {
    return stars;
  }
  
  public String getContent() {
    return content;
  }
  
  public LocalDate getLeftAt() {
    return leftAt;
  }

  public Review(int id, int userId, int hotelId, int stars, String content, LocalDate leftAt, User author, Hotel aboutHotel) {
    this.id = id;
    this.userId = userId;
    this.hotelId = hotelId;
    this.stars = stars;
    this.content = content;
    this.leftAt = leftAt;
    this.author = author;
    this.aboutHotel = aboutHotel;
  }


}
