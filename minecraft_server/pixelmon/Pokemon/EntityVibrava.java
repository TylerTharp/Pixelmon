package pixelmon.Pokemon;

import net.minecraft.src.World;
import pixelmon.entities.EntityGroundPixelmon;

public class EntityVibrava extends EntityGroundPixelmon
{
	
	public EntityVibrava(World world)
	{
		super(world);
		texture = "/pixelmon/image/vibrava.png";
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