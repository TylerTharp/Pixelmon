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
import cpw.mods.fml.server.FMLServerHandler;

import net.minecraft.server.MinecraftServer;
import net.minecraft.src.AnvilSaveHandler;
import net.minecraft.src.Chunk;
import net.minecraft.src.CompressedStreamTools;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityList;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.ISaveHandler;
import net.minecraft.src.ModLoader;
import net.minecraft.src.NBTBase;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NetworkManager;
import net.minecraft.src.Packet1Login;
import net.minecraft.src.SaveHandler;
import net.minecraft.src.World;
import net.minecraft.src.mod_Pixelmon;
import net.minecraft.src.forge.IConnectionHandler;
import net.minecraft.src.forge.ISaveEventHandler;

public class PokeballManager implements ISaveEventHandler {
	private File workingDir;

	private ArrayList<PlayerStorage> playerPokemonList = new ArrayList<PlayerStorage>();

	public enum PokeballManagerMode {
		Player, Trainer
	}

	public PokeballManager() {
	}

	public PlayerStorage getPlayerStorage(EntityPlayer owner) {

		for (PlayerStorage p : playerPokemonList) {
			if (p.player.username.equals(owner.username))
				return p;
		}
		loadPlayer(owner);
		return getPlayerStorage(owner);
	}

	@SuppressWarnings("unchecked")
	public void loadPlayer(EntityPlayer player) {
		File saveDirPath = new File(getSaveFolder(player));
		if (!saveDirPath.exists())
			saveDirPath.mkdirs();
		File playerFile = new File(getSaveFolder(player) + player.username + ".pk");
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
		}
	}

	public void save() {
		try {
			for (int i = 0; i < playerPokemonList.size(); i++) {
				EntityPlayer player = playerPokemonList.get(i).player;
				boolean playerConnected = false;
				for (String playerName : FMLServerHandler.instance().getServer().getPlayerNamesAsList())
					if (player.username.equals(playerName)) {
						playerConnected = true;
						break;
					}

				if (playerConnected) {
					File playerSaveFile = new File(getSaveFolder(player) + player.username + ".pk");
					FileOutputStream f = new FileOutputStream(playerSaveFile);
					DataOutputStream s = new DataOutputStream(f);
					CompressedStreamTools.write(getData(player), s);
					s.close();
					f.close();
				} else {
					playerPokemonList.remove(i);
					i--;
				}
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

	private String getSaveFolder(EntityPlayer player) {
		try {
			return ModLoader.getPrivateValue(SaveHandler.class, (SaveHandler) player.worldObj.getSaveHandler(), "b") + "/pokemon/";
		} catch (Throwable e) {
			//System.err.println(e);
			try {
				return ModLoader.getPrivateValue(SaveHandler.class, (SaveHandler) player.worldObj.getSaveHandler(), "worldDirectory") + "/pokemon/";
			} catch (Throwable f) {
				System.err.println(f);

			}
		}
		return null;
	}

	@Override
	public void onWorldLoad(World world) {
		playerPokemonList.clear();
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
