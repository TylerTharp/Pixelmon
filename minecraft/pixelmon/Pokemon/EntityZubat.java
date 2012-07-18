package pixelmon.Pokemon;

import pixelmon.entities.pixelmon.EntityFlyingPixelmon;
import net.minecraft.src.MathHelper;
import net.minecraft.src.World;

public class EntityZubat extends EntityFlyingPixelmon {

	public EntityZubat(World par1World) {
		super(par1World);
		init();
	}

	public void init() {
		name = "Zubat";
		isImmuneToFire = false;
		super.init();
		doesLand = false;
		isAggressive = true;
		courseChangeCooldown = 10;
	}

	public void evolve() {
	}

	public boolean getCanSpawnHere() {
		int var1 = MathHelper.floor_double(this.posX);
		int var2 = MathHelper.floor_double(this.boundingBox.minY);
		int var3 = MathHelper.floor_double(this.posZ);
		if (this.worldObj.getFullBlockLightValue(var1, var2, var3) < 8)
			return worldObj.checkIfAABBIsClear(boundingBox);
		else
			return false;
	}

}