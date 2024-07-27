package net.goldfoxyt.moremilk;

import com.google.common.collect.ImmutableSet;
import com.mojang.logging.LogUtils;
import net.goldfoxyt.moremilk.block.ModBlocks;
import net.goldfoxyt.moremilk.event.ForgeMilkEvent;
import net.goldfoxyt.moremilk.item.ModItems;
import net.goldfoxyt.moremilk.loot.ModLootModifiers;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import net.goldfoxyt.moremilk.creativetab.ModCreativeModeTabs;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mod(MoreMilk.MOD_ID)
public class MoreMilk {
    public static final String MOD_ID = "moremilk";
    private static final Logger LOGGER = LogUtils.getLogger();

    public MoreMilk() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::loadComplete);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);
        ModLootModifiers.register(modEventBus);
        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void loadComplete(final FMLLoadCompleteEvent event) {
        MinecraftForge.EVENT_BUS.register(new ForgeMilkEvent());
    }
    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ComposterBlock.COMPOSTABLES.put(ModItems.BANANA.get(), 0.3f);
            ComposterBlock.COMPOSTABLES.put(ModItems.BANANA_SEEDS.get(), 0.3f);
        });
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS){
            event.accept(ModItems.MILK_BOTTLE);
            event.accept(ModItems.CHOCOLATE_MILK_BOTTLE);
            event.accept(ModItems.BANANA_MILK_BOTTLE);
            event.accept(ModItems.MILK_CARTON);
            event.accept(ModItems.EMPTY_MILK_CARTON);
            event.accept(ModItems.BANANA);
            event.accept(ModItems.BANANA_SEEDS);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            Stream<Item> vanillaWantedItems = Villager.WANTED_ITEMS.stream();
            Stream<Item> modWantedItems = Stream.of(ModItems.BANANA_SEEDS.get(),
                    ModItems.BANANA.get());
            Villager.WANTED_ITEMS = ImmutableSet.copyOf(
                    Stream.concat(vanillaWantedItems, modWantedItems)
                            .collect(Collectors.toSet()));
        }
    }
}