package pixelmon.entities.pixelmon.helpers;

import pixelmon.entities.pixelmon.EntityFlyingPixelmon;
import net.minecraft.src.Block;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerSP;
import net.minecraft.src.MathHelper;
import net.minecraft.src.MovementInput;
import net.minecraft.src.World;

public class RidingHelper {

	MovementInput movementInput; // how we will receive input from the player to
									// initialize sprinting.
	int sprintToggleTimer; // also part of the sprinting code
	int jumpTicks; // part of jumping code
	double speedBonus; // multiplies the player movement input to make the mount
						// move faster

	EntityLiving parent;
	World worldObj;

	public RidingHelper(EntityLiving parent, World worldObj) {
		this.worldObj = worldObj;
		this.parent = parent;
		sprintToggleTimer = 0;
		speedBonus = 10;
	}

	public double getMountedYOffset() {
		return (double) parent.height * 0.9D;
	}

	public void onLivingUpdate() {
		if (sprintToggleTimer > 0) { // used to determine if sprinting should be
										// activated.
			sprintToggleTimer--;
		}
		if (jumpTicks > 0 && !(parent instanceof EntityFlyingPixelmon)) // used
																		// to
																		// limit
																		// how
																		// long
																		// the
																		// mount
																		// will
																		// rise
																		// while
		// jumping
		{
			jumpTicks--;
		}

		if (parent.riddenByEntity != null) {
			// stops up-and-down head movement
			parent.rotationPitch = 0;

			// Control where the horse is facing (doesn't work while standing
			// still)
			EntityPlayer entityRider = (EntityPlayer) parent.riddenByEntity;
			parent.rotationYaw = parent.prevRotationYaw = entityRider.rotationYaw;
		}
	}

	public void moveEntity(double d, double d1, double d2) {
		if (parent.riddenByEntity != null) {
			/**
			 * initiate sprinting while ridden via keybind. Basically, if the
			 * player has tapped once, it begins the timer which counts down
			 * from seven, slightly less than a quarter second in realtime. If
			 * the player taps forward before the countdown, sprinting is
			 * initiated
			 */
			boolean flag = ((EntityPlayerSP) parent.riddenByEntity).movementInput.moveForward >= 0.8F;
			((EntityPlayerSP) parent.riddenByEntity).movementInput.updatePlayerMoveState();
			boolean flag1 = ((EntityPlayerSP) parent.riddenByEntity).movementInput.moveForward >= 0.8F;

			if (parent.onGround && !flag && flag1 && !parent.isSprinting()) // if
																			// forward
																			// is
																			// tapped...
			{
				if (sprintToggleTimer == 0) // ... if it is tapped after the
											// countdown reach zero...
				{
					sprintToggleTimer = 7; // ... reset...
				} else // ... But if forward is hit before the end of the
						// countdown...
				{
					parent.setSprinting(true); // ... sprint!
					sprintToggleTimer = 0;
				}
			}

			// Adjust for ice. Ice caused a rapid acceleration so I had to slow
			// the mount while on it.
			int j = worldObj.getBlockId(MathHelper.floor_double(parent.posX), MathHelper.floor_double(parent.boundingBox.minY) - 1, MathHelper.floor_double(parent.posZ));
			float f = 1;
			if (j == Block.ice.blockID) {
				f = Block.blocksList[j].slipperiness * 0.3F;
			}

			// Initiate jumping while ridden via keybind
			if (((EntityPlayerSP) parent.riddenByEntity).movementInput.jump) { // hijacking
																				// the
																				// preset
																				// 'jump'
																				// input.
				jump(true); // this method is seen overridden in here
			}

			// Determine movement and speed
			if (parent.isSprinting() && parent.onGround) { // if sprinting on
															// the ground
				parent.motionX += parent.riddenByEntity.motionX * speedBonus * f;
				parent.motionZ += parent.riddenByEntity.motionZ * speedBonus * f;
			} else if (parent.isSprinting()) { // if jumping while sprinting
				if (parent instanceof EntityFlyingPixelmon) {
					parent.motionX += parent.riddenByEntity.motionX * 4D;
					parent.motionZ += parent.riddenByEntity.motionZ * 4D;

				} else {
					parent.motionX += parent.riddenByEntity.motionX * 3D;
					parent.motionZ += parent.riddenByEntity.motionZ * 3D;
				}
			} else if (parent.onGround && !onIce()) {
				parent.motionX += parent.riddenByEntity.motionX * 7D * f;
				parent.motionZ += parent.riddenByEntity.motionZ * 7D * f;
			} else { // jumping while walking normally
				if (parent instanceof EntityFlyingPixelmon) {
					parent.motionX += parent.riddenByEntity.motionX * 3D;
					parent.motionZ += parent.riddenByEntity.motionZ * 3D;
				} else {
					parent.motionX += parent.riddenByEntity.motionX;
					parent.motionZ += parent.riddenByEntity.motionZ;
				}
			}

			// Cancel sprinting I don't -think- the check for being in water or
			// web is necessary, but its logical.
			if (parent.isSprinting() && (((EntityPlayerSP) parent.riddenByEntity).movementInput.moveForward < 0.8F) || parent.isInWater()) {
				parent.setSprinting(false);
			}

			// Autojump
			if (parent.isCollidedHorizontally) {
				jump(false);
			}

			// Submit!
			((IHaveHelper) parent).doMoveEntity(parent.motionX, parent.motionY, parent.motionZ);
		} else
			((IHaveHelper) parent).doMoveEntity(d, d1, d2);
	}

	public void updateRidden() {
		if (parent.ridingEntity.isDead) {
			parent.ridingEntity = null;
			return;
		}
		parent.motionX = 0.0D;
		parent.motionY = 0.0D;
		parent.motionZ = 0.0D;
		parent.onUpdate();
		if (parent.ridingEntity == null) {
			return;
		}
	}

	public void jump(Boolean flag) { // boolean. true = 2.5-high jump. false =
										// normal jump.
		if (parent.onGround && jumpTicks == 0 && !(parent instanceof EntityFlyingPixelmon)) {
			if (parent instanceof IHaveHelper)
				if (flag) {
					parent.motionY += 0.2; // makes mount jump higher. Do not
											// use big values!
				}
			jumpTicks = 10;
		}
		if (parent instanceof EntityFlyingPixelmon) {
			if (parent.onGround) {
				parent.motionY += 0.3;
			} else
				parent.motionY += 0.1;
		}
	}

	protected boolean onIce() { // just a check.
		int i = MathHelper.floor_double(parent.posX);
		int j = MathHelper.floor_double(parent.posY);
		int k = MathHelper.floor_double(parent.posZ);

		if (worldObj.getBlockId(i, j, k) == Block.ice.blockID) {
			return true;
		}
		return false;
	}
}