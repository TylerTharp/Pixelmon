package pixelmon.attacks.attackModifiers;

public enum AttackModifierType {
	AlwaysHit,
	CriticalHit,
	Damage,
	Flinch,
	MultipleHit,
	Recoil;
	
	public static AttackModifierType getAttackModifierType(String string){
		if (string.equalsIgnoreCase("AlwaysHit")) return AttackModifierType.AlwaysHit;
		if (string.equalsIgnoreCase("CriticalHit")) return AttackModifierType.CriticalHit;
		if (string.equalsIgnoreCase("Damage")) return AttackModifierType.Damage;
		if (string.equalsIgnoreCase("Flinch")) return AttackModifierType.Flinch;
		if (string.equalsIgnoreCase("MultipleHit")) return AttackModifierType.MultipleHit;
		if (string.equalsIgnoreCase("Recoil")) return AttackModifierType.Recoil;
		return null;
	}
	
	public static boolean isAttackModifierType(String string){
		if (string.equalsIgnoreCase("AlwaysHit")) return true;
		if (string.equalsIgnoreCase("CriticalHit")) return true;
		if (string.equalsIgnoreCase("Damage")) return true;
		if (string.equalsIgnoreCase("Flinch")) return true;
		if (string.equalsIgnoreCase("MultipleHit")) return true;
		if (string.equalsIgnoreCase("Recoil")) return true;
		return false;		
	}
}
