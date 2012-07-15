package pixelmon.attacks.attackEffects;

public enum StatsEffectType {
	Defence,
	Attack,
	Evasion,
	SpecialAttack,
	SpecialDefence,
	HP,
	Accuracy,
	Speed;
	
	public static StatsEffectType getStatsEffect(String string){
		if (string.equalsIgnoreCase("Defence")) return StatsEffectType.Defence;
		if (string.equalsIgnoreCase("Attack")) return StatsEffectType.Attack;
		if (string.equalsIgnoreCase("Evasion")) return StatsEffectType.Evasion;
		if (string.equalsIgnoreCase("SpecialAttack")) return StatsEffectType.SpecialAttack;
		if (string.equalsIgnoreCase("SpecialDefence")) return StatsEffectType.SpecialDefence;
		if (string.equalsIgnoreCase("HP")) return StatsEffectType.HP;
		if (string.equalsIgnoreCase("Accuracy")) return StatsEffectType.Accuracy;
		if (string.equalsIgnoreCase("Speed")) return StatsEffectType.Speed;
		return null;
	}
	
	public static boolean isStatsEffect(String string){
		if (string.equalsIgnoreCase("Defence")) return true;
		if (string.equalsIgnoreCase("Attack")) return true;
		if (string.equalsIgnoreCase("Evasion")) return true;
		if (string.equalsIgnoreCase("SpecialAttack")) return true;
		if (string.equalsIgnoreCase("SpecialDefence")) return true;
		if (string.equalsIgnoreCase("HP")) return true;
		if (string.equalsIgnoreCase("Accuracy")) return true;
		if (string.equalsIgnoreCase("Speed")) return true;
		return false;
	}
}
