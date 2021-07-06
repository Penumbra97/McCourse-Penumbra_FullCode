package com.Penumbra.mccourse.enchantment;

import com.Penumbra.mccourse.item.weapon.BluntItem;
import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class BluntEnchantment extends Enchantment
{
    private static final int[] MIN_COST = new int[]{1, 5, 5};
    private static final int[] LEVEL_COST = new int[]{11, 8, 8};
    private static final int[] LEVEL_COST_SPAN = new int[]{20, 20, 20};
    public final int damageType;

    protected BluntEnchantment(Rarity p_i46731_1_, EnchantmentType p_i46731_2_, int damageType,
                               EquipmentSlotType... p_i46731_3_)
    {
        super(p_i46731_1_, p_i46731_2_, p_i46731_3_);
        this.damageType = damageType;
    }

    public int getMinEnchantability(int p_77321_1_) {
        return MIN_COST[this.damageType] + (p_77321_1_ - 1) * LEVEL_COST[this.damageType];
    }

    public int getMaxEnchantability(int p_223551_1_) {
        return this.getMinEnchantability(p_223551_1_) + LEVEL_COST_SPAN[this.damageType];
    }

    public int getMaxLevel() {
        return 5;
    }

    public float calcDamageByCreature(int p_152376_1_, CreatureAttribute p_152376_2_) {
        if (this.damageType == 0) {
            return 1.0F + (float)Math.max(0, p_152376_1_ - 1) * 0.5F;
        } else if (this.damageType == 1 && p_152376_2_ == CreatureAttribute.UNDEAD) {
            return (float)p_152376_1_ * 2.5F;
        } else {
            return this.damageType == 2 && p_152376_2_ == CreatureAttribute.ARTHROPOD ? (float)p_152376_1_ * 2.5F : 0.0F;
        }
    }

    public boolean canApplyTogether(Enchantment p_77326_1_) {
        return !(p_77326_1_ instanceof DamageEnchantment || p_77326_1_ instanceof BluntEnchantment);
    }

    public boolean canApply(ItemStack p_92089_1_) {
        return p_92089_1_.getItem() instanceof BluntItem ? true : super.canApply(p_92089_1_);
    }

    public void onEntityDamaged(LivingEntity p_151368_1_, Entity p_151368_2_, int p_151368_3_) {
        if (p_151368_2_ instanceof LivingEntity) {
            LivingEntity lvt_4_1_ = (LivingEntity)p_151368_2_;
            if (this.damageType == 2 && lvt_4_1_.getCreatureAttribute() == CreatureAttribute.ARTHROPOD) {
                int lvt_5_1_ = 20 + p_151368_1_.getRNG().nextInt(10 * p_151368_3_);
                lvt_4_1_.addPotionEffect(new EffectInstance(Effects.SLOWNESS, lvt_5_1_, 3));
            }
        }

    }
}
