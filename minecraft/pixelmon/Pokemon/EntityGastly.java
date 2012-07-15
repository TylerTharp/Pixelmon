package pixelmon.Pokemon;

import pixelmon.entities.EntityGroundPixelmon;
import net.minecraft.src.World;

public class EntityGastly extends EntityGroundPixelmon
{

	public EntityGastly(World par1World) 
	{
		super(par1World);
		texture = "/pixelmon/image/ghastly.png";
		init();
	}

	public void init() 
	{
		name = "Gastly";
		yOffset = 0;
		isImmuneToFire = false;
		doesHover = true;
		hoverHeight = 1f;
		super.init();
	}

	public void evolve() 
	{
	}
	
}