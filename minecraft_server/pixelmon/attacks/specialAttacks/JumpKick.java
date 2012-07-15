package pixelmon.attacks.specialAttacks;

import java.util.ArrayList;

import pixelmon.ChatHandler;
import pixelmon.attacks.Attack;
import pixelmon.attacks.attackEffects.EffectBase;
import pixelmon.attacks.statusEffects.StatusEffectBase;
import pixelmon.attacks.statusEffects.StatusEffectType;
import pixelmon.entities.PixelmonEntityHelper;

import net.minecraft.src.DamageSource;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.ModLoader;

public class JumpKick extends SpecialAttackBase {

	public JumpKick() {
		super(SpecialAttackType.JumpKick, ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, Attack a, ArrayList<String> attackList) {
		return false;
	}
	
	@Override
	public void ApplyMissEffect(PixelmonEntityHelper user, PixelmonEntityHelper target){
		user.attackEntityFrom(DamageSource.causeMobDamage((EntityLiving) user.getEntity()), user.getHealth()/2);
		ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() +" kept on going and hurt itself trying to land!");
	}
}
