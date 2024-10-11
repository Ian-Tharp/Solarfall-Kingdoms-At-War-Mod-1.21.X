package net.solarfall.kingdomsatwar.item;


import net.solarfall.kingdomsatwar.KingdomsAtWar;
import net.solarfall.kingdomsatwar.item.custom.ChiselItem;
import net.solarfall.kingdomsatwar.item.custom.FuelItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;


public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(KingdomsAtWar.MOD_ID);

    public static final DeferredItem<Item> VITARIUM_DUST = ITEMS.register("vitarium_dust", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> VITARIUM_INGOT = ITEMS.register("vitarium_ingot", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> CHISEL = ITEMS.register("chisel", () -> new ChiselItem(new Item.Properties().durability(32)));

    public static final DeferredItem<Item> RADISH = ITEMS.register("radish", () -> new Item(new Item.Properties().food(ModFoodProperties.RADISH))
    {
        @Override
        public void appendHoverText(net.minecraft.world.item.ItemStack stack, TooltipContext context,
            java.util.List<net.minecraft.network.chat.Component> tooltipComponents, net.minecraft.world.item.TooltipFlag tooltipFlag) {
            tooltipComponents.add(Component.translatable("tooltip.kingdomsatwar.radish.tooltip"));
            super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        }
    });

    public static final DeferredItem<Item> FROSTFIRE_ICE = ITEMS.register("frostfire_ice", () -> new FuelItem(new Item.Properties(), 800));
    public static final DeferredItem<Item> STARLIGHT_ASHES = ITEMS.register("starlight_ashes", () -> new Item(new Item.Properties()));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
