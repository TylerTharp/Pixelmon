package pixelmon.Pokemon;

import pixelmon.entities.pixelmon.BaseEntityPixelmon;
import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import net.minecraft.src.World;


public class EntityOddish extends EntityGroundPixelmon
{

	public EntityOddish(World par1World) 
	{
		super(par1World);
		init();
	}
	
	public void init() 
	{
		name = "Oddish";
		isImmuneToFire = false;
		super.init();
	}

	public void evolve() 
	{
		BaseEntityPixelmon entity = new EntityGloom(worldObj);
		helper.evolve(entity.helper);
	}
	
}