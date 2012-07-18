package pixelmon.Pokemon;

import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import net.minecraft.src.World;

public class EntityMankey  extends EntityGroundPixelmon
{
	
	public EntityMankey(World world)
	{
		super(world);
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
