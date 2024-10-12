package net.solarfall.kingdomsatwar.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.solarfall.kingdomsatwar.KingdomsAtWar;
import net.solarfall.kingdomsatwar.block.ModBlocks;
import net.solarfall.kingdomsatwar.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, KingdomsAtWar.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.VITARIUM_DUST.get());
        basicItem(ModItems.VITARIUM_INGOT.get());

        basicItem(ModItems.RADISH.get());
        basicItem(ModItems.CHISEL.get());
        basicItem(ModItems.FROSTFIRE_ICE.get());
        basicItem(ModItems.STARLIGHT_ASHES.get());

        buttonItem(ModBlocks.VITARIUM_BUTTON, ModBlocks.VITARIUM_BLOCK);
        fenceItem(ModBlocks.VITARIUM_FENCE, ModBlocks.VITARIUM_BLOCK);
        wallItem(ModBlocks.VITARIUM_WALL, ModBlocks.VITARIUM_BLOCK);

        basicItem(ModBlocks.VITARIUM_DOOR.asItem());

    }

    public void buttonItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
            .texture("texture", ResourceLocation.fromNamespaceAndPath(KingdomsAtWar.MOD_ID,
                "block/" + baseBlock.getId().getPath()));
    }

    public void fenceItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/fence_inventory"))
            .texture("texture", ResourceLocation.fromNamespaceAndPath(KingdomsAtWar.MOD_ID,
                "block/" + baseBlock.getId().getPath()));
    }

    public void wallItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
            .texture("wall", ResourceLocation.fromNamespaceAndPath(KingdomsAtWar.MOD_ID,
                "block/" + baseBlock.getId().getPath()));
    }
}
