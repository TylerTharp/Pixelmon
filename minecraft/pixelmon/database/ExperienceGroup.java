package pixelmon.database;

public enum ExperienceGroup {
	Erratic,
	Fast,
	MediumFast,
	MediumSlow,
	Slow,
	Fluctuating;
	
	public static ExperienceGroup getExperienceGroup(String s){
		if (s.equalsIgnoreCase("Erratic")) return ExperienceGroup.Erratic;
		if (s.equalsIgnoreCase("Fast")) return ExperienceGroup.Fast;
		if (s.equalsIgnoreCase("MediumFast")) return ExperienceGroup.MediumFast;
		if (s.equalsIgnoreCase("MediumSlow")) return ExperienceGroup.MediumSlow;
		if (s.equalsIgnoreCase("Slow")) return ExperienceGroup.Slow;
		if (s.equalsIgnoreCase("Fluctuating")) return ExperienceGroup.Fluctuating;
		return null;
	}
}
