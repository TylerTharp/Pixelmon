package net.minecraft.src;

public class EntityAIOwnerHurtTarget extends EntityAITarget
{
    EntityTameable field_48304_a;
    EntityLiving field_48303_b;

    public EntityAIOwnerHurtTarget(EntityTameable par1EntityTameable)
    {
        super(par1EntityTameable, 32.0F, false);
        this.field_48304_a = par1EntityTameable;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (!this.field_48304_a.isTamed())
        {
            return false;
        }
        else
        {
            EntityLiving var1 = this.field_48304_a.getOwner();

            if (var1 == null)
            {
                return false;
            }
            else
            {
                this.field_48303_b = var1.getLastAttackingEntity();
                return this.func_48284_a(this.field_48303_b, false);
            }
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.taskOwner.setAttackTarget(this.field_48303_b);
        super.startExecuting();
    }
}
