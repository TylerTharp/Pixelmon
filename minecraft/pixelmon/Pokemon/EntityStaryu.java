package pixelmon.Pokemon;

import pixelmon.entities.pixelmon.BaseEntityPixelmon;
import pixelmon.entities.pixelmon.EntityWaterPixelmon;
import pixelmon.entities.pixelmon.helpers.IHaveHelper;
import net.minecraft.src.*;


public class EntityStaryu extends EntityWaterPixelmon
{
	
	public EntityStaryu(World world)
	{
		super(world);
		init();
	}

	public void init() 
	{
		name = "Staryu";
		depthRangeStart=7;
		isImmuneToFire = false;
		super.init();
	}

	public float getMoveSpeed() 
	{
		return 2F;
	}

	public void evolve() 
	{
		IHaveHelper entity = new EntityStarmie(worldObj);
		helper.evolve(entity.getHelper());
	}
	
	public boolean getCanSpawnHere() {
		return  worldObj.getBlockId((int)posX,(int)Math.ceil(posY)-1,(int)posZ)!= Block.waterStill.blockID && super.getCanSpawnHere();
	}
}