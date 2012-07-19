package pixelmon.attacks;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ModLoader;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.mod_Pixelmon;
import net.minecraft.src.forge.MinecraftForge;
import pixelmon.comm.ChatHandler;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PacketCreator;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.entities.pixelmon.BaseEntityPixelmon;
import pixelmon.entities.pixelmon.helpers.IHaveHelper;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;
import pixelmon.enums.EnumGui;

public class PlayerParticipant implements IBattleParticipant {
	public EntityPlayer player;
	PixelmonEntityHelper currentPixelmon;
	BattleController bc;

	public PlayerParticipant(EntityPlayer p, PixelmonEntityHelper firstPixelmon) {
		player = p;
		currentPixelmon = firstPixelmon;
	}

	@Override
	public boolean canGainXP() {
		return true;
	}

	@Override
	public PixelmonEntityHelper currentPokemon() {
		return currentPixelmon;
	}

	@Override
	public boolean hasMorePokemon() {
		if (mod_Pixelmon.pokeballManager.getPlayerStorage(player).countAblePokemon() > 0)
			return true;
		return false;
	}

	@Override
	public void EndBattle(boolean didWin, IBattleParticipant foe) {
		currentPixelmon.getBattleStats().clearBattleStats();
		currentPixelmon.EndBattle();
		ModLoader.getMinecraftServerInstance().configManager.sendPacketToPlayer(player.username, PacketCreator.createPacket(EnumPackets.BattleFinished, 0));
	}

	@Override
	public void getNextPokemon() {
		player.openGui(mod_Pixelmon.instance, EnumGui.ChoosePokemon.getIndex(), player.worldObj, mod_Pixelmon.battleRegistry.getIndex(bc), currentPokemon().getPokemonId(), 0);
	}

	@Override
	public boolean getIsFaintedOrDead() {
		return currentPixelmon.getIsDead() || currentPixelmon.getIsFainted();
	}

	@Override
	public String getName() {
		return player.username;
	}

	@Override
	public Attack getMove(IBattleParticipant participant2) {
		if (MinecraftForge.isClient()) {
			int y = 0;
			if (currentPixelmon.getBattleController().participant1.currentPokemon() == currentPixelmon)
				y = 1;
			player.openGui(mod_Pixelmon.instance, EnumGui.ChooseAttack.getIndex(), player.worldObj, mod_Pixelmon.battleRegistry.getIndex(bc), y, 0);
		} else {
			int x = 0, y = 0;
			PixelmonDataPacket p = new PixelmonDataPacket(currentPokemon(), mod_Pixelmon.instance, EnumPackets.AddToTempStore);
			p.order = ModLoader.getUniqueEntityId();
			x= p.order;
			ModLoader.getMinecraftServerInstance().configManager.sendPacketToPlayer(player.username, p.getPacket());
			p = new PixelmonDataPacket(participant2.currentPokemon(), mod_Pixelmon.instance, EnumPackets.AddToTempStore);
			p.order = ModLoader.getUniqueEntityId();
			y = p.order;
			ModLoader.getMinecraftServerInstance().configManager.sendPacketToPlayer(player.username, p.getPacket());

			bc.waitForMove(this);
			player.openGui(mod_Pixelmon.instance, EnumGui.ChooseAttack.getIndex(), player.worldObj, x, y, mod_Pixelmon.battleRegistry.getIndex(bc));
		}
		return null;
	}

	@Override
	public void switchPokemon(IBattleParticipant participant2, int newPixelmonId) {
		currentPixelmon.getBattleStats().clearBattleStats();
		ChatHandler.sendChat(player, participant2.currentPokemon().getOwner(), "That's enough " + currentPixelmon.getName() + "!");
		currentPixelmon.catchInPokeball();

		mod_Pixelmon.pokeballManager.getPlayerStorage(currentPixelmon.getOwner()).retrieve((IHaveHelper) currentPixelmon.getIHaveHelper());
		IHaveHelper newPixelmon = mod_Pixelmon.pokeballManager.getPlayerStorage(currentPixelmon.getOwner()).sendOut(newPixelmonId,
				currentPixelmon.getOwner().worldObj);
		newPixelmon.getHelper().setLocationAndAngles(currentPixelmon.getIHaveHelper());
		newPixelmon.releaseFromPokeball();
		ChatHandler.sendChat(player, participant2.currentPokemon().getOwner(), "Go " + newPixelmon.getHelper().getName() + "!");
		currentPixelmon = newPixelmon.getHelper();
	}

	@Override
	public boolean checkPokemon() {
		for (NBTTagCompound n : mod_Pixelmon.pokeballManager.getPlayerStorage(currentPokemon().getOwner()).partyPokemon) {
			if (n != null && n.getInteger("PixelmonNumberMoves") == 0) {
				ChatHandler.sendChat(currentPixelmon.getOwner(), "Couldn't load pokemon's moves");
				return false;
			}
		}
		return true;
	}

	@Override
	public void setBattleController(BattleController bc) {
		this.bc = bc;
	}
}
