package pixelmon.gui.pokedex;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.src.GuiButton;
import net.minecraft.src.ModLoader;
import net.minecraft.src.RenderHelper;
import net.minecraft.src.ScaledResolution;
import net.minecraft.src.Tessellator;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public abstract class GuiPokedexSlotBase
{
    /**
     * The width of the GuiScreen. Affects the container rendering, but not the overlays.
     */
    private final int width;

    /**
     * The height of the GuiScreen. Affects the container rendering, but not the overlays or the scrolling.
     */
    private final int height;

    /** The top of the slot container. Affects the overlays and scrolling. */
    protected final int top;

    /** The bottom of the slot container. Affects the overlays and scrolling. */
    protected final int bottom;
    private final int right;
    private final int left;

    /** The height of a slot. */
    protected final int slotHeight;

    /** button id of the button used to scroll up */
    private int scrollUpButtonID;

    /** the buttonID of the button used to scroll down */
    private int scrollDownButtonID;

    /** X axis position of the mouse */
    protected int mouseX;

    /** Y axis position of the mouse */
    protected int mouseY;

    /** where the mouse was in the window when you first clicked to scroll */
    private float initialClickY = -2.0F;

    /**
     * what to multiply the amount you moved your mouse by(used for slowing down scrolling when over the items and no on
     * scroll bar)
     */
    private float scrollMultiplier;

    /** how far down this slot has been scrolled */
    private float amountScrolled;

    /** the element in the list that was selected */
    private int selectedElement = -1;

    /** the time when this button was last clicked. */
    private long lastClicked = 0L;
    private boolean field_25123_p = true;
    private boolean field_27262_q;
    private int field_27261_r;

    public GuiPokedexSlotBase(int top, int left, int height)
    {
        this.width = 80;
        this.height = 175;
        this.top = top;
        this.bottom = this.top+this.height;
        this.slotHeight = 10;
        this.left = left;
        this.right = this.width+this.left;
    }

    public void func_27258_a(boolean par1)
    {
        this.field_25123_p = par1;
    }

    protected void func_27259_a(boolean par1, int par2)
    {
        this.field_27262_q = par1;
        this.field_27261_r = par2;

        if (!par1)
        {
            this.field_27261_r = 0;
        }
    }

    /**
     * Gets the size of the current slot list.
     */
    protected abstract int getSize();

    /**
     * the element in the slot that was clicked, boolean for wether it was double clicked or not
     */
    protected abstract void elementClicked(int var1, boolean var2);

    /**
     * returns true if the element passed in is currently selected
     */
    protected abstract boolean isSelected(int var1);

    /**
     * return the height of the content being scrolled
     */
    protected int getContentHeight()
    {
        return this.getSize() * 4 + this.field_27261_r;
    }

    protected abstract void drawBackground();

    protected abstract void drawSlot(int var1, int var2, int var3, int var4, Tessellator var5);

    protected void func_27260_a(int par1, int par2, Tessellator par3Tessellator) {}

    protected void func_27255_a(int par1, int par2) {}

    protected void func_27257_b(int par1, int par2) {}

    public int func_27256_c(int par1, int par2)
    {
        int var3 = this.width / 2 - 110;
        int var4 = this.width / 2 + 110;
        int var5 = par2 - this.top - this.field_27261_r + (int)this.amountScrolled - 4;
        int var6 = var5 / this.slotHeight;
        return par1 >= var3 && par1 <= var4 && var6 >= 0 && var5 >= 0 && var6 < this.getSize() ? var6 : -1;
    }

    /**
     * Registers the IDs that can be used for the scrollbar's buttons.
     */
    public void registerScrollButtons(List par1List, int par2, int par3)
    {
        this.scrollUpButtonID = par2;
        this.scrollDownButtonID = par3;
    }

    /**
     * stop the thing from scrolling out of bounds
     */
    private void bindAmountScrolled()
    {
        int var1 = this.getContentHeight() - (this.bottom - this.top - 4);

        if (var1 < 0)
        {
            var1 /= 2;
        }

        if (this.amountScrolled < 0.0F)
        {
            this.amountScrolled = 0.0F;
        }

        if (this.amountScrolled > (float)var1)
        {
            this.amountScrolled = (float)var1;
        }
    }

    public void actionPerformed(GuiButton par1GuiButton)
    {
        if (par1GuiButton.enabled)
        {
            if (par1GuiButton.id == this.scrollUpButtonID)
            {
                this.amountScrolled -= (float)(this.slotHeight * 2 / 3);
                this.initialClickY = -2.0F;
                this.bindAmountScrolled();
            }
            else if (par1GuiButton.id == this.scrollDownButtonID)
            {
                this.amountScrolled += (float)(this.slotHeight * 2 / 3);
                this.initialClickY = -2.0F;
                this.bindAmountScrolled();
            }
        }
    }

    /**
     * draws the slot to the screen, pass in mouse's current x and y and partial ticks
     */
    public void drawScreen(int mousePosX, int mousePosY, float par3)
    {
    	ScaledResolution var5 = new ScaledResolution(ModLoader.getMinecraftInstance().gameSettings, ModLoader.getMinecraftInstance().displayWidth,
				ModLoader.getMinecraftInstance().displayHeight);
        this.mouseX = mousePosX;
        this.mouseY = mousePosY;
        //this.drawBackground();
        int length = this.getSize();
        int posScrollBar = left + this.width;
        int posScrollBar2 = posScrollBar + 6;
        int var9;
        int var10;
        int index;
        int middleOfSelect;
        int topLeftOfSelect;
        int var6 = var5.getScaledWidth();
		int var7 = var5.getScaledHeight();
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_LIGHTING);

		ModLoader.getMinecraftInstance().entityRenderer.setupOverlayRendering();
		RenderHelper.enableGUIStandardItemLighting();
        
        if (Mouse.isButtonDown(0))
        {
            if (this.initialClickY == -1.0F)
            {
                boolean flag = true;

                if (mousePosY >= this.top && mousePosY <= this.bottom)
                {
                    int var8 = this.left;
                    var9 = this.left + this.width;
                    var10 = mousePosY - this.top - this.field_27261_r + (int)this.amountScrolled - 4;
                    index = var10 / this.slotHeight;

                    if (mousePosX >= var8 && mousePosX <= var9 && index >= 0 && var10 >= 0 && index < length)
                    {
                        boolean var12 = index == this.selectedElement && System.currentTimeMillis() - this.lastClicked < 250L;
                        this.elementClicked(index, var12);
                        this.selectedElement = index;
                        this.lastClicked = System.currentTimeMillis();
                    }
                    else if (mousePosX >= var8 && mousePosX <= var9 && var10 < 0)
                    {
                        this.func_27255_a(mousePosX - var8, mousePosY - this.top + (int)this.amountScrolled - 4);
                        flag = false;
                    }

                    if (mousePosX >= posScrollBar && mousePosX <= posScrollBar2)
                    {
                        this.scrollMultiplier = -1.0F;
                        topLeftOfSelect = this.getContentHeight() - (this.bottom - this.top - 4);

                        if (topLeftOfSelect < 1)
                        {
                            topLeftOfSelect = 1;
                        }

                        middleOfSelect = (int)((float)((this.bottom - this.top) * (this.bottom - this.top)) / (float)this.getContentHeight());

                        if (middleOfSelect < 32)
                        {
                            middleOfSelect = 32;
                        }

                        if (middleOfSelect > this.bottom - this.top - 8)
                        {
                            middleOfSelect = this.bottom - this.top - 8;
                        }

                        this.scrollMultiplier /= (float)(this.bottom - this.top - middleOfSelect) / (float)topLeftOfSelect;
                    }
                    else
                    {
                        this.scrollMultiplier = 1.0F;
                    }

                    if (flag)
                    {
                        this.initialClickY = (float)mousePosY;
                    }
                    else
                    {
                        this.initialClickY = -2.0F;
                    }
                }
                else
                {
                    this.initialClickY = -2.0F;
                }
            }
            else if (this.initialClickY >= 0.0F)
            {
                this.amountScrolled -= ((float)mousePosY - this.initialClickY) * this.scrollMultiplier;
                this.initialClickY = (float)mousePosY;
            }
        }
        else
        {
            while (Mouse.next())
            {
                int scroll = Mouse.getEventDWheel();

                if (scroll != 0)
                {
                    if (scroll > 0)
                    {
                        scroll = -1;
                    }
                    else if (scroll < 0)
                    {
                        scroll = 1;
                    }

                    this.amountScrolled += (float)(scroll * this.slotHeight / 2);
                }
            }

            this.initialClickY = -1.0F;
        }

        this.bindAmountScrolled();
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_FOG);
        Tessellator tessellator = Tessellator.instance;
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        float var17 = 32.0F;

        var9 = this.left;//posX of scroll
        var10 = this.top + 4 - (int)this.amountScrolled;//posY of scroll

        if (this.field_27262_q)
        {
            this.func_27260_a(var9, var10, tessellator);
        }

        int leftish;

        for (index = 0; index < length; ++index)//draw the buttons, and the selection box
        {
            topLeftOfSelect = var10 + index * this.slotHeight + this.field_27261_r;
            middleOfSelect = this.slotHeight - 4;

            if (topLeftOfSelect+6 <= this.bottom && topLeftOfSelect + middleOfSelect-8 >= this.top)
            {
                if (this.field_25123_p && this.isSelected(index))//draws the selected entry thing
                {
                    leftish = left;
                    int rightish = left + this.width;
                    GL11.glColor4f(1.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glDisable(GL11.GL_TEXTURE_2D);
                    tessellator.startDrawingQuads();
                    tessellator.setColorOpaque_I(8421504);
                    tessellator.addVertexWithUV((double)80, (double)(topLeftOfSelect + middleOfSelect + 2), 0.0D, 0.0D, 1.0D);
                    tessellator.addVertexWithUV((double)80, (double)(topLeftOfSelect + middleOfSelect + 2), 0.0D, 1.0D, 1.0D);
                    tessellator.addVertexWithUV((double)80, (double)(topLeftOfSelect - 2), 0.0D, 1.0D, 0.0D);
                    tessellator.addVertexWithUV((double)80, (double)(topLeftOfSelect - 2), 0.0D, 0.0D, 0.0D);
                    tessellator.setColorOpaque_I(0);
                    tessellator.addVertexWithUV((double)(leftish + 1), (double)(topLeftOfSelect + middleOfSelect + 1), 0.0D, 0.0D, 1.0D);
                    tessellator.addVertexWithUV((double)(rightish - 1), (double)(topLeftOfSelect + middleOfSelect + 1), 0.0D, 1.0D, 1.0D);
                    tessellator.addVertexWithUV((double)(rightish - 1), (double)(topLeftOfSelect - 1), 0.0D, 1.0D, 0.0D);
                    tessellator.addVertexWithUV((double)(leftish + 1), (double)(topLeftOfSelect - 1), 0.0D, 0.0D, 0.0D);
                    tessellator.draw();
                    GL11.glEnable(GL11.GL_TEXTURE_2D);
                }

                this.drawSlot(index, var9, topLeftOfSelect, middleOfSelect, tessellator);
            }
        }

        GL11.glDisable(GL11.GL_DEPTH_TEST);
        byte var20 = 4;
        this.overlayBackground(0, this.top, 255, 255);
        this.overlayBackground(this.bottom, this.height, 255, 255);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA(255, 0, 0, 0);
        tessellator.addVertexWithUV((double)this.left, (double)(this.top + var20), 0.0D, 0.0D, 1.0D);
        tessellator.addVertexWithUV((double)this.right, (double)(this.top + var20), 0.0D, 1.0D, 1.0D);
        tessellator.setColorRGBA(255, 0, 0, 255);
        tessellator.addVertexWithUV((double)this.right, (double)this.top, 0.0D, 1.0D, 0.0D);
        tessellator.addVertexWithUV((double)this.left, (double)this.top, 0.0D, 0.0D, 0.0D);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA(255, 0, 0, 255);
        tessellator.addVertexWithUV((double)this.left, (double)this.bottom+2, 0.0D, 0.0D, 1.0D);
        tessellator.addVertexWithUV((double)this.right, (double)this.bottom+2, 0.0D, 1.0D, 1.0D);
        tessellator.setColorRGBA(255, 0, 0, 0);
        tessellator.addVertexWithUV((double)this.right, (double)(this.bottom+2 - var20), 0.0D, 1.0D, 0.0D);
        tessellator.addVertexWithUV((double)this.left, (double)(this.bottom+2 - var20), 0.0D, 0.0D, 0.0D);
        tessellator.draw();
        topLeftOfSelect = this.getContentHeight() - (this.bottom - this.top - 4);

        if (topLeftOfSelect > 0)
        {
            middleOfSelect = (this.bottom - this.top) * (this.bottom - this.top) / this.getContentHeight();

            if (middleOfSelect < 32)
            {
                middleOfSelect = 32;
            }

            if (middleOfSelect > this.bottom - this.top - 8)
            {
                middleOfSelect = this.bottom - this.top - 8;
            }

            leftish = (int)this.amountScrolled * (this.bottom - this.top - middleOfSelect) / topLeftOfSelect + this.top;

            if (leftish < this.top)
            {
                leftish = this.top;
            }

            tessellator.startDrawingQuads();
            tessellator.setColorRGBA_I(0, 255);
            tessellator.addVertexWithUV((double)posScrollBar, (double)this.bottom+2, 0.0D, 0.0D, 1.0D);
            tessellator.addVertexWithUV((double)posScrollBar2, (double)this.bottom+2, 0.0D, 1.0D, 1.0D);
            tessellator.addVertexWithUV((double)posScrollBar2, (double)this.top+2, 0.0D, 1.0D, 0.0D);
            tessellator.addVertexWithUV((double)posScrollBar, (double)this.top+2, 0.0D, 0.0D, 0.0D);
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setColorRGBA_I(8421504, 255);
            tessellator.addVertexWithUV((double)posScrollBar, (double)(leftish + middleOfSelect+2), 0.0D, 0.0D, 1.0D);
            tessellator.addVertexWithUV((double)posScrollBar2, (double)(leftish + middleOfSelect+2), 0.0D, 1.0D, 1.0D);
            tessellator.addVertexWithUV((double)posScrollBar2, (double)leftish+2, 0.0D, 1.0D, 0.0D);
            tessellator.addVertexWithUV((double)posScrollBar, (double)leftish+2, 0.0D, 0.0D, 0.0D);
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setColorRGBA_I(12632256, 255);
            tessellator.addVertexWithUV((double)posScrollBar, (double)(leftish + middleOfSelect - 1+2), 0.0D, 0.0D, 1.0D);
            tessellator.addVertexWithUV((double)(posScrollBar2 - 1), (double)(leftish + middleOfSelect - 1+2), 0.0D, 1.0D, 1.0D);
            tessellator.addVertexWithUV((double)(posScrollBar2 - 1), (double)leftish+2, 0.0D, 1.0D, 0.0D);
            tessellator.addVertexWithUV((double)posScrollBar, (double)leftish+2, 0.0D, 0.0D, 0.0D);
            tessellator.draw();
        }

        this.func_27257_b(mousePosX, mousePosY);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glShadeModel(GL11.GL_FLAT);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glDisable(GL11.GL_BLEND);
    }

    /**
     * Overlays the background to hide scrolled items
     */
    private void overlayBackground(int par1, int par2, int par3, int par4)
    {
    	return;
    }
    private void drawImageQuad(int textureHandle, int x, int y, float w, float h, float us, float vs, float ue, float ve) {
		// activate the specified texture
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureHandle);

		float var7 = 0.00390625F;
		float var8 = 0.00390625F;
		Tessellator var9 = Tessellator.instance;
		var9.startDrawingQuads();
		var9.addVertexWithUV((double) (x + 0), (double) (y + h), (double) 0, (double) ((float) us), (double) ((float) ve));
		var9.addVertexWithUV((double) (x + w), (double) (y + h), (double) 0, (double) ((float) ue), (double) ((float) ve));
		var9.addVertexWithUV((double) (x + w), (double) (y + 0), (double) 0, (double) ((float) ue), (double) ((float) vs));
		var9.addVertexWithUV((double) (x + 0), (double) (y + 0), (double) 0, (double) ((float) us), (double) ((float) vs));
		var9.draw();
	}
}
