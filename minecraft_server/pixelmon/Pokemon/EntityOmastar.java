package pixelmon.Pokemon;

import pixelmon.entities.BaseEntityPixelmon;
import pixelmon.entities.EntityWaterPixelmon;
import net.minecraft.src.*;


public class EntityOmastar extends EntityWaterPixelmon
{
	
	public EntityOmastar(World world)
	{
		super(world);
		texture = "/pixelmon/image/omastar.png";
		init();
	}

	public void init() 
	{
		name = "Omastar";
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

	}
	
	public boolean getCanSpawnHere() {
		
		return  this.posY < 55.0D && super.getCanSpawnHere();
	}
}