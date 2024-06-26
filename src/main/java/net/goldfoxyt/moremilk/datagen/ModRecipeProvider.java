package net.goldfoxyt.moremilk.datagen;

import net.goldfoxyt.moremilk.item.ModItems;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private PackOutput pOutPut;
    private CompletableFuture<HolderLookup.Provider> pRegistries;

    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
        this.pOutPut = pOutput;
        this.pRegistries = pRegistries;
    }

    @SubscribeEvent
    public void gatherData(GatherDataEvent event) {
        event.getGenerator().addProvider(event.includeServer(), new ModRecipeProvider(pOutPut, pRegistries));
    }


    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.MILK_CARTON.get())
                .pattern("AXA")
                .pattern("AXA")
                .pattern("AXA")
                .define('X', ModItems.MILK_BOTTLE.get())
                .define('A', Items.PAPER)
                .unlockedBy("has_milk_bucket", has(Items.MILK_BUCKET))
                .save(pRecipeOutput);
    }
}