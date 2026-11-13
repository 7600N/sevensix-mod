package com.sevensix.items;

import com.sevensix.SevensixMod;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.consume_effects.RemoveStatusEffectsConsumeEffect;

import java.lang.classfile.attribute.NestHostAttribute;
import java.util.function.Function;

public class Items {
    //创建物品类
    public static ResourceKey<Item> create(String name) {
        return ResourceKey.create(Registries.ITEM, SevensixMod.id(name));
    }
    //创建一个key来接收创建的物品类
    public static Item register(ResourceKey<Item> itemKey, Function<Item.Properties, Item> itemFactory, Item.Properties settings) {
        Item item = itemFactory.apply(settings.setId(itemKey));
        return Registry.register(BuiltInRegistries.ITEM, itemKey, item);
    }

    //食物效果及属性构建
    public static final FoodProperties CHOCOLATE_COMMON = new FoodProperties(6,0.6f,false);
    public static final Consumable CHOCOLATE_BUFF = Consumables.defaultFood().onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.REGENERATION,10 * 20,2),0.3f)).build();

    //注册物品
    public static final ResourceKey<Item> CHOCOLATE = create("chocolate");
    public static final Item chocolate = register(Items.CHOCOLATE, Item::new, new Item.Properties().food(CHOCOLATE_COMMON,CHOCOLATE_BUFF));

    //这里在模组加载中调用上面所写的，需要在SevensixMod中声明
    public static void initialize() {
        //将物品加入创造模式栏，这里用的是食物栏
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FOOD_AND_DRINKS)
                .register((creativeTab) -> creativeTab.accept(Items.chocolate));
    }
}
