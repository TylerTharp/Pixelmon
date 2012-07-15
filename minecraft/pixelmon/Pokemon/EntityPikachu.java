package pixelmon.Pokemon;

import pixelmon.entities.BaseEntityPixelmon;
import pixelmon.entities.EntityGroundPixelmon;
import net.minecraft.src.*;

public class EntityPikachu extends EntityGroundPixelmon
{
	
	public EntityPikachu(World world)
	{
		super(world);
		texture = "/pixelmon/image/pikachu.png";
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
