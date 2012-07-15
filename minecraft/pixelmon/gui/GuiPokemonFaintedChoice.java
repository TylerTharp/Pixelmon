package pixelmon.gui;

import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiScreen;

import org.lwjgl.input.Keyboard;

import pixelmon.attacks.BattleController;
import pixelmon.entities.BaseEntityPixelmon;

public class GuiPokemonFaintedChoice extends GuiScreen {

	private BaseEntityPixelmon mypixelmon;
	private BattleController bc;

	public GuiPokemonFaintedChoice(BattleController bc, BaseEntityPixelmon entity) {
		mypixelmon = entity;
		this.bc = bc;
	}

	@SuppressWarnings("unchecked")
	public void initGui() {
		Keyboard.enableRepeatEvents(true);
		controlList.clear();
		controlList.add(new GuiButton(10, width / 2 - 100, height / 2 - 20,
				"Switch To Another Pokemon"));
		if (!bc.isTrainerVsTrainer())
			controlList.add(new GuiButton(10, width / 2 - 100, height / 2 + 20,
					"Run"));
	}

	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

	protected void actionPerformed(GuiButton par1GuiButton) {
		if (par1GuiButton.id == 0) {

		} else {

		}
		mc.displayGuiScreen(null);
		mc.setIngameFocus();
	}

	protected void mouseClicked(int par1, int par2, int par3) {
		super.mouseClicked(par1, par2, par3);
	}

	public void drawScreen(int i, int i1, float f) {

		drawDefaultBackground();
		super.drawScreen(i, i1, f);
		drawCenteredString(fontRenderer, mypixelmon.getName()
				+ "can no longer fight", width / 2, 10, 0xFFFFFF);
	}
}
