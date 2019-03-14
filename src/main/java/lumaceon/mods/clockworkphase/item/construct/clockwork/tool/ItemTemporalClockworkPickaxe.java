package lumaceon.mods.clockworkphase.item.construct.clockwork.tool;

import lumaceon.mods.clockworkphase.custom.CustomUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import lumaceon.mods.clockworkphase.init.ModBlocks;
import lumaceon.mods.clockworkphase.init.ModItems;
import lumaceon.mods.clockworkphase.item.construct.IKeybindAbility;
import lumaceon.mods.clockworkphase.item.construct.ITemporalChange;
import lumaceon.mods.clockworkphase.lib.MechanicTweaker;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.lib.ValidBlockLists;
import lumaceon.mods.clockworkphase.network.MessageTemporalItemChange;
import lumaceon.mods.clockworkphase.network.PacketHandler;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import lumaceon.mods.clockworkphase.util.TimeSandParser;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class ItemTemporalClockworkPickaxe extends ItemClockworkPickaxe implements IKeybindAbility, ITemporalChange
{
    public ItemTemporalClockworkPickaxe(ToolMaterial mat)
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
            if(timeSand < MechanicTweaker.TIME_SAND_PER_BLOCK_BREAK_PICKAXE && !player.inventory.getStackInSlot(player.inventory.currentItem).isEmpty())
            {
                if(player.inventory.getStackInSlot(player.inventory.currentItem).equals(is))
                {
                    ItemStack newItem = new ItemStack(this.getItemChangeTo());
                    newItem.setTagCompound(is.getTagCompound());
                    newItem.setItemDamage(is.getItemDamage());
                    player.inventory.setInventorySlotContents(player.inventory.currentItem, newItem);
                }
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack is, World worldIn, List<String> list, ITooltipFlag flagIn)
    {
        list.add("Tension: " + "\u00a7e" + NBTHelper.getInt(is, NBTTags.TENSION_ENERGY) + "/" + "\u00a7e" + NBTHelper.getInt(is, NBTTags.MAX_TENSION));
        int timeSand = getTimeSand(is);
        if(timeSand > 0)
        {
            list.add(TimeSandParser.getStringForRenderingFromTimeSand(timeSand));
        }

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
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if(ValidBlockLists.BlockOres.isBlockValidOre(world.getBlockState(pos).getBlock()))
        {
            if(world.isBlockModifiable(player, pos))
            {
                int amountToRemove = MechanicTweaker.TIME_SAND_COST_TEMPORAL_PICKAXE;
                if(this.getTimeSandFromInventory(player.inventory) >= amountToRemove || this.getTimeSand(player.getHeldItem(hand)) >= amountToRemove)
                {
                    amountToRemove -= this.removeTimeSandFromInventory(player.inventory, amountToRemove);
                    this.removeTimeSand(player.getHeldItem(hand), amountToRemove);
                    world.setBlockState(pos, ModBlocks.oreTemporal.getDefaultState());
                    world.destroyBlock(pos, true);
                    world.updateObservingBlocksAt(pos, world.getBlockState(pos).getBlock());
                }
            }
        }
        return EnumActionResult.PASS;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack is, World world, IBlockState state, BlockPos pos, EntityLivingBase entity)
    {
        super.onBlockDestroyed(is, world, state, pos, entity);
        if(entity instanceof EntityPlayer)
        {
            if(this.getDestroySpeed(is, state) <= 1.0F)
            {
                return true;
            }
            int amountToRemove = MechanicTweaker.TIME_SAND_PER_BLOCK_BREAK_PICKAXE;
            EntityPlayer player = (EntityPlayer)entity;
            amountToRemove -= this.removeTimeSandFromInventory(player.inventory, amountToRemove);
            amountToRemove -= this.removeTimeSand(is, amountToRemove);
            if(amountToRemove > 0) //Change item back to non-temporal if the player can't meet the TS requirements
            {
                ItemStack newItem = new ItemStack(this.getItemChangeTo());
                newItem.setTagCompound(is.getTagCompound());
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
    public float getDestroySpeed(ItemStack stack, IBlockState state)
    {
        int tension = NBTHelper.getInt(stack, NBTTags.TENSION_ENERGY); if(tension <= 0) { return 1.0F; }
        int speed = NBTHelper.getInt(stack, NBTTags.SPEED); if(speed <= 0) { return 1.0F; }
        if(CustomUtils.isToolEffective(stack, state))
        {
            return super.getDestroySpeed(stack, state) * 3F;
        }
        float efficiency = super.getDestroySpeed(stack, state); if(efficiency == 1.0F) { return efficiency; }

        return super.getDestroySpeed(stack, state) * 3F;
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
        return ModItems.clockworkPickaxe;
    }
}
