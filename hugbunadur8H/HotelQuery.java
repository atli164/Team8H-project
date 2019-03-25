package hugbunadur8H;

public class HotelQuery{
    public boolean mustHavePool;
    public boolean mustHaveGym;
    public boolean mustHaveClub;
    public int minStars;
    public boolean orderByName;
    public boolean orderByStars;
    public boolean orderByPos;
    public double latiNear;
    public double longNeat;

    public HotelQuery(boolean mustHavePool, boolean mustHaveGym, 
            boolean mustHaveClub, int minStars, 
            boolean orderByName,boolean orderByStars, 
            boolean orderByPos, double latiNear,double longNeat){
        this.mustHavePool = mustHavePool;
        this.mustHaveGym = mustHaveGym;
        this.mustHaveClub = mustHaveClub;
        this.minStars = minStars;
        this.orderByName = orderByName;
        this.orderByStars = orderByStars;
        this.orderByPos = orderByPos;
        this.latiNear = latiNear;
        this.longNeat = longNeat;
    }
}