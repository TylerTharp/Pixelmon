package pixelmon.gui;

import java.util.ArrayList;

import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.ModLoader;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.mod_Pixelmon;

import org.lwjgl.input.Keyboard;

import pixelmon.attacks.BattleController;
import pixelmon.entities.PixelmonEntityHelper;
import pixelmon.storage.PokeballManager;

public class GuiChoosePokemon extends GuiScreen {

	@SuppressWarnings("unused")
	private BattleController bc;
	@SuppressWarnings("unused")
	private GuiScreen parentGui;
	private PixelmonEntityHelper currentPixelmon;
	
	public GuiChoosePokemon(BattleController bc, PixelmonEntityHelper mypixelmon, GuiScreen parentGui) {
		this.parentGui=parentGui;
		this.bc = bc;
		currentPixelmon = mypixelmon;
	}

	public GuiChoosePokemon(BattleController bc, PixelmonEntityHelper mypixelmon) {
		this.bc = bc;
		currentPixelmon = mypixelmon;
	}
	
	@SuppressWarnings("unchecked")
	public void initGui() {
		Keyboard.enableRepeatEvents(true);
		controlList.clear();
		NBTTagCompound[] pokemon = mod_Pixelmon.pokeballManager.getPlayerStorage(ModLoader.getMinecraftInstance().thePlayer).getList();
		int i=0;
		for (NBTTagCompound p:pokemon){
			if(p != null){
				if (!p.getBoolean("IsFainted") && p.getInteger("pixelmonID")!=currentPixelmon.getPokemonId()) {
					if (!p.getString("Nickname").isEmpty())
						controlList.add(new GuiButton(p.getInteger("PixelmonOrder"), width / 2 - 100, height / 4 + i*24 + 20 + 12,	p.getString("Nickname")));
					else
						controlList.add(new GuiButton(p.getInteger("PixelmonOrder"), width / 2 - 100, height / 4 + i*24 + 20 + 12,	p.getString("Name")));
					i++;
				}
			}
		}
		controlList.add(new GuiButton(10, width / 2 - 100, height / 2 + 20,
				"Back"));
	}

	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

	protected void actionPerformed(GuiButton par1GuiButton) {
		if (par1GuiButton.id < 6) {
			for(NBTTagCompound n:mod_Pixelmon.pokeballManager.getPlayerStorage(ModLoader.getMinecraftInstance().thePlayer).getList()){
				if(n != null){
					if (n.getInteger("PixelmonOrder") == par1GuiButton.id) bc.SwitchPokemon(currentPixelmon, n.getInteger("pixelmonID"));
				}
			}
			mc.displayGuiScreen(null);
			mc.setIngameFocus();
		} else {
			if (parentGui!=null)
				;
			else
				mc.displayGuiScreen(parentGui);
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
