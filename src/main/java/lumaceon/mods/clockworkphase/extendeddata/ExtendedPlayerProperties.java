package lumaceon.mods.clockworkphase.extendeddata;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

import java.util.ArrayList;

public class ExtendedPlayerProperties implements IExtendedEntityProperties
{
    public static final String HANDLER_NAME = "clockwork_phase_player_data";
    private final EntityPlayer player;

    public double prevPosX, prevPosY, prevPosZ;
    public double prevMotionX, prevMotionY, prevMotionZ;

    public ExtendedPlayerProperties(EntityPlayer player) {
        this.player = player;
    }

    public static void register(EntityPlayer player)
    {
        player.registerExtendedProperties(ExtendedPlayerProperties.HANDLER_NAME, new ExtendedPlayerProperties(player));
    }

    public static ExtendedPlayerProperties get(EntityPlayer player)
    {
        return (ExtendedPlayerProperties) player.getExtendedProperties(HANDLER_NAME);
    }

    private static String getSaveKey(EntityPlayer player)
    {
        return player.getUniqueID().toString() + ":" + HANDLER_NAME;
    }

    @Override
    public void saveNBTData(NBTTagCompound compound) {

    }

    @Override
    public void loadNBTData(NBTTagCompound compound) {

    }

    @Override
    public void init(Entity entity, World world) {

    }
}
