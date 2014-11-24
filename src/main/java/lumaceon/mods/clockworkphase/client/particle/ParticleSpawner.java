package lumaceon.mods.clockworkphase.client.particle;

import lumaceon.mods.clockworkphase.client.particle.entityfx.*;
import lumaceon.mods.clockworkphase.proxy.ClientProxy;
import net.minecraft.client.particle.EntityFX;

public class ParticleSpawner
{
    public ParticleGenerator gen;

    public ParticleSpawner(ParticleGenerator gen)
    {
        this.gen = gen;
    }

    public void spawnElementizeParticle(double x, double y, double z)
    {
        gen.initWorldAndRandom();
        EntityFX particle = new EntityElementalAttunementFX(gen.world, x, y, z);
        ClientProxy.particleGenerator.spawnParticle(particle, 64.0F);
    }

    public void spawnTimeSandExtractionParticles(double x, double y, double z, int amount)
    {
        gen.initWorldAndRandom();
        for(int n = 0; n < amount; n++)
        {
            EntityTimeSandExtractionFX particle = new EntityTimeSandExtractionFX(gen.world, x, y, z);
            ClientProxy.particleGenerator.spawnParticle(particle, 64);
        }
    }

    public void spawnGrowthAbsorptionParticle(double fromX, double fromY, double fromZ, double x, double y, double z)
    {
        gen.initWorldAndRandom();
        EntityFX particle = new EntityGrowthAbsorptionFX(gen.world, fromX, fromY, fromZ, x, y, z);
        ClientProxy.particleGenerator.spawnParticle(particle, 64.0F);
    }

    public void spawnBlockInTheWayParticle(double x, double y, double z)
    {
        gen.initWorldAndRandom();
        EntityFX particle = new EntityBlockInTheWayFX(gen.world, x, y, z);
        ClientProxy.particleGenerator.spawnParticle(particle, 48.0F);
    }

    public void spawnTimeSandTunnelParticle(double x, double y, double z, double targetX, double targetY, double targetZ)
    {
        gen.initWorldAndRandom();
        EntityFX particle = new EntityTimeSandTunnelFX(gen.world, x, y, z, targetX, targetY, targetZ);
        ClientProxy.particleGenerator.spawnParticle(particle, 64.0F);
    }
}
