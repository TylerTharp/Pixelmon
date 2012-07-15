package pixelmon.entities;

import java.util.Random;

import org.lwjgl.util.vector.Vector3f;

import pixelmon.ChatHandler;
import pixelmon.storage.PokeballManager;
import net.minecraft.src.EntityCrit2FX;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityReddustFX;
import net.minecraft.src.EntityThrowable;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.MovingObjectPosition;
import net.minecraft.src.Vec3D;
import net.minecraft.src.World;
import net.minecraft.src.mod_Pixelmon;

public class EntityEmptyPokeBall extends EntityThrowable {
	public double ballBonus;
	public int shakePokeball;
	private EntityLiving thrower;
	private PixelmonEntityHelper p;
	private boolean isWaiting;
	private int waitTime;
	private boolean canCatch = false;

	public EntityEmptyPokeBall(World world) {
		super(world);
		ballBonus = 1;
	}

	public EntityEmptyPokeBall(World world, EntityLiving entityliving, double d) {
		super(world, entityliving);
		thrower = entityliving;
		ballBonus = d;
	}

	public void init() {
	}

	@Override
	protected void onImpact(MovingObjectPosition movingobjectposition) {
		if (!worldObj.isRemote) {
			if (movingobjectposition != null) {
				if (movingobjectposition.entityHit != null && (movingobjectposition.entityHit instanceof IHaveHelper)) {
					IHaveHelper entitypixelmon = (IHaveHelper) movingobjectposition.entityHit;
					p = entitypixelmon.getHelper();
					if (p.getOwner() == (EntityPlayer) thrower) {
						ChatHandler.sendChat((EntityPlayer) thrower, "You can't catch other people's Pokemon!");
						spawnFailParticles();
						return;
					}
					doCaptureCalc(p);
					isWaiting = true;
					motionX = motionZ = 0;
					motionY = -0.1;
				}

				else {
					if (!isWaiting) {
						entityDropItem(new ItemStack(mod_Pixelmon.getKindOfBallFromBonus(ballBonus, true)), 0.0F);
						setDead();
					}
				}
			}
		}
	}

	int numRocks = 0;
	boolean isUnloaded = false;

	Vec3D initPos;
	Vec3D diff;
	float initialScale;

	@Override
	public void onEntityUpdate() {
		if (worldObj.isRemote) {
			motionX = motionY = motionZ = 0;
		}
		if (isWaiting) {
			if (waitTime == 0 && !isUnloaded) {
				initialScale = p.getScale();
				initPos = p.getPosition();
				diff = Vec3D.createVector(posX, posY, posZ).subtract(initPos);
				p.setScale(initialScale / 1.1f);
			}
			if (waitTime == 1 && !isUnloaded) {
				p.setScale(initialScale / 1.3f);
				moveCloser();
			}
			if (waitTime == 2 && !isUnloaded) {
				p.setScale(initialScale / 1.7f);
				moveCloser();
			}
			if (waitTime == 3 && !isUnloaded) {
				p.setScale(initialScale / 2.2f);
				moveCloser();
			}
			if (waitTime == 4 && !isUnloaded) {
				p.setScale(initialScale / 3);
				moveCloser();
			}
			if (waitTime == 4 && !isUnloaded) {
				p.unloadEntity();
				isUnloaded = true;
				waitTime = 0;
			}
			if (!thrower.worldObj.isAirBlock((int) this.posX, (int) Math.ceil(this.posY) - 2, (int) this.posZ) && this.posY % 1 <= this.height) {
				this.motionY = 0;
				this.motionX = 0;
				this.motionZ = 0;
			}

			waitTime++;
		}
	}

	int i = 0;

	private void moveCloser() {
		p.setPosition(initPos.addVector(diff.xCoord * i / 4, diff.yCoord * i / 4, diff.zCoord * i / 4));
		i++;
	}

	int initialDelay = 15;
	int wobbleTime = 5;

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (isWaiting)
			rotationPitch = 0;

		if (waitTime >= initialDelay && waitTime < initialDelay + wobbleTime) {
			p.setScale(initialScale);
			if (numShakes == 0)
				catchPokemon();
			this.rotationPitch = ((float) (waitTime - initialDelay)) / wobbleTime * (float) 35;
		} else if (waitTime >= initialDelay + wobbleTime && waitTime < initialDelay + 3 * wobbleTime) {
			this.rotationPitch = -1 * ((float) (waitTime - (initialDelay + wobbleTime))) / wobbleTime * (float) 35 + 35;
		} else if (waitTime >= initialDelay + 3 * wobbleTime && waitTime < initialDelay + 4 * wobbleTime) {
			this.rotationPitch = ((float) (waitTime - (initialDelay + 3 * wobbleTime))) / wobbleTime * (float) 35 - 35;
		} else if (waitTime == initialDelay + 4 * wobbleTime) {
			waitTime = 0;
			shakeCount++;
			if (shakeCount == numShakes - 1 || numShakes == 1) {
				catchPokemon();
			}
		}
	}

	int numShakes = 0;
	int shakeCount = 0;

	private void doCatchCheck() {

	}

	private void catchPokemon() {
		if (canCatch) {
			p.setTamed(true);
			p.setOwner((EntityPlayer) thrower);
			mod_Pixelmon.pokeballManager.getPlayerStorage((EntityPlayer) thrower).addToParty(p);
			p.clearAttackTarget();
			p.catchInPokeball();
			spawnCaptureParticles();
			waitTime = 0;
			isUnloaded = false;
			isWaiting = false;
			setDead();
		} else {
			spawnFailParticles();
			waitTime = 0;
			isWaiting = false;
			isUnloaded = false;
			worldObj.spawnEntityInWorld(p.getEntity());
			setDead();
		}
	}

	private void spawnCaptureParticles() {
		for (int i = mod_Pixelmon.getRandomNumberBetween(5, 7); i > 0; i--) {
			EntityCrit2FX entitycrit2fx = new EntityCrit2FX(worldObj, this, "crit");
			ModLoader.getMinecraftInstance().effectRenderer.addEffect(entitycrit2fx);
		}
	}

	private void spawnFailParticles() {

		for (int i = 0; i < 30; i++) {
			EntityReddustFX entityred = new EntityReddustFX(worldObj, posX, posY, posZ, 1, 0, 0);
			entityred.setVelocity(worldObj.rand.nextFloat() / 5, worldObj.rand.nextFloat() / 5, worldObj.rand.nextFloat() / 5);
			ModLoader.getMinecraftInstance().effectRenderer.addEffect(entityred);
		}
	}

	private int b;

	protected void doCaptureCalc(PixelmonEntityHelper entitypixelmon) {
		int pokemonRate = entitypixelmon.getStats().BaseStats.CatchRate;
		int hpMax = entitypixelmon.getMaxHealth();
		int hpCurrent = entitypixelmon.getHealth();
		int bonusStatus = 1;
		double a, b, p;
		a = (((3 * hpMax - 2 * hpCurrent) * pokemonRate * ballBonus) / (3 * hpMax)) * bonusStatus;
		b = (Math.pow(2, 16) - 1) * Math.sqrt(Math.sqrt((a / (Math.pow(2, 8) - 1))));
		p = Math.pow(((b + 1) / Math.pow(2, 16)), 4);
		p = (p * 10000) / 100;
		b = (int) Math.floor(65536 / Math.pow((255 / p), 1f / 4f));
		int passedShakes = 0;
		for (int i = 0; i < 4; i++) {
			int roll = new Random().nextInt(65536);
			if (roll <= b) {
				passedShakes++;
			}
		}
		if (passedShakes == 4) {
			canCatch = true;
		} else {
			canCatch = false;
		}
		numShakes = passedShakes;
	}
}
