package pixelmon.Pokemon;

import pixelmon.entities.BaseEntityPixelmon;
import pixelmon.entities.EntityGroundPixelmon;
import net.minecraft.src.World;


public class EntityWartortle extends EntityGroundPixelmon
{

	public EntityWartortle(World par1World) 
	{
		super(par1World);
		texture = "/pixelmon/image/wartortle.png";
		init();
	}

	public void init() 
	{
		name = "Wartortle";
		isImmuneToFire = false;
		super.init();
	}

	public void evolve() 
	{
		BaseEntityPixelmon entity = new EntityBlastoise(worldObj);
		helper.evolve(entity.helper);
	}
}
