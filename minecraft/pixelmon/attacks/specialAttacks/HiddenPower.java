package pixelmon.attacks.specialAttacks;

import java.util.ArrayList;

import pixelmon.attacks.Attack;
import pixelmon.attacks.Type;
import pixelmon.attacks.attackEffects.EffectBase;
import pixelmon.entities.PixelmonEntityHelper;

import net.minecraft.src.DamageSource;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.ModLoader;

public class HiddenPower extends SpecialAttackBase {

	public HiddenPower() {
		super(SpecialAttackType.HiddenPower, ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, Attack attack, ArrayList<String> attackList) {
		int a, b, c, d, e, f;

		a = user.getStats().IVs.HP % 2;
		b = user.getStats().IVs.Attack % 2;
		c = user.getStats().IVs.Defence % 2;
		d = user.getStats().IVs.Speed % 2;
		e = user.getStats().IVs.SpAtt % 2;
		f = user.getStats().IVs.SpDef % 2;

		double fedbca = 32 * f + 16 * e + 8 * d + 4 * c + 2 * b + a;
		int type = (int) Math.floor((fedbca) * 15f / 63f);

		if (type == 0)
			attack.attackType = Type.Fighting;
		else if (type == 1)
			attack.attackType = Type.Flying;
		else if (type == 2)
			attack.attackType = Type.Poison;
		else if (type == 3)
			attack.attackType = Type.Ground;
		else if (type == 4)
			attack.attackType = Type.Rock;
		else if (type == 5)
			attack.attackType = Type.Bug;
		else if (type == 6)
			attack.attackType = Type.Ghost;
		else if (type == 7)
			attack.attackType = Type.Steel;
		else if (type == 8)
			attack.attackType = Type.Fire;
		else if (type == 9)
			attack.attackType = Type.Water;
		else if (type == 10)
			attack.attackType = Type.Grass;
		else if (type == 11)
			attack.attackType = Type.Electric;
		else if (type == 12)
			attack.attackType = Type.Psychic;
		else if (type == 13)
			attack.attackType = Type.Ice;
		else if (type == 14)
			attack.attackType = Type.Dragon;
		else if (type == 15)
			attack.attackType = Type.Dark;

		boolean stab = false;
		for (Type t : user.getType()) {
			if (t == attack.attackType) {
				stab = true;
				break;
			}
		}

		int u = 0, v = 0, w = 0, x = 0, y = 0, z = 0;
		int tmp;
		tmp = user.getStats().IVs.HP % 4;
		if (tmp == 2 || tmp == 3)
			u = 1;
		tmp = user.getStats().IVs.Attack % 4;
		if (tmp == 2 || tmp == 3)
			v = 1;
		tmp = user.getStats().IVs.Defence % 4;
		if (tmp == 2 || tmp == 3)
			w = 1;
		tmp = user.getStats().IVs.Speed % 4;
		if (tmp == 2 || tmp == 3)
			x = 1;
		tmp = user.getStats().IVs.SpAtt % 4;
		if (tmp == 2 || tmp == 3)
			y = 1;
		tmp = user.getStats().IVs.SpDef % 4;
		if (tmp == 2 || tmp == 3)
			z = 1;

		attack.basePower = (int) Math.floor(((double) (u + 2 * v + 4 * w + 8 * x + 16 * y + 32 * z)) * 40 / 63 + 30);
		return false;
	}
}
