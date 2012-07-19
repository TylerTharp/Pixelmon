package pixelmon.gui;

import pixelmon.comm.EnumPackets;
import pixelmon.comm.PacketCreator;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;
import pixelmon.gui.pc.GuiPC;
import net.minecraft.src.GuiButton;
import net.minecraft.src.ModLoader;
import net.minecraft.src.StatCollector;

public class GuiScreenPokeCheckerPC extends GuiScreenPokeChecker {
	
	private GuiPC parent;
	private int index, box;

	public GuiScreenPokeCheckerPC(PixelmonEntityHelper pixelmonEntityHelper, GuiPC parent, int box, int index) {
		super(pixelmonEntityHelper);
		this.parent = parent;
		this.index = index;
		this.box = box;
	}
	
	public GuiScreenPokeCheckerPC(PixelmonDataPacket packet, GuiPC parent, int box, int index) {
		super(packet);
		this.parent = parent;
		this.index = index;
		this.box = box;
	}
	
	public void initGui()
    {
        controlList.clear();
        controlList.add(new GuiButton(0, width / 2 - 100, (int) (height * 0.8) ,"Back to PC"));
       
    }
	
	public void onGuiClosed(){
		super.onGuiClosed();
	}
	
	   public void actionPerformed(GuiButton button)
	    {
	    	switch(button.id)
	    	{
	    		case 0:
	    			if(ModLoader.getMinecraftInstance().theWorld.isRemote){
	    				ModLoader.sendPacket(PacketCreator.createPacket(EnumPackets.PCClick, -5));
	    			}
	    			else{
	    				mc.displayGuiScreen(parent);	
	    			}
	    			break;
	    	}
	    
	    }

}
