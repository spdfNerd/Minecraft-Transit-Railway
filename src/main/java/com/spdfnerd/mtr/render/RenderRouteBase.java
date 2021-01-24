package com.spdfnerd.mtr.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.spdfnerd.mtr.block.BlockPlatformRail;
import com.spdfnerd.mtr.block.IBlock;
import com.spdfnerd.mtr.block.IPropagateBlock;
import com.spdfnerd.mtr.gui.IGui;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public abstract class RenderRouteBase<T extends TileEntity> extends TileEntityRenderer<T> implements IGui {

	private static final float EXTRA_PADDING = 0.0625F;

	public RenderRouteBase(TileEntityRendererDispatcher rendererDispatcher) {
		super(rendererDispatcher);
	}

	@Override
	public final void render(T entity, float tickDelta, MatrixStack matrices, IRenderTypeBuffer renderTypeBuffer, int light, int overlay) {
		final World world = entity.getWorld();
		if (world == null) {
			return;
		}

		final BlockPos pos = entity.getPos();

		final BlockPos platformPos = findPlatformPos(world, pos);
		if (platformPos == null) {
			return;
		}

		final BlockState state = world.getBlockState(pos);
		final Direction facing = IBlock.getStatePropertySafe(state, HorizontalBlock.HORIZONTAL_FACING);
		final int arrowDirection = IBlock.getStatePropertySafe(state, IPropagateBlock.PROPAGATE_PROPERTY);

		final RouteRenderer routeRenderer = new RouteRenderer(matrices, renderTypeBuffer, platformPos, false);

		matrices.push();
		matrices.translate(0.5, 1, 0.5);
		matrices.rotate(Vector3f.YP.rotationDegrees(-facing.getHorizontalAngle()));
		matrices.rotate(Vector3f.ZP.rotationDegrees(180));
		matrices.translate(-0.5, 0, getZ() - SMALL_OFFSET * 2);

		if (isLeft(state)) {
			final int glassLength = getGlassLength(world, pos, facing);
			if (glassLength > 1) {
				switch (getRenderType(world, pos, state)) {
					case ARROW:
						routeRenderer.renderArrow(getSidePadding() + EXTRA_PADDING, glassLength - getSidePadding() - EXTRA_PADDING, getTopPadding() + EXTRA_PADDING, 1 - getBottomPadding() - EXTRA_PADDING, (arrowDirection & 0b10) > 0, (arrowDirection & 0b01) > 0, light);
						break;
					case ROUTE:
						final boolean flipLine = arrowDirection == 1;
						routeRenderer.renderLine(flipLine ? glassLength - getSidePadding() - EXTRA_PADDING * 2 : getSidePadding() + EXTRA_PADDING * 2, flipLine ? getSidePadding() + EXTRA_PADDING * 2 : glassLength - getSidePadding() - EXTRA_PADDING * 2, getTopPadding() + EXTRA_PADDING, 1 - getBottomPadding() - EXTRA_PADDING, getBaseScale(), light);
						break;
				}
			}
		}

		renderAdditional(matrices, renderTypeBuffer, routeRenderer, state, light);

		matrices.pop();
	}

	protected abstract float getZ();

	protected abstract float getSidePadding();

	protected abstract float getBottomPadding();

	protected abstract float getTopPadding();

	protected abstract int getBaseScale();

	protected abstract boolean isLeft(BlockState state);

	protected abstract boolean isRight(BlockState state);

	protected abstract RenderType getRenderType(IWorld world, BlockPos pos, BlockState state);

	protected abstract void renderAdditional(MatrixStack matrices, IRenderTypeBuffer renderTypeBuffer, RouteRenderer routeRenderer, BlockState state, int light);

	private BlockPos findPlatformPos(IWorld world, BlockPos pos) {
		final Direction facing = IBlock.getStatePropertySafe(world, pos, HorizontalBlock.HORIZONTAL_FACING);
		for (int y = 1; y <= 3; y++) {
			for (int x = 1; x <= 2; x++) {
				final BlockPos checkPos = pos.down(y).offset(facing, x);
				if (world.getBlockState(checkPos).getBlock() instanceof BlockPlatformRail) {
					return BlockPlatformRail.getPlatformPos1(world, checkPos);
				}
			}
		}
		return null;
	}

	private int getGlassLength(IWorld world, BlockPos pos, Direction facing) {
		int glassLength = 1;

		while (true) {
			final BlockState state = world.getBlockState(pos.offset(facing.rotateY(), glassLength));
			if (state.getBlock() == world.getBlockState(pos).getBlock() && !isLeft(state)) {
				glassLength++;
				if (isRight(state)) {
					break;
				}
			} else {
				break;
			}
		}

		return glassLength;
	}

	protected enum RenderType {
		ARROW, ROUTE, NONE
	}

}
