package pixelmon.entities;

import pixelmon.attacks.BattleController;
import net.minecraft.src.EntityLookHelper;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.NBTTagCompound;

public interface IHaveHelper {

	void SetBattleController(BattleController battleController);

	void EndBattle();

	void catchInPokeball();

	void writeEntityToNBT(NBTTagCompound nbt);

	void setLocationAndAngles(double posX, double posY, double posZ, float rotationYaw, float f);

	void releaseFromPokeball();

	void StartBattle(EntityTrainer entityHit, EntityPlayer opponent);

	void StartBattle(PixelmonEntityHelper entityHit);

	void setPositionAndRotation(double posX, double posY, double posZ, float rotationYaw, float rotationPitch);

	void evolve();

	PixelmonEntityHelper getHelper();

	EntityPlayer getOwner();

	void setOwner(EntityPlayer player);

	void unloadEntity();

	void writeEntityToStorageNBT(NBTTagCompound nbt);

	void loadMoveset();
}
