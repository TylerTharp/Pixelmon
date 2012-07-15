package pixelmon.attacks.attackEffects;

import java.util.ArrayList;

import pixelmon.ChatHandler;
import pixelmon.attacks.EffectType;
import pixelmon.attacks.statusEffects.StatusEffectBase;
import pixelmon.attacks.statusEffects.StatusEffectType;
import pixelmon.entities.PixelmonEntityHelper;

import net.minecraft.src.ModLoader;

public class RemoveEffect extends EffectBase {
	StatusEffectType removeType;
	public RemoveEffect(StatusEffectType removeType) {
		super(EffectType.Remove, ApplyStage.End, false);
		this.removeType= removeType;
	}

	@Override
	public void ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, ArrayList<String> attackList) {
		for (StatusEffectBase e:target.getStatus()){
			if (e.type == removeType) {
				target.getStatus().remove(e);
				ChatHandler.sendChat(user.getOwner(), target.getOwner(), user
						.getName() + " removed " + target.getName() + "'s "+ e.type.toString() +"!");
			}
			
		}
	}

	@Override
	public boolean cantMiss() {
		return false;
	}

}
