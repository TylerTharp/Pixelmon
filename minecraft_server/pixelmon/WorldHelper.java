package pixelmon;

import net.minecraft.src.Block;
import net.minecraft.src.World;

public class WorldHelper {
	public static int getWaterDepth(int posX,int posY, int posZ, World worldObj){
		int count=0;
		while (worldObj.getBlockId(posX, posY, posZ) == Block.waterStill.blockID){
			posY++;
			count++;
		}
		return count;
	}

}
