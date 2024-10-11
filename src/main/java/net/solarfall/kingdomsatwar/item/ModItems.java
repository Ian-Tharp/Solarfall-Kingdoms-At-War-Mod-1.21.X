package net.solarfall.kingdomsatwar.item;


import net.solarfall.kingdomsatwar.KingdomsAtWar;
import net.solarfall.kingdomsatwar.item.custom.ChiselItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;


public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(KingdomsAtWar.MOD_ID);

    public static final DeferredItem<Item> VITARIUM_DUST = ITEMS.register("vitarium_dust", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> VITARIUM_INGOT = ITEMS.register("vitarium_ingot", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> CHISEL = ITEMS.register("chisel", () -> new ChiselItem(new Item.Properties().durability(32)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
