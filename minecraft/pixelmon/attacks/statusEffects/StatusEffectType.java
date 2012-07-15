package pixelmon.attacks.statusEffects;

public enum StatusEffectType {
	Burn,
	Confusion,
	Infatuated,
	Flee,
	Flying,
	Freeze,
	FireSpin,
	Leech,
	LightScreen,
	Mist,
	Paralysis,
	Poison,
	PoisonBadly,
	Protect,
	SafeGuard,
	Sleep,
	SmackedDown,
	Substitute,
	Sunny,
	WaitAfter;
	
	public static StatusEffectType getStatusEffect(String string){
		if (string.equalsIgnoreCase("Burn")) return StatusEffectType.Burn;
		if (string.equalsIgnoreCase("Confusion")) return StatusEffectType.Confusion;
		if (string.equalsIgnoreCase("FireSpin")) return StatusEffectType.FireSpin;
		if (string.equalsIgnoreCase("Flee")) return StatusEffectType.Flee;
		if (string.equalsIgnoreCase("Flying")) return StatusEffectType.Flying;
		if (string.equalsIgnoreCase("Freeze")) return StatusEffectType.Freeze;
		if (string.equalsIgnoreCase("Infatuated")) return StatusEffectType.Infatuated;
		if (string.equalsIgnoreCase("Leech")) return StatusEffectType.Leech;
		if (string.equalsIgnoreCase("LightScreen")) return StatusEffectType.LightScreen;
		if (string.equalsIgnoreCase("Mist")) return StatusEffectType.Mist;
		if (string.equalsIgnoreCase("Paralysis")) return StatusEffectType.Paralysis;
		if (string.equalsIgnoreCase("Poison")) return StatusEffectType.Poison;
		if (string.equalsIgnoreCase("PoisonBadly")) return StatusEffectType.PoisonBadly;
		if (string.equalsIgnoreCase("Protect")) return StatusEffectType.Protect;
		if (string.equalsIgnoreCase("SafeGuard")) return StatusEffectType.SafeGuard;
		if (string.equalsIgnoreCase("Sleep")) return StatusEffectType.Sleep;
		if (string.equalsIgnoreCase("SmackedDown")) return StatusEffectType.SmackedDown;
		if (string.equalsIgnoreCase("Substitute")) return StatusEffectType.Substitute;
		if (string.equalsIgnoreCase("Sunny")) return StatusEffectType.Sunny;
		if (string.equalsIgnoreCase("WaitAfter")) return StatusEffectType.WaitAfter;
		return null;
	}
	
	public static boolean isStatusEffect(String string){
		if (string.equalsIgnoreCase("Burn")) return true;
		if (string.equalsIgnoreCase("Confusion")) return true;
		if (string.equalsIgnoreCase("FireSpin")) return true;
		if (string.equalsIgnoreCase("Flee")) return true;
		if (string.equalsIgnoreCase("Flying")) return true;
		if (string.equalsIgnoreCase("Freeze")) return true;
		if (string.equalsIgnoreCase("Infatuated")) return true;
		if (string.equalsIgnoreCase("Leech")) return true;
		if (string.equalsIgnoreCase("LightScreen")) return true;
		if (string.equalsIgnoreCase("Mist")) return true;
		if (string.equalsIgnoreCase("Paralysis")) return true;
		if (string.equalsIgnoreCase("Poison")) return true;
		if (string.equalsIgnoreCase("PoisonBadly")) return true;
		if (string.equalsIgnoreCase("Protect")) return true;
		if (string.equalsIgnoreCase("SafeGuard")) return true;
		if (string.equalsIgnoreCase("Sleep")) return true;
		if (string.equalsIgnoreCase("SmackedDown")) return true;
		if (string.equalsIgnoreCase("Substitute")) return true;
		if (string.equalsIgnoreCase("Sunny")) return true;
		if (string.equalsIgnoreCase("WaitAfter")) return true;
		
		return false;
	}
}
