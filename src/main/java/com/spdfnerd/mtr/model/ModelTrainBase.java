package com.spdfnerd.mtr.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.spdfnerd.mtr.entity.EntityTrainBase;
import com.spdfnerd.mtr.render.MoreRenderTypes;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;

public abstract class ModelTrainBase extends EntityModel<EntityTrainBase> {

	public static final int MAX_LIGHT = 0xF00000;

	@Override
	public void setRotationAngles(EntityTrainBase entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public final void render(MatrixStack matrices, IVertexBuilder vertices, int light, int overlay, float red, float green, float blue, float alpha) {
	}

	public final void render(MatrixStack matrices, IRenderTypeBuffer renderTypeBuffer, ResourceLocation texture, int light, float doorLeftValue, float doorRightValue, boolean isEnd1Head, boolean isEnd2Head, boolean head1IsFront) {
		render(matrices, renderTypeBuffer.getBuffer(MoreRenderTypes.getLight(texture)), RenderStage.LIGHTS, MAX_LIGHT, doorLeftValue, doorRightValue, isEnd1Head, isEnd2Head, head1IsFront);
		render(matrices, renderTypeBuffer.getBuffer(MoreRenderTypes.getInterior(texture)), RenderStage.INTERIOR, MAX_LIGHT, doorLeftValue, doorRightValue, isEnd1Head, isEnd2Head, head1IsFront);

		for (int position : getDoorPositions()) {
			renderDoorLabels(matrices, renderTypeBuffer, RenderStage.INTERIOR, MAX_LIGHT, position / 16F, doorLeftValue, doorRightValue);
			renderDoorLabels(matrices, renderTypeBuffer, RenderStage.EXTERIOR, light, position / 16F, doorLeftValue, doorRightValue);
		}

		render(matrices, renderTypeBuffer.getBuffer(MoreRenderTypes.getInteriorTranslucent(texture)), RenderStage.INTERIOR_TRANSLUCENT, MAX_LIGHT, doorLeftValue, doorRightValue, isEnd1Head, isEnd2Head, head1IsFront);
		render(matrices, renderTypeBuffer.getBuffer(MoreRenderTypes.getExterior(texture)), RenderStage.EXTERIOR, light, doorLeftValue, doorRightValue, isEnd1Head, isEnd2Head, head1IsFront);
	}

	private void render(MatrixStack matrices, IVertexBuilder vertices, RenderStage renderStage, int light, float doorLeftValue, float doorRightValue, boolean isEnd1Head, boolean isEnd2Head, boolean head1IsFront) {
		for (int position : getWindowPositions()) {
			renderWindowPositions(matrices, vertices, renderStage, light, position);
		}
		for (int position : getDoorPositions()) {
			renderDoorPositions(matrices, vertices, renderStage, light, position, doorLeftValue, doorRightValue);
		}

		if (isEnd1Head) {
			renderHeadPosition1(matrices, vertices, renderStage, light, getEndPositions()[0], head1IsFront);
		} else {
			renderEndPosition1(matrices, vertices, renderStage, light, getEndPositions()[0]);
		}

		if (isEnd2Head) {
			renderHeadPosition2(matrices, vertices, renderStage, light, getEndPositions()[1], !head1IsFront);
		} else {
			renderEndPosition2(matrices, vertices, renderStage, light, getEndPositions()[1]);
		}
	}

	protected abstract void renderWindowPositions(MatrixStack matrices, IVertexBuilder vertices, RenderStage renderStage, int light, int position);

	protected abstract void renderDoorPositions(MatrixStack matrices, IVertexBuilder vertices, RenderStage renderStage, int light, int position, float doorLeftValue, float doorRightValue);

	protected abstract void renderHeadPosition1(MatrixStack matrices, IVertexBuilder vertices, RenderStage renderStage, int light, int position, boolean useHeadlights);

	protected abstract void renderHeadPosition2(MatrixStack matrices, IVertexBuilder vertices, RenderStage renderStage, int light, int position, boolean useHeadlights);

	protected abstract void renderEndPosition1(MatrixStack matrices, IVertexBuilder vertices, RenderStage renderStage, int light, int position);

	protected abstract void renderEndPosition2(MatrixStack matrices, IVertexBuilder vertices, RenderStage renderStage, int light, int position);

	protected abstract void renderDoorLabels(MatrixStack matrices, IRenderTypeBuffer renderTypeBuffer, RenderStage renderStage, int light,
											 float positionScaled, float doorLeftValue, float doorRightValue);

	protected abstract int[] getWindowPositions();

	protected abstract int[] getDoorPositions();

	protected abstract int[] getEndPositions();

	protected static void setRotationAngle(ModelRenderer bone, float x, float y, float z) {
		bone.rotateAngleX = x;
		bone.rotateAngleY = y;
		bone.rotateAngleZ = z;
	}

	protected static void renderMirror(ModelRenderer bone, MatrixStack matrices, IVertexBuilder vertices, int light, float position) {
		renderOnce(bone, matrices, vertices, light, position);
		renderOnceFlipped(bone, matrices, vertices, light, position);
	}

	protected static void renderOnce(ModelRenderer bone, MatrixStack matrices, IVertexBuilder vertices, int light, float position) {
		bone.setRotationPoint(0, 0, position);
		setRotationAngle(bone, 0, 0, 0);
		bone.render(matrices, vertices, light, OverlayTexture.NO_OVERLAY);
	}

	protected static void renderOnceFlipped(ModelRenderer bone, MatrixStack matrices, IVertexBuilder vertices, int light, float position) {
		bone.setRotationPoint(0, 0, position);
		setRotationAngle(bone, 0, (float) Math.PI, 0);
		bone.render(matrices, vertices, light, OverlayTexture.NO_OVERLAY);
	}

	protected enum RenderStage {

		LIGHTS, INTERIOR, INTERIOR_TRANSLUCENT, EXTERIOR

	}

}
