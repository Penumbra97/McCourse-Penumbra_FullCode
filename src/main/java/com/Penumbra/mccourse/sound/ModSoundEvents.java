package com.Penumbra.mccourse.sound;

import com.Penumbra.mccourse.MCCourseMod;
import com.Penumbra.mccourse.util.Registration;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;

public class ModSoundEvents
{
    public static final RegistryObject<SoundEvent> SMALL_EXPLOSION =
            Registration.SOUND_EVENT.register("small_explosion",
            () -> new SoundEvent(new ResourceLocation(MCCourseMod.Mod_ID, "small_explosion")));

    public static void register() { }
}
