package pixelmon.attacks.statusEffects;

import java.util.ArrayList;

import pixelmon.ChatHandler;
import pixelmon.attacks.*;
import pixelmon.entities.PixelmonEntityHelper;

import net.minecraft.src.DamageSource;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.ModLoader;

public class Burn extends StatusEffectBase {

	public Burn() {
		super(StatusEffectType.Burn, true, false, false);
	}

	@Override
	public void ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, ArrayList<String> attackList) {

		if (checkChance()) {
			if (target.getType().contains(Type.Fire)){
				ChatHandler.sendChat(user.getOwner(), target.getOwner(), "no effect!");
				return;
			}
			for (StatusEffectBase e : target.getStatus())
				if (e.type == StatusEffectType.Burn) {
					ChatHandler.sendChat(user.getOwner(), target.getOwner(), target.getName() + " is already burnt!");
					return;
				}
			target.getStatus().add(this);
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), target.getName() + " has been burnt!");
		}
	}

	@Override
	public void applyRepeatedEffect(PixelmonEntityHelper user, PixelmonEntityHelper target) {
		ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " is hurt by its burn!");
		user.attackEntityFrom(DamageSource.causeMobDamage((EntityLiving) user.getEntity()), (int) (((float) user.getMaxHealth()) / 8));
	}

	@Override
	public boolean ClearsOnBattleEnd() {
		return false;
	}
}
