package pixelmon.Pokemon;

import pixelmon.entities.pixelmon.BaseEntityPixelmon;
import pixelmon.entities.pixelmon.EntityWaterPixelmon;
import pixelmon.entities.pixelmon.helpers.IHaveHelper;
import net.minecraft.src.*;



public class EntityOmanyte extends EntityWaterPixelmon
{
	
	public EntityOmanyte(World world)
	{
		super(world);
		depthRangeStart = 5;
		depthRangeEnd = 10;
		init();
	}

	public void init() 
	{
		name = "Omanyte";
		isImmuneToFire = false;
		super.init();
	}

	public float getMoveSpeed() 
	{
		return 2F;
	}

	public void evolve() 
	{
		IHaveHelper entity = new EntityOmastar(worldObj);
		helper.evolve(entity.getHelper());
	}
}