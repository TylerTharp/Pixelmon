package pixelmon.Pokemon;

import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import net.minecraft.src.World;


public class EntityEevee extends EntityGroundPixelmon
{

	public EntityEevee(World par1World) 
	{
		super(par1World);
		init();
	}

	public void init() 
	{
		name = "Eevee";
		isImmuneToFire = false;
		super.init();
	}


	public void evolve() 
	{
		// TODO Auto-generated method stub
	}
	
	
}