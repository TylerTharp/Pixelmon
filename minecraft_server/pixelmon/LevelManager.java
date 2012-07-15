package pixelmon;

import java.util.ArrayList;

import pixelmon.attacks.Attack;
import pixelmon.database.DatabaseMoves;
import pixelmon.database.ExperienceGroup;
import pixelmon.entities.BaseEntityPixelmon;
import pixelmon.entities.EntityWaterPixelmon;
import pixelmon.entities.PixelmonEntityHelper;
import pixelmon.gui.EnumGui;

import cpw.mods.fml.common.registry.FMLRegistry;

import net.minecraft.src.Entity;
import net.minecraft.src.ModLoader;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.mod_Pixelmon;

public class LevelManager {

	private PixelmonEntityHelper pixelmon;
	private int exp;
	private int expToNextLevel;
	private int baseLevel = 0;
	private int level;
	private int maxHealth = 10;

	public LevelManager(PixelmonEntityHelper p) {
		this.pixelmon = p;
		exp = 0;
		setScale();
	}

	protected void updateStats() {
		pixelmon.getStats().setLevelStats(level);
		maxHealth = pixelmon.getStats().HP;
	}

	public void writeToNBT(NBTTagCompound var1) {
		var1.setInteger("Level", level);
		var1.setInteger("EXP", exp);
	}

	public void readFromNBT(NBTTagCompound var1) {
		level = var1.getInteger("Level");
		exp = var1.getInteger("EXP");
		expToNextLevel = getExpForLevel(level + 1) - getExpForLevel(level);
		updateEntityString();
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int i) {
		level = i;
		setScale();
		expToNextLevel = getExpForLevel(level + 1) - getExpForLevel(level);
		exp = 0;
		float oldHp = pixelmon.getStats().HP;
		updateStats();
		float percentGain = ((float) pixelmon.getStats().HP) / oldHp;
		float newHealth = ((float) pixelmon.getHealth()) * percentGain;
		pixelmon.setHealth((int) Math.ceil(newHealth));
		updateEntityString();
	}

	private int getExpForLevel(int level2) {
		double l = level2;
		if (pixelmon.getStats().BaseStats.ExperienceGroup == null)
			;// .getMinecraftInstance().ingameGUI.addChatMessage("Database error with "
				// + pixelmon.getName());
		if (pixelmon.getStats().BaseStats.ExperienceGroup == ExperienceGroup.Erratic) {
			if (l <= 50)
				return (int) (l * l * l * (100 - l)) / 50;
			if (l <= 68)
				return (int) (l * l * l * (150 - l)) / 100;
			if (l <= 98)
				return (int) (l * l * l * (1911 - 10 * l)) / 3;
			if (l <= 100)
				return (int) (l * l * l * (160 - l)) / 100;
		} else if (pixelmon.getStats().BaseStats.ExperienceGroup == ExperienceGroup.Fast) {
			return (int) (4 * l * l * l / 5);
		} else if (pixelmon.getStats().BaseStats.ExperienceGroup == ExperienceGroup.MediumFast) {
			return (int) (l * l * l);
		} else if (pixelmon.getStats().BaseStats.ExperienceGroup == ExperienceGroup.MediumSlow) {
			return (int) ((6 / 5) * l * l * l - 15 * l * l + 100 * l - 140);
		} else if (pixelmon.getStats().BaseStats.ExperienceGroup == ExperienceGroup.Slow) {
			return (int) (5 * l * l * l / 4);
		} else if (pixelmon.getStats().BaseStats.ExperienceGroup == ExperienceGroup.Fluctuating) {
			if (l <= 15)
				return (int) (l * l * l * ((l + 1) / 3 + 24) / 50);
			if (l <= 36)
				return (int) (l * l * l * (l + 14) / 50);
			if (l <= 100)
				return (int) (l * l * l * ((l / 2) + 32) / 50);
		}

		return -1;
	}

	public int getExp() {
		return exp;
	}

	public int getExpToNextLevel() {
		return expToNextLevel;
	}

	public boolean canLevelUp() {
		return level != 100;
	}

	protected void onLevelUp() {
		float oldHp = pixelmon.getStats().HP;
		updateStats();
		float percentGain = ((float) pixelmon.getStats().HP) / oldHp;
		float newHealth = ((float) pixelmon.getHealth()) * percentGain;
		pixelmon.setHealth((int) Math.ceil(newHealth));
		if (pixelmon.getOwner() != null)
			mod_Pixelmon.pokeballManager.getPlayerStorage(pixelmon.getOwner()).updateNBT(pixelmon);
		String name = "";
		if (mod_Pixelmon.pokeballManager.getPlayerStorage(pixelmon.getOwner()).contains(pixelmon.getPokemonId())) {
			// mc.ingameGUI.addChatMessage("Your " + pixelmon.getName() +
			// " leveled up to level " + level + "!");
			mod_Pixelmon.pokeballManager.getPlayerStorage(pixelmon.getOwner()).updateNBT(pixelmon);
		}
		name = pixelmon.getName();

		if (DatabaseMoves.LearnsAttackAtLevel(name, level)) {
			ArrayList<Attack> newAttacks = DatabaseMoves.getAttacksAtLevel(name, level);
			for (Attack a : newAttacks) {
				if (pixelmon.getMoveset().size() >= 4) {
					pixelmon.getOwner().openGui(mod_Pixelmon.instance, EnumGui.LearnMove.getIndex(), pixelmon.getOwner().worldObj, pixelmon.getPokemonId(),
							a.attackIndex, 0); // guiLearnMove
				} else {
					pixelmon.getMoveset().add(a);
					ChatHandler.sendChat(pixelmon.getOwner(), pixelmon.getName() + " just learnt " + a.attackName + "!");
				}
			}
		}
		setScale();
		updateEntityString();
	}

	public void awardEXP(int i) {
		exp += i;
		if ((pixelmon.getOwner() != null) && getLevel() != 100)
			if (pixelmon.getOwner() != null)
				ChatHandler.sendChat(pixelmon.getOwner(), "Your " + pixelmon.getName() + " gained " + i + " EXP!");
		if (!canLevelUp() || expToNextLevel == -1) {
			exp = 0;
			return;
		}
		while (exp >= expToNextLevel) {
			level++;
			onLevelUp();
			if (level >= pixelmon.getStats().BaseStats.EvolveLevel) {
				if (pixelmon.getIHaveHelper() instanceof BaseEntityPixelmon)
					((BaseEntityPixelmon) pixelmon.getIHaveHelper()).evolve();
				else if (pixelmon.getIHaveHelper() instanceof EntityWaterPixelmon)
					((EntityWaterPixelmon) pixelmon.getIHaveHelper()).evolve();
			}
			if (!canLevelUp())
				return;
			exp -= expToNextLevel;
			expToNextLevel = getExpForLevel(level + 1) - getExpForLevel(level);
		}
		updateEntityString();
	}

	public void setEXP(int i) {
		exp = i;
		updateEntityString();
	}

	private void setScale() {
		float percent = 1;
		percent = 0.8f + 0.4f * (level) / (100);
		if (percent > pixelmon.getMaxScale())
			percent = pixelmon.getMaxScale();
		pixelmon.increaseSize(percent);
	}

	public void updateEntityString() {
		String lvlString = "" + exp + ";" + expToNextLevel + ";" + level + ";" + (int)pixelmon.getHealth() + ";" + pixelmon.getStats().HP;
		pixelmon.updateLvlString(lvlString);
	}

	public void recalculateXP() {
		exp = 0;
		expToNextLevel = getExpForLevel(level + 1) - getExpForLevel(level);
	}
}
