package pixelmon.storage;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import pixelmon.entities.PixelmonEntityHelper;

import net.minecraft.src.Chunk;
import net.minecraft.src.CompressedStreamTools;
import net.minecraft.src.ModLoader;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;
import net.minecraft.src.mod_Pixelmon;
import net.minecraft.src.forge.ISaveEventHandler;

public class ComputerManager implements ISaveEventHandler {

	private ComputerBox[] storageBoxes = new ComputerBox[boxCount];
	private NBTTagCompound data = new NBTTagCompound();
	private File workingDir;
	public static final int boxCount = 8;

	public ComputerManager() {
		for (int i = 0; i < boxCount; i++) {
			storageBoxes[i] = new ComputerBox(i);
		}
	}

	public void addToComputer(PixelmonEntityHelper p) {
		for (ComputerBox c : storageBoxes) {
			if (c.hasSpace()) {
				c.add(p, getId());
				save();
				return;
			}
		}
		ModLoader.getMinecraftInstance().ingameGUI.addChatMessage("You have no space left in your computer!");
	}

	public int getId() {
		int id = ModLoader.getUniqueEntityId();
		boolean isUsed = false;
		do {
			isUsed = false;
			for (NBTTagCompound nbt : mod_Pixelmon.pokeballManager.getPlayerStorage(ModLoader.getMinecraftInstance().thePlayer).getList()) {
				if(nbt != null){
					if (nbt.getInteger("pixelmonID") == id) {
						id = ModLoader.getUniqueEntityId();
						isUsed = true;
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

	public ComputerBox getBox(int boxNumber) {
		return storageBoxes[boxNumber];
	}

	public ComputerBox[] getBoxList() {
		return storageBoxes;
	}

	@SuppressWarnings("unchecked")
	private void load(World world) {
		File f = new File(".");
		File f1 = new File(f.getAbsolutePath().substring(0, f.getAbsolutePath().lastIndexOf('.')));
		workingDir = new File(ModLoader.getMinecraftInstance().getMinecraftDir() + "\\saves\\" + world.getSaveHandler().getSaveDirectoryName() + "\\");
		if (!workingDir.exists())
			workingDir.mkdir();
		File file = new File(workingDir, "computer.dat");
		if (!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		else if (world.isNewWorld) {
			file.delete();
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		load(file);
	}

	public void load(File file) {
		try {
			try {
				data = null;
				data = CompressedStreamTools.read(file);
			} catch (java.io.EOFException e) {
				data = new NBTTagCompound();
			}
			if (data == null)
				data = new NBTTagCompound();
			if (data.getTags().isEmpty()) {
				return;
			}
			Iterator<NBTTagCompound> i = data.getTags().iterator();

			while (i.hasNext()) {
				NBTTagCompound tag = i.next();
				ComputerBox c = new ComputerBox(Integer.parseInt(tag.getName()));
				c.load(tag);
				storageBoxes[Integer.parseInt(tag.getName())] = c;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private File getFile() {
		File f = new File(".");
		File f1 = new File(f.getAbsolutePath().substring(0, f.getAbsolutePath().lastIndexOf('.')));
		workingDir = new File(ModLoader.getMinecraftInstance().getMinecraftDir() + "\\saves\\"
				+ ModLoader.getMinecraftInstance().theWorld.getSaveHandler().getSaveDirectoryName() + "\\");
		if (!workingDir.exists())
			workingDir.mkdir();
		File file = new File(workingDir, "computer.dat");
		if (!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		return file;
	}

	public void save() {
		try {
			boolean hasChanges = false;
			for (ComputerBox c : storageBoxes) {
				if (c.hasChanged)
					hasChanges = true;
			}
			if (!hasChanges)
				return;
			data = new NBTTagCompound();
			for (int i = 0; i < storageBoxes.length; i++) {
				ComputerBox c = storageBoxes[i];
				NBTTagCompound cTag = new NBTTagCompound();
				c.save(cTag);
				data.setCompoundTag("" + c.position, cTag);
			}
			if (!getFile().exists()) {
				if (getFile().mkdirs())
					getFile().createNewFile();
			}
			CompressedStreamTools.write(data, getFile());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onWorldLoad(World world) {
		load(world);
	}

	@Override
	public void onWorldSave(World world) {
		save();
	}

	@Override
	public void onChunkLoad(World world, Chunk chunk) {
	}

	@Override
	public void onChunkUnload(World world, Chunk chunk) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onChunkSaveData(World world, Chunk chunk, NBTTagCompound data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onChunkLoadData(World world, Chunk chunk, NBTTagCompound data) {
		// TODO Auto-generated method stub

	}

	public void resetStatus() {

	}

}
