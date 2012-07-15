package pixelmon.attacks.attackEffects;

import java.util.ArrayList;

import pixelmon.attacks.EffectType;
import pixelmon.attacks.attackModifiers.*;
import pixelmon.attacks.specialAttacks.*;
import pixelmon.attacks.statusEffects.*;


public class EffectParser {

	private String effectTypeString;
	private String effectValueString;

	private EffectType effectType;
	@SuppressWarnings("unused")
	private ValueType valueType;

	private int Value = 0;
	private int Value2 = 0;
	private String ValueString;

	private boolean hasModifier;
	private ArrayList<ModifierStoreClass> modifierStore = new ArrayList<ModifierStoreClass>();

	public EffectBase ParseEffect(String effect) {
		if (effect.equalsIgnoreCase("None")) {
			effectType = EffectType.None;
			return null;
		}
		String[] splits = effect.split(":");
		String[] effectSplits = splits[0].split("=");
		effectTypeString = effectSplits[0];
		if (effectSplits.length > 1)
			effectValueString = effectSplits[1];
		else
			valueType = ValueType.None;

		if (splits.length > 1) {
			hasModifier = true;
			String[] modifierStrings = splits[1].split(":");
			for (String s : modifierStrings) {
				ModifierStoreClass m = new ModifierStoreClass();
				String[] modifierSplits = s.split("=");
				m.modifierTypeString = modifierSplits[0];
				if (modifierSplits.length > 1)
					m.modifierValueString = modifierSplits[1];
				else
					m.modifierValueType = ValueType.None;
				modifierStore.add(m);
			}

		} else
			hasModifier = false;

		calcEffectType();

		calcValue();

		return getEffect();
	}

	private EffectBase getEffect() {
		EffectBase effect = null;
		if (effectType == EffectType.Status){
			if (StatusEffectType.getStatusEffect(effectTypeString) == StatusEffectType.Burn)
				effect = new Burn();
			if (StatusEffectType.getStatusEffect(effectTypeString) == StatusEffectType.Confusion)
				effect = new Confusion();
			if (StatusEffectType.getStatusEffect(effectTypeString) == StatusEffectType.FireSpin)
				effect = new FireSpin();
			if (StatusEffectType.getStatusEffect(effectTypeString) == StatusEffectType.Flee)
				effect = new Flee();
			if (StatusEffectType.getStatusEffect(effectTypeString) == StatusEffectType.Freeze)
				effect = new Freeze();
			if (StatusEffectType.getStatusEffect(effectTypeString) == StatusEffectType.Infatuated)
				effect = new Infatuated();
			if (StatusEffectType.getStatusEffect(effectTypeString) == StatusEffectType.Leech)
				effect = new Leech();
			if (StatusEffectType.getStatusEffect(effectTypeString) == StatusEffectType.LightScreen)
				effect = new LightScreen();
			if (StatusEffectType.getStatusEffect(effectTypeString) == StatusEffectType.Mist)
				effect = new Mist();
			if (StatusEffectType.getStatusEffect(effectTypeString) == StatusEffectType.Paralysis)
				effect = new Paralysis();
			if (StatusEffectType.getStatusEffect(effectTypeString) == StatusEffectType.Poison)
				effect = new Poison();
			if (StatusEffectType.getStatusEffect(effectTypeString) == StatusEffectType.PoisonBadly)
				effect = new PoisonBadly();
			if (StatusEffectType.getStatusEffect(effectTypeString) == StatusEffectType.Protect)
				effect = new Protect();
			if (StatusEffectType.getStatusEffect(effectTypeString) == StatusEffectType.SafeGuard)
				effect = new SafeGuard();
			if (StatusEffectType.getStatusEffect(effectTypeString) == StatusEffectType.Sleep)
				effect = new Sleep();
			if (StatusEffectType.getStatusEffect(effectTypeString) == StatusEffectType.Sunny)
				effect = new Sunny();
			if (StatusEffectType.getStatusEffect(effectTypeString) == StatusEffectType.WaitAfter)
				effect = new WaitAfter(Value);
		}
		if (effectType == EffectType.Stats)
			effect = new StatsEffect(
					StatsEffectType.getStatsEffect(effectTypeString), Value,
					modifierStoreContains(ModifierType.User));
		if (effectType == EffectType.Remove)
			effect = new RemoveEffect(
					StatusEffectType.getStatusEffect(ValueString));
		if (effectType == EffectType.AttackModifier) {
			if (AttackModifierType.getAttackModifierType(effectTypeString) == AttackModifierType.AlwaysHit)
				effect = new AlwaysHitAttackModifier();
			if (AttackModifierType.getAttackModifierType(effectTypeString) == AttackModifierType.CriticalHit)
				effect = new CriticalHitAttackModifier(Value);
			if (AttackModifierType.getAttackModifierType(effectTypeString) == AttackModifierType.Damage)
				effect = new DamageAttackModifier(Value, valueType);
			else if (AttackModifierType.getAttackModifierType(effectTypeString) == AttackModifierType.Flinch)
				effect = new FlinchAttackModifier();
			else if (AttackModifierType.getAttackModifierType(effectTypeString) == AttackModifierType.MultipleHit)
				effect = new MultipleHitAttackModifier(Value, Value2);
			else if (AttackModifierType.getAttackModifierType(effectTypeString) == AttackModifierType.Recoil)
				effect = new RecoilAttackModifier(Value);
		}else if (effectType == EffectType.MultiTurnSpecialAttack){
			if (MultiTurnSpecialAttackType.getMultiTurnSpecialAttackType(effectTypeString) == MultiTurnSpecialAttackType.PetalDance)
				effect = new PetalDance();
			if (MultiTurnSpecialAttackType.getMultiTurnSpecialAttackType(effectTypeString) == MultiTurnSpecialAttackType.RazorWind)
				effect = new RazorWind();
			if (MultiTurnSpecialAttackType.getMultiTurnSpecialAttackType(effectTypeString) == MultiTurnSpecialAttackType.SolarBeam)
				effect = new SolarBeam();
		}else if (effectType == EffectType.SpecialAttack){
			if (SpecialAttackType.getSpecialAttackType(effectTypeString)== SpecialAttackType.Acupressure)
				effect = new Acupressure();
			if (SpecialAttackType.getSpecialAttackType(effectTypeString)== SpecialAttackType.EchoedVoice)
				effect = new EchoedVoice();
			if (SpecialAttackType.getSpecialAttackType(effectTypeString)== SpecialAttackType.Facade)
				effect = new Facade();
			if (SpecialAttackType.getSpecialAttackType(effectTypeString)== SpecialAttackType.Guillotine)
				effect = new Guillotine();
			if (SpecialAttackType.getSpecialAttackType(effectTypeString)== SpecialAttackType.Frustration)
				effect = new Frustration();
			else if (SpecialAttackType.getSpecialAttackType(effectTypeString)== SpecialAttackType.HiddenPower)
				effect = new HiddenPower();
			else if (SpecialAttackType.getSpecialAttackType(effectTypeString)== SpecialAttackType.NightShade)
				effect = new NightShade();
			else if (SpecialAttackType.getSpecialAttackType(effectTypeString)== SpecialAttackType.JumpKick)
				effect = new JumpKick();
			if (SpecialAttackType.getSpecialAttackType(effectTypeString)== SpecialAttackType.Return)
				effect = new Return();
			else if (SpecialAttackType.getSpecialAttackType(effectTypeString)== SpecialAttackType.Venoshock)
				effect = new Venoshock();
			
		}

		if (hasModifier) {
			for (ModifierStoreClass m : modifierStore) {
				if (m.effectModifier == ModifierType.Chance)
					effect.AddModifier(new ChanceModifier(m.modifierValue));
			}
		}
		return effect;
	}

	private boolean modifierStoreContains(ModifierType user) {
		for (ModifierStoreClass m : modifierStore)
			if (m.effectModifier == ModifierType.User)
				return true;
		return false;
	}

	private void calcEffectType() {
		if (StatusEffectType.isStatusEffect(effectTypeString))
			effectType = EffectType.Status;
		else if (AttackModifierType.isAttackModifierType(effectTypeString))
			effectType = EffectType.AttackModifier;
		else if (StatsEffectType.isStatsEffect(effectTypeString))
			effectType = EffectType.Stats;
		else if (SpecialAttackType.isSpecialAttackType(effectTypeString))
			effectType = EffectType.SpecialAttack;
		else if (MultiTurnSpecialAttackType.isMultiTurnSpecialAttackType(effectTypeString))
			effectType = EffectType.MultiTurnSpecialAttack;

		if (hasModifier)
			for (ModifierStoreClass m : modifierStore)
				m.effectModifier = ModifierType
						.getModifierType(m.modifierTypeString);
	}

	private void calcValue() {
		if (effectValueString != null) {
			if (effectValueString.endsWith("%")) {
				valueType = ValueType.Percent;
				effectValueString = effectValueString.replace("%", "");
			} else if (effectValueString.contains("to")) {
				valueType = ValueType.Range;
				int toInd = effectValueString.indexOf("to");
				Value2 = Integer.parseInt(effectValueString
						.substring(toInd + 2));
				effectValueString = effectValueString.substring(0, toInd);
			} else
				valueType = ValueType.WholeNumber;
			try {
				Value = Integer.parseInt(effectValueString);
			} catch (Exception e) {
			} finally {
				ValueString = effectValueString;
			}
		}
		for (ModifierStoreClass m : modifierStore) {
			if (m.modifierValueString != null) {
				if (m.modifierValueString.endsWith("%")) {
					m.modifierValueType = ValueType.Percent;
					m.modifierValueString = m.modifierValueString.replace("%", "");
				} else
					m.modifierValueType = ValueType.WholeNumber;
				m.modifierValue = Integer.parseInt(m.modifierValueString);
			}
		}
	}

	public enum ValueType {
		None, WholeNumber, Percent, Range
	}
	
	public class ModifierStoreClass {

		public ModifierType effectModifier;
		public String modifierTypeString;
		public String modifierValueString;
		public ValueType modifierValueType;
		public int modifierValue;

	}
}


