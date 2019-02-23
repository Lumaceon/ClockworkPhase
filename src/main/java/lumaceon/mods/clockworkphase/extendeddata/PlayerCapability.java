package lumaceon.mods.clockworkphase.extendeddata;

import lumaceon.mods.clockworkphase.lib.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PlayerCapability {

    public static final String HANDLER_NAME = "clockwork_phase_player_data";

    @CapabilityInject(IPlayerHandler.class)
    public static final Capability<IPlayerHandler> CAPABILITY_PLAYER = null;

    public static void preInit() {
        CapabilityManager.INSTANCE.register(IPlayerHandler.class, new Storage(), DefaultPlayerHandler::new);
    }

    @Mod.EventBusSubscriber(modid = Reference.MOD_ID)
    public static class CapabilityHandler {
        @SubscribeEvent
        public static void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {

            if (event.getObject() instanceof EntityPlayer)
                event.addCapability(new ResourceLocation(Reference.MOD_ID, HANDLER_NAME), new Provider());
        }

        @SubscribeEvent
        public void clonePlayer(PlayerEvent.Clone event) {

            final IPlayerHandler original = event.getOriginal().getCapability(CAPABILITY_PLAYER, null);
            final IPlayerHandler clone = event.getEntity().getCapability(CAPABILITY_PLAYER, null);
            clone.setPrevPos(original.getPrevPos());
            clone.setPrevMotion(original.getPrevMotion());
        }
    }

    public static interface IPlayerHandler {

        BlockPos getPrevPos();

        void setPrevPos(BlockPos prevPos);

        BlockPos getPrevMotion();

        void setPrevMotion(BlockPos prevPos);
    }

    public static class DefaultPlayerHandler implements IPlayerHandler {

        private BlockPos prevPos = BlockPos.ORIGIN, prevMotion = BlockPos.ORIGIN;

        @Override
        public BlockPos getPrevPos() {
            return prevPos;
        }

        @Override
        public void setPrevPos(BlockPos prevPos) {
            this.prevPos = prevPos;
        }

        @Override
        public BlockPos getPrevMotion() {
            return prevMotion;
        }

        @Override
        public void setPrevMotion(BlockPos prevMotion) {
            this.prevMotion = prevMotion;
        }
    }

    public static class Storage implements Capability.IStorage<IPlayerHandler> {

        @Override
        public NBTBase writeNBT(Capability<IPlayerHandler> capability, IPlayerHandler instance, EnumFacing side) {
            return new NBTTagCompound();
        }

        @Override
        public void readNBT(Capability<IPlayerHandler> capability, IPlayerHandler instance, EnumFacing side, NBTBase nbt) {
        }
    }

    public static class Provider implements ICapabilitySerializable<NBTTagCompound> {

        IPlayerHandler instance = CAPABILITY_PLAYER.getDefaultInstance();

        @Override
        public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
            return capability == CAPABILITY_PLAYER;
        }

        @Override
        public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
            return hasCapability(capability, facing) ? CAPABILITY_PLAYER.<T>cast(instance) : null;
        }

        @Override
        public NBTTagCompound serializeNBT() {
            return new NBTTagCompound();
        }

        @Override
        public void deserializeNBT(NBTTagCompound nbt) {
        }
    }
}
