package pixelmon;

import java.util.Random;

import pixelmon.comm.EnumPackets;
import pixelmon.comm.PacketCreator;
import pixelmon.entities.EntityPokeBall;
import pixelmon.entities.IHaveHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.src.KeyBinding;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModLoader;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.Packet250CustomPayload;
import net.minecraft.src.mod_Pixelmon;

public class KeyboardHandler {

	public static void handleKeyboardEvent(KeyBinding event) {
		if (ModLoader.getMinecraftInstance().theWorld == null)
			return;
		if (ModLoader.getMinecraftInstance().currentScreen != null)
			return;
		if (event == mod_Pixelmon.nextPixelmonKey) {
			mod_Pixelmon.pixelmonOverlay.selectNextPixelmon();
		} else if (event == mod_Pixelmon.previousPixelmonKey) {
			mod_Pixelmon.pixelmonOverlay.selectPreviousPixelmon();
		} else if (event == mod_Pixelmon.sendPixelmonKey) {
			if (ModLoader.getMinecraftInstance().theWorld.isRemote) {
				ModLoader.sendPacket(PacketCreator.createPacket(EnumPackets.SendPokemon,
						mod_Pixelmon.serverStorageDisplay.pokemon[mod_Pixelmon.pixelmonOverlay.selectedPixelmon].pokemonID));
			} else {
				Minecraft mc = ModLoader.getMinecraftInstance();
				NBTTagCompound nbt = mod_Pixelmon.pokeballManager.getPlayerStorage(mc.thePlayer).getNBT(
						mod_Pixelmon.pokeballManager.getPlayerStorage(mc.thePlayer).getIDFromPosition(mod_Pixelmon.pixelmonOverlay.selectedPixelmon));
				if (!mod_Pixelmon.pokeballManager.getPlayerStorage(mc.thePlayer).EntityAlreadyExists(
						mod_Pixelmon.pokeballManager.getPlayerStorage(mc.thePlayer).getIDFromPosition(mod_Pixelmon.pixelmonOverlay.selectedPixelmon),
						mc.theWorld)
						&& (mod_Pixelmon.currentPokeball == null || mod_Pixelmon.currentPokeball.isDead)
						&& !mod_Pixelmon.pokeballManager.getPlayerStorage(mc.thePlayer).isFainted(
								mod_Pixelmon.pokeballManager.getPlayerStorage(mc.thePlayer).getIDFromPosition(mod_Pixelmon.pixelmonOverlay.selectedPixelmon))) {
					mc.theWorld.playSoundAtEntity(mc.thePlayer, "random.bow", 0.5F, 0.4F / ((new Random()).nextFloat() * 0.4F + 0.8F));
					mod_Pixelmon.currentPokeball = new EntityPokeBall(mc.theWorld, mc.thePlayer, mod_Pixelmon.pokeballManager
							.getPlayerStorage(mc.thePlayer)
							.sendOut(
									mod_Pixelmon.pokeballManager.getPlayerStorage(mc.thePlayer)
											.getIDFromPosition(mod_Pixelmon.pixelmonOverlay.selectedPixelmon), mc.theWorld).getHelper());
					boolean flag = MathHelper.stringNullOrLengthZero(nbt.getString("Nickname"));
					mc.ingameGUI.addChatMessage("You sent out " + (flag ? nbt.getString("Name") : nbt.getString("Nickname")) + "!");
					mc.theWorld.spawnEntityInWorld(mod_Pixelmon.currentPokeball);
				} else if (mod_Pixelmon.pokeballManager.getPlayerStorage(mc.thePlayer).isFainted(
						mod_Pixelmon.pokeballManager.getPlayerStorage(mc.thePlayer).getIDFromPosition(mod_Pixelmon.pixelmonOverlay.selectedPixelmon))) {
					boolean flag = MathHelper.stringNullOrLengthZero(nbt.getString("Nickname"));
					mc.ingameGUI.addChatMessage((flag ? nbt.getString("Name") : nbt.getString("Nickname")) + " is unable to battle!");
				} else if (mod_Pixelmon.pokeballManager.getPlayerStorage(mc.thePlayer).EntityAlreadyExists(
						mod_Pixelmon.pokeballManager.getPlayerStorage(mc.thePlayer).getIDFromPosition(mod_Pixelmon.pixelmonOverlay.selectedPixelmon),
						mc.theWorld)) {
					IHaveHelper pixelmon = mod_Pixelmon.pokeballManager.getPlayerStorage(mc.thePlayer).getAlreadyExists(
							mod_Pixelmon.pokeballManager.getPlayerStorage(mc.thePlayer).getIDFromPosition(mod_Pixelmon.pixelmonOverlay.selectedPixelmon),
							mc.theWorld);
					if (pixelmon == null) {
						return;
					}
					if (pixelmon.getHelper().getOwner() == null)
						pixelmon.unloadEntity();
					else if (pixelmon.getHelper().getOwner() == ModLoader.getMinecraftInstance().thePlayer) {
						mod_Pixelmon.pokeballManager.getPlayerStorage(mc.thePlayer).retrieve(pixelmon);
						pixelmon.catchInPokeball();
						boolean flag = MathHelper.stringNullOrLengthZero(nbt.getString("Nickname"));
						mc.ingameGUI.addChatMessage("You retrieved " + (flag ? nbt.getString("Name") : nbt.getString("Nickname")) + "!");
					}

				}
			}
		} else if (event == mod_Pixelmon.minmizePixelmonKey) {
			mod_Pixelmon.isGuiMinimized = !mod_Pixelmon.isGuiMinimized;
		}
	}
}
