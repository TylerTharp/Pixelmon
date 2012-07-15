package pixelmon.storage;

import java.awt.Rectangle;

import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.ModLoader;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.Tessellator;
import net.minecraft.src.mod_Pixelmon;

import org.lwjgl.opengl.GL11;

import pixelmon.comm.PixelmonDataPacket;
import pixelmon.entities.IHaveHelper;
import pixelmon.entities.PixelmonEntityList;
import pixelmon.gui.GuiScreenPokeCheckerPC;

public class GuiPC extends GuiScreen {

	private int boxNumber, trashX, trashY, checkX, checkY;
	private SlotPC mouseSlot;
	private SlotPCPC[][] pcSlots = new SlotPCPC[ComputerManager.boxCount][ComputerBox.boxLimit];
	private SlotPCParty[] partySlots = new SlotPCParty[6];

	public GuiPC() {
		super();
		boxNumber = 0;
	}

	public void initGui() {
		controlList.clear();
		controlList.add(new GuiButton(0, width / 2 - 131, height / 6 + 60, 20, 20, "<-"));
		controlList.add(new GuiButton(1, width / 2 - 91 + 205, height / 6 + 60, 20, 20, "->"));
		trashX = width / 2 - 91 + 202;
		trashY = height / 6 + 150;
		checkY = trashY;
		checkX = width / 2 - 140;
		if (ModLoader.getMinecraftInstance().theWorld.isRemote) {
			mouseSlot = new SlotPC(0, 0, (PixelmonDataPacket) null);
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < ComputerBox.boxLimit; j++) {
					int x = j % 6;
					int y = j / 6;
					x *= 30;
					y *= 28;
					x += width / 2 - 90;
					y += height / 6 - 5;
					pcSlots[i][j] = new SlotPCPC(x, y, i, j, null);
					PixelmonDataPacket p = PixelmonServerStore.store.get(j);
					if (p != null) {
						pcSlots[i][j].setPokemon(p);
					}
				}
			}
			for (int i = 0; i < 6; i++) {
				int x = i;
				x *= 30;
				x += width / 2 - 90;
				int y = height / 6 + 145;
				partySlots[i] = new SlotPCParty(x, y, i, null);
				PixelmonDataPacket p = mod_Pixelmon.serverStorageDisplay.pokemon[i];
				if (p != null) {
					partySlots[i].setPokemon(p);
				}
			}
		} else {
			mouseSlot = new SlotPC(0, 0, (NBTTagCompound) null);
			for (int i = 0; i < mod_Pixelmon.computerManager.getBoxList().length; i++) {
				for (int j = 0; j < mod_Pixelmon.computerManager.getBox(i).getStoredPokemon().length; j++) {
					int x = j % 6;
					int y = j / 6;
					x *= 30;
					y *= 28;
					x += width / 2 - 90;
					y += height / 6 - 5;
					pcSlots[i][j] = new SlotPCPC(x, y, i, j, null);
					NBTTagCompound n = mod_Pixelmon.computerManager.getBox(i).getStoredPokemon()[j];
					if (n != null) {
						pcSlots[i][j].setPokemon(n);
					}
				}
			}
			for (int i = 0; i < 6; i++) {
				int x = i;
				x *= 30;
				x += width / 2 - 90;
				int y = height / 6 + 145;
				partySlots[i] = new SlotPCParty(x, y, i, null);
				NBTTagCompound n = mod_Pixelmon.pokeballManager.getPlayerStorage(ModLoader.getMinecraftInstance().thePlayer).getList()[i];
				if (n != null) {
					partySlots[i].setPokemon(n);
				}
			}
		}
	}

	public SlotPC getSlotAt(int x, int y) {
		for (SlotPCPC slot : pcSlots[boxNumber]) {
			if (slot.getBounds().contains(x, y)) {
				return slot;
			}
		}
		for (SlotPCParty slot : partySlots) {
			if (slot.getBounds().contains(x, y)) {
				return slot;
			}
		}
		return null;
	}

	public boolean checkIfLast() {
		int i = 0;
		for (SlotPC slot : partySlots) {
			if (ModLoader.getMinecraftInstance().theWorld.isRemote) {
				if (slot.pokemonData != null)
					i++;
			} else {
				if (slot.pokemon != null)
					i++;
			}
		}
		if (i == 1) {
			return true;
		}
		return false;
	}

	protected void mouseClicked(int par1, int par2, int par3) {
		super.mouseClicked(par1, par2, par3);
		if (par3 == 0) {
			SlotPC slot = getSlotAt(par1, par2);
			if (slot != null) {
				NBTTagCompound temp = slot.pokemon;
				if ((slot instanceof SlotPCParty && mouseSlot.pokemon == null && checkIfLast())) {
					return;
				} else {
					slot.setPokemon((NBTTagCompound) null);
				}
				if (mouseSlot.pokemon != null) {
					slot.setPokemon(mouseSlot.pokemon);
					mouseSlot.pokemon = null;
				}
				if (temp != null) {
					mouseSlot.pokemon = temp;
				}
			} else if (new Rectangle(trashX, trashY, 32, 32).contains(par1, par2)) {
				mouseSlot.clearPokemon();
			} else if (new Rectangle(checkX, checkY, 32, 32).contains(par1, par2)) {
				if (mouseSlot.pokemon != null) {
					for (int i = 0; i < mod_Pixelmon.computerManager.getBoxList().length; i++) {
						ComputerBox c = mod_Pixelmon.computerManager.getBoxList()[i];
						if (c.hasSpace()) {
							int j = c.getNextSpace();
							System.out.println(j);
							IHaveHelper e = (IHaveHelper) PixelmonEntityList.createEntityFromNBT(mouseSlot.pokemon, ModLoader.getMinecraftInstance().theWorld);
							mc.displayGuiScreen(new GuiScreenPokeCheckerPC(e.getHelper(), this, i, j));
							break;
						}
					}
				}
			} else {
				return;
			}
			for (int i = 0; i < pcSlots.length; i++) {
				NBTTagCompound[] newPokemon = new NBTTagCompound[pcSlots[i].length];
				for (int j = 0; j < pcSlots[i].length; j++) {
					NBTTagCompound n = pcSlots[i][j].pokemon;
					if (n != null) {
						n.setInteger("StoragePosition", j);
						newPokemon[j] = n;
					}
				}
				mod_Pixelmon.computerManager.getBox(i).setStoredPokemon(newPokemon);
				mod_Pixelmon.computerManager.getBox(i).hasChanged = true;
			}
			NBTTagCompound[] newPokemon = new NBTTagCompound[partySlots.length];
			for (int i = 0; i < partySlots.length; i++) {
				NBTTagCompound n = partySlots[i].pokemon;
				if (n != null) {
					n.setInteger("PixelmonOrder", i);
					newPokemon[i] = n;
				}
			}
			mod_Pixelmon.pokeballManager.getPlayerStorage(ModLoader.getMinecraftInstance().thePlayer).setPokemon(newPokemon);
			mod_Pixelmon.pokeballManager.save();
			if (mouseSlot.pokemon != null) {
				NBTTagCompound n = mouseSlot.pokemon;
				for (ComputerBox c : mod_Pixelmon.computerManager.getBoxList()) {
					if (c.hasSpace()) {
						n.setInteger("StoragePosition", c.getNextSpace());
						c.getStoredPokemon()[c.getNextSpace()] = n;
						c.hasChanged = true;
						break;
					}
				}
			}
			mod_Pixelmon.computerManager.save();
		}
	}

	protected void mouseMovedOrUp(int par1, int par2, int par3) {
		super.mouseMovedOrUp(par1, par2, par3);
		mouseSlot.setXandY(par1 - 15, par2 - 15);
	}

	public void drawScreen(int var1, int var2, float var3) {
		drawDefaultBackground();
		int w = width;
		int h = height;
		int partyTexture = ModLoader.getMinecraftInstance().renderEngine.getTexture("/pixelmon/gui/pcPartyBox.png");
		ModLoader.getMinecraftInstance().renderEngine.bindTexture(partyTexture);
		drawTexturedModalRect(width / 2 - 91, height / 6 + 151, 0, 0, 182, 29);
		int i = 0;
		partyTexture = ModLoader.getMinecraftInstance().renderEngine.getTexture("/pixelmon/gui/pcBox.png");
		ModLoader.getMinecraftInstance().renderEngine.bindTexture(partyTexture);
		drawTexturedModalRect(width / 2 - 91, height / 6, 0, 0, 182, 141);
		drawTexturedModalRect(trashX, trashY, 0, 256 - 32, 32, 32);
		drawImageQuad(ModLoader.getMinecraftInstance().renderEngine.getTexture("/pixelmon/image/pokechecker.png"), checkX, checkY, 32f, 32f, 0f, 0f, 1f, 1f);
		if (ModLoader.getMinecraftInstance().theWorld.isRemote) {
			for (int a = 0; a < pcSlots[boxNumber].length; a++) {
				SlotPCPC slot = pcSlots[boxNumber][a];
				if (slot.pokemonData == null) {
					continue;
				}
				int image = slot.getRenderInt();
				drawImageQuad(image, slot.x, slot.y, 30f, 30f, 0f, 0f, 1f, 1f);
			}
			for (int a = 0; a < partySlots.length; a++) {
				SlotPCParty slot = partySlots[a];
				if (slot.pokemonData == null) {
					continue;
				}
				int image = slot.getRenderInt();
				drawImageQuad(image, slot.x, slot.y, 30f, 30f, 0f, 0f, 1f, 1f);
			}
			if (mouseSlot.pokemonData != null) {
				int image = mouseSlot.getRenderInt();
				drawImageQuad(image, mouseSlot.x, mouseSlot.y, 30f, 30f, 0f, 0f, 1f, 1f);
				PixelmonDataPacket p = mouseSlot.pokemonData;
				if (p.nickname == null || p.nickname.equalsIgnoreCase("")) {
					p.nickname = p.name;
				}
				fontRenderer.drawString(p.nickname, mouseSlot.x + 30, mouseSlot.y + 10, 0xFFFFFF);
				partyTexture = ModLoader.getMinecraftInstance().renderEngine.getTexture("/pixelmon/gui/pixelmonOverlay.png");
				ModLoader.getMinecraftInstance().renderEngine.bindTexture(partyTexture);
				if (p.isMale)
					drawTexturedModalRect(fontRenderer.getStringWidth(p.nickname) + mouseSlot.x + 32, mouseSlot.y + 10, 33, 208, 5, 9);
				else
					drawTexturedModalRect(fontRenderer.getStringWidth(p.nickname) + mouseSlot.x + 32, mouseSlot.y + 10, 33, 218, 5, 9);
				fontRenderer.drawString("Lvl " + p.lvl, mouseSlot.x + 30, mouseSlot.y + 20, 0xFFFFFF);
				if (p.isFainted) {
					fontRenderer.drawString("Fainted", mouseSlot.x + 35 + fontRenderer.getStringWidth("Lvl " + p.lvl), mouseSlot.y + 20, 0xFFFFFF);
				} else {
					fontRenderer.drawString("HP " + p.health + "/" + p.hp, mouseSlot.x + 35 + fontRenderer.getStringWidth("Lvl " + p.lvl), mouseSlot.y + 20,
							0xFFFFFF);
				}
			}
		} else {
			for (int a = 0; a < pcSlots[boxNumber].length; a++) {
				SlotPCPC slot = pcSlots[boxNumber][a];
				if (slot.pokemon == null) {
					continue;
				}
				int image = slot.getRenderInt();
				drawImageQuad(image, slot.x, slot.y, 30f, 30f, 0f, 0f, 1f, 1f);
			}
			for (int a = 0; a < partySlots.length; a++) {
				SlotPCParty slot = partySlots[a];
				if (slot.pokemon == null) {
					continue;
				}
				int image = slot.getRenderInt();
				drawImageQuad(image, slot.x, slot.y, 30f, 30f, 0f, 0f, 1f, 1f);
			}
			if (mouseSlot.pokemon != null) {
				int image = mouseSlot.getRenderInt();
				drawImageQuad(image, mouseSlot.x, mouseSlot.y, 30f, 30f, 0f, 0f, 1f, 1f);
				NBTTagCompound n = mouseSlot.pokemon;
				if (n.getString("Nickname") == null || n.getString("Nickname").equalsIgnoreCase("")) {
					n.setString("Nickname", n.getName());
				}
				fontRenderer.drawString(n.getString("Nickname"), mouseSlot.x + 30, mouseSlot.y + 10, 0xFFFFFF);
				partyTexture = ModLoader.getMinecraftInstance().renderEngine.getTexture("/pixelmon/gui/pixelmonOverlay.png");
				ModLoader.getMinecraftInstance().renderEngine.bindTexture(partyTexture);
				if (n.getBoolean("IsMale"))
					drawTexturedModalRect(fontRenderer.getStringWidth(n.getString("Nickname")) + mouseSlot.x + 32, mouseSlot.y + 10, 33, 208, 5, 9);
				else
					drawTexturedModalRect(fontRenderer.getStringWidth(n.getString("Nickname")) + mouseSlot.x + 32, mouseSlot.y + 10, 33, 218, 5, 9);
				fontRenderer.drawString("Lvl " + n.getInteger("Level"), mouseSlot.x + 30, mouseSlot.y + 20, 0xFFFFFF);
				if (n.getBoolean("IsFainted")) {
					fontRenderer.drawString("Fainted", mouseSlot.x + 35 + fontRenderer.getStringWidth("Lvl " + n.getInteger("Level")), mouseSlot.y + 20,
							0xFFFFFF);
				} else {
					fontRenderer.drawString("HP " + n.getShort("Health") + "/" + n.getInteger("StatsHP"),
							mouseSlot.x + 35 + fontRenderer.getStringWidth("Lvl " + n.getInteger("Level")), mouseSlot.y + 20, 0xFFFFFF);
				}
			}
		}
		fontRenderer.drawString("Box: " + (boxNumber + 1), width / 2 - 18, height / 6 - 20, 0xffffff);
		super.drawScreen(var1, var2, var3);

	}

	public void onGuiClosed() {
		super.onGuiClosed();
		for (int i = 0; i < pcSlots.length; i++) {
			NBTTagCompound[] newPokemon = new NBTTagCompound[pcSlots[i].length];
			for (int j = 0; j < pcSlots[i].length; j++) {
				NBTTagCompound n = pcSlots[i][j].pokemon;
				if (n != null) {
					n.setInteger("StoragePosition", j);
					newPokemon[j] = n;
				}
			}
			mod_Pixelmon.computerManager.getBox(i).setStoredPokemon(newPokemon);
			mod_Pixelmon.computerManager.getBox(i).hasChanged = true;
		}
		NBTTagCompound[] newPokemon = new NBTTagCompound[partySlots.length];
		for (int i = 0; i < partySlots.length; i++) {
			NBTTagCompound n = partySlots[i].pokemon;
			if (n != null) {
				n.setInteger("PixelmonOrder", i);
				newPokemon[i] = n;
			}
		}
		mod_Pixelmon.pokeballManager.getPlayerStorage(ModLoader.getMinecraftInstance().thePlayer).setPokemon(newPokemon);
		if (mouseSlot.pokemon != null) {
			NBTTagCompound n = mouseSlot.pokemon;
			for (ComputerBox c : mod_Pixelmon.computerManager.getBoxList()) {
				if (c.hasSpace()) {
					n.setInteger("StoragePosition", c.getNextSpace());
					c.getStoredPokemon()[c.getNextSpace()] = n;
					c.hasChanged = true;
					break;
				}
			}
		}
		mod_Pixelmon.computerManager.save();
	}

	public void actionPerformed(GuiButton button) {
		int b = button.id;
		if (b == 0) {
			if (boxNumber == 0) {
				boxNumber = 7;
			} else {
				boxNumber--;
			}
		}
		if (b == 1) {
			if (boxNumber == 7) {
				boxNumber = 0;
			} else {
				boxNumber++;
			}
		}
	}

	public boolean doesGuiPauseGame() {
		return false;
	}

	public void switchBackFromGui(int box, int i) {
		mouseSlot.pokemon = pcSlots[box][i].pokemon;
		pcSlots[box][i].clearPokemon();
	}

	private void drawImageQuad(int textureHandle, int x, int y, float w, float h, float us, float vs, float ue, float ve) {
		// activate the specified texture
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureHandle);

		float var7 = 0.00390625F;
		float var8 = 0.00390625F;
		Tessellator var9 = Tessellator.instance;
		var9.startDrawingQuads();
		var9.addVertexWithUV((double) (x + 0), (double) (y + h), (double) this.zLevel, (double) ((float) us), (double) ((float) ve));
		var9.addVertexWithUV((double) (x + w), (double) (y + h), (double) this.zLevel, (double) ((float) ue), (double) ((float) ve));
		var9.addVertexWithUV((double) (x + w), (double) (y + 0), (double) this.zLevel, (double) ((float) ue), (double) ((float) vs));
		var9.addVertexWithUV((double) (x + 0), (double) (y + 0), (double) this.zLevel, (double) ((float) us), (double) ((float) vs));
		var9.draw();
	}

}
