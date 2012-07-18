package pixelmon.Pokemon;

import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import net.minecraft.src.World;

public class EntityGrowlithe extends EntityGroundPixelmon
{
	
	public EntityGrowlithe(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		name = "Growlithe";
		isImmuneToFire = true;
		super.init();
	}
	
	public void evolve() 
	{
		
	}

}
