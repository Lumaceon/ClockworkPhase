package lumaceon.mods.clockworkphase.block.blockitems;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lumaceon.mods.clockworkphase.ClockworkPhase;
import lumaceon.mods.clockworkphase.init.ModBlocks;
import lumaceon.mods.clockworkphase.item.construct.abstracts.IElemental;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.lib.Phases;
import lumaceon.mods.clockworkphase.lib.Ranges;
import lumaceon.mods.clockworkphase.lib.Textures;
import lumaceon.mods.clockworkphase.proxy.ClientProxy;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;

public class ItemBlockExtractor extends ItemBlock implements IElemental
{
    public ItemBlockExtractor(Block block)
    {
        super(block);
        this.setMaxStackSize(1);
        this.setCreativeTab(ClockworkPhase.instance.creativeTabClockworkPhase);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack is, EntityPlayer player, List list, boolean flag)
    {

    }

    @Override
    public int getEntityLifespan(ItemStack itemStack, World world)
    {
        return 24000;
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player)
    {
        NBTHelper.setInteger(item, NBTTags.ELEMENTIZE_TIMER, 0);
        return true;
    }

    @Override
    public boolean onEntityItemUpdate(EntityItem entityItem)
    {
        int x = (int)Math.floor(entityItem.posX);
        int y = (int)Math.floor(entityItem.posY - 1);
        int z = (int)Math.floor(entityItem.posZ);

        Block targetBlock = entityItem.worldObj.getBlock(x, y, z);
        boolean flag = true;

        if(targetBlock == null)
        {
            return false;
        }

        if(!targetBlock.equals(ModBlocks.celestialCompass) && !targetBlock.equals(ModBlocks.celestialCompassSub))
        {
            return false;
        }

        int meta = entityItem.worldObj.getBlockMetadata(x, y, z);
        ForgeDirection direction = ForgeDirection.getOrientation(meta);

        for(int n = 0; n < 10 && flag; n++)
        {
            //Block is null, return false
            if(entityItem.worldObj.getBlock(x, y, z) == null)
            {
                return false;
            }
            //Celestial Compass was found, exit out of the loop
            else if(entityItem.worldObj.getBlock(x, y, z).equals(ModBlocks.celestialCompass))
            {
                flag = false;
            }
            //Continue searching by way of metadata
            else
            {
                x += direction.offsetX;
                z += direction.offsetZ;

                meta = entityItem.worldObj.getBlockMetadata(x, y, z);
                direction = ForgeDirection.getOrientation(meta);
            }
        }
        handleElementization(entityItem, x, y, z);
        return false;
    }

    private void handleElementization(EntityItem item, int x, int y, int z)
    {
        double xDifference = item.posX - x;
        double zDifference = item.posZ - z;

        if(Ranges.CELESTIAL_LIFE[0].isValueInclusivelyWithinRange(xDifference) && Ranges.CELESTIAL_LIFE[1].isValueInclusivelyWithinRange(zDifference))
        {
            if(item.worldObj.isRemote)
            {
                ClientProxy.particleGenerator.SPAWNER.spawnElementizeParticle(item.posX, item.posY, item.posZ);
            }

            if(NBTHelper.getInt(item.getEntityItem(), NBTTags.ELEMENTIZE_TIMER) > 200)
            {
                elementize(Phases.LIFE, item);
                return;
            }
        }
        else if(Ranges.CELESTIAL_LIGHT[0].isValueInclusivelyWithinRange(xDifference) && Ranges.CELESTIAL_LIGHT[1].isValueInclusivelyWithinRange(zDifference))
        {
            if(item.worldObj.isRemote)
            {
                ClientProxy.particleGenerator.SPAWNER.spawnElementizeParticle(item.posX, item.posY, item.posZ);
            }

            if(NBTHelper.getInt(item.getEntityItem(), NBTTags.ELEMENTIZE_TIMER) > 200)
            {
                elementize(Phases.LIGHT, item);
                return;
            }
        }
        else if(Ranges.CELESTIAL_WATER[0].isValueInclusivelyWithinRange(xDifference) && Ranges.CELESTIAL_WATER[1].isValueInclusivelyWithinRange(zDifference))
        {
            if(item.worldObj.isRemote)
            {
                ClientProxy.particleGenerator.SPAWNER.spawnElementizeParticle(item.posX, item.posY, item.posZ);
            }

            if(NBTHelper.getInt(item.getEntityItem(), NBTTags.ELEMENTIZE_TIMER) > 200)
            {
                elementize(Phases.WATER, item);
                return;
            }
        }
        else if(Ranges.CELESTIAL_EARTH[0].isValueInclusivelyWithinRange(xDifference) && Ranges.CELESTIAL_EARTH[1].isValueInclusivelyWithinRange(zDifference))
        {
            if(item.worldObj.isRemote)
            {
                ClientProxy.particleGenerator.SPAWNER.spawnElementizeParticle(item.posX, item.posY, item.posZ);
            }

            if(NBTHelper.getInt(item.getEntityItem(), NBTTags.ELEMENTIZE_TIMER) > 200)
            {
                elementize(Phases.EARTH, item);
                return;
            }
        }
        else if(Ranges.CELESTIAL_AIR[0].isValueInclusivelyWithinRange(xDifference) && Ranges.CELESTIAL_AIR[1].isValueInclusivelyWithinRange(zDifference))
        {
            if(item.worldObj.isRemote)
            {
                ClientProxy.particleGenerator.SPAWNER.spawnElementizeParticle(item.posX, item.posY, item.posZ);
            }

            if(NBTHelper.getInt(item.getEntityItem(), NBTTags.ELEMENTIZE_TIMER) > 200)
            {
                elementize(Phases.AIR, item);
                return;
            }
        }
        else if(Ranges.CELESTIAL_FIRE[0].isValueInclusivelyWithinRange(xDifference) && Ranges.CELESTIAL_FIRE[1].isValueInclusivelyWithinRange(zDifference))
        {
            if(item.worldObj.isRemote)
            {
                ClientProxy.particleGenerator.SPAWNER.spawnElementizeParticle(item.posX, item.posY, item.posZ);
            }

            if(NBTHelper.getInt(item.getEntityItem(), NBTTags.ELEMENTIZE_TIMER) > 200)
            {
                elementize(Phases.FIRE, item);
                return;
            }
        }
        else if(Ranges.CELESTIAL_DARKNESS[0].isValueInclusivelyWithinRange(xDifference) && Ranges.CELESTIAL_DARKNESS[1].isValueInclusivelyWithinRange(zDifference))
        {
            if(item.worldObj.isRemote)
            {
                ClientProxy.particleGenerator.SPAWNER.spawnElementizeParticle(item.posX, item.posY, item.posZ);
            }

            if(NBTHelper.getInt(item.getEntityItem(), NBTTags.ELEMENTIZE_TIMER) > 200)
            {
                elementize(Phases.LUNAR, item);
                return;
            }
        }
        else if(Ranges.CELESTIAL_DEATH[0].isValueInclusivelyWithinRange(xDifference) && Ranges.CELESTIAL_DEATH[1].isValueInclusivelyWithinRange(zDifference))
        {
            if(item.worldObj.isRemote)
            {
                ClientProxy.particleGenerator.SPAWNER.spawnElementizeParticle(item.posX, item.posY, item.posZ);
            }

            if(NBTHelper.getInt(item.getEntityItem(), NBTTags.ELEMENTIZE_TIMER) > 200)
            {
                elementize(Phases.DEATH, item);
                return;
            }
        }
        else
        {
            return;
        }

        NBTHelper.setInteger(item.getEntityItem(), NBTTags.ELEMENTIZE_TIMER, NBTHelper.getInt(item.getEntityItem(), NBTTags.ELEMENTIZE_TIMER) + 1);
    }

    @Override
    public void elementize(Phases phase, EntityItem item)
    {
        NBTHelper.setInteger(item.getEntityItem(), NBTTags.ELEMENTIZE_TIMER, 0);
        int id = phase.ordinal();
        if(!item.getEntityItem().getItem().equals(ModBlocks.extractorsElements[id]))
        {
            ItemStack newItem = new ItemStack(ModBlocks.extractorsElements[id]);
            newItem.setTagCompound(item.getEntityItem().stackTagCompound);
            newItem.setItemDamage(item.getEntityItem().getItemDamage());
            item.setEntityItemStack(newItem);
        }
    }

    @Override
    public void unelementize(EntityItem item)
    {
        //NOOP
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("tile.%s%s", Textures.RESOURCE_PREFIX, this.field_150939_a.getUnlocalizedName().substring(this.field_150939_a.getUnlocalizedName().indexOf('.') + 1));
    }

    @Override
    public String getUnlocalizedName(ItemStack is)
    {
        return String.format("tile.%s%s", Textures.RESOURCE_PREFIX, this.field_150939_a.getUnlocalizedName().substring(this.field_150939_a.getUnlocalizedName().indexOf('.') + 1));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister registry)
    {
        this.itemIcon = registry.registerIcon(this.field_150939_a.getUnlocalizedName().substring(this.field_150939_a.getUnlocalizedName().indexOf(".") + 1));
    }
}
