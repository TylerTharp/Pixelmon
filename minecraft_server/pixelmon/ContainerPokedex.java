package pixelmon;

import net.minecraft.src.*;

public class ContainerPokedex extends Container
{

	private Pokedex pokedex;
	private EntityLiving selectedEntity;
	private PokedexEntry selectedEntry;
	
	public ContainerPokedex(Pokedex p)
	{
		pokedex = p;
		//selectedEntry = p.getEntry(0);
		//selectedEntity = selectedEntry.getEntity(true);
	}
	
	public Pokedex getPokedex()
	{
		return pokedex;
	}
	
	public boolean canInteractWith(EntityPlayer var1) 
	{
		return true;
	}
	
}