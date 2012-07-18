package pixelmon.Pokemon;

import net.minecraft.src.World;
import pixelmon.entities.pixelmon.EntityGroundPixelmon;

public class EntityVibrava extends EntityGroundPixelmon
{
	
	public EntityVibrava(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		name = "Vibrava";
		isImmuneToFire = false;
		super.init();
	}
	
	public void evolve() 
	{		
		
		
	}
}