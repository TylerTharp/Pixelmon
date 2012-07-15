package pixelmon;

import pixelmon.entities.BaseEntityPixelmon;
import net.minecraft.src.EntitySmokeFX;
import net.minecraft.src.ModLoader;

public class AttackBite 
{

	public AttackBite(BaseEntityPixelmon attacker) 
	{
//		super(attacker);
//		attackType = TYPE_DARK;
//		attackKind = ATTACK_PHYSICAL;
//		attackName = "Bite";
//		basePower = 40;
//		rangeMax = 5;
	}
	
	protected void doMove(BaseEntityPixelmon user, BaseEntityPixelmon target)
	{
//		super.doMove(user, target);
        for(int i = 0; i < 10; i++)
        {
        	EntitySmokeFX entity = new EntitySmokeFX(user.worldObj, user.posX, user.posY, user.posZ, 0, 0, 0);
        	entity.setVelocity(user.worldObj.rand.nextFloat() / 5, user.worldObj.rand.nextFloat() / 5, user.worldObj.rand.nextFloat() / 5);
        	ModLoader.getMinecraftInstance().effectRenderer.addEffect(entity);
        }

	}
	
	
}