package pixelmon.Pokemon;

import pixelmon.entities.pixelmon.BaseEntityPixelmon;
import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import net.minecraft.src.World;


public class EntityNidoranMale extends EntityGroundPixelmon
{

	public EntityNidoranMale(World par1World) 
	{
		super(par1World);
		texture = "/pixelmon/image/nidoranMale.png";
		init();
	}
	
	public void init() 
	{
		name = "NidoranMale";
		isImmuneToFire = false;
		super.init();
	}

	public void evolve() 
	{
	}
	
}