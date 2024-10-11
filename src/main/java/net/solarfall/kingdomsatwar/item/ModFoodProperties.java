package net.solarfall.kingdomsatwar.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class ModFoodProperties {
    public static final FoodProperties RADISH = new FoodProperties.Builder()
        .nutrition(2)
        .saturationModifier(0.3f)
        .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 400, 0), 1.0f)
        .build();
    
}
