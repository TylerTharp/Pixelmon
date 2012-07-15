package pixelmon.attacks.specialAttacks;

public enum SpecialAttackType {
	Acupressure,
	EchoedVoice,
	Facade,
	Frustration,
	Guillotine,
	HiddenPower,
	HornDrill,
	JumpKick,
	NightShade,
	Payday,
	PetalDance,
	Return,
	SmackDown,
	Substitute,
	Venoshock;

	public static SpecialAttackType getSpecialAttackType(String string) {
		if (string.equalsIgnoreCase("Acupressure")) return SpecialAttackType.Acupressure;
		if (string.equalsIgnoreCase("EchoedVoice")) return SpecialAttackType.EchoedVoice;
		if (string.equalsIgnoreCase("Facade")) return SpecialAttackType.Facade;
		if (string.equalsIgnoreCase("Frustration")) return SpecialAttackType.Frustration;
		if (string.equalsIgnoreCase("Guillotine")) return SpecialAttackType.Guillotine;
		if (string.equalsIgnoreCase("HiddenPower")) return SpecialAttackType.HiddenPower;
		if (string.equalsIgnoreCase("HornDrill")) return SpecialAttackType.HornDrill;
		if (string.equalsIgnoreCase("JumpKick")) return SpecialAttackType.JumpKick;
		if (string.equalsIgnoreCase("NightShade")) return SpecialAttackType.NightShade;
		if (string.equalsIgnoreCase("Payday")) return SpecialAttackType.Payday;
		if (string.equalsIgnoreCase("PetalDance")) return SpecialAttackType.PetalDance;
		if (string.equalsIgnoreCase("Return")) return SpecialAttackType.Return;
		if (string.equalsIgnoreCase("SmackDown")) return SpecialAttackType.SmackDown;
		if (string.equalsIgnoreCase("Substitute")) return SpecialAttackType.Substitute;
		if (string.equalsIgnoreCase("Venoshock")) return SpecialAttackType.Venoshock;
		return null;
	}

	public static boolean isSpecialAttackType(String string) {
		if (string.equalsIgnoreCase("Acupressure")) return true;
		if (string.equalsIgnoreCase("EchoedVoice")) return true;
		if (string.equalsIgnoreCase("Facade")) return true;
		if (string.equalsIgnoreCase("Frustration")) return true;
		if (string.equalsIgnoreCase("Guillotine")) return true;
		if (string.equalsIgnoreCase("HiddenPower")) return true;
		if (string.equalsIgnoreCase("HornDrill")) return true;
		if (string.equalsIgnoreCase("JumpKick")) return true;
		if (string.equalsIgnoreCase("NightShade")) return true;
		if (string.equalsIgnoreCase("Payday")) return true;
		if (string.equalsIgnoreCase("PetalDance")) return true;
		if (string.equalsIgnoreCase("Return")) return true;
		if (string.equalsIgnoreCase("SmackDown")) return true;
		if (string.equalsIgnoreCase("Substitute")) return true;
		if (string.equalsIgnoreCase("Venoshock")) return true;
		return false;
	}
}
