package com.sevensix.items;

import com.sevensix.SevensixMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.alchemy.Potion;

public class ModPotionIds {
    public static final ResourceKey<Potion> EXCITED_POTION = create("excited");

    private static ResourceKey<Potion> create(String name){
        Identifier id = SevensixMod.id(name);
        return ResourceKey.create(Registries.POTION,id);
    }
}
