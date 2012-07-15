package pixelmon.comm;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.Packet;
import pixelmon.attacks.Moveset;

public class PixelmonMovesetDataPacket {
	public String attackName;
	public int pp;
	public int ppBase;

	public static PixelmonMovesetDataPacket createPacket(Moveset moveset, int i) {
		if (moveset.size() <= i)
			return null;
		PixelmonMovesetDataPacket p = new PixelmonMovesetDataPacket();
		p.attackName = moveset.get(i).attackName;
		p.pp = moveset.get(i).pp;
		p.ppBase = moveset.get(i).ppBase;
		return p;
	}

	public static PixelmonMovesetDataPacket createPacket(NBTTagCompound nbt, int i) {
		if (nbt.getInteger("PixelmonNumberMoves") <= i)
			return null;
		PixelmonMovesetDataPacket p = new PixelmonMovesetDataPacket();
		p.attackName = nbt.getString("PixelmonMoveName" + i);
		p.pp = nbt.getInteger("PixelmonMovePP" + i);
		p.ppBase = nbt.getInteger("PixelmonMovePPBase" + i);
		return null;
	}

	public void writeData(DataOutputStream data) throws IOException {
		Packet.writeString(attackName, data);
		data.writeInt(pp);
		data.writeInt(ppBase);
	}
	
	public void readData(DataInputStream data) throws IOException {
		attackName = Packet.readString(data, 64);
		pp = data.readInt();
		ppBase = data.readInt();
	}

}
