package pixelmon.gui;

import pixelmon.attacks.Attack;
import pixelmon.entities.BaseEntityPixelmon;
import pixelmon.entities.EntityWaterPixelmon;
import pixelmon.entities.PixelmonEntityHelper;
import net.minecraft.src.*;

public class GuiLearnMove extends GuiScreen
{
	
	private PixelmonEntityHelper user;
	private Attack moveToLearn;
	
	public GuiLearnMove(PixelmonEntityHelper helper, Attack a)
	{
		user = helper;
		moveToLearn = a;
	}

	@SuppressWarnings("unchecked")
	public void initGui()
	{
		controlList.clear();
		for (int i =0; i < user.getMoveset().size(); i++){
			controlList.add(new GuiButton(i + 1, width / 2 - 100, height / 4 + i*24 + 20 + 12,
					user.getMoveset().get(i).attackName));
		}
		controlList.add(new GuiButton(0, width / 2 - 100, height / 4 + 96 + 20 + 12, "Cancel"));
	}
	private void teachMove(int index, Attack a)
	{
		if(index == 0)
		{
			ModLoader.getMinecraftInstance().ingameGUI.addChatMessage("Decided not to teach " + user.getName() + " " + a.attackName + ".");
			return;
		}
		ModLoader.getMinecraftInstance().ingameGUI.addChatMessage("Your " + user.getName() + " forgot " + user.getMoveset().get(index - 1).attackName + ", and learned " + a.attackName);
		user.getMoveset().set(index - 1, a);
	}
	public void actionPerformed(GuiButton b)
	{
		teachMove(b.id, moveToLearn);
		mc.displayGuiScreen(null);
	}
	
	public void drawScreen(int par1, int par2, float par3)
	{
		drawDefaultBackground();
		super.drawScreen(par1, par2, par3);
		drawCenteredString(fontRenderer, "Your " + user.getName() + " wants to learn the move " + moveToLearn.attackName + ",", width / 2, 10, 0xFFFFFF);
		drawCenteredString(fontRenderer, "but " + user.getName() + " already knows four moves. Which move should be forgotten?", width / 2, 20, 0xFFFFFF);
	}
	
}