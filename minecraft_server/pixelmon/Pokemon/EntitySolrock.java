package pixelmon.Pokemon;

import pixelmon.entities.pixelmon.BaseEntityPixelmon;
import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import net.minecraft.src.World;

public class EntitySolrock  extends EntityGroundPixelmon
{
	
	public EntitySolrock(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		name = "Solrock";
		isImmuneToFire = true;
		doesHover = true;
		hoverHeight=1f;
		super.init();
	}
	
	public void evolve() 
	{		
	
	}
	
	public boolean getCanSpawnHere()
	{
		if(worldObj.isDaytime() ) {
			return true;
		}
		
		return false;
	}
	
	
	
	
	
	
	
	
}
