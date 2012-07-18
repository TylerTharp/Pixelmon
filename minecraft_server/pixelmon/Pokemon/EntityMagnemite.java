package pixelmon.Pokemon;

import pixelmon.entities.pixelmon.BaseEntityPixelmon;
import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import net.minecraft.src.World;

public class EntityMagnemite  extends EntityGroundPixelmon
{
	
	public EntityMagnemite(World world)
	{
		super(world);
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
