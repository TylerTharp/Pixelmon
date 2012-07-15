package pixelmon.attacks.attackModifiers;

import java.util.ArrayList;

import pixelmon.attacks.Attack;
import pixelmon.attacks.EffectType;
import pixelmon.attacks.attackEffects.EffectBase;
import pixelmon.entities.PixelmonEntityHelper;


public abstract class AttackModifierBase extends EffectBase{
	public AttackModifierType type;
	public int value = -1;
	public int value2 = -1;
	
	public AttackModifierBase(AttackModifierType type, ApplyStage a, boolean persists){
		super(EffectType.AttackModifier, a, persists);
		this.type = type;
	}

	public abstract boolean ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, Attack a);
	
	@Override
	public void ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, ArrayList<String> attackList) {
	}
	
	public boolean cantMiss() {
		return false;
	}
}
