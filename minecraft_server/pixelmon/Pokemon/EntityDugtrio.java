package pixelmon.Pokemon;

import pixelmon.entities.EntityGroundPixelmon;
import net.minecraft.src.*;

public class EntityDugtrio extends EntityGroundPixelmon
{
	
	public EntityDugtrio(World world)
	{
		super(world);
		texture = "/pixelmon/image/dugtrio.png";
		init();
	}

	public void init() 
	{
		name = "Dugtrio";
		isImmuneToFire = true;
		super.init();
	}

	public void evolve() 
	{	
	}
	public boolean getCanSpawnHere() {
		int var1 = MathHelper.floor_double(this.posX);
		int var2 = MathHelper.floor_double(this.boundingBox.minY);
		int var3 = MathHelper.floor_double(this.posZ);
	    return this.worldObj.getBlockId(var1, var2 - 1, var3) == Block.sand.blockID;
	}
}
