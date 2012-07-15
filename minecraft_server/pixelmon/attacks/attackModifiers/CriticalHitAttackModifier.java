package pixelmon.attacks.attackModifiers;

import pixelmon.attacks.Attack;
import pixelmon.entities.PixelmonEntityHelper;

public class CriticalHitAttackModifier extends AttackModifierBase {

	public CriticalHitAttackModifier(int value) {
		super(AttackModifierType.CriticalHit, ApplyStage.During, false);
		this.value = value;
	}

	@Override
	public boolean ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, Attack a) {
		return false;
	}

}
