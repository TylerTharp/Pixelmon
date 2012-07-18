package pixelmon.Pokemon;

import net.minecraft.src.World;
import pixelmon.entities.pixelmon.EntityGroundPixelmon;

public class EntityVaporeon  extends EntityGroundPixelmon
{
	
	public EntityVaporeon(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		name = "Vaporeon";
		isImmuneToFire = false;
		super.init();
	}
	
	public void evolve() 
	{		
	
	}
}
