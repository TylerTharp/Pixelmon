package pixelmon.AI;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import pixelmon.entities.BaseEntityPixelmon;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityAINearestAttackableTargetSorter;
import net.minecraft.src.EntityAITarget;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;

public class EntityAINearestPixelmonTarget extends EntityAIPixelmonTarget {
	EntityLiving targetEntity;
	int targetChance;
	int aggression;
	private EntityAINearestPixelmonTargetSorter field_48387_g;

	public EntityAINearestPixelmonTarget(EntityLiving par1EntityLiving, float par3, int par4, boolean par5) {
		this(par1EntityLiving, par3, par4, par5, false);
	}

	public EntityAINearestPixelmonTarget(EntityLiving par1EntityLiving, float par3, int par4, boolean par5, boolean par6) {
		super(par1EntityLiving, par3, par5, par6);
		this.targetDistance = par3;
		this.targetChance = par4;
		this.field_48387_g = new EntityAINearestPixelmonTargetSorter(this, par1EntityLiving);
		this.setMutexBits(1);
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute() {
		if (this.targetChance > 0 && this.taskOwner.getRNG().nextInt(this.targetChance) != 0) {
			return false;
		} else {
			
			EntityPlayer var1 = this.taskOwner.worldObj.getClosestPlayerToEntity(this.taskOwner, (double) this.targetDistance);
			if (this.func_48376_a(var1, true)) {
				this.targetEntity = var1;
				return true;
			}

			List var5 = this.taskOwner.worldObj.getEntitiesWithinAABB(BaseEntityPixelmon.class,
					this.taskOwner.boundingBox.expand((double) this.targetDistance, 4.0D, (double) this.targetDistance));
			Collections.sort(var5, this.field_48387_g);
			Iterator var2 = var5.iterator();

			while (var2.hasNext()) {
				Entity var3 = (Entity) var2.next();
				EntityLiving var4 = (EntityLiving) var3;

				if (this.func_48376_a(var4, false)) {
					this.targetEntity = var4;
					return true;
				}
			}
		}

		return false;

	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public void startExecuting() {
		if (targetEntity instanceof EntityPlayer) {
			this.taskOwner.setAttackTarget(targetEntity);
		}
		this.taskOwner.setAttackTarget(this.targetEntity);
		super.startExecuting();
	}

}
