package hugbunadur8H;

import java.util.StringJoiner;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;

public class CLI {
    static String bookingToString(Booking b) {
        String p1 = String.valueOf(b.getRoomId());
        String p2 = b.getFromDate().toString();
        String p3 = b.getToDate().toString();
        String p4 = b.getBookerUser().getUserName();
        return "Room " + p1 + " booked from " + p2 + " to " + p3 + " by " + p4 + ".";
    }

    static String hotelToString(Hotel h, boolean d) {
        String res = "Hotel no. " + String.valueOf(h.getId()) + ", " + h.getName() + " ";
        for(int i = 0; i < h.getStars(); ++i) {
            res = res + "*";
        }
        if(d) {
            res += "\nLocation: " + String.valueOf(h.getLatiPos()) + " " + String.valueOf(h.getLongPos());
            if(h.getHasPool() || h.getHasGym() || h.getHasClub()) {
                StringJoiner jp = new StringJoiner(", ");
                if(h.getHasPool()) {
                    jp.add("a pool");
                }
                if(h.getHasGym()) {
                    jp.add("a gym");
                }
                if(h.getHasClub()) {
                    jp.add("a club");
                }
                res = res + "\nIncludes access to: " + jp.toString();
            }
        }
        return res;
    }

    static String reviewToString(Review r) {
        String p1 = r.getAuthor().getUserName();
        String p2 = r.getLeftAt().toString();
        String p3 = "";
        for(int i = 0; i < r.getStars(); ++i) {
            p3 = p3 + "*";
        }
        String p4 = r.getContent();
        return p1 + " " + p2 + " " + p3 + "\n" + p4;
    }

    static String roomToString(Room r) {
        String p1 = r.getBelongsTo().getName() + " room no. " + String.valueOf(r.getId()) + "\n";
        String p2 = String.valueOf(r.getSingleBeds()) + " single bed(s), " + String.valueOf(r.getDoubleBeds());
        String p3 = " double bed(s) and " + String.valueOf(r.getFoldedBeds()) + " folded bed(s)\n";
        StringJoiner jp = new StringJoiner(", ");
        String p4 = "For " + String.valueOf(r.getCostPerNight()) + "isk per night.";
        if(r.getFreeWifi()) {
            jp.add("free wifi");
        } else if(r.getHasWifi()) {
            jp.add("wifi");
        }
        if(r.getBreakfastPrePaid()) {
            jp.add("included breakfast");
        } else if(r.getHasBreakfast()) {
            jp.add("breakfast");
        }
        if(r.getHasParking()) {
            jp.add("parking");
        }
        if(r.getIsSmoking()) {
            jp.add("smoking");
        }
        if(r.getWithAView()) {
            jp.add("a view");
        }
        if(r.getHasBath()) {
            jp.add("a personal bath");
        }
        if(r.getHasAC()) {
            jp.add("AC");
        }
        if(r.getHasMinibar()) {
            jp.add("a minibar");
        }
        if(r.getDailyCleaning()) {
            jp.add("daily cleaning");
        }
        if(jp.toString() != "") {
            p4 = "Includes: " + jp.toString() + "\n" + p4;
        }
        return p1 + p2 + p3 + p4;
    }

    static String userToString(User u) {
        return u.getUserName();
    }

    public static void main(String args[]) {
        System.out.println("Enter a command and then press enter to execute it. To show available commands type \"c\" " + 
                "and press enter, to quit type \"q\" and press enter.");
        Scanner s = new Scanner(System.in);
        while(true) {
            String t = s.nextLine();
            if(t.isEmpty()) {
                continue;
            }
            if(t.charAt(0) == 'c') {
                System.out.println("c : Show a list of available commands.");
                System.out.println("h : Enter the interface to search for hotels.");
                System.out.println("r : Enter the interface to search for rooms.");
                System.out.println("b : Enter the interface to book a room.");
                System.out.println("q : Exit the program.");
            } else if(t.charAt(0) == 'q') {
                System.exit(0);
            } else if(t.charAt(0) == 'r') {
                System.out.println("When asked with a yes/no question, please reply with exactly \"y\" or \"n\".");
                System.out.println("Search only for rooms at a specific hotel?");
                t = s.nextLine();
                while(t.isEmpty()) {
                    t = s.nextLine();
                }
                boolean _limitToHotel = t.charAt(0) == 'y';
                Hotel _limitedTo = null;
                if(_limitToHotel) {
                    System.out.println("Enter the id of the hotel you wish to restrain the search to.");
                    int limitId;
                    while(true) {
                        t = s.nextLine();
                        try {
                            limitId = Integer.parseInt(t);
                            _limitedTo = HotelRegistry.getHotel(limitId);
                            if(_limitedTo == null) {
                                System.out.println("Hotel not found. Try again.");
                            } else {
                                break;
                            }
                        } catch(NumberFormatException e) {
                            t = s.nextLine();
                        }
                    }
                }
                int _minBedNum, _maxBedNum;
                System.out.println("Constrain the number of beds in searched rooms?");
                t = s.nextLine();
                while(t.isEmpty()) {
                    t = s.nextLine();
                }
                boolean constrainBeds = t.charAt(0) == 'y';
                if(constrainBeds) {
                    System.out.println("Please enter the minimum number of beds.");
                    t = s.nextLine();
                    while(true) {
                        try {
                            _minBedNum = Integer.parseInt(t);
                            break;
                        } catch(NumberFormatException e) {
                            t = s.nextLine();
                        }
                    }
                    System.out.println("Please enter the maximum number of beds.");
                    t = s.nextLine();
                    while(true) {
                        try {
                            _maxBedNum = Integer.parseInt(t);
                            break;
                        } catch(NumberFormatException e) {
                            t = s.nextLine();
                        }
                    }
                } else {
                    _minBedNum = 0;
                    _maxBedNum = 1000;
                }
                System.out.println("Limit search to rooms with wifi?");
                t = s.nextLine();
                while(t.isEmpty()) {
                    t = s.nextLine();
                }
                boolean _mustHaveWifi = t.charAt(0) == 'y';
                System.out.println("Limit search to rooms with access to breakfast?");
                t = s.nextLine();
                while(t.isEmpty()) {
                    t = s.nextLine();
                }
                boolean _mustHaveBreakfast = t.charAt(0) == 'y';
                System.out.println("Limit search to rooms with parking access?");
                t = s.nextLine();
                while(t.isEmpty()) {
                    t = s.nextLine();
                }
                boolean _mustHaveParking = t.charAt(0) == 'y';
                System.out.println("Limit search to rooms with smoking allowed?");
                t = s.nextLine();
                while(t.isEmpty()) {
                    t = s.nextLine();
                }
                boolean _mustBeSmoking = t.charAt(0) == 'y';
                System.out.println("Limit search to rooms with a view?");
                t = s.nextLine();
                while(t.isEmpty()) {
                    t = s.nextLine();
                }
                boolean _mustHaveView = t.charAt(0) == 'y';
                System.out.println("Limit search to rooms with a bath?");
                t = s.nextLine();
                while(t.isEmpty()) {
                    t = s.nextLine();
                }
                boolean _mustHaveBath = t.charAt(0) == 'y';
                System.out.println("Limit search to rooms with AC?");
                t = s.nextLine();
                while(t.isEmpty()) {
                    t = s.nextLine();
                }
                boolean _mustHaveAC = t.charAt(0) == 'y';
                System.out.println("Limit search to rooms with a minibar?");
                t = s.nextLine();
                while(t.isEmpty()) {
                    t = s.nextLine();
                }
                boolean _mustHaveMinibar = t.charAt(0) == 'y';
                System.out.println("Limit search to rooms with daily cleaning?");
                t = s.nextLine();
                while(t.isEmpty()) {
                    t = s.nextLine();
                }
                boolean _mustHaveDailyCleaning = t.charAt(0) == 'y';
                int _minCostPerNight, _maxCostPerNight;
                System.out.println("Constrain the cost of a night's stay in the searched rooms?");
                t = s.nextLine();
                while(t.isEmpty()) {
                    t = s.nextLine();
                }
                boolean constrainPrice = t.charAt(0) == 'y';
                if(constrainPrice) {
                    System.out.println("Please enter the minimum cost of a night's stay.");
                    t = s.nextLine();
                    while(true) {
                        try {
                            _minCostPerNight = Integer.parseInt(t);
                            break;
                        } catch(NumberFormatException e) {
                            t = s.nextLine();
                        }
                    }
                    System.out.println("Please enter the maximum cost of a night's stay.");
                    t = s.nextLine();
                    while(true) {
                        try {
                            _maxCostPerNight = Integer.parseInt(t);
                            break;
                        } catch(NumberFormatException e) {
                            t = s.nextLine();
                        }
                    }
                } else {
                    _minCostPerNight = 0;
                    _maxCostPerNight = 1000000000;
                }
                RoomQuery rq = new RoomQuery(_limitedTo, _limitToHotel, _minBedNum, _maxBedNum, _mustHaveWifi,
                        _mustHaveBreakfast, _mustHaveParking, _mustBeSmoking, _mustHaveView, _mustHaveBath, _mustHaveAC,
                        _mustHaveMinibar, _mustHaveDailyCleaning, _minCostPerNight, _maxCostPerNight);
                ArrayList<Room> results = RoomRegistry.searchRooms(rq, 100);
                int cnt = 0;
                for(Room r : results) {
                    if(BookingRegistry.isCurrentlyBooked(r)) {
                        continue;
                    }
                    cnt++;
                    System.out.println(roomToString(r));
                    System.out.println("");
                    if(cnt == 5) {
                        break;
                    }
                }
                if(results.isEmpty()) {
                    System.out.println("No resuts found.");
                }
            } else if(t.charAt(0) == 'h') {
                System.out.println("When asked with a yes/no question, please reply with exactly \"y\" or \"n\".");
                System.out.println("Search only for hotels with pools?");
                t = s.nextLine();
                while(t.isEmpty()) {
                    t = s.nextLine();
                }
                boolean _mustHavePool = t.charAt(0) == 'y';
                System.out.println("Search only for hotels with gyms?");
                t = s.nextLine();
                while(t.isEmpty()) {
                    t = s.nextLine();
                }
                boolean _mustHaveGym = t.charAt(0) == 'y';
                System.out.println("Search only for hotels with clubs?");
                t = s.nextLine();
                while(t.isEmpty()) {
                    t = s.nextLine();
                }
                boolean _mustHaveClub = t.charAt(0) == 'y';
                int _minStars = 0;
                System.out.println("Search only for hotels with at least some number of stars?");
                t = s.nextLine();
                while(t.isEmpty()) {
                    t = s.nextLine();
                }
                if(t.charAt(0) == 'y') {
                    System.out.println("Enter the minimum number of stars (1, 2, 3, 4, 5)");
                    t = s.nextLine();
                    while(t.isEmpty()) {
                        t = s.nextLine();
                    }
                    if(t.charAt(0) == '5') {
                        _minStars = 5;
                    } else if(t.charAt(0) == '4') {
                        _minStars = 4;
                    } else if(t.charAt(0) == '3') {
                        _minStars = 3;
                    } else if(t.charAt(0) == '2') {
                        _minStars = 2;
                    } else {
                        _minStars = 1;
                    }
                }
                System.out.println("Order hotels by name?");
                t = s.nextLine();
                while(t.isEmpty()) {
                    t = s.nextLine();
                }
                boolean _orderByName = t.charAt(0) == 'y';
                boolean _orderByStars = false;
                boolean _orderByPos = false;
                double _latiNear = 0;
                double _longNear = 0;
                if(!_orderByName) {
                    System.out.println("Order hotels by their number of stars?");
                    t = s.nextLine();
                    while(t.isEmpty()) {
                        t = s.nextLine();
                    }
                    _orderByStars = t.charAt(0) == 'y';
                    if(!_orderByStars) {
                        _orderByPos = true;
                        System.out.println("Ordering by location. Please enter latitude to search near");
                        t = s.nextLine();
                        while(true) {
                            try {
                                _latiNear = Double.parseDouble(t);
                                break;
                            } catch(NumberFormatException e) {
                                t = s.nextLine();
                            }
                        }
                        System.out.println("Please enter longditude to search near");
                        t = s.nextLine();
                        while(true) {
                            try {
                                _longNear = Double.parseDouble(t);
                                break;
                            } catch(NumberFormatException e) {
                                t = s.nextLine();
                            }
                        }
                    }
                }
                HotelQuery hq = new HotelQuery(_mustHavePool, _mustHaveGym, _mustHaveClub,
                        _minStars, _orderByName, _orderByStars, _orderByPos,
                        _latiNear, _longNear);
                ArrayList<Hotel> results = HotelRegistry.searchHotels(hq, 5);
                for(Hotel h : results) {
                    System.out.println(hotelToString(h, true));
                    System.out.println("");
                }
                if(results.isEmpty()) {
                    System.out.println("No resuts found.");
                }
            } else if(t.charAt(0) == 'b') {
                int bookedId;
                Room toBook;
                System.out.println("Enter the id of the room you wish to book.");
                while(true) {
                    t = s.nextLine();
                    try {
                        bookedId = Integer.parseInt(t);
                        toBook = RoomRegistry.getRoom(bookedId);
                        if(toBook == null) {
                            System.out.println("Room not found. Try again.");
                            t = s.nextLine();
                        } else {
                            break;
                        }
                    } catch(NumberFormatException e) {
                        t = s.nextLine();
                    }
                }
                int startYear;
                System.out.println("Please enter the year of the first day of your visit.");
                t = s.nextLine();
                while(true) {
                    try {
                        startYear = Integer.parseInt(t);
                        if(startYear < 2000 || startYear > 3000) {
                            System.out.println("That is not a valid year. Please try again.");
                            t = s.nextLine();
                        } else {
                            break;
                        }
                    } catch(NumberFormatException e) {
                        t = s.nextLine();
                    }
                }
                int startMonth;
                System.out.println("Please enter the month of the first day of your visit (1, 2, ..., 12).");
                t = s.nextLine();
                while(true) {
                    try {
                        startMonth = Integer.parseInt(t);
                        if(startMonth < 1 || startMonth > 12) {
                            System.out.println("That is not a valid month. Please try again.");
                            t = s.nextLine();
                        } else {
                            break;
                        }
                    } catch(NumberFormatException e) {
                        t = s.nextLine();
                    }
                }
                int startDay;
                System.out.println("Please enter the day of the month of the first day of your visit (1, 2, ..., 31).");
                t = s.nextLine();
                while(true) {
                    try {
                        startDay = Integer.parseInt(t);
                        if(startDay < 1 || startDay > LocalDate.of(startYear, startMonth, 1).lengthOfMonth()) {
                            System.out.println("That is not a valid day of your entered month. Please try again.");
                            t = s.nextLine();
                        } else {
                            break;
                        }
                    } catch(NumberFormatException e) {
                        t = s.nextLine();
                    }
                }
                LocalDate startDate = LocalDate.of(startYear, startMonth, startDay);
                int endYear;
                System.out.println("Please enter the year of the last date of your visit.");
                t = s.nextLine();
                while(true) {
                    try {
                        endYear = Integer.parseInt(t);
                        if(endYear < 2000 || endYear > 3000) {
                            System.out.println("That is not a valid year. Please try again.");
                            t = s.nextLine();
                        } else {
                            break;
                        }
                    } catch(NumberFormatException e) {
                        t = s.nextLine();
                    }
                }
                int endMonth;
                System.out.println("Please enter the month of the last day of your visit (1, 2, ..., 12).");
                t = s.nextLine();
                while(true) {
                    try {
                        endMonth = Integer.parseInt(t);
                        if(endMonth < 1 || endMonth > 12) {
                            System.out.println("That is not a valid month. Please try again.");
                            t = s.nextLine();
                        } else {
                            break;
                        }
                    } catch(NumberFormatException e) {
                        t = s.nextLine();
                    }
                }
                int endDay;
                System.out.println("Please enter the day of the month of the last day of your visit (1, 2, ..., 31).");
                t = s.nextLine();
                while(true) {
                    try {
                        endDay = Integer.parseInt(t);
                        if(endDay < 1 || endDay > LocalDate.of(endYear, endMonth, 1).lengthOfMonth()) {
                            System.out.println("That is not a valid day of your entered month. Please try again.");
                            t = s.nextLine();
                        } else {
                            break;
                        }
                    } catch(NumberFormatException e) {
                        t = s.nextLine();
                    }
                }
                LocalDate endDate = LocalDate.of(endYear, endMonth, endDay);
                if(endDate.compareTo(startDate) < 0) {
                    System.out.println("Start date can not be after end date.");
                } else if(startDate.compareTo(LocalDate.now()) < 0) {
                    System.out.println("Can not book rooms into the past.");
                } else {
                    ArrayList<Booking> other = BookingRegistry.getBookings(startDate, endDate, toBook);
                    if(!other.isEmpty()) {
                        System.out.println("Sorry, but your booking overlaps an existing booking. Please try a different room or time frame.");
                    } else {
                        // Harð kóða user fyrst við ætlum ekki að sýna neitt af því
                        int userId = 1;
                        User u = UserRegistry.getUser(userId);
                        System.out.println("If you have any special requests, please enter them now and they will be forwarded.");
                        String req = s.nextLine();
                        int nwId = BookingRegistry.getNewId();
                        Booking nwBook = new Booking(nwId, userId, toBook.getId(), startDate, endDate, req, u, toBook);
                        if(BookingRegistry.addBooking(nwBook)) {
                            System.out.println("Booking added.");
                        } else {
                            System.out.println("Booking addition failed.");
                        }
                    }
                }
            } else {
                System.out.println("Unrecognized command");
            }
        }
    }
}
