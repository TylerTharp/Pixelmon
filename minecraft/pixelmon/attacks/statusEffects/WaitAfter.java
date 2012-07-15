package pixelmon.attacks.statusEffects;

import java.util.ArrayList;

import pixelmon.ChatHandler;
import pixelmon.attacks.Attack;
import pixelmon.attacks.specialAttacks.SpecialAttackType;
import pixelmon.entities.PixelmonEntityHelper;

import net.minecraft.src.ModLoader;

public class WaitAfter extends StatusEffectBase {

	private int numTurns;
	private int turnCount;
	public WaitAfter(int value) {
		super(StatusEffectType.WaitAfter, false, true, false);
		numTurns = value;
	}

	@Override
	public void ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, ArrayList<String> attackList) {
		turnCount = 0;
		user.getStatus().add(this);
	}

	@Override
	public boolean canAttackThisTurn(PixelmonEntityHelper user, PixelmonEntityHelper target) {
		ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName()+ " is recharging!");
		return false;
	}

	@Override
	public double adjustDamage(Attack a, double damage, PixelmonEntityHelper user, PixelmonEntityHelper target, double crit) {
		return 0;
	}

	@Override
	public void turnTick(PixelmonEntityHelper user, PixelmonEntityHelper target) {
		turnCount++;
		if (turnCount == numTurns) user.getStatus().remove(this);
	}

}
