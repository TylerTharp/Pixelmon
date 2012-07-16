package pixelmon.Pokemon;

import net.minecraft.src.World;
import pixelmon.entities.EntityWaterPixelmon;


public class EntityDratini extends EntityWaterPixelmon
{
	
	public EntityDratini(World world)
	{
		super(world);
		texture = "/pixelmon/image/dratini.png";
		init();
	}

	public void init() 
	{
		name = "Dratini";
		isImmuneToFire = false;
		moveSpeed = 9F;
		super.init();
		
		
	}

	public void evolve() 
	{
	
	}
}