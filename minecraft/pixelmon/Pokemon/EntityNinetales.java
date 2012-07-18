package pixelmon.Pokemon;

import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import net.minecraft.src.World;

public class EntityNinetales  extends EntityGroundPixelmon
{
	
	public EntityNinetales(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		name = "Ninetales";
		isImmuneToFire = true;
		super.init();
	}
	
	public void evolve() 
	{		
		
	}
}
