package com.spdfnerd.mtr.render;

import com.spdfnerd.mtr.entity.EntityMTrain;
import com.spdfnerd.mtr.model.ModelMTrain;
import com.spdfnerd.mtr.model.ModelTrainBase;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class RenderMTrain extends RenderTrainBase<EntityMTrain> {

	private static final ResourceLocation texture = new ResourceLocation("mtr:textures/entity/m_train.png");
	private static final ModelMTrain model = new ModelMTrain();

	public RenderMTrain(EntityRendererManager manager) {
		super(manager);
	}

	@Override
	public ResourceLocation getEntityTexture(EntityMTrain entity) {
		return texture;
	}

	@Override
	protected ModelTrainBase getModel() {
		return model;
	}

}
