package pixelmon.storage;

import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.MathHelper;
import net.minecraft.src.forge.ISpawnHandler;
import net.minecraft.src.forge.IThrowableEntity;
import net.minecraft.src.forge.MinecraftForge;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.entities.BaseEntityPixelmon;
import pixelmon.entities.IHaveHelper;

public class ServerStorageDisplay {
	public PixelmonDataPacket[] pokemon = new PixelmonDataPacket[6];

	public void add(DataInputStream dataStream) {
		PixelmonDataPacket packet = new PixelmonDataPacket();
		try {
			packet.readData(dataStream);
			for (int i = 0; i < pokemon.length; i++) {
				if (pokemon[i] == null) {
					pokemon[i] = packet;
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int count() {
		int count = 0;
		for (int i = 0; i < pokemon.length; i++)
			if (pokemon[i] != null)
				count++;
		return count;
	}

	public void update(DataInputStream dataStream) {
		PixelmonDataPacket packet = new PixelmonDataPacket();
		try {
			packet.readData(dataStream);
		}catch(IOException e){
			e.printStackTrace();
		}
		for(int i=0; i < pokemon.length; i++){
			if (pokemon[i]!=null && pokemon[i].pokemonID == packet.pokemonID)
				pokemon[i] = packet;
		}

	}

	public void clear() {
		for (int i=0; i < pokemon.length; i++)
			pokemon[i] =null;
	}

	public boolean contains(int pokemonId) {
		for (int i=0; i < pokemon.length; i++){
			if (pokemon[i] !=null && pokemon[i].pokemonID == pokemonId) return true;
		}
		return false;
	}
}
