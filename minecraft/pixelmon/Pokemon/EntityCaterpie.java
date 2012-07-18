package pixelmon.Pokemon;

import pixelmon.entities.pixelmon.BaseEntityPixelmon;
import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import net.minecraft.src.World;

public class EntityCaterpie extends EntityGroundPixelmon
{
	
	public EntityCaterpie(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		name = "Caterpie";
		isImmuneToFire = false;
		super.init();
	}
	
	public void evolve() 
	{		
		BaseEntityPixelmon entity = new EntityMetapod(worldObj);
		helper.evolve(entity.helper);
	}
}
