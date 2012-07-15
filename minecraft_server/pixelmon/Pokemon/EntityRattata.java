package pixelmon.Pokemon;

import pixelmon.entities.EntityGroundPixelmon;
import net.minecraft.src.World;



public class EntityRattata extends EntityGroundPixelmon
{

	public EntityRattata(World par1World) 
	{
		super(par1World);
		texture = "/pixelmon/image/rattata.png";
		init();
	}

	public void init() 
	{
		name = "Rattata";
		isImmuneToFire = false;
		super.init();
	}

	@Override
	public void evolve() 
	{
	}
	
}