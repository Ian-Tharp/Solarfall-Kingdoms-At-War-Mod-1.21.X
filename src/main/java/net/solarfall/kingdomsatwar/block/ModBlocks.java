package net.solarfall.kingdomsatwar.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.solarfall.kingdomsatwar.KingdomsAtWar;
import net.solarfall.kingdomsatwar.block.custom.MagicBlock;
import net.solarfall.kingdomsatwar.item.ModItems;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(KingdomsAtWar.MOD_ID);

    public static final DeferredBlock<Block> VITARIUM_BLOCK = registerBlock("vitarium_block",
        () -> new Block(BlockBehaviour.Properties.of()
            .strength(4f)
            .requiresCorrectToolForDrops()
            .sound(SoundType.AMETHYST)
        )
    );

    public static final DeferredBlock<Block> VITARIUM_ORE = registerBlock("vitarium_ore",
        () -> new DropExperienceBlock(UniformInt.of(3, 7),
            BlockBehaviour.Properties.of()
            .strength(3f)
            .requiresCorrectToolForDrops()
            .sound(SoundType.STONE)
        )
    );

    public static final DeferredBlock<Block> VITARIUM_DEEPSLATE_ORE = registerBlock("vitarium_deepslate_ore",
        () -> new DropExperienceBlock(UniformInt.of(3, 6),
            BlockBehaviour.Properties.of()
            .strength(4f)
            .requiresCorrectToolForDrops()
            .sound(SoundType.STONE)
        )
    );

    public static final DeferredBlock<Block> MAGIC_BLOCK = registerBlock("magic_block",
        () -> new MagicBlock(BlockBehaviour.Properties.of()
            .strength(2f)
            .requiresCorrectToolForDrops()
        )
    );

    


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
