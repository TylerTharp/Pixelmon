package pixelmon.gui;

import java.util.ArrayList;

import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.ModLoader;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.mod_Pixelmon;

import org.lwjgl.input.Keyboard;

import pixelmon.attacks.BattleController;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PacketCreator;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;
import pixelmon.storage.PokeballManager;

public class GuiChoosePokemon extends GuiScreen {

	@SuppressWarnings("unused")
	private BattleController bc;
	@SuppressWarnings("unused")
	private GuiScreen parentGui;
	private PixelmonEntityHelper currentPixelmon;
	private PixelmonDataPacket userPacket;
	int bcIndex;

	public GuiChoosePokemon(BattleController bc, PixelmonEntityHelper mypixelmon, GuiScreen parentGui) {
		this.parentGui = parentGui;
		this.bc = bc;
		currentPixelmon = mypixelmon;
	}

	public GuiChoosePokemon(PixelmonDataPacket userPacket, int bcIndex, GuiAttacking parentGui) {
		this.parentGui = parentGui;
		this.userPacket = userPacket;
		this.bcIndex = bcIndex;
	}

	@SuppressWarnings("unchecked")
	public void initGui() {
		Keyboard.enableRepeatEvents(true);
		controlList.clear();
		if (ModLoader.getMinecraftInstance().theWorld.isRemote) {
			PixelmonDataPacket[] pokemon = mod_Pixelmon.serverStorageDisplay.pokemon;
			int i = 0;
			for (PixelmonDataPacket p : pokemon) {
				if (p != null) {
					if (!p.isFainted && p.pokemonID != userPacket.pokemonID) {
						controlList.add(new GuiButton(p.order, width / 2 - 100, height / 4 + i * 24 + 20 + 12, p.nickname));
					}
				}
			}
		} else {
			NBTTagCompound[] pokemon = mod_Pixelmon.pokeballManager.getPlayerStorage(ModLoader.getMinecraftInstance().thePlayer).getList();
			int i = 0;
			for (NBTTagCompound p : pokemon) {
				if (p != null) {
					if (!p.getBoolean("IsFainted") && p.getInteger("pixelmonID") != currentPixelmon.getPokemonId()) {
						if (!p.getString("Nickname").isEmpty())
							controlList.add(new GuiButton(p.getInteger("PixelmonOrder"), width / 2 - 100, height / 4 + i * 24 + 20 + 12, p.getString("Nickname")));
						else
							controlList.add(new GuiButton(p.getInteger("PixelmonOrder"), width / 2 - 100, height / 4 + i * 24 + 20 + 12, p.getString("Name")));
						i++;
					}
				}
			}
		}
		controlList.add(new GuiButton(10, width / 2 - 100, height / 2 + 20, "Back"));
	}

	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

	protected void actionPerformed(GuiButton par1GuiButton) {
		if (ModLoader.getMinecraftInstance().theWorld.isRemote) {
			if (par1GuiButton.id < 6) {
				ModLoader.sendPacket(PacketCreator.createPacket(EnumPackets.SwitchPokemon, par1GuiButton.id, bcIndex, 0));
				mc.displayGuiScreen(parentGui);
				mc.setIngameFocus();
			} else
				mc.displayGuiScreen(parentGui);
		} else {
			if (par1GuiButton.id < 6) {
				for (NBTTagCompound n : mod_Pixelmon.pokeballManager.getPlayerStorage(ModLoader.getMinecraftInstance().thePlayer).getList()) {
					if (n != null) {
						if (n.getInteger("PixelmonOrder") == par1GuiButton.id)
							bc.SwitchPokemon(currentPixelmon, n.getInteger("pixelmonID"));
					}
				}
				mc.displayGuiScreen(null);
				mc.setIngameFocus();
			} else {
				mc.displayGuiScreen(parentGui);
			}
		}
	}

	protected void mouseClicked(int par1, int par2, int par3) {
		super.mouseClicked(par1, par2, par3);
	}

	public void drawScreen(int i, int i1, float f) {

		drawDefaultBackground();
		super.drawScreen(i, i1, f);
		drawCenteredString(fontRenderer, "Who do you want to send out?", width / 2, 10, 0xFFFFFF);
	}
}
