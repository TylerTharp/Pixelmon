package pixelmon.Pokemon;

import pixelmon.entities.EntityGroundPixelmon;
import net.minecraft.src.World;

public class EntityGrowlithe extends EntityGroundPixelmon
{
	
	public EntityGrowlithe(World world)
	{
		super(world);
		texture = "/pixelmon/image/growlithe.png";
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
