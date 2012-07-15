package pixelmon.entities;

import pixelmon.storage.PokeballManager;
import net.minecraft.src.EntityCrit2FX;
import net.minecraft.src.EntityLiving;
//import net.minecraft.src.EntityReddustFX;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityThrowable;
import net.minecraft.src.ModLoader;
import net.minecraft.src.MovingObjectPosition;
import net.minecraft.src.World;
import net.minecraft.src.mod_Pixelmon;

public class EntityPokeBall extends EntityThrowable {

	public PixelmonEntityHelper pixelmon;
	public double ballBonus=1;

	public EntityPokeBall(World world) {
		super(world);
	}

	public EntityPokeBall(World world, EntityLiving entityliving, PixelmonEntityHelper e) {
		super(world, entityliving);
		pixelmon = e;
		ballBonus = 1;
	}

	public void init() {
	}

	protected void onImpact(MovingObjectPosition movingobjectposition) {
		if (movingobjectposition != null && !worldObj.isRemote) {
			if (pixelmon != null) {
				pixelmon.setLocationAndAngles(posX, posY, posZ, rotationYaw, 0.0F);
				pixelmon.setMotion(0, 0, 0);
				pixelmon.releaseFromPokeball();
				if (movingobjectposition.entityHit != null
						&& (movingobjectposition.entityHit instanceof IHaveHelper)
						&& !mod_Pixelmon.pokeballManager.getPlayerStorage(ModLoader.getMinecraftInstance().thePlayer).isIn(
								((IHaveHelper) movingobjectposition.entityHit).getHelper()))
					pixelmon.StartBattle(((IHaveHelper) movingobjectposition.entityHit).getHelper());
				if (movingobjectposition.entityHit != null && movingobjectposition.entityHit instanceof EntityTrainer)
					pixelmon.StartBattle((EntityTrainer) movingobjectposition.entityHit, (EntityPlayer) thrower);
				else
					pixelmon.clearAttackTarget();
				if (thrower instanceof EntityPlayer) {

				}
				spawnCaptureParticles();
			}
			setDead();
		}
	}

	private void spawnCaptureParticles() {
		for (int i = mod_Pixelmon.getRandomNumberBetween(5, 7); i > 0; i--) {
			EntityCrit2FX entitycrit2fx = new EntityCrit2FX(worldObj, this, "crit");
			ModLoader.getMinecraftInstance().effectRenderer.addEffect(entitycrit2fx);
		}
	}

	/*
	 * private void spawnFailParticles() {
	 * 
	 * for (int i = 0; i < 30; i++) { EntityReddustFX entityred = new
	 * EntityReddustFX(worldObj, posX, posY, posZ, 1, 0, 0);
	 * entityred.setVelocity(worldObj.rand.nextFloat() / 5,
	 * worldObj.rand.nextFloat() / 5, worldObj.rand.nextFloat() / 5);
	 * ModLoader.getMinecraftInstance().effectRenderer.addEffect(entityred); } }
	 */
}