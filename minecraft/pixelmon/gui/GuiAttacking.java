package pixelmon.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.ModLoader;
import net.minecraft.src.RenderHelper;
import net.minecraft.src.RenderManager;
import net.minecraft.src.Tessellator;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import pixelmon.attacks.BattleController;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PacketCreator;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.entities.pixelmon.BaseEntityPixelmon;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;
import pixelmon.storage.PixelmonServerStore;

public class GuiAttacking extends GuiScreen {

	private PixelmonEntityHelper mypixelmon;
	private PixelmonEntityHelper target;
	private BattleController bc;

	public GuiAttacking(BattleController bc, PixelmonEntityHelper pixelmon1, PixelmonEntityHelper pixelmon2) {
		mypixelmon = pixelmon1;
		this.target = pixelmon2;
		this.bc = bc;
	}

	private PixelmonDataPacket userPacket, targetPacket;
	private int battleControllerIndex;

	public GuiAttacking(int pokemon1Index, int pokemon2Index, int battleControllerIndex) {
		userPacket = PixelmonServerStore.getPixelmonData(pokemon1Index);
		targetPacket = PixelmonServerStore.getPixelmonData(pokemon2Index);
		this.battleControllerIndex = battleControllerIndex;
	}

	@SuppressWarnings("unchecked")
	public void initGui() {
		Keyboard.enableRepeatEvents(true);
		controlList.clear();
		fontRenderer.FONT_HEIGHT = 10;
		if (ModLoader.getMinecraftInstance().theWorld.isRemote) {
			controlList.add(new GuiButton(0, width / 3 - width / 3, height * 3 / 4, width / 3 - 10, 20, userPacket.moveset[0].attackName + "(" + userPacket.moveset[0].pp + "/"
					+ userPacket.moveset[0].ppBase + ")"));
			if (userPacket.numMoves > 1)
				controlList.add(new GuiButton(1, width * 2 / 3 - width / 3, height * 3 / 4, width / 3 - 10, 20, userPacket.moveset[1].attackName + "(" + userPacket.moveset[1].pp + "/"
						+ userPacket.moveset[1].ppBase + ")"));
			if (userPacket.numMoves > 2)
				controlList.add(new GuiButton(2, width / 3 - width / 3, height * 3 / 4 + 30, width / 3 - 10, 20, userPacket.moveset[2].attackName + "(" + userPacket.moveset[2].pp + "/"
						+ userPacket.moveset[2].ppBase + ")"));
			if (userPacket.numMoves > 3)
				controlList.add(new GuiButton(3, width * 2 / 3 - width / 3, height * 3 / 4 + 30, width / 3 - 10, 20, userPacket.moveset[3].attackName + "(" + userPacket.moveset[3].pp + "/"
						+ userPacket.moveset[3].ppBase + ")"));
		} else {
			controlList.add(new GuiButton(0, width / 3 - width / 3, height * 3 / 4, width / 3 - 10, 20, mypixelmon.getMoveset().get(0).attackName + "(" + mypixelmon.getMoveset().get(0).pp + "/"
					+ mypixelmon.getMoveset().get(0).ppBase + ")"));
			if (mypixelmon.getMoveset().size() > 1)
				controlList.add(new GuiButton(1, width * 2 / 3 - width / 3, height * 3 / 4, width / 3 - 10, 20, mypixelmon.getMoveset().get(1).attackName + "(" + mypixelmon.getMoveset().get(1).pp
						+ "/" + mypixelmon.getMoveset().get(1).ppBase + ")"));
			if (mypixelmon.getMoveset().size() > 2)
				controlList.add(new GuiButton(2, width / 3 - width / 3, height * 3 / 4 + 30, width / 3 - 10, 20, mypixelmon.getMoveset().get(2).attackName + "(" + mypixelmon.getMoveset().get(2).pp
						+ "/" + mypixelmon.getMoveset().get(2).ppBase + ")"));
			if (mypixelmon.getMoveset().size() > 3)
				controlList.add(new GuiButton(3, width * 2 / 3 - width / 3, height * 3 / 4 + 30, width / 3 - 10, 20, mypixelmon.getMoveset().get(3).attackName + "("
						+ mypixelmon.getMoveset().get(3).pp + "/" + mypixelmon.getMoveset().get(3).ppBase + ")"));
		}
		controlList.add(new GuiButton(10, width * 3 / 4, height * 3 / 4, width / 4 - 5, 20, "Run"));
		controlList.add(new GuiButton(11, width * 3 / 4, height * 3 / 4 + 30, width / 4 - 5, 20, "Switch"));
	}

	public void updateScreen() {

	}

	public void keyTyped(char i, int i1) {
	}

	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

	protected void actionPerformed(GuiButton par1GuiButton) {
		if (ModLoader.getMinecraftInstance().theWorld.isRemote) {
			if (par1GuiButton.id < 4) {
				ModLoader.sendPacket(PacketCreator.createPacket(EnumPackets.ChooseAttack, par1GuiButton.id, battleControllerIndex, userPacket.pokemonID));
				mc.displayGuiScreen(null);
				mc.setIngameFocus();
			} else if (par1GuiButton.id == 11) {
				mc.displayGuiScreen(new GuiChoosePokemon(userPacket, battleControllerIndex, this));
			} else {
				ModLoader.sendPacket(PacketCreator.createPacket(EnumPackets.Flee, 0));
				mc.displayGuiScreen(null);
				mc.setIngameFocus();
			}
		} else {
			if (par1GuiButton.id < 4) {
				if (mypixelmon.getMoveset().get(0).pp > 0) {
					bc.setAttack(mypixelmon, mypixelmon.getMoveset().get(par1GuiButton.id));
					mc.displayGuiScreen(null);
					mc.setIngameFocus();
				}
			} else if (par1GuiButton.id == 11) {
				mc.displayGuiScreen(new GuiChoosePokemon(bc, mypixelmon, this));
			} else if (par1GuiButton.id == 10) {
				bc.setFlee(mypixelmon);
				mc.displayGuiScreen(null);
				mc.setIngameFocus();
			}
		}
	}

	protected void mouseClicked(int par1, int par2, int par3) {
		super.mouseClicked(par1, par2, par3);
	}

	public void drawScreen(int i, int i1, float f) {

		drawDefaultBackground();
		super.drawScreen(i, i1, f);
		if (ModLoader.getMinecraftInstance().theWorld.isRemote) {
			drawCenteredString(fontRenderer, "Which move do you want your " + userPacket.nickname + " to use against the " + ("wild ") + targetPacket.nickname + "?", width / 2, 10, 0xFFFFFF);
			drawPokemonStats(userPacket, width / 8, height * 2 / 6, true);
			drawPokemonStats(targetPacket, width * 5 / 8, height * 2 / 6, false);
			drawHealthBar(userPacket.health, userPacket.hp, width / 8, height * 2 / 6 + 10, 0, 0, 0);
			drawHealthBar(targetPacket.health, targetPacket.hp, width * 5 / 8, height * 2 / 6 + 10, 0, 0, 0);
		} else {
			drawCenteredString(fontRenderer, "Which move do you want your " + mypixelmon.getName() + " to use against the " + ("wild ") + target.getName() + "?", width / 2, 10, 0xFFFFFF);
			drawPokemonStats(mypixelmon, width / 8, height * 2 / 6, true);
			drawPokemonStats(target, width * 5 / 8, height * 2 / 6, false);
			drawHealthBar(mypixelmon.getHealth(), mypixelmon.getStats().HP, width / 8, height * 2 / 6 + 10, 0, 0, 0);
			drawHealthBar(target.getHealth(), target.getStats().HP, width * 5 / 8, height * 2 / 6 + 10, 0, 0, 0);
		}
	}

	public void drawPokemonStats(PixelmonEntityHelper pixelmon, int x, int y, boolean isMine) {
		fontRenderer.FONT_HEIGHT = 10;
		drawString(fontRenderer, pixelmon.getName(), x, y, 0xDDDDDD);
		if (pixelmon.getIsMale())
			drawString(fontRenderer, "♂ Lv." + pixelmon.getLvl().getLevel(), x + width / 4, y, 0xDDDDDD);
		else
			drawString(fontRenderer, "♀ Lv." + pixelmon.getLvl().getLevel(), x + width / 4, y, 0xDDDDDD);
		drawString(fontRenderer, pixelmon.getHealth() + "/" + pixelmon.getMaxHealth(), x + 50, y + 15, 0xDDDDDD);

	}

	public void drawPokemonStats(PixelmonDataPacket pixelmon, int x, int y, boolean isMine) {
		fontRenderer.FONT_HEIGHT = 10;
		drawString(fontRenderer, pixelmon.nickname, x, y, 0xDDDDDD);
		if (pixelmon.isMale)
			drawString(fontRenderer, "♂ Lv." + pixelmon.lvl, x + width / 4, y, 0xDDDDDD);
		else
			drawString(fontRenderer, "♀ Lv." + pixelmon.lvl, x + width / 4, y, 0xDDDDDD);
		drawString(fontRenderer, pixelmon.health + "/" + pixelmon.hp, x + 50, y + 15, 0xDDDDDD);

	}

	public void drawHealthBar(int health, int hp, double d, double d1, double d2, float f, float f1) {
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		GL11.glPushMatrix();
		GL11.glTranslatef((float) (d) + 120, (float) (d1) + 1, 50.0F);
		// float var7 = 30.0F;
		// int j = (width - xSize) / 2;
		// int k = (height - ySize) / 2;
		GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
		float var12 = (float) (d1 + 75 - 50) - 20;
		GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
		RenderHelper.enableStandardItemLighting();
		GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-((float) Math.atan((double) (var12 / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(-20, 1F, 0F, 0F);
		// RenderManager.instance.playerViewY = 180.0F;
		RenderHelper.disableStandardItemLighting();
		Tessellator tessellator = Tessellator.instance;
		GL11.glDisable(3553 /* GL_TEXTURE_2D */);
		tessellator.startDrawingQuads();
		double f5 = health;
		double f6 = hp;
		double f8 = 100 * (f5 / f6);
		tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F);
		tessellator.addVertex(-30, -4, 0.0);
		tessellator.addVertex(-20, 4, 0.0);
		tessellator.addVertex(130, 4, 0.0);
		tessellator.addVertex(140, -4, 0.0);
		tessellator.setColorRGBA_F(0.0F, 0.0F, 0.0F, 1.0F);
		tessellator.addVertex(0, -2.2, 0.0);
		tessellator.addVertex(0, 2.2, 0.0);
		tessellator.addVertex(100, 2.2, 0.0);
		tessellator.addVertex(100, -2.2, 0.0);
		tessellator.setColorRGBA_F(1.0F, 0.0F, 0.0F, 1.0F);
		tessellator.addVertex(0, -1.6, 0.0);
		tessellator.addVertex(0, 1.6, 0.0);
		tessellator.addVertex(100, 1.6, 0.0);
		tessellator.addVertex(100, -1.6, 0.0);
		tessellator.setColorRGBA_F(0.0F, 1.0F, 0.0F, 1.0F);
		tessellator.addVertex(100 - f8, -1.6, 0.0);
		tessellator.addVertex(100 - 1 * f8, 1.6, 0.0);
		tessellator.addVertex(100, 1.6, 0.0);
		tessellator.addVertex(100, -1.6, 0.0);
		tessellator.draw();
		GL11.glPopMatrix();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glDisable(GL11.GL_COLOR_MATERIAL);
	}

}
