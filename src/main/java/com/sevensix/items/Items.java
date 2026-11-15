package com.sevensix.items;

import com.sevensix.SevensixMod;
import com.sevensix.effects.SevensixModEffects;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.component.*;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.level.storage.loot.providers.number.floats.ResolvableFloat;
import net.minecraft.world.level.storage.loot.providers.number.ints.ResolvableInt;
import net.minecraft.world.level.block.Block;

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

    public static final TagKey<Block> INCORRECT_FOR_CHOCOLATE_TOOL = TagKey.create(Registries.BLOCK, SevensixMod.id("incorrect_for_chocolate_tool"));

    //工具属性
    public static final TagKey<Item> REPAIRS_CHOCOLATE_TOOL = TagKey.create(BuiltInRegistries.ITEM.key(),SevensixMod.id("repairs_chocolate_tool"));
    public static final ToolMaterial CHOCOLATE_TOOL_MATERIAL = new ToolMaterial(INCORRECT_FOR_CHOCOLATE_TOOL,9999,99.0F,9.1F,78,REPAIRS_CHOCOLATE_TOOL);

    //咖啡
    public static final Holder<Potion> EXCITED_POTION = Registry.registerForHolder(BuiltInRegistries.POTION,ModPotionIds.EXCITED_POTION,new Potion("excited",new MobEffectInstance(SevensixModEffects.EXCITED,3600,0)));

    //食物效果及属性构建
    public static final FoodProperties CHOCOLATE_COMMON = new FoodProperties(6,0.6f,false);
    public static final Consumable CHOCOLATE_BUFF = Consumables.defaultFood().onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.REGENERATION,10 * 20,2),0.3f)).build();

    //注册物品
    public static final ResourceKey<Item> CHOCOLATE = create("chocolate");
    public static final ResourceKey<Item> CHOCOLATE_PAPER = create("chocolate_paper");
    public static final ResourceKey<Item> COCOA_POWDER = create("cocoa_powder");
    public static final ResourceKey<Item> EXCITED_POTION_ITEM = create("excited_potion");
    public static final ResourceKey<Item> CHOCOLATE_SWORD = create("chocolate_sword");
    public static final ResourceKey<Item> CHOCOLATE_SPEAR = create("chocolate_spear");
    public static final ResourceKey<Item> CHOCOLATE_PICKAXE = create("chocolate_pickaxe");
    public static final ResourceKey<Item> CHOCOLATE_AXE = create("chocolate_axe");
    public static final ResourceKey<Item> CHOCOLATE_SHOVEL = create("chocolate_shovel");
    public static final ResourceKey<Item> CHOCOLATE_HOE = create("chocolate_hoe");

    public static final Item chocolate_paper = register(Items.CHOCOLATE_PAPER,Item::new,new Item.Properties().component(DataComponents.COOKING_FUEL,new CookingFuel(new ResolvableInt.Constant(20 * 20),new ResolvableFloat.Constant(1.0f))));
    public static final Item chocolate = register(Items.CHOCOLATE, Item::new, new Item.Properties().food(CHOCOLATE_COMMON,CHOCOLATE_BUFF).component(DataComponents.USE_REMAINDER,new UseRemainder(new ItemStackTemplate(Items.chocolate_paper))));
    public static final Item cocoa_powder = register(Items.COCOA_POWDER,Item::new,new Item.Properties().component(DataComponents.COMPOSTABLE,new Compostable(new ResolvableInt.Constant(1))));
    public static final Item excited_potion = register(EXCITED_POTION_ITEM, PotionItem::new, new Item.Properties().component(DataComponents.CONSUMABLE, Consumables.DEFAULT_DRINK) .component(DataComponents.POTION_CONTENTS,new PotionContents(EXCITED_POTION)));
    public static final Item chocolate_sword = register(Items.CHOCOLATE_SWORD,Item::new,new Item.Properties().sword(CHOCOLATE_TOOL_MATERIAL,9999f,9f));
    public static final Item chocolate_spear = register(Items.CHOCOLATE_SPEAR, Item::new, new Item.Properties().spear(CHOCOLATE_TOOL_MATERIAL, 1.15F, 1.2F, 0.4F, 2.5F, 9.0F, 5.5F, 5.1F, 8.75F, 4.6F));
    public static final Item chocolate_pickaxe = register(Items.CHOCOLATE_PICKAXE,Item::new,new Item.Properties().pickaxe(CHOCOLATE_TOOL_MATERIAL,9999f,9f));
    public static final Item chocolate_axe = register(Items.CHOCOLATE_AXE,Item::new,new Item.Properties().axe(CHOCOLATE_TOOL_MATERIAL,91.0F, -3.0F));
    public static final Item chocolate_shovel =register(Items.CHOCOLATE_SHOVEL,Item::new,new Item.Properties().shovel(CHOCOLATE_TOOL_MATERIAL,91.0F, -3.0F));
    public static final Item chocolate_hoe =register(Items.CHOCOLATE_HOE,Item::new,new Item.Properties().hoe(CHOCOLATE_TOOL_MATERIAL,91.0F, -3.0F));

    //这里在模组加载中调用上面所写的，需要在SevensixMod中声明
    public static void initialize() {
        //将物品加入创造模式栏，这里用的是食物栏
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FOOD_AND_DRINKS)
                .register((creativeTab) -> creativeTab.accept(Items.chocolate));
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS)
                .register((creativeTab) -> creativeTab.accept(Items.chocolate_paper));
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS)
                .register((creativeTab) -> creativeTab.accept(Items.cocoa_powder));
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FOOD_AND_DRINKS)
                .register((creativeTab) -> creativeTab.accept(Items.excited_potion));
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES)
                .register((creativeTab) -> {
                    creativeTab.accept(Items.chocolate_pickaxe);
                    creativeTab.accept(Items.chocolate_shovel);
                    creativeTab.accept(Items.chocolate_hoe);
                });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT)
                .register((creativeTab) -> {
                    creativeTab.accept(Items.chocolate_sword);
                    creativeTab.accept(Items.chocolate_spear);
                    creativeTab.accept(Items.chocolate_axe);
                });
    }
}
