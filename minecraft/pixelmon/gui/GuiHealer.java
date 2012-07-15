package pixelmon.gui;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.StatCollector;

import org.lwjgl.opengl.GL11;

import pixelmon.ContainerHealer;
import pixelmon.TileEntityPokemonHealer;

public class GuiHealer extends GuiContainer
	{
	    private TileEntityPokemonHealer healerInventory;

	    public GuiHealer(InventoryPlayer par1InventoryPlayer, TileEntityPokemonHealer par2TileEntityHealer)
	    {
	        super(new ContainerHealer(par1InventoryPlayer, par2TileEntityHealer));
	        healerInventory = par2TileEntityHealer;
	    }

	    /**
	     * Draw the foreground layer for the GuiContainer (everythin in front of the items)
	     */
	    protected void drawGuiContainerForegroundLayer()
	    {
	        fontRenderer.drawString(StatCollector.translateToLocal("Healer"), 60, 6, 0x404040);
	        fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, (ySize - 96) + 2, 0x404040);
	    }

	    /**
	     * Draw the background layer for the GuiContainer (everything behind the items)
	     */
	    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	    {
	        int i = mc.renderEngine.getTexture("/pixelmon/gui/healer.png");
	        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	        mc.renderEngine.bindTexture(i);
	        int j = (width - xSize) / 2;
	        int k = (height - ySize) / 2;
	        drawTexturedModalRect(j, k, 0, 0, xSize, ySize);

	        if (healerInventory.isHealing())
	        {
	            int l = healerInventory.getHealTimeRemainingScaled(12);
	            drawTexturedModalRect(j + 82, (k + 31 + 12) - l, 176, 12 - l, 14, l + 2);
	        }
	    }
	}


