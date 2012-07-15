package pixelmon.Pokemon;

import pixelmon.entities.EntityGroundPixelmon;
import net.minecraft.src.World;

public class EntityBlastoise extends EntityGroundPixelmon
{
	
	public EntityBlastoise(World world)
	{
		super(world);
		texture = "/pixelmon/image/blastoise.png";
		init();
	}

	public void init()
	{
		name = "Blastoise";
		isImmuneToFire = false;
		super.init();
	}
	
	public void evolve() 
	{
	}

}