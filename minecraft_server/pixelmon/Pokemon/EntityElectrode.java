package pixelmon.Pokemon;

import pixelmon.entities.EntityGroundPixelmon;
import net.minecraft.src.*;

public class EntityElectrode extends EntityGroundPixelmon
{
	
	public EntityElectrode(World world)
	{
		super(world);
		texture = "/pixelmon/image/electrode.png";
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
