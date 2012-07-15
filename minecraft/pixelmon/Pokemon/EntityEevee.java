package pixelmon.Pokemon;

import pixelmon.entities.EntityGroundPixelmon;
import net.minecraft.src.World;


public class EntityEevee extends EntityGroundPixelmon
{

	public EntityEevee(World par1World) 
	{
		super(par1World);
		texture = "/pixelmon/image/eevee.png";
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