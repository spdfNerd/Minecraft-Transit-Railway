package com.spdfnerd.mtr.render;

import net.minecraft.client.renderer.RenderState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

public class MoreRenderTypes extends RenderState {

	public MoreRenderTypes(String name, Runnable beginAction, Runnable endAction) {
		super(name, beginAction, endAction);
	}

	public static RenderType getLight(ResourceLocation texture) {
		final RenderType.State multiPhaseParameters = getParameters(texture).alpha(DEFAULT_ALPHA).build(true);
		return RenderType.makeType("train_light", DefaultVertexFormats.ENTITY, 7, 256, false, false, multiPhaseParameters);
	}

	public static RenderType getInterior(ResourceLocation texture) {
		final RenderType.State multiPhaseParameters =
				getParameters(texture).diffuseLighting(DIFFUSE_LIGHTING_ENABLED).alpha(DEFAULT_ALPHA).overlay(OVERLAY_ENABLED).build(true);
		return RenderType.makeType("train_interior", DefaultVertexFormats.ENTITY, 7, 256, true, true, multiPhaseParameters);
	}

	public static RenderType getInteriorTranslucent(ResourceLocation texture) {
		final RenderType.State multiPhaseParameters = getParameters(texture).transparency(TRANSLUCENT_TRANSPARENCY).diffuseLighting(DIFFUSE_LIGHTING_ENABLED).alpha(DEFAULT_ALPHA).overlay(OVERLAY_ENABLED).build(true);
		return RenderType.makeType("train_interior", DefaultVertexFormats.ENTITY, 7, 256, true, true, multiPhaseParameters);
	}

	public static RenderType getExterior(ResourceLocation texture) {
		final RenderType.State multiPhaseParameters =
				getParameters(texture).diffuseLighting(DIFFUSE_LIGHTING_ENABLED).alpha(DEFAULT_ALPHA).lightmap(LIGHTMAP_ENABLED).overlay(OVERLAY_ENABLED).build(true);
		return RenderType.makeType("train_exterior", DefaultVertexFormats.ENTITY, 7, 256, true, true, multiPhaseParameters);
	}

	public static RenderType getRectangleBright() {
		final RenderType.State multiPhaseParameters = RenderType.State.getBuilder().build(true);
		return RenderType.makeType("rectangle_bright", DefaultVertexFormats.POSITION_COLOR, 7, 256, true, true, multiPhaseParameters);
	}

	private static RenderType.State.Builder getParameters(ResourceLocation texture) {
		return RenderType.State.getBuilder().texture(new RenderState.TextureState(texture, false, false));
	}

}
