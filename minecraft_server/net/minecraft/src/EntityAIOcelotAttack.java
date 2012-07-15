package net.minecraft.src;

public class EntityAIOcelotAttack extends EntityAIBase
{
    World theWorld;
    EntityLiving theEntity;
    EntityLiving field_48170_c;
    int field_48168_d = 0;

    public EntityAIOcelotAttack(EntityLiving par1EntityLiving)
    {
        this.theEntity = par1EntityLiving;
        this.theWorld = par1EntityLiving.worldObj;
        this.setMutexBits(3);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        EntityLiving var1 = this.theEntity.getAttackTarget();

        if (var1 == null)
        {
            return false;
        }
        else
        {
            this.field_48170_c = var1;
            return true;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return !this.field_48170_c.isEntityAlive() ? false : (this.theEntity.getDistanceSqToEntity(this.field_48170_c) > 225.0D ? false : !this.theEntity.getNavigator().noPath() || this.shouldExecute());
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        this.field_48170_c = null;
        this.theEntity.getNavigator().clearPathEntity();
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        this.theEntity.getLookHelper().setLookPositionWithEntity(this.field_48170_c, 30.0F, 30.0F);
        double var1 = (double)(this.theEntity.width * 2.0F * this.theEntity.width * 2.0F);
        double var3 = this.theEntity.getDistanceSq(this.field_48170_c.posX, this.field_48170_c.boundingBox.minY, this.field_48170_c.posZ);
        float var5 = 0.23F;

        if (var3 > var1 && var3 < 16.0D)
        {
            var5 = 0.4F;
        }
        else if (var3 < 225.0D)
        {
            var5 = 0.18F;
        }

        this.theEntity.getNavigator().tryMoveToEntityLiving(this.field_48170_c, var5);
        this.field_48168_d = Math.max(this.field_48168_d - 1, 0);

        if (var3 <= var1)
        {
            if (this.field_48168_d <= 0)
            {
                this.field_48168_d = 20;
                this.theEntity.attackEntityAsMob(this.field_48170_c);
            }
        }
    }
}
