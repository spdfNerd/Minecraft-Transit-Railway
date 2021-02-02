package com.spdfnerd.mtr.model;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.model.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.state.Property;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.client.model.data.IDynamicBakedModel;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.pipeline.BakedQuadBuilder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Function;

public abstract class CustomBlockModelBase implements IDynamicBakedModel, IUnbakedModel {

	private final Map<ImmutableMap<Property<?>, Comparable<?>>, IBakedModel> meshMap = new HashMap<>();

	@Override
	public Collection<ResourceLocation> getDependencies() {
		return Collections.emptyList();
	}

	@Nullable
	@Override
	public IBakedModel bakeModel(ModelBakery modelBakery, Function<RenderMaterial, TextureAtlasSprite> spriteGetter, IModelTransform transform, ResourceLocation location) {
		getBlock().getStateContainer().getValidStates().forEach(state -> meshMap.put(state.getValues(), bake(state, spriteGetter)));
		return this;
	}

	@Nonnull
	@Override
	public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand, @Nonnull IModelData extraData) {
		return null;
	}

	@Override
	public boolean isAmbientOcclusion() {
		return true;
	}

	@Override
	public boolean isGui3d() {
		return false;
	}

	@Override
	public boolean isSideLit() {
		return false;
	}

	@Override
	public boolean isBuiltInRenderer() {
		return false;
	}

	@Override
	public ItemCameraTransforms getItemCameraTransforms() {
		return null;
	}

	@Override
	public ItemOverrideList getOverrides() {
		return null;
	}

	protected abstract IBakedModel bake(BlockState state, Function<RenderMaterial, TextureAtlasSprite> spriteGetter);

	protected abstract Block getBlock();

	protected void putVertex(BakedQuadBuilder builder, Vector3d normal, double x, double y, double z, float u, float v, TextureAtlasSprite sprite, float r,
						   float g, float b) {
		ImmutableList<VertexFormatElement> elements = builder.getVertexFormat().getElements().asList();
		for (int i = 0; i < elements.size(); i++) {
			VertexFormatElement element = elements.get(i);
			switch (element.getUsage()) {
				case POSITION:
					builder.put(i, (float) x, (float) y, (float) z, 1f);
					break;
				case COLOR:
					builder.put(i, r, g, b, 1f);
					break;
				case UV:
					switch (element.getIndex()) {
						case 0:
							float iu = sprite.getInterpolatedU(u);
							float iv = sprite.getInterpolatedV(v);
							builder.put(i, iu, iv);
							break;
						case 2:
							builder.put(i, (short) 0, (short) 0);
							break;
						default:
							builder.put(i);
							break;
					}
					break;
				case NORMAL:
					builder.put(i, (float) normal.x, (float) normal.y, (float) normal.z);
					break;
				default:
					builder.put(i);
					break;
			}
		}
	}
}
