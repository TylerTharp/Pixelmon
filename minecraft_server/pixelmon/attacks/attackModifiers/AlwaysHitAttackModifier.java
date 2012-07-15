package pixelmon.attacks.attackModifiers;

import pixelmon.attacks.Attack;
import pixelmon.entities.PixelmonEntityHelper;

public class AlwaysHitAttackModifier extends AttackModifierBase {

	public AlwaysHitAttackModifier() {
		super(AttackModifierType.AlwaysHit, ApplyStage.Start, false);
	}

	@Override
	public boolean ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, Attack a) {
		return false;
	}
	
	public boolean hasSpecialAccuracyEffect() {
		return true;
	}

	public double getAccuracy(PixelmonEntityHelper user, PixelmonEntityHelper target) {
		return 100;
	}

}
