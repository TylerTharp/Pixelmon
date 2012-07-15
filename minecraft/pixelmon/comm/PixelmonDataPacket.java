package pixelmon.comm;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import pixelmon.database.DatabaseStats;
import pixelmon.entities.PixelmonEntityHelper;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.DataWatcher;
import net.minecraft.src.MathHelper;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.Packet;
import net.minecraft.src.forge.ISpawnHandler;
import net.minecraft.src.forge.IThrowableEntity;
import net.minecraft.src.forge.MinecraftForge;
import net.minecraft.src.forge.NetworkMod;

public class PixelmonDataPacket extends PixelmonPacket {
	public int modID;
	public int pokemonID;
	public int nationalPokedexNumber;
	public String name;
	public String nickname;
	public int lvl;
	public int hp;
	public int health;
	public boolean isMale;
	public boolean isFainted;
	public int order;
	public int numMoves;
	
	public PixelmonMovesetDataPacket[] moveset = new PixelmonMovesetDataPacket[4];
	
	public PixelmonDataPacket() {
		
	}

	public PixelmonDataPacket(NBTTagCompound p, NetworkMod mod, EnumPackets packetType) {
		this.packetType = packetType;
		modID = MinecraftForge.getModID(mod);
		pokemonID = p.getInteger("pixelmonID");
		nationalPokedexNumber = DatabaseStats.getNationalPokedexNumber(p.getString("Name"));
		name = p.getString("Name");
		nickname = p.getString("Nickname");
		lvl = p.getInteger("Level");
		hp = p.getInteger("StatsHP");
		health = p.getShort("Health");
		isMale = p.getBoolean("IsMale");
		isFainted = p.getBoolean("IsFainted");
		order = p.getInteger("PixelmonOrder");
		numMoves = p.getInteger("PixelmonNumberMoves");
		for (int i =0; i < numMoves; i++){
			moveset[i] = PixelmonMovesetDataPacket.createPacket(p,i);
		}
	}

	public PixelmonDataPacket(PixelmonEntityHelper p, NetworkMod mod, EnumPackets packetType) {
		this.packetType = packetType;
		modID = MinecraftForge.getModID(mod);
		pokemonID = p.getPokemonId();
		nationalPokedexNumber = p.getStats().BaseStats.nationalPokedexNumber;
		name = p.getName();
		nickname = p.getNickName();
		lvl = p.getLvl().getLevel();
		hp = p.getStats().HP;
		health = p.getHealth();
		isMale = p.getIsMale();
		isFainted = p.getIsFainted();
		order = 0;
		if (p.getMoveset().size() ==0) p.loadMoveset();
		numMoves = p.getMoveset().size();
		for (int i =0; i < numMoves; i++){
			moveset[i] = PixelmonMovesetDataPacket.createPacket(p.getMoveset(),i);
		}
	}

	public void writeData(DataOutputStream data) throws IOException {
		data.writeInt(modID);
		data.writeInt(pokemonID);
		data.writeInt(nationalPokedexNumber);
		Packet.writeString(name, data);
		Packet.writeString(nickname, data);
		data.writeInt(lvl);
		data.writeInt(hp);
		data.writeInt(health);
		data.writeBoolean(isMale);
		data.writeBoolean(isFainted);
		data.writeInt(order);
		data.writeInt(numMoves);
		for (int i=0; i < numMoves; i++){
			moveset[i].writeData(data);
		}
	}

	public void readData(DataInputStream data) throws IOException {
		modID = data.readInt();
		pokemonID = data.readInt();
		nationalPokedexNumber = data.readInt();
		name = Packet.readString(data, 64);
		nickname = Packet.readString(data, 64);
		lvl = data.readInt();
		hp = data.readInt();
		health = data.readInt();
		isMale = data.readBoolean();
		isFainted = data.readBoolean();
		order = data.readInt();
		numMoves = data.readInt();
		for (int i=0; i < numMoves; i++){
			moveset[i] = new PixelmonMovesetDataPacket();
			moveset[i].readData(data);
		}
	}

	@Override
	public int getID() {
		return packetType.getIndex();
	}
}
