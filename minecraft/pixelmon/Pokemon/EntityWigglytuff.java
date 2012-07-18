package pixelmon.Pokemon;

import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import net.minecraft.src.World;

public class EntityWigglytuff extends EntityGroundPixelmon
{
	
	public EntityWigglytuff(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		name = "Wigglytuff";
		isImmuneToFire = false;
		super.init();
	}
	
	public void evolve() 
	{		
		
	}
}
