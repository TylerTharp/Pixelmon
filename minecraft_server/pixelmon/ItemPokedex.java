package pixelmon;

import net.minecraft.src.*;

public class ItemPokedex extends Item
{

	public ItemPokedex(int par1) 
	{
		super(par1);
	}
	
	public ItemStack onItemRightClick(ItemStack i, World world, EntityPlayer player)
	{
		player.openGui(mod_Pixelmon.instance, 23, world,0,0,0);
		//super.
		return i;
	}
}














