package pixelmon.gui;

import pixelmon.entities.PixelmonEntityHelper;
import pixelmon.storage.GuiPC;
import net.minecraft.src.GuiButton;
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
	
	public void initGui()
    {
        controlList.clear();
        controlList.add(new GuiButton(0, width / 2 - 100, (int) (height * 0.8) ,"Back to PC"));
       
    }
	
	   public void actionPerformed(GuiButton button)
	    {
	    	switch(button.id)
	    	{
	    		case 0:
	    			mc.displayGuiScreen(parent);
	    			parent.switchBackFromGui(box, index);
	    			break;
	    	}
	    
	    }

}
