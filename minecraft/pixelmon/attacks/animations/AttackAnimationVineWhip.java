package pixelmon.attacks.animations;

import pixelmon.entities.BaseEntityPixelmon;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityReddustFX;
import net.minecraft.src.ModLoader;

public class AttackAnimationVineWhip implements IAttackAnimation
{
	public void doMove(EntityLiving user, EntityLiving target)
	{
        for(int i = 0; i < 10; i++)
        {
        	EntityReddustFX entity = new EntityReddustFX(user.worldObj, user.posX, user.posY, user.posZ, 0.1F, 1, 0);
        	entity.setVelocity(user.worldObj.rand.nextFloat() / 5, user.worldObj.rand.nextFloat() / 5, user.worldObj.rand.nextFloat() / 5);
        	ModLoader.getMinecraftInstance().effectRenderer.addEffect(entity);
        }
	}
}