package pixelmon.Pokemon;

import pixelmon.entities.BaseEntityPixelmon;
import pixelmon.entities.EntityWaterPixelmon;
import pixelmon.entities.IHaveHelper;
import net.minecraft.src.*;



public class EntitySeaking extends EntityWaterPixelmon
{
	
	public EntitySeaking(World world)
	{
		super(world);
		texture = "/pixelmon/image/seaking.png";
		init();
	}

	public void init() 
	{
		name = "Seaking";
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