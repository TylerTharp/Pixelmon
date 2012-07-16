package pixelmon.comm;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import pixelmon.attacks.Type;
import pixelmon.database.BaseStats;
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
	public Type type1;
	public Type type2;
	public int HP;
	public int Speed;
	public int Attack;
	public int Defence;
	public int SpecialAttack;
	public int SpecialDefence;
	public int nextLvlXP;
	
	public PixelmonMovesetDataPacket[] moveset = new PixelmonMovesetDataPacket[4];
	
	public PixelmonDataPacket() {
		
	}

	public PixelmonDataPacket(NBTTagCompound p, NetworkMod mod, EnumPackets packetType) {
		this.packetType = packetType;
		modID = MinecraftForge.getModID(mod);
		pokemonID = p.getInteger("pixelmonID");
		BaseStats b = DatabaseStats.GetBaseStats(p.getString("Name"));
		nationalPokedexNumber = b.nationalPokedexNumber;
		name = p.getString("Name");
		nickname = p.getString("Nickname");
		lvl = p.getInteger("Level");
		nextLvlXP = p.getInteger("EXPToNextLevel");
		hp = p.getInteger("StatsHP");
		health = p.getShort("Health");
		isMale = p.getBoolean("IsMale");
		isFainted = p.getBoolean("IsFainted");
		order = p.getInteger("PixelmonOrder");
		numMoves = p.getInteger("PixelmonNumberMoves");
		for (int i =0; i < numMoves; i++){
			moveset[i] = PixelmonMovesetDataPacket.createPacket(p,i);
		}
		type1 = b.Type1;
		type2 = b.Type2;
		HP = p.getInteger("StatsHP");
		Speed = p.getInteger("StatsSpeed");
		Attack = p.getInteger("StatsAttack");
		Defence = p.getInteger("StatsDefence");
		SpecialAttack = p.getInteger("StatsSpecialAttack");
		SpecialDefence = p.getInteger("StatsSpecialDefence");
	}

	public PixelmonDataPacket(PixelmonEntityHelper p, NetworkMod mod, EnumPackets packetType) {
		this.packetType = packetType;
		modID = MinecraftForge.getModID(mod);
		pokemonID = p.getPokemonId();
		nationalPokedexNumber = p.getStats().BaseStats.nationalPokedexNumber;
		name = p.getName();
		nickname = p.getNickName();
		lvl = p.getLvl().getLevel();
		nextLvlXP = p.getLvl().getExpToNextLevel();
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
		type1 = p.getType().get(0);
		if (p.getType().size()==1) type2 = Type.Mystery;
		else type2 = p.getType().get(1);
		HP = p.getStats().HP;
		Speed = p.getStats().Speed;
		Attack = p.getStats().Attack;
		Defence = p.getStats().Defence;
		SpecialAttack = p.getStats().SpecialAttack;
		SpecialDefence = p.getStats().SpecialDefence;		
	}

	public void writeData(DataOutputStream data) throws IOException {
		data.writeInt(modID);
		data.writeInt(pokemonID);
		data.writeShort(nationalPokedexNumber);
		Packet.writeString(name, data);
		Packet.writeString(nickname, data);
		data.writeShort(lvl);
		data.writeShort(nextLvlXP);
		data.writeShort(hp);
		data.writeShort(health);
		data.writeBoolean(isMale);
		data.writeBoolean(isFainted);
		data.writeShort(order);
		data.writeShort(numMoves);
		for (int i=0; i < numMoves; i++){
			moveset[i].writeData(data);
		}
		data.writeShort(type1.getIndex());
		data.writeShort(type2.getIndex());
		data.writeShort(HP);
		data.writeShort(Speed);
		data.writeShort(Attack);
		data.writeShort(Defence);
		data.writeShort(SpecialAttack);
		data.writeShort(SpecialDefence);
	}

	public void readData(DataInputStream data) throws IOException {
		modID = data.readInt();
		pokemonID = data.readInt();
		nationalPokedexNumber = data.readShort();
		name = Packet.readString(data, 64);
		nickname = Packet.readString(data, 64);
		lvl = data.readShort();
		nextLvlXP = data.readShort();
		hp = data.readShort();
		health = data.readShort();
		isMale = data.readBoolean();
		isFainted = data.readBoolean();
		order = data.readShort();
		numMoves = data.readShort();
		for (int i=0; i < numMoves; i++){
			moveset[i] = new PixelmonMovesetDataPacket();
			moveset[i].readData(data);
		}
		type1 = Type.parseType(data.readShort());
		type2 = Type.parseType(data.readShort());
		HP = data.readShort();
		Speed = data.readShort();
		Attack = data.readShort();
		Defence = data.readShort();
		SpecialAttack = data.readShort();
		SpecialDefence = data.readShort();
	}

	@Override
	public int getID() {
		return packetType.getIndex();
	}
}
