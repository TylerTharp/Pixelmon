package pixelmon.attacks.specialAttacks;

import java.util.ArrayList;

import pixelmon.ChatHandler;
import pixelmon.attacks.Attack;
import pixelmon.attacks.statusEffects.SmackedDown;
import pixelmon.attacks.statusEffects.StatusEffectBase;
import pixelmon.attacks.statusEffects.StatusEffectType;
import pixelmon.entities.PixelmonEntityHelper;

import net.minecraft.src.ModLoader;

public class SmackDown extends SpecialAttackBase {

	public SmackDown() {
		super(SpecialAttackType.SmackDown, ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, Attack a, ArrayList<String> attackList) {
		target.getStatus().add(new SmackedDown());
		for (int i = 0; i < target.getStatus().size(); i++) {
			StatusEffectBase s = target.getStatus().get(i);
			if (s.type == StatusEffectType.Flying) {
				ChatHandler.sendChat(user.getOwner(), target.getOwner(), target.getName() + " got knocked out of the sky!");
				target.getStatus().remove(s);
			}
		}
		return false;
	}

}
