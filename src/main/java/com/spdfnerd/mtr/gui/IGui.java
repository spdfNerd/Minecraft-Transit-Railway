package com.spdfnerd.mtr.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.spdfnerd.mtr.MTR;
import com.spdfnerd.mtr.model.ModelTrainBase;
import com.spdfnerd.mtr.render.MoreRenderTypes;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Matrix3f;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;

import java.util.stream.IntStream;

public interface IGui {

	int SQUARE_SIZE = 20;
	int PANEL_WIDTH = 144;
	int TEXT_HEIGHT = 8;
	int TEXT_PADDING = 6;
	int TEXT_FIELD_PADDING = 4;
	int LINE_HEIGHT = 10;

	float SMALL_OFFSET = 0.0001F;

	int RGB_WHITE = 0xFFFFFF;
	int ARGB_WHITE = 0xFFFFFFFF;
	int ARGB_BLACK = 0xFF000000;
	int ARGB_WHITE_TRANSLUCENT = 0x7FFFFFFF;
	int ARGB_BLACK_TRANSLUCENT = 0x7F000000;
	int ARGB_LIGHT_GRAY = 0xFFAAAAAA;
	int ARGB_BACKGROUND = 0xFF121212;

	static String formatStationName(String name) {
		return name.replace('|', ' ');
	}

	static String textOrUntitled(String text) {
		return text.isEmpty() ? new StringTextComponent("gui.mtr.untitled").getString() : text;
	}

	static String formatVerticalChinese(String text) {
		final StringBuilder textBuilder = new StringBuilder();

		for (int i = 0; i < text.length(); i++) {
			final boolean isChinese = Character.UnicodeScript.of(text.codePointAt(i)) == Character.UnicodeScript.HAN;
			if (isChinese) {
				textBuilder.append('|');
			}
			textBuilder.append(text, i, i + 1);
			if (isChinese) {
				textBuilder.append('|');
			}
		}

		String newText = textBuilder.toString();
		while (newText.contains("||")) {
			newText = newText.replace("||", "|");
		}

		return newText;
	}

	static void drawStringWithFont(MatrixStack matrices, FontRenderer fontRenderer, String text, float x, float y) {
		drawStringWithFont(matrices, fontRenderer, text, 1, 1, x, y, ARGB_WHITE, 1, null);
	}

	static void drawStringWithFont(MatrixStack matrices, FontRenderer fontRenderer, String text, int horizontalAlignment, int verticalAlignment, float x,
								   float y, int textColor, int drawStyle, DrawingCallback drawingCallback) {
		while (text.contains("||")) {
			text = text.replace("||", "|");
		}
		final String[] textSplit = text.split("\\|");

		final int[] lineHeights = new int[textSplit.length];
		for (int i = 0; i < textSplit.length; i++) {
			final boolean hasChinese = textSplit[i].codePoints().anyMatch(codePoint -> Character.UnicodeScript.of(codePoint) == Character.UnicodeScript.HAN);
			lineHeights[i] = LINE_HEIGHT * (hasChinese ? 2 : 1);
		}

		final Style style = Style.EMPTY.setFontId(new ResourceLocation(MTR.MOD_ID, "com/spdfnerd/mtr"));
		final int totalHeight = IntStream.of(lineHeights).sum();
		int totalWidth = 0;
		float offset = y - verticalAlignment * totalHeight / 2F;
		for (int i = 0; i < textSplit.length; i++) {
			final String orderedText = new StringTextComponent(textSplit[i]).mergeStyle(style).toString();
			final int textWidth = fontRenderer.getStringWidth(orderedText);
			totalWidth = Math.max(textWidth, totalWidth);
			switch (drawStyle) {
				case 0:
					fontRenderer.drawString(matrices, orderedText, x - horizontalAlignment * textWidth / 2F, offset, textColor);
					break;
				case 1:
					fontRenderer.drawStringWithShadow(matrices, orderedText, x - horizontalAlignment * textWidth / 2F, offset, textColor);
					break;
			}
			offset += lineHeights[i];
		}

		if (drawingCallback != null) {
			final float x1 = x - horizontalAlignment * totalWidth / 2F;
			final float y1 = y - verticalAlignment * totalHeight / 2F;
			drawingCallback.drawingCallback(x1, y1, x1 + totalWidth, y1 + totalHeight);
		}
	}

	static void setPositionAndWidth(Widget widget, int x, int y, int widgetWidth) {
		widget.x = x;
		widget.y = y;
		widget.setWidth(widgetWidth);
	}

	static int divideColorRGB(int color, int amount) {
		final int r = ((color >> 16) & 0xFF) / amount;
		final int g = ((color >> 8) & 0xFF) / amount;
		final int b = (color & 0xFF) / amount;
		return (r << 16) + (g << 8) + b;
	}

	static void drawRectangle(IVertexBuilder vertex, double x1, double y1, double x2, double y2, int color) {
		final int a = (color >> 24) & 0xFF;
		final int r = (color >> 16) & 0xFF;
		final int g = (color >> 8) & 0xFF;
		final int b = color & 0xFF;
		vertex.pos(x1, y1, 0).color(r, g, b, a).endVertex();
		vertex.pos(x1, y2, 0).color(r, g, b, a).endVertex();
		vertex.pos(x2, y2, 0).color(r, g, b, a).endVertex();
		vertex.pos(x2, y1, 0).color(r, g, b, a).endVertex();
	}

	static void drawRectangle(MatrixStack matrices, IRenderTypeBuffer renderTypeBuffer, float x1, float y1, float x2, float y2, float z, int color, int light) {
		drawRectangle(matrices, renderTypeBuffer, x1, y1, z, x2, y2, z, color, light);
	}

	static void drawRectangle(MatrixStack matrices, IRenderTypeBuffer renderTypeBuffer, float x1, float y1, float z1, float x2, float y2, float z2, int color, int light) {
		drawTexture(matrices, renderTypeBuffer, "mtr:textures/block/white.png", x1, y1, z1, x2, y2, z2, 0, 0, 1, 1, color, light);
	}

	static void drawTexture(MatrixStack matrices, IRenderTypeBuffer renderTypeBuffer, String texture, float x, float y, float width, float height, int light) {
		drawTexture(matrices, renderTypeBuffer, texture, x, y, 0, x + width, y + height, 0, 0, 0, 1, 1, -1, light);
	}

	static void drawTexture(MatrixStack matrices, IRenderTypeBuffer renderTypeBuffer, String texture, float x, float y, float width, float height, float u1, float v1, float u2, float v2, int color, int light) {
		drawTexture(matrices, renderTypeBuffer, texture, x, y, 0, x + width, y + height, 0, u1, v1, u2, v2, color, light);
	}

	static void drawTexture(MatrixStack matrices, IRenderTypeBuffer renderTypeBuffer, String texture, float x1, float y1, float z1, float x2, float y2, float z2, float u1, float v1, float u2, float v2, int color, int light) {
		final Matrix4f matrix4f = matrices.getLast().getMatrix();
		final Matrix3f matrix3f = matrices.getLast().getNormal();
		final IVertexBuilder vertex = renderTypeBuffer.getBuffer(light == ModelTrainBase.MAX_LIGHT ? MoreRenderTypes.getInterior(new ResourceLocation(texture)) :
				MoreRenderTypes.getExterior(new ResourceLocation(texture)));
		final int a = (color >> 24) & 0xFF;
		final int r = (color >> 16) & 0xFF;
		final int g = (color >> 8) & 0xFF;
		final int b = color & 0xFF;
		vertex.pos(matrix4f, x1, y2, z1).color(r, g, b, a).tex(u1, v2).overlay(OverlayTexture.NO_OVERLAY).lightmap(light).normal(matrix3f, 1, 0, 1).endVertex();
		vertex.pos(matrix4f, x2, y2, z2).color(r, g, b, a).tex(u2, v2).overlay(OverlayTexture.NO_OVERLAY).lightmap(light).normal(matrix3f, 1, 0, 1).endVertex();
		vertex.pos(matrix4f, x2, y1, z2).color(r, g, b, a).tex(u2, v1).overlay(OverlayTexture.NO_OVERLAY).lightmap(light).normal(matrix3f, 1, 0, 1).endVertex();
		vertex.pos(matrix4f, x1, y1, z1).color(r, g, b, a).tex(u1, v1).overlay(OverlayTexture.NO_OVERLAY).lightmap(light).normal(matrix3f, 1, 0, 1).endVertex();
	}

	static void drawRectangleBright(MatrixStack matrices, IRenderTypeBuffer renderTypeBuffer, float x1, float y1, float z1, float x2, float y2, float z2, int color) {
		final Matrix4f matrix4f = matrices.getLast().getMatrix();
		final IVertexBuilder vertex = renderTypeBuffer.getBuffer(MoreRenderTypes.getRectangleBright());
		final int a = (color >> 24) & 0xFF;
		final int r = (color >> 16) & 0xFF;
		final int g = (color >> 8) & 0xFF;
		final int b = color & 0xFF;
		vertex.pos(matrix4f, x1, y2, z1).color(r, g, b, a).endVertex();
		vertex.pos(matrix4f, x2, y2, z2).color(r, g, b, a).endVertex();
		vertex.pos(matrix4f, x2, y1, z2).color(r, g, b, a).endVertex();
		vertex.pos(matrix4f, x1, y1, z1).color(r, g, b, a).endVertex();
	}

	@FunctionalInterface
	interface DrawingCallback {
		void drawingCallback(float x1, float y1, float x2, float y2);
	}

}
