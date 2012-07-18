package pixelmon.Pokemon;

import pixelmon.entities.pixelmon.BaseEntityPixelmon;
import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import net.minecraft.src.*;

public class EntityButterfree extends EntityGroundPixelmon
{
	
	public EntityButterfree(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		name = "Butterfree";
		isImmuneToFire = false;
		doesHover = true;
		hoverHeight = 1f;
		super.init();
	}
	
	public void evolve() 
	{
		
	}

}
