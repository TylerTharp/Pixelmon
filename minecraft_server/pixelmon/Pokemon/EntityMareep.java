package pixelmon.Pokemon;

import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.World;
import pixelmon.entities.pixelmon.EntityGroundPixelmon;

public class EntityMareep extends EntityGroundPixelmon
{
	
	public EntityMareep(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		name = "Mareep";
		isImmuneToFire = false;
		super.init();
	}
	
	public void evolve() 
	{		
		
	}
	 public boolean interact(EntityPlayer par1EntityPlayer)
	    {
	        return super.interact(par1EntityPlayer);
	    }

	 protected int getDropItemId()
	    {
	        return Block.cloth.blockID;
	    }
	

}
