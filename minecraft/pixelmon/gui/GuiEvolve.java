package pixelmon.gui;

import pixelmon.entities.IHaveHelper;
import pixelmon.entities.PixelmonEntityHelper;
import net.minecraft.src.*;

public class GuiEvolve extends GuiScreen
{
	
	public IHaveHelper beginPixelmon, finishPixelmon;
	private PixelmonEntityHelper b, f;
	private String text1, text2, cancel;
	
	public GuiEvolve(IHaveHelper start, IHaveHelper end)
	{
		beginPixelmon = start;
		finishPixelmon = end;
		b = new PixelmonEntityHelper(beginPixelmon);
		f = new PixelmonEntityHelper(finishPixelmon);
		text1 = "What? Your " + getName(b)
				+ " is evolving!";
		text2 = "Congratulations, your " + getName(b) + " evolved into " + f.getName() + "!";
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
}