package com.spdfnerd.mtr.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.CheckboxButton;
import net.minecraft.util.text.ITextComponent;

public class WidgetBetterCheckbox extends CheckboxButton implements IGui {

	private final OnClick onClick;

	public WidgetBetterCheckbox(int x, int y, int width, int height, ITextComponent text, OnClick onClick) {
		super(x, y, width, height, text, false, false);
		this.onClick = onClick;
	}

	@Override
	public void onPress() {
		super.onPress();
		onClick.onClick(isChecked());
	}

	@Override
	public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta) {
		super.renderButton(matrices, mouseX, mouseY, delta);
		drawString(matrices, Minecraft.getInstance().fontRenderer, getMessage(), x + 24, y + (height - 8) / 2, ARGB_WHITE);
	}

	public void setChecked(boolean checked) {
		if (checked != isChecked()) {
			super.onPress();
		}
	}

	@FunctionalInterface
	public interface OnClick {
		void onClick(boolean checked);
	}

}
