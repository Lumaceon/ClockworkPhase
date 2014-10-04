package lumaceon.mods.clockworkphase.item.component;

import net.minecraft.item.ItemStack;

public interface IBaseComponent
{
    /**
     * This marks this component as a speed enhancement item. Returning false
     * will cause the crafting recipes to ignore speed.
     *
     * @return Whether or not this item enhances speed.
     */
    public boolean isComponentSpeedy(ItemStack is);

    /**
     * This marks this component as a quality enhancement item. Returning false
     * will cause the crafting recipes to ignore quality.
     *
     * @return Whether or not this item enhances quality.
     */
    public boolean isComponentQuality(ItemStack is);

    /**
     * This marks this component as a memory item. Returning false will cause
     * the crafting recipes to ignore memory values.
     *
     * @return Whether or not this item holds memories.
     */
    public boolean isComponentMemory(ItemStack is);

    /**
     * What gear quality determines is dependant on the construct using it.
     * In most cases, quality will determine the maximum capabilities of an item, such
     * as a pickaxe's effective harvesting level.
     *
     * A general guide to gear quality:
     * 0 - You tried to build a gear out of dirt and the result was a pile of dirt.
     * 10 - Gold gear that you made because gold. However, gold ended up being too soft a metal.
     * 25 - Iron gear of average quality.
     * 35 - Brass gear of slightly above-average quality.
     * 40 - Bronze gear of rather decently above-average quality.
     * 50 - Steel gear of excellent quality.
     * 70 - Diamond gear of rather outstanding though not entirely unbeatable quality.
     * 70+ - Very high tier mod materials.
     *
     * @return The quality of the gear. This is dependant on the item using it.
     */
    public int getGearQuality(ItemStack is);

    /**
     * Gear speed, along with quality, is used different ways depending on the item.
     * Typically, a higher gear speed will exhaust the mainspring's tension at a
     * faster rate, but will offset this with a higher power.
     *
     * A general guide to gear speed:
     * 0 - You built a gear out of dirt and it flops around like a whale on a small island.
     * 5 - Gold gear.
     * 15 - Iron gear.
     * 20 - Bronze gear.
     * 25 - Brass gear.
     * 30 - Steel gear.
     * 50 - Diamond gear.
     * 50+ - Very high tier mod materials.
     *
     * @return The speed of the gear. This is dependant on the item using it.
     */
    public int getGearSpeed(ItemStack is);

    /**
     * Memory value represents the total memories that this item holds. In many cases, this
     * value may return the result of an NBT value held by the component itself. Memory effects
     * often increase the power that the item gains as a result of the player having more XP.
     *
     * Memory typically adds a "secondary benefit" to the resultant item. In the case of a
     * clockwork pickaxe, you may be given the chance to double-harvest, which is completely
     * separate from any fortune effect the pickaxe may be enchanted with.
     *
     * @return The amount memories of past times that this memory item represents.
     */
    public int getMemoryValue(ItemStack is);
}
