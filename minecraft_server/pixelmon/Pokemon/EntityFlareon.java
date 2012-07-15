package pixelmon.Pokemon;

import pixelmon.entities.EntityGroundPixelmon;
import net.minecraft.src.World;

public class EntityFlareon extends EntityGroundPixelmon
{
	
	public EntityFlareon(World world)
	{
		super(world);
		texture = "/pixelmon/image/flareon.png";
		init();
	}

	public void init()
	{
		name = "Flareon";
		isImmuneToFire = true;
		super.init();
	}
	
	public void evolve() 
	{
		
	}

}
