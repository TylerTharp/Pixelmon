package pixelmon.storage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import pixelmon.PixelmonEntityList;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.BaseEntityPixelmon;
import pixelmon.entities.pixelmon.helpers.IHaveHelper;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;
import pixelmon.enums.EnumGui;

import cpw.mods.fml.common.FMLCommonHandler;

import net.minecraft.src.Chunk;
import net.minecraft.src.CompressedStreamTools;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityList;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ModLoader;
import net.minecraft.src.NBTBase;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NetworkManager;
import net.minecraft.src.Packet1Login;
import net.minecraft.src.World;
import net.minecraft.src.mod_Pixelmon;
import net.minecraft.src.forge.IConnectionHandler;
import net.minecraft.src.forge.ISaveEventHandler;
import net.minecraft.src.forge.MinecraftForge;

public class PokeballManager implements ISaveEventHandler {
	private File workingDir;

	private ArrayList<PlayerStorage> playerPokemonList = new ArrayList<PlayerStorage>();

	public enum PokeballManagerMode {
		Player, Trainer
	}

	public PokeballManager() {
	}

	public PlayerStorage getPlayerStorage(EntityPlayer owner) {
		if (owner.worldObj.isRemote){
			if (serverStorage==null){
				serverStorage = new PlayerStorage(owner);
			}
			return serverStorage;
		}
		if (playerPokemonList.size() == 0)
			loadPlayer(ModLoader.getMinecraftInstance().thePlayer);
		for (PlayerStorage p : playerPokemonList) {
			if (p.player.username.equals(owner.username))
				return p;
		}
		return null;
	}

	private PlayerStorage serverStorage = null;

	@SuppressWarnings("unchecked")
	public void loadPlayer(EntityPlayer player) {
		File saveDirPath = new File(getSaveFolder());
		if (!saveDirPath.exists())
			saveDirPath.mkdirs();
		File playerFile = new File(getSaveFolder() + "client.pk");
		if (playerFile.exists()) {
			PlayerStorage p = new PlayerStorage(player);
			try {
				p.readFromNBT(CompressedStreamTools.read(new DataInputStream(new FileInputStream(playerFile))));
			} catch (FileNotFoundException e) {
				System.out.println("Couldn't read player data file for " + player.username);
			} catch (IOException e) {
				System.out.println("Couldn't read player data file for " + player.username);
			}
			playerPokemonList.add(p);
		} else {
			PlayerStorage p = new PlayerStorage(player);
			playerPokemonList.add(p);
			player.openGui(mod_Pixelmon.instance, EnumGui.ChooseStarter.getIndex(), player.worldObj, 0, 0, 0);
		}
	}

	public void save() {
		try {
			if (playerPokemonList.size() == 0)
				loadPlayer(ModLoader.getMinecraftInstance().thePlayer);
			for (int i = 0; i < playerPokemonList.size(); i++) {
				if (playerPokemonList.get(i).count() == 0)
					playerPokemonList.get(i).player.openGui(mod_Pixelmon.instance, EnumGui.ChooseStarter.getIndex(), ModLoader.getMinecraftInstance().theWorld,
							0, 0, 0);
				EntityPlayer player = playerPokemonList.get(i).player;
				File playerSaveFile = new File(getSaveFolder() + "client.pk");
				CompressedStreamTools.write(getData(player), new DataOutputStream(new FileOutputStream(playerSaveFile)));
			}
			// CompressedStreamTools.write(data, getFile());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private NBTTagCompound getData(EntityPlayer player) {
		for (PlayerStorage p : playerPokemonList)
			if (p.player == player) {
				NBTTagCompound n = new NBTTagCompound();
				p.writeToNBT(n);
				return n;
			}
		return null;
	}

	private String getSaveFolder() {
		return ModLoader.getMinecraftInstance().getMinecraftDir() + "/saves/"
				+ ModLoader.getMinecraftInstance().theWorld.getSaveHandler().getSaveDirectoryName() + "/pokemon/";
	}

	@Override
	public void onWorldLoad(World world) {
		playerPokemonList.clear();
		mod_Pixelmon.serverStorageDisplay.clear();
		mod_Pixelmon.firstJoin=true;
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
}
