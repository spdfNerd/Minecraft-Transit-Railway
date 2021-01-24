package com.spdfnerd.mtr.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.spdfnerd.mtr.block.BlockPSDAPGDoorBase;
import com.spdfnerd.mtr.entity.EntityTrainBase;
import com.spdfnerd.mtr.model.ModelTrainBase;
import com.spdfnerd.mtr.path.PathFinderBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public abstract class RenderTrainBase<T extends EntityTrainBase> extends EntityRenderer<T> {

	public RenderTrainBase(EntityRendererManager renderManager) {
		super(renderManager);
		shadowSize = 0f;
	}

	@Override
	public void render(T entity, float yaw, float tickDelta, MatrixStack matrices, IRenderTypeBuffer renderTypeBuffer, int light) {
		final int doorValue = entity.getDoorValue();
		final float doorLeftValue = entity.getDoorLeft() ? (float) doorValue / BlockPSDAPGDoorBase.MAX_OPEN_VALUE : 0;
		final float doorRightValue = entity.getDoorRight() ? (float) doorValue / BlockPSDAPGDoorBase.MAX_OPEN_VALUE : 0;

		matrices.push();
		matrices.translate(0, 1, 0);
		matrices.rotate(Vector3f.YP.rotationDegrees(yaw));
		matrices.rotate(Vector3f.XN.rotationDegrees(180 + MathHelper.lerp(tickDelta, entity.prevRotationPitch, entity.rotationPitch)));
		getModel().render(matrices, renderTypeBuffer, getEntityTexture(entity), light, doorLeftValue, doorRightValue, entity.getIsEnd1Head(), entity.getIsEnd2Head(), entity.getHead1IsFront());
		matrices.pop();

		final Minecraft minecraft = Minecraft.getInstance();
		final PlayerEntity player = minecraft.player;
		if (player != null && entity.isPassenger(player)) {
			final IFormattableTextComponent text;
			if (entity.getDoorValue() > 0) {
				text = new TranslationTextComponent("mount.onboard", minecraft.gameSettings.keyBindSneak.getTranslationKey());
			} else {
				final double speed = Math.sqrt(PathFinderBase.distanceSquaredBetween((float) entity.prevPosX, (float) entity.prevPosY, (float) entity.prevPosZ, (float) entity.getPosX(), (float) entity.getPosY(), (float) entity.getPosZ())) * 20;
				text = new TranslationTextComponent("gui.mtr.train_speed").append(new StringTextComponent(String.format(" %s m/s (%s km/h)", Math.round(speed * 10) / 10F, Math.round(speed * 36) / 10F)));
			}
			player.sendStatusMessage(text, true);
		}
	}

	protected abstract ModelTrainBase getModel();

}
