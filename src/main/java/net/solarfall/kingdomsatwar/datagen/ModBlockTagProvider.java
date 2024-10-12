package net.solarfall.kingdomsatwar.datagen;

import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.Nullable;

import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.core.HolderLookup;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.solarfall.kingdomsatwar.KingdomsAtWar;
import net.solarfall.kingdomsatwar.block.ModBlocks;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, KingdomsAtWar.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .add(ModBlocks.VITARIUM_ORE.get())
            .add(ModBlocks.MAGIC_BLOCK.get())
            .add(ModBlocks.VITARIUM_BLOCK.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
            .add(ModBlocks.VITARIUM_DEEPSLATE_ORE.get());

        tag(BlockTags.FENCES).add(ModBlocks.VITARIUM_FENCE.get());
        tag(BlockTags.FENCE_GATES).add(ModBlocks.VITARIUM_FENCE_GATE.get());
        tag(BlockTags.WALLS).add(ModBlocks.VITARIUM_WALL.get());
    }
}
