package pixelmon.storage;

import net.minecraft.src.NBTTagCompound;

public class SlotPCPC extends SlotPC{
	public int boxNumber, boxPosition;
	
	public SlotPCPC(int x, int y, int boxNum, int boxPos, NBTTagCompound n){
		super(x, y, n);
		boxNumber = boxNum;
		boxPosition = boxPos;
	}
	
}
