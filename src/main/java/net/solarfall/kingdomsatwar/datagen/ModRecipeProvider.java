package net.solarfall.kingdomsatwar.datagen;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.solarfall.kingdomsatwar.KingdomsAtWar;
import net.solarfall.kingdomsatwar.block.ModBlocks;
import net.solarfall.kingdomsatwar.item.ModItems;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        List<ItemLike> VITARIUM_SMELTABLES = List.of(
            ModItems.VITARIUM_DUST,
            ModBlocks.VITARIUM_ORE,
            ModBlocks.VITARIUM_DEEPSLATE_ORE
        );

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.VITARIUM_BLOCK.get())
            .pattern("###")
            .pattern("###")
            .pattern("###")
            .define('#', ModItems.VITARIUM_INGOT.get())
            .unlockedBy("has_vitarium_ingot", has(ModItems.VITARIUM_INGOT.get()))
            .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.VITARIUM_INGOT.get(), 9)
            .requires(ModBlocks.VITARIUM_BLOCK.get())
            .unlockedBy("has_vitarium_block", has(ModBlocks.VITARIUM_BLOCK.get()))
            .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.VITARIUM_INGOT.get(), 18)
            .requires(ModBlocks.MAGIC_BLOCK.get())
            .unlockedBy("has_magic_block", has(ModBlocks.MAGIC_BLOCK.get()))
            .save(recipeOutput, "kingdomsatwar:vitarium_ingot_from_magic_block"); // Pass in ID on save, same result but different recipe name

        oreSmelting(recipeOutput, VITARIUM_SMELTABLES, RecipeCategory.MISC, ModItems.VITARIUM_INGOT.get(), 0.25f, 200, "vitarium_ingot");
        oreBlasting(recipeOutput, VITARIUM_SMELTABLES, RecipeCategory.MISC, ModItems.VITARIUM_INGOT.get(), 0.25f, 100, "vitarium_ingot");


        stairBuilder(ModBlocks.VITARIUM_STAIRS.get(), Ingredient.of(ModItems.VITARIUM_INGOT)).group("vitarium")
            .unlockedBy("has_vitarium", has(ModItems.VITARIUM_INGOT)).save(recipeOutput);
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.VITARIUM_SLAB.get(), ModItems.VITARIUM_INGOT.get());
        
        buttonBuilder(ModBlocks.VITARIUM_BUTTON.get(), Ingredient.of(ModItems.VITARIUM_INGOT.get())).group("vitarium")
            .unlockedBy("has_vitarium", has(ModItems.VITARIUM_INGOT)).save(recipeOutput);
        pressurePlate(recipeOutput, ModBlocks.VITARIUM_PRESSURE_PLATE.get(), ModItems.VITARIUM_INGOT.get());
        
        fenceBuilder(ModBlocks.VITARIUM_FENCE.get(), Ingredient.of(ModItems.VITARIUM_INGOT.get())).group("vitarium")
            .unlockedBy("has_vitarium", has(ModItems.VITARIUM_INGOT)).save(recipeOutput);
        fenceGateBuilder(ModBlocks.VITARIUM_FENCE_GATE.get(), Ingredient.of(ModItems.VITARIUM_INGOT.get())).group("vitarium")
            .unlockedBy("has_vitarium", has(ModItems.VITARIUM_INGOT)).save(recipeOutput);
        wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.VITARIUM_WALL.get(), ModItems.VITARIUM_INGOT.get());

        doorBuilder(ModBlocks.VITARIUM_DOOR.get(), Ingredient.of(ModItems.VITARIUM_INGOT.get())).group("vitarium")
            .unlockedBy("has_vitarium", has(ModItems.VITARIUM_INGOT.get())).save(recipeOutput);
        trapdoorBuilder(ModBlocks.VITARIUM_TRAPDOOR.get(), Ingredient.of(ModItems.VITARIUM_INGOT.get())).group("vitarium")
            .unlockedBy("has_vitarium", has(ModItems.VITARIUM_INGOT.get())).save(recipeOutput);
    }

    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
        float pExperience, int pCookingTIme, String pGroup) {

        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult, pExperience,
            pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
        float pExperience, int pCookingTime, String pGroup) {

        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult, pExperience,
            pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer,
        AbstractCookingRecipe.Factory<T> factory, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience,
        int pCookingTime, String pGroup, String pRecipeName) {
            
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory)
            .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
            .save(recipeOutput, KingdomsAtWar.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}
