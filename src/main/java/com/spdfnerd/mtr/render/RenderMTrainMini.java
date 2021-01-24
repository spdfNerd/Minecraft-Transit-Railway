package com.spdfnerd.mtr.render;

import com.spdfnerd.mtr.entity.EntityMTrainMini;
import com.spdfnerd.mtr.model.ModelMTrainMini;
import com.spdfnerd.mtr.model.ModelTrainBase;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class RenderMTrainMini extends RenderTrainBase<EntityMTrainMini> {

	private static final ResourceLocation texture = new ResourceLocation("mtr:textures/entity/m_train.png");
	private static final ModelMTrainMini model = new ModelMTrainMini();

	public RenderMTrainMini(EntityRendererManager manager) {
		super(manager);
	}

	@Override
	public ResourceLocation getEntityTexture(EntityMTrainMini entity) {
		return texture;
	}

	@Override
	protected ModelTrainBase getModel() {
		return model;
	}

}
