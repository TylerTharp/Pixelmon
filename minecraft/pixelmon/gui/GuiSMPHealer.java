package pixelmon.gui;

import pixelmon.attacks.Attack;
import pixelmon.attacks.Type;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.entities.PixelmonEntityHelper;
import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.ModLoader;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.ScaledResolution;
import net.minecraft.src.StatCollector;
import net.minecraft.src.mod_Pixelmon;

public class GuiSMPHealer extends GuiScreen {

	private boolean isHealing=false;
	public ProgressBar[] progressBars;

	public GuiSMPHealer() {
		progressBars = new ProgressBar[6];
		for (int i = 0; i < progressBars.length; i++)
			progressBars[i] = new ProgressBar();
	}

	@SuppressWarnings("unchecked")
	public void initGui() {
		controlList.clear();
		controlList.add(new GuiButton(0, width /2-100, (int) (height * 0.8), StatCollector.translateToLocal("Heal")));
	}

	@Override
	public void updateScreen() {
		if (!isHealing)return;
		int num = 0;
		if (ModLoader.getMinecraftInstance().theWorld.isRemote)
			num =  mod_Pixelmon.serverStorageDisplay.count();
		else
			num = mod_Pixelmon.pokeballManager.getPlayerStorage(ModLoader.getMinecraftInstance().thePlayer).count();
		for (int i = 0; i < num; i++) {
			if (progressBars[i].value < 100) {
				progressBars[i].value++;
				break;
			}
		}
	}
	
	public void actionPerformed(GuiButton b)
	{
		isHealing=true;
	}

	public void drawScreen(int i, int i1, float f) {
		drawDefaultBackground();
		super.drawScreen(i, i1, f);
		drawCenteredString(fontRenderer, "Heal your pokemon?", width / 2, 10, 0xFFFFFF);
		
		if (ModLoader.getMinecraftInstance().theWorld.isRemote) {
			int j=0;
			for (PixelmonDataPacket p : mod_Pixelmon.serverStorageDisplay.pokemon) {
				int offset = 0;
				if (p != null) {
					drawPokemonStats(p, width / 2-100, height * (j+2) / 10, true);
					progressBars[j].draw(width / 2, height * (j+2) / 10, 20, 200, width,height);
					j++;
				}
			}

		} else {
			int j=0;
			for (NBTTagCompound n: mod_Pixelmon.pokeballManager.getPlayerStorage(ModLoader.getMinecraftInstance().thePlayer).partyPokemon){
				if (n!=null){
					drawPokemonStats(n, width/2-100-fontRenderer.getStringWidth(n.getString("Nickname"))-1, height * (j+2) / 10);
					progressBars[j].draw(width / 2, height * (j+2) / 10, 20, 200, width,height);
					if (progressBars[j].value!=100)drawString(fontRenderer, progressBars[j].value +" %",width/2+110,height * (j+2) / 10+6, 0xDDDDDD);
					else drawString(fontRenderer, "Healed" ,width/2+110,height * (j+2) / 10+6, 0xDDDDDD);
					j++;
				}
			}
		}
	}
	
	public void drawPokemonStats(NBTTagCompound pixelmon, int x, int y) {
		fontRenderer.FONT_HEIGHT = 10;
		drawString(fontRenderer, pixelmon.getString("Nickname"), x, y, 0xDDDDDD);
		drawCenteredString(fontRenderer, pixelmon.getShort("Health") + "/" + pixelmon.getInteger("StatsHP"), x+fontRenderer.getStringWidth(pixelmon.getString("Nickname"))/2, y + 15, 0xDDDDDD);

	}
	
	public void drawPokemonStats(PixelmonDataPacket pixelmon, int x, int y, boolean isMine) {
		fontRenderer.FONT_HEIGHT = 10;
		drawString(fontRenderer, pixelmon.nickname, x, y, 0xDDDDDD);
		if (pixelmon.isMale)
			drawString(fontRenderer, "♂ Lv." + pixelmon.lvl, x + width / 4, y, 0xDDDDDD);
		else
			drawString(fontRenderer, "♀ Lv." + pixelmon.lvl, x + width / 4, y, 0xDDDDDD);
		drawString(fontRenderer, pixelmon.health + "/" + pixelmon.hp, x + 50, y + 15, 0xDDDDDD);

	}
}
