package pixelmon;

import java.util.ArrayList;

import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NBTTagList;
import net.minecraft.src.TileEntity;
import net.minecraft.src.mod_Pixelmon;

public class TileEntityPokemonHealer extends TileEntity implements IInventory {

	private ItemStack healerItemStacks[];
	public int availableHealTime;
	public int currentItemHealTime;
	public int healTime;

	public TileEntityPokemonHealer() {
		healerItemStacks = new ItemStack[7];
		healTime = 0;
		currentItemHealTime = 0;
		availableHealTime = 0;
	}

	public int getSizeInventory() {
		return healerItemStacks.length;
	}

	public ItemStack getStackInSlot(int par1) {
		return healerItemStacks[par1];
	}

	private NBTTagCompound getFirstSlotCanHeal()
	{
		/*int i = -1;
		for(int i1 = 1; i1 < healerItemStacks.length; i1++)
		{
			if(healerItemStacks[i1] != null)
			{
				NBTTagCompound e = mod_Pixelmon.pokeballManager.getNBT(healerItemStacks[i1].getItemDamage());
				if(e != null)
				{
					if(e.getShort("Health") < e.getInteger("StatsHP"))
					{
						i = i1;
						break;
					}
				}
			}
		}
		return i;*/
//		NBTTagCompound[] a = mod_Pixelmon.pokeballManager.getPlayerStorage(pixelmon.getOwner()).getList();
//		for(int i1 = a.length - 1; i1 >= 0; i1--)
//		{
//			if(a[i1] != null)
//				if(a[i1].getShort("Health") < a[i1].getInteger("StatsHP"))
//				{
//					return a[i1];
//				}
//		}
		return null;
	}

	/*private int getLastStackCantHeal()
	{
		int i = -1;
		for(int i1 = 1; i1 < healerItemStacks.length; i1++)
		{
			if(healerItemStacks[i1] != null)
			{
				NBTTagCompound e = mod_Pixelmon.pokeballManager.getNBT(healerItemStacks[i1].getItemDamage());
				if(e != null)
				{
					if(e.getShort("Health") < e.getInteger("StatsHP"))
					{
						i = i1;
					}
				}
			}
		}
		return i;
	}*/

	public ItemStack decrStackSize(int par1, int par2) {
		if (healerItemStacks[par1] != null) {
			if (healerItemStacks[par1].stackSize <= par2) {
				ItemStack itemstack = healerItemStacks[par1];
				healerItemStacks[par1] = null;
				return itemstack;
			}

			ItemStack itemstack1 = healerItemStacks[par1].splitStack(par2);

			if (healerItemStacks[par1].stackSize == 0) {
				healerItemStacks[par1] = null;
			}

			return itemstack1;
		} else {
			return null;
		}
	}

	public ItemStack getStackInSlotOnClosing(int par1) {
		if (healerItemStacks[par1] != null) {
			ItemStack itemstack = healerItemStacks[par1];
			healerItemStacks[par1] = null;
			return itemstack;
		} else {
			return null;
		}
	}

	public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
		healerItemStacks[par1] = par2ItemStack;

		if (par2ItemStack != null
				&& par2ItemStack.stackSize > getInventoryStackLimit()) {
			par2ItemStack.stackSize = getInventoryStackLimit();
		}
	}

	public String getInvName() {
		return "Healer";
	}

	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
		healerItemStacks = new ItemStack[getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); i++) {
			NBTTagCompound nbttagcompound = (NBTTagCompound) nbttaglist
					.tagAt(i);
			byte byte0 = nbttagcompound.getByte("Slot");

			if (byte0 >= 0 && byte0 < healerItemStacks.length) {
				healerItemStacks[byte0] = ItemStack
						.loadItemStackFromNBT(nbttagcompound);
			}
		}

		healTime = par1NBTTagCompound.getShort("HealTime");
		currentItemHealTime = par1NBTTagCompound.getShort("HealingTime");
		availableHealTime = getItemHealAmount(healerItemStacks[0]);
	}

	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setShort("HealTime", (short) healTime);
		par1NBTTagCompound.setShort("HealingTime", (short) currentItemHealTime);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < healerItemStacks.length; i++) {
			if (healerItemStacks[i] != null) {
				NBTTagCompound nbttagcompound = new NBTTagCompound();
				nbttagcompound.setByte("Slot", (byte) i);
				healerItemStacks[i].writeToNBT(nbttagcompound);
				nbttaglist.appendTag(nbttagcompound);
			}
		}

		par1NBTTagCompound.setTag("Items", nbttaglist);
	}

	/**
	 * Returns the maximum stack size for a inventory slot. Seems to always be
	 * 64, possibly will be extended. *Isn't this more of a set than a get?*
	 */
	public int getInventoryStackLimit() {
		return 64;
	}

	/**
	 * Returns an integer between 0 and the passed value representing how close
	 * the current item is to being completely cooked
	 */
	public int getHealProgressScaled(int par1) {
		return (availableHealTime * par1) / 600;
	}

	/**
	 * Returns an integer between 0 and the passed value representing how much
	 * burn time is left on the current fuel item, where 0 means that the item
	 * is exhausted and the passed value means that the item is fresh
	 */
	public int getHealTimeRemainingScaled(int par1) {
		if (currentItemHealTime == 0) {
			currentItemHealTime = 600;
		}

		return (availableHealTime * par1) / currentItemHealTime;
	}

	/**
	 * Returns true if the furnace is currently burning
	 */
	public boolean isHealing() {
		return availableHealTime > 0;
	}

	/**
	 * Allows the entity to update its state. Overridden in most subclasses,
	 * e.g. the mob spawner uses this to count ticks and creates a new spawn
	 * inside its implementation.
	 */
	public void updateEntity() {
		boolean flag = availableHealTime > 0;
		boolean flag1 = false;

		if (availableHealTime > 0) {
			availableHealTime--;
		}

		if (!worldObj.isRemote) {
			if (availableHealTime == 0 && canHeal()) {
				currentItemHealTime = availableHealTime = getItemHealAmount(healerItemStacks[0]);

				if (availableHealTime > 0) {
					flag1 = true;

					if (healerItemStacks[0] != null) {
						if (healerItemStacks[0].getItem().func_46003_i()) {
							healerItemStacks[0] = new ItemStack(
									healerItemStacks[0].getItem().setFull3D());
						} else {
							healerItemStacks[0].stackSize--;
						}

						if (healerItemStacks[0].stackSize == 0) {
							healerItemStacks[0] = null;
						}
					}
				}
			}

			if (isHealing() && canHeal()) {
				healTime++;

				if (healTime == 50) {
					healTime = 0;
					heal();
					flag1 = true;
				}
			} else {
				healTime = 0;
			}

			if (flag != (availableHealTime > 0)) {
				flag1 = true;
				BlockHealer.updateFurnaceBlockState(availableHealTime > 0,
						worldObj, xCoord, yCoord, zCoord);
			}
		}

		if (flag1) {
			onInventoryChanged();
		}
	}

	private boolean canHeal() {
		NBTTagCompound n = getFirstSlotCanHeal();
		if(n == null) return false;
		else return true;
	}

	public void heal() {
		if (!canHeal()) {
			return;
		}
		/*
		for(int i = 0; i <= getLastStackCantHeal(); i++)
		{
			if(healerItemStacks[i] == null) continue;
	        EntityItem entityitem = new EntityItem(worldObj, xCoord, yCoord + 1, zCoord, healerItemStacks[i].copy());
	        entityitem.delayBeforeCanPickup = 10;
	        worldObj.spawnEntityInWorld(entityitem);
	        worldObj.playSoundAtEntity(entityitem, "random.pop", 0.2F, ((worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
			healerItemStacks[i] = null;
		}
		*/
		NBTTagCompound nbt = getFirstSlotCanHeal();
		nbt.setBoolean("IsFainted", false);
		nbt.setShort("Health", (short)nbt.getInteger("StatsHP"));
		int numMoves = nbt.getInteger("PixelmonNumberMoves");
		for (int i=0; i < numMoves; i++){
			nbt.setInteger("PixelmonMovePP" + i,nbt.getInteger("PixelmonMovePPBase"));
		}
        //EntityItem entityitem = new EntityItem(worldObj, xCoord, yCoord + 1, zCoord, healerItemStacks[i].copy());
        //entityitem.delayBeforeCanPickup = 10;
        //worldObj.spawnEntityInWorld(entityitem);
        //worldObj.playSoundAtEntity(entityitem, "random.pop", 0.2F, ((worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
		//healerItemStacks[i] = null;
	}

	public static int getItemHealAmount(ItemStack par1ItemStack) {
		if (par1ItemStack == null) {
			return 0;
		}

		int i = par1ItemStack.itemID;

		if (i == Item.wheat.shiftedIndex) {
			return 50;
		}
		return 0;
	}

	public static boolean canItemHeal(ItemStack par0ItemStack) {
		return getItemHealAmount(par0ItemStack) > 0;
	}

	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
		if (worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) != this) {
			return false;
		}

		return par1EntityPlayer.getDistanceSq((double) xCoord + 0.5D,
				(double) yCoord + 0.5D, (double) zCoord + 0.5D) <= 64D;
	}

	public void openChest() {
	}

	public void closeChest() {
	}

}