package pixelmon.storage;

import java.util.Iterator;

import pixelmon.comm.ChatHandler;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ModLoader;
import net.minecraft.src.NBTBase;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.mod_Pixelmon;

public class PlayerComputerStorage {
	private ComputerBox[] storageBoxes = new ComputerBox[boxCount];
	public static final int boxCount = 8;
	private NBTTagCompound data = new NBTTagCompound();
	public EntityPlayer player;

	public PlayerComputerStorage(EntityPlayer player) {
		this.player = player;
		for (int i = 0; i < boxCount; i++) {
			storageBoxes[i] = new ComputerBox(i);

		}
	}

	public void addToComputer(PixelmonEntityHelper p) {
		for (ComputerBox c : storageBoxes) {
			if (c.hasSpace()) {
				c.add(p, getId());
				return;
			}
		}
		ChatHandler.sendChat(p.getOwner(), "You have no space left in your computer!");
	}

	public int getId() {
		int id = ModLoader.getUniqueEntityId();
		boolean isUsed = false;
		do {
			isUsed = false;
			for (ComputerBox c : storageBoxes) {
				for (NBTTagCompound nbt : c.getStoredPokemon()) {
					if (nbt != null) {
						if (nbt.getInteger("pixelmonID") == id) {
							id = ModLoader.getUniqueEntityId();
							isUsed = true;
						}
					}
				}
			}
			for (ComputerBox c : storageBoxes) {
				for (int i = 0; i < ComputerBox.boxLimit; i++) {
					if (c.getStoredPokemon()[i] != null) {
						if (c.getStoredPokemon()[i].getInteger("pixelmonID") == id) {
							id = ModLoader.getUniqueEntityId();
							isUsed = true;
						}
					}
				}
			}
		} while (isUsed);
		return id;
	}
	
	public void changePokemon(int box, int boxPos, NBTTagCompound n){
		if(n != null){
			n.setInteger("BoxNumber", box);
			n.setInteger("PixelmonOrder", boxPos);
		}
		ComputerBox c = storageBoxes[box];
		NBTTagCompound[] pixelmon = c.getStoredPokemon();
		pixelmon[boxPos] = n;
		c.setStoredPokemon(pixelmon);
		c.hasChanged = true;
	}

	public ComputerBox getBox(int boxNumber) {
		return storageBoxes[boxNumber];
	}

	public ComputerBox getBoxFromPosition(int pos){
		for (int i=0; i < boxCount;i++){
			if (storageBoxes[i].position == pos) return storageBoxes[i];
		}
		return null;
	}
	
	public ComputerBox[] getBoxList() {
		return storageBoxes;
	}

	@SuppressWarnings("rawtypes")
	public void readFromNBT(NBTTagCompound var1) {
		Iterator<NBTTagCompound> i = data.getTags().iterator();

		while (i.hasNext()) {
			NBTTagCompound tag = i.next();
			ComputerBox c = new ComputerBox(Integer.parseInt(tag.getName()));
			c.load(tag);
			storageBoxes[Integer.parseInt(tag.getName())] = c;
		}
	}

	public void writeToNBT(NBTTagCompound n) {
		data = new NBTTagCompound();
		for (int i = 0; i < storageBoxes.length; i++) {
			ComputerBox c = storageBoxes[i];
			NBTTagCompound cTag = new NBTTagCompound();
			c.save(cTag);
			data.setCompoundTag("" + c.position, cTag);
		}
	}

	public boolean hasChanges() {
		for (ComputerBox c : storageBoxes) {
			if (c.hasChanged)
				return true;
		}
		return false;
	}
}