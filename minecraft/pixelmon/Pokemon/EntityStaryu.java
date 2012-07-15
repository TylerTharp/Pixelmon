package pixelmon.Pokemon;

import pixelmon.entities.BaseEntityPixelmon;
import pixelmon.entities.EntityWaterPixelmon;
import pixelmon.entities.IHaveHelper;
import net.minecraft.src.*;


public class EntityStaryu extends EntityWaterPixelmon
{
	
	public EntityStaryu(World world)
	{
		super(world);
		texture = "/pixelmon/image/staryu.png";
		init();
	}

	public void init() 
	{
		name = "Staryu";
		stayNearSurface = false;
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
		
		return  this.posY < 55.0D && super.getCanSpawnHere();
	}
}