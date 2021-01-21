package com.spdfnerd.mtr.render;

import com.spdfnerd.mtr.entity.EntityMTrain;
import com.spdfnerd.mtr.model.ModelMTrain;
import com.spdfnerd.mtr.model.ModelTrainBase;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.util.Identifier;

public class RenderMTrain extends RenderTrainBase<EntityMTrain> {

	private static final Identifier texture = new Identifier("mtr:textures/entity/m_train.png");
	private static final ModelMTrain model = new ModelMTrain();

	public RenderMTrain(EntityRenderDispatcher dispatcher) {
		super(dispatcher);
	}

	@Override
	public Identifier getTexture(EntityMTrain entity) {
		return texture;
	}

	@Override
	protected ModelTrainBase getModel() {
		return model;
	}
}
