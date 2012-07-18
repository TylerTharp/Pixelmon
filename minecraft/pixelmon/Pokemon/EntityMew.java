package pixelmon.Pokemon;

import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import net.minecraft.src.World;

public class EntityMew extends EntityGroundPixelmon
{
	
	public EntityMew(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		name = "Mew";
		isImmuneToFire = false;
		super.init();
	}
	
	public void evolve() 
	{		
		
	}
}
