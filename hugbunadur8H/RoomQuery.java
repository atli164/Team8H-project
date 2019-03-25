package hugbunadur8H;

public class RoomQuery {
    public Hotel limitedTo;
    public boolean limitToHotel;
    public int minBedNum;
    public int maxBedNum;
    public boolean mustHaveWifi;
    public boolean mustHaveBreakfast;
    public boolean mustHaveParking;
    public boolean mustBeSmoking;
    public boolean mustHaveView;
    public boolean mustHaveBath;
    public boolean mustHaveAC;
    public boolean mustHaveMinibar;
    public boolean mustHaveDailyCleaning;
    public int minCostPerNight;
    public int maxCostPerNight;

    public RoomQuery(Hotel _limitedTo, boolean _limitToHotel, int _minBedNum,
            int _maxBedNum, boolean _mustHaveWifi, boolean _mustHaveBreakfast,
            boolean _mustHaveParking, boolean _mustBeSmoking, boolean _mustHaveView,
            boolean _mustHaveBath, boolean _mustHaveAC, boolean _mustHaveMinibar,
            boolean _mustHaveDailyCleaning, int _minCostPerNight, int _maxCostPerNight) {
        limitedTo = _limitedTo;
        limitToHotel = _limitToHotel;
        minBedNum = _minBedNum;
        maxBedNum = _maxBedNum;
        mustHaveWifi = _mustHaveWifi;
        mustHaveBreakfast = _mustHaveBreakfast;
        mustHaveBath = _mustHaveBath;
        mustHaveAC = _mustHaveAC;
        mustHaveMinibar = _mustHaveMinibar;
        mustHaveDailyCleaning = _mustHaveDailyCleaning;
        minCostPerNight = _minCostPerNight;
        maxCostPerNight = _maxCostPerNight;
    }
}
