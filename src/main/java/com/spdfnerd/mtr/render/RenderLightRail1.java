package com.spdfnerd.mtr.render;

import com.spdfnerd.mtr.entity.EntityLightRail1;
import com.spdfnerd.mtr.model.ModelSP1900;
import com.spdfnerd.mtr.model.ModelTrainBase;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class RenderLightRail1 extends RenderTrainBase<EntityLightRail1> {

	private static final ResourceLocation texture = new ResourceLocation("textures/block/glowstone.png");
	private static final ModelSP1900 model = new ModelSP1900();

	public RenderLightRail1(EntityRendererManager dispatcher) {
		super(dispatcher);
	}

	@Override
	public ResourceLocation getEntityTexture(EntityLightRail1 entity) {
		return texture;
	}

	@Override
	protected ModelTrainBase getModel() {
		return model;
	}

}
