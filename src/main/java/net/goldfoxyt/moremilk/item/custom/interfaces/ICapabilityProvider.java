package net.goldfoxyt.moremilk.item.custom.interfaces;

import net.minecraft.world.item.ItemStack;

public interface ICapabilityProvider {
    net.minecraftforge.common.capabilities.ICapabilityProvider initCapabilities(ItemStack stack, @org.jetbrains.annotations.Nullable net.minecraft.nbt.CompoundTag nbt);
}
