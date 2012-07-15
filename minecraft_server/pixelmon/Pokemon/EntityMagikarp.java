package pixelmon.Pokemon;

import pixelmon.entities.BaseEntityPixelmon;
import pixelmon.entities.EntityWaterPixelmon;
import pixelmon.entities.IHaveHelper;
import net.minecraft.src.*;



public class EntityMagikarp extends EntityWaterPixelmon
{
	
	public EntityMagikarp(World world)
	{
		super(world);
		texture = "/pixelmon/image/magikarp.png";
		init();
	}

	public void init() 
	{
		name = "Magikarp";
		isImmuneToFire = false;
		super.init();
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