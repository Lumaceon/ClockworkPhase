package lumaceon.mods.clockworkphase.block.extractor;

import lumaceon.mods.clockworkphase.block.tileentity.TileEntityExtractor;
import lumaceon.mods.clockworkphase.lib.Phases;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashMap;

public class ExtractorAreas
{
    public static final HashMap[] EXTRACTORS =
            {
                    new HashMap<Integer, ExtractorAreas>(10),
                    new HashMap<Integer, ExtractorAreas>(10),
                    new HashMap<Integer, ExtractorAreas>(10),
                    new HashMap<Integer, ExtractorAreas>(10),
                    new HashMap<Integer, ExtractorAreas>(10),
                    new HashMap<Integer, ExtractorAreas>(10),
                    new HashMap<Integer, ExtractorAreas>(10),
                    new HashMap<Integer, ExtractorAreas>(10)
            };

    public HashMap<Integer, ExtractorArea> areas = new HashMap<Integer, ExtractorArea>(100);

    /**
     * @return The first valid ExtractorArea that contains the coordinate. Null if none present.
     */
    public ExtractorArea getValidArea(World world, int x, int y, int z)
    {
        ExtractorArea area = null;
        boolean found = false;
        for(int n = 0; n < this.areas.values().toArray().length && !found; n++)
        {
            area = (ExtractorArea)this.areas.values().toArray()[n];
            if(x >= area.lowX && x <= area.highX)
            {
                if(y >= area.lowY && y <= area.highY)
                {
                    if(z >= area.lowZ && z <= area.highZ)
                    {
                        found = true;
                    }
                }
            }
        }

        if(found)
        {
            TileEntity te = world.getTileEntity(new BlockPos(area.extractorX, area.extractorY, area.extractorZ));
            if(te instanceof TileEntityExtractor)
            {
                return area;
            }
            else
            {
                removeArea(area.id);
                return getValidArea(world, x, y, z);
            }
        }
        else
        {
            return null;
        }
    }

    /**
     * Checks each area's extractor location against the given coordinates.
     * Mostly used to avoid duplicate areas.
     * @param world The world to check for the area.
     * @param x The x coordinate to check for the extractor.
     * @param y The y coordinate to check for the extractor.
     * @param z The z coordinate to check for the extractor.
     * @return Whether or not there's an area with an extractor at the given coordinates.
     */
    public static boolean doesAreaExist(World world, int x, int y, int z)
    {
        for(int n = 0; n < ExtractorAreas.EXTRACTORS.length; n++) //For every element.
        {
            ExtractorAreas areas = (ExtractorAreas) ExtractorAreas.EXTRACTORS[n].get(world.provider.getDimension()); //Gets the areas for the world.
            if (areas != null)
            {
                for(int g = 0; g < areas.areas.size(); g++) //For each area.
                {
                    ExtractorArea area = areas.areas.get(g);
                    if(area != null)
                    {
                        if(area.extractorX == x && area.extractorY == y && area.extractorZ == z) //There is an area with the given coordinates.
                        {
                            return true;
                        }
                    }
                }
            }
        }
        return false; //Never returned true, thus defaulting to false.
    }

    public int addArea(int extractorX, int extractorY, int extractorZ, int lowX, int lowY, int lowZ, int highX, int highY, int highZ)
    {
        int id = getNextFreeId();
        areas.put(id, new ExtractorArea(extractorX, extractorY, extractorZ, lowX, lowY, lowZ, highX, highY, highZ, id));
        return id;
    }

    public Integer getNextFreeId()
    {
        int n = -1;
        boolean found = false;
        while(n < 100000 && !found)
        {
            n++;
            found = !areas.containsKey(Integer.valueOf(n));
        }
        return n;
    }

    public void removeArea(Integer id)
    {
        areas.remove(id);
    }

    public static ExtractorAreas getAreasFromWorld(World world, Phases phase)
    {
        return getAreasFromWorldId(world.provider.getDimension(), phase);
    }

    public static ExtractorAreas getAreasFromWorldId(int id, Phases phase)
    {
        HashMap<Integer, ExtractorAreas> extractorWorlds = EXTRACTORS[phase.ordinal()];
        ExtractorAreas extractorAreas = extractorWorlds.get(id);
        if(extractorAreas == null)
        {
            extractorAreas = new ExtractorAreas();
            extractorWorlds.put(id, extractorAreas);
        }
        return extractorAreas;
    }

    /**
     * All coordinate areas are inclusive.
     */
    public class ExtractorArea
    {
        public int extractorX, extractorY, extractorZ, highX, highY, highZ, lowX, lowY, lowZ, id;

        public ExtractorArea(int extractorX, int extractorY, int extractorZ, int lowX, int lowY, int lowZ, int highX, int highY, int highZ, int id)
        {
            this.extractorX = extractorX;
            this.extractorY = extractorY;
            this.extractorZ = extractorZ;
            this.highX = highX;
            this.highY = highY;
            this.highZ = highZ;
            this.lowX = lowX;
            this.lowY = lowY;
            this.lowZ = lowZ;
            this.id = id;
        }
    }
}
