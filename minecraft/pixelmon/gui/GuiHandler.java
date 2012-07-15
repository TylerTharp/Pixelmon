package pixelmon.gui;

import pixelmon.StarterList;
import pixelmon.TileEntityPokemonHealer;
import pixelmon.attacks.BattleController;
import pixelmon.attacks.BattleRegistry;
import pixelmon.database.DatabaseMoves;
import pixelmon.entities.BaseEntityPixelmon;
import pixelmon.entities.PixelmonEntityHelper;
import pixelmon.storage.GuiPC;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ModLoader;
import net.minecraft.src.World;
import net.minecraft.src.mod_Pixelmon;
import net.minecraft.src.forge.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == EnumGui.ChooseStarter.getIndex())
			return new GuiChooseStarter();
		else if (ID == EnumGui.LearnMove.getIndex())
			return new GuiLearnMove(mod_Pixelmon.pokeballManager.getPlayerStorage(ModLoader.getMinecraftInstance().thePlayer)
					.getAlreadyExists(x, ModLoader.getMinecraftInstance().theWorld).getHelper(), DatabaseMoves.getAttack(y));
		else if (ID == EnumGui.ChooseAttack.getIndex()) {
			if (world.isRemote) {
				return new GuiAttacking(x,y,z);
			} else {
				PixelmonEntityHelper pixelmon1 = null, pixelmon2 = null;
				BattleController bc = mod_Pixelmon.battleRegistry.getBattle(x);
				if (y == 1) {
					pixelmon1 = bc.participant1.currentPokemon();
					pixelmon2 = bc.participant2.currentPokemon();
				} else {
					pixelmon1 = bc.participant2.currentPokemon();
					pixelmon2 = bc.participant1.currentPokemon();
				}

				return new GuiAttacking(bc, pixelmon1, pixelmon2);
			}
		} else if (ID == EnumGui.ChoosePokemon.getIndex()) {
			BattleController bc = mod_Pixelmon.battleRegistry.getBattle(x);
			PixelmonEntityHelper p;
			if (y == 1)
				p = bc.participant1.currentPokemon();
			else
				p = bc.participant2.currentPokemon();
			return new GuiChoosePokemon(bc, p);
		} else if (ID == EnumGui.Pokedex.getIndex()) {
			return new GuiPokedex();
		} else if (ID == EnumGui.PC.getIndex()) {
			return new GuiPC();
		} else if (ID == EnumGui.Healer.getIndex()) {
			TileEntityPokemonHealer tileentityfurnace = (TileEntityPokemonHealer) world.getBlockTileEntity(x, y, z);
			return new GuiHealer(player.inventory, tileentityfurnace);
		} else if (ID == EnumGui.PokeChecker.getIndex()) {
			return new GuiScreenPokeChecker(mod_Pixelmon.pokeballManager.getPlayerStorage(player).getAlreadyExists(x, world).getHelper());
		}
		return null;
	}
}
