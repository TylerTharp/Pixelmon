package pixelmon.attacks.statusEffects;

import java.util.ArrayList;

import pixelmon.attacks.Attack;
import pixelmon.attacks.Type;
import pixelmon.entities.PixelmonEntityHelper;

import net.minecraft.src.mod_Pixelmon;

public class SmackedDown extends StatusEffectBase {

	public SmackedDown() {
		super(StatusEffectType.SmackedDown, false, true, false);
	}

	@Override
	public void ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, ArrayList<String> attackList) {}
	
	@Override
	public double adjustDamage(Attack a, double damage, PixelmonEntityHelper user, PixelmonEntityHelper target, double crit) {
		double stab = 1;
		if (a.STAB)
			stab = 1.5;
		double type = Type.getTotalEffectiveness(user.getType(), a.attackType);
		if (a.attackType == Type.Ground && user.getType().contains(Type.Flying))
			type = 1;
		double critical = crit;
		double rand = ((double) mod_Pixelmon.getRandomNumberBetween(85, 100)) / 100;
		double modifier = stab * type * critical * rand;
		double attack = 0, defence = 0;
		if (a.attackCategory == Attack.ATTACK_PHYSICAL) {
			attack = ((double) target.getStats().Attack)
					* ((double) target.getBattleStats().AttackModifier) / 100;
			defence = ((double) user.getStats().Defence)
					* ((double) user.getBattleStats().DefenceModifier) / 100;
		} else if (a.attackCategory == Attack.ATTACK_SPECIAL) {
			attack = ((double) target.getStats().SpecialAttack)
					* ((double) target.getBattleStats().SpecialAttackModifier)
					/ 100;
			defence = ((double) user.getStats().SpecialDefence)
					* ((double) user.getBattleStats().SpecialDefenceModifier)
					/ 100;
		}
		double Damage = ((2 * ((float)target.getLvl().getLevel()) + 10) / 250
				* (attack / defence) * a.basePower + 2)
				* modifier;

		return Damage;
	}
}
