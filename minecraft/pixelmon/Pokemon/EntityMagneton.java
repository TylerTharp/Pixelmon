package pixelmon.Pokemon;

import pixelmon.entities.EntityGroundPixelmon;
import net.minecraft.src.World;

public class EntityMagneton extends EntityGroundPixelmon
{
	
	public EntityMagneton(World world)
	{
		super(world);
		texture = "/pixelmon/image/magneton.png";
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
