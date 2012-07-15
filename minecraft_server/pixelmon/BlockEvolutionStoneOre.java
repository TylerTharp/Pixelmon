package pixelmon;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.Material;
import net.minecraft.src.mod_Pixelmon;

public class BlockEvolutionStoneOre extends Block{
	
	private int type;
	
	public BlockEvolutionStoneOre(int id, int type){
		super(id, Material.rock);
		this.type = type;
	}
	
	
	public int idDropped(int i, Random rand, int j){
		int result = 0;
		switch(type){
		case 0:
			result = mod_Pixelmon.thunderStoneShard.shiftedIndex;
			break;
		case 1:
			result = mod_Pixelmon.leafStoneShard.shiftedIndex;
		}
		
		return result;
	}
	
	public int quantityDropped(Random rand){
		return rand.nextInt(3) + 2;
	}

}
