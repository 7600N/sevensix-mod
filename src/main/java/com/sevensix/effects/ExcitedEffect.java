package com.sevensix.effects;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class ExcitedEffect extends MobEffect {
    protected ExcitedEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x8B4726);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier){
        return true;
    }

    //他妈根本没有这样的效果
    //你只是怕了
    //这他妈谁不怕
    @Override
    public boolean applyEffectTick(ServerLevel level, LivingEntity entity, int amplifier){
        entity.heal(1 << amplifier);
        return super.applyEffectTick(level,entity,amplifier);
    }
}
