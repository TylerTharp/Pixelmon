package pixelmon.Pokemon;

import pixelmon.entities.BaseEntityPixelmon;
import pixelmon.entities.EntityGroundPixelmon;
import net.minecraft.src.World;

public class EntityLunatone extends EntityGroundPixelmon
{
	
	public EntityLunatone(World world)
	{
		super(world);
		texture = "/pixelmon/image/lunatone.png";
		init();
	}

	public void init()
	{
		name = "Lunatone";
		isImmuneToFire = false;
		doesHover = true;
		hoverHeight=1f;
		super.init();
	}
	
	public void evolve() 
	{		
	
	}
	
	public boolean getCanSpawnHere()
	{
		if(!worldObj.isDaytime() ) {
			return true;
		}
		
		return false;
	}
	
}
	
	