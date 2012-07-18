package pixelmon.Pokemon;

import pixelmon.entities.BaseEntityPixelmon;
import pixelmon.entities.EntityWaterPixelmon;
import pixelmon.helpers.IHaveHelper;
import net.minecraft.src.*;

public class EntityTentacool extends EntityWaterPixelmon
{
	
	public EntityTentacool(World world)
	{
		super(world);
		init();
	}

	public void init() 
	{
		name = "Tentacool";
		depthRangeStart=3;
		depthRangeEnd=7;
		isImmuneToFire = false;
		super.init();
	}

	public float getMoveSpeed() 
	{
		return 0.5F;
	}

	public void evolve() 
	{
		//IHaveHelper entity = new EntityGyarados(worldObj);
		//helper.evolve(entity.getHelper());
	}
}