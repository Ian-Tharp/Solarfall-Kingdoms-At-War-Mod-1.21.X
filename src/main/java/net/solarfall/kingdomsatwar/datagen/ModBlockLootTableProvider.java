package net.solarfall.kingdomsatwar.datagen;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.solarfall.kingdomsatwar.block.ModBlocks;
import net.solarfall.kingdomsatwar.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    
    @Override
    protected void generate() {
        dropSelf(ModBlocks.MAGIC_BLOCK.get());
        dropSelf(ModBlocks.VITARIUM_BLOCK.get());

        add(ModBlocks.VITARIUM_ORE.get(),
            block -> createOreDrop(ModBlocks.VITARIUM_ORE.get(), ModItems.VITARIUM_DUST.get()));

        add(ModBlocks.VITARIUM_DEEPSLATE_ORE.get(),
            block -> createMultipleOreDrops(block, ModItems.VITARIUM_DUST.get(), 2, 5));

        dropSelf(ModBlocks.VITARIUM_STAIRS.get());
        dropSelf(ModBlocks.VITARIUM_FENCE.get());
        dropSelf(ModBlocks.VITARIUM_FENCE_GATE.get());
        dropSelf(ModBlocks.VITARIUM_WALL.get());

        add(ModBlocks.VITARIUM_DOOR.get(),
            block -> createDoorTable(ModBlocks.VITARIUM_DOOR.get()));

        dropSelf(ModBlocks.VITARIUM_TRAPDOOR.get());
        dropSelf(ModBlocks.VITARIUM_BUTTON.get());
        dropSelf(ModBlocks.VITARIUM_PRESSURE_PLATE.get());

        add(ModBlocks.VITARIUM_SLAB.get(),
            block -> createSlabItemTable(ModBlocks.VITARIUM_SLAB.get()));

        dropSelf(ModBlocks.VITARIUM_LAMP.get());

    }


    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registryLookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(
            pBlock,
            this.applyExplosionDecay(pBlock, LootItem.lootTableItem(item)
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops))))
                .apply(ApplyBonusCount.addOreBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))
        );
    }


    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
