package pixelmon.gui.pokedex;

import java.util.ArrayList;
import java.util.HashMap;
import java.sql.*;

import pixelmon.database.DatabaseHelper;
import pixelmon.enums.EnumPixelmonRarity;

//import net.minecraft.src.mod_Pixelmon;
import net.minecraft.src.forge.ObjectPair;

public class Pokedex 
{

	private HashMap<Integer, String> numToName = new HashMap<Integer, String>();
	private HashMap<String, Integer> nameToNum = new HashMap<String, Integer>();
	private HashMap<String, PokedexEntry> pokedexMap = new HashMap<String, PokedexEntry>();
	private ArrayList<ObjectPair<Integer, PokedexEntry>> pokedex = new ArrayList<ObjectPair<Integer, PokedexEntry>>();
	private ArrayList<PokedexEntry> pokedexList = new ArrayList<PokedexEntry>();
	
	public Pokedex()
	{
		pokedexMap.put("???", new PokedexEntry(0, "???", "").setHeightAndWeight(12, 0));
		ResultSet r = DatabaseHelper.getResultSet("select * from Pixelmon");
		try 
		{
			while(r.next())
			{
				String s = r.getString("Name");
				int i = r.getInt("NationalPokedexNumber");
				if(i == 0)
					continue;
				float d = r.getFloat("Height");
				float d1 = r.getFloat("Weight");
				String rarity = EnumPixelmonRarity.EnumPixelmonRarityLevel(r.getInt("Rarity")).toString();
				numToName.put(i, s);
				nameToNum.put(s, i);
				PokedexEntry p = new PokedexEntry(i+1, s, r.getString("Description")).setHeightAndWeight(d, d1);
				p.rarity = rarity;
				p.name=s;
				//PokedexEntry RareTest = new PokedexEntry(i+1, s, r.getString("Rarity")).setHeightAndWeight(d, d1);
				pokedexMap.put(s, p);
				//pokedexMap.put(s, RareTest);
				pokedex.add(new ObjectPair<Integer, PokedexEntry>(i, p));
				pokedexList.add(p);
				//pokedexList.add(RareTest);
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		DatabaseHelper.finish(r);
	}
	
	public int size()
	{
		return pokedexMap.size();
	}
	
	public String getFromPokedex(int i)
	{
		return numToName.get(i);
	}
	
	public int getFromPokedex(String s)
	{
		return nameToNum.get(s);
	}
	
	public PokedexEntry getEntry(String s)
	{
		return getEntry(nameToNum.get(s));
	}
	
	public PokedexEntry getEntry(int i)
	{
		PokedexEntry e1 = pokedexMap.get("???");
		e1.pokedexNumber= i+1;
		for(ObjectPair<Integer, PokedexEntry> o : pokedex)
		{
			PokedexEntry e = o.getValue2();
			int i1 = o.getValue1().intValue();
			if(i1 == i)
			{
				e1 = e;
				break;
			}
				
		}
		return e1;
	}
}