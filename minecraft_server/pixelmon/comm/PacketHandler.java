package pixelmon.comm;

import java.io.*;
import java.util.Random;

import pixelmon.PixelmonEntityList;
import pixelmon.StarterList;
import pixelmon.attacks.BattleController;
import pixelmon.attacks.BattleRegistry;
import pixelmon.attacks.PlayerParticipant;
import pixelmon.entities.pixelmon.helpers.IHaveHelper;
import pixelmon.entities.pokeballs.EntityPokeBall;
import pixelmon.enums.EnumGui;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModLoader;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NetServerHandler;
import net.minecraft.src.NetworkManager;
import net.minecraft.src.Packet;
import net.minecraft.src.Packet1Login;
import net.minecraft.src.mod_Pixelmon;
import net.minecraft.src.forge.IConnectionHandler;
import net.minecraft.src.forge.IPacketHandler;
import net.minecraft.src.forge.MessageManager;

public class PacketHandler implements IConnectionHandler, IPacketHandler {

	static EntityPokeBall currentPokeball = null;
	PacketPCClickHandler pcHandler;
	
	public PacketHandler(){
		pcHandler = new PacketPCClickHandler();
	}

	@Override
	public void onPacketData(NetworkManager network, String channel, byte[] data) {

		DataInputStream dataStream = new DataInputStream(new ByteArrayInputStream(data));
		try {
			int packetID = dataStream.readInt();
			if (packetID == EnumPackets.ChooseStarter.getIndex()) {
				EntityPlayer player = ((NetServerHandler) network.getNetHandler()).getPlayerEntity();
				IHaveHelper p = (IHaveHelper) PixelmonEntityList.createEntityByName(StarterList.getStarterStringList()[dataStream.readInt()], player.worldObj);
				p.getHelper().getLvl().setLevel(5);
				p.getHelper().loadMoveset();
				mod_Pixelmon.pokeballManager.getPlayerStorage(player).addToParty(p.getHelper());
			} else if (packetID == EnumPackets.RegisterPlayer.getIndex()) {
				EntityPlayer player = ((NetServerHandler) network.getNetHandler()).getPlayerEntity();
				if (mod_Pixelmon.pokeballManager.getPlayerStorage(player).count() == 0) {
					player.openGui(mod_Pixelmon.instance, EnumGui.ChooseStarter.getIndex(), player.worldObj, 0, 0, 0);
				}
			} else if (packetID == EnumPackets.SendPokemon.getIndex()) {
				EntityPlayer player = ((NetServerHandler) network.getNetHandler()).getPlayerEntity();
				int pokemonId = dataStream.readInt();
				NBTTagCompound nbt = mod_Pixelmon.pokeballManager.getPlayerStorage(player).getNBT(pokemonId);
				if (!mod_Pixelmon.pokeballManager.getPlayerStorage(player).EntityAlreadyExists(pokemonId, player.worldObj) && (currentPokeball == null || currentPokeball.isDead)
						&& !mod_Pixelmon.pokeballManager.getPlayerStorage(player).isFainted(pokemonId)) {
					player.worldObj.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / ((new Random()).nextFloat() * 0.4F + 0.8F));
					currentPokeball = new EntityPokeBall(player.worldObj, player, mod_Pixelmon.pokeballManager.getPlayerStorage(player).sendOut(pokemonId, player.worldObj).getHelper());
					boolean flag = nbt.getString("NickName") == null || nbt.getString("Nickname").isEmpty();
					ChatHandler.sendChat(player, "You sent out " + (flag ? nbt.getString("Name") : nbt.getString("Nickname")) + "!");
					player.worldObj.spawnEntityInWorld(currentPokeball);
				} else if (mod_Pixelmon.pokeballManager.getPlayerStorage(player).isFainted(pokemonId)) {
					boolean flag = nbt.getString("NickName") == null || nbt.getString("Nickname").isEmpty();
					ChatHandler.sendChat(player, (flag ? nbt.getString("Name") : nbt.getString("Nickname")) + " is unable to battle!");
				} else if (mod_Pixelmon.pokeballManager.getPlayerStorage(player).EntityAlreadyExists(pokemonId, player.worldObj)) {
					IHaveHelper pixelmon = mod_Pixelmon.pokeballManager.getPlayerStorage(player).getAlreadyExists(pokemonId, player.worldObj);
					if (pixelmon == null) {
						return;
					}
					if (pixelmon.getHelper().getOwner() == null)
						pixelmon.unloadEntity();
					else if (pixelmon.getHelper().getOwner() == player) {
						mod_Pixelmon.pokeballManager.getPlayerStorage(player).retrieve(pixelmon);
						pixelmon.catchInPokeball();
						boolean flag = nbt.getString("NickName") == null || nbt.getString("Nickname").isEmpty();
						ChatHandler.sendChat(player, "You retrieved " + (flag ? nbt.getString("Name") : nbt.getString("Nickname")) + "!");
					}
				}
			} else if (packetID == EnumPackets.ChooseAttack.getIndex()) {
				int buttonId = dataStream.readInt();
				int battleIndex = dataStream.readInt();
				int pokemonID = dataStream.readInt();
				EntityPlayer player = ((NetServerHandler) network.getNetHandler()).getPlayerEntity();
				if (buttonId < 4) {
					BattleController bc = mod_Pixelmon.battleRegistry.getBattle(battleIndex);
					if (bc.participant1.currentPokemon().getPokemonId() == pokemonID)
						bc.setAttack(bc.participant1.currentPokemon(), bc.participant1.currentPokemon().getMoveset().get(buttonId));
					else
						bc.setAttack(bc.participant2.currentPokemon(), bc.participant2.currentPokemon().getMoveset().get(buttonId));
				} else if (buttonId == 11) {
					player.openGui(mod_Pixelmon.instance, EnumGui.ChoosePokemon.getIndex(), player.worldObj, 0, 0, 0);
				} else if (buttonId == 10) {
					BattleController bc = mod_Pixelmon.battleRegistry.getBattle(battleIndex);
					if (bc.participant1.currentPokemon().getPokemonId() == pokemonID)
						bc.setFlee(bc.participant1.currentPokemon());
					else
						bc.setFlee(bc.participant2.currentPokemon());
				}

			} else if (packetID == EnumPackets.HealPokemon.getIndex()) {
				EntityPlayer player = ((NetServerHandler) network.getNetHandler()).getPlayerEntity();
				int index = dataStream.readInt();
				if (index==-1)
					mod_Pixelmon.pokeballManager.getPlayerStorage(player).healAllPokemon();
				else
					mod_Pixelmon.pokeballManager.getPlayerStorage(player).heal(index);
			} else if (packetID == EnumPackets.SwitchPokemon.getIndex()) {
				EntityPlayer player = ((NetServerHandler) network.getNetHandler()).getPlayerEntity();
				int pos = dataStream.readInt();
				BattleController bc = mod_Pixelmon.battleRegistry.getBattle(dataStream.readInt());
				if (bc.participant1 instanceof PlayerParticipant) {
					if (((PlayerParticipant) bc.participant1).player == player) {
						bc.SwitchPokemon(bc.participant1.currentPokemon(), mod_Pixelmon.pokeballManager.getPlayerStorage(player).getIDFromPosition(pos));
					}
				}
				if (bc.participant2 instanceof PlayerParticipant) {
					if (((PlayerParticipant) bc.participant2).player == player) {
						bc.SwitchPokemon(bc.participant2.currentPokemon(), mod_Pixelmon.pokeballManager.getPlayerStorage(player).getIDFromPosition(pos));
					}

				}
			} else if (packetID == EnumPackets.Flee.getIndex()) {
				EntityPlayer player = ((NetServerHandler) network.getNetHandler()).getPlayerEntity();
				BattleController bc = mod_Pixelmon.battleRegistry.getBattle(player);
				if (bc.participant1 instanceof PlayerParticipant)
					if (((PlayerParticipant) bc.participant1).player == player)
						bc.setFlee(bc.participant1.currentPokemon());

				if (bc.participant2 instanceof PlayerParticipant)
					if (((PlayerParticipant) bc.participant2).player == player)
						bc.setFlee(bc.participant2.currentPokemon());

			} else if (packetID == EnumPackets.RenamePokemon.getIndex()) {
				EntityPlayer player = ((NetServerHandler) network.getNetHandler()).getPlayerEntity();
				int id = dataStream.readInt();
				if (mod_Pixelmon.pokeballManager.getPlayerStorage(player).EntityAlreadyExists(id, player.worldObj)) {
					mod_Pixelmon.pokeballManager.getPlayerStorage(player).getAlreadyExists(id, player.worldObj).getHelper().setNickname(Packet.readString(dataStream, 64));
				} else {
					NBTTagCompound nbt = mod_Pixelmon.pokeballManager.getPlayerStorage(player).getNBT(id);
					if (nbt != null) {
						nbt.setString("Nickname", Packet.readString(dataStream, 64));
					}
				}
				mod_Pixelmon.pokeballManager.save();
			}
			else if(packetID == EnumPackets.PCClick.getIndex()){
				EntityPlayer player = ((NetServerHandler) network.getNetHandler()).getPlayerEntity();
				pcHandler.handle(dataStream, player);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onConnect(NetworkManager network) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLogin(NetworkManager network, Packet1Login login) {
		MessageManager.getInstance().registerChannel(network, this, "Pixelmon");
	}

	@Override
	public void onDisconnect(NetworkManager network, String message, Object[] args) {
		// TODO Auto-generated method stub

	}

}
