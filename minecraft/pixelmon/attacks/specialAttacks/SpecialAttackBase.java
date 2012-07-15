package pixelmon.attacks.specialAttacks;

import java.util.ArrayList;

import pixelmon.attacks.Attack;
import pixelmon.attacks.EffectType;
import pixelmon.attacks.attackEffects.EffectBase;
import pixelmon.attacks.attackEffects.EffectBase.ApplyStage;
import pixelmon.attacks.attackModifiers.AttackModifierType;
import pixelmon.entities.PixelmonEntityHelper;


public abstract class SpecialAttackBase extends EffectBase{
	public SpecialAttackType type;
	public int value = -1;
	public int value2 = -1;
	
	public SpecialAttackBase(SpecialAttackType type, ApplyStage a, boolean persists){
		super(EffectType.SpecialAttack, a, persists);
		this.type = type;
	}

	public abstract boolean ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, Attack a, ArrayList<String> attackList);
	
	@Override
	public void ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, ArrayList<String> attackList) {
	}
	
	public boolean cantMiss() {
		return false;
	}
}
