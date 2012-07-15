package pixelmon.Pokemon;

import pixelmon.entities.EntityGroundPixelmon;
import net.minecraft.src.World;

public class EntityNinetales  extends EntityGroundPixelmon
{
	
	public EntityNinetales(World world)
	{
		super(world);
		texture = "/pixelmon/image/ninetails.png";
		init();
	}

	public void init()
	{
		name = "Ninetales";
		isImmuneToFire = true;
		super.init();
	}
	
	public void evolve() 
	{		
		
	}
}
