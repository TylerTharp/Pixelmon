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

public class Venoshock extends SpecialAttackBase {

	public Venoshock() {
		super(SpecialAttackType.Venoshock, ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, Attack a, ArrayList<String> attackList) {
		a.basePower = 65;
		boolean isPoisoned=false;
		for(StatusEffectBase e:target.getStatus())
			if (e.type == StatusEffectType.Poison || e.type == StatusEffectType.PoisonBadly) isPoisoned = true;

		if (isPoisoned) a.basePower=130;
		return false;
	}

}
