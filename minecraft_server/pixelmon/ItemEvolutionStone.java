package pixelmon;

import java.util.Random;

import net.minecraft.src.Item;

public class ItemEvolutionStone extends Item{
	
	private EnumEvolutionStone stoneType;
	
	public ItemEvolutionStone(int id, EnumEvolutionStone stoneType){
		super(id);
		this.stoneType = stoneType;
		
	}
	
	public EnumEvolutionStone getType(){
		return stoneType;
	}


}
