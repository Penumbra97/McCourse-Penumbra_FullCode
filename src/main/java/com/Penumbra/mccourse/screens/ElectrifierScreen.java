package com.Penumbra.mccourse.screens;

import com.Penumbra.mccourse.MCCourseMod;
import com.Penumbra.mccourse.container.ElectrifierContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class ElectrifierScreen extends ContainerScreen<ElectrifierContainer>
{
    private final ResourceLocation GUI = new ResourceLocation(MCCourseMod.Mod_ID,
            "textures/gui/electrifier_gui.png");

    public ElectrifierScreen(ElectrifierContainer container, PlayerInventory inv, ITextComponent name)
    {
        super(container, inv, name);
    }

    @Override
    public void render(MatrixStack p_230430_1_, int p_230430_2_, int p_230430_3_, float p_230430_4_)
    {
        this.renderBackground(p_230430_1_);
        super.render(p_230430_1_, p_230430_2_, p_230430_3_, p_230430_4_);
        this.renderHoveredTooltip(p_230430_1_, p_230430_2_, p_230430_3_);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack p_230451_1_, int p_230451_2_, int p_230451_3_)
    {
        drawString(p_230451_1_, Minecraft.getInstance().fontRenderer, "Energy: " + container.getEnergyLevel(),
                28, 10, 0xffffff);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y)
    {
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.getTextureManager().bindTexture(GUI);
        int i = this.guiLeft;
        int j = this.guiTop;
        this.blit(matrixStack, i, j, 0, 0, this.xSize, this.ySize);

        this.blit(matrixStack, i + 13, j + 9, 176, 0, 11, 64 - container.getEnergyLevel());
    }
}
