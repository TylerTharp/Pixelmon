package pixelmon.Pokemon;

import pixelmon.entities.BaseEntityPixelmon;
import pixelmon.entities.EntityGroundPixelmon;
import net.minecraft.src.*;

public class EntityButterfree extends EntityGroundPixelmon
{
	
	public EntityButterfree(World world)
	{
		super(world);
		texture = "/pixelmon/image/butterfree.png";
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
