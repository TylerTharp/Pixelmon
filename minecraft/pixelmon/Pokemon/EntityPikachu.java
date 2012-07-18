package pixelmon.Pokemon;

import pixelmon.entities.pixelmon.BaseEntityPixelmon;
import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import net.minecraft.src.*;

public class EntityPikachu extends EntityGroundPixelmon
{
	
	public EntityPikachu(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		name = "Pikachu";
		isImmuneToFire = false;
		super.init();
	}
	
	public void evolve() 
	{

	}
}
