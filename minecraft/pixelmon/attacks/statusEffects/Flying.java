package pixelmon.attacks.statusEffects;

import java.util.ArrayList;

import pixelmon.attacks.Attack;
import pixelmon.entities.PixelmonEntityHelper;


public class Flying extends StatusEffectBase {

	int turnCount;
	public Flying() {
		super(StatusEffectType.Flying, false, false, true);
	}

	@Override
	public void ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, ArrayList<String> attackList) {
		turnCount=0;
	}

	@Override
	public boolean stopsSwitching() {
		return true;
	}

	@Override
	public boolean stopsIncomingAttack(PixelmonEntityHelper user, PixelmonEntityHelper target, Attack a) {
		if (!a.attackName.equalsIgnoreCase("Smack Down"))
			return true;
		return false;
	}
	
	@Override
	public boolean stopsStatusChange() {
		return true;
	}
}
