package pixelmon.enums;

import net.minecraft.src.BiomeGenBase;

public enum EnumGui {
	ChoosePokemon(0),
	ChooseAttack(1),
	ChooseStarter(2),
	FaintedChoice(3),
	Healer(4),
	Pokedex(5),
	PokeChecker(6),
	RenamePokemon(7), 
	LearnMove(8), 
	PC(9);
	
	private int index;
	
	private EnumGui(int i)
	{
		index=i;
	}
	
	public Integer getIndex()
	{
		return index;
	}
}
