package pixelmon.enums;

import java.util.ArrayList;
import java.util.Iterator;

import net.minecraft.src.BiomeGenBase;


public enum EnumBiomes
{
	
	Beach("Beach", BiomeGenBase.beach),
	Desert("Desert", BiomeGenBase.desert),
	DesertHills("DesertHills", BiomeGenBase.desertHills),
	Forest("Forest", BiomeGenBase.forest),
	ForestHills("ForestHills", BiomeGenBase.forestHills),
	IcePlains("IcePlains", BiomeGenBase.icePlains),
	IceMountain("IceMountains", BiomeGenBase.iceMountains),
	Jungle("Jungle", BiomeGenBase.jungle),
	JungleHills("JungleHills", BiomeGenBase.jungleHills),
	Mountain("ExtremeHills", BiomeGenBase.extremeHills),
	MountainEdge("ExtremeHillsEdge", BiomeGenBase.extremeHillsEdge),
	Mushroom("MushroomIsland", BiomeGenBase.mushroomIsland),
	MushroomShore("MushroomIslandShore", BiomeGenBase.mushroomIslandShore),
	Plains("Plains", BiomeGenBase.plains),
	Swamp("SwampLand", BiomeGenBase.swampland),
	Taiga("Taiga", BiomeGenBase.taiga),
	TaigaHills("TaigaHills", BiomeGenBase.taigaHills),
	
	FrozenOcean("FrozenOcean", BiomeGenBase.frozenOcean),
	FrozenRiver("FrozenRiver", BiomeGenBase.frozenRiver),
	Ocean("Ocean", BiomeGenBase.ocean),
	River("River", BiomeGenBase.river),
	
	Nether("Hell", BiomeGenBase.hell),
	Sky("Sky", BiomeGenBase.sky);
	
	private BiomeGenBase biome;
	private String name;
	
	private EnumBiomes(String s, BiomeGenBase b)
	{
		name = s;
		biome = b;
	}
	
	public BiomeGenBase getBiome()
	{
		return biome;
	}
	
	public String getName()
	{
		return name;
	}
	
	public static EnumBiomes parseBiome(String s)
	{
 		ArrayList<EnumBiomes> list = getAllBiomes();
 		Iterator<EnumBiomes> it = list.iterator();
 		while(it.hasNext())
 		{
 			EnumBiomes t = it.next();
 			if(t.name.equalsIgnoreCase(s))
 				return t;
 		}
 		return Forest;
	}
	
	public static ArrayList<EnumBiomes> getAllBiomes()
	{
 		ArrayList<EnumBiomes> list = new ArrayList<EnumBiomes>();
 		EnumBiomes[] t = values();
 		for(int i = 0; i < t.length; i++)
 			list.add(t[i]);
 		return list;
	}
}