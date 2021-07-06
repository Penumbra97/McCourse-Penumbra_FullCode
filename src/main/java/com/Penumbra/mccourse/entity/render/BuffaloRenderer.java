package com.Penumbra.mccourse.entity.render;

import com.Penumbra.mccourse.MCCourseMod;
import com.Penumbra.mccourse.entity.BuffaloEntity;
import com.Penumbra.mccourse.entity.model.BuffaloModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class BuffaloRenderer extends MobRenderer<BuffaloEntity, BuffaloModel<BuffaloEntity>>
{

    public BuffaloRenderer(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new BuffaloModel<>(), 0.9f);
    }

    @Override
    public ResourceLocation getEntityTexture(BuffaloEntity buffaloEntity)
    {
        return new ResourceLocation(MCCourseMod.Mod_ID, "textures/entity/buffalo.png");
    }
}
