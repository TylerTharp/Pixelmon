package pixelmon.attacks.animations;

import java.util.ArrayList;

public class AttackAnimationParser {
	public static ArrayList<IAttackAnimation> GetAnimation(String animationListString){
		ArrayList<IAttackAnimation> animations = new ArrayList<IAttackAnimation>();
		String[] animationList = animationListString.split(";");
		for(String animationString: animationList){
			if (AttackAnimationType.isAttackAnimationType(animationString)){
				if (AttackAnimationType.getAttackModifierType(animationString) == AttackAnimationType.LeapForward)
					animations.add(new AttackAnimationLeapForward());					
			}
		}
		if (animations.size()==0) animations.add(new AttackAnimationLeapForward());
		return animations;
	}
}
