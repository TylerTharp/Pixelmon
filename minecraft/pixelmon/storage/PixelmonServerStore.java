package pixelmon.storage;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;

import pixelmon.comm.PixelmonDataPacket;

public class PixelmonServerStore {
	static ArrayList<PixelmonDataPacket> store = new ArrayList<PixelmonDataPacket>();

	public static void addToList(DataInputStream dataStream) {
		PixelmonDataPacket packet = new PixelmonDataPacket();
		try {
			packet.readData(dataStream);
			store.add(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static PixelmonDataPacket getPixelmonData(int index) {
		for (PixelmonDataPacket p : store) {
			if (p.order == index)
				return p;
		}
		return null;
	}

	public static void clearList() {
		store.clear();
	}
}
