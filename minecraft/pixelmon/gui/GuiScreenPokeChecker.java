package pixelmon.gui;

import pixelmon.attacks.Attack;
import pixelmon.attacks.Type;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.entities.BaseEntityPixelmon;
import pixelmon.entities.PixelmonEntityHelper;
import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.ModLoader;
import net.minecraft.src.StatCollector;

public class GuiScreenPokeChecker extends GuiScreen {
	protected PixelmonEntityHelper target;
	protected PixelmonDataPacket targetPacket;

	public GuiScreenPokeChecker(PixelmonEntityHelper pixelmonEntityHelper) {
		target = pixelmonEntityHelper;
	}

	public GuiScreenPokeChecker(PixelmonDataPacket pixelmonDataPacket) {
		targetPacket = pixelmonDataPacket;
	}

	public boolean doesGuiPauseGame() {
		return true;
	}

	@SuppressWarnings("unchecked")
	public void initGui() {
		controlList.clear();
		controlList.add(new GuiButton(0, width / 2 - 100, (int) (height * 0.8), StatCollector.translateToLocal("menu.returnToGame")));
		String s = "";
		if (ModLoader.getMinecraftInstance().theWorld.isRemote)
			s = !targetPacket.name.equals(targetPacket.nickname) ? "Give Nickname" : "Change Nickname";
		else
			s = !target.getDisplayName().equals(target.getNickName()) ? "Give Nickname" : "Change Nickname";
		controlList.add(new GuiButton(1, width / 2 - 100, (int) (height * 0.8 - 25), s));
	}

	public void actionPerformed(GuiButton button) {
		switch (button.id) {
		case 0:
			mc.displayGuiScreen(null);
			mc.setIngameFocus();
			break;
		case 1:
			if (ModLoader.getMinecraftInstance().theWorld.isRemote)
				mc.displayGuiScreen(new GuiRenamePokemon(targetPacket, this));
			else
				mc.displayGuiScreen(new GuiRenamePokemon(target, this));
		}

	}

	public void drawScreen(int i, int i1, float f) {
		drawDefaultBackground();
		// drawCenteredString(fontRenderer, "PokeChecker", width / 2, 40,
		// 0xffffff);
		// drawCenteredString(fontRenderer, "Lv: " + target.getLevel() +
		// target.getName(), width / 2, 35, 0xcccccc);
		drawCenteredString(fontRenderer, "PokeChecker", width / 2, height / 7, 0xffffff);
		if (ModLoader.getMinecraftInstance().theWorld.isRemote) {
			drawCenteredString(fontRenderer, "Lv: " + targetPacket.lvl + " " + targetPacket.nickname + " (" + targetPacket.name + ")", width / 2, height / 7 + 15, 0xcccccc);
			this.drawHorizontalLine(width / 5, height / 7 + 20, width * 4 / 5, 0xffffff);
			// STATS
			drawCenteredString(fontRenderer, "Stats", width / 3, height / 7 + 25, 0xdddddd);
			String s = (targetPacket.type2 == Type.Mystery ? "Type:" : "Types:");
			drawCenteredString(fontRenderer, s, width / 3, height / 7 + 40, 0xdddddd);
			if (targetPacket.type2 == Type.Mystery) {
				drawCenteredString(fontRenderer, targetPacket.type1.getName(), width / 3, height / 7 + 50, targetPacket.type1.getColor());
			}
			else{
				int swidth = fontRenderer.splitStringWidth(targetPacket.type1.getName(), 10);
				drawCenteredString(fontRenderer, targetPacket.type1.getName(), width / 3 - (swidth / 2), height / 7 + 50, targetPacket.type1.getColor());
				drawString(fontRenderer, targetPacket.type2.getName(), width / 3 + 3, height / 7 + 50, targetPacket.type2.getColor());
			}
			drawCenteredString(fontRenderer, "Health: " + targetPacket.health + "/" + targetPacket.hp, width / 3, height / 7 + 60, 0xdddddd);
			drawCenteredString(fontRenderer, "Exp: " + targetPacket.lvl + "/" + targetPacket.nextLvlXP, width / 3, height / 7 + 70, 0xdddddd);
			drawCenteredString(fontRenderer, "Attack: " + targetPacket.Attack, width / 3, height / 7 + 80, 0xdddddd);
			drawCenteredString(fontRenderer, "Defence: " + targetPacket.Defence, width / 3, height / 7 + 90, 0xdddddd);
			drawCenteredString(fontRenderer, "Special Attack: " + targetPacket.SpecialAttack, width / 3, height / 7 + 100, 0xdddddd);
			drawCenteredString(fontRenderer, "Special Defence: " + targetPacket.SpecialDefence, width / 3, height / 7 + 110, 0xdddddd);
			// MOVES
			drawCenteredString(fontRenderer, "Moves", width * 2 / 3, height / 7 + 25, 0xdddddd);
			for (int i2 = 0; i2 < targetPacket.numMoves; i2++) {
				drawCenteredString(fontRenderer, (targetPacket.moveset[i2]).attackName, width * 2 / 3, height / 7 + 40 + (i2 * 10),
						targetPacket.moveset[i2].type.getColor());
			}
		} else {
			drawCenteredString(fontRenderer, "Lv: " + target.getLvl().getLevel() + " " + target.getDisplayName() + " (" + target.getName() + ")", width / 2, height / 7 + 15, 0xcccccc);
			this.drawHorizontalLine(width / 5, height / 7 + 20, width * 4 / 5, 0xffffff);
			// STATS
			drawCenteredString(fontRenderer, "Stats", width / 3, height / 7 + 25, 0xdddddd);
			String s = (target.getType().size() == 1 ? "Type:" : "Types:");
			drawCenteredString(fontRenderer, s, width / 3, height / 7 + 40, 0xdddddd);
			if (target.getType().size() == 1) {
				drawCenteredString(fontRenderer, target.getType().get(0).getName(), width / 3, height / 7 + 50, target.getType().get(0).getColor());
			}
			if (target.getType().size() == 2) {
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
			// MOVES
			drawCenteredString(fontRenderer, "Moves", width * 2 / 3, height / 7 + 25, 0xdddddd);
			for (int i2 = 0; i2 < target.getMoveset().size(); i2++) {
				drawCenteredString(fontRenderer, (((Attack) target.getMoveset().get(i2))).attackName, width * 2 / 3, height / 7 + 40 + (i2 * 10),
						((Attack) target.getMoveset().get(i2)).attackType.getColor());
			}
		}
		super.drawScreen(i, i1, f);
	}
}