package pixelmon.AI;

import pixelmon.entities.pixelmon.BaseEntityPixelmon;
import pixelmon.entities.pixelmon.helpers.IHaveHelper;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityAIBase;
import net.minecraft.src.EntityPlayer;

public class EntityAIStartBattle extends EntityAIBase {
	private IHaveHelper theEntity;
	public EntityAIStartBattle(IHaveHelper theEntity){
		this.theEntity = theEntity;
	}
	@Override
	public boolean shouldExecute() {
		if (((BaseEntityPixelmon)theEntity).getAttackTarget() == null) return false;
		if (((BaseEntityPixelmon)theEntity).getAttackTarget() instanceof EntityPlayer) 
			return true;
		if (((BaseEntityPixelmon)theEntity).getAttackTarget().getDistanceSqToEntity((Entity)this.theEntity) < 2){
			theEntity.StartBattle(((BaseEntityPixelmon)((BaseEntityPixelmon)theEntity).getAttackTarget()).helper);
			return true;
		}
		return false;
	}

}
