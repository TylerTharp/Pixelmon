package pixelmon.database;

import net.minecraft.src.NBTTagCompound;

public class BattleStats {
	public int Accuracy;
	public int Evasion;
	public int AttackModifier;
	public int DefenceModifier;
	public int SpecialAttackModifier;
	public int SpecialDefenceModifier;
	public int SpeedModifier;
	
	public void setIsParalyzed(){
		SpeedModifier/=4;
	}
	public void setNotParalyzed(){
		SpeedModifier*=4;
	}
	
	public boolean IncreaseAccuracy(int Amount){
		int oldVal = Accuracy;
		for (int i =0; i < Amount; i++){
			Accuracy = increaseAccuracyOrEvasion(Accuracy);
		}
		if (oldVal== Accuracy) return false;
		return true;
	}

	public boolean DecreaseAccuracy(int Amount){
		int oldVal=Accuracy;
		for (int i = 0; i < Amount; i++)
			Accuracy = decreaseAccuracyOrEvasion(Accuracy);
		if (oldVal== Accuracy) return false;
		return true;
	}
	
	public boolean IncreaseEvasion(int Amount){
		int oldVal= Evasion;
		for (int i =0; i < Amount; i++){
			Evasion = increaseAccuracyOrEvasion(Evasion);
		}
		if (oldVal== Evasion) return false;
		return true;
	}

	public boolean DecreaseEvasion(int Amount){
		int oldVal=Evasion;
		for (int i = 0; i < Amount; i++)
			Evasion = decreaseAccuracyOrEvasion(Evasion);
		if (oldVal== Evasion) return false;
		return true;

	}
	
	public boolean IncreaseAttack(int Amount){
		int oldVal = AttackModifier;
		for (int i =0; i < Amount; i++){
			AttackModifier = increaseBattleStat(AttackModifier);
		}
		if (oldVal== AttackModifier) return false;
		return true;
	}
	
	public boolean DecreaseAttack(int Amount){
		int oldVal = AttackModifier;
		for (int i =0; i < Amount; i++){
			AttackModifier = decreaseBattleStat(AttackModifier);
		}
		if (oldVal== AttackModifier) return false;
		return true;
	}
	
	public boolean IncreaseDefence(int Amount){
		int oldVal = DefenceModifier;
		for (int i =0; i < Amount; i++){
			DefenceModifier = increaseBattleStat(DefenceModifier);
		}
		if (oldVal== DefenceModifier) return false;
		return true;

	}
	
	public boolean DecreaseDefence(int Amount){
		int oldVal = DefenceModifier;
		for (int i =0; i < Amount; i++){
			DefenceModifier = decreaseBattleStat(DefenceModifier);
		}
		if (oldVal== DefenceModifier) return false;
		return true;
	}
	
	public boolean IncreaseSpecialAttack(int Amount){
		int oldVal = SpecialAttackModifier;
		for (int i =0; i < Amount; i++){
			SpecialAttackModifier = increaseBattleStat(SpecialAttackModifier);
		}
		if (oldVal== SpecialAttackModifier) return false;
		return true;

	}
	
	public boolean DecreaseSpecialAttack(int Amount){
		int oldVal = SpecialAttackModifier;
		for (int i =0; i < Amount; i++){
			SpecialAttackModifier = decreaseBattleStat(SpecialAttackModifier);
		}
		if (oldVal== SpecialAttackModifier) return false;
		return true;
	}
		
	public boolean IncreaseSpecialDefence(int Amount){
		int oldVal = SpecialDefenceModifier;
		for (int i =0; i < Amount; i++){
			SpecialDefenceModifier = increaseBattleStat(SpecialDefenceModifier);
		}
		if (oldVal== SpecialDefenceModifier) return false;
		return true;
	}
	
	public boolean DecreaseSpecialDefence(int Amount){
		int oldVal = SpecialDefenceModifier;
		for (int i =0; i < Amount; i++){
			SpecialDefenceModifier = decreaseBattleStat(SpecialDefenceModifier);
		}
		if (oldVal== SpecialDefenceModifier) return false;
		return true;
	}
	
	public boolean IncreaseSpeed(int Amount){
		int oldVal = SpeedModifier;
		for (int i =0; i < Amount; i++){
			SpeedModifier = increaseBattleStat(SpeedModifier);
		}
		if (oldVal== SpeedModifier) return false;
		return true;

	}
	
	public boolean DecreaseSpeed(int Amount){
		int oldVal = SpeedModifier;
		for (int i =0; i < Amount; i++){
			SpeedModifier = decreaseBattleStat(SpeedModifier);
		}
		if (oldVal== SpeedModifier) return false;
		return true;
	}
	
	public BattleStats(){
		clearBattleStats();
	}
	
	private int increaseBattleStat(int initialValue){
		if (initialValue==25) return 29;
		if (initialValue==29) return 33;
		if (initialValue==33) return 40;
		if (initialValue==40) return 50;
		if (initialValue==50) return 67;
		if (initialValue==67) return 100;
		if (initialValue==100) return 150;
		if (initialValue==150) return 200;
		if (initialValue==200) return 250;
		if (initialValue==250) return 300;
		if (initialValue==300) return 350;
		if (initialValue==350) return 400;
		if (initialValue==400) return 400;
		return initialValue;
	}
	private int increaseAccuracyOrEvasion(int initialValue){
		if (initialValue==33) return 39;
		if (initialValue==38) return 43;
		if (initialValue==43) return 50;
		if (initialValue==50) return 60;
		if (initialValue==60) return 75;
		if (initialValue==75) return 100;
		if (initialValue==100) return 133;
		if (initialValue==133) return 167;
		if (initialValue==167) return 200;
		if (initialValue==200) return 233;
		if (initialValue==233) return 267;
		if (initialValue==267) return 300;
		if (initialValue==300) return 300;
		return initialValue;
	}
	
	private int decreaseBattleStat(int initialValue){
		if (initialValue==25) return 25;
		if (initialValue==29) return 25;
		if (initialValue==33) return 29;
		if (initialValue==40) return 33;
		if (initialValue==50) return 40;
		if (initialValue==67) return 50;
		if (initialValue==100) return 67;
		if (initialValue==150) return 100;
		if (initialValue==200) return 150;
		if (initialValue==250) return 200;
		if (initialValue==300) return 250;
		if (initialValue==350) return 300;
		if (initialValue==400) return 350;
		return initialValue;
	}
	private int decreaseAccuracyOrEvasion(int initialValue){
		if (initialValue==33) return 33;
		if (initialValue==38) return 33;
		if (initialValue==43) return 38;
		if (initialValue==50) return 43;
		if (initialValue==60) return 50;
		if (initialValue==75) return 60;
		if (initialValue==100) return 75;
		if (initialValue==133) return 100;
		if (initialValue==167) return 133;
		if (initialValue==200) return 167;
		if (initialValue==233) return 200;
		if (initialValue==267) return 233;
		if (initialValue==300) return 267;
		return initialValue;
	}
	public void writeToNBT(NBTTagCompound var1) {
		var1.setInteger("BattleAccuracy", Accuracy);
		var1.setInteger("BattleEvasion", Evasion);
		var1.setInteger("BattleAttackModifier", AttackModifier);
		var1.setInteger("BattleDefenceModifier", DefenceModifier);
		var1.setInteger("BattleSpecialAttackModifier", SpecialAttackModifier);
		var1.setInteger("BattleSpecialDefenceModifier", SpecialDefenceModifier);
		var1.setInteger("BattleSpeedModifier", SpeedModifier);
	}
	
	public void clearBattleStats(){
		AttackModifier=100;
		DefenceModifier=100;
		SpecialAttackModifier=100;
		SpecialDefenceModifier=100;
		SpeedModifier=100;
		Accuracy = 100;
		Evasion=100;
	}
	
	public void readFromNBT(NBTTagCompound var1) {
		Accuracy = var1.getInteger("BattleAccuracy");
		Evasion = var1.getInteger("BattleEvasion");
		AttackModifier = var1.getInteger("BattleAttackModifier");
		DefenceModifier = var1.getInteger("BattleDefenceModifier");
		SpecialAttackModifier = var1.getInteger("BattleSpecialAttackModifier");
		SpecialDefenceModifier = var1.getInteger("BattleSpecialDefenceModifier");
		SpeedModifier = var1.getInteger("BattleSpeedModifier");
	}
	
}
