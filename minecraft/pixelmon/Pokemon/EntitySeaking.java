package pixelmon.Pokemon;

import pixelmon.entities.BaseEntityPixelmon;
import pixelmon.entities.EntityWaterPixelmon;
import pixelmon.helpers.IHaveHelper;
import net.minecraft.src.*;



public class EntitySeaking extends EntityWaterPixelmon
{
	
	public EntitySeaking(World world)
	{
		super(world);
		init();
	}

	public void init() 
	{
		name = "Seaking";
		depthRangeStart=6;
		depthRangeEnd=20;
		isImmuneToFire = false;
		super.init();
	}

	public float getMoveSpeed() 
	{
		return 2F;
	}

	public void evolve() 
	{

	}
}