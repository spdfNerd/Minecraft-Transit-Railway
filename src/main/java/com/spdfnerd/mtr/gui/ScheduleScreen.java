package com.spdfnerd.mtr.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.spdfnerd.mtr.data.Platform;
import com.spdfnerd.mtr.data.RailwayData;
import com.spdfnerd.mtr.data.Train;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Triple;

import java.util.Comparator;
import java.util.List;

public class ScheduleScreen extends Screen implements IGui {

	private final List<Triple<Integer, Long, Train.TrainType>> schedule;
	private final int maxRouteWidth, maxTrainTypeWidth;

	private static final int ARGB_GOLD = 0xFFFFAA00;
	private static final int ARGB_GREEN = 0xFF55FF55;

	private static BlockPos platformPos;

	public ScheduleScreen(BlockPos platformPos) {
		super(new StringTextComponent(""));
		final Platform platform = RailwayData.getPlatformByPos(ClientData.platforms, platformPos);
		ScheduleScreen.platformPos = platformPos;
		schedule = platform.getSchedule();
		font = Minecraft.getInstance().fontRenderer;
		maxRouteWidth = platform.shuffleRoutes ? 0 : schedule.stream().map(scheduleEntry -> font.getStringWidth(RailwayData.getDataById(ClientData.routes,
				scheduleEntry.getMiddle()).name)).max(Comparator.comparingInt(value -> value)).orElse(0);
		maxTrainTypeWidth = platform.shuffleTrains ? 0 :
				schedule.stream().map(scheduleEntry -> font.getStringWidth(scheduleEntry.getRight().getName())).max(Comparator.comparingInt(value -> value)).orElse(0);
	}

	@Override
	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		renderBackground(matrixStack);
		final int time = minecraft != null && minecraft.world != null ?
				(int) ((minecraft.world.getDayTime() + 6000) % (Platform.HOURS_IN_DAY * Platform.TICKS_PER_HOUR)) : 0;
		final int timeWidth = font.getStringWidth("00:00") + TEXT_PADDING;
		drawString(matrixStack, font, new TranslationTextComponent("gui.mtr.train_schedule"), TEXT_PADDING, TEXT_PADDING, ARGB_LIGHT_GRAY);
		drawString(matrixStack, font, getTimeString(time), width - timeWidth, TEXT_PADDING, ARGB_LIGHT_GRAY);

		final int rows = (height - SQUARE_SIZE - TEXT_PADDING) / LINE_HEIGHT;
		final int columns = (int) Math.ceil((float) schedule.size() / rows);

		if (columns > 0) {
			final int columnWidth = (width - TEXT_PADDING) / columns;
			final int remainingWidth = columnWidth - timeWidth;
			int routeWidth = maxRouteWidth + TEXT_PADDING;
			int trainTypeWidth = maxTrainTypeWidth + TEXT_PADDING;
			if (routeWidth + trainTypeWidth > remainingWidth) {
				if (routeWidth <= remainingWidth / 2) {
					trainTypeWidth = remainingWidth - routeWidth;
				} else if (trainTypeWidth <= remainingWidth / 2) {
					routeWidth = remainingWidth - trainTypeWidth;
				} else {
					routeWidth = remainingWidth / 2;
					trainTypeWidth = remainingWidth / 2;
				}
			}

			for (int column = 0; column < columns; column++) {
				for (int row = 0; row < rows; row++) {
					final int index = row + rows * column;
					if (index < schedule.size()) {
						final Triple<Integer, Long, Train.TrainType> scheduleEntry = schedule.get(index);
						final int timeDifference = time - scheduleEntry.getLeft();
						final int textColor = timeDifference >= -50 && timeDifference < 0 ? ARGB_GOLD : timeDifference >= 0 && timeDifference < 50 ?
								ARGB_GREEN : ARGB_WHITE;
						drawString(matrixStack, font,getTimeString(scheduleEntry.getLeft()), TEXT_PADDING + column * columnWidth,
								SQUARE_SIZE + row * LINE_HEIGHT, textColor);
						drawString(matrixStack, font, cutToWidth(getRouteString(scheduleEntry.getMiddle()), routeWidth - TEXT_PADDING),
								TEXT_PADDING + column * columnWidth + timeWidth, SQUARE_SIZE + row * LINE_HEIGHT, ARGB_LIGHT_GRAY);
						drawString(matrixStack, font, cutToWidth(getTrainTypeString(scheduleEntry.getRight()), trainTypeWidth - TEXT_PADDING),
								TEXT_PADDING + column * columnWidth + timeWidth + routeWidth, SQUARE_SIZE + row * LINE_HEIGHT, ARGB_LIGHT_GRAY);
					}
				}
			}
		}
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}

	private String getTimeString(int time) {
		final String hourString = StringUtils.leftPad(String.valueOf(time / Platform.TICKS_PER_HOUR), 2, "0");
		final String minuteString = StringUtils.leftPad(String.valueOf((int) (0.06 * (time % Platform.TICKS_PER_HOUR))), 2, "0");
		return hourString + ":" + minuteString;
	}

	private String getRouteString(long routeId) {
		return routeId < 0 ? "" : RailwayData.getDataById(ClientData.routes, routeId).name;
	}

	private String getTrainTypeString(Train.TrainType trainType) {
		return trainType == null ? "" : trainType.getName();
	}

	private String cutToWidth(String string, int maxWidth) {
		String cutString = string;
		while (font.getStringWidth(cutString) > maxWidth) {
			cutString = cutString.substring(0, cutString.length() - 1);
		}
		return cutString;
	}

	public static void open() {
		Minecraft.getInstance().displayGuiScreen(new ScheduleScreen(platformPos));
	}

}
