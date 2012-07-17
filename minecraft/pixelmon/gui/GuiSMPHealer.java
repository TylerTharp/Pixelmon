package pixelmon.gui;

import pixelmon.attacks.Attack;
import pixelmon.attacks.Type;
import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.ModLoader;
import net.minecraft.src.StatCollector;

public class GuiSMPHealer extends GuiScreen {

	public ProgressBar[] progressBars;
	
	public GuiSMPHealer() {
		progressBars = new ProgressBar[6];
		for (int i=0; i < progressBars.length; i++)
			progressBars[i] = new ProgressBar(30, 300, 120, 60+i*40);
	}
	@SuppressWarnings("unchecked")
	public void initGui() {
		controlList.clear();
		controlList.add(new GuiButton(0, width *3/ 4 , (int) (height * 0.8), StatCollector.translateToLocal("Heal")));
	}
	
	@Override
	public void updateScreen(){
		for (int i=0; i < progressBars.length; i++){
			if (progressBars[i].value <100) {
				progressBars[i].value++;
				break;
			}
		}
	}
	
	public void drawScreen(int i, int i1, float f) {
		drawDefaultBackground();
		for (int j=0; j < progressBars.length; j++)
			progressBars[j].draw();
		if (ModLoader.getMinecraftInstance().theWorld.isRemote) {
			
		} else {
			
		}
		super.drawScreen(i, i1, f);
	}
}
