package lumaceon.mods.clockworkphase.handler;

import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.FillBucketEvent;

import java.util.HashMap;
import java.util.Map;

public class BucketHandler
{
    public static BucketHandler INSTANCE = new BucketHandler();
    public Map<Block, Item> buckets = new HashMap<Block, Item>();

    private BucketHandler() {}

    @SubscribeEvent
    public void onBucketFill(FillBucketEvent event) {

        if (event.getTarget() == null || event.getTarget().typeOfHit != RayTraceResult.Type.BLOCK) {
            return;
        }

        ItemStack stack = fillCustomBucket(event.getWorld(), event.getTarget());

        if(stack.isEmpty()) { return; }

        event.setFilledBucket(stack);
        event.setResult(Event.Result.ALLOW);
    }

    private ItemStack fillCustomBucket(World world, RayTraceResult pos) {

        Block block = world.getBlockState(pos.getBlockPos()).getBlock();
        Item bucket = buckets.get(block);

        if(bucket != null && world.getBlockState(pos.getBlockPos()).getBlock().getMetaFromState(world.getBlockState(pos.getBlockPos())) == 0)
        {
            world.setBlockToAir(pos.getBlockPos());
            return new ItemStack(bucket);
        }
        else
        {
            return ItemStack.EMPTY;
        }
    }
}
