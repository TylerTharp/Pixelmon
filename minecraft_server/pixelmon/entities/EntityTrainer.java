package pixelmon.entities;

import java.util.Random;

import pixelmon.PixelmonEntityList;
import pixelmon.comm.ChatHandler;
import pixelmon.database.DatabaseTrainers;
import pixelmon.database.TrainerInfo;
import pixelmon.entities.pixelmon.helpers.IHaveHelper;
import pixelmon.storage.PlayerStorage;
import pixelmon.storage.PokeballManager;
import pixelmon.storage.PokeballManager.PokeballManagerMode;

import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ModLoader;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;
import net.minecraft.src.mod_Pixelmon;
import net.minecraft.src.forge.MinecraftForge;

public class EntityTrainer extends EntityLiving {

	public PlayerStorage pokemonStorage;
	public IHaveHelper releasedPokemon;
	public String name;
	public TrainerInfo info;

	public EntityTrainer(World par1World) {
		super(par1World);
		init();
	}

	public void init() {
		pokemonStorage = new PlayerStorage(this);
	}

	@Override
	public int getMaxHealth() {
		return 100;
	}

	public void writeEntityToNBT(NBTTagCompound var1) {
		super.writeEntityToNBT(var1);
	}

	public void readEntityFromNBT(NBTTagCompound var1) {
		super.readEntityFromNBT(var1);
	}

	public void releasePokemon() {
		if (pokemonStorage.count() == 0)
			loadPokemon();
		IHaveHelper p = pokemonStorage.getFirstAblePokemon(worldObj);
		if (p != null) {
			releasedPokemon = p;
			p.setLocationAndAngles(posX, posY, posZ, rotationYaw, 0.0F);
			motionX = 0;
			motionY = 0;
			motionZ = 0;
			p.releaseFromPokeball();
		}
	}

	public boolean hasAblePokemon() {
		if (pokemonStorage.countAblePokemon() == 0)
			return false;
		return true;
	}

	public void loadPokemon() {
		if (info == null)
			info = DatabaseTrainers.GetTrainerInfo(name);
		for (String pokemonName : info.partypokemon) {
			IHaveHelper p = (IHaveHelper) PixelmonEntityList.createEntityByName(pokemonName, worldObj);
			if (p != null) {
				p.getHelper().getLvl().setLevel((new Random()).nextInt(3) - 1 + info.level);
				p.getHelper().setTrainer(this);
				pokemonStorage.addToParty(p.getHelper());
			}
		}
	}

	public void startBattle(EntityPlayer player) {
		ChatHandler.sendChat(player, info.greeting);
	}

	public void loseBattle(EntityPlayer player) {
		ChatHandler.sendChat(player, info.loseMessage);
	}

	public void winBattle(EntityPlayer player) {
		ChatHandler.sendChat(player, info.winMessage);
	}

	public void retrievePokemon() {
		pokemonStorage.retrieve(releasedPokemon);
	}

	public void healAllPokemon() {
		for (NBTTagCompound nbt : pokemonStorage.partyPokemon) {
			if (nbt != null) {
				nbt.setShort("Health", (short) nbt.getInteger("StatsHP"));
				nbt.setBoolean("IsFainted", false);
				int numMoves = nbt.getInteger("PixelmonNumberMoves");
				for (int i = 0; i < numMoves; i++) {
					nbt.setInteger("PixelmonMovePP" + i, nbt.getInteger("PixelmonMovePPBase" + i));
				}
			}
		}
	}
}
