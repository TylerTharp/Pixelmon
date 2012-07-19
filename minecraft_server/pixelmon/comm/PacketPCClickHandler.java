package pixelmon.comm;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.mod_Pixelmon;

public class PacketPCClickHandler {
	
	public HashMap<EntityPlayer, NBTTagCompound> mousePokemon;
	
	public PacketPCClickHandler(){
		mousePokemon = new HashMap<EntityPlayer, NBTTagCompound>();
	}
	
	public void handle(DataInputStream data, EntityPlayer player){
		try {
			int box = data.readInt();
			if(box == -2){
				mousePokemon.put(player, null);
				return;
			}
			else if(box == -1){
				int useless = data.readInt();
				int pos = data.readInt();
				NBTTagCompound n = mod_Pixelmon.pokeballManager.getPlayerStorage(player).getNBT(pos);
				NBTTagCompound n1 = mousePokemon.get(player);
				mousePokemon.put(player, n);
				mod_Pixelmon.pokeballManager.getPlayerStorage(player).changePokemon(pos, n1);
				return;
			}
			else if(box == -3){
				int box1 = data.readInt();
				int boxPos = data.readInt();
				NBTTagCompound n = mousePokemon.get(player);
				mousePokemon.put(player, null);
				mod_Pixelmon.computerManager.getPlayerStorage(player).changePokemon(box1, boxPos, n);
				return;
			}
			else if(box >= 0 && box <= 8){
				int useless = data.readInt();
				int boxPos = data.readInt();
				NBTTagCompound n1 = mousePokemon.get(player);
				NBTTagCompound n = mod_Pixelmon.computerManager.getPlayerStorage(player).getBox(box).get(boxPos);
				mousePokemon.put(player, n);
				mod_Pixelmon.computerManager.getPlayerStorage(player).changePokemon(box, boxPos, n1);
				return;
			}
			else if(box == -4){
				int box1 = data.readInt();
				int boxPos = data.readInt();
				NBTTagCompound n = mousePokemon.get(player);
				mousePokemon.put(player, null);
				mod_Pixelmon.computerManager.getPlayerStorage(player).changePokemon(box1, boxPos, n);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
