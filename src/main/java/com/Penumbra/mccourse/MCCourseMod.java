package com.Penumbra.mccourse;

import com.Penumbra.mccourse.block.ModBlocks;
import com.Penumbra.mccourse.block.ModFluids;
import com.Penumbra.mccourse.container.ModContainers;
import com.Penumbra.mccourse.enchantment.ModEnchantments;
import com.Penumbra.mccourse.entity.BuffaloEntity;
import com.Penumbra.mccourse.entity.ModEntityTypes;
import com.Penumbra.mccourse.events.ModEvents;
import com.Penumbra.mccourse.item.ModItems;
import com.Penumbra.mccourse.setup.ClientProxy;
import com.Penumbra.mccourse.setup.IProxy;
import com.Penumbra.mccourse.setup.ServerProxy;
import com.Penumbra.mccourse.sound.ModSoundEvents;
import com.Penumbra.mccourse.tileentity.ModTileEntities;
import com.Penumbra.mccourse.util.Config;
import com.Penumbra.mccourse.util.Registration;
import com.Penumbra.mccourse.world.biome.ModBiomes;
import com.Penumbra.mccourse.world.biome.ModSurfaceBuilders;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MCCourseMod.Mod_ID)
public class MCCourseMod
{
    public static final String Mod_ID = "mccourse";

    public static final ItemGroup Course_Tab;

    static {
        Course_Tab = new ItemGroup("coursetab") {
            @Override
            public ItemStack createIcon() {
                return new ItemStack(ModItems.COPPER_WIRE.get());
            }
        };
    }

    public static IProxy proxy;

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public MCCourseMod()
    {
        proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> ServerProxy::new);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        registerModAdditions();


        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        registerConfigs();

        proxy.init();

        DeferredWorkQueue.runLater(() -> {
            GlobalEntityTypeAttributes.put(ModEntityTypes.BUFFALO.get(), BuffaloEntity.SetCustomAttributes().create());
        });

        loadConfigs();
    }

    private void registerConfigs()
    {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SERVER_CONFIG);
    }

    private void loadConfigs()
    {
        Config.loadConfigFile(Config.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve("mccourse-client.toml").toString());
        Config.loadConfigFile(Config.SERVER_CONFIG, FMLPaths.CONFIGDIR.get().resolve("mccourse-server.toml").toString());
    }

    private void registerModAdditions()
    {
        // inits registration of our additions
        Registration.init();

        // Registers items, blocks etc... added by mod
        ModItems.register();
        ModBlocks.register();
        ModFluids.register();

        ModBiomes.register();
        ModSurfaceBuilders.register();

        ModSoundEvents.register();

        ModTileEntities.register();
        ModContainers.register();
        ModEntityTypes.register();
        ModEnchantments.register();

        // Registers mod events
        MinecraftForge.EVENT_BUS.register(new ModEvents());
    }

        }
