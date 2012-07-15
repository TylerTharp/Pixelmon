package pixelmon.attacks.animations;

import net.minecraft.src.EntityLiving;

public interface IAttackAnimation 
{
	public void doMove(EntityLiving user, EntityLiving target);
}
