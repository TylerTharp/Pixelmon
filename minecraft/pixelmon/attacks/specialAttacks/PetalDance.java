package pixelmon.attacks.specialAttacks;

import java.util.ArrayList;
import java.util.Random;

import pixelmon.ChatHandler;
import pixelmon.attacks.Attack;
import pixelmon.attacks.attackEffects.EffectBase;
import pixelmon.attacks.statusEffects.Confusion;
import pixelmon.attacks.statusEffects.StatusEffectBase;
import pixelmon.attacks.statusEffects.StatusEffectType;
import pixelmon.entities.PixelmonEntityHelper;

import net.minecraft.src.DamageSource;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.ModLoader;

public class PetalDance extends MultiTurnSpecialAttackBase {

	public PetalDance() {
		super(MultiTurnSpecialAttackType.PetalDance, (new Random()).nextInt(2)+2);
	}

	@Override
	public boolean ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, Attack a, ArrayList<String> attackList) {
		if (!persists){
			persists = true;
			turnCounter=0;
		}
		turnCounter++;
		if (turnCounter == turnCount){
			persists = false;
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " became confused!");
			user.getStatus().add(new Confusion());
		}
		return false;
	}

	@Override
	public boolean cantMiss() {
		
		return false;
	}

}
