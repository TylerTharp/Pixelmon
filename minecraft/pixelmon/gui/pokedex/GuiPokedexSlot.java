package pixelmon.gui.pokedex;

import java.util.Date;


import net.minecraft.client.Minecraft;
import net.minecraft.src.GuiSelectWorld;
import net.minecraft.src.GuiSlot;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModLoader;
import net.minecraft.src.SaveFormatComparator;
import net.minecraft.src.StatCollector;
import net.minecraft.src.Tessellator;


public class GuiPokedexSlot extends GuiPokedexSlotBase {
	GuiPokedex guiPokedex;
	public GuiPokedexSlot(GuiPokedex gui) 
	{
		super(gui.top+19, gui.left+3, gui.height);
		guiPokedex = gui;
	}

	@Override
    protected int getSize()
    {
        return GuiPokedex.getSize(this.guiPokedex).size();
    }


    /**
     * the element in the slot that was clicked, boolean for wether it was double clicked or not
     */
    protected void elementClicked(int par1, boolean par2)
    {
    	GuiPokedex.setSelectedEntry(this.guiPokedex, par1);
    	GuiPokedex.onElementSelected(this.guiPokedex, par1);
    }

    /**
     * returns true if the element passed in is currently selected
     */

    protected boolean isSelected(int par1)
    {
        return par1 == GuiPokedex.getSelectedEntry(this.guiPokedex);
    }

    /**
     * return the height of the content being scrolled
     */
    protected int getContentHeight()
    {
        return GuiPokedex.getSize(this.guiPokedex).size() * 10;
    }

    protected void drawBackground()
    {
        this.guiPokedex.drawBackground(0);
    }

	@Override
	protected void drawSlot(int var1, int var2, int var3, int var4,
			Tessellator var5) {
		
		this.guiPokedex.drawString(ModLoader.getMinecraftInstance().fontRenderer, guiPokedex.pokedex.getEntry(var1).getDisplayNumber() + " " + (String)this.guiPokedex.pokedexList.get(var1), var2+2, var3-1, 16777215);
	}

}
