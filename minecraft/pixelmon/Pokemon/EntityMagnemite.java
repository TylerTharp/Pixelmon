package pixelmon.Pokemon;

import pixelmon.entities.BaseEntityPixelmon;
import pixelmon.entities.EntityGroundPixelmon;
import net.minecraft.src.World;

public class EntityMagnemite  extends EntityGroundPixelmon
{
	
	public EntityMagnemite(World world)
	{
		super(world);
		texture = "/pixelmon/image/magnemite.png";
		init();
	}

	public void init()
	{
		name = "Magnemite";
		isImmuneToFire = false;
		doesHover = true;
		hoverHeight=1f;
		super.init();
	}
	
	public void evolve() 
	{		
		BaseEntityPixelmon entity = new EntityMagneton(worldObj);
		helper.evolve(entity.helper);
	}
}
