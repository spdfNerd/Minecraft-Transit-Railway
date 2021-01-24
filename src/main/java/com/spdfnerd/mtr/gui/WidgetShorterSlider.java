package com.spdfnerd.mtr.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.AbstractSlider;
import net.minecraft.util.text.StringTextComponent;

public class WidgetShorterSlider extends AbstractSlider implements IGui {

	private final int maxValue;
	private final OnSlide onSlide;
	private final SetMessage setMessage;

	private static final int SLIDER_WIDTH = 6;

	public WidgetShorterSlider(int x, int width, int maxValue, OnSlide onSlide, SetMessage setMessage) {
		super(x, 0, width, 0, new StringTextComponent(""), 0);
		this.maxValue = maxValue;
		this.onSlide = onSlide;
		this.setMessage = setMessage;
	}

	@Override
	public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta) {
		final Minecraft client = Minecraft.getInstance();
		client.getTextureManager().bindTexture(WIDGETS_LOCATION);

		blit(matrices, x, y, 0, 46, width / 2, height / 2);
		blit(matrices, x, y + height / 2, 0, 66 - height / 2, width / 2, height / 2);
		blit(matrices, x + width / 2, y, 200 - width / 2, 46, width / 2, height / 2);
		blit(matrices, x + width / 2, y + height / 2, 200 - width / 2, 66 - height / 2, width / 2, height / 2);

		final int v = isHovered() ? 86 : 66;
		final int xOffset = (width - SLIDER_WIDTH) * getIntValue() / maxValue;
		blit(matrices, x + xOffset, y, 0, v, SLIDER_WIDTH / 2, height / 2);
		blit(matrices, x + xOffset, y + height / 2, 0, v + 20 - height / 2, SLIDER_WIDTH / 2, height / 2);
		blit(matrices, x + xOffset + SLIDER_WIDTH / 2, y, 200 - SLIDER_WIDTH / 2, v, SLIDER_WIDTH / 2, height / 2);
		blit(matrices, x + xOffset + SLIDER_WIDTH / 2, y + height / 2, 200 - SLIDER_WIDTH / 2, v + 20 - height / 2, SLIDER_WIDTH / 2, height / 2);

		drawString(matrices, client.fontRenderer, getMessage().getString(), x + width + TEXT_PADDING, y + (height - TEXT_HEIGHT) / 2, ARGB_WHITE);
	}

	@Override
	protected void func_230979_b_() {
		setMessage(new StringTextComponent(setMessage.setMessage(getIntValue())));
	}

	@Override
	protected void func_230972_a_() {
		onSlide.onSlide(getIntValue());
	}

	public void setValue(int valueInt) {
		sliderValue = (double) valueInt / maxValue;
		func_230979_b_();
	}

	public void setHeight(int height) {
		this.height = height;
	}

	private int getIntValue() {
		return (int) Math.round(sliderValue * maxValue);
	}

	@FunctionalInterface
	public interface OnSlide {
		void onSlide(int value);
	}

	@FunctionalInterface
	public interface SetMessage {
		String setMessage(int value);
	}

}
