package pixelmon.attacks.specialAttacks;

import java.util.ArrayList;

import pixelmon.attacks.Attack;
import pixelmon.attacks.EffectType;
import pixelmon.attacks.attackEffects.EffectBase;
import pixelmon.attacks.attackEffects.EffectParser;
import pixelmon.attacks.attackEffects.EffectBase.ApplyStage;
import pixelmon.attacks.attackModifiers.ChanceModifier;
import pixelmon.attacks.attackModifiers.ModifierBase;
import pixelmon.attacks.attackModifiers.ModifierType;
import pixelmon.entities.PixelmonEntityHelper;


public abstract class MultiTurnSpecialAttackBase extends EffectBase {
		public int turnCount=2;
		public int turnCounter=0;

		private MultiTurnSpecialAttackType mtsatype;
		
		public MultiTurnSpecialAttackBase(MultiTurnSpecialAttackType type, int turnCount) {
			super(EffectType.MultiTurnSpecialAttack, ApplyStage.During, true);
			this.turnCount = turnCount;
			this.mtsatype = type;
		}

		public abstract boolean ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, Attack a, ArrayList<String> attackList);
		
		@Override
		public void ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, ArrayList<String> attackList) {
		}
		
		public abstract boolean cantMiss();
}
