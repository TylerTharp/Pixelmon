package pixelmon.attacks.animations;

import pixelmon.attacks.attackModifiers.AttackModifierType;

public enum AttackAnimationType {
	StepForward,
	StepBackward,
	LeapForward;
	
	public static AttackAnimationType getAttackModifierType(String string){
		if (string.equalsIgnoreCase("StepForward")) return AttackAnimationType.StepForward;
		if (string.equalsIgnoreCase("StepBackward")) return AttackAnimationType.StepBackward;
		if (string.equalsIgnoreCase("LeapForward")) return AttackAnimationType.LeapForward;
		return null;
	}
	
	public static boolean isAttackAnimationType(String string){
		if (string.equalsIgnoreCase("StepForward")) return true;
		if (string.equalsIgnoreCase("StepBackward")) return true;
		if (string.equalsIgnoreCase("LeapForward")) return true;
		return false;		
	}
}
