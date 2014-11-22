package lumaceon.mods.clockworkphase.block.tileentity;

public interface ITimeSandTile
{
    public int getMaxTimeSandCapacity();

    /**
     * Method used to add time sand to this tile entity.
     * @param amount
     * @return The amount of overflow that couldn't be added to this tile entity.
     */
    public int addTimeSand(int amount);

    /**
     * Method used to remove time sand from this tile entity.
     * @param amount The amount requested.
     * @return The amount of time sand that could actually be removed.
     */
    public int removeTimeSand(int amount);

    /**
     * To set the time sand client-side.
     * @param amount
     */
    public void setTimeSandUnsynced(int amount);

    public int getTimeSand();
}
