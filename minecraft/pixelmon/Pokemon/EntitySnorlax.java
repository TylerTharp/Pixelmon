package pixelmon.Pokemon;

import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import net.minecraft.src.World;

public class EntitySnorlax extends EntityGroundPixelmon
{
	
	public EntitySnorlax(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		name = "Snorlax";
		isImmuneToFire = false;
		super.init();
	}
	
	public void evolve() 
	{		
		
	}
}
