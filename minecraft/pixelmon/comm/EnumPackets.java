package pixelmon.comm;

public enum EnumPackets {
	ChooseStarter(0), RegisterPlayer(1), AddToStorage(2), RemoveFromStorage(3), SendPokemon(4), AddToTempStore(5), ChooseAttack(6), BattleFinished(7), UpdateStorage(
			8), HealPokemon(9);

	private int index;

	private EnumPackets(int i) {
		index = i;
	}

	public Integer getIndex() {
		return index;
	}

	public static EnumPackets getEnum(int readInt) {
		for (int i = 0; i < values().length; i++) {
			if (values()[i].index == readInt)
				return values()[i];
		}
		return null;
	}
}
