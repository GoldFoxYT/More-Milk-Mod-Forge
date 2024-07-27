package net.goldfoxyt.moremilk.item;

import net.goldfoxyt.moremilk.MoreMilk;
import net.goldfoxyt.moremilk.block.ModBlocks;
import net.goldfoxyt.moremilk.item.custom.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MoreMilk.MOD_ID);

    public static final RegistryObject<Item> MILK_BOTTLE = ITEMS.register("milk_bottle",
            () -> new BottleMilkItem(new Item.Properties().stacksTo(1), entity -> {}));

    public static final RegistryObject<Item> CHOCOLATE_MILK_BOTTLE = ITEMS.register("chocolate_milk_bottle",
            () -> new BottleMilkItem(new Item.Properties().stacksTo(1),
                    entity -> entity.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 900, 1))));

    public static final RegistryObject<Item> BANANA_MILK_BOTTLE = ITEMS.register("banana_milk_bottle",
            () -> new BottleMilkItem(new Item.Properties().stacksTo(1),
                    entity -> entity.addEffect(new MobEffectInstance(MobEffects.JUMP, 900, 1))));

    public static final RegistryObject<Item> MILK_CARTON = ITEMS.register("milk_carton",
            () -> new CartonMilkItem(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> EMPTY_MILK_CARTON = ITEMS.register("empty_milk_carton",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BANANA = ITEMS.register("banana",
            () -> new Item(new Item.Properties().food(ModFoodProperties.BANANA)));

    public static final RegistryObject<Item> BANANA_SEEDS = ITEMS.register("banana_seeds",
            () -> new ItemNameBlockItem(ModBlocks.BANANA_CROP.get(), new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
