package pixelmon.items;

import pixelmon.enums.EnumGui;
import pixelmon.gui.pokedex.GuiPokedex;
import net.minecraft.src.*;

public class ItemPokedex extends Item
{

	public ItemPokedex(int par1) 
	{
		super(par1);
	}
	
	public ItemStack onItemRightClick(ItemStack i, World world, EntityPlayer player)
	{
		player.openGui(mod_Pixelmon.instance, EnumGui.Pokedex.getIndex(), world, 0, 0, 0);
		return i;
	}
	
	public EnumRarity getRarity(ItemStack i)
	{
		return EnumRarity.rare;
	}
}