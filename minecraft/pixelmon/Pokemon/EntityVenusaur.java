package pixelmon.Pokemon;

import pixelmon.entities.EntityGroundPixelmon;
import net.minecraft.src.World;

public class EntityVenusaur extends EntityGroundPixelmon
{
	
	public EntityVenusaur(World world)
	{
		super(world);
		texture = "/pixelmon/image/venusaur.png";
		init();
	}

	public void init()
	{
		name = "Venusaur";
		isImmuneToFire = false;
		super.init();
	}
	
	public void evolve() 
	{		
	}
}