package pixelmon.entities.pixelmon.helpers;

import java.util.ArrayList;
import java.util.List;

import pixelmon.PixelmonEntityList;
import pixelmon.attacks.Attack;
import pixelmon.attacks.BattleController;
import pixelmon.attacks.Moveset;
import pixelmon.attacks.Type;
import pixelmon.attacks.statusEffects.StatusEffectBase;
import pixelmon.comm.ChatHandler;
import pixelmon.database.BattleStats;
import pixelmon.database.DatabaseStats;
import pixelmon.database.EvolutionInfo;
import pixelmon.database.Stats;
import pixelmon.entities.EntityTrainer;
import pixelmon.entities.pixelmon.BaseEntityPixelmon;
import pixelmon.entities.pixelmon.EntityWaterPixelmon;
import pixelmon.entities.pixelmon.helpers.IHaveHelper;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;
import pixelmon.enums.EnumGui;
import pixelmon.items.ItemEvolutionStone;

import net.minecraft.src.DamageSource;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityLookHelper;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModLoader;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.Vec3D;
import net.minecraft.src.World;
import net.minecraft.src.mod_Pixelmon;

public class PixelmonEntityHelper {
	private IHaveHelper pixelmon;

	public PixelmonEntityHelper(IHaveHelper pixelmon) {
		this.pixelmon = pixelmon;
	}

	public EntityLookHelper getLookHelper() {
		if (pixelmon instanceof BaseEntityPixelmon)
			return ((BaseEntityPixelmon) pixelmon).getLookHelper();
		else if (pixelmon instanceof EntityWaterPixelmon)
			return ((EntityWaterPixelmon) pixelmon).getLookHelper();
		return null;
	}

	public String getName() {
		if (pixelmon instanceof BaseEntityPixelmon) {
			BaseEntityPixelmon p = (BaseEntityPixelmon) pixelmon;
			return p.name;
		} else if (pixelmon instanceof EntityWaterPixelmon) {
			EntityWaterPixelmon p = (EntityWaterPixelmon) pixelmon;
			return p.name;
		}
		return null;
	}

	public String getDisplayName() {
		if (pixelmon instanceof BaseEntityPixelmon) {
			BaseEntityPixelmon p = (BaseEntityPixelmon) pixelmon;
			return !(p.nickname == null || p.nickname.equals("")) ? p.nickname : p.name;
		} else if (pixelmon instanceof EntityWaterPixelmon) {
			EntityWaterPixelmon p = (EntityWaterPixelmon) pixelmon;
			return !(p.nickname == null || p.nickname.equals("")) ? p.nickname : p.name;
		}
		return null;
	}

	public BattleStats getBattleStats() {
		if (pixelmon instanceof BaseEntityPixelmon)
			return ((BaseEntityPixelmon) pixelmon).battleStats;
		else if (pixelmon instanceof EntityWaterPixelmon)
			return ((EntityWaterPixelmon) pixelmon).battleStats;
		return null;
	}

	public ArrayList<StatusEffectBase> getStatus() {
		if (pixelmon instanceof BaseEntityPixelmon)
			return ((BaseEntityPixelmon) pixelmon).status;
		else if (pixelmon instanceof EntityWaterPixelmon)
			return ((EntityWaterPixelmon) pixelmon).status;

		return null;
	}

	public boolean attackEntityFrom(DamageSource dmgSource, int power) {
		boolean flag = ((EntityLiving) pixelmon).attackEntityFrom(dmgSource, power);
		Entity entity = dmgSource.getEntity();
		if (pixelmon instanceof IHaveHelper) {
			if (((IHaveHelper) pixelmon).getHelper().isValidTarget(entity)) {
				((IHaveHelper) pixelmon).getHelper().setAttackTarget((EntityLiving) entity);
				((IHaveHelper) pixelmon).getHelper().setTarget(entity);
			}
		}
		return flag;
	}

	private void setTarget(Entity entity) {
		if (entity instanceof BaseEntityPixelmon)
			((BaseEntityPixelmon) entity).setTarget(entity);
		else if (entity instanceof EntityWaterPixelmon)
			((EntityWaterPixelmon) entity).setTarget(entity);

	}

	private void setAttackTarget(EntityLiving entity) {
		if (entity instanceof BaseEntityPixelmon)
			((BaseEntityPixelmon) entity).setAttackTarget(entity);
		else if (entity instanceof EntityWaterPixelmon)
			((EntityWaterPixelmon) entity).setAttackTarget(entity);
	}

	public ArrayList<Type> getType() {
		if (pixelmon instanceof BaseEntityPixelmon)
			return ((BaseEntityPixelmon) pixelmon).type;
		else if (pixelmon instanceof EntityWaterPixelmon)
			return ((EntityWaterPixelmon) pixelmon).type;
		return null;
	}

	public LevelHelper getLvl() {
		if (pixelmon instanceof BaseEntityPixelmon) {
			BaseEntityPixelmon p = (BaseEntityPixelmon) pixelmon;
			if (p.lvl == null) {
				p.lvl = new LevelHelper(p.helper);
				p.lvl.setLevel(mod_Pixelmon.getRandomNumberBetween(getStats().BaseStats.SpawnLevel, getStats().BaseStats.SpawnLevel + getStats().BaseStats.SpawnLevelRange));
				p.setHealth(p.stats.HP);
				p.lvl.updateEntityString();
			}
			return p.lvl;
		} else if (pixelmon instanceof EntityWaterPixelmon) {
			EntityWaterPixelmon p = (EntityWaterPixelmon) pixelmon;
			if (p.lvl == null) {
				p.lvl = new LevelHelper(p.helper);
				p.lvl.setLevel(mod_Pixelmon.getRandomNumberBetween(getStats().BaseStats.SpawnLevel, getStats().BaseStats.SpawnLevel + getStats().BaseStats.SpawnLevelRange));
				p.setHealth(p.stats.HP);
				p.lvl.updateEntityString();
			}
			return ((EntityWaterPixelmon) pixelmon).lvl;
		}
		return null;
	}

	public Stats getStats() {
		if (pixelmon instanceof BaseEntityPixelmon)
			return ((BaseEntityPixelmon) pixelmon).stats;
		else if (pixelmon instanceof EntityWaterPixelmon)
			return ((EntityWaterPixelmon) pixelmon).stats;
		return null;
	}

	public void SetBattleController(BattleController battleController) {
		pixelmon.SetBattleController(battleController);
	}

	public boolean getIsFainted() {
		if (pixelmon instanceof BaseEntityPixelmon)
			return ((BaseEntityPixelmon) pixelmon).isFainted;
		else if (pixelmon instanceof EntityWaterPixelmon)
			return ((EntityWaterPixelmon) pixelmon).isFainted;
		return false;
	}

	public void EndBattle() {
		pixelmon.EndBattle();
	}

	public int getHealth() {
		if (pixelmon instanceof BaseEntityPixelmon)
			return ((BaseEntityPixelmon) pixelmon).getHealth();
		else if (pixelmon instanceof EntityWaterPixelmon)
			return ((EntityWaterPixelmon) pixelmon).getHealth();
		return 0;
	}

	public int getMaxHealth() {
		if (pixelmon instanceof BaseEntityPixelmon && ((BaseEntityPixelmon) pixelmon).stats != null)
			return ((BaseEntityPixelmon) pixelmon).stats.HP;
		else if (pixelmon instanceof EntityWaterPixelmon && ((EntityWaterPixelmon) pixelmon).stats != null)
			return ((EntityWaterPixelmon) pixelmon).stats.HP;
		return 1;
	}

	public Moveset getMoveset() {
		if (pixelmon instanceof BaseEntityPixelmon)
			return ((BaseEntityPixelmon) pixelmon).moveset;
		else if (pixelmon instanceof EntityWaterPixelmon)
			return ((EntityWaterPixelmon) pixelmon).moveset;
		return null;
	}

	public void setHealth(int i) {
		if (pixelmon instanceof BaseEntityPixelmon)
			((BaseEntityPixelmon) pixelmon).setHealth(i);
		else if (pixelmon instanceof EntityWaterPixelmon)
			((EntityWaterPixelmon) pixelmon).setHealth(i);
	}

	public boolean getIsMale() {
		if (pixelmon instanceof BaseEntityPixelmon)
			return ((BaseEntityPixelmon) pixelmon).isMale;
		else if (pixelmon instanceof EntityWaterPixelmon)
			return ((EntityWaterPixelmon) pixelmon).isMale;
		return false;
	}

	public void catchInPokeball() {
		pixelmon.catchInPokeball();
	}

	public int getPokemonId() {
		return pixelmon.getPokemonId();
	}

	public void setIsInBall(boolean b) {
		if (pixelmon instanceof BaseEntityPixelmon)
			((BaseEntityPixelmon) pixelmon).isInBall = b;
		else if (pixelmon instanceof EntityWaterPixelmon)
			((EntityWaterPixelmon) pixelmon).isInBall = b;
	}

	public void writeEntityToNBT(NBTTagCompound nbt) {
		pixelmon.writeEntityToStorageNBT(nbt);
	}

	public void setLocationAndAngles(double posX, double posY, double posZ, float rotationYaw, float f) {
		pixelmon.setLocationAndAngles(posX, posY, posZ, rotationYaw, f);
	}

	public void setMotion(int i, int j, int k) {
		if (pixelmon instanceof BaseEntityPixelmon)
			((BaseEntityPixelmon) pixelmon).setMotion(i, j, k);
		else if (pixelmon instanceof EntityWaterPixelmon)
			((EntityWaterPixelmon) pixelmon).setMotion(i, j, k);
	}

	public void releaseFromPokeball() {
		pixelmon.releaseFromPokeball();
	}

	public void clearAttackTarget() {
		if (pixelmon instanceof BaseEntityPixelmon) {
			((BaseEntityPixelmon) pixelmon).setTarget(null);
			((BaseEntityPixelmon) pixelmon).setAttackTarget(null);
		} else if (pixelmon instanceof EntityWaterPixelmon) {
			((EntityWaterPixelmon) pixelmon).setTarget(null);
			((EntityWaterPixelmon) pixelmon).setAttackTarget(null);
		}
	}

	public boolean getIsInBall() {
		if (pixelmon instanceof BaseEntityPixelmon)
			return ((BaseEntityPixelmon) pixelmon).isInBall;
		else if (pixelmon instanceof EntityWaterPixelmon)
			return ((EntityWaterPixelmon) pixelmon).isInBall;
		return false;
	}

	public void StartBattle(EntityTrainer entityHit, EntityPlayer opponent) {
		pixelmon.StartBattle(entityHit, opponent);
	}

	public void StartBattle(PixelmonEntityHelper entityHit) {
		pixelmon.StartBattle(entityHit);

	}

	public void setTamed(boolean b) {
		if (pixelmon instanceof BaseEntityPixelmon)
			((BaseEntityPixelmon) pixelmon).setTamed(b);
		else if (pixelmon instanceof EntityWaterPixelmon)
			((EntityWaterPixelmon) pixelmon).setTamed(b);
	}

	public void setLocationAndAngles(IHaveHelper currentPixelmon) {
		if (pixelmon instanceof BaseEntityPixelmon)
			((BaseEntityPixelmon) pixelmon).setLocationAndAngles(currentPixelmon);
		else if (pixelmon instanceof EntityWaterPixelmon)
			((EntityWaterPixelmon) pixelmon).setLocationAndAngles(currentPixelmon);
	}

	public String getNickName() {
		if (pixelmon instanceof BaseEntityPixelmon)
			return ((BaseEntityPixelmon) pixelmon).nickname;
		else if (pixelmon instanceof EntityWaterPixelmon)
			return ((EntityWaterPixelmon) pixelmon).nickname;

		return null;
	}

	public void setPokemonID(int uniqueEntityId) {
		pixelmon.setPokemonId(uniqueEntityId);
	}

	public void setNickname(String nickname) {
		if (pixelmon instanceof BaseEntityPixelmon)
			((BaseEntityPixelmon) pixelmon).nickname = nickname;
		else if (pixelmon instanceof EntityWaterPixelmon)
			((EntityWaterPixelmon) pixelmon).nickname = nickname;
	}

	public void setCaughtBall(double caughtBall) {
		if (pixelmon instanceof BaseEntityPixelmon)
			((BaseEntityPixelmon) pixelmon).caughtBall = caughtBall;
		else if (pixelmon instanceof EntityWaterPixelmon)
			((EntityWaterPixelmon) pixelmon).caughtBall = caughtBall;
	}

	public double getCaughtBall() {
		if (pixelmon instanceof BaseEntityPixelmon)
			return ((BaseEntityPixelmon) pixelmon).caughtBall;
		else if (pixelmon instanceof EntityWaterPixelmon)
			return ((EntityWaterPixelmon) pixelmon).caughtBall;
		return -1;
	}

	public void setPositionAndRotation(double posX, double posY, double posZ, float rotationYaw, float rotationPitch) {
		pixelmon.setPositionAndRotation(posX, posY, posZ, rotationYaw, rotationPitch);
	}

	public float getMaxScale() {
		if (pixelmon instanceof BaseEntityPixelmon)
			return ((BaseEntityPixelmon) pixelmon).maxScale;
		else if (pixelmon instanceof EntityWaterPixelmon)
			return ((EntityWaterPixelmon) pixelmon).maxScale;
		return 0;
	}

	public void increaseSize(float percent) {
		if (pixelmon instanceof BaseEntityPixelmon)
			((BaseEntityPixelmon) pixelmon).scale = percent;
		else if (pixelmon instanceof EntityWaterPixelmon)
			((EntityWaterPixelmon) pixelmon).scale = percent;

	}

	public float getScale() {
		if (pixelmon instanceof BaseEntityPixelmon)
			return ((BaseEntityPixelmon) pixelmon).scale;
		else if (pixelmon instanceof EntityWaterPixelmon)
			return ((EntityWaterPixelmon) pixelmon).scale;
		return 0;
	}

	public boolean getDoesHover() {
		if (pixelmon instanceof BaseEntityPixelmon)
			return ((BaseEntityPixelmon) pixelmon).doesHover;

		return false;
	}

	public float getHoverHeight() {
		if (pixelmon instanceof BaseEntityPixelmon)
			return ((BaseEntityPixelmon) pixelmon).hoverHeight;
		return 0;
	}

	public Entity getEntity() {
		return (Entity) pixelmon;
	}

	protected void copyTo(PixelmonEntityHelper entity) {
		entity.setTamed(true);
		entity.setNickname(getNickName());
		entity.setCaughtBall(getCaughtBall());
		entity.getMoveset().clear();
		entity.setIsMale(getIsMale());
		entity.setIsShiny(getIsShiny());
		for (int i = 0; i < getMoveset().size(); i++)
			entity.getMoveset().add(getMoveset().get(i));
		entity.getStats().IVs.CopyIVs(getStats().IVs);
		entity.getLvl().setLevel(getLvl().getLevel());
		entity.getLvl().setEXP(getLvl().getExp());
		if (pixelmon instanceof BaseEntityPixelmon) {
			BaseEntityPixelmon p = (BaseEntityPixelmon) pixelmon;
			entity.setPositionAndRotation(p.posX, p.posY, p.posZ, p.rotationYaw, p.rotationPitch);
		} else if (pixelmon instanceof EntityWaterPixelmon) {
			EntityWaterPixelmon p = (EntityWaterPixelmon) pixelmon;
			entity.setPositionAndRotation(p.posX, p.posY, p.posZ, p.rotationYaw, p.rotationPitch);
		}
	}

	public void evolve(PixelmonEntityHelper entity) {
		entity.setOwner(this.getOwner());
		if (entity.getOwner() != null)
			ChatHandler.sendChat(entity.getOwner(), "Your " + getName() + " evolved into " + entity.getName() + "!");
		int oldHP = getStats().HP;
		copyTo(entity);
		if (getNickName().equals(getName()))
			entity.setNickname(entity.getName());
		float percentIncrease = ((float) getStats().HP) / ((float) oldHP);
		setHealth((int) (((float) getHealth()) * percentIncrease));
		mod_Pixelmon.pokeballManager.getPlayerStorage(entity.getOwner()).replace(this, entity);
		entity.getOwner().worldObj.spawnEntityInWorld((EntityLiving) entity.getEntity());
		entity.setPokemonID(getPokemonId());
		getEntity().setDead();
		entity.getLvl().recalculateXP();
	}

	public IHaveHelper getIHaveHelper() {
		return pixelmon;
	}

	public boolean interact(EntityPlayer entity) {
		if (entity instanceof EntityPlayer) {
			EntityPlayer entity1 = (EntityPlayer) entity;
			ItemStack itemstack = entity1.getCurrentEquippedItem();
			if (itemstack == null)
				return false;
			boolean flag = false;
			if (itemstack.itemID == mod_Pixelmon.rareCandy.shiftedIndex && getOwner() == entity) {
				getLvl().awardEXP(getLvl().getExpToNextLevel() - getLvl().getExp());
				if (!entity.capabilities.isCreativeMode)
					itemstack.stackSize--;
				flag = true;
			}
			if (itemstack.itemID == mod_Pixelmon.pokeChecker.shiftedIndex && getOwner() != null) {
				getOwner().openGui(mod_Pixelmon.instance, EnumGui.PokeChecker.getIndex(), getOwner().worldObj, getPokemonId(), 0, 0); // Pokechecker
				flag = true;
			}
			if (itemstack.itemID == mod_Pixelmon.potion.shiftedIndex && getOwner() == entity) {
				setHealth(getHealth() + 20);
				if (!entity.capabilities.isCreativeMode)
					itemstack.stackSize--;
				if (getHealth() > getStats().HP)
					setHealth(getStats().HP);
				flag = true;
			}
			// if (itemstack.itemID == mod_Pixelmon.pokeDex.shiftedIndex) {
			// if (getOwner() == entity) {
			// getOwner().openGui(mod_Pixelmon.instance,
			// EnumGui.Pokedex.getIndex(), getOwner().worldObj, getPokemonId(),
			// 0, 0); // Pokedex
			// flag = true;
			// }
			// }
			if (itemstack.getItem() instanceof ItemEvolutionStone && getOwner() == entity) {
				ItemEvolutionStone i = (ItemEvolutionStone) itemstack.getItem();
				for (EvolutionInfo e : getEvolveList()) {
					if (e.evolutionStone == i.getType()) {
						BaseEntityPixelmon evolveTo = (BaseEntityPixelmon) PixelmonEntityList.createEntityByName(e.pokemonName, getOwner().worldObj);
						if (evolveTo == null) {
							System.out.println(e.pokemonName + " isn't coded yet");
							break;
						}
						evolve(evolveTo.helper);
						flag = true;
						itemstack.stackSize--;
						break;
					}
				}
			}
			if (itemstack.stackSize <= 0)
				entity.inventory.setInventorySlotContents(entity.inventory.currentItem, null);
			return flag;
		}

		return false;
	}

	public boolean isValidTarget(Entity entity) {
		if (!(entity instanceof IHaveHelper))
			return false;

		if (getOwner() == ((IHaveHelper) entity).getHelper().getOwner()) {
			return false;
		}

		return true;
	}

	public void readFromNBT(NBTTagCompound var1) {
		getLvl().readFromNBT(var1);
		setHealth(var1.getShort("Health"));
		setPokemonID(var1.getInteger("pixelmonID"));
		getStats().readFromNBT(var1);
		if (getHealth() > getStats().HP)
			setHealth(getStats().HP);
		setIsInBall(var1.getBoolean("IsInBall"));
		setNickname(var1.getString("Nickname"));
		increaseSize(var1.getFloat("Scale"));
		setIsFainted(var1.getBoolean("IsFainted"));
		setIsMale(var1.getBoolean("IsMale"));
		setIsShiny(var1.getBoolean("IsShiny"));
		getBattleStats().readFromNBT(var1);
		getMoveset().readFromNBT(var1);
		getLvl().updateEntityString();
	}

	private void setIsMale(boolean boolean1) {
		if (pixelmon instanceof BaseEntityPixelmon)
			((BaseEntityPixelmon) pixelmon).isMale = boolean1;
		else if (pixelmon instanceof EntityWaterPixelmon)
			((EntityWaterPixelmon) pixelmon).isMale = boolean1;
	}

	public void setIsFainted(boolean boolean1) {
		if (pixelmon instanceof BaseEntityPixelmon)
			((BaseEntityPixelmon) pixelmon).isFainted = boolean1;
		else if (pixelmon instanceof EntityWaterPixelmon)
			((EntityWaterPixelmon) pixelmon).isFainted = boolean1;
	}

	public void writeToNBT(NBTTagCompound var1) {
		getLvl().writeToNBT(var1);
		var1.setInteger("pixelmonID", getPokemonId());
		var1.setString("Name", getName());
		if (getNickName() != null && !getNickName().equalsIgnoreCase("")) {
			var1.setString("Nickname", getNickName());
		} else {
			var1.setString("Nickname", getName());
		}
		var1.setBoolean("IsInBall", getIsInBall());
		var1.setFloat("Scale", getScale());
		var1.setBoolean("IsFainted", getIsFainted() || getIsDead());
		var1.setBoolean("IsMale", getIsMale());
		var1.setBoolean("IsShiny", getIsShiny());
		if (getBattleStats() != null)
			getBattleStats().writeToNBT(var1);
		getMoveset().writeToNBT(var1);
		getStats().writeToNBT(var1);
	}

	public boolean getIsDead() {
		if (pixelmon instanceof BaseEntityPixelmon)
			return ((BaseEntityPixelmon) pixelmon).isDead;
		else if (pixelmon instanceof EntityWaterPixelmon)
			return ((EntityWaterPixelmon) pixelmon).isDead;
		return false;
	}

	public ArrayList<EvolutionInfo> getEvolveList() {
		return DatabaseStats.getEvolveList(getName());
	}

	public void unloadEntity() {
		if (pixelmon instanceof BaseEntityPixelmon)
			((BaseEntityPixelmon) pixelmon).unloadEntity();
		else if (pixelmon instanceof EntityWaterPixelmon)
			((EntityWaterPixelmon) pixelmon).unloadEntity();
	}

	public void setTrainer(EntityTrainer trainer) {
		if (pixelmon instanceof BaseEntityPixelmon) {
			((BaseEntityPixelmon) pixelmon).trainer = trainer;
		} else if (pixelmon instanceof EntityWaterPixelmon) {
			((EntityWaterPixelmon) pixelmon).trainer = trainer;
		}
	}

	public EntityTrainer getTrainer() {
		if (pixelmon instanceof BaseEntityPixelmon) {
			return ((BaseEntityPixelmon) pixelmon).trainer;
		} else if (pixelmon instanceof EntityWaterPixelmon) {
			return ((EntityWaterPixelmon) pixelmon).trainer;
		}
		return null;
	}

	public void updateLvlString(String string) {
		if (pixelmon instanceof BaseEntityPixelmon) {
			((BaseEntityPixelmon) pixelmon).setLvlString(string);
		} else if (pixelmon instanceof EntityWaterPixelmon)
			((EntityWaterPixelmon) pixelmon).setLvlString(string);
	}

	public int getXPos() {
		if (pixelmon instanceof BaseEntityPixelmon) {
			return (int) ((BaseEntityPixelmon) pixelmon).posX;
		} else if (pixelmon instanceof EntityWaterPixelmon) {
			return (int) ((EntityWaterPixelmon) pixelmon).posX;
		}
		return 0;
	}

	public int getYPos() {
		if (pixelmon instanceof BaseEntityPixelmon) {
			return (int) ((BaseEntityPixelmon) pixelmon).posY;
		} else if (pixelmon instanceof EntityWaterPixelmon) {
			return (int) ((EntityWaterPixelmon) pixelmon).posY;
		}
		return 0;
	}

	public int getZPos() {
		if (pixelmon instanceof BaseEntityPixelmon) {
			return (int) ((BaseEntityPixelmon) pixelmon).posZ;
		} else if (pixelmon instanceof EntityWaterPixelmon) {
			return (int) ((EntityWaterPixelmon) pixelmon).posZ;
		}
		return 0;
	}

	public BattleController getBattleController() {
		if (pixelmon instanceof BaseEntityPixelmon) {
			return ((BaseEntityPixelmon) pixelmon).bc;
		} else if (pixelmon instanceof EntityWaterPixelmon) {
			return ((EntityWaterPixelmon) pixelmon).bc;
		}
		return null;
	}

	public EntityPlayer getOwner() {
		return pixelmon.getOwner();
	}

	public void setOwner(EntityPlayer player) {
		pixelmon.setOwner(player);
	}

	public void loadMoveset() {
		pixelmon.loadMoveset();
	}

	public void setScale(float f) {
		if (pixelmon instanceof BaseEntityPixelmon)
			((BaseEntityPixelmon) pixelmon).scale = f;
		else if (pixelmon instanceof EntityWaterPixelmon)
			((EntityWaterPixelmon) pixelmon).scale = f;

	}

	public Vec3D getPosition() {
		if (pixelmon instanceof BaseEntityPixelmon) {
			BaseEntityPixelmon p = (BaseEntityPixelmon) pixelmon;
			return Vec3D.createVector(p.posX, p.posY, p.posZ);
		} else if (pixelmon instanceof EntityWaterPixelmon) {
			EntityWaterPixelmon p = (EntityWaterPixelmon) pixelmon;
			return Vec3D.createVector(p.posX, p.posY, p.posZ);
		}
		return null;
	}

	public void setPosition(Vec3D pos) {
		if (pixelmon instanceof BaseEntityPixelmon) {
			BaseEntityPixelmon p = (BaseEntityPixelmon) pixelmon;
			p.posX = pos.xCoord;
			p.posY = pos.yCoord;
			p.posZ = pos.zCoord;
		} else if (pixelmon instanceof EntityWaterPixelmon) {
			EntityWaterPixelmon p = (EntityWaterPixelmon) pixelmon;
			p.posX = pos.xCoord;
			p.posY = pos.yCoord;
			p.posZ = pos.zCoord;
		}
	}

	private void setIsShiny(boolean isShiny) {
		if (pixelmon instanceof BaseEntityPixelmon)
			((BaseEntityPixelmon) pixelmon).setIsShiny(isShiny);
		else if (pixelmon instanceof EntityWaterPixelmon)
			((EntityWaterPixelmon) pixelmon).setIsShiny(isShiny);
	}

	private boolean getIsShiny() {
		if (pixelmon instanceof BaseEntityPixelmon)
			return ((BaseEntityPixelmon) pixelmon).getIsShiny();
		else if (pixelmon instanceof EntityWaterPixelmon)
			return ((EntityWaterPixelmon) pixelmon).getIsShiny();
		return false;
	}

	public void setIsDead(boolean b) {
		if (pixelmon instanceof BaseEntityPixelmon) {
			((BaseEntityPixelmon) pixelmon).isDead = b;
			if (!b)
				((BaseEntityPixelmon) pixelmon).deathTime = 0;
		}
		if (pixelmon instanceof EntityWaterPixelmon) {
			((EntityWaterPixelmon) pixelmon).isDead = b;
			if (!b)
				((EntityWaterPixelmon) pixelmon).deathTime = 0;
		}
	}

	public void clearVelocity() {
		if (pixelmon instanceof BaseEntityPixelmon) {
			((BaseEntityPixelmon) pixelmon).fallDistance = 0;
		}
		if (pixelmon instanceof EntityWaterPixelmon) {
			((EntityWaterPixelmon) pixelmon).fallDistance = 0;
		}
	}
}
