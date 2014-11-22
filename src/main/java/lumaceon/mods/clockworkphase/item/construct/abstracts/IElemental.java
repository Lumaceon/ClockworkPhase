package lumaceon.mods.clockworkphase.item.construct.abstracts;

import lumaceon.mods.clockworkphase.lib.Phases;
import net.minecraft.entity.item.EntityItem;

public interface IElemental
{
    /**
     * Method for changing an item's type via celestial compass.
     * Overrides should always call the super class.
     * @param phase The phase that the item was dropped on.
     * @param item The item to be elementized.
     */
    public void elementize(Phases phase, EntityItem item);

    /**
     * Genericizes the item dropped in the center of the celestial compass.
     * Overrides should always call the super class.
     * @param item The item to be genericized.
     */
    public void unelementize(EntityItem item);
}
