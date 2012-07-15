package pixelmon.Pokemon;

import pixelmon.entities.BaseEntityPixelmon;
import pixelmon.entities.EntityGroundPixelmon;
import pixelmon.entities.IHaveHelper;
import net.minecraft.src.*;

public class EntitySquirtle extends EntityGroundPixelmon
{
	public EntitySquirtle(World world)
	{
		super(world);
		texture = "/pixelmon/image/squirtle.png";
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