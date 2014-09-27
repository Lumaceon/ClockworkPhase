package lumaceon.mods.clockworkphase.util;

public class Range2I
{
    public int low, high;

    public Range2I(int low, int high)
    {
        this.low = low;
        this.high = high;
    }

    public boolean isValueInclusivelyWithinRange(int value)
    {
        return value <= high && value >= low;
    }
}
