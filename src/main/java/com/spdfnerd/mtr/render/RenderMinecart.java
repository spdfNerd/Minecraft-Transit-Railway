package com.spdfnerd.mtr.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.spdfnerd.mtr.entity.EntityMinecart;
import com.spdfnerd.mtr.model.ModelTrainBase;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.MinecartModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;

public class RenderMinecart extends RenderTrainBase<EntityMinecart> {

	private static final ResourceLocation texture = new ResourceLocation("textures/entity/minecart.png");
	private static final EntityModel<EntityMinecart> model = new MinecartModel<>();

	public RenderMinecart(EntityRendererManager manager) {
		super(manager);
	}

	@Override
	public void render(EntityMinecart entity, float yaw, float tickDelta, MatrixStack matrices, IRenderTypeBuffer renderTypeBuffer, int light) {
		matrices.push();
		matrices.translate(0, 0.5, 0);
		matrices.rotate(Vector3f.YP.rotationDegrees(yaw + 90));
		matrices.rotate(Vector3f.ZN.rotationDegrees(180 + MathHelper.lerp(tickDelta, entity.prevRotationPitch, entity.rotationPitch)));
		model.setRotationAngles(entity, 0, 0, -0.1F, 0, 0);
		IVertexBuilder vertexBuilder = renderTypeBuffer.getBuffer(model.getRenderType(texture));
		model.render(matrices, vertexBuilder, light, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		matrices.pop();
	}

	@Override
	public ResourceLocation getEntityTexture(EntityMinecart entity) {
		return texture;
	}

	@Override
	protected ModelTrainBase getModel() {
		return null;
	}

}
