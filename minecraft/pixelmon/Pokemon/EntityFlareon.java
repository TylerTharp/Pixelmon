package pixelmon.Pokemon;

import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import net.minecraft.src.World;

public class EntityFlareon extends EntityGroundPixelmon
{
	
	public EntityFlareon(World world)
	{
		super(world);
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
