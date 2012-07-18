package pixelmon.gui;

import pixelmon.entities.pixelmon.helpers.IHaveHelper;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;
import net.minecraft.src.*;

public class GuiEvolve extends GuiScreen
{
	
	public IHaveHelper beginPixelmon, finishPixelmon;
	private PixelmonEntityHelper b, f;
	private String start, complete, cancel;
	
	public GuiEvolve(IHaveHelper start, IHaveHelper end)
	{
		beginPixelmon = start;
		finishPixelmon = end;
		b = beginPixelmon.getHelper();
		f = finishPixelmon.getHelper();
		this.start = "What? Your " + getName(b)
				+ " is evolving!";
		complete = "Congratulations, your " + getName(b) + " evolved into " + f.getName() + "!";
		cancel = "Huh? " + getName(b) + " stopped evolving!";
	}
	
	private String getName(PixelmonEntityHelper e)
	{
		return (MathHelper.stringNullOrLengthZero(b.getNickName())?b.getName():b.getNickName());
	}
	
	public void initGui()
	{
		controlList.clear();
	}
	
	public void updateScreen()
	{
		mod_Pixelmon.drawModelToScreen(1, 1, 1, mc.displayWidth / 2, mc.displayWidth / 2, b.getEntity(), this, true);
	}
}