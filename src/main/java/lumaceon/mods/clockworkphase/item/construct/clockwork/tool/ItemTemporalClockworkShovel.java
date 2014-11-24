package lumaceon.mods.clockworkphase.item.construct.clockwork.tool;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lumaceon.mods.clockworkphase.init.ModBlocks;
import lumaceon.mods.clockworkphase.init.ModItems;
import lumaceon.mods.clockworkphase.item.construct.IKeybindAbility;
import lumaceon.mods.clockworkphase.item.construct.ITemporalChange;
import lumaceon.mods.clockworkphase.lib.MechanicTweaker;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.lib.ValidBlockLists;
import lumaceon.mods.clockworkphase.network.MessageTemporalItemChange;
import lumaceon.mods.clockworkphase.network.PacketHandler;
import lumaceon.mods.clockworkphase.util.Logger;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.BlockRedstoneOre;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class ItemTemporalClockworkShovel extends ItemClockworkShovel implements IKeybindAbility, ITemporalChange
{
    public ItemTemporalClockworkShovel(ToolMaterial mat)
    {
        super(mat);
        this.setCreativeTab(null);
    }

    @Override
    public void onUpdate(ItemStack is, World world, Entity entity, int p_77663_4_, boolean p_77663_5_)
    {
        if(entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer)entity;
            int timeSand = getTimeSand(is);
            timeSand += getTimeSandFromInventory(player.inventory);
            if(timeSand < MechanicTweaker.TIME_SAND_PER_BLOCK_BREAK_SHOVEL && player.inventory.getStackInSlot(player.inventory.currentItem) != null)
            {
                if(player.inventory.getStackInSlot(player.inventory.currentItem).equals(is))
                {
                    ItemStack newItem = new ItemStack(this.getItemChangeTo());
                    newItem.setTagCompound(is.stackTagCompound);
                    newItem.setItemDamage(is.getItemDamage());
                    player.inventory.setInventorySlotContents(player.inventory.currentItem, newItem);
                }
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack is, EntityPlayer player, List list, boolean flag)
    {
        list.add("Tension: " + "\u00a7e" + NBTHelper.getInt(is, NBTTags.TENSION_ENERGY) + "/" + "\u00a7e" + NBTHelper.getInt(is, NBTTags.MAX_TENSION));
        list.add("Time Sand: " + "\u00A7e" + getTimeSand(is));

        if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
        {
            list.add("");
            list.add("Clockwork Quality: " + "\u00A7e" + NBTHelper.getInt(is, NBTTags.QUALITY) + " " + "\u00A7b" + "x 3");
            list.add("Clockwork Speed: " + "\u00A7e" + NBTHelper.getInt(is, NBTTags.SPEED) + " " + "\u00A7b" + "x 3");
            list.add("Memory: " + "\u00A7e" + NBTHelper.getInt(is, NBTTags.MEMORY));
            list.add("");
        }
        else
        {
            list.add("-Hold shift for details-");
        }
    }

    @Override
    public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int meta, float f1, float f2, float f3)
    {
        if(ValidBlockLists.BlockShovelables.isBlockValidShovelable(world.getBlock(x, y, z)))
        {
            if(world.canMineBlock(player, x, y, z))
            {
                int amountToRemove = MechanicTweaker.TIME_SAND_COST_TEMPORAL_SHOVEL;
                if(this.getTimeSandFromInventory(player.inventory) >= amountToRemove || this.getTimeSand(is) >= amountToRemove)
                {
                    amountToRemove -= this.removeTimeSandFromInventory(player.inventory, amountToRemove);
                    this.removeTimeSand(is, amountToRemove);
                    world.setBlock(x, y, z, ModBlocks.sandTemporal);
                    world.func_147480_a(x, y, z, true);
                    world.notifyBlocksOfNeighborChange(x, y, z, world.getBlock(x, y, z));
                }
            }
        }
        return false;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack is, World world, Block block, int x, int y, int z, EntityLivingBase entity)
    {
        super.onBlockDestroyed(is, world, block, x, y, z, entity);
        if(entity != null && entity instanceof EntityPlayer)
        {
            if(this.func_150893_a(is, block) <= 1.0F)
            {
                return true;
            }
            int amountToRemove = MechanicTweaker.TIME_SAND_PER_BLOCK_BREAK_SHOVEL;
            EntityPlayer player = (EntityPlayer)entity;
            amountToRemove -= this.removeTimeSandFromInventory(player.inventory, amountToRemove);
            amountToRemove -= this.removeTimeSand(is, amountToRemove);
            if(amountToRemove > 0) //Change item back to non-temporal if the player can't meet the TS requirements
            {
                ItemStack newItem = new ItemStack(this.getItemChangeTo());
                newItem.setTagCompound(is.stackTagCompound);
                newItem.setItemDamage(is.getItemDamage());
                if(player.inventory.getStackInSlot(player.inventory.currentItem).equals(is))
                {
                    player.inventory.setInventorySlotContents(player.inventory.currentItem, newItem);
                }
            }
        }
        return true;
    }

    @Override
    public float func_150893_a(ItemStack is, Block block)
    {
        float efficiency = super.func_150893_a(is, block); if(efficiency == 1.0F) { return efficiency; }
        int tension = NBTHelper.getInt(is, NBTTags.TENSION_ENERGY); if(tension <= 0) { return 0.0F; }
        int speed = NBTHelper.getInt(is, NBTTags.SPEED); if(speed <= 0) { return 0.0F; }

        return super.func_150893_a(is, block) * 3.0F;
    }

    @Override
    public void disassemble(World world, double x, double y, double z, ItemStack is)
    {
        //NOOP
    }

    @Override
    public void useTemporalAbility()
    {
        PacketHandler.INSTANCE.sendToServer(new MessageTemporalItemChange());
    }

    @Override
    public Item getItemChangeTo()
    {
        return ModItems.clockworkShovel;
    }
}
