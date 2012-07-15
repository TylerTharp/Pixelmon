package pixelmon.attacks.attackModifiers;

import net.minecraft.src.mod_Pixelmon;

public class ChanceModifier extends ModifierBase{

	public ChanceModifier(int value) {
		super(ModifierType.Chance);
		this.value = value;
	}
	
	public boolean RollChance(){
		if (mod_Pixelmon.getRandomNumberBetween(0, 100) < value)
			return true;
		else
			return false;
	}
}
