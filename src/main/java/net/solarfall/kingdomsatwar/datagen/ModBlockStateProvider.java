package net.solarfall.kingdomsatwar.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.solarfall.kingdomsatwar.KingdomsAtWar;
import net.solarfall.kingdomsatwar.block.ModBlocks;
import net.solarfall.kingdomsatwar.block.custom.VitariumLampBlock;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, KingdomsAtWar.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.VITARIUM_BLOCK);
        blockWithItem(ModBlocks.MAGIC_BLOCK);
        blockWithItem(ModBlocks.VITARIUM_ORE);
        blockWithItem(ModBlocks.VITARIUM_DEEPSLATE_ORE);

        stairsBlock(ModBlocks.VITARIUM_STAIRS.get(), blockTexture(ModBlocks.VITARIUM_BLOCK.get()));
        slabBlock(ModBlocks.VITARIUM_SLAB.get(), blockTexture(ModBlocks.VITARIUM_BLOCK.get()), blockTexture(ModBlocks.VITARIUM_BLOCK.get()));
        buttonBlock(ModBlocks.VITARIUM_BUTTON.get(), blockTexture(ModBlocks.VITARIUM_BLOCK.get()));
        pressurePlateBlock(ModBlocks.VITARIUM_PRESSURE_PLATE.get(), blockTexture(ModBlocks.VITARIUM_BLOCK.get()));

        fenceBlock(ModBlocks.VITARIUM_FENCE.get(), blockTexture(ModBlocks.VITARIUM_BLOCK.get()));
        fenceGateBlock(ModBlocks.VITARIUM_FENCE_GATE.get(), blockTexture(ModBlocks.VITARIUM_BLOCK.get()));
        wallBlock(ModBlocks.VITARIUM_WALL.get(), blockTexture(ModBlocks.VITARIUM_BLOCK.get()));

        doorBlockWithRenderType(ModBlocks.VITARIUM_DOOR.get(), modLoc("block/vitarium_door_bottom"), modLoc("block/vitarium_door_top"), "cutout");
        trapdoorBlockWithRenderType(ModBlocks.VITARIUM_TRAPDOOR.get(), modLoc("block/vitarium_trapdoor"), true, "cutout");

        blockItem(ModBlocks.VITARIUM_STAIRS);
        blockItem(ModBlocks.VITARIUM_SLAB);
        blockItem(ModBlocks.VITARIUM_PRESSURE_PLATE);
        blockItem(ModBlocks.VITARIUM_FENCE_GATE);
        blockItem(ModBlocks.VITARIUM_TRAPDOOR, "_bottom");

        customLamp();

    }
    
    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("kingdomsatwar:block/" + deferredBlock.getId().getPath()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("kingdomsatwar:block/" + deferredBlock.getId().getPath() + appendix));
    }

    // Extend later for other lamps
    private void customLamp() {
        getVariantBuilder(ModBlocks.VITARIUM_LAMP.get()).forAllStates(state -> {
            if (state.getValue(VitariumLampBlock.CLICKED)) {
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("vitarium_lamp_on",
                    ResourceLocation.fromNamespaceAndPath(KingdomsAtWar.MOD_ID, "block/" + "vitarium_lamp_on")))};
            }
            else {
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("vitarium_lamp_off",
                    ResourceLocation.fromNamespaceAndPath(KingdomsAtWar.MOD_ID, "block/" + "vitarium_lamp_off")))};
            }
        });
        simpleBlockItem(ModBlocks.VITARIUM_LAMP.get(), models().cubeAll("vitarium_lamp_on",
            ResourceLocation.fromNamespaceAndPath(KingdomsAtWar.MOD_ID, "block/" + "vitarium_lamp_on")));
    }
}
