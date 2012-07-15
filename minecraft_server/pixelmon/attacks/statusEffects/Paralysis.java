package pixelmon.attacks.statusEffects;

import java.util.ArrayList;

import pixelmon.ChatHandler;
import pixelmon.attacks.Attack;
import pixelmon.attacks.Type;
import pixelmon.entities.PixelmonEntityHelper;

import net.minecraft.src.ModLoader;
import net.minecraft.src.mod_Pixelmon;

public class Paralysis extends StatusEffectBase {

	public Paralysis() {
		super(StatusEffectType.Paralysis, false, true, false);
	}

	@Override
	public void ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, ArrayList<String> attackList) {
		if (checkChance()) {
			for (StatusEffectBase e : target.getStatus())
				if (e.type == StatusEffectType.Paralysis) {
					ChatHandler.sendChat(user.getOwner(), target.getOwner(), target.getName() + " is already paralyzed!");
					return;
				}

			target.getStatus().add(this);
			target.getBattleStats().setIsParalyzed();
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), target.getName() + " is paralyzed!");

		} else
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " failed!");

	}

	@Override
	public boolean canAttackThisTurn(PixelmonEntityHelper user, PixelmonEntityHelper target) {
		if (mod_Pixelmon.getRandomNumberBetween(0, 100) <= 25) {
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), target.getName() + " is paralyzed!");
			return false;
		} else {
			return true;
		}
	}
}
