package pixelmon.attacks.statusEffects;

import java.util.ArrayList;

import pixelmon.ChatHandler;
import pixelmon.attacks.Attack;
import pixelmon.attacks.EffectType;
import pixelmon.attacks.attackEffects.EffectBase;
import pixelmon.attacks.attackEffects.StatsEffect;
import pixelmon.attacks.attackModifiers.ModifierBase;
import pixelmon.attacks.attackModifiers.ModifierType;
import pixelmon.entities.PixelmonEntityHelper;

import net.minecraft.src.ModLoader;

public class Mist extends StatusEffectBase {

	int turnCount =0;
	public Mist() {
		super(StatusEffectType.Mist, false, true, false);
	}

	@Override
	public void ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, ArrayList<String> attackList) {
		if (checkChance()) {
			for (StatusEffectBase e : user.getStatus())
				if (e.type == StatusEffectType.Paralysis) {
					ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " is already surrounded by mist!");
					return;
				}

			user.getStatus().add(this);
			turnCount=0;
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " creates a cloud of mist!");

		} else
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " failed!");
	}

	@Override
	public boolean stopsIncomingAttack(PixelmonEntityHelper user, PixelmonEntityHelper target, Attack a) {
		if (a.attackCategory ==  Attack.ATTACK_STATUS){
			for (EffectBase e:a.effects){
				if (e.effectType == EffectType.Stats){
					for (ModifierBase m: (((StatsEffect)e).modifiers)){
						if (m.type == ModifierType.User) return false;
					}
					ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " is protected by the mist!");
					return true;
				}
			}
		}
			
		return false;
	}

	@Override
	public void turnTick(PixelmonEntityHelper user, PixelmonEntityHelper target) {
		turnCount++;
		if (turnCount==5) {
			user.getStatus().remove(this);
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), "The mist wore off!");
		}
	}

}
