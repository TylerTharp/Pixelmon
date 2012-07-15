package pixelmon.Pokemon;

import pixelmon.entities.BaseEntityPixelmon;
import pixelmon.entities.EntityGroundPixelmon;
import net.minecraft.src.World;

public class EntityJigglypuff extends EntityGroundPixelmon
{
	
	public EntityJigglypuff(World world)
	{
		super(world);
		texture = "/pixelmon/image/jigglypuff.png";
		init();
	}

	public void init()
	{
		name = "Jigglypuff";
		isImmuneToFire = false;
		super.init();
	}
	
	public void evolve() 
	{		
		BaseEntityPixelmon entity = new EntityWigglytuff(worldObj);
		helper.evolve(entity.helper);
	}
}
