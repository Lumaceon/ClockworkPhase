package lumaceon.mods.clockworkphase.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
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
        if(!event.world.isRemote && event.harvester != null)
        {
            if(!event.isSilkTouching)
            {
                ItemStack[] pocketWatches = InventorySearchHelper.getPocketWatches(event.harvester.inventory);
                if(pocketWatches != null && ItemPocketWatch.doesActiveItemModuleExist(pocketWatches, ModItems.moduleFurnace))
                {
                    ArrayList<ItemStack> newDrops = new ArrayList<ItemStack>(event.drops.size() + 10);
                    for(int n = event.drops.size() - 1; n >= 0; n--)
                    {
                        ItemStack smeltedOutput = FurnaceRecipes.smelting().getSmeltingResult(event.drops.get(n).copy());

                        //Fortune code from BlockOre\\
                        int j = event.world.rand.nextInt(event.fortuneLevel + 2) - 1;
                        if (j < 0) { j = 0; }
                        int size = j + 1; //Modified to ignore quantity dropped, already handled by iterating through drops.
                        //Fortune code from BlockOre\\

                        if(smeltedOutput != null)
                        {
                            int timeSandRequirement = MechanicTweaker.TIME_SAND_COST_FURNACE_MODULE;
                            timeSandRequirement -= TimeSandHelper.removeTimeSandFromInventory(event.harvester.inventory, timeSandRequirement);
                            if(timeSandRequirement > 0)
                            {
                                return;
                            }

                            //Only drop 1 if the smelted item is a block or the same as the block broken
                            if(Item.getItemFromBlock(event.block).equals(smeltedOutput.getItem()))
                            {
                                size = 1;
                            }

                            for(int i = 0; i < size; i++)
                            {
                                newDrops.add(smeltedOutput.copy());
                            }
                            event.drops.remove(n);
                        }
                    }
                    for(int n = newDrops.size() - 1; n >= 0; n--)
                    {
                        float f = 0.7F;
                        double d0 = (double)(event.world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
                        double d1 = (double)(event.world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
                        double d2 = (double)(event.world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
                        EntityItem entityitem = new EntityItem(event.world, (double)event.x + d0, (double)event.y + d1, (double)event.z + d2, newDrops.get(n));
                        entityitem.delayBeforeCanPickup = 10;
                        event.world.spawnEntityInWorld(entityitem);
                    }
                }
                else if(pocketWatches != null && ItemPocketWatch.doesActiveItemModuleExist(pocketWatches, ModItems.moduleSilkTouch))
                {
                    if(event.block.canSilkHarvest(event.world, event.harvester, event.x, event.y, event.z, event.blockMetadata))
                    {
                        int timeSandRequirement = MechanicTweaker.TIME_SAND_COST_SILK_MODULE;
                        timeSandRequirement -= TimeSandHelper.removeTimeSandFromInventory(event.harvester.inventory, timeSandRequirement);
                        if(timeSandRequirement > 0)
                        {
                            return;
                        }

                        ItemStack result = new ItemStack(event.block, 1, event.blockMetadata);

                        event.drops.clear();
                        float f = 0.7F;
                        double d0 = (double)(event.world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
                        double d1 = (double)(event.world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
                        double d2 = (double)(event.world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
                        EntityItem entityitem = new EntityItem(event.world, (double)event.x + d0, (double)event.y + d1, (double)event.z + d2, result);
                        entityitem.delayBeforeCanPickup = 10;
                        event.world.spawnEntityInWorld(entityitem);
                    }
                }
            }
        }
    }

}
