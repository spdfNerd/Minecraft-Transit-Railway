package com.spdfnerd.mtr.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.spdfnerd.mtr.block.BlockClock;
import com.spdfnerd.mtr.block.IBlock;
import com.spdfnerd.mtr.gui.IGui;
import com.spdfnerd.mtr.tileentity.ClockTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;

public class RenderClock extends TileEntityRenderer<ClockTileEntity> implements IGui, IBlock {

	public RenderClock(TileEntityRendererDispatcher dispatcher) {
		super(dispatcher);
	}

	@Override
	public void render(ClockTileEntity entity, float tickDelta, MatrixStack matrices, IRenderTypeBuffer renderTypeBuffer, int light, int overlay) {
		final World world = entity.getWorld();
		if (world == null) {
			return;
		}

		final BlockPos pos = entity.getPos();

		final BlockState state = world.getBlockState(pos);
		final boolean rotated = IBlock.getStatePropertySafe(state, BlockClock.FACING);

		matrices.push();
		matrices.translate(0.5, 0.3125, 0.5);
		if (rotated) {
			matrices.rotate(Vector3f.YP.rotationDegrees(90));
		}

		final long time = world.getDayTime() + 6000;

		drawHand(matrices, renderTypeBuffer, time * 360F / 12000, light, true);
		drawHand(matrices, renderTypeBuffer, time * 360F / 1000, light, false);

		matrices.rotate(Vector3f.YP.rotationDegrees(180));
		drawHand(matrices, renderTypeBuffer, time * 360F / 12000, light, true);
		drawHand(matrices, renderTypeBuffer, time * 360F / 1000, light, false);

		matrices.pop();
	}

	private static void drawHand(MatrixStack matrices, IRenderTypeBuffer renderTypeBuffer, float rotation, int light, boolean isHourHand) {
		matrices.push();
		matrices.rotate(Vector3f.ZN.rotationDegrees(rotation));
		IGui.drawRectangleBright(matrices, renderTypeBuffer, -0.01F, isHourHand ? 0.15F : 0.24F, isHourHand ? 0.1F : 0.105F, 0.01F, -0.03F, isHourHand ? 0.1F : 0.105F, ARGB_LIGHT_GRAY);
		matrices.pop();
	}

}
