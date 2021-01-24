package com.spdfnerd.mtr.render;

import com.spdfnerd.mtr.entity.EntitySP1900;
import com.spdfnerd.mtr.model.ModelSP1900;
import com.spdfnerd.mtr.model.ModelTrainBase;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class RenderSP1900 extends RenderTrainBase<EntitySP1900> {

	private static final ResourceLocation texture = new ResourceLocation("mtr:textures/entity/sp1900.png");
	private static final ModelSP1900 model = new ModelSP1900();

	public RenderSP1900(EntityRendererManager manager) {
		super(manager);
	}

	@Override
	public ResourceLocation getEntityTexture(EntitySP1900 entity) {
		return texture;
	}

	@Override
	protected ModelTrainBase getModel() {
		return model;
	}
	
}
