package pixelmon.Pokemon;

import pixelmon.entities.pixelmon.BaseEntityPixelmon;
import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import net.minecraft.src.*;


public class EntityJolteon extends EntityGroundPixelmon
{
	
	public EntityJolteon(World world)
	{
		super(world);
		init();
	}

	public void init() 
	{
		name = "Jolteon";
		isImmuneToFire = true;
		super.init();
	}

	public void evolve() 
	{	
		
	}
}
