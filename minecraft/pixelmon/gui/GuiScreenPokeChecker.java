package pixelmon.gui;

import pixelmon.attacks.Attack;
import pixelmon.entities.BaseEntityPixelmon;
import pixelmon.entities.PixelmonEntityHelper;
import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.StatCollector;


public class GuiScreenPokeChecker extends GuiScreen
{
	protected PixelmonEntityHelper target;
	
	public GuiScreenPokeChecker(PixelmonEntityHelper pixelmonEntityHelper)
	{
		target = pixelmonEntityHelper;
	}
	
	public boolean doesGuiPauseGame()
	{
		return true;
	}
	
    @SuppressWarnings("unchecked")
	public void initGui()
    {
        controlList.clear();
        controlList.add(new GuiButton(0, width / 2 - 100, (int) (height * 0.8) , StatCollector.translateToLocal("menu.returnToGame")));
        String s = !target.getDisplayName().equals(target.getNickName())?"Give Nickname":"Change Nickname";
        controlList.add(new GuiButton(1, width / 2 - 100, (int) (height * 0.8 - 25), s));
       
    }
    
    public void actionPerformed(GuiButton button)
    {
    	switch(button.id)
    	{
    		case 0:
    			mc.displayGuiScreen(null);
    			mc.setIngameFocus();
    			break;
    		case 1:
    			mc.displayGuiScreen(new GuiRenamePokemon(target, this));
    	}
    
    }
    
    public void drawScreen(int i, int i1, float f)
    {
    	drawDefaultBackground();
        //drawCenteredString(fontRenderer, "PokeChecker", width / 2, 40, 0xffffff);
        //drawCenteredString(fontRenderer, "Lv: " + target.getLevel() + target.getName(), width / 2, 35, 0xcccccc);
    	drawCenteredString(fontRenderer, "PokeChecker", width / 2, height / 7, 0xffffff);
    	drawCenteredString(fontRenderer, "Lv: " + target.getLvl().getLevel() + " " + target.getDisplayName() + " (" + target.getName() + ")", width / 2, height / 7 + 15, 0xcccccc);
    	this.drawHorizontalLine(width / 5, height / 7 + 20, width * 4 / 5, 0xffffff);
    	//STATS
    	drawCenteredString(fontRenderer, "Stats", width / 3, height / 7 + 25, 0xdddddd);
    	String s = (target.getType().size() == 1?"Type:":"Types:");
    	drawCenteredString(fontRenderer, s, width / 3, height / 7 + 40, 0xdddddd);
    	if(target.getType().size() == 1)
    	{
    		drawCenteredString(fontRenderer, target.getType().get(0).getName(), width / 3, height / 7 + 50, target.getType().get(0).getColor());
    	}
    	if(target.getType().size() == 2)
    	{
    		int swidth = fontRenderer.splitStringWidth(target.getType().get(0).getName(), 10);
    		drawCenteredString(fontRenderer, target.getType().get(0).getName(), width / 3 - (swidth / 2), height / 7 + 50, target.getType().get(0).getColor());
    		drawString(fontRenderer, target.getType().get(1).getName(), width / 3 + 3, height / 7 + 50, target.getType().get(1).getColor());
    	}
    	drawCenteredString(fontRenderer, "Health: " + target.getHealth() + "/" + target.getMaxHealth(), width / 3, height / 7 + 60, 0xdddddd);
    	drawCenteredString(fontRenderer, "Exp: " + target.getLvl().getExp() + "/" + target.getLvl().getExpToNextLevel(), width / 3, height / 7 + 70, 0xdddddd);
    	drawCenteredString(fontRenderer, "Attack: " + target.getStats().Attack, width / 3, height / 7 + 80, 0xdddddd);
    	drawCenteredString(fontRenderer, "Defence: " + target.getStats().Defence, width / 3, height / 7 + 90, 0xdddddd);
    	drawCenteredString(fontRenderer, "Special Attack: " + target.getStats().SpecialAttack, width / 3, height / 7 + 100, 0xdddddd);
    	drawCenteredString(fontRenderer, "Special Defence: " + target.getStats().SpecialDefence, width / 3, height / 7 + 110, 0xdddddd);
    	//MOVES
    	drawCenteredString(fontRenderer, "Moves", width * 2 / 3, height / 7 + 25, 0xdddddd);
    	for(int i2 = 0; i2 < target.getMoveset().size(); i2++)
    	{
    		drawCenteredString(fontRenderer, (((Attack)target.getMoveset().get(i2))).attackName, width * 2 / 3, height / 7 + 40 + (i2 * 10), ((Attack)target.getMoveset().get(i2)).attackType.getColor());
    	}
        super.drawScreen(i, i1, f);
    }
}