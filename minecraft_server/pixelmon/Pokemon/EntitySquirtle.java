package pixelmon.Pokemon;

import pixelmon.entities.pixelmon.BaseEntityPixelmon;
import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import pixelmon.entities.pixelmon.helpers.IHaveHelper;
import net.minecraft.src.*;

public class EntitySquirtle extends EntityGroundPixelmon
{
	public EntitySquirtle(World world)
	{
		super(world);
		moveSpeed = 0.3F;
		init();
	}

	public void init() 
	{
		name = "Squirtle";
		isImmuneToFire = false;
		super.init();
	}

	public void evolve() 
	{
		IHaveHelper entity = new EntityWartortle(worldObj);
		helper.evolve(entity.getHelper());
	}
}