package pixelmon.attacks.attackModifiers;

import pixelmon.ChatHandler;
import pixelmon.attacks.Attack;
import pixelmon.attacks.attackEffects.EffectBase;
import pixelmon.entities.PixelmonEntityHelper;
import net.minecraft.src.DamageSource;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.ModLoader;

public class MultipleHitAttackModifier extends AttackModifierBase {

	private int count = 0;

	public MultipleHitAttackModifier(int value1, int value2) {
		super(AttackModifierType.MultipleHit, ApplyStage.During, false);
		value = value1;
		this.value2 = value2;
	}

	public boolean RepeatsAttack() {
		if (value2 == -1) {
			if (count > value)
				return false;
			count++;
			return true;
		} else {
			if (count > value2 - value)
				return false;
			count++;
			return true;
		}
	}

	@Override
	public boolean ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, Attack a) {
		while (RepeatsAttack()) {
			double crit = a.calcCriticalHit(null);
			for (EffectBase e : a.effects)
				crit = a.calcCriticalHit(e);

			int power = a.doDamageCalc(user, target, crit);
			if (a.attackCategory == Attack.ATTACK_STATUS)
				power = 0;
			target.attackEntityFrom(
					DamageSource.causeMobDamage((EntityLiving) user.getEntity()), power);
			a.doMove((EntityLiving)user.getEntity(), (EntityLiving)target.getEntity());
			if (crit > 1)
				ChatHandler.sendChat(user.getOwner(), target.getOwner(), "Critical Hit!");
		}
		ChatHandler.sendChat(user.getOwner(), target.getOwner(), user
				.getName() + " attacked " + count + " times!");
		return true;
	}


}
