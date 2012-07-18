package pixelmon.Pokemon;

import pixelmon.entities.pixelmon.BaseEntityPixelmon;
import pixelmon.entities.pixelmon.EntityWaterPixelmon;
import net.minecraft.src.*;


public class EntityOmastar extends EntityWaterPixelmon
{
	
	public EntityOmastar(World world)
	{
		super(world);
		init();
	}

	public void init() 
	{
		name = "Omastar";
		depthRangeStart = 5;
		depthRangeEnd = 10;
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
	
	public boolean getCanSpawnHere() {
		
		return  this.posY < 55.0D && super.getCanSpawnHere();
	}
}