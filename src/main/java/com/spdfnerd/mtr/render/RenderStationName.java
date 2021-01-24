package com.spdfnerd.mtr.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.spdfnerd.mtr.block.BlockStationNameBase;
import com.spdfnerd.mtr.block.IBlock;
import com.spdfnerd.mtr.gui.ClientData;
import com.spdfnerd.mtr.gui.IGui;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IWorld;

public class RenderStationName<T extends BlockStationNameBase.TileEntityStationNameBase> extends TileEntityRenderer<T> implements IGui {

	public RenderStationName(TileEntityRendererDispatcher dispatcher) {
		super(dispatcher);
	}

	@Override
	public void render(T entity, float tickDelta, MatrixStack matrices, IRenderTypeBuffer renderTypeBuffer, int light, int overlay) {
		if (!entity.shouldRender()) {
			return;
		}

		final IWorld world = entity.getWorld();
		if (world == null) {
			return;
		}

		final BlockPos pos = entity.getPos();
		final String stationName =
				ClientData.stations.stream().filter(station1 -> station1.inStation(pos.getX(), pos.getZ())).findFirst().map(station2 -> station2.name).orElse(new TranslationTextComponent("gui.mtr.untitled").getString());

		final BlockState state = world.getBlockState(pos);
		final Direction facing = IBlock.getStatePropertySafe(state, BlockStationNameBase.HORIZONTAL_FACING);
		final int color;
		switch (IBlock.getStatePropertySafe(state, BlockStationNameBase.COLOUR)) {
			case 0:
				color = ARGB_WHITE;
				break;
			case 1:
				color = ARGB_LIGHT_GRAY;
				break;
			default:
				color = ARGB_BLACK;
				break;
		}

		matrices.push();
		matrices.translate(0.5, 0.5 + entity.yOffset, 0.5);
		matrices.rotate(Vector3f.YP.rotationDegrees(-facing.getHorizontalAngle()));
		matrices.rotate(Vector3f.ZP.rotationDegrees(180));
		matrices.translate(0, 0, 0.5 - entity.zOffset - SMALL_OFFSET);
		matrices.scale(1F / entity.scale, 1F / entity.scale, 1F / entity.scale);
		final int drawStyle = entity.hasShadow && color != ARGB_BLACK ? 1 : 0;
		IGui.drawStringWithFont(matrices, Minecraft.getInstance().fontRenderer, entity.verticalChinese ? IGui.formatVerticalChinese(stationName) : stationName,
				1, 1, 0, 0, color, drawStyle, null);
		matrices.pop();
	}

}
