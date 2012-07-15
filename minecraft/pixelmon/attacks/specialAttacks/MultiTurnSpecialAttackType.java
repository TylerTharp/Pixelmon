package pixelmon.attacks.specialAttacks;

public enum MultiTurnSpecialAttackType {
	Fly,
	PetalDance,
	RazorWind,
	SolarBeam;

	public static MultiTurnSpecialAttackType getMultiTurnSpecialAttackType(String string) {
		if (string.equalsIgnoreCase("Fly")) return MultiTurnSpecialAttackType.Fly;
		if (string.equalsIgnoreCase("PetalDance")) return MultiTurnSpecialAttackType.PetalDance;
		if (string.equalsIgnoreCase("RazorWind")) return MultiTurnSpecialAttackType.RazorWind;
		if (string.equalsIgnoreCase("SolarBeam")) return MultiTurnSpecialAttackType.SolarBeam;
		return null;
	}

	public static boolean isMultiTurnSpecialAttackType(String string) {
		if (string.equalsIgnoreCase("Fly")) return true;
		if (string.equalsIgnoreCase("PetalDance")) return true;
		if (string.equalsIgnoreCase("RazorWind")) return true;
		if (string.equalsIgnoreCase("SolarBeam")) return true;
		return false;
	}
}
