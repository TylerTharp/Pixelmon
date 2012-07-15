package pixelmon.attacks.animations;

import pixelmon.entities.BaseEntityPixelmon;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.ModLoader;

public class AttackAnimationEmber implements IAttackAnimation
{

	public void doMove(EntityLiving user, EntityLiving target)
	{
        for(int i = 0; i < 10; i++)
        {
//        	EntityFlameFX entity = new EntityFlameFX(user.worldObj, user.posX, user.posY, user.posZ, 0, 0, 0);
//        	entity.setVelocity(user.worldObj.rand.nextFloat() / 5, user.worldObj.rand.nextFloat() / 5, user.worldObj.rand.nextFloat() / 5);
//        	ModLoader.getMinecraftInstance().effectRenderer.addEffect(entity);
        }
	}
}