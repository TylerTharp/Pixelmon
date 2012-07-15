package pixelmon.Pokemon;

import pixelmon.entities.EntityGroundPixelmon;
import net.minecraft.src.World;

public class EntityMankey  extends EntityGroundPixelmon
{
	
	public EntityMankey(World world)
	{
		super(world);
		texture = "/pixelmon/image/mankey.png";
		init();
	}

	public void init()
	{
		name = "Mankey";
		isImmuneToFire = false;
		super.init();
	}
	
	public void evolve() 
	{		
		
	}
}
