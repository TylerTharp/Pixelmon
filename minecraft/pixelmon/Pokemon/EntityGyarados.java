package pixelmon.Pokemon;

import pixelmon.entities.pixelmon.BaseEntityPixelmon;
import pixelmon.entities.pixelmon.EntityWaterPixelmon;
import net.minecraft.src.*;


public class EntityGyarados extends EntityWaterPixelmon
{
	
	public EntityGyarados(World world)
	{
		super(world);
		init();
	}

	public void init() 
	{
		name = "Gyarados";
		depthRangeStart = 10;
		depthRangeEnd = 100;
		isImmuneToFire = false;
		this.swimSpeed = 3;
		super.init();
	}

	public float getMoveSpeed() 
	{
		return 1.5F;
	}

	public void evolve() 
	{

	}
}