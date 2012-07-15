package pixelmon.Pokemon;

import pixelmon.entities.BaseEntityPixelmon;
import pixelmon.entities.EntityGroundPixelmon;
import net.minecraft.src.*;

public class EntityMetapod extends EntityGroundPixelmon
{
	
	public EntityMetapod(World world)
	{
		super(world);
		texture = "/pixelmon/image/metapod.png";
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
