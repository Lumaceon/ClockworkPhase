package lumaceon.mods.clockworkphase.client.render;

import java.util.Random;

public class RenderDrone
{
    private Random rand;

    public float offsetX, offsetY, offsetZ, prevOffsetX, prevOffsetY, prevOffsetZ;
    public int timeSinceLastUpdate;
    public int timeNextUpdate;

    public RenderDrone(Random random)
    {
        this.rand = random;
        offsetX = (rand.nextFloat() - 0.5F) * 1.6F;
        offsetY = (rand.nextFloat() - 0.5F) * 1.6F;
        offsetZ = (rand.nextFloat() - 0.5F) * 1.6F;
        timeNextUpdate = rand.nextInt(61) + 20;
        timeSinceLastUpdate = 0;
        prevOffsetX = offsetX;
        prevOffsetY = offsetY;
        prevOffsetZ = offsetZ;
    }

    public void move()
    {
        prevOffsetX = offsetX;
        prevOffsetY = offsetY;
        prevOffsetZ = offsetZ;
        offsetX = (rand.nextFloat() - 0.5F) * 1.6F;
        offsetY = (rand.nextFloat() - 0.5F) * 1.6F;
        offsetZ = (rand.nextFloat() - 0.5F) * 1.6F;
        timeNextUpdate = rand.nextInt(61) + 10;
        timeSinceLastUpdate = 0;
    }
}
