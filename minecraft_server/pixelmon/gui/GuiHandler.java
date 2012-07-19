package pixelmon.gui;

import pixelmon.enums.EnumGui;
import net.minecraft.src.Container;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.World;
import net.minecraft.src.forge.IGuiHandler;

public class GuiHandler implements IGuiHandler{

	@Override
	public Object getGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		if (ID== EnumGui.ChooseStarter.getIndex()){
			return new ContainerEmpty();
		}if (ID== EnumGui.ChooseAttack.getIndex()){
			return new ContainerEmpty();
		}if (ID==EnumGui.PC.getIndex()){
			return new ContainerEmpty();
		}if (ID==EnumGui.PokeChecker.getIndex()){
			return new ContainerEmpty();
		}if (ID==EnumGui.ChoosePokemon.getIndex()){
			return new ContainerEmpty();
		}
		return null;
	}

	
}
