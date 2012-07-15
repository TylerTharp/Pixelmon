package pixelmon.Pokemon;

import pixelmon.entities.BaseEntityPixelmon;
import pixelmon.entities.EntityGroundPixelmon;
import net.minecraft.src.*;

public class EntityBulbasaur extends EntityGroundPixelmon
{
	
	public EntityBulbasaur(World world)
	{
		super(world);
		texture = "/pixelmon/image/bulbasaur.png";
		init();
	}

	public void init()
	{
		name = "Bulbasaur";
		isImmuneToFire = false;
		super.init();
	}
	
	public void evolve() 
	{		
		BaseEntityPixelmon entity = new EntityIvysaur(worldObj);
		helper.evolve(entity.helper);
	}
}