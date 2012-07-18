package pixelmon.gui.pc;

import java.awt.Rectangle;

import pixelmon.comm.PixelmonDataPacket;

import net.minecraft.src.ModLoader;
import net.minecraft.src.NBTTagCompound;

public class SlotPC {

	public NBTTagCompound pokemon;
	public PixelmonDataPacket pokemonData;
	public int x, y;

	public SlotPC(int x, int y, NBTTagCompound pokemon) {
		this.pokemon = pokemon;
		this.x = x;
		this.y = y;
	}

	public SlotPC(int x, int y, PixelmonDataPacket pokemon) {
		this.pokemonData = pokemon;
		this.x = x;
		this.y = y;
	}

	public int getRenderInt() {
		if (ModLoader.getMinecraftInstance().theWorld.isRemote) {
			if (pokemonData ==null) return 0;
			String pokeNum ="";
			if (pokemonData.nationalPokedexNumber<10) pokeNum = "00" + pokemonData.nationalPokedexNumber;
			else if (pokemonData.nationalPokedexNumber<100) pokeNum = "0" + pokemonData.nationalPokedexNumber;
			else pokeNum = "" + pokemonData.nationalPokedexNumber;
			return ModLoader.getMinecraftInstance().renderEngine.getTexture("/pixelmon/sprites/" + pokeNum);
		} else {
			if (pokemon == null) {
				return 0;
			}
			return ModLoader.getMinecraftInstance().renderEngine.getTexture("/pixelmon/sprites/" + pokemon.getString("NationalPokedexNumber"));
		}
	}

	public void clearPokemon() {
		pokemon = null;
		pokemonData = null;
	}

	public void setPokemon(NBTTagCompound n) {
		pokemon = n;
	}

	public void setPokemon(PixelmonDataPacket p) {
		pokemonData = p;
	}

	public void setXandY(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 30, 30);
	}

}
