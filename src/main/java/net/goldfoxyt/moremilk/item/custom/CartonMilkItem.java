package net.goldfoxyt.moremilk.item.custom;

import net.goldfoxyt.moremilk.item.ModItems;
import net.goldfoxyt.moremilk.item.custom.interfaces.ICapabilityProvider;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

public class CartonMilkItem extends Item implements ICapabilityProvider {
    private static final int DRINK_DURATION = 32;
    public int durability = 6;
    public int drinkCounter = 0;

    public CartonMilkItem(Item.Properties pProperties) {
        super(pProperties);
    }

    /**
     * Called when the player finishes using this Item (E.g. finishes eating.). Not called when the player stops using
     * the Item before the action is complete.
     */
    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving) {
        if (pEntityLiving instanceof ServerPlayer serverPlayer) {
            if(drinkCounter < durability - 1){
                drinkCounter++;
            }else {
                ItemStack emptyCarton = new ItemStack(ModItems.EMPTY_MILK_CARTON.get());
                pEntityLiving.setItemInHand(InteractionHand.MAIN_HAND, emptyCarton);
                drinkCounter = 0;
            }
            CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayer, pStack);
            serverPlayer.awardStat(Stats.ITEM_USED.get(this));
        }

        if (!pLevel.isClientSide) {
            pEntityLiving.removeAllEffects();
        }

        return pStack;
    }

    /**
     * How long it takes to use or consume an item
     */
    public int getUseDuration(ItemStack pStack, LivingEntity p_342040_) {
        return 32;
    }

    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.DRINK;
    }

    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        return ItemUtils.startUsingInstantly(pLevel, pPlayer, pHand);
    }

    @Override
    public net.minecraftforge.common.capabilities.ICapabilityProvider initCapabilities(ItemStack stack,  @org.jetbrains.annotations.Nullable net.minecraft.nbt.CompoundTag nbt) {
        return new net.minecraftforge.fluids.capability.wrappers.FluidBucketWrapper(stack);
    }
}