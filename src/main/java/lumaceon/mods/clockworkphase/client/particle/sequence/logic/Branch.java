package lumaceon.mods.clockworkphase.client.particle.sequence.logic;

import java.util.Random;

public class Branch
{
    public Random random;

    public int timer;

    public int startTimer;

    public double[] currentLocation = new double[3];

    public boolean[] orientation = new boolean[3];

    public float particleScale;

    public int length;

    public boolean isMain;

    public Branch(Random random, int timer, int startingTimer, double xLoc, double yLoc, double zLoc, boolean isMain)
    {
        this.timer = timer;

        this.startTimer = startingTimer;

        currentLocation[0] = xLoc;
        currentLocation[1] = yLoc;
        currentLocation[2] = zLoc;

        orientation[0] = random.nextBoolean();
        orientation[1] = isMain;
        orientation[2] = random.nextBoolean();

        this.isMain = isMain;

        particleScale = 1.0F;
    }

    /**
     * Method used for expanding the branch, does not actually create a particle, just updates location and such.
     * @return True if branch should create multiples, false if it should not.
     */
    public boolean expandBranch()
    {
        particleScale *= 0.9F;

        for(byte n = 0; n <= 2; n++)
        {
            //Special case for main branches.
            if(isMain)
            {
                //Special Y case, gains height as timer increases.
                if(n == 1)
                {
                    float difference = ( ((startTimer * 2) / (timer + startTimer)) - 1 ) * ( (random.nextInt(50) + 50) / 100 );

                    if(orientation[n])
                    {
                        currentLocation[n] = currentLocation[n] + difference;
                    }
                    else
                    {
                        currentLocation[n] = currentLocation[n] + difference;
                    }
                }
                else //The opposite applies to both other axis.
                {
                    float difference = ((timer + startTimer) / (startTimer * 2)) * ( (random.nextInt(50) + 50) / 100 );
                    if(orientation[n])
                    {
                        currentLocation[n] = currentLocation[n] + difference;
                    }
                    else
                    {
                        currentLocation[n] = currentLocation[n] - difference;
                    }
                }
            }
            //Not a main branch, ignore special scaling and shorten the expansion.
            if(orientation[n])
            {
                currentLocation[n] = currentLocation[n] + (random.nextFloat() * 0.35F);
            }
            else
            {
                currentLocation[n] = currentLocation[n] - (random.nextFloat() * 0.35F);
            }
        }

        if(random.nextInt(5) == 0)
        {
            return true;
        }
        return false;
    }
}
