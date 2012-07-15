package pixelmon.attacks.statusEffects;

import java.util.ArrayList;

import pixelmon.ChatHandler;
import pixelmon.attacks.Attack;
import pixelmon.entities.PixelmonEntityHelper;

import net.minecraft.src.ModLoader;

public class LightScreen extends StatusEffectBase {
	private int effectTurns = -1;

	public LightScreen() {
		super(StatusEffectType.LightScreen, false, false, true);
	}

	@Override
	public void ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, ArrayList<String> attackList) {

		if (checkChance()) {
			if (user.getStatus().contains(this))
				ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " already has a lightscreen!");
			target.getStatus().add(this);
			effectTurns = 5;
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " has put up a screen of shimmering light!");
		} else
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " failed!");
	}

	@Override
	public double adjustDamage(Attack a, double damage, PixelmonEntityHelper user, PixelmonEntityHelper target, double crit) {
		if (a.attackCategory == Attack.ATTACK_SPECIAL)
			return damage / 2;
		return damage;
	}

	@Override
	public void turnTick(PixelmonEntityHelper user, PixelmonEntityHelper target) {
		if (effectTurns == 0) {
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + "'s Lightscreen wears off!");
			user.getStatus().remove(this);
		}
		effectTurns--;
	}
}
