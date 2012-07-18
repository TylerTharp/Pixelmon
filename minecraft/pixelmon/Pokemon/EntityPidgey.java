package pixelmon.Pokemon;

import pixelmon.entities.BaseEntityPixelmon;
import pixelmon.entities.EntityFlyingPixelmon;
import pixelmon.entities.EntityGroundPixelmon;
import pixelmon.helpers.IHaveHelper;
import net.minecraft.src.World;

public class EntityPidgey extends EntityFlyingPixelmon
{
	
	public EntityPidgey(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		name = "Pidgey";
		isImmuneToFire = false;
		super.init();
	}
	
	public void evolve() 
	{		
		IHaveHelper entity = new EntityPidgeotto(worldObj);
		helper.evolve(entity.getHelper());
	}
}
