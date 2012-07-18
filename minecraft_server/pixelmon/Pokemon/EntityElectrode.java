package pixelmon.Pokemon;

import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import net.minecraft.src.*;

public class EntityElectrode extends EntityGroundPixelmon
{
	
	public EntityElectrode(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		name = "Electrode";
		isImmuneToFire = false;
		super.init();
	}
	 protected int getDropItemId()
	    {
	        return Item.gunpowder.shiftedIndex;
	    }
	
	public void evolve() 
	{
	}

}
