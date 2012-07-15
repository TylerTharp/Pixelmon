package pixelmon.attacks.statusEffects;

import java.util.ArrayList;

import pixelmon.ChatHandler;
import pixelmon.attacks.*;
import pixelmon.entities.PixelmonEntityHelper;

import net.minecraft.src.DamageSource;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.ModLoader;
import net.minecraft.src.mod_Pixelmon;

public class Confusion extends StatusEffectBase {
	private int effectTurns = -1;

	public Confusion() {
		super(StatusEffectType.Confusion, false, true, false);
	}

	@Override
	public void ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, ArrayList<String> attackList) {
		if (checkChance()) {
			for (StatusEffectBase e : target.getStatus())
				if (e.type == StatusEffectType.Confusion) {
					ChatHandler.sendChat(user.getOwner(), target.getOwner(), target.getName() + " is already confused!");
					return;
				}
			target.getStatus().add(this);
			effectTurns = mod_Pixelmon.getRandomNumberBetween(1, 4);
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), target.getName() + " has become confused!");
		} else
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " failed!");
	}

	@Override
	public boolean canAttackThisTurn(PixelmonEntityHelper user, PixelmonEntityHelper target) {
		ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " is confused...");
		if (mod_Pixelmon.getRandomNumberBetween(0, 100) <= 50) {
			user.attackEntityFrom(DamageSource.causeMobDamage((EntityLiving) user.getEntity()), calculateConfusionDamage(user));
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " hurt itself in its confusion");
			return false;
		}
		return true;
	}

	private int calculateConfusionDamage(PixelmonEntityHelper user) {
		double stab = 1;
		double type = Type.getTotalEffectiveness(user.getType(), Type.Normal);
		double critical = 1;
		double rand = ((double) mod_Pixelmon.getRandomNumberBetween(85, 100)) / 100;
		double modifier = stab * type * critical * rand;
		double attack = ((double) user.getStats().Attack) * ((double) user.getBattleStats().AttackModifier) / 100;
		double defence = ((double) user.getStats().Defence) * ((double) user.getBattleStats().DefenceModifier) / 100;
		double Damage = ((2 * user.getLvl().getLevel() + 10) / 250 * (attack / defence) * 40 + 2) * modifier;

		return (int) Math.round(Damage);
	}

	@Override
	public void turnTick(PixelmonEntityHelper user, PixelmonEntityHelper target) {
		if (effectTurns == 0) {
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " snaps out of confusion!");
			user.getStatus().remove(this);
		}
		effectTurns--;
	}
}
