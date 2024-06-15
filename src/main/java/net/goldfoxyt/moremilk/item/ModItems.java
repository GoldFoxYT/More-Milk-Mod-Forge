package net.goldfoxyt.moremilk.item;

import net.goldfoxyt.moremilk.MoreMilk;
import net.goldfoxyt.moremilk.item.custom.*;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MoreMilk.MOD_ID);

    public static final RegistryObject<Item> MILK_BOTTLE = ITEMS.register("milk_bottle",
            () -> new BottleMilkItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> MILK_CARTON = ITEMS.register("milk_carton",
            () -> new CartonMilkItem(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> EMPTY_MILK_CARTON = ITEMS.register("empty_milk_carton",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
