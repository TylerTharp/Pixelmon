package pixelmon.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import pixelmon.ContainerPokedex;
import pixelmon.Pokedex;
import pixelmon.PokedexEntry;
import pixelmon.entities.BaseEntityPixelmon;
import pixelmon.entities.PixelmonEntityHelper;
import net.minecraft.src.*;

public class GuiPokedex extends GuiContainer
{
	
	public Pokedex pokedex;
    private GuiPokedexSlot pokedexSlotContainer;
    private int selectedEntryIndex;
	public EntityLiving selectedEntity;
	public PokedexEntry selectedEntry;
	private ContainerPokedex container;
	@SuppressWarnings("rawtypes")
	public List pokedexList;
	
	public int top;
	public int left;
	
	@SuppressWarnings("unchecked")
	public GuiPokedex()
	{
		super(new ContainerPokedex(new Pokedex()));
		container = (ContainerPokedex) super.inventorySlots;
		pokedex = container.getPokedex();
		pokedexList = new ArrayList<String>();
		for (int i =1; i <= 649; i++){
			PokedexEntry e = pokedex.getEntry(i);
			if (e!=null)
			pokedexList.add(e.name);
			else pokedexList.add("???");
		}
		mc = ModLoader.getMinecraftInstance();
		setSelectedEntry(pokedex.getEntry(1));
		xSize = 256;
		ySize = 202;
	}
	
	public GuiPokedex(PixelmonEntityHelper pixelmonEntityHelper)
	{
		this();
		setSelectedEntry(pokedex.getEntry(pokedex.getFromPokedex(pixelmonEntityHelper.getName())));
	}
	
	@SuppressWarnings("unchecked")
	public void initGui()
	{
        left = (width - xSize) / 2;
        top = (height - ySize) / 2;
		controlList.clear();
        this.pokedexSlotContainer = new GuiPokedexSlot(this);
        this.pokedexSlotContainer.registerScrollButtons(this.controlList, 4, 5);
	}
	
	int mouseX,mouseY;
	float mfloat;
    public void drawScreen(int par1, int par2, float par3)
    {
    	super.drawScreen(par1, par2, par3);
        left = (width - xSize) / 2;
        top = (height - ySize) / 2;
    	mouseX = par1;mouseY=par2;mfloat=par3;
        float f = selectedEntry.height;
        drawModelToScreen(f , left + 131, top + 107);
        int i = mc.renderEngine.getTexture("/pixelmon/gui/pokedex.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(i);
        drawTexturedModalRect(left, top, 0, 0, xSize, ySize);
        this.fontRenderer.drawString("Pokedex", left + 6, top + 5, 0xFFFFFF);
        String s = selectedEntry.getDisplayNumber() + " " + selectedEntry.name;
        fontRenderer.drawString(s, left + 174 - fontRenderer.getStringWidth(s) / 2, top + 38 - 3, 0x575757);
        s = "Description";
        fontRenderer.drawString(s, left + 141 - fontRenderer.getStringWidth(s) / 2, top + 125, 0x575757);
        s = selectedEntry.description;
        fontRenderer.drawSplitString(s, left + 104, top + 141 - 3, 141, 0x575757);
        s = "Height: ";
        s += selectedEntry.getDisplayHeight(mod_Pixelmon.isInMetric);
        fontRenderer.drawString(s, left + 164, top + 69 - 10, 0x575757);
        s = "Weight: ";
        s += selectedEntry.getDisplayWeight(mod_Pixelmon.isInMetric);
        fontRenderer.drawString(s, left + 164, top + 69 + 0, 0x575757);
        s = "Rarity: ";
        s += selectedEntry.getDisplayRarity(mod_Pixelmon.isInMetric);
        fontRenderer.drawString(s, left + 164, top + 69 + 10, 0x575757);
        this.pokedexSlotContainer.drawScreen(mouseX,mouseY,mfloat);
    }
    
    protected void actionPerformed(GuiButton par1GuiButton)
    {
    }
    
    public void setSelectedEntry(PokedexEntry e)
    {
    	if(e == null)
    		e = pokedex.getEntry(0);
    	selectedEntry = e;
    	setSelectedEntity(selectedEntry.getEntity(true));
    }
    
    public void setSelectedEntity(EntityLiving entity)
    {
    	
    	if(entity == null)
    		selectedEntity = pokedex.getEntry(0).getEntity(true);
    	else
    		selectedEntity = entity;
    	
    }
    
    //int count=0;
    protected void drawModelToScreen(float size, int xPos, int yPos)
    {
    	mod_Pixelmon.drawModelToScreen(size, xSize, ySize, xPos, yPos, selectedEntity, this, true);
    }
    

    
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) 
	{
		
	}
	
    protected void drawGuiContainerForegroundLayer()
    {
      
    }
    
    @SuppressWarnings("rawtypes")
	static List getSize(GuiPokedex par0GuiPokedex)
    {
        return par0GuiPokedex.pokedexList;
    }

	public static int getSelectedEntry(GuiPokedex guiPokedex) {
		return guiPokedex.selectedEntryIndex;
	}

	public static void setSelectedEntry(GuiPokedex guiPokedex, int value) {
		guiPokedex.selectedEntryIndex = value;
	}

	public static int onElementSelected(GuiPokedex guiPokedex, int par1) {
	    guiPokedex.setSelectedEntry(guiPokedex.pokedex.getEntry(par1+1));
	    return getSelectedEntry(guiPokedex);
	}

}
