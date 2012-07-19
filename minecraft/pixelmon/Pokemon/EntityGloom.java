package pixelmon.Pokemon;

import net.minecraft.src.World;
import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import pixelmon.entities.pixelmon.helpers.IHaveHelper;


public class EntityGloom extends EntityGroundPixelmon
{

	public EntityGloom(World par1World) 
	{
		super(par1World);
		init();
	}
	
	public void init() 
	{
		name = "Gloom";
		isImmuneToFire = false;
		super.init();
	}

	public void evolve() 
	{
	
	}
	
}