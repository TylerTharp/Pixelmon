package pixelmon.Pokemon;

import pixelmon.entities.BaseEntityPixelmon;
import pixelmon.entities.EntityGroundPixelmon;
import net.minecraft.src.*;


public class EntityJolteon extends EntityGroundPixelmon
{
	
	public EntityJolteon(World world)
	{
		super(world);
		texture = "/pixelmon/image/jolteon.png";
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
