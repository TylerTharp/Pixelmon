package pixelmon.Pokemon;

import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import pixelmon.entities.pixelmon.helpers.IHaveHelper;
import net.minecraft.src.Item;
import net.minecraft.src.World;

public class EntityVoltorb extends EntityGroundPixelmon
{
	
	public EntityVoltorb(World world)
	{
		super(world);
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
