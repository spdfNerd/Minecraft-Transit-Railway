package com.spdfnerd.mtr.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.spdfnerd.mtr.block.IBlock;
import com.spdfnerd.mtr.gui.IGui;
import com.spdfnerd.mtr.tileentity.APGGlassTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class RenderAPGGlass extends RenderRouteBase<APGGlassTileEntity> implements IGui, IBlock {

	private static final float COLOR_STRIP_START = 0.75F;
	private static final float COLOR_STRIP_END = 0.78125F;

	public RenderAPGGlass(TileEntityRendererDispatcher dispatcher) {
		super(dispatcher);
	}

	@Override
	protected float getZ() {
		return 0.25F;
	}

	@Override
	protected float getSidePadding() {
		return 0.5F;
	}

	@Override
	protected float getBottomPadding() {
		return 0.25F;
	}

	@Override
	protected float getTopPadding() {
		return 0.5F;
	}

	@Override
	protected int getBaseScale() {
		return 480;
	}

	@Override
	protected boolean isLeft(BlockState state) {
		return IBlock.getStatePropertySafe(state, SIDE_EXTENDED) == EnumSide.LEFT;
	}

	@Override
	protected boolean isRight(BlockState state) {
		return IBlock.getStatePropertySafe(state, SIDE_EXTENDED) == EnumSide.RIGHT;
	}

	@Override
	protected RenderType getRenderType(IWorld world, BlockPos pos, BlockState state) {
		if (IBlock.getStatePropertySafe(state, HALF) == SlabType.BOTTOM) {
			return RenderType.NONE;
		} else if ((Math.floorMod(pos.getX(), 8) < 4) == (Math.floorMod(pos.getZ(), 8) < 4)) {
			return RenderType.ARROW;
		} else {
			return RenderType.ROUTE;
		}
	}

	@Override
	protected void renderAdditional(MatrixStack matrices, IRenderTypeBuffer renderTypeBuffer, RouteRenderer routeRenderer, BlockState state, int light) {
		if (IBlock.getStatePropertySafe(state, HALF) == SlabType.TOP && IBlock.getStatePropertySafe(state, SIDE_EXTENDED) != EnumSide.SINGLE) {
			final boolean isLeft = isLeft(state);
			final boolean isRight = isRight(state);
			routeRenderer.renderColorStrip(isLeft ? getSidePadding() : 0, COLOR_STRIP_START, 0, isRight ? getSidePadding() : 1, COLOR_STRIP_END, 0, light);
			routeRenderer.renderColorStrip(isRight ? getSidePadding() : 1, COLOR_STRIP_START, 0.125F, isLeft ? getSidePadding() : 0, COLOR_STRIP_END, 0.125F, light);
			IGui.drawRectangle(matrices, renderTypeBuffer, isLeft ? getSidePadding() : 0, getTopPadding(), isRight ? getSidePadding() : 1, COLOR_STRIP_START, SMALL_OFFSET * 2, ARGB_WHITE, light);
			IGui.drawRectangle(matrices, renderTypeBuffer, isRight ? getSidePadding() : 1, getTopPadding(), isLeft ? getSidePadding() : 0, COLOR_STRIP_START, 0.125F - SMALL_OFFSET * 2, ARGB_WHITE, light);
		}
	}

}
