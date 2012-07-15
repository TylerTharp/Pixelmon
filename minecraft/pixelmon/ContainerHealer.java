package pixelmon;

import net.minecraft.src.Container;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ICrafting;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Slot;

public class ContainerHealer extends Container {
	private TileEntityPokemonHealer healer;

	public ContainerHealer(InventoryPlayer par1InventoryPlayer,
			TileEntityPokemonHealer par2TileEntityHealer) {
		healer = par2TileEntityHealer;

		addSlot(new Slot(par2TileEntityHealer, 0, 63, 30));//FUEL

		for (int i = 0; i < 3; i++) {
			for (int k = 0; k < 9; k++) {
				addSlot(new Slot(par1InventoryPlayer, k + i * 9 + 9,
						8 + k * 18, 84 + i * 18));
			}
		}

		for (int j = 0; j < 9; j++) {
			addSlot(new Slot(par1InventoryPlayer, j, 8 + j * 18, 142));
		}
	}

	/**
	 * Updates crafting matrix; called from onCraftMatrixChanged. Args: none
	 */
	public void updateCraftingResults() {
		super.updateCraftingResults();
	}

	public void updateProgressBar(int par1, int par2) {
		
	}

	public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
		return healer.isUseableByPlayer(par1EntityPlayer);
	}

	/**
	 * Called to transfer a stack from one inventory to the other eg. when shift
	 * clicking.
	 */
	public ItemStack transferStackInSlot(int par1) {
		ItemStack itemstack = null;
		Slot slot = (Slot) inventorySlots.get(par1);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (par1 == 2) {
				if (!mergeItemStack(itemstack1, 3, 39, true)) {
					return null;
				}

				slot.func_48433_a(itemstack1, itemstack);
			} else if (par1 == 1 || par1 == 0) {
				if (!mergeItemStack(itemstack1, 3, 39, false)) {
					return null;
				}
			} else if (par1 >= 30 && par1 < 39
					&& !mergeItemStack(itemstack1, 3, 30, false)) {
				return null;
			}

			if (itemstack1.stackSize == 0) {
				slot.putStack(null);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize != itemstack.stackSize) {
				slot.onPickupFromSlot(itemstack1);
			} else {
				return null;
			}
		}

		return itemstack;
	}
}
