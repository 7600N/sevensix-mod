package com.sevensix.effects;

import com.sevensix.SevensixMod;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;

public class SevensixModEffects implements ModInitializer {
    public static final Holder<MobEffect> EXCITED = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, SevensixMod.id("excited"),new ExcitedEffect());

    @Override
    public void onInitialize(){
    }
}
