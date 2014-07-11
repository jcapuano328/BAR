package ica.BAR.Core;

/**
 * Created by jcapuano on 7/11/2014.
 */
public class FireResult {
    public FireResult(int lo, int hi, String result) {
        Low = lo;
        High = hi;
        Result = result;
    }

    public int Low;
    public int High;
    public String Result;

    public boolean isWithin(int i) {
        return i >= Low && i <= High;
    }
}
