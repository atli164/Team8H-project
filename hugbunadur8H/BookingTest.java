package hugbunadur8H;
import org.junit.Assert.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BookingTest{
    private BookingRegistry test;
    private Booking testBooking;
    private Booking testBooking1;
    private Room testRoom;
    private LocalDate date;
    @Before
    public void setUp() {
	date = LocalDate.of(Integer.parseInt("2018"), Integer.parseInt("1"), Integer.parseInt("23"));
    	testBooking = new Booking(1, 1, 1, date, date, "Nothing", null, null);
	testBooking1 = new Booking(2, 1, 1, date, date, "Nothing", null, null);
	testBooking2 = new Booking(3, 1, 1, date, date, "Nothing", null, null);
    	testRoom = new Room(null, 1, 0, 0, 0, 0, false, false, false, false, false, false, false, false, false, false, 0);
    }
    @After
    public void tearDown(){
    	testBooking = null;
	testBooking1 = null;
	testBooking2 = null;
    	testRoom = null;
    }
    @Test
    public void getBookingsTest() {
    	//Perform test to see if it adds new room
    	//to the bookings
    	 ArrayList<Booking> a = test.getBookings(date, date, testRoom);
    	 int oldSize = a.size();
    	 
    	 test.addBooking(testBooking2);
    	 
    	 ArrayList<Booking> b = test.getBookings(date, date, testRoom);
    	 assertTrue(oldSize < b.size());
    	 
    }
    @Test
    public void getBookingTest(){
    	//Making a test to the booking with
    	//the id 1
    	Booking test1 = test.getBooking(1);
    	assertTrue("True",test1 != null);
    	
    	// Making a test to see that there
    	// is no Booking with id -1
    	Booking test2 = test.getBooking(-1);
    	assertFalse("False",test2 != null);
    	
    	
    	String rettRequest = "Nothing";
    	Booking test3 = test.getBooking(1);
    	assertTrue(rettRequest.equals(test3.getRequests()));
    }
    @Test 
    public void removBookingTest(){
    	//Perform test to removeBooking
    	boolean b = test.removeBooking(1);
    	assertTrue(b);
    	
    }
    @Test 
    public void addBookingTest(){  
    	test.addBooking(testBooking);
	test.addBooking(testBooking1);
    	Booking b = test.getBooking(1);
    	
    	// Perform actions to be tested on the booking
    	assertEquals(testBooking.getId(), b.getId());
    	assertEquals(testBooking.getUserId(),b.getUserId());
    	assertEquals(testBooking.getRoomId(), b.getRoomId());
    	assertEquals(testBooking.getFromDate(), b.getFromDate());
    	assertEquals(testBooking.getToDate(), b.getToDate());
    	assertEquals(testBooking.getRequests(), b.getRequests());
    	
    }
}
