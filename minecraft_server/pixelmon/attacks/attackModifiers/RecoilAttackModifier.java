package pixelmon.attacks.attackModifiers;

import pixelmon.ChatHandler;
import pixelmon.attacks.Attack;
import pixelmon.attacks.attackEffects.EffectBase;
import pixelmon.entities.PixelmonEntityHelper;
import net.minecraft.src.DamageSource;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.ModLoader;

public class RecoilAttackModifier extends AttackModifierBase {

	
	public RecoilAttackModifier(int percent) {
		super(AttackModifierType.Recoil, ApplyStage.During, false);
		this.value = percent;
	}

	@Override
	public boolean ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, Attack a) {
		double crit = a.calcCriticalHit(null);
		for (EffectBase e : a.effects)
			crit = a.calcCriticalHit(e);

		int power = a.doDamageCalc(user, target, crit);
		if (a.attackCategory == Attack.ATTACK_STATUS)
			power = 0;
		double factor = ((double)value)/100;
		double dmg = ((double)power) * factor;
		user.attackEntityFrom(DamageSource.causeMobDamage((EntityLiving) user.getEntity()), (int)dmg);
		ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " took recoil damage!");
		return false;
	}

}
