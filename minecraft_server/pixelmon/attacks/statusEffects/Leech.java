package pixelmon.attacks.statusEffects;

import java.util.ArrayList;

import pixelmon.ChatHandler;
import pixelmon.attacks.Attack;
import pixelmon.attacks.Type;
import pixelmon.entities.PixelmonEntityHelper;

import net.minecraft.src.DamageSource;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.ModLoader;

public class Leech extends StatusEffectBase {

	private int effectTurns;

	public Leech() {
		super(StatusEffectType.Leech, true, false, false);
	}

	@Override
	public void ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, ArrayList<String> attackList) {
		if (checkChance()) {
			for (StatusEffectBase e : target.getStatus())
				if (e.type == StatusEffectType.Leech) {
					ChatHandler.sendChat(user.getOwner(), target.getOwner(), target.getName() + " is already seeded!");
					return;
				}
			target.getStatus().add(this);
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " has planted a seed!");
		} else
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " failed!");
	}

	@Override
	public void applyRepeatedEffect(PixelmonEntityHelper user, PixelmonEntityHelper target) {
		ChatHandler.sendChat(user.getOwner(), target.getOwner(), target.getName() + " drains health from " + user.getName());
		int dmg = (int) (((float) user.getMaxHealth()) / 8);
		if (user.getHealth() < 16)
			dmg = 1;
		user.attackEntityFrom(DamageSource.causeMobDamage((EntityLiving) user.getEntity()), dmg);
		target.setHealth(target.getHealth() + dmg);
	}
}
