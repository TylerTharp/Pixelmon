package pixelmon.Pokemon;

import pixelmon.entities.BaseEntityPixelmon;
import pixelmon.entities.EntityFlyingPixelmon;
import pixelmon.entities.EntityGroundPixelmon;
import pixelmon.helpers.IHaveHelper;
import net.minecraft.src.World;

public class EntityPidgeotto extends EntityFlyingPixelmon
{
	
	public EntityPidgeotto(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		name = "Pidgeotto";
		isImmuneToFire = false;
		super.init();
	}
	
	public void evolve() 
	{		
		IHaveHelper entity = new EntityPidgeot(worldObj);
		helper.evolve(entity.getHelper());
	}
}
