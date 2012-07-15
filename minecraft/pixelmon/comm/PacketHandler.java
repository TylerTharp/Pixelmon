package pixelmon.comm;

import java.io.*;

import pixelmon.StarterList;
import pixelmon.entities.IHaveHelper;
import pixelmon.entities.PixelmonEntityList;
import pixelmon.storage.PixelmonServerStore;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ModLoader;
import net.minecraft.src.NetClientHandler;
import net.minecraft.src.NetworkManager;
import net.minecraft.src.Packet1Login;
import net.minecraft.src.mod_Pixelmon;
import net.minecraft.src.forge.IConnectionHandler;
import net.minecraft.src.forge.IPacketHandler;
import net.minecraft.src.forge.MessageManager;

public class PacketHandler implements IConnectionHandler, IPacketHandler{

	@Override
	public void onPacketData(NetworkManager network, String channel, byte[] data) {
		 DataInputStream dataStream = new DataInputStream(new ByteArrayInputStream(data));
         try{
        	 int packetID = dataStream.readInt();
        	 if (packetID == EnumPackets.AddToStorage.getIndex()){
        		 mod_Pixelmon.serverStorageDisplay.add(dataStream);
        	 }else if (packetID == EnumPackets.UpdateStorage.getIndex()){
            		 mod_Pixelmon.serverStorageDisplay.update(dataStream);
        	 }else if (packetID == EnumPackets.AddToTempStore.getIndex()){
        		 PixelmonServerStore.addToList(dataStream);
        	 }else if (packetID == EnumPackets.BattleFinished.getIndex()){
        		 PixelmonServerStore.clearList();
        	 }
         }catch(IOException e){
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
