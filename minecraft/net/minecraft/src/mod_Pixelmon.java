package net.minecraft.src;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.src.forge.Configuration;
import net.minecraft.src.forge.ForgeHooksClient;
import net.minecraft.src.forge.MinecraftForge;
import net.minecraft.src.forge.MinecraftForgeClient;
import net.minecraft.src.forge.NetworkMod;
import net.minecraft.src.forge.Property;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import pixelmon.BlockEvolutionStoneOre;
import pixelmon.BlockHealer;
import pixelmon.EnumEvolutionStone;
import pixelmon.ItemEmptyPokeBall;
import pixelmon.ItemEvolutionStone;
import pixelmon.ItemPokedex;
import pixelmon.KeyboardHandler;
import pixelmon.PixelmonIDList;
import pixelmon.RenderEmptyPokeball;
import pixelmon.RenderPokeball;
import pixelmon.RenderTrainer;
import pixelmon.TileEntityPokemonHealer;
import pixelmon.TrainerIDList;
import pixelmon.WorldGenLeafStoneOre;
import pixelmon.Pokemon.EntityBulbasaur;
import pixelmon.Pokemon.EntityCharmander;
import pixelmon.Pokemon.EntityEevee;
import pixelmon.Pokemon.EntitySquirtle;
import pixelmon.attacks.Attack;
import pixelmon.attacks.BattleRegistry;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PacketCreator;
import pixelmon.comm.PacketHandler;
import pixelmon.database.DatabaseHelper;
import pixelmon.database.DatabaseMoves;
import pixelmon.entities.BaseEntityPixelmon;
import pixelmon.entities.EntityEmptyPokeBall;
import pixelmon.entities.EntityPokeBall;
import pixelmon.entities.EntityTrainer;
import pixelmon.entities.IHaveHelper;
import pixelmon.entities.PixelmonEntityList;
import pixelmon.gui.EnumGui;
import pixelmon.gui.GuiChooseStarter;
import pixelmon.gui.GuiHandler;
import pixelmon.gui.GuiPixelmonOverlay;
import pixelmon.storage.BlockPC;
import pixelmon.storage.ComputerManager;
import pixelmon.storage.PokeballManager;
import pixelmon.storage.ServerStorageDisplay;
import pixelmon.storage.PokeballManager.PokeballManagerMode;

import cpw.mods.fml.common.registry.FMLRegistry;

public class mod_Pixelmon extends NetworkMod {
	static Configuration configuration = new Configuration(new File(Minecraft.getMinecraftDir(), "config/pixelmon.cfg"));
	public static int pokemonHealerIdleId = configurationProperties();
	public static int pokemonHealerActiveId;
	public static int thunderStoneOreId;
	public static int leafStoneOreId;
	public static int pcId;

	public static boolean spawnSheep;
	public static boolean spawnCow;
	public static boolean spawnPig;
	public static boolean spawnChicken;
	public static boolean spawnOcelot;
	public static boolean spawnWolf;
	public static boolean spawnCaveSpider;
	public static boolean spawnCreeper;
	public static boolean spawnEnderman;
	public static boolean spawnGhast;
	public static boolean spawnGiantZombie;
	public static boolean spawnGolem;
	public static boolean spawnMooshroom;
	public static boolean spawnPigZombie;
	public static boolean spawnSilverFish;
	public static boolean spawnSkeleton;
	public static boolean spawnSlime;
	public static boolean spawnSpider;
	public static boolean spawnSquid;
	public static boolean spawnZombie;

	public static boolean isInMetric;
	public static boolean isGuiMinimized = false;
	public static int numGroundPokemon;
	public static int numWaterPokemon;
	public static int spawnFrequency;

	private static final int pIMG = ModLoader.addOverride("/gui/items.png", "/pixelmon/image/pokeball.png");
	private static final int gIMG = ModLoader.addOverride("/gui/items.png", "/pixelmon/image/greatball.png");
	private static final int uIMG = ModLoader.addOverride("/gui/items.png", "/pixelmon/image/ultraball.png");
	private static final int mIMG = ModLoader.addOverride("/gui/items.png", "/pixelmon/image/masterball.png");
	public static final Block healerIdle = (new BlockHealer(pokemonHealerIdleId, false)).setHardness(3.5F).setStepSound(Block.soundStoneFootstep)
			.setBlockName("PokeHealer").setRequiresSelfNotify();
	public static final Block healerActive = (new BlockHealer(pokemonHealerActiveId, true)).setHardness(3.5F).setStepSound(Block.soundStoneFootstep)
			.setLightValue(0.875F).setBlockName("healer").setRequiresSelfNotify();
	public static final Block thunderStoneOre = new BlockEvolutionStoneOre(thunderStoneOreId, 0).setHardness(3.0f).setStepSound(Block.soundStoneFootstep)
			.setBlockName("ThunderStoneOre");
	public static final Block leafStoneOre = new BlockEvolutionStoneOre(leafStoneOreId, 1).setHardness(3.0f).setStepSound(Block.soundStoneFootstep)
			.setBlockName("LeafStoneOre");
	public static final Block pc = new BlockPC(pcId).setBlockName("pc").setHardness(2.5f);
	public static final Item pokeBall = new ItemEmptyPokeBall(10000, 1).setItemName("PokeBall").setIconIndex(pIMG);
	public static final Item greatBall = new ItemEmptyPokeBall(10001, 1.5).setItemName("GreatBall").setIconIndex(gIMG);
	public static final Item ultraBall = new ItemEmptyPokeBall(10002, 2).setItemName("UltraBall").setIconIndex(uIMG);
	public static final Item masterBall = new ItemEmptyPokeBall(10003, 255).setItemName("MasterBall").setIconIndex(mIMG);
	public static final Item pokeChecker = new Item(10004).setItemName("PokeChecker")
			.setIconIndex(ModLoader.addOverride("/gui/items.png", "/pixelmon/image/pokechecker.png")).setMaxStackSize(1);
	public static final Item pokeDex = new ItemPokedex(10027).setItemName("Pokedex")
			.setIconIndex(ModLoader.addOverride("/gui/items.png", "/pixelmon/image/pokedex.png")).setMaxStackSize(1);
	public static final Item rareCandy = new Item(10005).setItemName("Rare Candy").setIconIndex(
			ModLoader.addOverride("/gui/items.png", "/pixelmon/image/rarecandy.png"));
	public static final Item potion = new Item(10006).setItemName("Potion").setIconIndex(ModLoader.addOverride("/gui/items.png", "/pixelmon/image/potion.png"))
			.setMaxStackSize(16);
	public static final Item coalDust = new Item(10007).setItemName("CoalDust").setIconIndex(
			ModLoader.addOverride("/gui/items.png", "/pixelmon/image/coaldust.png"));
	public static final Item fireStone = new ItemEvolutionStone(10008, EnumEvolutionStone.FIRESTONE).setItemName("FireStone").setIconIndex(
			ModLoader.addOverride("/gui/items.png", "/pixelmon/image/Firestone.png"));
	public static final Item waterStone = new ItemEvolutionStone(10009, EnumEvolutionStone.WATERSTONE).setItemName("WaterStone").setIconIndex(
			ModLoader.addOverride("/gui/items.png", "/pixelmon/image/Waterstone.png"));
	public static final Item moonStone = new ItemEvolutionStone(10010, EnumEvolutionStone.MOONSTONE).setItemName("MoonStone").setIconIndex(
			ModLoader.addOverride("/gui/items.png", "/pixelmon/image/Moonstone.png"));
	public static final Item thunderStone = new ItemEvolutionStone(10011, EnumEvolutionStone.THUNDERSTONE).setItemName("ThunderStone").setIconIndex(
			ModLoader.addOverride("/gui/items.png", "/pixelmon/image/Thunderstone.png"));
	public static final Item leafStone = new ItemEvolutionStone(10012, EnumEvolutionStone.LEAFSTONE).setItemName("LeafStone").setIconIndex(
			ModLoader.addOverride("/gui/items.png", "/pixelmon/image/Leafstone.png"));
	// 9 ids needed for the 9 stones, shards starting on next open id and are
	// the stone they make's id + 10
	public static final Item thunderStoneShard = new Item(10021).setItemName("ThunderStoneShard").setIconIndex(
			ModLoader.addOverride("/gui/items.png", "/pixelmon/image/Thunderstone_Shard.png"));
	public static final Item leafStoneShard = new Item(10022).setItemName("LeafStoneShard").setIconIndex(
			ModLoader.addOverride("/gui/items.png", "/pixelmon/image/Leafstone_Shard.png"));
	@SuppressWarnings("rawtypes")
	private static CustomSpawner myCustomSpawner;

	public static final KeyBinding minmizePixelmonKey = new KeyBinding("Minimize Pixelmon Overlay", Keyboard.KEY_O);
	public static final KeyBinding sendPixelmonKey = new KeyBinding("Send/Recieve Pixelmon", Keyboard.KEY_P);
	public static final KeyBinding nextPixelmonKey = new KeyBinding("Next Pixelmon", 27);
	public static final KeyBinding previousPixelmonKey = new KeyBinding("Previous Pixelmon", 26);
	public static GuiPixelmonOverlay pixelmonOverlay = new GuiPixelmonOverlay();

	public static final PokeballManager pokeballManager = new PokeballManager();
	public static final ComputerManager computerManager = new ComputerManager();
	public static final ServerStorageDisplay serverStorageDisplay = new ServerStorageDisplay();

	/*
	 * public static final String BLACK = "/2470", DARKBLUE = "/2471", DARKGREEN
	 * = "/2472", DARKAQUA = "/2473", DARKRED = "/2474", PURPLE = "/2475", GOLD
	 * = "/2476", GRAY = "/2477", DARKGRAY = "/2478", INDIGO = "/2479",
	 * BRIGHTGREEN = "/247a", AQUA = "/247b", RED = "/247c", PINK = "/247d",
	 * YELLOW = "/247e", WHITE = "/247f", RANDOM = "/247k";
	 */

	public String getVersion() {
		return "version 1.4.2a for 1.2.5";
	}

	private static int configurationProperties() {
		configuration.load();
		pokemonHealerActiveId = Integer.parseInt(configuration.getOrCreateBlockIdProperty("PokemonHealerActive", 201).value);
		pokemonHealerIdleId = Integer.parseInt(configuration.getOrCreateBlockIdProperty("PokemonHealerIdle", 202).value);
		thunderStoneOreId = Integer.parseInt(configuration.getOrCreateBlockIdProperty("ThunderStoneOre", 203).value);
		leafStoneOreId = Integer.parseInt(configuration.getOrCreateBlockIdProperty("LeafStoneOre", 204).value);
		pcId = Integer.parseInt(configuration.getOrCreateBlockIdProperty("PC", 205).value);

		PixelmonIDList.load(configuration);
		TrainerIDList.load(configuration);

		isInMetric = Boolean.parseBoolean((configuration.getOrCreateBooleanProperty("Metric", "Pokedex", true)).value);

		spawnCaveSpider = Boolean.parseBoolean((configuration.getOrCreateBooleanProperty("SpawnCaveSpider", "Vanilla Spawns", false)).value);
		spawnChicken = Boolean.parseBoolean((configuration.getOrCreateBooleanProperty("SpawnChicken", "Vanilla Spawns", false)).value);
		spawnCow = Boolean.parseBoolean((configuration.getOrCreateBooleanProperty("SpawnCow", "Vanilla Spawns", false)).value);
		spawnCreeper = Boolean.parseBoolean((configuration.getOrCreateBooleanProperty("SpawnCreeper", "Vanilla Spawns", false)).value);
		spawnEnderman = Boolean.parseBoolean((configuration.getOrCreateBooleanProperty("SpawnEnderman", "Vanilla Spawns", false)).value);
		spawnGhast = Boolean.parseBoolean((configuration.getOrCreateBooleanProperty("SpawnGhast", "Vanilla Spawns", false)).value);
		spawnGiantZombie = Boolean.parseBoolean((configuration.getOrCreateBooleanProperty("SpawnGiantZombie", "Vanilla Spawns", false)).value);
		spawnGolem = Boolean.parseBoolean((configuration.getOrCreateBooleanProperty("SpawnGolem", "Vanilla Spawns", false)).value);
		spawnMooshroom = Boolean.parseBoolean((configuration.getOrCreateBooleanProperty("SpawnMooshroom", "Vanilla Spawns", false)).value);
		spawnOcelot = Boolean.parseBoolean((configuration.getOrCreateBooleanProperty("SpawnOcelot", "Vanilla Spawns", false)).value);
		spawnPig = Boolean.parseBoolean((configuration.getOrCreateBooleanProperty("SpawnPig", "Vanilla Spawns", false)).value);
		spawnPigZombie = Boolean.parseBoolean((configuration.getOrCreateBooleanProperty("SpawnPigZombie", "Vanilla Spawns", false)).value);
		spawnSheep = Boolean.parseBoolean((configuration.getOrCreateBooleanProperty("SpawnSheep", "Vanilla Spawns", false)).value);
		spawnSilverFish = Boolean.parseBoolean((configuration.getOrCreateBooleanProperty("SpawnSilverFish", "Vanilla Spawns", false)).value);
		spawnSkeleton = Boolean.parseBoolean((configuration.getOrCreateBooleanProperty("SpawnSkeleton", "Vanilla Spawns", false)).value);
		spawnSlime = Boolean.parseBoolean((configuration.getOrCreateBooleanProperty("SpawnSlime", "Vanilla Spawns", false)).value);
		spawnSpider = Boolean.parseBoolean((configuration.getOrCreateBooleanProperty("SpawnSpider", "Vanilla Spawns", false)).value);
		spawnSquid = Boolean.parseBoolean((configuration.getOrCreateBooleanProperty("SpawnSquid", "Vanilla Spawns", false)).value);
		spawnWolf = Boolean.parseBoolean((configuration.getOrCreateBooleanProperty("SpawnWolf", "Vanilla Spawns", false)).value);
		spawnZombie = Boolean.parseBoolean((configuration.getOrCreateBooleanProperty("SpawnZombie", "Vanilla Spawns", false)).value);

		numGroundPokemon = Integer.parseInt((configuration.getOrCreateIntProperty("NumberNonWaterPixelmonSpawns", "Overall Spawning settings", 100)).value);
		numWaterPokemon = Integer.parseInt((configuration.getOrCreateIntProperty("NumberWaterPixelmonSpawns", "Overall Spawning settings", 100)).value);
		spawnFrequency = Integer.parseInt((configuration.getOrCreateIntProperty("SpawnFrequency", "Overall Spawning settings", 10)).value);
		configuration.save();
		return pokemonHealerIdleId;
	}

	public static void savePokedexProps() {
		Property p = configuration.getOrCreateBooleanProperty("Metric", "Pokedex", true);
		p.value = String.valueOf(isInMetric);
		configuration.save();
	}

	public static void setPokedexProp(int i, boolean b) {
		if (i == 1)
			isInMetric = b;
	}

	private static boolean alreadySet = false;

	private GuiHandler guiHandler;

	public void load() {
		if (!DatabaseHelper.has()) {
			ModLoader.throwException("Can not start Pixelmon without SQLite jar or database!!! Please reinstall!!", new java.lang.Error(
					"Can not start Pixelmon without SQLite jar or database!!! Please reinstall!!"));
		}
		if (!(ModLoader.isModLoaded("mod_MinecraftForge")))
			ModLoader.throwException("Can not start Pixelmon without Minecraft Forge!!! Please download it!!!", new java.lang.Error(
					"Can not start Pixelmon without Minecraft Forge!!! Please download it!!!"));
		if (ModLoader.isModLoaded("mod_pokemobs"))
			System.exit(1);
		try {
			Class.forName("net.minecraft.src.CustomSpawner");
		} catch (ClassNotFoundException e) {
			ModLoader.throwException("Can not start Pixelmon without Dr. Zharks Custom Spawner!!! Please download it!!!", new java.lang.Error(
					"Can not start Pixelmon without Dr. Zharks Custom Spawner!!! Please download it!!!"));
		}
		instance = this;
		guiHandler = new GuiHandler();
		MinecraftForge.setGuiHandler(instance, guiHandler);
		myCustomSpawner = new CustomSpawner();

		ModLoader.setInGUIHook(this, true, true);
		ModLoader.setInGameHook(this, true, true);
		addNames();
		registerEntities();
		addSpawns();
		addRecipes();
		ForgeHooksClient.renderWorldLastHandlers.add(pixelmonOverlay);
		MinecraftForge.registerEntity(BaseEntityPixelmon.class, this, 0, 100, 1, true);
		MinecraftForge.registerSaveHandler(pokeballManager);
		MinecraftForge.registerSaveHandler(computerManager);
		MinecraftForge.registerConnectionHandler(new PacketHandler());
		ModLoader.registerKey(this, nextPixelmonKey, false);
		ModLoader.registerKey(this, previousPixelmonKey, false);
		ModLoader.registerKey(this, sendPixelmonKey, false);
		ModLoader.registerKey(this, minmizePixelmonKey, false);
		alreadySet = true;
	}

	public static int pcFront = ModLoader.addOverride("/terrain.png", "/pixelmon/block/PcFrontInactive.png");

	public void addNames() {
		// ModLoader.addName(pokeBall2, "PokeBall2");
		thunderStoneOre.blockIndexInTexture = Block.blockGold.blockIndexInTexture;
		// placeholder texture
		leafStoneOre.blockIndexInTexture = 145;
		// placeholder texture
		pc.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/pixelmon/block/Pcside.png");
		ModLoader.addName(pokeBall, "PokeBall");
		ModLoader.addName(greatBall, "GreatBall");
		ModLoader.addName(ultraBall, "UltraBall");
		ModLoader.addName(masterBall, "MasterBall");
		ModLoader.addName(pokeChecker, "PokeChecker");
		ModLoader.addName(rareCandy, "Rare Candy");
		ModLoader.addName(potion, "Potion");
		ModLoader.addName(coalDust, "Coal Dust");
		ModLoader.addName(pokeDex, "Pokedex");
		ModLoader.registerBlock(healerIdle);
		ModLoader.registerBlock(healerActive);
		ModLoader.registerBlock(thunderStoneOre);
		ModLoader.registerBlock(leafStoneOre);
		ModLoader.registerBlock(pc);
		ModLoader.addName(healerIdle, "Healer");
		ModLoader.addName(healerActive, "Healer");
		ModLoader.addName(thunderStoneOre, "Thunder Stone Ore");
		ModLoader.addName(leafStoneOre, "Leaf Stone Ore");
		ModLoader.addName(pc, "PC");
		ModLoader.addName(fireStone, "Fire Stone");
		ModLoader.addName(leafStone, "Leaf Stone");
		ModLoader.addName(waterStone, "Water Stone");
		ModLoader.addName(thunderStone, "Thunder Stone");
		ModLoader.addName(moonStone, "Moon Stone");
		ModLoader.addName(thunderStoneShard, "Thunder Stone Shard");
		ModLoader.addName(leafStoneShard, "Leaf Stone Shard");
	}

	public void registerEntities() {
		ModLoader.registerTileEntity(TileEntityPokemonHealer.class, "Healer");
		removeNormalMobsAndCreatures();
		PixelmonEntityList.registerEntities();
		MinecraftForge.registerEntity(EntityEmptyPokeBall.class, this, PixelmonIDList.i, 50, 1, true); PixelmonIDList.i++;
		MinecraftForge.registerEntity(EntityPokeBall.class, this, PixelmonIDList.i, 50, 1, true); PixelmonIDList.i++;
	}

	public void addSpawns() {
		myCustomSpawner.setMaxMobs(30);
		myCustomSpawner.setMaxAnimals(numGroundPokemon);
		myCustomSpawner.setMaxAquatic(numWaterPokemon);

		PixelmonEntityList.addSpawns(myCustomSpawner);

		// ModLoader.addSpawn(EntityTrainer.class, 150, 1, 1,
		// EnumCreatureType.creature, new BiomeGenBase[] {
		// BiomeGenBase.desert, BiomeGenBase.desertHills,
		// BiomeGenBase.extremeHills,
		// BiomeGenBase.extremeHillsEdge, BiomeGenBase.forest,
		// BiomeGenBase.forestHills, BiomeGenBase.frozenOcean,
		// BiomeGenBase.frozenRiver, BiomeGenBase.iceMountains,
		// BiomeGenBase.icePlains, BiomeGenBase.jungle,
		// BiomeGenBase.jungleHills, BiomeGenBase.plains,
		// BiomeGenBase.river, BiomeGenBase.swampland,
		// BiomeGenBase.taiga, BiomeGenBase.taigaHills });

	}

	public void addRecipes() {
		ModLoader.addRecipe(
				new ItemStack(healerIdle),
				new Object[] { "IRI", "RDR", "IRI", Character.valueOf('D'), new ItemStack(Item.diamond), Character.valueOf('I'), Item.ingotIron,
						Character.valueOf('R'), Block.stone });
		ModLoader.addRecipe(new ItemStack(pokeBall, 1), new Object[] { "RRR", "CBC", "III", Character.valueOf('R'), new ItemStack(Item.dyePowder, 1, 1),
				Character.valueOf('B'), Block.button, Character.valueOf('I'), Item.ingotIron, Character.valueOf('C'), coalDust });
		ModLoader.addRecipe(new ItemStack(greatBall), new Object[] { "LLL", "CBC", "III", Character.valueOf('L'), new ItemStack(Item.dyePowder, 1, 4),
				Character.valueOf('C'), coalDust, Character.valueOf('B'), Block.button, Character.valueOf('I'), Item.ingotIron });
		ModLoader.addRecipe(new ItemStack(ultraBall), new Object[] { "GGG", "CBC", "III", Character.valueOf('G'), Item.ingotGold, Character.valueOf('C'),
				coalDust, Character.valueOf('B'), Block.button, Character.valueOf('I'), Item.ingotIron });
		ModLoader.addRecipe(new ItemStack(masterBall), new Object[] { "PPP", "OBO", "DDD", Character.valueOf('P'), new ItemStack(Item.dyePowder, 1, 5),
				Character.valueOf('O'), Block.obsidian, Character.valueOf('B'), Block.button, Character.valueOf('D'), Item.diamond });
		ModLoader.addRecipe(new ItemStack(pokeChecker), new Object[] { " GG", "IIG", "II ", Character.valueOf('G'), Block.glass, Character.valueOf('I'),
				Item.ingotIron });
		ModLoader.addShapelessRecipe(new ItemStack(rareCandy), new Object[] { Item.lightStoneDust, Item.appleGold, Item.sugar });
		ModLoader.addShapelessRecipe(new ItemStack(potion, 4), new Object[] { Item.glassBottle, Item.bucketMilk, Item.wheat });
		ModLoader.addShapelessRecipe(new ItemStack(coalDust, 4), new Object[] { Item.coal });
		ModLoader.addRecipe(new ItemStack(Item.coal, 1), new Object[] { "XX", "XX", Character.valueOf('X'), coalDust });
		ModLoader.addRecipe(new ItemStack(pokeDex, 1),
				new Object[] { "IPI", "DGD", "IRI", Character.valueOf('I'), Item.ingotIron, Character.valueOf('P'), Block.thinGlass, Character.valueOf('D'),
						new ItemStack(Item.dyePowder, 1, 1), Character.valueOf('G'), Block.redstoneLampIdle, Character.valueOf('R'), Item.redstone });
		ModLoader.addRecipe(new ItemStack(thunderStone, 1), new Object[] { "XXX", "XXX", "XXX", Character.valueOf('X'), thunderStoneShard });
		ModLoader.addRecipe(new ItemStack(leafStone, 1), new Object[] { "XXX", "XXX", "XXX", Character.valueOf('X'), leafStoneShard });
		ModLoader.addShapelessRecipe(new ItemStack(pc, 1), new Object[] { Block.dirt });
		// ModLoader.addShapelessRecipe(new ItemStack(pokeBall, 1), new Object[]
		// { Block.dirt, Block.dirt });
		// ModLoader.addShapelessRecipe(new ItemStack(rareCandy, 1), new
		// Object[] { Block.sand });

	}

	private void removeNormalMobsAndCreatures() {
		BiomeGenBase[] allbiomes = new BiomeGenBase[] { BiomeGenBase.beach, BiomeGenBase.desert, BiomeGenBase.desertHills, BiomeGenBase.extremeHills,
				BiomeGenBase.extremeHillsEdge, BiomeGenBase.forest, BiomeGenBase.forestHills, BiomeGenBase.frozenOcean, BiomeGenBase.frozenRiver,
				BiomeGenBase.iceMountains, BiomeGenBase.icePlains, BiomeGenBase.jungle, BiomeGenBase.jungleHills, BiomeGenBase.mushroomIsland,
				BiomeGenBase.mushroomIslandShore, BiomeGenBase.ocean, BiomeGenBase.plains, BiomeGenBase.river, BiomeGenBase.sky, BiomeGenBase.swampland,
				BiomeGenBase.taiga, BiomeGenBase.taigaHills };
		if (!spawnSheep)
			FMLRegistry.removeSpawn(EntitySheep.class, EnumCreatureType.creature, allbiomes);
		if (!spawnCow)
			FMLRegistry.removeSpawn(EntityCow.class, EnumCreatureType.creature, allbiomes);
		if (!spawnPig)
			FMLRegistry.removeSpawn(EntityPig.class, EnumCreatureType.creature, allbiomes);
		if (!spawnChicken)
			FMLRegistry.removeSpawn(EntityChicken.class, EnumCreatureType.creature, allbiomes);
		if (!spawnOcelot)
			FMLRegistry.removeSpawn(EntityOcelot.class, EnumCreatureType.monster, allbiomes);
		if (!spawnWolf)
			FMLRegistry.removeSpawn(EntityWolf.class, EnumCreatureType.creature, allbiomes);
		if (!spawnCaveSpider)
			FMLRegistry.removeSpawn(EntityCaveSpider.class, EnumCreatureType.monster, allbiomes);
		if (!spawnCreeper)
			FMLRegistry.removeSpawn(EntityCreeper.class, EnumCreatureType.monster, allbiomes);
		if (!spawnEnderman)
			FMLRegistry.removeSpawn(EntityEnderman.class, EnumCreatureType.monster, allbiomes);
		if (!spawnGhast)
			FMLRegistry.removeSpawn(EntityGhast.class, EnumCreatureType.monster, allbiomes);
		if (!spawnGiantZombie)
			FMLRegistry.removeSpawn(EntityGiantZombie.class, EnumCreatureType.monster, allbiomes);
		if (!spawnGolem)
			FMLRegistry.removeSpawn(EntityGolem.class, EnumCreatureType.monster, allbiomes);
		if (!spawnMooshroom)
			FMLRegistry.removeSpawn(EntityMooshroom.class, EnumCreatureType.creature, allbiomes);
		if (!spawnPigZombie)
			FMLRegistry.removeSpawn(EntityPigZombie.class, EnumCreatureType.monster, allbiomes);
		if (!spawnSilverFish)
			FMLRegistry.removeSpawn(EntitySilverfish.class, EnumCreatureType.monster, allbiomes);
		if (!spawnSkeleton)
			FMLRegistry.removeSpawn(EntitySkeleton.class, EnumCreatureType.monster, allbiomes);
		if (!spawnSlime)
			FMLRegistry.removeSpawn(EntitySlime.class, EnumCreatureType.monster, allbiomes);
		if (!spawnSpider)
			FMLRegistry.removeSpawn(EntitySpider.class, EnumCreatureType.monster, allbiomes);
		if (!spawnSquid)
			FMLRegistry.removeSpawn(EntitySquid.class, EnumCreatureType.waterCreature, allbiomes);
		if (!spawnZombie)
			FMLRegistry.removeSpawn(EntityZombie.class, EnumCreatureType.monster, allbiomes);
	}

	public int addFuel(int i, int i1) {
		if (i == coalDust.shiftedIndex) {
			return 400;
		}
		return 0;
	}

	public boolean dispenseEntity(World world, double d, double d1, double d2, int i, int j, ItemStack itemstack) {
		return false;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addRenderer(Map map) {
		map.put(EntityEmptyPokeBall.class, new RenderEmptyPokeball());
		map.put(EntityPokeBall.class, new RenderPokeball());
		PixelmonEntityList.addRenderer(map);
	}

	public static int getRandomNumberBetween(int i, int i1) {
		return getRandomNumberBetween(i, i1, true);
	}

	public static int getRandomNumberBetween(int i, int i1, boolean flag) {
		Random rand = new Random();
		int number = 0;
		for (int i2 = -1; !(i2 >= i && i2 <= i1);) {
			i2 = number = rand.nextInt(i1 + 1);
		}
		if (!flag)
			number *= -1;
		return number;
	}

	public boolean onTickInGUI(float f, Minecraft mc, GuiScreen gui) {
		return true;
	}

	public static boolean firstJoin = true;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean onTickInGame(float f, Minecraft mc) {
		if (mc.theWorld.isRemote) {
			if (serverStorageDisplay.count() == 0 && mc.currentScreen == null) {
				Packet250CustomPayload packet = PacketCreator.createPacket(EnumPackets.RegisterPlayer, 0);
				ModLoader.sendPacket(packet);
			}
		}

		if (mc.thePlayer.sleeping) {
			if (mc.theWorld.isRemote) {
				Packet250CustomPayload packet = PacketCreator.createPacket(EnumPackets.HealPokemon, 0);
				ModLoader.sendPacket(packet);				
			} else {
				for (NBTTagCompound nbt : pokeballManager.getPlayerStorage(ModLoader.getMinecraftInstance().thePlayer).getList()) {
					if (nbt != null) {
						nbt.setShort("Health", (short) nbt.getInteger("StatsHP"));
						nbt.setBoolean("IsFainted", false);
						int numMoves = nbt.getInteger("PixelmonNumberMoves");
						for (int i = 0; i < numMoves; i++) {
							nbt.setInteger("PixelmonMovePP" + i, nbt.getInteger("PixelmonMovePPBase" + i));
						}
					}
				}
			}
		}

		if (!mc.theWorld.isRemote && mc.theWorld.worldInfo.getWorldTime() % spawnFrequency == 0L) {
			myCustomSpawner.doCustomSpawning(mc.theWorld, mc.gameSettings.difficulty > 0, true);
			despawnMobs();
		}
		return true;
	}

	private void despawnMobs() {
		PixelmonEntityList.despawnPixelmon(myCustomSpawner);
		removeNormalMobsAndCreatures();
	}

	public static Item getKindOfBallFromBonus(double d, boolean flag) {
		if (d == 1)
			return pokeBall;
		if (d == 1.5)
			return greatBall;
		if (d == 2)
			return ultraBall;
		if (d == 255)
			return masterBall;
		return pokeBall;
	}

	public static Item getBallFromID(int i) {
		if (i == pokeBall.shiftedIndex)
			return pokeBall;
		if (i == greatBall.shiftedIndex)
			return greatBall;
		if (i == ultraBall.shiftedIndex)
			return ultraBall;
		if (i == masterBall.shiftedIndex)
			return masterBall;
		return null;
	}

	public static IHaveHelper getRandomPokemon() {
		try {
			IHaveHelper e = (IHaveHelper) EntityBulbasaur.class.getConstructor(new Class[] { World.class }).newInstance(
					new Object[] { ModLoader.getMinecraftInstance().theWorld });
			return e;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static EntityPokeBall currentPokeball;

	public void keyboardEvent(KeyBinding event) {
		KeyboardHandler.handleKeyboardEvent(event);
	}

	static float spinCount = 0;
	public static NetworkMod instance;
	public static BattleRegistry battleRegistry = new BattleRegistry();

	public static void drawModelToScreen(float size, int xSize, int ySize, int xPos, int yPos, Entity entity, GuiScreen gui, boolean spin) {
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		GL11.glPushMatrix();
		GL11.glTranslatef((float) (xPos), (float) (yPos), 50.0F);
		if (entity.width > entity.height) {
			size = xSize / entity.width / 6;
		} else
			size = xSize / entity.height / 6;
		GL11.glScalef(size, size, size);
		GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
		float var12 = (float) (yPos + 75 - 50) - ySize;
		GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
		RenderHelper.enableStandardItemLighting();
		GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(0.0F, entity.yOffset, 0.0F);
		GL11.glRotatef(100, 0F, 1F, 0F);
		entity.rotationYaw = 90;
		if (spin)
			GL11.glRotatef(spinCount += 0.55F, 0.0F, 1F, 0.0F);
		RenderManager.instance.renderEntityWithPosYaw(entity, 0.0D, 0.0D, 0.0D, 90.0F, 0.0F);
		RenderManager.instance.playerViewY = 180.0F;
		RenderHelper.disableStandardItemLighting();
		GL11.glPopMatrix();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glDisable(GL11.GL_COLOR_MATERIAL);
	}

	public void generateSurface(World world, Random rand, int x, int z) {
		// thunderstone ore
		for (int i = 0; i < 30; i++) {
			int xPos = rand.nextInt(16) + x;
			int zPos = rand.nextInt(16) + z;
			int yPos = rand.nextInt(50) + 75; // generates 75 to 125
			new WorldGenMinable(thunderStoneOre.blockID, 2 + rand.nextInt(2)).generate(world, rand, xPos, yPos, zPos);
		}

		// leafstone ore
		for (int i = 0; i < 20; i++) {
			int xPos = rand.nextInt(16) + x;
			int zPos = rand.nextInt(16) + z;
			int yPos = rand.nextInt(100) + 28;
			new WorldGenLeafStoneOre().generate(world, rand, xPos, yPos, zPos);
		}
	}

	private IHaveHelper LocateSentOutPokemon(int idFromPosition) {
		for (Object e : ModLoader.getMinecraftInstance().theWorld.loadedEntityList) {
			if (e instanceof IHaveHelper) {
				if (((IHaveHelper) e).getHelper().getPokemonId() == idFromPosition)
					return (IHaveHelper) e;
			}
		}
		return null;
	}
}
