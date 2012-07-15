package pixelmon.database;

import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.mod_Pixelmon;

public class PixelmonIVStore {
	public int HP;
	public int Attack;
	public int Defence;
	public int SpAtt;
	public int SpDef;
	public int Speed;

	public static PixelmonIVStore CreateNewIVs() {
		PixelmonIVStore iv = new PixelmonIVStore();
		iv.SpDef = mod_Pixelmon.getRandomNumberBetween(0, 32);
		iv.SpAtt = mod_Pixelmon.getRandomNumberBetween(0, 32);
		iv.Speed = mod_Pixelmon.getRandomNumberBetween(0, 32);
		iv.Defence = mod_Pixelmon.getRandomNumberBetween(0, 32);
		iv.Attack = mod_Pixelmon.getRandomNumberBetween(0, 32);
		iv.HP = mod_Pixelmon.getRandomNumberBetween(0, 32);
		return iv;
	}

	public void writeToNBt(NBTTagCompound var1) {
		var1.setInteger("IVHP", HP);
		var1.setInteger("IVAttack", Attack);
		var1.setInteger("IVDefence", Defence);
		var1.setInteger("IVSpAtt", SpAtt);
		var1.setInteger("IVSpDef", SpDef);
		var1.setInteger("IVSpeed", Speed);
	}

	public void readFromNBT(NBTTagCompound var1) {
		HP = var1.getInteger("IVHP");
		Attack = var1.getInteger("IVAttack");
		Defence = var1.getInteger("IVDefence");
		SpAtt = var1.getInteger("IVSpAtt");
		SpDef = var1.getInteger("IVSpDef");
		Speed = var1.getInteger("IVSpeed");
	}

	public void CopyIVs(PixelmonIVStore iVs) {
		HP = iVs.HP;
		Attack = iVs.Attack;
		Defence = iVs.Defence;
		SpAtt = iVs.SpAtt;
		SpDef = iVs.SpDef;
		Speed = iVs.Speed;
	}
}
