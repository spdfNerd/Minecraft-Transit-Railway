package com.spdfnerd.mtr.setup;

import com.spdfnerd.mtr.MTR;
import com.spdfnerd.mtr.block.*;
import com.spdfnerd.mtr.data.Train;
import com.spdfnerd.mtr.entity.*;
import com.spdfnerd.mtr.item.ItemDashboard;
import com.spdfnerd.mtr.item.ItemEscalator;
import com.spdfnerd.mtr.item.ItemPSDAPGBase;
import com.spdfnerd.mtr.tileentity.*;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registration {

//	@Override
//	public void onInitialize() {
//
//		ServerSidePacketRegistry.INSTANCE.register(IPacket.ID_STATIONS_AND_ROUTES, PacketTrainDataGuiServer::receiveStationsAndRoutesC2S);
//		ServerSidePacketRegistry.INSTANCE.register(IPacket.ID_PLATFORM, PacketTrainDataGuiServer::receivePlatformC2S);
//
//		ServerTickEvents.START_SERVER_TICK.register(minecraftServer -> minecraftServer.getWorlds().forEach(serverWorld -> {
//			RailwayData railwayData = RailwayData.getInstance(serverWorld);
//			if (railwayData != null) {
//				railwayData.simulateTrains(serverWorld);
//			}
//		}));
//		ServerEntityEvents.ENTITY_LOAD.register((entity, serverWorld) -> {
//			if (entity instanceof ServerPlayerEntity) {
//				final RailwayData railwayData = RailwayData.getInstance(serverWorld);
//				if (railwayData != null) {
//					PacketTrainDataGuiServer.broadcastS2C(serverWorld, railwayData);
//				}
//			}
//		});
//	}

	public static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MTR.MOD_ID);
	public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MTR.MOD_ID);
	public static DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MTR.MOD_ID);
	public static DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, MTR.MOD_ID);

	public static void init() {
		BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
		ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		TILE_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
		ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
	}

	public static final RegistryObject<Item> BRUSH = ITEMS.register("brush", () -> new Item(new Item.Properties().group(ItemGroup.TOOLS).maxStackSize(1)));
	public static final RegistryObject<Item> DASHBOARD = ITEMS.register("dashboard",
			() -> new ItemDashboard(new Item.Properties().group(ItemGroup.TOOLS).maxStackSize(1)));

	public static final RegistryObject<Block> APG_DOOR = BLOCKS.register("apg_door", BlockAPGDoor::new);
	public static final RegistryObject<Item> APG_DOOR_ITEM = ITEMS.register("apg_door", () -> new ItemPSDAPGBase(ItemPSDAPGBase.EnumPSDAPGItem.PSD_APG_DOOR,
			ItemPSDAPGBase.EnumPSDAPGType.APG));
	public static final RegistryObject<Block> APG_GLASS = BLOCKS.register("apg_glass", BlockAPGGlass::new);
	public static final RegistryObject<Item> APG_GLASS_ITEM = ITEMS.register("apg_glass", () -> new ItemPSDAPGBase(ItemPSDAPGBase.EnumPSDAPGItem.PSD_APG_GLASS,
			ItemPSDAPGBase.EnumPSDAPGType.APG));
	public static final RegistryObject<Block> APG_GLASS_END = BLOCKS.register("apg_glass_end", BlockAPGGlassEnd::new);
	public static final RegistryObject<Item> APG_GLASS_END_ITEM = ITEMS.register("apg_glass_end",
			() -> new ItemPSDAPGBase(ItemPSDAPGBase.EnumPSDAPGItem.PSD_APG_GLASS_END, ItemPSDAPGBase.EnumPSDAPGType.APG));
	public static final RegistryObject<Block> CEILING = BLOCKS.register("ceiling", () -> new BlockCeiling(AbstractBlock.Properties.create(Material.IRON,
			MaterialColor.QUARTZ).setRequiresTool().hardnessAndResistance(2f).setLightLevel(value -> 15)));
	public static final RegistryObject<BlockItem> CEILING_ITEM = ITEMS.register("ceiling", () -> new BlockItem(CEILING.get(),
			new Item.Properties().group(ItemGroup.DECORATIONS)));
	public static final RegistryObject<Block> CLOCK = BLOCKS.register("clock", () -> new BlockClock(AbstractBlock.Properties.create(Material.IRON,
			MaterialColor.QUARTZ).setRequiresTool().hardnessAndResistance(2f).setLightLevel(value -> 5)));
	public static final RegistryObject<BlockItem> CLOCK_ITEM = ITEMS.register("clock", () -> new BlockItem(CLOCK.get(),
			new Item.Properties().group(ItemGroup.DECORATIONS)));
	public static final RegistryObject<Block> ESCALATOR_SIDE = BLOCKS.register("escalator_side", BlockEscalatorSide::new);
	public static final RegistryObject<Block> ESCALATOR_STEP = BLOCKS.register("escalator_step", BlockEscalatorStep::new);
	public static final RegistryObject<Item> ESCALATOR_ITEM = ITEMS.register("escalator",
			() -> new ItemEscalator(new Item.Properties().group(ItemGroup.TRANSPORTATION)));
	public static final RegistryObject<Block> LOGO = BLOCKS.register("logo",
			() -> new Block(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(2f).setLightLevel(value -> 10)));
	public static final RegistryObject<BlockItem> LOGO_ITEM = ITEMS.register("logo", () -> new BlockItem(LOGO.get(),
			new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));
	public static final RegistryObject<Block> PIDS_1 = BLOCKS.register("pids_1",
			() -> new BlockPIDS1(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(2f).setLightLevel(value -> 5)));
	public static final RegistryObject<BlockItem> PIDS_1_ITEM = ITEMS.register("pids_1", () -> new BlockItem(PIDS_1.get(),
			new Item.Properties().group(ItemGroup.DECORATIONS)));
	public static final RegistryObject<Block> PLATFORM = BLOCKS.register("platform", () -> new BlockPlatform(AbstractBlock.Properties.create(Material.IRON,
			MaterialColor.YELLOW).setRequiresTool().hardnessAndResistance(2f)));
	public static final RegistryObject<BlockItem> PLATFORM_ITEM = ITEMS.register("platform", () -> new BlockItem(PLATFORM.get(),
			new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));
	public static final RegistryObject<Block> PLATFORM_RAIL = BLOCKS.register("platform_rail",
			() -> new BlockPlatformRail(AbstractBlock.Properties.create(Material.IRON, MaterialColor.BROWN).hardnessAndResistance(2f).setOpaque((state, reader, pos) -> false)));
	public static final RegistryObject<BlockItem> PLATFORM_RAIL_ITEM = ITEMS.register("platform_rail", () -> new BlockItem(PLATFORM_RAIL.get(),
			new Item.Properties().group(ItemGroup.TRANSPORTATION)));
	public static final RegistryObject<Block> PSD_DOOR = BLOCKS.register("psd_door", BlockPSDDoor::new);
	public static final RegistryObject<Item> PSD_DOOR_ITEM = ITEMS.register("psd_door", () -> new ItemPSDAPGBase(ItemPSDAPGBase.EnumPSDAPGItem.PSD_APG_DOOR,
			ItemPSDAPGBase.EnumPSDAPGType.PSD));
	public static final RegistryObject<Block> PSD_GLASS = BLOCKS.register("psd_glass", BlockPSDGlass::new);
	public static final RegistryObject<Item> PSD_GLASS_ITEM = ITEMS.register("psd_glass", () -> new ItemPSDAPGBase(ItemPSDAPGBase.EnumPSDAPGItem.PSD_APG_GLASS,
			ItemPSDAPGBase.EnumPSDAPGType.PSD));
	public static final RegistryObject<Block> PSD_GLASS_END = BLOCKS.register("psd_glass_end", BlockPSDGlassEnd::new);
	public static final RegistryObject<Item> PSD_GLASS_END_ITEM = ITEMS.register("psd_glass_end",
			() -> new ItemPSDAPGBase(ItemPSDAPGBase.EnumPSDAPGItem.PSD_APG_GLASS_END, ItemPSDAPGBase.EnumPSDAPGType.PSD));
	public static final RegistryObject<Block> PSD_TOP = BLOCKS.register("psd_top", BlockPSDTop::new);
	public static final RegistryObject<Block> STATION_COLOUR_ANDESITE = BLOCKS.register("station_color_andesite",
			() -> new BlockStationColor(AbstractBlock.Properties.from(Blocks.ANDESITE)));
	public static final RegistryObject<BlockItem> STATION_COLOUR_ANDESITE_ITEM = ITEMS.register("station_color_andesite",
			() -> new BlockItem(STATION_COLOUR_ANDESITE.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));
	public static final RegistryObject<Block> STATION_COLOUR_BEDROCK = BLOCKS.register("station_color_bedrock",
			() -> new BlockStationColor(AbstractBlock.Properties.from(Blocks.BEDROCK)));
	public static final RegistryObject<BlockItem> STATION_COLOUR_BEDROCK_ITEM = ITEMS.register("station_color_bedrock",
			() -> new BlockItem(STATION_COLOUR_BEDROCK.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));
	public static final RegistryObject<Block> STATION_COLOUR_BIRCH_WOOD = BLOCKS.register("station_color_birch_wood",
			() -> new BlockStationColor(AbstractBlock.Properties.from(Blocks.BIRCH_WOOD)));
	public static final RegistryObject<BlockItem> STATION_COLOUR_BIRCH_WOOD_ITEM = ITEMS.register("station_color_birch_wood",
			() -> new BlockItem(STATION_COLOUR_BIRCH_WOOD.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));
	public static final RegistryObject<Block> STATION_COLOUR_CHISELED_STONE_BRICKS = BLOCKS.register("station_color_chiseled_stone_bricks",
			() -> new BlockStationColor(AbstractBlock.Properties.from(Blocks.CHISELED_STONE_BRICKS)));
	public static final RegistryObject<BlockItem> STATION_COLOUR_CHISELED_STONE_BRICKS_ITEM = ITEMS.register("station_color_chiseled_stone_bricks",
			() -> new BlockItem(STATION_COLOUR_CHISELED_STONE_BRICKS.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));
	public static final RegistryObject<Block> STATION_COLOUR_CLAY = BLOCKS.register("station_color_clay",
			() -> new BlockStationColor(AbstractBlock.Properties.from(Blocks.CLAY)));
	public static final RegistryObject<BlockItem> STATION_COLOUR_CLAY_ITEM = ITEMS.register("station_color_clay",
			() -> new BlockItem(STATION_COLOUR_CLAY.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));
	public static final RegistryObject<Block> STATION_COLOUR_COAL_ORE = BLOCKS.register("station_color_coal_ore",
			() -> new BlockStationColor(AbstractBlock.Properties.from(Blocks.COAL_ORE)));
	public static final RegistryObject<BlockItem> STATION_COLOUR_COAL_ORE_ITEM = ITEMS.register("station_color_coal_ore",
			() -> new BlockItem(STATION_COLOUR_COAL_ORE.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));
	public static final RegistryObject<Block> STATION_COLOUR_COBBLESTONE = BLOCKS.register("station_color_cobblestone",
			() -> new BlockStationColor(AbstractBlock.Properties.from(Blocks.COBBLESTONE)));
	public static final RegistryObject<BlockItem> STATION_COLOUR_COBBLESTONE_ITEM = ITEMS.register("station_color_cobblestone",
			() -> new BlockItem(STATION_COLOUR_COBBLESTONE.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));
	public static final RegistryObject<Block> STATION_COLOUR_CONCRETE = BLOCKS.register("station_color_concrete",
			() -> new BlockStationColor(AbstractBlock.Properties.from(Blocks.WHITE_CONCRETE)));
	public static final RegistryObject<BlockItem> STATION_COLOUR_CONCRETE_ITEM = ITEMS.register("station_color_concrete",
			() -> new BlockItem(STATION_COLOUR_CONCRETE.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));
	public static final RegistryObject<Block> STATION_COLOUR_CONCRETE_POWDER = BLOCKS.register("station_color_concrete_powder",
			() -> new BlockStationColor(AbstractBlock.Properties.from(Blocks.WHITE_CONCRETE_POWDER)));
	public static final RegistryObject<BlockItem> STATION_COLOUR_CONCRETE_POWDER_ITEM = ITEMS.register("station_color_concrete_powder",
			() -> new BlockItem(STATION_COLOUR_CONCRETE_POWDER.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));
	public static final RegistryObject<Block> STATION_COLOUR_CRACKED_STONE_BRICKS = BLOCKS.register("station_color_cracked_stone_bricks",
			() -> new BlockStationColor(AbstractBlock.Properties.from(Blocks.CRACKED_STONE_BRICKS)));
	public static final RegistryObject<BlockItem> STATION_COLOUR_CRACKED_STONE_BRICKS_ITEM = ITEMS.register("station_color_cracked_stone_bricks",
			() -> new BlockItem(STATION_COLOUR_CRACKED_STONE_BRICKS.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));
	public static final RegistryObject<Block> STATION_COLOUR_DARK_PRISMARINE = BLOCKS.register("station_color_dark_prismarine",
			() -> new BlockStationColor(AbstractBlock.Properties.from(Blocks.DARK_PRISMARINE)));
	public static final RegistryObject<BlockItem> STATION_COLOUR_DARK_PRISMARINE_ITEM = ITEMS.register("station_color_dark_prismarine",
			() -> new BlockItem(STATION_COLOUR_DARK_PRISMARINE.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));
	public static final RegistryObject<Block> STATION_COLOUR_DIORITE = BLOCKS.register("station_color_diorite",
			() -> new BlockStationColor(AbstractBlock.Properties.from(Blocks.DIORITE)));
	public static final RegistryObject<BlockItem> STATION_COLOUR_DIORITE_ITEM = ITEMS.register("station_color_diorite",
			() -> new BlockItem(STATION_COLOUR_DIORITE.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));
	public static final RegistryObject<Block> STATION_COLOUR_GRAVEL = BLOCKS.register("station_color_gravel",
			() -> new BlockStationColor(AbstractBlock.Properties.from(Blocks.GRAVEL)));
	public static final RegistryObject<BlockItem> STATION_COLOUR_GRAVEL_ITEM = ITEMS.register("station_color_gravel",
			() -> new BlockItem(STATION_COLOUR_GRAVEL.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));
	public static final RegistryObject<Block> STATION_COLOUR_IRON_BLOCK = BLOCKS.register("station_color_iron_block",
			() -> new BlockStationColor(AbstractBlock.Properties.from(Blocks.IRON_BLOCK)));
	public static final RegistryObject<BlockItem> STATION_COLOUR_IRON_BLOCK_ITEM = ITEMS.register("station_color_iron_block",
			() -> new BlockItem(STATION_COLOUR_IRON_BLOCK.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));
	public static final RegistryObject<Block> STATION_COLOUR_METAL = BLOCKS.register("station_color_metal",
			() -> new BlockStationColor(AbstractBlock.Properties.from(LOGO.get())));
	public static final RegistryObject<BlockItem> STATION_COLOUR_METAL_ITEM = ITEMS.register("station_color_metal",
			() -> new BlockItem(STATION_COLOUR_METAL.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));
	public static final RegistryObject<Block> STATION_COLOUR_PLANKS = BLOCKS.register("station_color_planks",
			() -> new BlockStationColor(AbstractBlock.Properties.from(Blocks.OAK_PLANKS)));
	public static final RegistryObject<BlockItem> STATION_COLOUR_PLANKS_ITEM = ITEMS.register("station_color_planks",
			() -> new BlockItem(STATION_COLOUR_PLANKS.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));
	public static final RegistryObject<Block> STATION_COLOUR_POLISHED_ANDESITE = BLOCKS.register("station_color_polished_andesite",
			() -> new BlockStationColor(AbstractBlock.Properties.from(Blocks.POLISHED_ANDESITE)));
	public static final RegistryObject<BlockItem> STATION_COLOUR_POLISHED_ANDESITE_ITEM = ITEMS.register("station_color_polished_andesite",
			() -> new BlockItem(STATION_COLOUR_POLISHED_ANDESITE.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));
	public static final RegistryObject<Block> STATION_COLOUR_POLISHED_DIORITE = BLOCKS.register("station_color_polished_diorite",
			() -> new BlockStationColor(AbstractBlock.Properties.from(Blocks.POLISHED_DIORITE)));
	public static final RegistryObject<BlockItem> STATION_COLOUR_POLISHED_DIORITE_ITEM = ITEMS.register("station_color_polished_diorite",
			() -> new BlockItem(STATION_COLOUR_POLISHED_DIORITE.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));
	public static final RegistryObject<Block> STATION_COLOUR_SMOOTH_STONE = BLOCKS.register("station_color_smooth_stone",
			() -> new BlockStationColor(AbstractBlock.Properties.from(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<BlockItem> STATION_COLOUR_SMOOTH_STONE_ITEM = ITEMS.register("station_color_smooth_stone",
			() -> new BlockItem(STATION_COLOUR_SMOOTH_STONE.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));
	public static final RegistryObject<Block> STATION_COLOUR_STAINED_GLASS = BLOCKS.register("station_color_stained_glass",
			() -> new BlockStationColor(AbstractBlock.Properties.from(Blocks.WHITE_STAINED_GLASS)));
	public static final RegistryObject<BlockItem> STATION_COLOUR_STAINED_GLASS_ITEM = ITEMS.register("station_color_stained_glass",
			() -> new BlockItem(STATION_COLOUR_STAINED_GLASS.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));
	public static final RegistryObject<Block> STATION_COLOUR_STONE = BLOCKS.register("station_color_stone",
			() -> new BlockStationColor(AbstractBlock.Properties.from(Blocks.STONE)));
	public static final RegistryObject<BlockItem> STATION_COLOUR_STONE_ITEM = ITEMS.register("station_color_stone",
			() -> new BlockItem(STATION_COLOUR_STONE.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));
	public static final RegistryObject<Block> STATION_COLOUR_STONE_BRICKS = BLOCKS.register("station_color_stone_bricks",
			() -> new BlockStationColor(AbstractBlock.Properties.from(Blocks.STONE_BRICKS)));
	public static final RegistryObject<BlockItem> STATION_COLOUR_STONE_BRICKS_ITEM = ITEMS.register("station_color_stone_bricks",
			() -> new BlockItem(STATION_COLOUR_STONE_BRICKS.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));
	public static final RegistryObject<Block> STATION_COLOUR_WOOL = BLOCKS.register("station_color_wool",
			() -> new BlockStationColor(AbstractBlock.Properties.from(Blocks.WHITE_WOOL)));
	public static final RegistryObject<BlockItem> STATION_COLOUR_WOOL_ITEM = ITEMS.register("station_color_wool",
			() -> new BlockItem(STATION_COLOUR_WOOL.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));
	public static final RegistryObject<Block> STATION_NAME_TALL_BLOCK = BLOCKS.register("station_name_tall_block", BlockStationNameTallBlock::new);
	public static final RegistryObject<BlockItem> STATION_NAME_TALL_BLOCK_ITEM = ITEMS.register("station_name_tall_block",
			() -> new BlockItem(STATION_NAME_TALL_BLOCK.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));
	public static final RegistryObject<Block> STATION_NAME_TALL_WALL = BLOCKS.register("station_name_tall_wall", BlockStationNameTallWall::new);
	public static final RegistryObject<BlockItem> STATION_NAME_TALL_WALL_ITEM = ITEMS.register("station_name_tall_wall",
			() -> new BlockItem(STATION_NAME_TALL_WALL.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));
	public static final RegistryObject<Block> STATION_NAME_WALL = BLOCKS.register("station_name_wall",
			() -> new BlockStationNameWall(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(2f).setOpaque((state, reader, pos) -> false)));
	public static final RegistryObject<BlockItem> STATION_NAME_WALL_ITEM = ITEMS.register("station_name_wall", () -> new BlockItem(STATION_NAME_WALL.get(),
			new Item.Properties().group(ItemGroup.DECORATIONS)));
	public static final RegistryObject<Block> STATION_POLE = BLOCKS.register("station_pole",
			() -> new BlockStationPole(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(2f).setOpaque((state, reader, pos) -> false)));
	public static final RegistryObject<BlockItem> STATION_POLE_ITEM = ITEMS.register("station_pole", () -> new BlockItem(STATION_POLE.get(),
			new Item.Properties().group(ItemGroup.DECORATIONS)));

	public static final RegistryObject<TileEntityType<ClockTileEntity>> CLOCK_TILE_ENTITY = TILE_ENTITIES.register("clock",
			() -> TileEntityType.Builder.create(ClockTileEntity::new, CLOCK.get()).build(null));
	public static final RegistryObject<TileEntityType<PSDTopTileEntity>> PSD_TOP_TILE_ENTITY = TILE_ENTITIES.register("psd_top",
			() -> TileEntityType.Builder.create(PSDTopTileEntity::new, PSD_TOP.get()).build(null));
	public static final RegistryObject<TileEntityType<APGGlassTileEntity>> APG_GLASS_TILE_ENTITY = TILE_ENTITIES.register("apg_glass",
			() -> TileEntityType.Builder.create(APGGlassTileEntity::new, APG_GLASS.get()).build(null));
	public static final RegistryObject<TileEntityType<StationNameTileEntity>> STATION_NAME_WALL_TILE_ENTITY = TILE_ENTITIES.register("station_name_wall",
			() -> TileEntityType.Builder.create(StationNameTileEntity::new, STATION_NAME_WALL.get()).build(null));
	public static final RegistryObject<TileEntityType<StationNameTallBlockTileEntity>> STATION_NAME_TALL_BLOCK_TILE_ENTITY = TILE_ENTITIES.register(
			"station_name_tall_block", () -> TileEntityType.Builder.create(StationNameTallBlockTileEntity::new, STATION_NAME_TALL_BLOCK.get()).build(null));
	public static final RegistryObject<TileEntityType<StationNameTallWallTileEntity>> STATION_NAME_TALL_WALL_TILE_ENTITY = TILE_ENTITIES.register(
			"station_name_tall_wall", () -> TileEntityType.Builder.create(StationNameTallWallTileEntity::new, STATION_NAME_TALL_WALL.get()).build(null));

	public static final RegistryObject<EntityType<EntityMinecart>> MINECART = ENTITIES.register("minecart",
			() -> EntityType.Builder.<EntityMinecart>create(EntityMinecart::new, EntityClassification.MISC)
			.size(Train.TrainType.MINECART.getLength(), 1)
			.build("minecart"));
	public static final RegistryObject<EntityType<EntitySP1900>> SP1900 = ENTITIES.register("sp1900",
			() -> EntityType.Builder.<EntitySP1900>create(EntitySP1900::new, EntityClassification.MISC)
			.size(Train.TrainType.SP1900.getLength(), 4)
			.build("sp1900"));
	public static final RegistryObject<EntityType<EntitySP1900Mini>> SP1900_MINI = ENTITIES.register("sp1900_mini",
			() -> EntityType.Builder.<EntitySP1900Mini>create(EntitySP1900Mini::new, EntityClassification.MISC)
			.size(Train.TrainType.SP1900_MINI.getLength(), 4)
			.build("sp1900_mini"));
	public static final RegistryObject<EntityType<EntityMTrain>> M_TRAIN = ENTITIES.register("m_train",
			() -> EntityType.Builder.<EntityMTrain>create(EntityMTrain::new, EntityClassification.MISC)
			.size(Train.TrainType.SP1900.getLength(), 4)
			.build("m_train"));
	public static final RegistryObject<EntityType<EntityMTrainMini>> M_TRAIN_MINI = ENTITIES.register("m_train_mini",
			() -> EntityType.Builder.<EntityMTrainMini>create(EntityMTrainMini::new, EntityClassification.MISC)
			.size(Train.TrainType.SP1900_MINI.getLength(), 4)
			.build("m_train_mini"));
	public static final RegistryObject<EntityType<EntityLightRail1>> LIGHT_RAIL_1 = null; // ENTITIES.register("light_rail_1",
//			() -> EntityType.Builder.create(EntityLightRail1::new, EntityClassification.MISC)
//			.size(Train.TrainType.LIGHT_RAIL_1.getLength(), 4)
//			.build("light_rail_1"));

}
