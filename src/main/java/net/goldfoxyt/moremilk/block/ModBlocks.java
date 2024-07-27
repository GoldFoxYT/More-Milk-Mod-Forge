package net.goldfoxyt.moremilk.block;

import net.goldfoxyt.moremilk.MoreMilk;
import net.goldfoxyt.moremilk.block.custom.BananaCropBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, MoreMilk.MOD_ID);

    public static final RegistryObject<Block> BANANA_CROP = BLOCKS.register("banana_crop",
            () -> new BananaCropBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY)));

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}