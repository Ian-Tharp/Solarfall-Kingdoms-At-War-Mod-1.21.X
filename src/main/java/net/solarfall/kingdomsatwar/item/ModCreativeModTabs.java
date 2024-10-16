package net.solarfall.kingdomsatwar.item;

import java.util.function.Supplier;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.solarfall.kingdomsatwar.block.ModBlocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.solarfall.kingdomsatwar.KingdomsAtWar;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, KingdomsAtWar.MOD_ID);

    // This tab comes first before the blocks tab
    public static final Supplier<CreativeModeTab> KINGDOMS_AT_WAR_ITEMS_TAB = CREATIVE_MODE_TABS.register("kingdoms_at_war_items_tab", () -> 
        CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItems.VITARIUM_INGOT.get()))
            .title(Component.translatable("creativetab.kingdoms_at_war.kingdoms_at_war_items"))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(ModItems.VITARIUM_INGOT);
                output.accept(ModItems.VITARIUM_DUST);
                output.accept(ModItems.CHISEL);
                output.accept(ModItems.RADISH);
                output.accept(ModItems.FROSTFIRE_ICE);
                output.accept(ModItems.STARLIGHT_ASHES);
            })
        .build()
    );

    // This tab comes after the items tab
    public static final Supplier<CreativeModeTab> KINGDOMS_AT_WAR_BLOCKS_TAB = CREATIVE_MODE_TABS.register("kingdoms_at_war_blocks_tab", () -> 
        CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModBlocks.VITARIUM_ORE))
            .withTabsBefore(ResourceLocation.fromNamespaceAndPath(KingdomsAtWar.MOD_ID, "kingdoms_at_war_items_tab"))
            .title(Component.translatable("creativetab.kingdoms_at_war.kingdoms_at_war_blocks"))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(ModBlocks.VITARIUM_ORE);
                output.accept(ModBlocks.VITARIUM_DEEPSLATE_ORE);
                output.accept(ModBlocks.VITARIUM_BLOCK);
                
                output.accept(ModBlocks.MAGIC_BLOCK);

                output.accept(ModBlocks.VITARIUM_STAIRS);
                output.accept(ModBlocks.VITARIUM_SLAB);

                output.accept(ModBlocks.VITARIUM_FENCE);
                output.accept(ModBlocks.VITARIUM_FENCE_GATE);
                output.accept(ModBlocks.VITARIUM_WALL);

                output.accept(ModBlocks.VITARIUM_DOOR);
                output.accept(ModBlocks.VITARIUM_TRAPDOOR);

                output.accept(ModBlocks.VITARIUM_BUTTON);
                output.accept(ModBlocks.VITARIUM_PRESSURE_PLATE);

                output.accept(ModBlocks.VITARIUM_LAMP);
            })
        .build()
    );

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
