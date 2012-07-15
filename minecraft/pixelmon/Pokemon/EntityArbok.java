package pixelmon.Pokemon;

import pixelmon.entities.EntityGroundPixelmon;
import net.minecraft.src.World;


public class EntityArbok extends EntityGroundPixelmon
{

	public EntityArbok(World par1World) 
	{
		super(par1World);
		texture = "/pixelmon/image/arbok.png";
		init();
	}

	public void init() 
	{
		name = "Arbok";
		isImmuneToFire = false;
		super.init();
	}


	public void evolve() 
	{
		
	}
	
	
}