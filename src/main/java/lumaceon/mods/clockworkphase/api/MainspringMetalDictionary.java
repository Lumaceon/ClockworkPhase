package lumaceon.mods.clockworkphase.api;

import lumaceon.mods.clockworkphase.lib.MechanicTweaker;

import java.util.ArrayList;

public class MainspringMetalDictionary
{
    public ArrayList<MainspringMetal> mainspringMetals = new ArrayList<MainspringMetal>(50);

    public void init()
    {
        int multiplier = MechanicTweaker.MAINSPRING_TENSION_MUILTIPLIER;

        MainspringMetal ingotIron = new MainspringMetal("ingotIron", 250 * multiplier);
        mainspringMetals.add(ingotIron);
        MainspringMetal blockIron = new MainspringMetal("blockIron", 2250 * multiplier);
        mainspringMetals.add(blockIron);
        MainspringMetal ingotBrass = new MainspringMetal("ingotBrass", 350 * multiplier);
        mainspringMetals.add(ingotBrass);
        MainspringMetal blockBrass = new MainspringMetal("blockBrass", 3150 * multiplier);
        mainspringMetals.add(blockBrass);
        MainspringMetal ingotTemporal = new MainspringMetal("ingotTemporal", 2500 * multiplier);
        mainspringMetals.add(ingotTemporal);
        MainspringMetal ingotGold = new MainspringMetal("ingotGold", 100 * multiplier);
        mainspringMetals.add(ingotGold);
        MainspringMetal ingotThaumium = new MainspringMetal("ingotThaumium", 700 * multiplier);
        mainspringMetals.add(ingotThaumium);
        MainspringMetal ingotSteel = new MainspringMetal("ingotSteel", 750 * multiplier);
        mainspringMetals.add(ingotSteel);
        MainspringMetal blockSteel = new MainspringMetal("blockSteel", 6750 * multiplier);
        mainspringMetals.add(blockSteel);
        MainspringMetal ingotLead = new MainspringMetal("ingotLead", 200 * multiplier);
        mainspringMetals.add(ingotLead);
        MainspringMetal blockLead = new MainspringMetal("blockLead", 1800 * multiplier);
        mainspringMetals.add(blockLead);
        MainspringMetal ingotCopper = new MainspringMetal("ingotCopper", 100 * multiplier);
        mainspringMetals.add(ingotCopper);
        MainspringMetal blockCopper = new MainspringMetal("blockCopper", 900 * multiplier);
        mainspringMetals.add(blockCopper);
        MainspringMetal ingotTin = new MainspringMetal("ingotTin", 100 * multiplier);
        mainspringMetals.add(ingotTin);
        MainspringMetal blockTin = new MainspringMetal("blockTin", 900 * multiplier);
        mainspringMetals.add(blockTin);
        MainspringMetal ingotBronze = new MainspringMetal("ingotBronze", 300 * multiplier);
        mainspringMetals.add(ingotBronze);
        MainspringMetal blockBronze = new MainspringMetal("blockBronze", 2700 * multiplier);
        mainspringMetals.add(blockBronze);
        MainspringMetal ingotSilver = new MainspringMetal("ingotSilver", 300 * multiplier);
        mainspringMetals.add(ingotSilver);
        MainspringMetal blockSilver = new MainspringMetal("blockSilver", 2700 * multiplier);
        mainspringMetals.add(blockSilver);
        MainspringMetal ingotCobalt = new MainspringMetal("ingotCobalt", 500 * multiplier);
        mainspringMetals.add(ingotCobalt);
        MainspringMetal blockCobalt = new MainspringMetal("blockCobalt", 4500 * multiplier);
        mainspringMetals.add(blockCobalt);
        MainspringMetal ingotArdite = new MainspringMetal("ingotArdite", 700 * multiplier);
        mainspringMetals.add(ingotArdite);
        MainspringMetal blockArdite = new MainspringMetal("blockArdite", 6300 * multiplier);
        mainspringMetals.add(blockArdite);
        MainspringMetal ingotManyullyn = new MainspringMetal("ingotManyullyn", 1000 * multiplier);
        mainspringMetals.add(ingotManyullyn);
        MainspringMetal blockManyullyn = new MainspringMetal("blockManyullyn", 9000 * multiplier);
        mainspringMetals.add(blockManyullyn);
        MainspringMetal ingotAluminium = new MainspringMetal("ingotAluminium", 200 * multiplier);
        mainspringMetals.add(ingotAluminium);
        MainspringMetal blockAluminium = new MainspringMetal("blockAluminium", 1800 * multiplier);
        mainspringMetals.add(blockAluminium);
        MainspringMetal ingotAluminiumBrass = new MainspringMetal("ingotAluminiumBrass", 350 * multiplier);
        mainspringMetals.add(ingotAluminiumBrass);
        MainspringMetal blockAluminiumBrass = new MainspringMetal("blockAluminiumBrass", 3150 * multiplier);
        mainspringMetals.add(blockAluminiumBrass);
        MainspringMetal ingotAlumite = new MainspringMetal("ingotAlumite", 500 * multiplier);
        mainspringMetals.add(ingotAlumite);
        MainspringMetal blockAlumite = new MainspringMetal("blockAlumite", 4500 * multiplier);
        mainspringMetals.add(blockAlumite);
        MainspringMetal ingotElectrum = new MainspringMetal("ingotElectrum", 400 * multiplier);
        mainspringMetals.add(ingotElectrum);
        MainspringMetal blockElectrum = new MainspringMetal("blockElectrum", 3600 * multiplier);
        mainspringMetals.add(blockElectrum);
        MainspringMetal ingotNickel = new MainspringMetal("ingotNickel", 300 * multiplier);
        mainspringMetals.add(ingotNickel);
        MainspringMetal blockNickel = new MainspringMetal("blockNickel", 2700 * multiplier);
        mainspringMetals.add(blockNickel);
        MainspringMetal ingotInvar = new MainspringMetal("ingotInvar", 500 * multiplier);
        mainspringMetals.add(ingotInvar);
        MainspringMetal blockInvar = new MainspringMetal("blockInvar", 4500 * multiplier);
        mainspringMetals.add(blockInvar);
    }
}

