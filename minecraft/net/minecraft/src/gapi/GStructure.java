package net.minecraft.src.gapi;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.src.Block;
import net.minecraft.src.World;

public class GStructure
{
	
	private static final String rulePrefix = "Rule: ";
	private static final String layerHeader = "Layer: ";
	private static final String layerFooter = "End Layer";
	private static final String heightLimitPrefix = "Height Limit: ";
	private static final String depthLimitPrefix = "Depth Limit: ";
	
	private File path;
	private int heightLimit = 256;
	private int depthLimit = 0;
	private ArrayList<ArrayList<ArrayList<Block>>> structure;
	private HashMap<String, Block> rules;
	private ArrayList<ArrayList<Block>> buffer;
	
	public GStructure(String f)
	{
		path = GIO.getFile(f);
		init();
	}
	
	public GStructure(String pd, String f)
	{
		path = GIO.getFile(pd, f);
		init();
	}
	
	private void init()
	{
		rules = new HashMap<String, Block>();
		structure = new ArrayList<ArrayList<ArrayList<Block>>>();
		buffer = new ArrayList<ArrayList<Block>>();
		boolean isOnLayer = false;
		//boolean isNewLayer = false;
		ArrayList<String> s = GIO.readFile(path);
		for(String s1 : s)
		{
			if(s1.startsWith(rulePrefix))
				addRule(s1.replaceFirst(rulePrefix, ""));
			else if(s1.startsWith(heightLimitPrefix))
				heightLimit = Integer.parseInt(s1.replaceFirst(heightLimitPrefix, ""));
			else if(s1.startsWith(depthLimitPrefix))
				depthLimit = Integer.parseInt(s1.replaceFirst(depthLimitPrefix, ""));
			else if(s1.equalsIgnoreCase(layerFooter))
			{
				isOnLayer = false;
			}
			else if(s1.startsWith(layerHeader))
			{
				if(isOnLayer)
					structure.add(buffer);
				isOnLayer = true;
			} else
			{
				ArrayList<Block> a = new ArrayList<Block>();
				for(String s2 : s1.split(" "))
					try
					{
						Block b = getBlock(s2);
						a.add(b);
					} catch(Exception e)
					{
						System.out.println("Illegal Block Argument " + s2 + " in the line " + s1);
					}
				buffer.add(a);
			}
		}
	}
	
	public void generate(int x, int y, int z, World world)
	{
		if(!(y > depthLimit && y < heightLimit))
			return;
		//Do generate here
	}
	
	public Block getBlock(String s) throws Exception
	{
		Block b = rules.get(s);
		if(b == null)
			throw new Exception();
		return b;
	}
	
	public void addRule(String rule)
	{
		String s = rule.split("=")[0];
		Block b = GUtils.getBlock(Integer.parseInt(rule.split("=")[1]));
		addRule(s, b);
	}
	
	public void addRule(String s, Block b)
	{
		rules.put(s, b);
	}
}