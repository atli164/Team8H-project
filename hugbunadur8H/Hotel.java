package hugbunadur8H;

public class Hotel {
    private int id;
    private String name;
    private int postNum;
    private double latiPos;
    private double longPos;
    private int stars;
    private boolean hasPool;
    private boolean hasGym;
    private boolean hasClub;

    public String getName() {
        return name;
    }

    public int getPostNum() {
        return postNum;
    }

    public double getLatiPos() {
        return latiPos;
    }

    public double getLongPos() {
        return longPos;
    }

    public int getStars() {
        return stars;
    }

    public boolean getHasPool() {
        return hasPool;
    }

    public boolean getHasGym() {
        return hasGym;
    }

    public boolean getHasCub() {
        return hasClub;
    }

    public Hotel(int _id, String _name, int _postNum,
            double _latiPos, double _longPos, int _stars,
            boolean _hasPool, boolean _hasGym, boolean _hasClub) {
        id = _id;
        name = _name;
        postNum = _postNum;
        latiPos = _latiPos;
        longPos = _longPos;
        stars = _stars;
        hasPool = _hasPool;
        hasGym = _hasGym;
        hasClub = _hasClub;
    }
}
