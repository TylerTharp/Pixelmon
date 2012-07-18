package pixelmon;

import net.minecraft.src.ModLoader;
import net.minecraft.src.forge.Configuration;

public class IDListTrainer {

	public static int trainerYoungsterId;
	public static int trainerYoungster2Id;
	public static int trainerBugCatcherId;
	
	public static void load(Configuration configuration) {
		trainerYoungsterId = Integer.parseInt(configuration.getOrCreateIntProperty("Youngster", "trainers", ModLoader.getUniqueEntityId()).value);
		trainerYoungster2Id = Integer.parseInt(configuration.getOrCreateIntProperty("Youngster2", "trainers", ModLoader.getUniqueEntityId()).value);
		trainerBugCatcherId = Integer.parseInt(configuration.getOrCreateIntProperty("BugCatcher", "trainers", ModLoader.getUniqueEntityId()).value);
	}
}
