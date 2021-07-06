package com.Penumbra.mccourse.block;

import com.Penumbra.mccourse.sound.ModSoundEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class CopperOre extends Block
{
    public CopperOre(Properties p_i48440_1_)
    {
        super(p_i48440_1_);
    }

    @Override
    public void onPlayerDestroy(IWorld p_176206_1_, BlockPos p_176206_2_, BlockState p_176206_3_)
    {
        if(p_176206_1_.isRemote())
        {
            Minecraft.getInstance().player.playSound(ModSoundEvents.SMALL_EXPLOSION.get(),
                    1f, 1f);
        }

        super.onPlayerDestroy(p_176206_1_, p_176206_2_, p_176206_3_);
    }
}
