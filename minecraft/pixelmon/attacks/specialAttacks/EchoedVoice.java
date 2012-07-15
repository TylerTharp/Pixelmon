package pixelmon.attacks.specialAttacks;

import java.util.ArrayList;

import pixelmon.attacks.Attack;
import pixelmon.entities.PixelmonEntityHelper;


public class EchoedVoice extends SpecialAttackBase {

	public EchoedVoice() {
		super(SpecialAttackType.EchoedVoice, ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, Attack a, ArrayList<String> attackList) {
		int power = 40;
		for(int i=attackList.size()-2; i >=0; i--){
			if (attackList.get(i) == a.attackName) power+=40;
			else break;
		}
		a.basePower = power;
		return false;
	}

}
