package net.solarfall.kingdomsatwar.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.solarfall.kingdomsatwar.KingdomsAtWar;
import net.solarfall.kingdomsatwar.block.custom.MagicBlock;
import net.solarfall.kingdomsatwar.block.custom.VitariumLampBlock;
import net.solarfall.kingdomsatwar.item.ModItems;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(KingdomsAtWar.MOD_ID);

    public static final DeferredBlock<Block> VITARIUM_BLOCK = registerBlock("vitarium_block",
        () -> new Block(BlockBehaviour.Properties.of()
            .strength(4f)
            .requiresCorrectToolForDrops()
            .sound(SoundType.AMETHYST)));

    public static final DeferredBlock<Block> VITARIUM_ORE = registerBlock("vitarium_ore",
        () -> new DropExperienceBlock(UniformInt.of(3, 7), BlockBehaviour.Properties.of()
            .strength(3f)
            .requiresCorrectToolForDrops()
            .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> VITARIUM_DEEPSLATE_ORE = registerBlock("vitarium_deepslate_ore",
        () -> new DropExperienceBlock(UniformInt.of(3, 6), BlockBehaviour.Properties.of()
            .strength(4f)
            .requiresCorrectToolForDrops()
            .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> MAGIC_BLOCK = registerBlock("magic_block",
        () -> new MagicBlock(BlockBehaviour.Properties.of()
            .strength(2f)
            .requiresCorrectToolForDrops()));

    public static final DeferredBlock<StairBlock> VITARIUM_STAIRS = registerBlock("vitarium_stairs",
        () -> new StairBlock(VITARIUM_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.of()
            .strength(2f)
            .requiresCorrectToolForDrops()));
    public static final DeferredBlock<SlabBlock> VITARIUM_SLAB = registerBlock("vitarium_slab",
        () -> new SlabBlock(BlockBehaviour.Properties.of()
            .requiresCorrectToolForDrops()));

    public static final DeferredBlock<PressurePlateBlock> VITARIUM_PRESSURE_PLATE = registerBlock("vitarium_pressure_plate",
        () -> new PressurePlateBlock(BlockSetType.IRON, BlockBehaviour.Properties.of()
            .strength(2f)
            .requiresCorrectToolForDrops()));
    public static final DeferredBlock<ButtonBlock> VITARIUM_BUTTON = registerBlock("vitarium_button",
        () -> new ButtonBlock(BlockSetType.IRON, 20, BlockBehaviour.Properties.of()
            .strength(2f)
            .noCollission()
            .requiresCorrectToolForDrops()));

    public static final DeferredBlock<FenceBlock> VITARIUM_FENCE = registerBlock("vitarium_fence",
        () -> new FenceBlock(BlockBehaviour.Properties.of()
            .strength(2f)
            .requiresCorrectToolForDrops()));
    public static final DeferredBlock<FenceGateBlock> VITARIUM_FENCE_GATE = registerBlock("vitarium_fence_gate",
        () -> new FenceGateBlock(WoodType.ACACIA, BlockBehaviour.Properties.of()
            .strength(2f)
            .requiresCorrectToolForDrops()));
    public static final DeferredBlock<WallBlock> VITARIUM_WALL = registerBlock("vitarium_wall",
        () -> new WallBlock(BlockBehaviour.Properties.of()
            .strength(2f)
            .requiresCorrectToolForDrops()));

    public static final DeferredBlock<DoorBlock> VITARIUM_DOOR = registerBlock("vitarium_door",
        () -> new DoorBlock(BlockSetType.IRON, BlockBehaviour.Properties.of()
            .strength(2f)
            .noOcclusion()
            .requiresCorrectToolForDrops()));
    public static final DeferredBlock<TrapDoorBlock> VITARIUM_TRAPDOOR = registerBlock("vitarium_trapdoor",
        () -> new TrapDoorBlock(BlockSetType.IRON, BlockBehaviour.Properties.of()
            .strength(2f)
            .noOcclusion()
            .requiresCorrectToolForDrops()));

    public static final DeferredBlock<VitariumLampBlock> VITARIUM_LAMP = registerBlock("vitarium_lamp",
        () -> new VitariumLampBlock(BlockBehaviour.Properties.of()
            .strength(2f)
            .requiresCorrectToolForDrops()
            .lightLevel(state -> state.getValue(VitariumLampBlock.CLICKED) ? 15 : 0)));

    //Register a block and its associated item
    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    //Need to register an item associated with the block
    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
