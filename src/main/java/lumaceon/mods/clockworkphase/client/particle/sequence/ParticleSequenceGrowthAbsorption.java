package lumaceon.mods.clockworkphase.client.particle.sequence;

import lumaceon.mods.clockworkphase.client.particle.ParticleGenerator;
import lumaceon.mods.clockworkphase.client.particle.entityfx.EntityGrowthAbsorptionFX;
import lumaceon.mods.clockworkphase.client.particle.sequence.logic.Branch;
import net.minecraft.client.particle.EntityFX;

import java.util.ArrayList;

public class ParticleSequenceGrowthAbsorption extends ParticleSequence
{
    public double[] from = new double[3];
    public double[] to = new double[3];

    public double currentTrunkCenter;

    public ArrayList<Branch> branches = new ArrayList<Branch>(25);

    public int startTimer;

    public ParticleSequenceGrowthAbsorption(ParticleGenerator particleGenerator, double xFrom, double yFrom, double zFrom, double xTo, double yTo, double zTo)
    {
        super(particleGenerator);

        this.timer = 60;
        this.startTimer = this.timer;

        this.from[0] = xFrom;
        this.from[1] = yFrom;
        this.from[2] = zFrom;

        this.to[0] = xTo;
        this.to[1] = yTo;
        this.to[2] = zTo;

        this.currentTrunkCenter = this.from[1];
    }

    @Override
    public boolean updateParticleSequence()
    {
        float blockYIncrease = 0.4F;
        float areaFlattening = (135 - (60 - timer)) / 135;

        if(timer >= 25)
        {
            //Spawn particles in the specified block area
            for(int n = 0; n < 15; n++)
            {
                double x = (from[0] - 0.5) + random.nextFloat() * areaFlattening;
                double y = (currentTrunkCenter - 0.5) + random.nextFloat() * blockYIncrease;
                double z = (from[2] - 0.5) + random.nextFloat() * areaFlattening;
                EntityFX particle = new EntityGrowthAbsorptionFX(particleGenerator.world, x, y, z, to[0], to[1], to[2], 1, timer);
                this.particleGenerator.spawnParticle(particle, 64);
            }

            currentTrunkCenter += blockYIncrease;
        }

        if(timer <= 50 && timer >= 20)
        {
            if(timer % 2 == 0)
            {
                Branch branch;
                double x;
                double y;
                double z;

                this.branches.add(new Branch(random, timer, startTimer, from[0], currentTrunkCenter, from[2], true));

                for(int n = 0; n < this.branches.size(); n++)
                {
                    branch = this.branches.get(n);
                    x = branch.currentLocation[0];
                    y = branch.currentLocation[1];
                    z = branch.currentLocation[2];

                    if(branch.expandBranch())
                    {
                        this.branches.add(new Branch(random, timer, startTimer, x, y, z, false));
                    }

                    EntityFX particle = new EntityGrowthAbsorptionFX(particleGenerator.world, x, y, z, to[0], to[1], to[2], branch.particleScale, timer);
                    this.particleGenerator.spawnParticle(particle, 64);
                }
            }
        }

        if(timer <= 0)
        {
            return false;
        }

        timer--;
        return true;
    }
}
