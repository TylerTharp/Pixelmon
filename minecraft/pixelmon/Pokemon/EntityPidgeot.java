package pixelmon.Pokemon;

import pixelmon.entities.BaseEntityPixelmon;
import pixelmon.entities.EntityFlyingPixelmon;
import pixelmon.entities.EntityGroundPixelmon;
import net.minecraft.src.World;

public class EntityPidgeot extends EntityFlyingPixelmon
{
	
	public EntityPidgeot(World world)
	{
		super(world);
		texture = "/pixelmon/image/pidgeot.png";
		init();
	}

	public void init()
	{
		name = "Pidgeot";
		isImmuneToFire = false;
		super.init();
	}
	public void evolve() 
	{		
	}
}
