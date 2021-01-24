package com.spdfnerd.mtr.render;

import com.spdfnerd.mtr.entity.EntitySP1900Mini;
import com.spdfnerd.mtr.model.ModelSP1900Mini;
import com.spdfnerd.mtr.model.ModelTrainBase;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class RenderSP1900Mini extends RenderTrainBase<EntitySP1900Mini> {

	private static final ResourceLocation texture = new ResourceLocation("mtr:textures/entity/sp1900.png");
	private static final ModelSP1900Mini model = new ModelSP1900Mini();

	public RenderSP1900Mini(EntityRendererManager manager) {
		super(manager);
	}

	@Override
	public ResourceLocation getEntityTexture(EntitySP1900Mini entity) {
		return texture;
	}

	@Override
	protected ModelTrainBase getModel() {
		return model;
	}

}
