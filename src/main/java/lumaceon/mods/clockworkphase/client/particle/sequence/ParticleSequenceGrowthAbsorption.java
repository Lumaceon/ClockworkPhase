package lumaceon.mods.clockworkphase.client.particle.sequence;

import lumaceon.mods.clockworkphase.client.particle.ParticleGenerator;
import lumaceon.mods.clockworkphase.client.particle.entityfx.EntityGrowthAbsorptionFX;
import lumaceon.mods.clockworkphase.util.Range2I;
import net.minecraft.client.particle.EntityFX;

public class ParticleSequenceGrowthAbsorption extends ParticleSequence
{
    public double[] from = new double[3];
    public double[] to = new double[3];

    public double currentTrunkCenter;

    public int startTimer;

    public ParticleSequenceGrowthAbsorption(ParticleGenerator particleGenerator, double xFrom, double yFrom, double zFrom, double xTo, double yTo, double zTo)
    {
        super(particleGenerator);

        this.timer = 100;
        this.startTimer = this.timer;

        //Create main particle overhead.
        this.sequencePhases.add(new Range2I(100, 100));

        //Blast the sapling with time particles.
        this.sequencePhases.add(new Range2I(78, 100));

        //Absorb nature particles from the sapling.
        this.sequencePhases.add(new Range2I(0, 20));

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
        EntityFX particle;
        double x = from[0] * random.nextFloat();
        double y = from[1] * random.nextFloat();
        double z = from[2] * random.nextFloat();

        if(this.sequencePhases.get(0).isValueInclusivelyWithinRange(this.timer))
        {
            x = to[0] + 0.5F;
            y = to[1] + 2;
            z = to[2] + 0.5F;

            //particle = new EntityTimeEnergyExtractorFX(particleGenerator.world, x, y, z);
            //this.particleGenerator.spawnParticle(particle, 64);
        }

        if(this.sequencePhases.get(1).isValueInclusivelyWithinRange(this.timer))
        {
            x = to[0] + 0.5F;
            y = to[1] + 2;
            z = to[2] + 0.5F;

            //particle = new EntityTimeBlastFX(particleGenerator.world, x, y, z, from[0], from[1], from[2]);
            //this.particleGenerator.spawnParticle(particle, 64);
        }

        if(this.sequencePhases.get(2).isValueInclusivelyWithinRange(this.timer))
        {
            x = from[0] + 0.5F;
            y = from[1] + 1.5F;
            z = from[2] + 0.5F;

            particle = new EntityGrowthAbsorptionFX(particleGenerator.world, x, y, z, to[0] + 0.5F, to[1] + 0.5F, to[2] + 0.5F);
            this.particleGenerator.spawnParticle(particle, 64);
        }

        if(timer <= 0)
        {
            return false;
        }

        timer--;
        return true;
    }
}
