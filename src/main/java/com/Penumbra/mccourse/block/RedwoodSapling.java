package com.Penumbra.mccourse.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.block.trees.Tree;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.Random;
import java.util.function.Supplier;

public class RedwoodSapling extends BushBlock implements IGrowable
{
    public static final IntegerProperty STAGE = BlockStateProperties.STAGE_0_1;

    private final Supplier<Tree> tree;

    public RedwoodSapling(Supplier<Tree> treeIn, Properties p_i48437_1_) {
        super(p_i48437_1_);
        this.tree = treeIn;
    }

    @Override
    public boolean canGrow(IBlockReader iBlockReader, BlockPos blockPos, BlockState blockState, boolean b)
    {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World world, Random random, BlockPos blockPos, BlockState blockState)
    {
        return (double)world.rand.nextFloat() < 0.5D;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void tick(BlockState p_225534_1_, ServerWorld p_225534_2_, BlockPos p_225534_3_, Random p_225534_4_)
    {
        super.tick(p_225534_1_, p_225534_2_, p_225534_3_, p_225534_4_);
        if(!p_225534_2_.isAreaLoaded(p_225534_3_, 1))
        {
            return;
        }

        if(p_225534_2_.getLight(p_225534_3_.up()) >= 9 && p_225534_4_.nextInt(7) == 0)
        {
            this.grow(p_225534_2_, p_225534_4_, p_225534_3_, p_225534_1_);
        }
    }

    @Override
    public void grow(ServerWorld serverWorld, Random random, BlockPos blockPos, BlockState blockState)
    {
        if(blockState.get(STAGE) == 0)
        {
            serverWorld.setBlockState(blockPos, blockState.cycleValue(STAGE), 4);
        }
        else
        {
            if(!ForgeEventFactory.saplingGrowTree(serverWorld, random, blockPos))
            {
                return;
            }

            this.tree.get().attemptGrowTree(serverWorld, serverWorld.getChunkProvider().getChunkGenerator(),
                    blockPos, blockState, random);
        }
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> p_206840_1_)
    {
        p_206840_1_.add(STAGE);
    }
}
