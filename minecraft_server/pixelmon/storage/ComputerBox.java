package pixelmon.storage;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

import pixelmon.entities.IHaveHelper;
import pixelmon.entities.PixelmonEntityHelper;

import net.minecraft.src.CompressedStreamTools;
import net.minecraft.src.Entity;
import net.minecraft.src.ModLoader;
import net.minecraft.src.NBTTagCompound;

public class ComputerBox {
	public boolean hasChanged = false;
	static final int boxLimit = 30;
	public int position;
	private NBTTagCompound[] storedPokemon = new NBTTagCompound[boxLimit];

	public ComputerBox(int position) {
		this.position = position;
		hasChanged = true;
	}

	public boolean hasSpace() {
		int count = 0;
		for (int i = 0; i < boxLimit; i++) {
			if (storedPokemon[i] != null)
				count++;
		}
		if (count < boxLimit)
			return true;
		return false;
	}

	public void add(PixelmonEntityHelper p, int id) {
		NBTTagCompound n = new NBTTagCompound();
		p.setPokemonID(id);
		p.writeEntityToNBT(n);
		Entity entity1 = (Entity) p.getEntity();
		entity1.writeToNBT(n);
		n.setString("id", p.getName());
		n.setName(p.getName());
		n.setString("Nickname", n.getName());
		n.setBoolean("IsInBall", true);
		int pos = getNextSpace();
		n.setInteger("StoragePosition", pos);
		storedPokemon[pos] = n;
		hasChanged = true;
	}

	public int getNextSpace() {
		for (int i = 0; i < boxLimit; i++)
			if (storedPokemon[i] == null)
				return i;
		return 0;
	}

	public NBTTagCompound get(int id) {
		for (NBTTagCompound n : storedPokemon) {
			if(n != null){
				if (n.getInteger("pixelmonID") == id)
					return n;
			}
		}
		return null;
	}

	public NBTTagCompound[] getStoredPokemon() {
		return storedPokemon;
	}
	
	public void setStoredPokemon(NBTTagCompound[] pokemon){
		storedPokemon = pokemon;
	}

	public void load(NBTTagCompound boxTag) {
		for (int i = 0; i < boxLimit; i++)
			storedPokemon[i] = null;
		Iterator<NBTTagCompound> i = boxTag.getTags().iterator();

		while (i.hasNext()) {
			NBTTagCompound tag = i.next();
			tag.setName(tag.getString("Name"));
			int pos = tag.getInteger("StoragePosition");
			storedPokemon[pos] = tag;
		}
		hasChanged = false;
	}

	public void save(NBTTagCompound tag) {
		for (int i = 0; i < boxLimit; i++) {
			if (storedPokemon[i] != null) {
				tag.setCompoundTag("" + storedPokemon[i].getInteger("pixelmonID"), storedPokemon[i]);
			}
		}
		hasChanged = false;
	}
}
