package net.goldfoxyt.moremilk.datagen.loot;

import net.goldfoxyt.moremilk.block.ModBlocks;
import net.goldfoxyt.moremilk.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashSet;
import java.util.Set;

import static net.goldfoxyt.moremilk.block.ModBlocks.*;

public class ModBlockLootTables extends BlockLootSubProvider{
    private static final Set<Item> SET = new HashSet<>();

    public ModBlockLootTables(HolderLookup.Provider provider) {
        super(SET, FeatureFlags.REGISTRY.allFlags(), provider);
    }

    @Override
    protected void generate() {
        LootItemCondition.Builder builder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(BANANA_CROP.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropBlock.AGE, 6));
        this.add(BANANA_CROP.get(), this.createCropDrops(BANANA_CROP.get(), ModItems.BANANA.get(), ModItems.BANANA_SEEDS.get(), builder));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}