package pixelmon.gui;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import pixelmon.PixelmonEntityList;
import pixelmon.StarterList;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PacketCreator;
import pixelmon.entities.pixelmon.BaseEntityPixelmon;
import pixelmon.entities.pixelmon.helpers.IHaveHelper;
import pixelmon.entities.pixelmon.helpers.WildPokemonHelper;
import pixelmon.storage.PokeballManager;

import net.minecraft.client.Minecraft;
import net.minecraft.src.*;

public class GuiChooseStarter extends GuiScreen {

	String[] starterList;
	public GuiChooseStarter() {
		starterList = StarterList.getStarterStringList();
	}

	@SuppressWarnings("unchecked")
	public void initGui() {
		controlList.clear();
		for (int i = 0; i < starterList.length; i++) {
			controlList.add(new GuiButton(i, width / 3 - 100, height / 6 + i * 20, starterList[i]));
		}
	}

	public void keyTyped(char i, int i1) {
	}

	public void actionPerformed(GuiButton button) {
		String pixelmonName = starterList[button.id];
		if (!ModLoader.getMinecraftInstance().theWorld.isRemote) {
			IHaveHelper pixelmon = (IHaveHelper)PixelmonEntityList.createEntityByName(pixelmonName, ModLoader.getMinecraftInstance().theWorld);
			pixelmon.getHelper().getLvl().setLevel(5);
			pixelmon.getHelper().loadMoveset();
			mod_Pixelmon.pokeballManager.getPlayerStorage(ModLoader.getMinecraftInstance().thePlayer).addToParty(pixelmon.getHelper());
			pixelmon.catchInPokeball();
		} else {
			Packet250CustomPayload packet = PacketCreator.createPacket(EnumPackets.ChooseStarter, button.id);
			ModLoader.sendPacket(packet);
		}
		mc.displayGuiScreen(null);
	}

	public void drawScreen(int par1, int par2, float par3) {
		drawDefaultBackground();
		super.drawScreen(par1, par2, par3);
		drawCenteredString(fontRenderer, "Welcome to the world of Pixelmon!! Thank you for installing this mod!", width / 2, 10, 0xFFFFFF);
		drawCenteredString(fontRenderer, "Now, please pick your desired starter Pokemon!", width / 2, 20, 0xFFFFFF);
	}
}