package lumaceon.mods.clockworkphase.handler;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import lumaceon.mods.clockworkphase.init.ModItems;
import lumaceon.mods.clockworkphase.item.construct.pocketwatch.ItemPocketWatch;
import lumaceon.mods.clockworkphase.lib.MechanicTweaker;
import lumaceon.mods.clockworkphase.util.InventorySearchHelper;
import lumaceon.mods.clockworkphase.util.TimeSandHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.event.world.BlockEvent;

import java.util.ArrayList;

public class WorldHandler
{
    @SubscribeEvent
    public void onBlockHarvested(BlockEvent.HarvestDropsEvent event)
    {
        if(!event.getWorld().isRemote && event.getHarvester() != null)
        {
            if(!event.isSilkTouching())
            {
                ItemStack[] pocketWatches = InventorySearchHelper.getPocketWatches(event.getHarvester().inventory);
                if(pocketWatches != null && ItemPocketWatch.doesActiveItemModuleExist(pocketWatches, ModItems.moduleFurnace))
                {
                    ArrayList<ItemStack> newDrops = new ArrayList<ItemStack>(event.getDrops().size() + 10);
                    for(int n = event.getDrops().size() - 1; n >= 0; n--)
                    {
                        ItemStack smeltedOutput = FurnaceRecipes.instance().getSmeltingResult(event.getDrops().get(n).copy());

                        //Fortune code from BlockOre\\
                        int j = event.getWorld().rand.nextInt(event.getFortuneLevel() + 2) - 1;
                        if (j < 0) { j = 0; }
                        int size = j + 1; //Modified to ignore quantity dropped, already handled by iterating through drops.
                        //Fortune code from BlockOre\\

                        if(!smeltedOutput.isEmpty())
                        {
                            int timeSandRequirement = MechanicTweaker.TIME_SAND_COST_FURNACE_MODULE;
                            timeSandRequirement -= TimeSandHelper.removeTimeSandFromInventory(event.getHarvester().inventory, timeSandRequirement);
                            if(timeSandRequirement > 0)
                            {
                                return;
                            }

                            //Only drop 1 if the smelted item is a block or the same as the block broken
                            if(Item.getItemFromBlock(event.getState().getBlock()).equals(smeltedOutput.getItem()))
                            {
                                size = 1;
                            }

                            for(int i = 0; i < size; i++)
                            {
                                newDrops.add(smeltedOutput.copy());
                            }
                            event.getDrops().remove(n);
                        }
                    }
                    for(int n = newDrops.size() - 1; n >= 0; n--)
                    {
                        float f = 0.7F;
                        double d0 = (double)(event.getWorld().rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
                        double d1 = (double)(event.getWorld().rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
                        double d2 = (double)(event.getWorld().rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
                        EntityItem entityitem = new EntityItem(event.getWorld(), (double)event.getPos().getX() + d0, (double)event.getPos().getY() + d1, (double)event.getPos().getZ() + d2, newDrops.get(n));
                        entityitem.setPickupDelay(10);
                        event.getWorld().spawnEntity(entityitem);
                    }
                }
                else if(pocketWatches != null && ItemPocketWatch.doesActiveItemModuleExist(pocketWatches, ModItems.moduleSilkTouch))
                {
                    if(event.getState().getBlock().canSilkHarvest(event.getWorld(), event.getPos(), event.getState(), event.getHarvester()))
                    {
                        int timeSandRequirement = MechanicTweaker.TIME_SAND_COST_SILK_MODULE;
                        timeSandRequirement -= TimeSandHelper.removeTimeSandFromInventory(event.getHarvester().inventory, timeSandRequirement);
                        if(timeSandRequirement > 0)
                        {
                            return;
                        }

                        ItemStack result = new ItemStack(event.getState().getBlock(), 1, event.getState().getBlock().getMetaFromState(event.getState()));

                        event.getDrops().clear();
                        float f = 0.7F;
                        double d0 = (double)(event.getWorld().rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
                        double d1 = (double)(event.getWorld().rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
                        double d2 = (double)(event.getWorld().rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
                        EntityItem entityitem = new EntityItem(event.getWorld(), (double)event.getPos().getX() + d0, (double)event.getPos().getY() + d1, (double)event.getPos().getZ() + d2, result);
                        entityitem.setPickupDelay(10);
                        event.getWorld().spawnEntity(entityitem);
                    }
                }
            }
        }
    }

}
