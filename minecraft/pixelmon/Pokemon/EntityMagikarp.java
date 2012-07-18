package pixelmon.Pokemon;

import pixelmon.entities.pixelmon.BaseEntityPixelmon;
import pixelmon.entities.pixelmon.EntityWaterPixelmon;
import pixelmon.entities.pixelmon.helpers.IHaveHelper;
import net.minecraft.src.*;



public class EntityMagikarp extends EntityWaterPixelmon
{
	
	public EntityMagikarp(World world)
	{
		super(world);
		init();
	}

	public void init() 
	{
		name = "Magikarp";
		isImmuneToFire = false;
		super.init();
		depthRangeStart =0;
		depthRangeEnd=5;
	}

	public float getMoveSpeed() 
	{
		return 2F;
	}

	public void evolve() 
	{
		IHaveHelper entity = new EntityGyarados(worldObj);
		helper.evolve(entity.getHelper());
	}
}