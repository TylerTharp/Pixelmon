package pixelmon.entities;

import java.util.ArrayList;

import pixelmon.*;
import pixelmon.attacks.*;
import pixelmon.attacks.statusEffects.StatusEffectBase;
import pixelmon.database.*;
import pixelmon.gui.EnumGui;

import net.minecraft.src.Block;
import net.minecraft.src.DamageSource;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityAIHurtByTarget;
import net.minecraft.src.EntityAIOwnerHurtByTarget;
import net.minecraft.src.EntityAIOwnerHurtTarget;
import net.minecraft.src.EntityAITasks;
import net.minecraft.src.EntityAnimal;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityTameable;
import net.minecraft.src.EnumSkyBlock;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModLoader;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;
import net.minecraft.src.mod_Pixelmon;

public abstract class BaseEntityPixelmon extends EntityTameable implements IHaveHelper {

	public String name;
	public String nickname = "";
	public float scale = 1F;
	public float maxScale = 1.25F;
	public LevelManager lvl;
	public ArrayList<Type> type = new ArrayList<Type>();
	public Moveset moveset = new Moveset();
	public double caughtBall = 0;
	public boolean isInBall = true;
	public int pokemonId;
	public Stats stats = new Stats();
	public boolean isFainted = false;
	public BattleStats battleStats = new BattleStats();
	public ArrayList<StatusEffectBase> status = new ArrayList<StatusEffectBase>();
	public boolean doesHover = false;
	public float hoverHeight;
	public boolean isMale;
	public PixelmonEntityHelper helper = new PixelmonEntityHelper(this);
	public int aggression;
	public boolean litUp;
	public int litLevel;

	public BaseEntityPixelmon(World par1World) {
		super(par1World);
		stats.IVs = PixelmonIVStore.CreateNewIVs();
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		pokemonId = -1;
		getNavigator().setAvoidsWater(true);
		setSize(0.5f, 0.5f);
		aggression = rand.nextInt(11) - 5;
	}

	public void init() {
		dataWatcher.addObject(18, "");
		moveSpeed = getMoveSpeed();// + getMoveSpeed();
		stats.BaseStats = DatabaseStats.GetBaseStats(name);
		health = 11;
		if (rand.nextInt(100) < stats.BaseStats.MalePercent)
			isMale = true;
		else
			isMale = false;
		type.add(stats.BaseStats.Type1);
		if (stats.BaseStats.Type2 != Type.Mystery)
			type.add(stats.BaseStats.Type2);

		helper.getLvl();
		setSize(stats.BaseStats.Height, width);
	}

	BattleController bc;
	public EntityTrainer trainer;
	private String ownerName;

	public void loadMoveset() {
		moveset = DatabaseMoves.GetInitialMoves(name, helper.getLvl().getLevel());
	}

	public void StartBattle(PixelmonEntityHelper target) {
		if (this.moveset.size() == 0)
			loadMoveset();

		IBattleParticipant p1, p2;
		if (getOwner() != null)
			p1 = new PlayerParticipant(getOwner(), helper);
		else
			p1 = new WildPixelmonParticipant(helper);

		if (target.getOwner() != null)
			p2 = new PlayerParticipant(target.getOwner(), target);
		else
			p2 = new WildPixelmonParticipant(target);

		bc = new BattleController(p1, p2);
		tasks = new EntityAITasks();
	}

	public void StartBattle(EntityTrainer trainer,EntityPlayer opponent) {
		if (this.moveset.size() == 0)
			loadMoveset();
		IBattleParticipant p1, p2;
		if (getOwner() != null)
			p1 = new PlayerParticipant(getOwner(), helper);
		else
			p1 = new WildPixelmonParticipant(helper);

		p2 = new TrainerParticipant(trainer, opponent);

		bc = new BattleController(p1, p2);
		tasks = new EntityAITasks();
	}

	public void SetBattleController(BattleController bc) {
		if (this.moveset.size() == 0)
			loadMoveset();

		this.bc = bc;
		tasks = new EntityAITasks();
	}

	public void EndBattle() {
		this.bc = null;
	}

	public void onDeath(DamageSource damagesource) {
		super.onDeath(damagesource);
		if (getOwner() != null && mod_Pixelmon.pokeballManager.getPlayerStorage(getOwner()).isIn(helper)) {
			String s = "Your " + getName() + " fainted!";
			ChatHandler.sendChat(getOwner(), s);
			isFainted = true;
			health = 0;
			catchInPokeball();
		} else {
			super.onDeath(damagesource);
			this.setDead();
			this.onEntityDeath();
		}
	}

	// Getters and Setters
	public float getMoveSpeed() {
		return 0.3f;
	}

	public int getMaxHealth() {
		if (stats == null)
			return 1;
		return stats.HP;
	}

	public String getName() {
		if (nickname == null || nickname.isEmpty())
			return name;
		return nickname;
	}

	public void setHealth(int i) {
		health = i;
	}

	public boolean isAIEnabled() {
		return true;
	}

	protected boolean isValidTarget(Entity entity) {
		return helper.isValidTarget(entity);
	}

	// Random Crap I
	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) {
		boolean flag = super.attackEntityFrom(par1DamageSource, par2);
		Entity entity = par1DamageSource.getEntity();
		if (isValidTarget(entity)) {
			setAttackTarget((EntityLiving) entity);
			setTarget(entity);
		}
		return flag;
	}

	public boolean interact(EntityPlayer entity) {
		return helper.interact(entity);
	}

	public void catchInPokeball() {
		mod_Pixelmon.pokeballManager.getPlayerStorage(getOwner()).updateNBT(helper);
		isInBall = true;
		unloadEntity();
	}

	public void releaseFromPokeball() {
		this.aggression = 0;
		worldObj.spawnEntityInWorld(this);
		isInBall = false;
	}

	public EntityAnimal spawnBabyAnimal(EntityAnimal entityanimal) {
		return null;
	}

	public abstract void evolve();

	protected void evolve(PixelmonEntityHelper entity) {
		helper.evolve(entity);
	}

	public void clearAttackTarget() {
		setTarget(null);
		setAttackTarget(null);
	}

	public void setAttackTarget(EntityLiving e) {
		super.setAttackTarget(e);
		super.setTarget(e);
		if (e instanceof BaseEntityPixelmon) {
			BaseEntityPixelmon e1 = (BaseEntityPixelmon) e;
			if (e1.getAttackTarget() == null)
				e1.setAttackTarget(this);
		}
	}

	public void increaseSize(float var1) {
		scale = var1;
	}

	public boolean hasOwner() {
		return (getOwnerName() != null && !getOwnerName().isEmpty());
	}

	public Stats getStats() {
		return stats;
	}

	public World getWorldObj() {
		return worldObj;
	}

	public boolean LearnsAttackAtLevel(int level) {
		return DatabaseMoves.LearnsAttackAtLevel(name, level);
	}

	public ArrayList<Attack> getAttacksAtLevel(int level) {
		return DatabaseMoves.getAttacksAtLevel(name, level);
	}

	public void LearnMove() {
		if (getMoveset().size() >= 4) {
			ArrayList<Attack> attacks = getAttacksAtLevel(lvl.getLevel());
			for (int i = 0; i < attacks.size(); i++)
				getOwner().openGui(mod_Pixelmon.instance, EnumGui.LearnMove.getIndex(), worldObj, pokemonId, attacks.get(i).attackIndex, 0);
		}
	}

	public float getMaxScale() {
		return maxScale;
	}

	public Moveset getMoveset() {
		return moveset;
	}

	public boolean getCanSpawnHere() {
		int var1 = MathHelper.floor_double(this.posX);
		int var2 = MathHelper.floor_double(this.boundingBox.minY);
		int var3 = MathHelper.floor_double(this.posZ);
		return this.worldObj.getBlockId(var1, var2 - 1, var3) == Block.grass.blockID && this.worldObj.getFullBlockLightValue(var1, var2, var3) > 8
				&& super.getCanSpawnHere();
	}

	public ArrayList<StatusEffectBase> getStatus() {
		return status;
	}

	public void writeEntityToNBT(NBTTagCompound var1) {
		if (trainer != null && !isStorage)
			return;
		super.writeEntityToNBT(var1);
		helper.writeToNBT(var1);
		if (ownerName != null && ownerName != "")
			var1.setString("pixelmonOwner", ownerName);
	}

	boolean isStorage = false;

	public void writeEntityToStorageNBT(NBTTagCompound var1) {
		isStorage = true;
		writeEntityToNBT(var1);
		isStorage = false;
	}

	public void readEntityFromNBT(NBTTagCompound var1) {
		super.readEntityFromNBT(var1);
		helper.readFromNBT(var1);
		if (var1.hasKey("pixelmonOwner"))
			ownerName = var1.getString("pixelmonOwner");
	}

	public void setMotion(int i, int j, int k) {
		motionX = i;
		motionY = j;
		motionZ = k;
	}

	public void setLocationAndAngles(IHaveHelper currentPixelmon) {
		if (currentPixelmon instanceof BaseEntityPixelmon) {
			this.posX = ((BaseEntityPixelmon) currentPixelmon).posX;
			this.posY = ((BaseEntityPixelmon) currentPixelmon).posY;
			this.posZ = ((BaseEntityPixelmon) currentPixelmon).posZ;
		} else if (currentPixelmon instanceof EntityWaterPixelmon) {
			this.posX = ((EntityWaterPixelmon) currentPixelmon).posX;
			this.posY = ((EntityWaterPixelmon) currentPixelmon).posY;
			this.posZ = ((EntityWaterPixelmon) currentPixelmon).posZ;
		}
	}

	public PixelmonEntityHelper getHelper() {
		return helper;
	}

	public void onUpdate() {
		if (bc != null) {
			if (bc.participant1.currentPokemon() == helper) bc.update();
		}
		if (litUp = true) {
			double po11 = this.lastTickPosX;
			double po22 = this.lastTickPosY;
			double po33 = this.lastTickPosZ;
			int par11 = (int) po11;
			int par22 = (int) po22;
			int par33 = (int) po33;
			double par44 = po11 + po22 + po33;
			int par444 = worldObj.getBlockLightValue(par11, par22, par33);
			double po1 = this.posX;
			double po2 = this.posY;
			double po3 = this.posZ;
			int par1 = (int) po1;
			int par2 = (int) po2;
			int par3 = (int) po3;
			double par4 = po1 + po2 + po3;
			if (par44 != par4) {
				worldObj.setLightValue(EnumSkyBlock.Block, par11, par22, par33, par444);
			} else {
				if (par444 < litLevel)
					worldObj.setLightValue(EnumSkyBlock.Block, par11, par22, par33, litLevel);
			}
		}
		super.onUpdate();
	}

	public EntityPlayer getOwner() {
		if (ownerName == null)
			return null;
		return worldObj.getPlayerEntityByName(ownerName);
	}

	public void setOwner(EntityPlayer owner) {
		ownerName = owner.username;
	}

	public void unloadEntity() {
		ArrayList<Entity> list = new ArrayList<Entity>();
		list.add(this);
		worldObj.unloadEntities(list);
		clearAttackTarget();
		if (bc != null) {
			bc = null;
		}
	}

	public void setLvlString(String string) {
		dataWatcher.updateObject(18, string);
	}
}
