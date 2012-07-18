package pixelmon.Pokemon;

import pixelmon.entities.pixelmon.BaseEntityPixelmon;
import pixelmon.entities.pixelmon.EntityWaterPixelmon;
import pixelmon.entities.pixelmon.helpers.IHaveHelper;
import net.minecraft.src.*;



public class EntityGoldeen extends EntityWaterPixelmon
{
	
	public EntityGoldeen(World world)
	{
		super(world);
		init();
	}

	public void init() 
	{
		name = "Goldeen";
		depthRangeStart=2;
		depthRangeEnd=10;
		isImmuneToFire = false;
		super.init();
	}

	public float getMoveSpeed() 
	{
		return 2F;
	}

	public void evolve() 
	{
		IHaveHelper entity = new EntitySeaking(worldObj);
		helper.evolve(entity.getHelper());
	}
}