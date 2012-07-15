package pixelmon.attacks.attackModifiers;

import pixelmon.ChatHandler;
import pixelmon.attacks.Attack;
import pixelmon.entities.PixelmonEntityHelper;
import net.minecraft.src.ModLoader;

public class FlinchAttackModifier extends AttackModifierBase {

	public FlinchAttackModifier() {
		super(AttackModifierType.Flinch, ApplyStage.End, false);
	}

	@Override
	public boolean ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, Attack a) {
		if (checkChance()){
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), target.getName() + " flinched!");
			return true;
		}
		return false;
	}

}
