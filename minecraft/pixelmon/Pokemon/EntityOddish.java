package pixelmon.Pokemon;

import pixelmon.entities.BaseEntityPixelmon;
import pixelmon.entities.EntityGroundPixelmon;
import net.minecraft.src.World;


public class EntityOddish extends EntityGroundPixelmon
{

	public EntityOddish(World par1World) 
	{
		super(par1World);
		texture = "/pixelmon/image/oddish.png";
		init();
	}
	
	public void init() 
	{
		name = "Oddish";
		isImmuneToFire = false;
		super.init();
	}

	public void evolve() 
	{
	}
	
}