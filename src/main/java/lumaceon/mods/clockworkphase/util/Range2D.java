package lumaceon.mods.clockworkphase.util;

public class Range2D
{
    public double low, high;

    public Range2D(double low, double high)
    {
        this.low = low;
        this.high = high;
    }

    public boolean isValueInclusivelyWithinRange(double value)
    {
        return value <= high && value >= low;
    }
}
