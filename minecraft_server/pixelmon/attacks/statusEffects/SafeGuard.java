package pixelmon.attacks.statusEffects;

import java.util.ArrayList;

import pixelmon.ChatHandler;
import pixelmon.attacks.Attack;
import pixelmon.attacks.Type;
import pixelmon.entities.PixelmonEntityHelper;

import net.minecraft.src.ModLoader;

public class SafeGuard extends StatusEffectBase {

	private int effectTurns;
	public SafeGuard() {
		super(StatusEffectType.SafeGuard, false, false, true);
	}

	@Override
	public void ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, ArrayList<String> attackList) {
		if (checkChance()) {
			for (StatusEffectBase e : user.getStatus())
				if (e.type == StatusEffectType.SafeGuard) {
					ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " already has a safeguard!");
					return;
				}

			target.getStatus().add(this);
			effectTurns = 5;
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " is looking a bit guarded!");
		} else
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " failed!");
	}

	@Override
	public boolean stopsStatusChange() {
		return true;
	}

	@Override
	public void turnTick(PixelmonEntityHelper user, PixelmonEntityHelper target) {
		if (effectTurns == 0) {
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + "'s Safeguard wears off!");
			user.getStatus().remove(this);
		}
		effectTurns--;
	}
}
