package com.spdfnerd.mtr.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.spdfnerd.mtr.data.Platform;
import com.spdfnerd.mtr.data.Station;
import com.spdfnerd.mtr.data.Train;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.IRenderable;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.gen.Heightmap;


public class WidgetMap implements IRenderable, IGuiEventListener, IGui {

	private int x;
	private int y;
	private int width;
	private int height;
	private double scale;
	private double centerX;
	private double centerY;
	private Tuple<Integer, Integer> drawStation1, drawStation2;
	private int mapState;

	private final OnDrawCorners onDrawCorners;
	private final OnClickPlatform onClickPlatform;
	private final ClientWorld world;
	private final ClientPlayerEntity player;
	private final FontRenderer fontRenderer;

	private static final int ARGB_BLUE = 0xFF4285F4;
	private static final int SCALE_UPPER_LIMIT = 16;
	private static final double SCALE_LOWER_LIMIT = 0.0078125;

	public WidgetMap(OnDrawCorners onDrawCorners, OnClickPlatform onClickPlatform) {
		this.onDrawCorners = onDrawCorners;
		this.onClickPlatform = onClickPlatform;

		final Minecraft minecraftClient = Minecraft.getInstance();
		world = minecraftClient.world;
		player = minecraftClient.player;
		fontRenderer = minecraftClient.fontRenderer;
		if (player == null) {
			centerX = 0;
			centerY = 0;
		} else {
			centerX = player.getPosX();
			centerY = player.getPosZ();
		}
		scale = 1;
	}

	@Override
	public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
		final Tessellator tessellator = Tessellator.getInstance();
		final BufferBuilder buffer = tessellator.getBuffer();
		RenderSystem.enableBlend();
		RenderSystem.disableTexture();
		RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE,
				GlStateManager.DestFactor.ZERO);
		buffer.begin(7, DefaultVertexFormats.POSITION_COLOR);

		final Tuple<Integer, Integer> topLeft = coordsToWorldPos(0, 0);
		final Tuple<Integer, Integer> bottomRight = coordsToWorldPos(width, height);
		final int increment = scale >= 1 ? 1 : (int) Math.ceil(1 / scale);
		for (int i = topLeft.getA(); i <= bottomRight.getA(); i += increment) {
			for (int j = topLeft.getB(); j <= bottomRight.getB(); j += increment) {
				if (world != null) {
					final int color =
							IGui.divideColorRGB(world.getBlockState(new BlockPos(i, world.getHeight(Heightmap.Type.MOTION_BLOCKING, i, j) - 1, j)).getBlock().getMaterialColor().colorValue,
									2);
					drawRectangleFromWorldCoords(buffer, i, j, i + increment, j + increment, ARGB_BLACK + color);
				}
			}
		}

		for (Platform platform : ClientData.platforms) {
			final BlockPos posStart = platform.getPos1();
			final BlockPos posEnd = platform.getPos2().add(1, 0, 1);
			drawRectangleFromWorldCoords(buffer, posStart.getX(), posStart.getZ(), posEnd.getX(), posEnd.getZ(), ARGB_WHITE);
		}
		for (Station station : ClientData.stations) {
			drawRectangleFromWorldCoords(buffer, station.corner1, station.corner2, ARGB_BLACK_TRANSLUCENT + station.color);
		}
		for (Train train : ClientData.trains) {
			for (int i = 0; i < train.posX.length - 1; i++) {
				final double carX = (train.posX[i] + train.posX[i + 1]) / 2;
				final double carZ = (train.posZ[i] + train.posZ[i + 1]) / 2;
				drawRectangleFromWorldCoords(buffer, carX - 0.5, carZ - 0.5, carX + 0.5, carZ + 0.5, ARGB_BLACK + train.color);
			}
		}

		if (mapState == 1 && drawStation1 != null && drawStation2 != null) {
			drawRectangleFromWorldCoords(buffer, drawStation1, drawStation2, ARGB_WHITE_TRANSLUCENT);
		}

		if (player != null) {
			drawFromWorldCoords(player.getPosX(), player.getPosZ(), (x1, y1) -> {
				drawRectangle(buffer, x1 - 2, y1 - 3, x1 + 2, y1 + 3, ARGB_WHITE);
				drawRectangle(buffer, x1 - 3, y1 - 2, x1 + 3, y1 + 2, ARGB_WHITE);
				drawRectangle(buffer, x1 - 2, y1 - 2, x1 + 2, y1 + 2, ARGB_BLUE);
			});
		}

		tessellator.draw();
		RenderSystem.enableTexture();
		RenderSystem.disableBlend();


		if (mapState == 1) {
			AbstractGui.drawString(matrices, fontRenderer, new TranslationTextComponent("gui.mtr.edit_station").getString(), x + TEXT_PADDING,
					y + TEXT_PADDING,	ARGB_WHITE);
		} else if (mapState == 2) {
			AbstractGui.drawString(matrices, fontRenderer, new TranslationTextComponent("gui.mtr.edit_route").getString(), x + TEXT_PADDING,
					y + TEXT_PADDING,	ARGB_WHITE);
		}
		if (scale >= 8) {
			for (Platform platform : ClientData.platforms) {
				final BlockPos pos = platform.getMidPos();
				drawFromWorldCoords(pos.getX() + 0.5, pos.getZ() + 0.5, (x1, y1) -> AbstractGui.drawCenteredString(matrices, fontRenderer, platform.name,
						x + (int) x1, y + (int) y1 - TEXT_HEIGHT / 2, ARGB_WHITE));
			}
		}
		if (scale >= 2) {
			for (Station station : ClientData.stations) {
				final BlockPos pos = station.getCenter();
				drawFromWorldCoords(pos.getX(), pos.getZ(), (x1, y1) -> IGui.drawStringWithFont(matrices, fontRenderer, station.name, x + (float) x1, y + (float) y1));
			}
		}

		final Tuple<Integer, Integer> mouseWorldPos = coordsToWorldPos(mouseX - x, mouseY - y);
		final String mousePosText = String.format("(%d, %d)", mouseWorldPos.getA(), mouseWorldPos.getB());
		AbstractGui.drawString(matrices, fontRenderer, mousePosText, x + width - TEXT_PADDING - fontRenderer.getStringWidth(mousePosText), y + TEXT_PADDING,
				ARGB_WHITE);
	}

	@Override
	public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
		if (mapState == 1) {
			drawStation2 = coordsToWorldPos((int) Math.round(mouseX - x), (int) Math.round(mouseY - y));
			if (drawStation1.getA().equals(drawStation2.getA())) {
				drawStation2 = new Tuple<>(drawStation2.getA() + 1, drawStation2.getB());
			}
			if (drawStation1.getB().equals(drawStation2.getB())) {
				drawStation2 = new Tuple<>(drawStation2.getA(), drawStation2.getB() + 1);
			}
			onDrawCorners.onDrawCorners(drawStation1, drawStation2);
		} else {
			centerX -= deltaX / scale;
			centerY -= deltaY / scale;
		}
		return true;
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int button) {
		if (isMouseOver(mouseX, mouseY)) {
			if (mapState == 1) {
				drawStation1 = coordsToWorldPos((int) (mouseX - x), (int) (mouseY - y));
				drawStation2 = null;
			} else if (mapState == 2) {
				final Tuple<Integer, Integer> worldPos = coordsToWorldPos((int) (mouseX - x), (int) (mouseY - y));
				ClientData.platforms.stream().filter(platform -> platform.inPlatform(worldPos.getA(), worldPos.getB())).findAny().ifPresent(platform -> onClickPlatform.onClickPlatform(platform.id));
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
		final double oldScale = scale;
		if (oldScale > SCALE_LOWER_LIMIT && amount < 0) {
			centerX -= (mouseX - x - width / 2D) / scale;
			centerY -= (mouseY - y - height / 2D) / scale;
		}
		scale(amount);
		if (oldScale < SCALE_UPPER_LIMIT && amount > 0) {
			centerX += (mouseX - x - width / 2D) / scale;
			centerY += (mouseY - y - height / 2D) / scale;
		}
		return true;
	}

	@Override
	public boolean isMouseOver(double mouseX, double mouseY) {
		return mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height && !(mouseX >= x + width - SQUARE_SIZE && mouseY >= y + height - SQUARE_SIZE * 2);
	}

	public void setPositionAndSize(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void scale(double amount) {
		scale *= Math.pow(2, amount);
		scale = MathHelper.clamp(scale, SCALE_LOWER_LIMIT, SCALE_UPPER_LIMIT);
	}

	public void find(double x1, double z1, double x2, double z2) {
		centerX = (x1 + x2) / 2;
		centerY = (z1 + z2) / 2;
		scale = Math.max(2, scale);
	}

	public void startEditingStation(Station editingStation) {
		mapState = 1;
		drawStation1 = editingStation.corner1;
		drawStation2 = editingStation.corner2;
	}

	public void startEditingRoute() {
		mapState = 2;
	}

	public void stopEditing() {
		mapState = 0;
	}

	private Tuple<Integer, Integer> coordsToWorldPos(int mouseX, int mouseY) {
		final int a = (int) Math.floor((mouseX - width / 2D) / scale + centerX);
		final int b = (int) Math.floor((mouseY - height / 2D) / scale + centerY);
		return new Tuple<>(a, b);
	}

	private void drawFromWorldCoords(double worldX, double worldZ, DrawFromWorldCoords callback) {
		final double coordsX = (worldX - centerX) * scale + width / 2D;
		final double coordsY = (worldZ - centerY) * scale + height / 2D;
		callback.drawFromWorldCoords(coordsX, coordsY);
	}

	private void drawRectangleFromWorldCoords(BufferBuilder buffer, Tuple<Integer, Integer> corner1, Tuple<Integer, Integer> corner2, int color) {
		drawRectangleFromWorldCoords(buffer, corner1.getA(), corner1.getB(), corner2.getA(), corner2.getB(), color);
	}

	private void drawRectangleFromWorldCoords(BufferBuilder buffer, double posX1, double posZ1, double posX2, double posZ2, int color) {
		final double x1 = (posX1 - centerX) * scale + width / 2D;
		final double z1 = (posZ1 - centerY) * scale + height / 2D;
		final double x2 = (posX2 - centerX) * scale + width / 2D;
		final double z2 = (posZ2 - centerY) * scale + height / 2D;
		drawRectangle(buffer, x1, z1, x2, z2, color);
	}

	private void drawRectangle(BufferBuilder buffer, double xA, double yA, double xB, double yB, int color) {
		final double x1 = Math.min(xA, xB);
		final double y1 = Math.min(yA, yB);
		final double x2 = Math.max(xA, xB);
		final double y2 = Math.max(yA, yB);
		if (x1 < width && y1 < height && x2 >= 0 && y2 >= 0) {
			IGui.drawRectangle(buffer, x + x1, y + y1, x + x2, y + y2, color);
		}
	}

	@FunctionalInterface
	public interface OnDrawCorners {
		void onDrawCorners(Tuple<Integer, Integer> corner1, Tuple<Integer, Integer> corner2);
	}

	@FunctionalInterface
	public interface OnClickPlatform {
		void onClickPlatform(long stationId);
	}

	@FunctionalInterface
	private interface DrawFromWorldCoords {
		void drawFromWorldCoords(double x1, double y1);
	}

}
