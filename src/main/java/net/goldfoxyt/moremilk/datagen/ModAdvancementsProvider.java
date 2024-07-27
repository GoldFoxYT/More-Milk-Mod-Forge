package net.goldfoxyt.moremilk.datagen;

import net.goldfoxyt.moremilk.MoreMilk;
import net.goldfoxyt.moremilk.item.ModItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.ConsumeItemTrigger;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

import java.util.function.Consumer;


public class ModAdvancementsProvider implements ForgeAdvancementProvider.AdvancementGenerator {

    @Override
    public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> saver, ExistingFileHelper existingFileHelper) {
        AdvancementHolder rootAdvancement = Advancement.Builder.advancement()
                .display(
                        new ItemStack(ModItems.MILK_BOTTLE.get()),
                        Component.literal("Milk Bottle!"),
                        Component.literal("Milk in a bottle!"),
                        ResourceLocation.fromNamespaceAndPath(MoreMilk.MOD_ID, "textures/gui/advancements/more_milk.png"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("has_milk_bottle", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.MILK_BOTTLE.get()))
                .save(saver, ResourceLocation.fromNamespaceAndPath(MoreMilk.MOD_ID, "milk_bottle"));

        AdvancementHolder milkCarton = Advancement.Builder.advancement()
                .display(
                        new ItemStack(ModItems.MILK_CARTON.get()),
                        Component.literal("You drank from the carton??"),
                        Component.literal("You really did!"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .parent(rootAdvancement)
                .addCriterion("drank_milk_carton", ConsumeItemTrigger.TriggerInstance.usedItem(ModItems.MILK_CARTON.get()))
                .save(saver, ResourceLocation.fromNamespaceAndPath(MoreMilk.MOD_ID, "milk_carton"));


        AdvancementHolder chocolatemilkbottle = Advancement.Builder.advancement()
                .display(
                        new ItemStack(ModItems.CHOCOLATE_MILK_BOTTLE.get()),
                        Component.literal("Yummy!"),
                        Component.literal("Drink chocolate milk from a bottle!"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .parent(rootAdvancement)
                .addCriterion("drank_chocolate_milk", ConsumeItemTrigger.TriggerInstance.usedItem(ModItems.CHOCOLATE_MILK_BOTTLE.get()))
                .save(saver, ResourceLocation.fromNamespaceAndPath(MoreMilk.MOD_ID, "chocolate_milk_bottle"));

        AdvancementHolder bananamilkbottle = Advancement.Builder.advancement()
                .display(
                        new ItemStack(ModItems.BANANA_MILK_BOTTLE.get()),
                        Component.literal("Banana Boost!"),
                        Component.literal("Damn, that's a lot of energy in one bottle!"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .parent(rootAdvancement)
                .addCriterion("drank_banana_milk", ConsumeItemTrigger.TriggerInstance.usedItem(ModItems.BANANA_MILK_BOTTLE.get()))
                .save(saver, ResourceLocation.fromNamespaceAndPath(MoreMilk.MOD_ID, "banana_milk_bottle"));

        AdvancementHolder banana = Advancement.Builder.advancement()
                .display(
                        new ItemStack(ModItems.BANANA.get()),
                        Component.literal("BANANA"),
                        Component.literal("Eat a BANANA"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .parent(rootAdvancement)
                .addCriterion("ate_banana", ConsumeItemTrigger.TriggerInstance.usedItem(ModItems.BANANA.get()))
                .save(saver, ResourceLocation.fromNamespaceAndPath(MoreMilk.MOD_ID, "banana"));
    }
}
