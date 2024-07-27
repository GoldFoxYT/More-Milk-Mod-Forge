package net.goldfoxyt.moremilk.creativetab;

import net.goldfoxyt.moremilk.MoreMilk;
import net.goldfoxyt.moremilk.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;


public class ModCreativeModeTabs {
   public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MoreMilk.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MORE_MILK_TAB = CREATIVE_MODE_TABS.register("more_milk_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.MILK_BOTTLE.get()))
                    .title(Component.literal("More Milk Tab")).displayItems((itemDisplayParameters, event) -> {
                        event.accept(ModItems.MILK_BOTTLE.get());
                        event.accept(ModItems.CHOCOLATE_MILK_BOTTLE.get());
                        event.accept(ModItems.BANANA_MILK_BOTTLE.get());
                        event.accept(ModItems.MILK_CARTON.get());
                        event.accept(ModItems.EMPTY_MILK_CARTON.get());
                        event.accept(ModItems.BANANA.get());
                        event.accept(ModItems.BANANA_SEEDS.get());
                    }).build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
