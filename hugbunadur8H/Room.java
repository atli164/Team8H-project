package hugbunadur8H;

public class Room {
    private Hotel belongsTo;
    private int id;
    private int hotelId;
    private int singleBeds;
    private int doubleBeds;
    private int foldedBeds;
    private boolean hasWifi;
    private boolean freeWifi;
    private boolean hasBreakfast;
    private boolean breakfastPrePaid;
    private boolean hasParking;
    private boolean isSmoking;
    private boolean withAView;
    private boolean hasBath;
    private boolean hasAC;
    private boolean hasMinibar;
    private boolean dailyCleaning;
    private int costPerNight;

    public Hotel getBelongsTo() {
        return belongsTo;
    }

    public int getSingleBeds() {
        return singleBeds;
    }

    public int getDoubleBeds() {
        return doubleBeds;
    }

    public int getFoldedBeds() {
        return foldedBeds;
    }

    public boolean getHasWifi() {
        return hasWifi;
    }

    public boolean freeWifi() {
        return freeWifi;
    }

    public boolean getHasBreakfast() {
        return hasBreakfast;
    }

    public boolean getBreakfastPrePaid() {
        return breakfastPrePaid;
    }

    public boolean getHasParking() {
        return hasParking;
    }

    public boolean getIsSmoking() {
        return isSmoking;
    }

    public boolean getWithAView() {
        return withAView;
    }

    public boolean getHasBath() {
        return hasBath;
    }

    public boolean getHasAC() {
        return hasAC;
    }

    public boolean getHasMinibar() {
        return hasMinibar;
    }

    public boolean getDailyCleaning() {
        return dailyCleaning;
    }

    public int getCostPerNight() {
        return costPerNight;
    }

    public Room(Hotel _belongsTo, int _id, int _hotelId, int _singleBeds, int _doubleBeds,
            int _foldedBeds, boolean _hasWifi, boolean _freeWifi,
            boolean _hasBreakfast, boolean _breakfastPrePaid,
            boolean _isSmoking, boolean _withAView, boolean _hasBath,
            boolean _hasAC, boolean _hasMinibar, boolean _dailyCleaning,
            int _costPerNight) {
        belongsTo = _belongsTo;
        id = _id;
        hotelId = _hotelId;
        singleBeds = _singleBeds;
        doubleBeds = _doubleBeds;
        foldedBeds = _foldedBeds;
        hasWifi = _hasWifi;
        freeWifi = _freeWifi;
        hasBreakfast = _hasBreakfast;
        breakfastPrePaid = _breakfastPrePaid;
        isSmoking = _isSmoking;
        withAView = _withAView;
        hasBath = _hasBath;
        hasAC = _hasAC;
        hasMinibar = _hasMinibar;
        dailyCleaning = _dailyCleaning;
        costPerNight = _costPerNight;
    }
}
