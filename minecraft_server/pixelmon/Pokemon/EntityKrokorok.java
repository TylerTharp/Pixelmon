package pixelmon.Pokemon;

import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import net.minecraft.src.*;

public class EntityKrokorok extends EntityGroundPixelmon
{
	
	public EntityKrokorok(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		name = "Krokorok";
		isImmuneToFire = false;
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
