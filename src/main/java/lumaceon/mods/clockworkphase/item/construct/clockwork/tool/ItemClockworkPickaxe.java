package lumaceon.mods.clockworkphase.item.construct.clockwork.tool;

import lumaceon.mods.clockworkphase.custom.CustomUtils;
import lumaceon.mods.clockworkphase.custom.IHasModel;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import lumaceon.mods.clockworkphase.ClockworkPhase;
import lumaceon.mods.clockworkphase.block.tileentity.TileEntityTimeWell;
import lumaceon.mods.clockworkphase.init.ModBlocks;
import lumaceon.mods.clockworkphase.init.ModItems;
import lumaceon.mods.clockworkphase.item.construct.abstracts.ITimeSand;
import lumaceon.mods.clockworkphase.item.construct.IKeybindAbility;
import lumaceon.mods.clockworkphase.item.construct.ITemporalChange;
import lumaceon.mods.clockworkphase.item.construct.abstracts.IClockwork;
import lumaceon.mods.clockworkphase.item.construct.abstracts.IDisassemble;
import lumaceon.mods.clockworkphase.lib.MechanicTweaker;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.network.MessageParticleSpawn;
import lumaceon.mods.clockworkphase.network.MessageTemporalItemChange;
import lumaceon.mods.clockworkphase.network.PacketHandler;
import lumaceon.mods.clockworkphase.util.*;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class ItemClockworkPickaxe extends ItemPickaxe implements IClockwork, IDisassemble, IKeybindAbility, ITemporalChange, ITimeSand, IHasModel
{
    public ItemClockworkPickaxe(ToolMaterial mat)
    {
        super(mat);
        this.setCreativeTab(ClockworkPhase.instance.creativeTabClockworkPhase);
        this.setMaxStackSize(1);
        this.setMaxDamage(50);
        this.setNoRepair();
        this.setHarvestLevel("pickaxe", 3);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        /**if(NBTHelper.hasTag(is, NBTTags.FLUXED_ITEM))
        {
            ItemStack fluxedItem = ItemStack.loadItemStackFromNBT((NBTTagCompound)NBTHelper.getTag(is, NBTTags.FLUXED_ITEM));
            NBTTagCompound nbt = new NBTTagCompound();
            is.writeToNBT(nbt);
            NBTHelper.setTag(fluxedItem, NBTTags.ORIGINAL_CLOCKWORK_ITEM, nbt);
            NBTHelper.removeTag(is, NBTTags.FLUXED_ITEM);
            player.inventory.setInventorySlotContents(player.inventory.currentItem, fluxedItem);
            return true;
        }**/
        return EnumActionResult.PASS;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
    {
        /**if(NBTHelper.hasTag(is, NBTTags.FLUXED_ITEM))
        {
            ItemStack fluxedItem = ItemStack.loadItemStackFromNBT((NBTTagCompound)NBTHelper.getTag(is, NBTTags.FLUXED_ITEM));
            NBTTagCompound nbt = new NBTTagCompound();
            is.writeToNBT(nbt);
            NBTHelper.setTag(fluxedItem, NBTTags.ORIGINAL_CLOCKWORK_ITEM, nbt);
            NBTHelper.removeTag(is, NBTTags.FLUXED_ITEM);
            player.inventory.setInventorySlotContents(player.inventory.currentItem, fluxedItem);
            return is;
        }**/
        return ActionResult.newResult(EnumActionResult.PASS, player.getHeldItem(hand));
    }

    @Override
    public boolean onEntityItemUpdate(EntityItem entityItem)
    {
        int x = (int) Math.floor(entityItem.posX);
        int y = (int) Math.floor(entityItem.posY - 1);
        int z = (int) Math.floor(entityItem.posZ);
        BlockPos pos = new BlockPos(x, y, z);

        Block targetBlock = entityItem.world.getBlockState(pos).getBlock();

        if (!targetBlock.equals(ModBlocks.timeWell))
        {
            return false;
        }

        TileEntity te = entityItem.world.getTileEntity(pos);
        if(te instanceof TileEntityTimeWell)
        {
            if(!entityItem.world.isRemote)
            {
                int amountRemoved = this.removeTimeSand(entityItem.getItem(), 1000);
                TileEntityTimeWell timeWell = (TileEntityTimeWell)te;
                timeWell.addTimeSand(amountRemoved);
                return false;
            }
        }
        return false;
    }

    @Override
    public float getDestroySpeed(ItemStack stack, IBlockState state)
    {
        int tension = NBTHelper.getInt(stack, NBTTags.TENSION_ENERGY); if(tension <= 0) { return 1.0F; }
        int speed = NBTHelper.getInt(stack, NBTTags.SPEED); if(speed <= 0) { return 1.0F; }
        if(CustomUtils.isToolEffective(stack, state))
        {
            return (float)speed / 20;
        }
        float efficiency = super.getDestroySpeed(stack, state); if(efficiency == 1.0F) { return efficiency; }

        return (float)speed / 20;
    }

    @Override
    public boolean hitEntity(ItemStack is, EntityLivingBase entity1, EntityLivingBase entity2)
    {
        return true;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack is, World world, IBlockState state, BlockPos pos, EntityLivingBase entity)
    {
        if ((double)state.getBlockHardness(world, pos) != 0.0D)
        {
            if(is.getItem() instanceof IClockwork)
            {
                int tension = NBTHelper.getInt(is, NBTTags.TENSION_ENERGY);
                int quality = NBTHelper.getInt(is, NBTTags.QUALITY); if(quality <= 0) { return false; }
                int speed = NBTHelper.getInt(is, NBTTags.SPEED);
                int memory = NBTHelper.getInt(is, NBTTags.MEMORY);
                float efficiency = (float)speed / (float)quality;
                int tensionCost = (int)Math.round(MechanicTweaker.TENSION_PER_BLOCK_BREAK * Math.pow(efficiency, 2));
                int newTension = tension - tensionCost;

                if(newTension <= 0)
                {
                    this.removeTension(is, tension);
                    return true;
                }

                if(memory > 0 && !world.isRemote)
                {
                    if(this.getDestroySpeed(is, state) > 1.0F)
                    {
                        if(entity instanceof EntityPlayer)
                        {
                            EntityPlayer player = (EntityPlayer)entity;
                            int memoryWebPower = (int)(memory * Math.pow(player.experienceLevel + 1.0F, 2.0F));
                            int chance = MechanicTweaker.TIME_SAND_CHANCE_FACTOR;
                            if(memoryWebPower > 0)
                            {
                                chance = MechanicTweaker.TIME_SAND_CHANCE_FACTOR / memoryWebPower;
                            }

                            if(chance < 1) { chance = 1; }
                            if(world.rand.nextInt(chance) == 0)
                            {
                                this.addTimeSand(is, MechanicTweaker.PICKAXE_TIME_SAND_INCREMENT);
                                PacketHandler.INSTANCE.sendToAllAround(new MessageParticleSpawn(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 1), new NetworkRegistry.TargetPoint(world.provider.getDimension(), pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 64));
                            }
                        }
                    }
                }
                this.removeTension(is, tensionCost);
            }
        }
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack is, World worldIn, List<String> list, ITooltipFlag flagIn)
    {
        EntityPlayer player = Minecraft.getMinecraft().player;
        list.add("Tension: " + "\u00a7e" + NBTHelper.getInt(is, NBTTags.TENSION_ENERGY) + "/" + "\u00a7e" + NBTHelper.getInt(is, NBTTags.MAX_TENSION));
        int timeSand = getTimeSand(is);
        if(timeSand > 0)
        {
            list.add(TimeSandParser.getStringForRenderingFromTimeSand(timeSand));
        }

        if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
        {
            int quality = NBTHelper.getInt(is, NBTTags.QUALITY);
            int speed = NBTHelper.getInt(is, NBTTags.SPEED);
            int memory = NBTHelper.getInt(is, NBTTags.MEMORY);
            int memoryWebPower = (int)(memory * Math.pow(player.experienceLevel + 1.0F, 2.0F));
            int chance = MechanicTweaker.TIME_SAND_CHANCE_FACTOR;
            if(memoryWebPower > 0)
            {
                chance = MechanicTweaker.TIME_SAND_CHANCE_FACTOR / memoryWebPower;
                if(chance < 1) { chance = 1; }
            }

            list.add("");
            list.add("Clockwork Quality: " + "\u00A7e" + quality);
            list.add("Clockwork Speed: " + "\u00A7e" + speed);
            list.add("Memory: " + "\u00A7e" + memory);
            if(memory > 0)
            {
                if(chance < 1000000)
                {
                    list.add("Chance of Extracting Time Sand: 1 in " + chance);
                }
                else
                {
                    list.add("Chance of Extracting Time Sand: 1 in an eternity.");
                }
            }

            list.add("");
        }
        else
        {
            list.add("-Hold shift for details-");
        }
    }

    @Override
    public void addTension(ItemStack is, int tension)
    {
        TensionHelper.addTension(is, tension);
    }

    @Override
    public void removeTension(ItemStack is, int tension)
    {
        TensionHelper.removeTension(is, tension);
    }

    @Override
    public int getMaxTimeSand()
    {
        return MechanicTweaker.MAX_TIME_SAND_TOOLS;
    }

    @Override
    public int getTimeSand(ItemStack is)
    {
        return TimeSandHelper.getTimeSand(is);
    }

    @Override
    public int addTimeSand(ItemStack is, int timeSand)
    {
        return TimeSandHelper.addTimeSand(is, timeSand, getMaxTimeSand());
    }

    @Override
    public int removeTimeSand(ItemStack is, int timeSand)
    {
        return TimeSandHelper.removeTimeSand(is, timeSand);
    }

    @Override
    public int removeTimeSandFromInventory(IInventory inventory, int timeSand)
    {
        return TimeSandHelper.removeTimeSandFromInventory(inventory, timeSand);
    }

    @Override
    public int getTimeSandFromInventory(IInventory inventory)
    {
        return TimeSandHelper.getTimeSandFromInventory(inventory);
    }

    @Override
    public void disassemble(World world, double x, double y, double z, ItemStack is)
    {
        if(world.isRemote)
        {
            return;
        }

        int maxTension = NBTHelper.getInt(is, NBTTags.MAX_TENSION);

        if(maxTension != 0)
        {
            ItemStack mainspring = new ItemStack(ModItems.mainspring);
            NBTHelper.setInteger(mainspring, NBTTags.MAX_TENSION, maxTension);
            NBTHelper.setInteger(mainspring, NBTTags.TENSION_ENERGY, 0);

            world.spawnEntity(new EntityItem(world, x, y, z, mainspring));
        }

        if(NBTHelper.hasTag(is, NBTTags.CLOCKWORK))
        {
            NBTTagList tagList = NBTHelper.getTagList(is, NBTTags.CLOCKWORK);
            if(tagList.tagCount() > 0)
            {
                ItemStack clockwork = new ItemStack(tagList.getCompoundTagAt(0));
                world.spawnEntity(new EntityItem(world, x, y, z, clockwork));
            }
        }

        NBTHelper.setInteger(is, NBTTags.TENSION_ENERGY, 0);
        NBTHelper.setInteger(is, NBTTags.MAX_TENSION, 0);
        NBTHelper.setInteger(is, NBTTags.QUALITY, 0);
        NBTHelper.setInteger(is, NBTTags.SPEED, 0);
        NBTHelper.setInteger(is, NBTTags.MEMORY, 0);
        NBTHelper.removeTag(is, NBTTags.CLOCKWORK);
        is.setItemDamage(is.getMaxDamage());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons()
    {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
//        this.itemIcon = registry.registerIcon(this.getTranslationKey().substring(this.getTranslationKey().indexOf(".") + 1));
    }

    @Override
    public void useTemporalAbility()
    {
        PacketHandler.INSTANCE.sendToServer(new MessageTemporalItemChange());
    }

    @Override
    public Item getItemChangeTo()
    {
        return ModItems.temporalClockworkPickaxe;
    }
}
