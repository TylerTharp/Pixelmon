package pixelmon.Pokemon;

import pixelmon.entities.BaseEntityPixelmon;
import pixelmon.entities.EntityWaterPixelmon;
import pixelmon.entities.IHaveHelper;
import net.minecraft.src.*;


public class EntityStarmie extends EntityWaterPixelmon
{
	
	public EntityStarmie(World world)
	{
		super(world);
		texture = "/pixelmon/image/starmie.png";
		init();
	}

	public void init() 
	{
		name = "Starmie";
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