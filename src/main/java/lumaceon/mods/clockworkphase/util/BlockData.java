package lumaceon.mods.clockworkphase.util;

public class BlockData
{
    public byte x, y, z;
    public byte meta;

    public BlockData(int x, int y, int z, int meta)
    {
        this.x = (byte)x;
        this.y = (byte)y;
        this.z = (byte)z;
        this.meta = (byte)meta;
    }
}
