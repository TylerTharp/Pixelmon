package net.minecraft.src.gapi;

import java.util.ArrayList;

import net.minecraft.src.Block;

/**
 * Simple utilities for various purposes
 * @author Grethen
 */
public class GUtils
{
	/**
	 * The list of all Blocks
	 */
	public static ArrayList<GBlock> blockList = initBlockList();
	
	/**
	 * Puts all the Blocks into a list and returns it.
	 * @return the list of all the Blocks
	 */
	private static ArrayList<GBlock> initBlockList()
	{
		ArrayList<GBlock> a = new ArrayList<GBlock>();
		/*
		a.add(new GBlock("stone", Block.stone));
		a.add(new GBlock("grass", Block.grass));
		a.add(new GBlock("dirt", Block.dirt));
		a.add(new GBlock("cobblestone", Block.cobblestone));
		a.add(new GBlock("woodplanks", Block.planks));
		a.add(new GBlock("sapling", Block.sapling));
		a.add(new GBlock("bedrock", Block.bedrock));
		a.add(new GBlock("water", Block.waterStill));
		a.add(new GBlock("lava", Block.lavaStill));
		*/
		return a;
	}
	
	/**
	 * Gets a Block by its id
	 * @param id - The id of the desired Block
	 * @return - The corresponding Block
	 */
	public static Block getBlock(int id)
	{
		for(GBlock g : blockList)
			if(g.id == id)
				return g.block;
		return Block.stone;
	}
}