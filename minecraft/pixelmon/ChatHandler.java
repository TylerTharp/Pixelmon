package pixelmon;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ModLoader;

public class ChatHandler {

	public static void sendChat(EntityPlayer owner, String string) {
		if (owner!=null)
			ModLoader.getMinecraftInstance().ingameGUI.addChatMessage(string);
	}

	public static void sendChat(EntityPlayer owner, EntityPlayer owner2,
			String string) {
		if (owner!=null || owner2!=null)
			ModLoader.getMinecraftInstance().ingameGUI.addChatMessage(string);
		
	}

}
