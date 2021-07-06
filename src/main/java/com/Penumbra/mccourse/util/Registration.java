package com.Penumbra.mccourse.util;

import com.Penumbra.mccourse.MCCourseMod;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registration
{
    public static final DeferredRegister<Block> BLOCKS
            = DeferredRegister.create(ForgeRegistries.BLOCKS, MCCourseMod.Mod_ID);

    public static final DeferredRegister<Item> ITEMS
            = DeferredRegister.create(ForgeRegistries.ITEMS, MCCourseMod.Mod_ID);

    public static final DeferredRegister<Fluid> FLUIDS
            = DeferredRegister.create(ForgeRegistries.FLUIDS, MCCourseMod.Mod_ID);

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES
            = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MCCourseMod.Mod_ID);

    public static final DeferredRegister<ContainerType<?>> CONTAINERS
            = DeferredRegister.create(ForgeRegistries.CONTAINERS, MCCourseMod.Mod_ID);

    public static final DeferredRegister<EntityType<?>> ENTITIES
            =DeferredRegister.create(ForgeRegistries.ENTITIES, MCCourseMod.Mod_ID);

    public static final DeferredRegister<Biome> BIOMES
            = DeferredRegister.create(ForgeRegistries.BIOMES, MCCourseMod.Mod_ID);

    public static final DeferredRegister<SurfaceBuilder<?>> SURFACE_BUILDERS
            = DeferredRegister.create(ForgeRegistries.SURFACE_BUILDERS, MCCourseMod.Mod_ID);

    public static final DeferredRegister<SoundEvent> SOUND_EVENT
            = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MCCourseMod.Mod_ID);

    public static final DeferredRegister<Enchantment> ENCHANTMENTS
            = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MCCourseMod.Mod_ID);


    public static void init()
    {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);
        FLUIDS.register(eventBus);
        TILE_ENTITY_TYPES.register(eventBus);
        CONTAINERS.register(eventBus);
        ENTITIES.register(eventBus);
        BIOMES.register(eventBus);
        SURFACE_BUILDERS.register(eventBus);
        SOUND_EVENT.register(eventBus);
        ENCHANTMENTS.register(eventBus);
    }
}
