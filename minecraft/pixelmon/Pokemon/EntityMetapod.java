package pixelmon.Pokemon;

import pixelmon.entities.pixelmon.BaseEntityPixelmon;
import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import net.minecraft.src.*;

public class EntityMetapod extends EntityGroundPixelmon
{
	
	public EntityMetapod(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		name = "Metapod";
		isImmuneToFire = false;
		super.init();
	}
	
	public void evolve() 
	{
		BaseEntityPixelmon entity = new EntityButterfree(worldObj);
		helper.evolve(entity.helper);
	}

}
