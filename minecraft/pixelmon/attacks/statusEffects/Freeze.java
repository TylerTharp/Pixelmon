package pixelmon.attacks.statusEffects;

import java.util.ArrayList;

import pixelmon.ChatHandler;
import pixelmon.attacks.Attack;
import pixelmon.attacks.Type;
import pixelmon.entities.PixelmonEntityHelper;

import net.minecraft.src.ModLoader;
import net.minecraft.src.mod_Pixelmon;

public class Freeze extends StatusEffectBase {

	public Freeze() {
		super(StatusEffectType.Freeze, false, true, false);
	}

	@Override
	public void ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, ArrayList<String> attackList) {
		if (checkChance()) {
			for (StatusEffectBase e : target.getStatus())
				if (e.type == StatusEffectType.Freeze) {
					ChatHandler.sendChat(user.getOwner(), target.getOwner(), target.getName() + " is already frozen!");
					return;
				}
			if (target.getType().contains(Type.Ice)){
				ChatHandler.sendChat(user.getOwner(), target.getOwner(), "no effect!");
				return;
			}
			target.getStatus().add(this);
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), target.getName() + " has been frozen solid");
		} else
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " failed!");

	}

	@Override
	public boolean canAttackThisTurn(PixelmonEntityHelper user, PixelmonEntityHelper target) {
		if (mod_Pixelmon.getRandomNumberBetween(0, 100) <= 20) {
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " breaks free from the ice!");
			user.getStatus().remove(this);
			return true;
		} else {
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " is frozen solid!");
			return false;
		}
	}

	@Override
	public boolean stopsIncomingAttack(PixelmonEntityHelper user, PixelmonEntityHelper target, Attack a) {
		return false;
	}

	public boolean ClearsOnBattleEnd() {
		return false;
	}
}
