package pixelmon.comm;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.Packet250CustomPayload;
import net.minecraft.src.mod_Pixelmon;

public class PacketCreator {
	public static Packet250CustomPayload createPacket(EnumPackets epacket, int i) {
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		DataOutputStream data = new DataOutputStream(bytes);
		Packet250CustomPayload packet = new Packet250CustomPayload();
		try {
			data.writeInt(epacket.getIndex());
			data.writeInt(i);
		} catch (IOException e) {
			e.printStackTrace();
		}
		packet.channel = "Pixelmon";
		packet.data = bytes.toByteArray();
		packet.length = packet.data.length;
		return packet;
	}
}
