package com.Penumbra.mccourse.item;

import com.Penumbra.mccourse.entity.ModEntityTypes;
import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ModSpawnEggItem extends SpawnEggItem
{
    protected static final List<ModSpawnEggItem> MOD_EGGS = new ArrayList<>();

    private final Lazy<? extends EntityType<?>> entityTypeSupplier;

    public ModSpawnEggItem(final RegistryObject<? extends EntityType<?>> entitySupplier, int p_i48465_2_,
                           int p_i48465_3_, Properties p_i48465_4_)
    {
        super(null, p_i48465_2_, p_i48465_3_, p_i48465_4_);
        this.entityTypeSupplier = Lazy.of(entitySupplier::get);
        MOD_EGGS.add(this);
    }

    public static void initSpawnEggs()
    {
        final Map<EntityType<?>, SpawnEggItem> EGGS =
                ObfuscationReflectionHelper.getPrivateValue(SpawnEggItem.class,
                        null, "EGGS");

        DefaultDispenseItemBehavior dispenseItemBehavior = new DefaultDispenseItemBehavior()
        {
            @Override
            protected ItemStack dispenseStack(IBlockSource p_82487_1_, ItemStack p_82487_2_)
            {
                Direction direction = p_82487_1_.getBlockState().get(DispenserBlock.FACING);
                EntityType<?> type = ((SpawnEggItem) p_82487_2_.getItem()).getType(p_82487_2_.getTag());
                type.spawn(p_82487_1_.getWorld(), p_82487_2_, null, p_82487_1_.getBlockPos(),
                        SpawnReason.DISPENSER, direction != Direction.UP, false);
                p_82487_2_.shrink(1);
                return p_82487_2_;
            }
        };

        for(final SpawnEggItem spawnEgg : ModSpawnEggItem.MOD_EGGS)
        {
            EGGS.put(spawnEgg.getType(null), spawnEgg);
            DispenserBlock.registerDispenseBehavior(spawnEgg, dispenseItemBehavior);
        }

        ModSpawnEggItem.MOD_EGGS.clear();
    }

    @Override
    public EntityType<?> getType(CompoundNBT nbt)
    {
        return this.entityTypeSupplier.get();
    }
}
