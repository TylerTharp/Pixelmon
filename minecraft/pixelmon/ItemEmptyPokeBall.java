package pixelmon;

import pixelmon.entities.EntityEmptyPokeBall;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class ItemEmptyPokeBall extends Item {
	public double ballBonus;

	public ItemEmptyPokeBall(int i, double i1) {
		super(i);
		maxStackSize = 64;
		ballBonus = i1;
		setMaxDamage(0xf4240);
	}

	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		{
			itemstack.stackSize--;
			world.playSoundAtEntity(entityplayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
			if (!world.isRemote)
				world.spawnEntityInWorld(new EntityEmptyPokeBall(world, entityplayer, ballBonus));
		}
		return itemstack;
	}
}
