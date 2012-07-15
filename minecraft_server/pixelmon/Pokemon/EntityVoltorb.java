package pixelmon.Pokemon;

import pixelmon.entities.EntityGroundPixelmon;
import pixelmon.entities.IHaveHelper;
import net.minecraft.src.Item;
import net.minecraft.src.World;

public class EntityVoltorb extends EntityGroundPixelmon
{
	
	public EntityVoltorb(World world)
	{
		super(world);
		texture = "/pixelmon/image/voltorb.png";
		init();
	}

	public void init()
	{
		name = "Voltorb";
		isImmuneToFire = false;
		super.init();
	}
	 protected int getDropItemId()
	    {
	        return Item.gunpowder.shiftedIndex;
	    }
	public void evolve() 
	{		
		IHaveHelper entity = new EntityElectrode(worldObj);
		helper.evolve(entity.getHelper());
		
	}
}
