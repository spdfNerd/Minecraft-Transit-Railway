package com.spdfnerd.mtr.model;

import com.mojang.datafixers.util.Pair;
import com.spdfnerd.mtr.block.BlockAPGDoor;
import com.spdfnerd.mtr.block.IBlock;
import net.fabricmc.fabric.api.renderer.v1.Renderer;
import net.fabricmc.fabric.api.renderer.v1.RendererAccess;
import net.fabricmc.fabric.api.renderer.v1.mesh.MutableQuadView;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.IUnbakedModel;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.data.IModelData;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Function;

public class APGDoorModel extends CustomBlockModelBase implements IBlock {

	private static final RenderMaterial[] SPRITE_IDS = new RenderMaterial[]{
			new RenderMaterial(AtlasTexture.LOCATION_BLOCKS_TEXTURE, new ResourceLocation("mtr:block/black")),
			new RenderMaterial(AtlasTexture.LOCATION_BLOCKS_TEXTURE, new ResourceLocation("mtr:block/psd_side_light")),
			new RenderMaterial(AtlasTexture.LOCATION_BLOCKS_TEXTURE, new ResourceLocation("mtr:block/apg_door_bottom")),
			new RenderMaterial(AtlasTexture.LOCATION_BLOCKS_TEXTURE, new ResourceLocation("mtr:block/apg_door_top"))
	};
	private static final int SPRITE_COUNT = SPRITE_IDS.length;

	private final TextureAtlasSprite[] SPRITES = new TextureAtlasSprite[SPRITE_COUNT];

	@Override
	public IBakedModel bake(BlockState state, Function<RenderMaterial, TextureAtlasSprite> spriteGetter) {
		for (int i = 0; i < SPRITE_COUNT; i++) {
			SPRITES[i] = spriteGetter.apply(SPRITE_IDS[i]);
		}

		Renderer renderer = RendererAccess.INSTANCE.getRenderer();
		if (renderer != null) {
//			MeshBuilder builder = renderer.meshBuilder();
//			QuadEmitter emitter = builder.getEmitter();

			final Direction facing = IBlock.getStatePropertySafe(state, BlockAPGDoor.HORIZONTAL_FACING);
			final boolean side = IBlock.getStatePropertySafe(state, SIDE) == EnumSide.RIGHT;
			final float open = (side ? -1F : 1F) * IBlock.getStatePropertySafe(state, BlockAPGDoor.OPEN) / BlockAPGDoor.MAX_OPEN_VALUE;
			final boolean top = IBlock.getStatePropertySafe(state, HALF) == SlabType.TOP;

//			emitter.square(facing, 0, 0, 1, 1, 0.0625F);
//			emitter.spriteBake(0, SPRITES[top ? 3 : 2], MutableQuadView.BAKE_LOCK_UV + (side ? 0 : MutableQuadView.BAKE_FLIP_U));
//			emitter.square(facing, open, 0, 1 + open, 1, 0.0625F);
//			emitter.spriteColor(0, -1, -1, -1, -1);
//			emitter.emit();

			emitter.square(facing.getOpposite(), 0, 0, 1, 1, 0.875F);
			emitter.spriteBake(0, SPRITES[top ? 3 : 2], MutableQuadView.BAKE_LOCK_UV + (side ? MutableQuadView.BAKE_FLIP_U : 0));
			emitter.square(facing.getOpposite(), -open, 0, 1 - open, 1, 0.875F);
			emitter.spriteColor(0, -1, -1, -1, -1);
			emitter.emit();

			emitter.square(facing.rotateYClockwise(), 0.875F, 0, 0.9375F, top ? 0.5F : 1, open);
			emitter.spriteBake(0, SPRITES[side ? 1 : 0], MutableQuadView.BAKE_LOCK_UV);
			emitter.spriteColor(0, -1, -1, -1, -1);
			emitter.emit();

			emitter.square(facing.rotateYCounterclockwise(), 0.0625F, 0, 0.125F, top ? 0.5F : 1, -open);
			emitter.spriteBake(0, SPRITES[side ? 0 : 1], MutableQuadView.BAKE_LOCK_UV);
			emitter.spriteColor(0, -1, -1, -1, -1);
			emitter.emit();

			drawThinTexture(emitter, facing, side);
			if (top) {
				customShape(emitter, facing, -open, 0.5F, 0.0625F, -open, 0.5F, 0.125F, 1 - open, 0.5F, 0.125F, 1 - open, 0.5F, 0.0625F);
			} else {
				customShape(emitter, facing, -open, 0.375F, 0, -open, 0.4375F, 0.0625F, 1 - open, 0.4375F, 0.0625F, 1 - open, 0.375F, 0);

				drawThinTexture(emitter, facing, side);
				emitter.square(facing, open, 0, 1 + open, 0.375F, 0);
				emitter.spriteColor(0, -1, -1, -1, -1);
				emitter.emit();

				emitter.square(facing.rotateYClockwise(), 0.9375F, 0, 1, 0.375F, open);
				emitter.spriteBake(0, SPRITES[side ? 1 : 0], MutableQuadView.BAKE_LOCK_UV);
				emitter.spriteColor(0, -1, -1, -1, -1);
				emitter.emit();

				emitter.square(facing.rotateYClockwise(), 0.9375F, 0.375F, 1, 0.4375F, open);
				emitter.spriteBake(0, SPRITES[side ? 1 : 0], MutableQuadView.BAKE_LOCK_UV);
				customShape(emitter, facing.rotateYClockwise(), 0.0625F, 0.375F, open, 0.0625F, 0.4375F, open, 0.0625F, 0.375F, open, 0, 0.375F, open);

				emitter.square(facing.rotateYCounterclockwise(), 0, 0, 0.0625F, 0.375F, -open);
				emitter.spriteBake(0, SPRITES[side ? 0 : 1], MutableQuadView.BAKE_LOCK_UV);
				emitter.spriteColor(0, -1, -1, -1, -1);
				emitter.emit();

				emitter.square(facing.rotateYCounterclockwise(), 0, 0.375F, 0.0625F, 0.4375F, -open);
				emitter.spriteBake(0, SPRITES[side ? 0 : 1], MutableQuadView.BAKE_LOCK_UV);
				customShape(emitter, facing.rotateYCounterclockwise(), 0.9375F, 0.375F, -open, 0.9375F, 0.4375F, -open, 0.9375F, 0.375F, -open, 1, 0.375F, -open);
			}

			return builder.build();
		} else {
			return null;
		}
	}

	@Override
	public Collection<RenderMaterial> getTextures(Function<ResourceLocation, IUnbakedModel> modelGetter, Set<Pair<String, String>> missingTextureErrors) {
		return Arrays.asList(SPRITE_IDS);
	}

	@Override
	public TextureAtlasSprite getParticleTexture() {
		return SPRITES[3];
	}

	@Override
	protected Block getBlock() {
		return new BlockAPGDoor();
	}

	private void drawThinTexture(QuadEmitter emitter, Direction facing, boolean side) {
		emitter.square(facing, 0, 0.46875F, 1, 0.5F, 0);
		emitter.spriteBake(0, SPRITES[3], MutableQuadView.BAKE_LOCK_UV + (side ? 0 : MutableQuadView.BAKE_FLIP_U));
	}

}
