package ica.BAR.Core;

public class MoraleLevel {
    private String name;
    private int high;
    private int low;

    public MoraleLevel() {
        name = "";
        high = 0;
        low = 0;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public int getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }
}

