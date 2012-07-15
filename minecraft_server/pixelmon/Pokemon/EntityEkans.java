package pixelmon.Pokemon;

import pixelmon.entities.BaseEntityPixelmon;
import pixelmon.entities.EntityGroundPixelmon;
import net.minecraft.src.World;

public class EntityEkans extends EntityGroundPixelmon
{

	public EntityEkans(World par1World) 
	{
		super(par1World);
		texture = "/pixelmon/image/ekans.png";
		init();
	}

	public void init() 
	{
		name = "Ekans";
		isImmuneToFire = false;
		super.init();
	}


	public void evolve() 
	{
		BaseEntityPixelmon entity = new EntityArbok(worldObj);
		helper.evolve(entity.helper);
	}
}