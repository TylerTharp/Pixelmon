package pixelmon.gui.pokedex;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import pixelmon.entities.pixelmon.BaseEntityPixelmon;

import net.minecraft.client.Minecraft;
import net.minecraft.src.*;

public class SlotPokedex extends GuiSlot
{

	private Pokedex pokedex;
	private BaseEntityPixelmon selectedEntity;
	private GuiPokedex guiPokedex;
	
	public SlotPokedex(GuiPokedex gui, Minecraft mc, Pokedex p) 
	{
		super(mc, gui.width, gui.height, 16, gui.width - 32 + 4, 25);
		guiPokedex = gui;
		pokedex = p;
	}

	protected int getSize() 
	{
		return pokedex.size();
	}

	protected void elementClicked(int var1, boolean var2) 
	{
	}

	protected boolean isSelected(int var1) 
	{
		return false;
	}

	protected void drawBackground() 
	{
	}

	protected void drawSlot(int index, int var2, int var3, int var4, Tessellator var5) 
	{
		if(index == 0)
			return;
		PokedexEntry e = pokedex.getEntry(index - 1);
	}
}