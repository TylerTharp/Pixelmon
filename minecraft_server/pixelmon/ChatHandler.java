package pixelmon;

import net.minecraft.src.EntityPlayer;

public class ChatHandler {

	public static void sendChat(EntityPlayer owner, String string) {
		if (owner !=null) owner.addChatMessage(string);
	}

	public static void sendChat(EntityPlayer owner, EntityPlayer owner2,
			String string) {
		if (owner !=null) owner.addChatMessage(string);
		if (owner2 !=null) owner2.addChatMessage(string);
		
	}

}
