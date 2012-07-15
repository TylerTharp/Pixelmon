package pixelmon.attacks.attackModifiers;

public enum ModifierType {
	Chance, User;
	
	public static ModifierType getModifierType(String string){
		if (string.equalsIgnoreCase("Chance")) return ModifierType.Chance;
		if (string.equalsIgnoreCase("User")) return ModifierType.User;
		return null;
	}
}
