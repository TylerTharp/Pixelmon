package pixelmon.attacks.animations;

import net.minecraft.src.EntityLiving;
import net.minecraft.src.MathHelper;

public class AttackAnimationLeapForward implements IAttackAnimation {

	@Override
	public void doMove(EntityLiving user, EntityLiving target) {
		double d = target.posX - user.posX;
		double d1 = target.posZ - user.posZ;
		float f = MathHelper.sqrt_double(d * d + d1 * d1);
		user.motionX += (d / (double) f) * 0.5D * 0.80000001192092896D
				+ user.motionX * 0.20000000298023224D;
		user.motionZ += (d1 / (double) f) * 0.5D * 0.80000001192092896D
				+ user.motionZ * 0.20000000298023224D;
		user.motionY = 0.4;
	}

}
