package pixelmon.Pokemon;

import pixelmon.entities.BaseEntityPixelmon;
import pixelmon.entities.EntityWaterPixelmon;
import net.minecraft.src.*;


public class EntityGyarados extends EntityWaterPixelmon
{
	
	public EntityGyarados(World world)
	{
		super(world);
		texture = "/pixelmon/image/gyarados.png";
		init();
	}

	public void init() 
	{
		name = "Gyarados";
		stayNearSurface = false;
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
	
	public boolean getCanSpawnHere() {
		
		return  this.posY < 60.0D && super.getCanSpawnHere();
	}
}