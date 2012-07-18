package pixelmon.Pokemon;

import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import net.minecraft.src.World;

public class EntityMagneton extends EntityGroundPixelmon
{
	
	public EntityMagneton(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		name = "Magneton";
		isImmuneToFire = false;
		super.init();
	}
	
	public void evolve() 
	{
	}

}
