package pixelmon.enums;

public enum EnumEvolutionStone {
	
	FIRESTONE,
	THUNDERSTONE,
	WATERSTONE,
	SUNSTONE,
	LEAFSTONE,
	DAWNSTONE,
	DUSKSTONE,
	MOONSTONE,
	SHINYSTONE;
	
	public static EnumEvolutionStone getEvolutionStone(String name){
		if (name.equalsIgnoreCase("firestone")) return FIRESTONE;
		if (name.equalsIgnoreCase("thunderstone")) return THUNDERSTONE;
		if (name.equalsIgnoreCase("waterstone")) return WATERSTONE;
		if (name.equalsIgnoreCase("sunstone")) return SUNSTONE;
		if (name.equalsIgnoreCase("leafstone")) return LEAFSTONE;
		if (name.equalsIgnoreCase("dawnstone")) return DAWNSTONE;
		if (name.equalsIgnoreCase("duskstone")) return DUSKSTONE;
		if (name.equalsIgnoreCase("moonstone")) return MOONSTONE;
		if (name.equalsIgnoreCase("shinystone")) return SHINYSTONE;
		return null;
	}

}
