package com.Penumbra.mccourse.world.biome;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraftforge.common.util.Lazy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.function.Supplier;

public class LoggingSurfaceBuilder<C extends ISurfaceBuilderConfig, S extends SurfaceBuilder<C>> extends SurfaceBuilder<C>
{
    private static final Logger LOGGER = LogManager.getLogger();
    private final Lazy<S> delegatedSurfaceBuilder;
    private boolean logged = false;

    public LoggingSurfaceBuilder(final Supplier<S> delegatedSurfaceBuilder, final Codec<C> codec)
    {
        super(codec);
        this.delegatedSurfaceBuilder = Lazy.of(delegatedSurfaceBuilder);
    }

    @Override
    public void buildSurface(Random random, IChunk iChunk, Biome biome, int i, int i1,
                             int i2, double v, BlockState blockState, BlockState blockState1,
                             int i3, long l, C c)
    {
        delegatedSurfaceBuilder.get().buildSurface(random, iChunk, biome, i, i1, i2, v, blockState, blockState1, i3, l, c);

        if(!logged)
        {
            logged = true;
            ChunkPos chunkPos = iChunk.getPos();
            LOGGER.info("Currently Generating {} at {}, {}", biome.getRegistryName(),
                    chunkPos.getXStart(), chunkPos.getZStart());
        }
    }
}
