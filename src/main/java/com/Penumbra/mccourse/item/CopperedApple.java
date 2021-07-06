package com.Penumbra.mccourse.item;

import com.Penumbra.mccourse.MCCourseMod;
import com.Penumbra.mccourse.util.Config;
import com.Penumbra.mccourse.util.KeyboardHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.List;

public class CopperedApple extends Item
{
    public CopperedApple()
    {
        super(new Properties().group(MCCourseMod.Course_Tab)
                .food(new Food.Builder()
                        .hunger(5)
                        .saturation(1.5f)
                        .effect(() -> new EffectInstance(Effects.GLOWING, Config.COPPERED_GLOW_DURATION.get(),
                                1),0.5f)
                        .build()));
    }

    @Override
    public void addInformation(ItemStack stack, World world, List<ITextComponent> tooltip,
                               ITooltipFlag flag)
    {
        if(KeyboardHelper.isHoldingShift())
        {
            tooltip.add(new StringTextComponent("Makes sheep drop Copper Ingots"));
        }
        else {
            tooltip.add(new StringTextComponent("Hold"+"\u0087e" + " SHIFT"+ "\u00A77" + " For more information" ));
        }
        super.addInformation(stack, world, tooltip, flag);
    }

    @Override
    public int getBurnTime(ItemStack itemStack)
    {
        return 600;
    }
}
