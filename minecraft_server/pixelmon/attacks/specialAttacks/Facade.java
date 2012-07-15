package pixelmon.attacks.specialAttacks;

import java.util.ArrayList;

import pixelmon.attacks.Attack;
import pixelmon.attacks.attackEffects.EffectBase;
import pixelmon.attacks.statusEffects.StatusEffectBase;
import pixelmon.attacks.statusEffects.StatusEffectType;
import pixelmon.entities.PixelmonEntityHelper;

import net.minecraft.src.DamageSource;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.ModLoader;

public class Facade extends SpecialAttackBase {

	public Facade() {
		super(SpecialAttackType.Facade, ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, Attack a, ArrayList<String> attackList) {
		a.basePower = 70;
		for (StatusEffectBase e :target.getStatus()){
			if (e.type == StatusEffectType.Burn || e.type == StatusEffectType.Poison || e.type == StatusEffectType.PoisonBadly || e.type == StatusEffectType.Paralysis)
				a.basePower = 140;
		}
		return false;
	}
}
