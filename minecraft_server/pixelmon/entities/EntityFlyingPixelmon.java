package pixelmon.entities;

import java.util.List;

import pixelmon.AI.EntityAINearestPixelmonTarget;
import pixelmon.AI.EntityAIStartBattle;
import pixelmon.attacks.BattleController;

import net.minecraft.src.*;

public abstract class EntityFlyingPixelmon extends BaseEntityPixelmon {

	private enum landingPlaceEnum {
		trees, water
	};

	private double waypointX;
	private double waypointY;
	private double waypointZ;
	public boolean isAggressive = false;
	public int courseChangeCooldown = 0;
	private Entity targetedEntity;
	private int aggroCooldown = 0;
	public boolean doesLand = true;
	private int attackCounter = 0;

	private landingPlaceEnum landingPlace = landingPlaceEnum.trees;

	public EntityFlyingPixelmon(World world) {
		super(world);
		moveSpeed = 2;
		setSize(0.5F, 0.3F);
		isCollidedVertically = true;
		wingb = 0.0F;
		wingc = 0.0F;
		wingh = 1.0F;
		fleeing = false;
	}

	public void init() {
		super.init();
		if (aggression > 0) {
			tasks.addTask(0, new EntityAIMoveTowardsTarget(this, moveSpeed, 15));
			tasks.addTask(1, new EntityAINearestPixelmonTarget(this, 10, 50 - aggression, true));
			tasks.addTask(2, new EntityAIStartBattle(this));
		}
		tasks.addTask(3, new EntityAISwimming(this));
		tasks.addTask(4, new EntityAITempt(this, moveSpeed, mod_Pixelmon.rareCandy.shiftedIndex, false));
		tasks.addTask(6, new EntityAIWatchClosest(this, pixelmon.entities.BaseEntityPixelmon.class, 8F));
		tasks.addTask(7, new EntityAILookIdle(this));
	}

	public void StartBattle(PixelmonEntityHelper target) {
		super.StartBattle(target);
	}

	public void StartBattle(EntityTrainer trainer, EntityPlayer opponent) {
		super.StartBattle(trainer,opponent);
	}

	public void SetBattleController(BattleController bc) {
		super.SetBattleController(bc);
	}

	public void EndBattle() {
		bc = null;
	}

	protected void fall(float f) {
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		winge = wingb;
		wingd = wingc;
		wingc = (float) ((double) wingc + (double) (onGround ? -1 : 4) * 0.29999999999999999D);
		if (wingc < 0.0F) {
			wingc = 0.0F;
		}
		if (wingc > 1.0F) {
			wingc = 1.0F;
		}
		if (onGround && !doesLand)
			motionY = 1F;
		if (!onGround && wingh < 1.0F) {
			wingh = 1.0F;
		}
		wingh = (float) ((double) wingh * 0.90000000000000002D);
		if (!onGround && motionY < 0.0D) {
			motionY *= 0.80000000000000004D;
		}
		wingb += wingh * 2.0F;
		EntityLiving entityliving = getClosestEntityLiving(this, 4D);
		if (entityliving != null && canEntityBeSeen(entityliving)) {
			fleeing = true;
		}
		if (rand.nextInt(300) == 0) {
			fleeing = true;
		}
		if (fleeing) {
			if (FlyToNextTree()) {
				fleeing = false;
			}
			int ai[] = ReturnNearestMaterialCoord(this, Material.leaves, Double.valueOf(16D));
			if (ai[0] == -1) {
				for (int i = 0; i < 2; i++) {
					WingFlap();
				}

				fleeing = false;
			}
			if (rand.nextInt(50) == 0) {
				fleeing = false;
			}
		}
	}

	protected void updateEntityActionState() {
		if (doesLand) {
			if (onGround
					&& rand.nextInt(10) == 0
					&& (motionX > 0.050000000000000003D || motionZ > 0.050000000000000003D || motionX < -0.050000000000000003D || motionZ < -0.050000000000000003D)) {
				motionY = 0.25D;
			}
			if (!fleeing) {
				super.updateEntityActionState();
			}
		} else {
			if (!this.worldObj.isRemote && this.worldObj.difficultySetting == 0) {
				this.setDead();
			}

			this.despawnEntity();
			double var1 = this.waypointX - this.posX;
			double var3 = this.waypointY - this.posY;
			double var5 = this.waypointZ - this.posZ;
			double var7 = (double) MathHelper.sqrt_double(var1 * var1 + var3 * var3 + var5 * var5);

			if (var7 < 1.0D || var7 > 60.0D) {
				this.waypointX = this.posX + (double) ((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
				this.waypointY = this.posY + (double) ((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
				this.waypointZ = this.posZ + (double) ((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
			}

			if (this.courseChangeCooldown-- <= 0) {
				this.courseChangeCooldown += this.rand.nextInt(5) + 2;

				if (this.isCourseTraversable(this.waypointX, this.waypointY, this.waypointZ, var7)) {
					this.motionX += var1 / var7 * 0.1D;
					this.motionY += var3 / var7 * 0.1D;
					this.motionZ += var5 / var7 * 0.1D;
				} else {
					this.waypointX = this.posX;
					this.waypointY = this.posY;
					this.waypointZ = this.posZ;
				}
			}
			if (this.isAggressive) {
				if (this.targetedEntity != null && this.targetedEntity.isDead) {
					this.targetedEntity = null;
				}

				if (this.targetedEntity == null || this.aggroCooldown-- <= 0) {
					this.targetedEntity = this.worldObj.getClosestVulnerablePlayerToEntity(this, 100.0D);

					if (this.targetedEntity != null) {
						this.aggroCooldown = 20;
					}
				}

				double var9 = 64.0D;

				if (this.targetedEntity != null && ((Entity) this.targetedEntity).getDistanceSqToEntity(this) < var9 * var9) {
					double var11 = this.targetedEntity.posX - this.posX;
					double var13 = this.targetedEntity.boundingBox.minY + (double) (this.targetedEntity.height / 2.0F)
							- (this.posY + (double) (this.height / 2.0F));
					double var15 = this.targetedEntity.posZ - this.posZ;
					this.renderYawOffset = this.rotationYaw = -((float) Math.atan2(var11, var15)) * 180.0F / (float) Math.PI;

					if (this.canEntityBeSeen(this.targetedEntity)) {
						if (this.attackCounter == 10) {
							this.worldObj.playAuxSFXAtEntity((EntityPlayer) null, 1007, (int) this.posX, (int) this.posY, (int) this.posZ, 0);
						}

						++this.attackCounter;

						if (this.attackCounter == 20) {
							this.worldObj.playAuxSFXAtEntity((EntityPlayer) null, 1008, (int) this.posX, (int) this.posY, (int) this.posZ, 0);
							EntityFireball var17 = new EntityFireball(this.worldObj, this, var11, var13, var15);
							double var18 = 4.0D;
							Vec3D var20 = this.getLook(1.0F);
							var17.posX = this.posX + var20.xCoord * var18;
							var17.posY = this.posY + (double) (this.height / 2.0F) + 0.5D;
							var17.posZ = this.posZ + var20.zCoord * var18;
							this.worldObj.spawnEntityInWorld(var17);
							this.attackCounter = -40;
						}
					} else if (this.attackCounter > 0) {
						--this.attackCounter;
					}
				} else {
					this.renderYawOffset = this.rotationYaw = -((float) Math.atan2(this.motionX, this.motionZ)) * 180.0F / (float) Math.PI;

					if (this.attackCounter > 0) {
						--this.attackCounter;
					}
				}

				if (!this.worldObj.isRemote) {
					byte var21 = this.dataWatcher.getWatchableObjectByte(16);
					byte var12 = (byte) (this.attackCounter > 10 ? 1 : 0);

					if (var21 != var12) {
						this.dataWatcher.updateObject(16, Byte.valueOf(var12));
					}
				}
			}
		}
	}

	/**
	 * True if the ghast has an unobstructed line of travel to the waypoint.
	 */
	private boolean isCourseTraversable(double par1, double par3, double par5, double par7) {
		double var9 = (this.waypointX - this.posX) / par7;
		double var11 = (this.waypointY - this.posY) / par7;
		double var13 = (this.waypointZ - this.posZ) / par7;
		AxisAlignedBB var15 = this.boundingBox.copy();

		for (int var16 = 1; (double) var16 < par7; ++var16) {
			var15.offset(var9, var11, var13);

			if (this.worldObj.getCollidingBoundingBoxes(this, var15).size() > 0) {
				return false;
			}
		}

		return true;
	}

	@SuppressWarnings("rawtypes")
	private EntityItem getClosestSeeds(Entity entity, double d) {
		double d1 = -1D;
		EntityItem entityitem = null;
		List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(d, d, d));
		for (int i = 0; i < list.size(); i++) {
			Entity entity1 = (Entity) list.get(i);
			if (!(entity1 instanceof EntityItem)) {
				continue;
			}
			EntityItem entityitem1 = (EntityItem) entity1;
			if (entityitem1.item.itemID != Item.seeds.shiftedIndex) {
				continue;
			}
			double d2 = entityitem1.getDistanceSq(entity.posX, entity.posY, entity.posZ);
			if ((d < 0.0D || d2 < d * d) && (d1 == -1D || d2 < d1)) {
				d1 = d2;
				entityitem = entityitem1;
			}
		}

		return entityitem;
	}

	@SuppressWarnings("rawtypes")
	private EntityLiving getClosestEntityLiving(Entity entity, double d) {
		double d1 = -1D;
		EntityLiving entityliving = null;
		List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(d, d, d));
		for (int i = 0; i < list.size(); i++) {
			Entity entity1 = (Entity) list.get(i);
			if (!(entity1 instanceof EntityLiving) || (entity1 instanceof EntityFlyingPixelmon)) {
				continue;
			}
			double d2 = entity1.getDistanceSq(entity.posX, entity.posY, entity.posZ);
			if ((d < 0.0D || d2 < d * d) && (d1 == -1D || d2 < d1) && ((EntityLiving) entity1).canEntityBeSeen(entity)) {
				d1 = d2;
				entityliving = (EntityLiving) entity1;
			}
		}

		return entityliving;
	}

	private boolean FlyToNextEntity(Entity entity) {
		if (entity != null) {
			int i = MathHelper.floor_double(entity.posX);
			int j = MathHelper.floor_double(entity.posY);
			int k = MathHelper.floor_double(entity.posZ);
			facePosition(i, j, k, 30F);
			if (MathHelper.floor_double(posY) < j) {
				motionY += 0.14999999999999999D;
			}
			if (posX < entity.posX) {
				double d = entity.posX - posX;
				if (d > 0.5D) {
					motionX += 0.050000000000000003D;
				}
			} else {
				double d1 = posX - entity.posX;
				if (d1 > 0.5D) {
					motionX -= 0.050000000000000003D;
				}
			}
			if (posZ < entity.posZ) {
				double d2 = entity.posZ - posZ;
				if (d2 > 0.5D) {
					motionZ += 0.050000000000000003D;
				}
			} else {
				double d3 = posZ - entity.posZ;
				if (d3 > 0.5D) {
					motionZ -= 0.050000000000000003D;
				}
			}
			return true;
		} else {
			return false;
		}
	}

	private void WingFlap() {
		motionY += 0.050000000000000003D;
		if (rand.nextInt(30) == 0) {
			motionX += 0.20000000000000001D;
		}
		if (rand.nextInt(30) == 0) {
			motionX -= 0.20000000000000001D;
		}
		if (rand.nextInt(30) == 0) {
			motionZ += 0.20000000000000001D;
		}
		if (rand.nextInt(30) == 0) {
			motionZ -= 0.20000000000000001D;
		}
	}

	private boolean FlyToNextTree() {
		int ai[] = ReturnNearestMaterialCoord(this, Material.leaves, Double.valueOf(20D));
		int ai1[] = FindTreeTop(ai[0], ai[1], ai[2]);
		if (ai1[1] != 0) {
			int i = ai1[0];
			int j = ai1[1];
			int k = ai1[2];
			facePosition(i, j, k, 30F);
			if (j - MathHelper.floor_double(posY) > 2) {
				motionY += 0.14999999999999999D;
			}
			int l = 0;
			int i1 = 0;
			if (posX < (double) i) {
				l = i - MathHelper.floor_double(posX);
				motionX += 0.050000000000000003D;
			} else {
				l = MathHelper.floor_double(posX) - i;
				motionX -= 0.050000000000000003D;
			}
			if (posZ < (double) k) {
				i1 = k - MathHelper.floor_double(posZ);
				motionZ += 0.050000000000000003D;
			} else {
				i1 = MathHelper.floor_double(posX) - k;
				motionZ -= 0.050000000000000003D;
			}
			double d = l + i1;
			if (d < 3D) {
				return true;
			}
		}
		return false;
	}

	private boolean FlyToNearestWater() {
		int ai[] = ReturnNearestMaterialCoord(this, Material.water, Double.valueOf(20D));
		int i = ai[0];
		int j = ai[1];
		int k = ai[2];
		facePosition(i, j, k, 30F);
		if (j - MathHelper.floor_double(posY) > 2) {
			motionY += 0.14999999999999999D;
		}
		int l = 0;
		int i1 = 0;
		if (posX < (double) i) {
			l = i - MathHelper.floor_double(posX);
			motionX += 0.050000000000000003D;
		} else {
			l = MathHelper.floor_double(posX) - i;
			motionX -= 0.050000000000000003D;
		}
		if (posZ < (double) k) {
			i1 = k - MathHelper.floor_double(posZ);
			motionZ += 0.050000000000000003D;
		} else {
			i1 = MathHelper.floor_double(posX) - k;
			motionZ -= 0.050000000000000003D;
		}
		double d = l + i1;
		if (d < 3D) {
			return true;
		}
		return false;
	}

	public void facePosition(int i, int j, int k, float f) {
		double d = (double) i - posX;
		double d1 = (double) k - posZ;
		double d2 = (double) j - posY;
		double d3 = MathHelper.sqrt_double(d * d + d1 * d1);
		float f1 = (float) ((Math.atan2(d1, d) * 180D) / 3.1415927410125728D) - 90F;
		float f2 = (float) ((Math.atan2(d2, d3) * 180D) / 3.1415927410125728D);
		rotationPitch = -b(rotationPitch, f2, f);
		rotationYaw = b(rotationYaw, f1, f);
	}

	private float b(float f, float f1, float f2) {
		float f3 = f1;
		for (f3 = f1 - f; f3 < -180F; f3 += 360F) {
		}
		for (; f3 >= 180F; f3 -= 360F) {
		}
		if (f3 > f2) {
			f3 = f2;
		}
		if (f3 < -f2) {
			f3 = -f2;
		}
		return f + f3;
	}

	private int[] FindTreeTop(int i, int j, int k) {
		int l = i - 5;
		int i1 = k - 5;
		int j1 = i + 5;
		int k1 = j + 7;
		int l1 = k + 5;
		for (int i2 = l; i2 < j1; i2++) {
			label0: for (int j2 = i1; j2 < l1; j2++) {
				int k2 = worldObj.getBlockId(i2, j, j2);
				if (k2 == 0 || Block.blocksList[k2].blockMaterial != Material.wood) {
					continue;
				}
				int l2 = j;
				do {
					if (l2 >= k1) {
						continue label0;
					}
					int i3 = worldObj.getBlockId(i2, l2, j2);
					if (i3 == 0) {
						return (new int[] { i2, l2 + 2, j2 });
					}
					l2++;
				} while (true);
			}

		}

		return (new int[] { 0, 0, 0 });
	}

	public int[] ReturnNearestMaterialCoord(Entity entity, Material material, Double double1) {
		AxisAlignedBB axisalignedbb = entity.boundingBox.expand(double1.doubleValue(), double1.doubleValue(), double1.doubleValue());
		int i = MathHelper.floor_double(axisalignedbb.minX);
		int j = MathHelper.floor_double(axisalignedbb.maxX + 1.0D);
		int k = MathHelper.floor_double(axisalignedbb.minY);
		int l = MathHelper.floor_double(axisalignedbb.maxY + 1.0D);
		int i1 = MathHelper.floor_double(axisalignedbb.minZ);
		int j1 = MathHelper.floor_double(axisalignedbb.maxZ + 1.0D);
		for (int k1 = i; k1 < j; k1++) {
			for (int l1 = k; l1 < l; l1++) {
				for (int i2 = i1; i2 < j1; i2++) {
					int j2 = worldObj.getBlockId(k1, l1, i2);
					if (j2 != 0 && Block.blocksList[j2].blockMaterial == material) {
						return (new int[] { k1, l1, i2 });
					}
				}

			}

		}

		return (new int[] { -1, 0, 0 });
	}

	public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		super.writeEntityToNBT(nbttagcompound);
	}

	public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		super.readEntityFromNBT(nbttagcompound);
	}

	protected String getLivingSound() {
		return null;
	}

	public boolean getCanSpawnHere() {
		int i = MathHelper.floor_double(posX);
		int j = MathHelper.floor_double(boundingBox.minY);
		int k = MathHelper.floor_double(posZ);
		worldObj.getBlockId(i, j - 1, k);
		if (super.getCanSpawnHere()) {
			return true;
		} else {
			return false;
		}
	}

	private boolean fleeing;
	public float wingb;
	public float wingc;
	public float wingd;
	public float winge;
	public float wingh;
}
