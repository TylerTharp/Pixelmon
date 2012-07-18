package pixelmon.Pokemon;

import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import net.minecraft.src.World;



public class EntityRattata extends EntityGroundPixelmon
{

	public EntityRattata(World par1World) 
	{
		super(par1World);
		init();
	}

	public void init() 
	{
		name = "Rattata";
		isImmuneToFire = false;
		super.init();
	}

	@Override
	public void evolve() 
	{
	}
	
}