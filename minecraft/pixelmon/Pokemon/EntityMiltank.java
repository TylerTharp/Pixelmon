package pixelmon.Pokemon;

import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class EntityMiltank  extends EntityGroundPixelmon
{
	
	public EntityMiltank(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		name = "Miltank";
		isImmuneToFire = false;
		super.init();
	}
	 public boolean interact(EntityPlayer par1EntityPlayer)
	    {
	        ItemStack var2 = par1EntityPlayer.inventory.getCurrentItem();

	        if (var2 != null && var2.itemID == Item.bucketEmpty.shiftedIndex)
	        {
	            par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, new ItemStack(Item.bucketMilk));
	            return true;
	        }
	        else
	        {
	            return super.interact(par1EntityPlayer);
	        }
	    }
	
	public void evolve() 
	{		
		
	}
}
