package com.spdfnerd.mtr.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.spdfnerd.mtr.gui.IGui;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.vector.Vector3f;

public class ModelSP1900 extends ModelTrainBase {

	private final ModelRenderer window;
	private final ModelRenderer upper_wall_r1;
	private final ModelRenderer seat;
	private final ModelRenderer seat_back_r1;
	private final ModelRenderer window_exterior;
	private final ModelRenderer upper_wall_r2;
	private final ModelRenderer side_panel;
	private final ModelRenderer handrail;
	private final ModelRenderer side_handrail_left_7_r1;
	private final ModelRenderer side_handrail_left_6_r1;
	private final ModelRenderer side_handrail_left_4_r1;
	private final ModelRenderer side_handrail_left_3_r1;
	private final ModelRenderer side_handrail_left_2_r1;
	private final ModelRenderer door;
	private final ModelRenderer door_left;
	private final ModelRenderer door_left_top_r1;
	private final ModelRenderer door_right;
	private final ModelRenderer door_right_top_r1;
	private final ModelRenderer door_exterior;
	private final ModelRenderer door_left_exterior;
	private final ModelRenderer door_left_top_r2;
	private final ModelRenderer door_right_exterior;
	private final ModelRenderer door_right_top_r2;
	private final ModelRenderer end;
	private final ModelRenderer upper_wall_2_r1;
	private final ModelRenderer upper_wall_1_r1;
	private final ModelRenderer seat_end_1;
	private final ModelRenderer seat_back_r2;
	private final ModelRenderer seat_end_2;
	private final ModelRenderer seat_bottom_r1;
	private final ModelRenderer seat_back_r3;
	private final ModelRenderer end_exterior;
	private final ModelRenderer upper_wall_2_r2;
	private final ModelRenderer upper_wall_1_r2;
	private final ModelRenderer floor_1_r1;
	private final ModelRenderer roof;
	private final ModelRenderer inner_roof_6_r1;
	private final ModelRenderer inner_roof_5_r1;
	private final ModelRenderer inner_roof_4_r1;
	private final ModelRenderer inner_roof_3_r1;
	private final ModelRenderer inner_roof_2_r1;
	private final ModelRenderer roof_exterior;
	private final ModelRenderer outer_roof_4_r1;
	private final ModelRenderer outer_roof_3_r1;
	private final ModelRenderer outer_roof_2_r1;
	private final ModelRenderer outer_roof_1_r1;
	private final ModelRenderer roof_light;
	private final ModelRenderer light_3_r1;
	private final ModelRenderer light_1_r1;
	private final ModelRenderer roof_end;
	private final ModelRenderer inner_roof_1;
	private final ModelRenderer inner_roof_6_r2;
	private final ModelRenderer inner_roof_5_r2;
	private final ModelRenderer inner_roof_4_r2;
	private final ModelRenderer inner_roof_3_r2;
	private final ModelRenderer inner_roof_2_r2;
	private final ModelRenderer inner_roof_2;
	private final ModelRenderer inner_roof_6_r3;
	private final ModelRenderer inner_roof_5_r3;
	private final ModelRenderer inner_roof_4_r3;
	private final ModelRenderer inner_roof_3_r3;
	private final ModelRenderer inner_roof_2_r3;
	private final ModelRenderer roof_end_exterior;
	private final ModelRenderer vent_2_r1;
	private final ModelRenderer vent_1_r1;
	private final ModelRenderer outer_roof_1;
	private final ModelRenderer outer_roof_3_r2;
	private final ModelRenderer outer_roof_2_r2;
	private final ModelRenderer outer_roof_1_r2;
	private final ModelRenderer outer_roof_2;
	private final ModelRenderer outer_roof_3_r3;
	private final ModelRenderer outer_roof_2_r3;
	private final ModelRenderer outer_roof_1_r3;
	private final ModelRenderer roof_end_light;
	private final ModelRenderer light_6_r1;
	private final ModelRenderer light_4_r1;
	private final ModelRenderer light_3_r2;
	private final ModelRenderer light_1_r2;
	private final ModelRenderer top_handrail;
	private final ModelRenderer top_handrail_bottom_right_r1;
	private final ModelRenderer top_handrail_right_3_r1;
	private final ModelRenderer top_handrail_right_2_r1;
	private final ModelRenderer top_handrail_bottom_left_r1;
	private final ModelRenderer top_handrail_left_3_r1;
	private final ModelRenderer top_handrail_left_2_r1;
	private final ModelRenderer handrail_strap;
	private final ModelRenderer tv_pole;
	private final ModelRenderer pole_5_r1;
	private final ModelRenderer pole_4_r1;
	private final ModelRenderer pole_2_r1;
	private final ModelRenderer pole_1_r1;
	private final ModelRenderer tv_right_r1;
	private final ModelRenderer tv_left_r1;
	private final ModelRenderer head;
	private final ModelRenderer upper_wall_2_r3;
	private final ModelRenderer upper_wall_1_r3;
	private final ModelRenderer seat_head_1;
	private final ModelRenderer seat_back_top_r1;
	private final ModelRenderer seat_head_2;
	private final ModelRenderer seat_bottom_r2;
	private final ModelRenderer seat_back_top_r2;
	private final ModelRenderer head_exterior;
	private final ModelRenderer driver_door_top_2_r1;
	private final ModelRenderer driver_door_top_1_r1;
	private final ModelRenderer upper_wall_2_r4;
	private final ModelRenderer upper_wall_1_r4;
	private final ModelRenderer floor_2_r1;
	private final ModelRenderer front;
	private final ModelRenderer bottom_corner_2_r1;
	private final ModelRenderer bottom_corner_1_r1;
	private final ModelRenderer roof_corner_2_r1;
	private final ModelRenderer roof_corner_1_r1;
	private final ModelRenderer roof_middle_corner_2_r1;
	private final ModelRenderer roof_middle_corner_1_r1;
	private final ModelRenderer roof_side_2_r1;
	private final ModelRenderer roof_side_1_r1;
	private final ModelRenderer top_side_2_r1;
	private final ModelRenderer top_side_1_r1;
	private final ModelRenderer bottom_side_2_r1;
	private final ModelRenderer bottom_side_1_r1;
	private final ModelRenderer front_side_2_r1;
	private final ModelRenderer front_side_1_r1;
	private final ModelRenderer front_bottom_r1;
	private final ModelRenderer bottom_r1;
	private final ModelRenderer front_roof_r1;
	private final ModelRenderer front_middle_r1;
	private final ModelRenderer top_handrail_head;
	private final ModelRenderer top_handrail_bottom_right_r2;
	private final ModelRenderer top_handrail_right_3_r2;
	private final ModelRenderer top_handrail_right_2_r2;
	private final ModelRenderer top_handrail_bottom_left_r2;
	private final ModelRenderer top_handrail_left_3_r2;
	private final ModelRenderer top_handrail_left_2_r2;
	private final ModelRenderer handrail_strap_head;
	private final ModelRenderer headlights;
	private final ModelRenderer headlight_4_r1;
	private final ModelRenderer headlight_3_r1;
	private final ModelRenderer headlight_2_r1;
	private final ModelRenderer tail_lights;
	private final ModelRenderer tail_light_4_r1;
	private final ModelRenderer tail_light_3_r1;
	private final ModelRenderer tail_light_2_r1;
	private final ModelRenderer bb_main;

	public ModelSP1900() {
		textureWidth = 416;
		textureHeight = 416;
		window = new ModelRenderer(this);
		window.setRotationPoint(0.0F, 24.0F, 0.0F);
		window.setTextureOffset(58, 115).addBox(-20.0F, 0.0F, -16.0F, 20.0F, 1.0F, 32.0F, 0.0F, false);
		window.setTextureOffset(326, 119).addBox(-20.0F, -14.0F, -18.0F, 2.0F, 14.0F, 36.0F, 0.0F, false);

		upper_wall_r1 = new ModelRenderer(this);
		upper_wall_r1.setRotationPoint(-20.0F, -14.0F, 0.0F);
		window.addChild(upper_wall_r1);
		setRotationAngle(upper_wall_r1, 0.0F, 0.0F, 0.1107F);
		upper_wall_r1.setTextureOffset(134, 311).addBox(0.0F, -19.0F, -18.0F, 2.0F, 19.0F, 36.0F, 0.0F, false);

		seat = new ModelRenderer(this);
		seat.setRotationPoint(-9.0F, 0.0F, 0.0F);
		window.addChild(seat);
		seat.setTextureOffset(228, 192).addBox(-9.0F, -6.0F, -16.0F, 7.0F, 1.0F, 32.0F, 0.0F, false);

		seat_back_r1 = new ModelRenderer(this);
		seat_back_r1.setRotationPoint(-9.0F, -6.5F, 0.0F);
		seat.addChild(seat_back_r1);
		setRotationAngle(seat_back_r1, 0.0F, 0.0F, -0.0873F);
		seat_back_r1.setTextureOffset(80, 334).addBox(0.0F, -8.0F, -16.0F, 1.0F, 8.0F, 32.0F, 0.0F, false);

		window_exterior = new ModelRenderer(this);
		window_exterior.setRotationPoint(0.0F, 24.0F, 0.0F);
		window_exterior.setTextureOffset(324, 343).addBox(-21.0F, 0.0F, -16.0F, 1.0F, 7.0F, 32.0F, 0.0F, false);
		window_exterior.setTextureOffset(128, 46).addBox(-20.0F, -14.0F, -18.0F, 0.0F, 14.0F, 36.0F, 0.0F, false);

		upper_wall_r2 = new ModelRenderer(this);
		upper_wall_r2.setRotationPoint(-20.0F, -14.0F, 0.0F);
		window_exterior.addChild(upper_wall_r2);
		setRotationAngle(upper_wall_r2, 0.0F, 0.0F, 0.1107F);
		upper_wall_r2.setTextureOffset(58, 112).addBox(0.0F, -19.0F, -18.0F, 0.0F, 19.0F, 36.0F, 0.0F, false);

		side_panel = new ModelRenderer(this);
		side_panel.setRotationPoint(0.0F, 24.0F, 0.0F);
		side_panel.setTextureOffset(38, 188).addBox(-18.0F, -34.0F, 0.0F, 7.0F, 30.0F, 0.0F, 0.0F, false);

		handrail = new ModelRenderer(this);
		handrail.setRotationPoint(0.0F, 0.0F, 0.0F);
		side_panel.addChild(handrail);
		handrail.setTextureOffset(0, 0).addBox(-12.0F, -16.0F, 0.0F, 0.0F, 10.0F, 0.0F, 0.2F, false);
		handrail.setTextureOffset(0, 0).addBox(-10.3697F, -26.2455F, 0.0F, 0.0F, 3.0F, 0.0F, 0.2F, false);

		side_handrail_left_7_r1 = new ModelRenderer(this);
		side_handrail_left_7_r1.setRotationPoint(-11.8689F, -31.7477F, 0.0F);
		handrail.addChild(side_handrail_left_7_r1);
		setRotationAngle(side_handrail_left_7_r1, 0.0F, 0.0F, -0.3491F);
		side_handrail_left_7_r1.setTextureOffset(0, 0).addBox(0.0F, -3.0F, 0.0F, 0.0F, 6.0F, 0.0F, 0.2F, false);

		side_handrail_left_6_r1 = new ModelRenderer(this);
		side_handrail_left_6_r1.setRotationPoint(-10.5751F, -27.5926F, 0.0F);
		handrail.addChild(side_handrail_left_6_r1);
		setRotationAngle(side_handrail_left_6_r1, 0.0F, 0.0F, -0.1745F);
		side_handrail_left_6_r1.setTextureOffset(0, 0).addBox(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 0.0F, 0.2F, false);

		side_handrail_left_4_r1 = new ModelRenderer(this);
		side_handrail_left_4_r1.setRotationPoint(-10.5751F, -21.8985F, 0.0F);
		handrail.addChild(side_handrail_left_4_r1);
		setRotationAngle(side_handrail_left_4_r1, 0.0F, 0.0F, 0.1745F);
		side_handrail_left_4_r1.setTextureOffset(0, 0).addBox(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 0.0F, 0.2F, false);

		side_handrail_left_3_r1 = new ModelRenderer(this);
		side_handrail_left_3_r1.setRotationPoint(-11.1849F, -19.6228F, 0.0F);
		handrail.addChild(side_handrail_left_3_r1);
		setRotationAngle(side_handrail_left_3_r1, 0.0F, 0.0F, 0.3491F);
		side_handrail_left_3_r1.setTextureOffset(0, 0).addBox(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 0.0F, 0.2F, false);

		side_handrail_left_2_r1 = new ModelRenderer(this);
		side_handrail_left_2_r1.setRotationPoint(-11.7947F, -17.347F, 0.0F);
		handrail.addChild(side_handrail_left_2_r1);
		setRotationAngle(side_handrail_left_2_r1, 0.0F, 0.0F, 0.1745F);
		side_handrail_left_2_r1.setTextureOffset(0, 0).addBox(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 0.0F, 0.2F, false);

		door = new ModelRenderer(this);
		door.setRotationPoint(0.0F, 24.0F, 0.0F);
		door.setTextureOffset(128, 49).addBox(-20.0F, 0.0F, -16.0F, 20.0F, 1.0F, 32.0F, 0.0F, false);

		door_left = new ModelRenderer(this);
		door_left.setRotationPoint(0.0F, 0.0F, 0.0F);
		door.addChild(door_left);
		door_left.setTextureOffset(224, 251).addBox(-20.8F, -14.0F, 0.0F, 1.0F, 14.0F, 16.0F, 0.0F, false);

		door_left_top_r1 = new ModelRenderer(this);
		door_left_top_r1.setRotationPoint(-20.8F, -14.0F, 0.0F);
		door_left.addChild(door_left_top_r1);
		setRotationAngle(door_left_top_r1, 0.0F, 0.0F, 0.1107F);
		door_left_top_r1.setTextureOffset(236, 122).addBox(0.0F, -19.0F, 0.0F, 1.0F, 19.0F, 16.0F, 0.0F, false);

		door_right = new ModelRenderer(this);
		door_right.setRotationPoint(0.0F, 0.0F, 0.0F);
		door.addChild(door_right);
		door_right.setTextureOffset(156, 251).addBox(-20.8F, -14.0F, -16.0F, 1.0F, 14.0F, 16.0F, 0.0F, false);

		door_right_top_r1 = new ModelRenderer(this);
		door_right_top_r1.setRotationPoint(-20.8F, -14.0F, 0.0F);
		door_right.addChild(door_right_top_r1);
		setRotationAngle(door_right_top_r1, 0.0F, 0.0F, 0.1107F);
		door_right_top_r1.setTextureOffset(116, 251).addBox(0.0F, -19.0F, -16.0F, 1.0F, 19.0F, 16.0F, 0.0F, false);

		door_exterior = new ModelRenderer(this);
		door_exterior.setRotationPoint(0.0F, 24.0F, 0.0F);
		door_exterior.setTextureOffset(178, 334).addBox(-21.0F, 0.0F, -16.0F, 1.0F, 7.0F, 32.0F, 0.0F, false);

		door_left_exterior = new ModelRenderer(this);
		door_left_exterior.setRotationPoint(0.0F, 0.0F, 0.0F);
		door_exterior.addChild(door_left_exterior);
		door_left_exterior.setTextureOffset(128, 33).addBox(-20.8F, -14.0F, 0.0F, 0.0F, 14.0F, 16.0F, 0.0F, false);

		door_left_top_r2 = new ModelRenderer(this);
		door_left_top_r2.setRotationPoint(-20.8F, -14.0F, 0.0F);
		door_left_exterior.addChild(door_left_top_r2);
		setRotationAngle(door_left_top_r2, 0.0F, 0.0F, 0.1107F);
		door_left_top_r2.setTextureOffset(130, 99).addBox(0.0F, -19.0F, 0.0F, 0.0F, 19.0F, 16.0F, 0.0F, false);

		door_right_exterior = new ModelRenderer(this);
		door_right_exterior.setRotationPoint(0.0F, 0.0F, 0.0F);
		door_exterior.addChild(door_right_exterior);
		door_right_exterior.setTextureOffset(96, 33).addBox(-20.8F, -14.0F, -16.0F, 0.0F, 14.0F, 16.0F, 0.0F, false);

		door_right_top_r2 = new ModelRenderer(this);
		door_right_top_r2.setRotationPoint(-20.8F, -14.0F, 0.0F);
		door_right_exterior.addChild(door_right_top_r2);
		setRotationAngle(door_right_top_r2, 0.0F, 0.0F, 0.1107F);
		door_right_top_r2.setTextureOffset(58, 99).addBox(0.0F, -19.0F, -16.0F, 0.0F, 19.0F, 16.0F, 0.0F, false);

		end = new ModelRenderer(this);
		end.setRotationPoint(0.0F, 24.0F, 0.0F);
		end.setTextureOffset(96, 0).addBox(-20.0F, 0.0F, -32.0F, 40.0F, 1.0F, 48.0F, 0.0F, false);
		end.setTextureOffset(58, 251).addBox(18.0F, -14.0F, -36.0F, 2.0F, 14.0F, 54.0F, 0.0F, true);
		end.setTextureOffset(228, 124).addBox(-20.0F, -14.0F, -36.0F, 2.0F, 14.0F, 54.0F, 0.0F, false);
		end.setTextureOffset(0, 188).addBox(11.0F, -33.0F, -36.0F, 7.0F, 33.0F, 12.0F, 0.0F, false);
		end.setTextureOffset(0, 115).addBox(-18.0F, -33.0F, -36.0F, 7.0F, 33.0F, 12.0F, 0.0F, false);
		end.setTextureOffset(320, 320).addBox(-18.0F, -44.0F, -36.0F, 36.0F, 11.0F, 12.0F, 0.0F, false);

		upper_wall_2_r1 = new ModelRenderer(this);
		upper_wall_2_r1.setRotationPoint(-20.0F, -14.0F, 0.0F);
		end.addChild(upper_wall_2_r1);
		setRotationAngle(upper_wall_2_r1, 0.0F, 0.0F, 0.1107F);
		upper_wall_2_r1.setTextureOffset(170, 178).addBox(0.0F, -19.0F, -36.0F, 2.0F, 19.0F, 54.0F, 0.0F, false);

		upper_wall_1_r1 = new ModelRenderer(this);
		upper_wall_1_r1.setRotationPoint(20.0F, -14.0F, 0.0F);
		end.addChild(upper_wall_1_r1);
		setRotationAngle(upper_wall_1_r1, 0.0F, 0.0F, -0.1107F);
		upper_wall_1_r1.setTextureOffset(194, 49).addBox(-2.0F, -19.0F, -36.0F, 2.0F, 19.0F, 54.0F, 0.0F, true);

		seat_end_1 = new ModelRenderer(this);
		seat_end_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		end.addChild(seat_end_1);
		seat_end_1.setTextureOffset(116, 178).addBox(11.0F, -6.0F, -24.0F, 7.0F, 1.0F, 40.0F, 0.0F, false);

		seat_back_r2 = new ModelRenderer(this);
		seat_back_r2.setRotationPoint(18.0F, -6.5F, 0.0F);
		seat_end_1.addChild(seat_back_r2);
		setRotationAngle(seat_back_r2, 0.0F, 0.0F, 0.0873F);
		seat_back_r2.setTextureOffset(194, 122).addBox(-1.0F, -8.0F, -24.0F, 1.0F, 8.0F, 40.0F, 0.0F, false);

		seat_end_2 = new ModelRenderer(this);
		seat_end_2.setRotationPoint(0.0F, 0.0F, 0.0F);
		end.addChild(seat_end_2);


		seat_bottom_r1 = new ModelRenderer(this);
		seat_bottom_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		seat_end_2.addChild(seat_bottom_r1);
		setRotationAngle(seat_bottom_r1, 0.0F, 3.1416F, 0.0F);
		seat_bottom_r1.setTextureOffset(116, 178).addBox(11.0F, -6.0F, -16.0F, 7.0F, 1.0F, 40.0F, 0.0F, false);

		seat_back_r3 = new ModelRenderer(this);
		seat_back_r3.setRotationPoint(-18.0F, -6.5F, 0.0F);
		seat_end_2.addChild(seat_back_r3);
		setRotationAngle(seat_back_r3, 0.0F, 3.1416F, -0.0873F);
		seat_back_r3.setTextureOffset(194, 122).addBox(-1.0F, -8.0F, -16.0F, 1.0F, 8.0F, 40.0F, 0.0F, false);

		end_exterior = new ModelRenderer(this);
		end_exterior.setRotationPoint(0.0F, 24.0F, 0.0F);
		end_exterior.setTextureOffset(0, 271).addBox(-21.0F, 0.0F, -32.0F, 1.0F, 7.0F, 48.0F, 0.0F, false);
		end_exterior.setTextureOffset(228, 228).addBox(18.0F, -14.0F, -36.0F, 2.0F, 14.0F, 54.0F, 0.0F, true);
		end_exterior.setTextureOffset(0, 197).addBox(-20.0F, -14.0F, -36.0F, 2.0F, 14.0F, 54.0F, 0.0F, false);
		end_exterior.setTextureOffset(162, 115).addBox(11.0F, -33.0F, -36.0F, 7.0F, 33.0F, 0.0F, 0.0F, false);
		end_exterior.setTextureOffset(38, 115).addBox(-18.0F, -33.0F, -36.0F, 7.0F, 33.0F, 0.0F, 0.0F, false);
		end_exterior.setTextureOffset(116, 219).addBox(-18.0F, -44.0F, -36.0F, 36.0F, 11.0F, 0.0F, 0.0F, false);

		upper_wall_2_r2 = new ModelRenderer(this);
		upper_wall_2_r2.setRotationPoint(-20.0F, -14.0F, 0.0F);
		end_exterior.addChild(upper_wall_2_r2);
		setRotationAngle(upper_wall_2_r2, 0.0F, 0.0F, 0.1107F);
		upper_wall_2_r2.setTextureOffset(0, 115).addBox(0.0F, -19.0F, -36.0F, 2.0F, 19.0F, 54.0F, 0.0F, false);

		upper_wall_1_r2 = new ModelRenderer(this);
		upper_wall_1_r2.setRotationPoint(20.0F, -14.0F, 0.0F);
		end_exterior.addChild(upper_wall_1_r2);
		setRotationAngle(upper_wall_1_r2, 0.0F, 0.0F, -0.1107F);
		upper_wall_1_r2.setTextureOffset(58, 178).addBox(-2.0F, -19.0F, -36.0F, 2.0F, 19.0F, 54.0F, 0.0F, true);

		floor_1_r1 = new ModelRenderer(this);
		floor_1_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		end_exterior.addChild(floor_1_r1);
		setRotationAngle(floor_1_r1, 0.0F, 3.1416F, 0.0F);
		floor_1_r1.setTextureOffset(0, 271).addBox(-21.0F, 0.0F, -16.0F, 1.0F, 7.0F, 48.0F, 0.0F, false);

		roof = new ModelRenderer(this);
		roof.setRotationPoint(0.0F, 24.0F, 0.0F);
		roof.setTextureOffset(70, 0).addBox(-18.0F, -32.0F, -16.0F, 3.0F, 0.0F, 32.0F, 0.0F, false);
		roof.setTextureOffset(8, 0).addBox(-4.0F, -36.0F, -16.0F, 4.0F, 0.0F, 32.0F, 0.0F, false);

		inner_roof_6_r1 = new ModelRenderer(this);
		inner_roof_6_r1.setRotationPoint(-4.19F, -35.8985F, 0.0F);
		roof.addChild(inner_roof_6_r1);
		setRotationAngle(inner_roof_6_r1, 0.0F, 0.0F, -0.2618F);
		inner_roof_6_r1.setTextureOffset(64, 0).addBox(-2.5F, 0.0F, -16.0F, 3.0F, 0.0F, 32.0F, 0.0F, false);

		inner_roof_5_r1 = new ModelRenderer(this);
		inner_roof_5_r1.setRotationPoint(-11.303F, -34.9926F, 0.0F);
		roof.addChild(inner_roof_5_r1);
		setRotationAngle(inner_roof_5_r1, 0.0F, 0.0F, -0.2618F);
		inner_roof_5_r1.setTextureOffset(8, 66).addBox(-1.0F, 0.0F, -16.0F, 2.0F, 0.0F, 32.0F, 0.0F, false);

		inner_roof_4_r1 = new ModelRenderer(this);
		inner_roof_4_r1.setRotationPoint(-12.2684F, -34.7329F, 0.0F);
		roof.addChild(inner_roof_4_r1);
		setRotationAngle(inner_roof_4_r1, 0.0F, 0.0F, -0.5236F);
		inner_roof_4_r1.setTextureOffset(78, 0).addBox(-1.0F, 0.0F, -16.0F, 1.0F, 0.0F, 32.0F, 0.0F, false);

		inner_roof_3_r1 = new ModelRenderer(this);
		inner_roof_3_r1.setRotationPoint(-13.1345F, -34.2329F, 0.0F);
		roof.addChild(inner_roof_3_r1);
		setRotationAngle(inner_roof_3_r1, 0.0F, 0.0F, -1.0472F);
		inner_roof_3_r1.setTextureOffset(12, 66).addBox(-2.0F, 0.0F, -16.0F, 2.0F, 0.0F, 32.0F, 0.0F, false);

		inner_roof_2_r1 = new ModelRenderer(this);
		inner_roof_2_r1.setRotationPoint(-15.0F, -32.0F, 0.0F);
		roof.addChild(inner_roof_2_r1);
		setRotationAngle(inner_roof_2_r1, 0.0F, 0.0F, -0.5236F);
		inner_roof_2_r1.setTextureOffset(80, 0).addBox(0.0F, 0.0F, -16.0F, 1.0F, 0.0F, 32.0F, 0.0F, false);

		roof_exterior = new ModelRenderer(this);
		roof_exterior.setRotationPoint(0.0F, 24.0F, 0.0F);
		roof_exterior.setTextureOffset(98, 0).addBox(-6.0F, -44.0F, -16.0F, 6.0F, 0.0F, 32.0F, 0.0F, false);

		outer_roof_4_r1 = new ModelRenderer(this);
		outer_roof_4_r1.setRotationPoint(-9.4468F, -43.3922F, 0.0F);
		roof_exterior.addChild(outer_roof_4_r1);
		setRotationAngle(outer_roof_4_r1, 0.0F, 0.0F, -0.1745F);
		outer_roof_4_r1.setTextureOffset(82, 0).addBox(-4.5F, 0.0F, -16.0F, 8.0F, 0.0F, 32.0F, 0.0F, false);

		outer_roof_3_r1 = new ModelRenderer(this);
		outer_roof_3_r1.setRotationPoint(-15.1775F, -41.8608F, 0.0F);
		roof_exterior.addChild(outer_roof_3_r1);
		setRotationAngle(outer_roof_3_r1, 0.0F, 0.0F, -0.5236F);
		outer_roof_3_r1.setTextureOffset(20, 0).addBox(-1.5F, 0.0F, -16.0F, 3.0F, 0.0F, 32.0F, 0.0F, false);

		outer_roof_2_r1 = new ModelRenderer(this);
		outer_roof_2_r1.setRotationPoint(-16.9765F, -40.2448F, 0.0F);
		roof_exterior.addChild(outer_roof_2_r1);
		setRotationAngle(outer_roof_2_r1, 0.0F, 0.0F, -1.0472F);
		outer_roof_2_r1.setTextureOffset(26, 6).addBox(-1.0F, 0.0F, -16.0F, 2.0F, 0.0F, 32.0F, 0.0F, false);

		outer_roof_1_r1 = new ModelRenderer(this);
		outer_roof_1_r1.setRotationPoint(-19.0F, -32.0F, 0.0F);
		roof_exterior.addChild(outer_roof_1_r1);
		setRotationAngle(outer_roof_1_r1, 0.0F, 0.0F, 0.1107F);
		outer_roof_1_r1.setTextureOffset(344, 228).addBox(0.0F, -8.0F, -16.0F, 1.0F, 8.0F, 32.0F, 0.0F, false);

		roof_light = new ModelRenderer(this);
		roof_light.setRotationPoint(0.0F, 24.0F, 0.0F);
		roof_light.setTextureOffset(16, 8).addBox(-9.4711F, -34.7514F, -16.0F, 2.0F, 0.0F, 32.0F, 0.0F, false);

		light_3_r1 = new ModelRenderer(this);
		light_3_r1.setRotationPoint(-7.0376F, -35.0016F, 0.0F);
		roof_light.addChild(light_3_r1);
		setRotationAngle(light_3_r1, 0.0F, 0.0F, -0.5236F);
		light_3_r1.setTextureOffset(30, 22).addBox(-0.5F, 0.0F, -16.0F, 1.0F, 0.0F, 32.0F, 0.0F, false);

		light_1_r1 = new ModelRenderer(this);
		light_1_r1.setRotationPoint(-9.9041F, -35.0014F, 0.0F);
		roof_light.addChild(light_1_r1);
		setRotationAngle(light_1_r1, 0.0F, 0.0F, 0.5236F);
		light_1_r1.setTextureOffset(76, 0).addBox(-0.5F, 0.0F, -16.0F, 1.0F, 0.0F, 32.0F, 0.0F, false);

		roof_end = new ModelRenderer(this);
		roof_end.setRotationPoint(0.0F, 24.0F, 0.0F);


		inner_roof_1 = new ModelRenderer(this);
		inner_roof_1.setRotationPoint(-4.19F, -35.8985F, 0.0F);
		roof_end.addChild(inner_roof_1);
		inner_roof_1.setTextureOffset(62, 0).addBox(-13.81F, 3.8985F, -24.0F, 3.0F, 0.0F, 40.0F, 0.0F, false);
		inner_roof_1.setTextureOffset(0, 0).addBox(0.19F, -0.1015F, -24.0F, 4.0F, 0.0F, 40.0F, 0.0F, false);

		inner_roof_6_r2 = new ModelRenderer(this);
		inner_roof_6_r2.setRotationPoint(0.0F, 0.0F, 0.0F);
		inner_roof_1.addChild(inner_roof_6_r2);
		setRotationAngle(inner_roof_6_r2, 0.0F, 0.0F, -0.2618F);
		inner_roof_6_r2.setTextureOffset(56, 0).addBox(-2.5F, 0.0F, -24.0F, 3.0F, 0.0F, 40.0F, 0.0F, false);

		inner_roof_5_r2 = new ModelRenderer(this);
		inner_roof_5_r2.setRotationPoint(-7.1131F, 0.9059F, 0.0F);
		inner_roof_1.addChild(inner_roof_5_r2);
		setRotationAngle(inner_roof_5_r2, 0.0F, 0.0F, -0.2618F);
		inner_roof_5_r2.setTextureOffset(0, 66).addBox(-1.0F, 0.0F, -24.0F, 2.0F, 0.0F, 40.0F, 0.0F, false);

		inner_roof_4_r2 = new ModelRenderer(this);
		inner_roof_4_r2.setRotationPoint(-8.0785F, 1.1656F, 0.0F);
		inner_roof_1.addChild(inner_roof_4_r2);
		setRotationAngle(inner_roof_4_r2, 0.0F, 0.0F, -0.5236F);
		inner_roof_4_r2.setTextureOffset(70, 0).addBox(-1.0F, 0.0F, -24.0F, 1.0F, 0.0F, 40.0F, 0.0F, false);

		inner_roof_3_r2 = new ModelRenderer(this);
		inner_roof_3_r2.setRotationPoint(-8.9445F, 1.6656F, 0.0F);
		inner_roof_1.addChild(inner_roof_3_r2);
		setRotationAngle(inner_roof_3_r2, 0.0F, 0.0F, -1.0472F);
		inner_roof_3_r2.setTextureOffset(4, 66).addBox(-2.0F, 0.0F, -24.0F, 2.0F, 0.0F, 40.0F, 0.0F, false);

		inner_roof_2_r2 = new ModelRenderer(this);
		inner_roof_2_r2.setRotationPoint(-10.81F, 3.8985F, 0.0F);
		inner_roof_1.addChild(inner_roof_2_r2);
		setRotationAngle(inner_roof_2_r2, 0.0F, 0.0F, -0.5236F);
		inner_roof_2_r2.setTextureOffset(72, 0).addBox(0.0F, 0.0F, -24.0F, 1.0F, 0.0F, 40.0F, 0.0F, false);

		inner_roof_2 = new ModelRenderer(this);
		inner_roof_2.setRotationPoint(4.19F, -35.8985F, 0.0F);
		roof_end.addChild(inner_roof_2);
		inner_roof_2.setTextureOffset(62, 0).addBox(10.81F, 3.8985F, -24.0F, 3.0F, 0.0F, 40.0F, 0.0F, true);
		inner_roof_2.setTextureOffset(0, 0).addBox(-4.19F, -0.1015F, -24.0F, 4.0F, 0.0F, 40.0F, 0.0F, true);

		inner_roof_6_r3 = new ModelRenderer(this);
		inner_roof_6_r3.setRotationPoint(0.0F, 0.0F, 0.0F);
		inner_roof_2.addChild(inner_roof_6_r3);
		setRotationAngle(inner_roof_6_r3, 0.0F, 0.0F, 0.2618F);
		inner_roof_6_r3.setTextureOffset(56, 0).addBox(-0.5F, 0.0F, -24.0F, 3.0F, 0.0F, 40.0F, 0.0F, true);

		inner_roof_5_r3 = new ModelRenderer(this);
		inner_roof_5_r3.setRotationPoint(7.1131F, 0.9059F, 0.0F);
		inner_roof_2.addChild(inner_roof_5_r3);
		setRotationAngle(inner_roof_5_r3, 0.0F, 0.0F, 0.2618F);
		inner_roof_5_r3.setTextureOffset(0, 66).addBox(-1.0F, 0.0F, -24.0F, 2.0F, 0.0F, 40.0F, 0.0F, true);

		inner_roof_4_r3 = new ModelRenderer(this);
		inner_roof_4_r3.setRotationPoint(8.0785F, 1.1656F, 0.0F);
		inner_roof_2.addChild(inner_roof_4_r3);
		setRotationAngle(inner_roof_4_r3, 0.0F, 0.0F, 0.5236F);
		inner_roof_4_r3.setTextureOffset(72, 0).addBox(0.0F, 0.0F, -24.0F, 1.0F, 0.0F, 40.0F, 0.0F, true);

		inner_roof_3_r3 = new ModelRenderer(this);
		inner_roof_3_r3.setRotationPoint(8.9445F, 1.6656F, 0.0F);
		inner_roof_2.addChild(inner_roof_3_r3);
		setRotationAngle(inner_roof_3_r3, 0.0F, 0.0F, 1.0472F);
		inner_roof_3_r3.setTextureOffset(4, 66).addBox(0.0F, 0.0F, -24.0F, 2.0F, 0.0F, 40.0F, 0.0F, true);

		inner_roof_2_r3 = new ModelRenderer(this);
		inner_roof_2_r3.setRotationPoint(10.81F, 3.8985F, 0.0F);
		inner_roof_2.addChild(inner_roof_2_r3);
		setRotationAngle(inner_roof_2_r3, 0.0F, 0.0F, 0.5236F);
		inner_roof_2_r3.setTextureOffset(72, 0).addBox(-1.0F, 0.0F, -24.0F, 1.0F, 0.0F, 40.0F, 0.0F, true);

		roof_end_exterior = new ModelRenderer(this);
		roof_end_exterior.setRotationPoint(0.0F, 24.0F, 0.0F);
		roof_end_exterior.setTextureOffset(0, 0).addBox(-8.0F, -45.0F, -36.0F, 16.0F, 2.0F, 64.0F, 0.0F, false);

		vent_2_r1 = new ModelRenderer(this);
		vent_2_r1.setRotationPoint(-8.0F, -45.0F, 0.0F);
		roof_end_exterior.addChild(vent_2_r1);
		setRotationAngle(vent_2_r1, 0.0F, 0.0F, -0.3491F);
		vent_2_r1.setTextureOffset(112, 112).addBox(-9.0F, 0.0F, -36.0F, 9.0F, 2.0F, 64.0F, 0.0F, true);

		vent_1_r1 = new ModelRenderer(this);
		vent_1_r1.setRotationPoint(8.0F, -45.0F, 0.0F);
		roof_end_exterior.addChild(vent_1_r1);
		setRotationAngle(vent_1_r1, 0.0F, 0.0F, 0.3491F);
		vent_1_r1.setTextureOffset(112, 112).addBox(0.0F, 0.0F, -36.0F, 9.0F, 2.0F, 64.0F, 0.0F, false);

		outer_roof_1 = new ModelRenderer(this);
		outer_roof_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		roof_end_exterior.addChild(outer_roof_1);


		outer_roof_3_r2 = new ModelRenderer(this);
		outer_roof_3_r2.setRotationPoint(-15.1775F, -41.8608F, 0.0F);
		outer_roof_1.addChild(outer_roof_3_r2);
		setRotationAngle(outer_roof_3_r2, 0.0F, 0.0F, -0.5236F);
		outer_roof_3_r2.setTextureOffset(0, 0).addBox(-1.5F, 0.0F, -36.0F, 3.0F, 0.0F, 52.0F, 0.0F, false);

		outer_roof_2_r2 = new ModelRenderer(this);
		outer_roof_2_r2.setRotationPoint(-16.9765F, -40.2448F, 0.0F);
		outer_roof_1.addChild(outer_roof_2_r2);
		setRotationAngle(outer_roof_2_r2, 0.0F, 0.0F, -1.0472F);
		outer_roof_2_r2.setTextureOffset(6, 6).addBox(-1.0F, 0.0F, -36.0F, 2.0F, 0.0F, 52.0F, 0.0F, false);

		outer_roof_1_r2 = new ModelRenderer(this);
		outer_roof_1_r2.setRotationPoint(-19.0F, -32.0F, 0.0F);
		outer_roof_1.addChild(outer_roof_1_r2);
		setRotationAngle(outer_roof_1_r2, 0.0F, 3.1416F, 0.1107F);
		outer_roof_1_r2.setTextureOffset(170, 251).addBox(-1.0F, -8.0F, -16.0F, 1.0F, 8.0F, 52.0F, 0.0F, true);

		outer_roof_2 = new ModelRenderer(this);
		outer_roof_2.setRotationPoint(0.0F, 0.0F, 0.0F);
		roof_end_exterior.addChild(outer_roof_2);


		outer_roof_3_r3 = new ModelRenderer(this);
		outer_roof_3_r3.setRotationPoint(15.1775F, -41.8608F, 0.0F);
		outer_roof_2.addChild(outer_roof_3_r3);
		setRotationAngle(outer_roof_3_r3, 0.0F, 0.0F, 0.5236F);
		outer_roof_3_r3.setTextureOffset(0, 0).addBox(-1.5F, 0.0F, -36.0F, 3.0F, 0.0F, 52.0F, 0.0F, false);

		outer_roof_2_r3 = new ModelRenderer(this);
		outer_roof_2_r3.setRotationPoint(16.9765F, -40.2448F, 0.0F);
		outer_roof_2.addChild(outer_roof_2_r3);
		setRotationAngle(outer_roof_2_r3, 0.0F, 0.0F, 1.0472F);
		outer_roof_2_r3.setTextureOffset(6, 6).addBox(-1.0F, 0.0F, -36.0F, 2.0F, 0.0F, 52.0F, 0.0F, false);

		outer_roof_1_r3 = new ModelRenderer(this);
		outer_roof_1_r3.setRotationPoint(19.0F, -32.0F, 0.0F);
		outer_roof_2.addChild(outer_roof_1_r3);
		setRotationAngle(outer_roof_1_r3, 0.0F, 0.0F, -0.1107F);
		outer_roof_1_r3.setTextureOffset(170, 251).addBox(-1.0F, -8.0F, -36.0F, 1.0F, 8.0F, 52.0F, 0.0F, true);

		roof_end_light = new ModelRenderer(this);
		roof_end_light.setRotationPoint(0.0F, 24.0F, 0.0F);
		roof_end_light.setTextureOffset(8, 8).addBox(-9.4711F, -34.7514F, -24.0F, 2.0F, 0.0F, 40.0F, 0.0F, false);
		roof_end_light.setTextureOffset(8, 8).addBox(7.4711F, -34.7514F, -24.0F, 2.0F, 0.0F, 40.0F, 0.0F, true);

		light_6_r1 = new ModelRenderer(this);
		light_6_r1.setRotationPoint(7.0376F, -35.0016F, 0.0F);
		roof_end_light.addChild(light_6_r1);
		setRotationAngle(light_6_r1, 0.0F, 0.0F, 0.5236F);
		light_6_r1.setTextureOffset(22, 22).addBox(-0.5F, 0.0F, -24.0F, 1.0F, 0.0F, 40.0F, 0.0F, true);

		light_4_r1 = new ModelRenderer(this);
		light_4_r1.setRotationPoint(9.9041F, -35.0014F, 0.0F);
		roof_end_light.addChild(light_4_r1);
		setRotationAngle(light_4_r1, 0.0F, 0.0F, -0.5236F);
		light_4_r1.setTextureOffset(68, 0).addBox(-0.5F, 0.0F, -24.0F, 1.0F, 0.0F, 40.0F, 0.0F, true);

		light_3_r2 = new ModelRenderer(this);
		light_3_r2.setRotationPoint(-7.0376F, -35.0016F, 0.0F);
		roof_end_light.addChild(light_3_r2);
		setRotationAngle(light_3_r2, 0.0F, 0.0F, -0.5236F);
		light_3_r2.setTextureOffset(22, 22).addBox(-0.5F, 0.0F, -24.0F, 1.0F, 0.0F, 40.0F, 0.0F, false);

		light_1_r2 = new ModelRenderer(this);
		light_1_r2.setRotationPoint(-9.9041F, -35.0014F, 0.0F);
		roof_end_light.addChild(light_1_r2);
		setRotationAngle(light_1_r2, 0.0F, 0.0F, 0.5236F);
		light_1_r2.setTextureOffset(68, 0).addBox(-0.5F, 0.0F, -24.0F, 1.0F, 0.0F, 40.0F, 0.0F, false);

		top_handrail = new ModelRenderer(this);
		top_handrail.setRotationPoint(0.0F, 24.0F, 0.0F);
		top_handrail.setTextureOffset(0, 0).addBox(-5.0F, -36.0F, 15.8F, 0.0F, 3.0F, 0.0F, 0.2F, false);
		top_handrail.setTextureOffset(0, 0).addBox(-5.0F, -36.0F, -15.8F, 0.0F, 3.0F, 0.0F, 0.2F, false);

		top_handrail_bottom_right_r1 = new ModelRenderer(this);
		top_handrail_bottom_right_r1.setRotationPoint(-5.0F, -31.0876F, -6.8876F);
		top_handrail.addChild(top_handrail_bottom_right_r1);
		setRotationAngle(top_handrail_bottom_right_r1, -1.5708F, 0.0F, 0.0F);
		top_handrail_bottom_right_r1.setTextureOffset(0, 0).addBox(0.0F, -7.0F, 0.0F, 0.0F, 14.0F, 0.0F, 0.2F, false);

		top_handrail_right_3_r1 = new ModelRenderer(this);
		top_handrail_right_3_r1.setRotationPoint(-5.0F, -31.4108F, -14.5938F);
		top_handrail.addChild(top_handrail_right_3_r1);
		setRotationAngle(top_handrail_right_3_r1, 1.0472F, 0.0F, 0.0F);
		top_handrail_right_3_r1.setTextureOffset(0, 0).addBox(0.0F, -0.5F, 0.0F, 0.0F, 1.0F, 0.0F, 0.2F, false);

		top_handrail_right_2_r1 = new ModelRenderer(this);
		top_handrail_right_2_r1.setRotationPoint(-5.0F, -32.2938F, -15.4768F);
		top_handrail.addChild(top_handrail_right_2_r1);
		setRotationAngle(top_handrail_right_2_r1, 0.5236F, 0.0F, 0.0F);
		top_handrail_right_2_r1.setTextureOffset(0, 0).addBox(0.0F, -0.5F, 0.0F, 0.0F, 1.0F, 0.0F, 0.2F, false);

		top_handrail_bottom_left_r1 = new ModelRenderer(this);
		top_handrail_bottom_left_r1.setRotationPoint(-5.0F, -31.0876F, 6.8876F);
		top_handrail.addChild(top_handrail_bottom_left_r1);
		setRotationAngle(top_handrail_bottom_left_r1, -1.5708F, 0.0F, 0.0F);
		top_handrail_bottom_left_r1.setTextureOffset(0, 0).addBox(0.0F, -7.0F, 0.0F, 0.0F, 14.0F, 0.0F, 0.2F, false);

		top_handrail_left_3_r1 = new ModelRenderer(this);
		top_handrail_left_3_r1.setRotationPoint(-5.0F, -31.4108F, 14.5938F);
		top_handrail.addChild(top_handrail_left_3_r1);
		setRotationAngle(top_handrail_left_3_r1, -1.0472F, 0.0F, 0.0F);
		top_handrail_left_3_r1.setTextureOffset(0, 0).addBox(0.0F, -0.5F, 0.0F, 0.0F, 1.0F, 0.0F, 0.2F, false);

		top_handrail_left_2_r1 = new ModelRenderer(this);
		top_handrail_left_2_r1.setRotationPoint(-5.0F, -32.2938F, 15.4768F);
		top_handrail.addChild(top_handrail_left_2_r1);
		setRotationAngle(top_handrail_left_2_r1, -0.5236F, 0.0F, 0.0F);
		top_handrail_left_2_r1.setTextureOffset(0, 0).addBox(0.0F, -0.5F, 0.0F, 0.0F, 1.0F, 0.0F, 0.2F, false);

		handrail_strap = new ModelRenderer(this);
		handrail_strap.setRotationPoint(0.0F, 0.0F, 0.0F);
		top_handrail.addChild(handrail_strap);
		handrail_strap.setTextureOffset(12, 12).addBox(-6.0F, -32.0F, -12.0F, 2.0F, 4.0F, 0.0F, 0.0F, false);
		handrail_strap.setTextureOffset(12, 12).addBox(-6.0F, -32.0F, -6.0F, 2.0F, 4.0F, 0.0F, 0.0F, false);
		handrail_strap.setTextureOffset(12, 12).addBox(-6.0F, -32.0F, 0.0F, 2.0F, 4.0F, 0.0F, 0.0F, false);
		handrail_strap.setTextureOffset(12, 12).addBox(-6.0F, -32.0F, 6.0F, 2.0F, 4.0F, 0.0F, 0.0F, false);
		handrail_strap.setTextureOffset(12, 12).addBox(-6.0F, -32.0F, 12.0F, 2.0F, 4.0F, 0.0F, 0.0F, false);

		tv_pole = new ModelRenderer(this);
		tv_pole.setRotationPoint(0.0F, 24.0F, 0.0F);
		tv_pole.setTextureOffset(18, 0).addBox(-4.0F, -36.0F, -1.0F, 8.0F, 6.0F, 2.0F, 0.0F, false);
		tv_pole.setTextureOffset(4, 0).addBox(0.0F, -27.5F, 0.0F, 0.0F, 28.0F, 0.0F, 0.2F, false);
		tv_pole.setTextureOffset(4, 0).addBox(-1.0F, -27.5F, 0.0F, 2.0F, 0.0F, 0.0F, 0.2F, false);

		pole_5_r1 = new ModelRenderer(this);
		pole_5_r1.setRotationPoint(-3.3885F, -29.8726F, 0.0F);
		tv_pole.addChild(pole_5_r1);
		setRotationAngle(pole_5_r1, 0.0F, 0.0F, 1.2217F);
		pole_5_r1.setTextureOffset(4, 0).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 0.0F, 0.0F, 0.2F, false);

		pole_4_r1 = new ModelRenderer(this);
		pole_4_r1.setRotationPoint(-2.0683F, -28.1521F, 0.0F);
		tv_pole.addChild(pole_4_r1);
		setRotationAngle(pole_4_r1, 0.0F, 0.0F, 0.6109F);
		pole_4_r1.setTextureOffset(4, 0).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 0.0F, 0.0F, 0.2F, false);

		pole_2_r1 = new ModelRenderer(this);
		pole_2_r1.setRotationPoint(2.0683F, -28.1521F, 0.0F);
		tv_pole.addChild(pole_2_r1);
		setRotationAngle(pole_2_r1, 0.0F, 0.0F, -0.6109F);
		pole_2_r1.setTextureOffset(4, 0).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 0.0F, 0.0F, 0.2F, false);

		pole_1_r1 = new ModelRenderer(this);
		pole_1_r1.setRotationPoint(3.3885F, -29.8726F, 0.0F);
		tv_pole.addChild(pole_1_r1);
		setRotationAngle(pole_1_r1, 0.0F, 0.0F, -1.2217F);
		pole_1_r1.setTextureOffset(4, 0).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 0.0F, 0.0F, 0.2F, false);

		tv_right_r1 = new ModelRenderer(this);
		tv_right_r1.setRotationPoint(0.0F, -33.5331F, -0.8686F);
		tv_pole.addChild(tv_right_r1);
		setRotationAngle(tv_right_r1, -0.1047F, 3.1416F, 0.0F);
		tv_right_r1.setTextureOffset(18, 8).addBox(-4.0F, -3.5F, -0.5F, 8.0F, 7.0F, 1.0F, 0.0F, false);

		tv_left_r1 = new ModelRenderer(this);
		tv_left_r1.setRotationPoint(0.0F, -33.5331F, 0.8686F);
		tv_pole.addChild(tv_left_r1);
		setRotationAngle(tv_left_r1, -0.1047F, 0.0F, 0.0F);
		tv_left_r1.setTextureOffset(18, 8).addBox(-4.0F, -3.5F, -0.5F, 8.0F, 7.0F, 1.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 24.0F, 0.0F);
		head.setTextureOffset(0, 66).addBox(-20.0F, 0.0F, -16.0F, 40.0F, 1.0F, 48.0F, 0.0F, false);
		head.setTextureOffset(326, 69).addBox(-20.0F, -14.0F, -18.0F, 2.0F, 14.0F, 36.0F, 0.0F, false);
		head.setTextureOffset(0, 326).addBox(18.0F, -14.0F, -18.0F, 2.0F, 14.0F, 36.0F, 0.0F, true);
		head.setTextureOffset(326, 192).addBox(-18.0F, -36.0F, 8.0F, 36.0F, 36.0F, 0.0F, 0.0F, false);

		upper_wall_2_r3 = new ModelRenderer(this);
		upper_wall_2_r3.setRotationPoint(20.0F, -14.0F, 0.0F);
		head.addChild(upper_wall_2_r3);
		setRotationAngle(upper_wall_2_r3, 0.0F, 0.0F, -0.1107F);
		upper_wall_2_r3.setTextureOffset(240, 296).addBox(-2.0F, -19.0F, -18.0F, 2.0F, 19.0F, 36.0F, 0.0F, true);

		upper_wall_1_r3 = new ModelRenderer(this);
		upper_wall_1_r3.setRotationPoint(-20.0F, -14.0F, 0.0F);
		head.addChild(upper_wall_1_r3);
		setRotationAngle(upper_wall_1_r3, 0.0F, 0.0F, 0.1107F);
		upper_wall_1_r3.setTextureOffset(304, 260).addBox(0.0F, -19.0F, -18.0F, 2.0F, 19.0F, 36.0F, 0.0F, false);

		seat_head_1 = new ModelRenderer(this);
		seat_head_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(seat_head_1);
		seat_head_1.setTextureOffset(0, 34).addBox(11.0F, -6.0F, -16.0F, 7.0F, 1.0F, 24.0F, 0.0F, false);

		seat_back_top_r1 = new ModelRenderer(this);
		seat_back_top_r1.setRotationPoint(18.0F, -6.5F, 0.0F);
		seat_head_1.addChild(seat_back_top_r1);
		setRotationAngle(seat_back_top_r1, 0.0F, 0.0F, 0.0873F);
		seat_back_top_r1.setTextureOffset(170, 178).addBox(-1.0F, -8.0F, -16.0F, 1.0F, 8.0F, 24.0F, 0.0F, false);

		seat_head_2 = new ModelRenderer(this);
		seat_head_2.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(seat_head_2);


		seat_bottom_r2 = new ModelRenderer(this);
		seat_bottom_r2.setRotationPoint(0.0F, 0.0F, 0.0F);
		seat_head_2.addChild(seat_bottom_r2);
		setRotationAngle(seat_bottom_r2, 0.0F, 3.1416F, 0.0F);
		seat_bottom_r2.setTextureOffset(0, 34).addBox(11.0F, -6.0F, -8.0F, 7.0F, 1.0F, 24.0F, 0.0F, false);

		seat_back_top_r2 = new ModelRenderer(this);
		seat_back_top_r2.setRotationPoint(-18.0F, -6.5F, 0.0F);
		seat_head_2.addChild(seat_back_top_r2);
		setRotationAngle(seat_back_top_r2, 0.0F, 3.1416F, -0.0873F);
		seat_back_top_r2.setTextureOffset(170, 178).addBox(-1.0F, -8.0F, -8.0F, 1.0F, 8.0F, 24.0F, 0.0F, false);

		head_exterior = new ModelRenderer(this);
		head_exterior.setRotationPoint(0.0F, 24.0F, 0.0F);
		head_exterior.setTextureOffset(224, 0).addBox(-21.0F, 0.0F, 8.0F, 42.0F, 7.0F, 24.0F, 0.0F, false);
		head_exterior.setTextureOffset(58, 188).addBox(-21.0F, 0.0F, -16.0F, 1.0F, 7.0F, 24.0F, 0.0F, false);
		head_exterior.setTextureOffset(40, 326).addBox(-18.0F, -36.0F, 9.0F, 36.0F, 36.0F, 0.0F, 0.0F, false);
		head_exterior.setTextureOffset(280, 315).addBox(-20.0F, -14.0F, -18.0F, 2.0F, 14.0F, 36.0F, 0.0F, false);
		head_exterior.setTextureOffset(116, 251).addBox(18.0F, -14.0F, -18.0F, 2.0F, 14.0F, 36.0F, 0.0F, true);
		head_exterior.setTextureOffset(200, 49).addBox(-20.8F, -14.0F, 16.0F, 1.0F, 14.0F, 16.0F, 0.0F, false);
		head_exterior.setTextureOffset(350, 22).addBox(19.8F, -14.0F, 16.0F, 1.0F, 14.0F, 16.0F, 0.0F, true);
		head_exterior.setTextureOffset(252, 31).addBox(-18.0F, -43.9F, 8.0F, 36.0F, 12.0F, 26.0F, 0.0F, false);

		driver_door_top_2_r1 = new ModelRenderer(this);
		driver_door_top_2_r1.setRotationPoint(19.8061F, -13.8896F, 0.0F);
		head_exterior.addChild(driver_door_top_2_r1);
		setRotationAngle(driver_door_top_2_r1, 0.0F, 0.0F, -0.1107F);
		driver_door_top_2_r1.setTextureOffset(116, 178).addBox(0.0F, -19.0F, 16.0F, 1.0F, 19.0F, 16.0F, 0.0F, true);

		driver_door_top_1_r1 = new ModelRenderer(this);
		driver_door_top_1_r1.setRotationPoint(-19.8061F, -13.8896F, 0.0F);
		head_exterior.addChild(driver_door_top_1_r1);
		setRotationAngle(driver_door_top_1_r1, 0.0F, 0.0F, 0.1107F);
		driver_door_top_1_r1.setTextureOffset(194, 122).addBox(-1.0F, -19.0F, 16.0F, 1.0F, 19.0F, 16.0F, 0.0F, false);

		upper_wall_2_r4 = new ModelRenderer(this);
		upper_wall_2_r4.setRotationPoint(20.0F, -14.0F, 0.0F);
		head_exterior.addChild(upper_wall_2_r4);
		setRotationAngle(upper_wall_2_r4, 0.0F, 0.0F, -0.1107F);
		upper_wall_2_r4.setTextureOffset(286, 192).addBox(-2.0F, -19.0F, -18.0F, 2.0F, 19.0F, 36.0F, 0.0F, true);

		upper_wall_1_r4 = new ModelRenderer(this);
		upper_wall_1_r4.setRotationPoint(-20.0F, -14.0F, 0.0F);
		head_exterior.addChild(upper_wall_1_r4);
		setRotationAngle(upper_wall_1_r4, 0.0F, 0.0F, 0.1107F);
		upper_wall_1_r4.setTextureOffset(286, 86).addBox(0.0F, -19.0F, -18.0F, 2.0F, 19.0F, 36.0F, 0.0F, false);

		floor_2_r1 = new ModelRenderer(this);
		floor_2_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		head_exterior.addChild(floor_2_r1);
		setRotationAngle(floor_2_r1, 0.0F, 3.1416F, 0.0F);
		floor_2_r1.setTextureOffset(58, 188).addBox(-21.0F, 0.0F, -8.0F, 1.0F, 7.0F, 24.0F, 0.0F, false);

		front = new ModelRenderer(this);
		front.setRotationPoint(0.0F, 0.0F, 0.0F);
		head_exterior.addChild(front);


		bottom_corner_2_r1 = new ModelRenderer(this);
		bottom_corner_2_r1.setRotationPoint(13.451F, 2.0181F, 50.7099F);
		front.addChild(bottom_corner_2_r1);
		setRotationAngle(bottom_corner_2_r1, -0.1745F, 0.5236F, 0.0873F);
		bottom_corner_2_r1.setTextureOffset(114, 32).addBox(-4.5F, -2.5F, 0.0F, 9.0F, 5.0F, 0.0F, 0.0F, false);

		bottom_corner_1_r1 = new ModelRenderer(this);
		bottom_corner_1_r1.setRotationPoint(-13.451F, 2.0181F, 50.7099F);
		front.addChild(bottom_corner_1_r1);
		setRotationAngle(bottom_corner_1_r1, -0.1745F, -0.5236F, -0.0873F);
		bottom_corner_1_r1.setTextureOffset(114, 37).addBox(-4.5F, -2.5F, 0.0F, 9.0F, 5.0F, 0.0F, 0.0F, false);

		roof_corner_2_r1 = new ModelRenderer(this);
		roof_corner_2_r1.setRotationPoint(16.7928F, -39.5613F, 34.8655F);
		front.addChild(roof_corner_2_r1);
		setRotationAngle(roof_corner_2_r1, 1.0472F, 0.0F, 1.0472F);
		roof_corner_2_r1.setTextureOffset(24, 30).addBox(-1.5F, -1.0F, 0.0F, 3.0F, 2.0F, 0.0F, 0.0F, false);

		roof_corner_1_r1 = new ModelRenderer(this);
		roof_corner_1_r1.setRotationPoint(-16.7928F, -39.5613F, 34.8655F);
		front.addChild(roof_corner_1_r1);
		setRotationAngle(roof_corner_1_r1, 1.0472F, 0.0F, -1.0472F);
		roof_corner_1_r1.setTextureOffset(30, 30).addBox(-1.5F, -1.0F, 0.0F, 3.0F, 2.0F, 0.0F, 0.0F, false);

		roof_middle_corner_2_r1 = new ModelRenderer(this);
		roof_middle_corner_2_r1.setRotationPoint(14.8026F, -41.2114F, 35.2985F);
		front.addChild(roof_middle_corner_2_r1);
		setRotationAngle(roof_middle_corner_2_r1, 1.0472F, 0.0F, 0.5236F);
		roof_middle_corner_2_r1.setTextureOffset(0, 55).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 0.0F, 0.0F, false);

		roof_middle_corner_1_r1 = new ModelRenderer(this);
		roof_middle_corner_1_r1.setRotationPoint(-14.8026F, -41.2114F, 35.2985F);
		front.addChild(roof_middle_corner_1_r1);
		setRotationAngle(roof_middle_corner_1_r1, 1.0472F, 0.0F, -0.5236F);
		roof_middle_corner_1_r1.setTextureOffset(0, 52).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 0.0F, 0.0F, false);

		roof_side_2_r1 = new ModelRenderer(this);
		roof_side_2_r1.setRotationPoint(9.6786F, -41.8273F, 36.5976F);
		front.addChild(roof_side_2_r1);
		setRotationAngle(roof_side_2_r1, 1.0472F, 0.0F, 0.1745F);
		roof_side_2_r1.setTextureOffset(224, 0).addBox(-4.0F, -3.0F, 0.0F, 8.0F, 6.0F, 0.0F, 0.0F, false);

		roof_side_1_r1 = new ModelRenderer(this);
		roof_side_1_r1.setRotationPoint(-9.6786F, -41.8273F, 36.5976F);
		front.addChild(roof_side_1_r1);
		setRotationAngle(roof_side_1_r1, 1.0472F, 0.0F, -0.1745F);
		roof_side_1_r1.setTextureOffset(224, 6).addBox(-4.0F, -3.0F, 0.0F, 8.0F, 6.0F, 0.0F, 0.0F, false);

		top_side_2_r1 = new ModelRenderer(this);
		top_side_2_r1.setRotationPoint(17.6914F, -26.7346F, 38.2783F);
		front.addChild(top_side_2_r1);
		setRotationAngle(top_side_2_r1, 0.0F, 1.309F, -0.1107F);
		top_side_2_r1.setTextureOffset(228, 192).addBox(-6.5F, -13.0F, 0.0F, 13.0F, 26.0F, 0.0F, 0.0F, false);

		top_side_1_r1 = new ModelRenderer(this);
		top_side_1_r1.setRotationPoint(-17.6914F, -26.7346F, 38.2783F);
		front.addChild(top_side_1_r1);
		setRotationAngle(top_side_1_r1, 0.0F, -1.309F, 0.1107F);
		top_side_1_r1.setTextureOffset(252, 69).addBox(-6.5F, -13.0F, 0.0F, 13.0F, 26.0F, 0.0F, 0.0F, false);

		bottom_side_2_r1 = new ModelRenderer(this);
		bottom_side_2_r1.setRotationPoint(18.3403F, -3.5F, 41.176F);
		front.addChild(bottom_side_2_r1);
		setRotationAngle(bottom_side_2_r1, 0.0F, 1.309F, 0.0F);
		bottom_side_2_r1.setTextureOffset(130, 148).addBox(-9.5F, -10.5F, 0.0F, 19.0F, 21.0F, 0.0F, 0.0F, false);

		bottom_side_1_r1 = new ModelRenderer(this);
		bottom_side_1_r1.setRotationPoint(-18.3403F, -3.5F, 41.176F);
		front.addChild(bottom_side_1_r1);
		setRotationAngle(bottom_side_1_r1, 0.0F, -1.309F, 0.0F);
		bottom_side_1_r1.setTextureOffset(200, 82).addBox(-9.5F, -10.5F, 0.0F, 19.0F, 21.0F, 0.0F, 0.0F, false);

		front_side_2_r1 = new ModelRenderer(this);
		front_side_2_r1.setRotationPoint(13.6611F, -21.1867F, 43.5695F);
		front.addChild(front_side_2_r1);
		setRotationAngle(front_side_2_r1, 0.3491F, 0.5236F, 0.0873F);
		front_side_2_r1.setTextureOffset(192, 251).addBox(-6.5F, -22.0F, 0.0F, 13.0F, 44.0F, 0.0F, 0.0F, false);

		front_side_1_r1 = new ModelRenderer(this);
		front_side_1_r1.setRotationPoint(-13.6611F, -21.1867F, 43.5695F);
		front.addChild(front_side_1_r1);
		setRotationAngle(front_side_1_r1, 0.3491F, -0.5236F, -0.0873F);
		front_side_1_r1.setTextureOffset(0, 265).addBox(-6.5F, -22.0F, 0.0F, 13.0F, 44.0F, 0.0F, 0.0F, false);

		front_bottom_r1 = new ModelRenderer(this);
		front_bottom_r1.setRotationPoint(0.0F, 1.7064F, 52.9026F);
		front.addChild(front_bottom_r1);
		setRotationAngle(front_bottom_r1, -0.1745F, 0.0F, 0.0F);
		front_bottom_r1.setTextureOffset(0, 59).addBox(-10.0F, -2.5F, 0.0F, 20.0F, 5.0F, 0.0F, 0.0F, false);

		bottom_r1 = new ModelRenderer(this);
		bottom_r1.setRotationPoint(0.0F, 4.6192F, 42.7393F);
		front.addChild(bottom_r1);
		setRotationAngle(bottom_r1, -1.3526F, 0.0F, 0.0F);
		bottom_r1.setTextureOffset(332, 0).addBox(-21.0F, -11.0F, 0.0F, 42.0F, 22.0F, 0.0F, 0.0F, false);

		front_roof_r1 = new ModelRenderer(this);
		front_roof_r1.setRotationPoint(0.0F, -42.5001F, 36.5976F);
		front.addChild(front_roof_r1);
		setRotationAngle(front_roof_r1, 1.0472F, 0.0F, 0.0F);
		front_roof_r1.setTextureOffset(224, 12).addBox(-6.0F, -3.0F, 0.0F, 12.0F, 6.0F, 0.0F, 0.0F, false);

		front_middle_r1 = new ModelRenderer(this);
		front_middle_r1.setRotationPoint(0.0F, -21.4283F, 45.8123F);
		front.addChild(front_middle_r1);
		setRotationAngle(front_middle_r1, 0.3491F, 0.0F, 0.0F);
		front_middle_r1.setTextureOffset(0, 66).addBox(-10.0F, -22.0F, 0.0F, 20.0F, 44.0F, 0.0F, 0.0F, false);

		top_handrail_head = new ModelRenderer(this);
		top_handrail_head.setRotationPoint(0.0F, 24.0F, 0.0F);
		top_handrail_head.setTextureOffset(0, 0).addBox(-5.0F, -36.0F, 9.8F, 0.0F, 3.0F, 0.0F, 0.2F, false);
		top_handrail_head.setTextureOffset(0, 0).addBox(-5.0F, -36.0F, -9.8F, 0.0F, 3.0F, 0.0F, 0.2F, false);

		top_handrail_bottom_right_r2 = new ModelRenderer(this);
		top_handrail_bottom_right_r2.setRotationPoint(-5.0F, -31.0876F, -3.8876F);
		top_handrail_head.addChild(top_handrail_bottom_right_r2);
		setRotationAngle(top_handrail_bottom_right_r2, -1.5708F, 0.0F, 0.0F);
		top_handrail_bottom_right_r2.setTextureOffset(0, 0).addBox(0.0F, -4.0F, 0.0F, 0.0F, 8.0F, 0.0F, 0.2F, false);

		top_handrail_right_3_r2 = new ModelRenderer(this);
		top_handrail_right_3_r2.setRotationPoint(-5.0F, -31.4108F, -8.5938F);
		top_handrail_head.addChild(top_handrail_right_3_r2);
		setRotationAngle(top_handrail_right_3_r2, 1.0472F, 0.0F, 0.0F);
		top_handrail_right_3_r2.setTextureOffset(0, 0).addBox(0.0F, -0.5F, 0.0F, 0.0F, 1.0F, 0.0F, 0.2F, false);

		top_handrail_right_2_r2 = new ModelRenderer(this);
		top_handrail_right_2_r2.setRotationPoint(-5.0F, -32.2938F, -9.4768F);
		top_handrail_head.addChild(top_handrail_right_2_r2);
		setRotationAngle(top_handrail_right_2_r2, 0.5236F, 0.0F, 0.0F);
		top_handrail_right_2_r2.setTextureOffset(0, 0).addBox(0.0F, -0.5F, 0.0F, 0.0F, 1.0F, 0.0F, 0.2F, false);

		top_handrail_bottom_left_r2 = new ModelRenderer(this);
		top_handrail_bottom_left_r2.setRotationPoint(-5.0F, -31.0876F, 3.8876F);
		top_handrail_head.addChild(top_handrail_bottom_left_r2);
		setRotationAngle(top_handrail_bottom_left_r2, -1.5708F, 0.0F, 0.0F);
		top_handrail_bottom_left_r2.setTextureOffset(0, 0).addBox(0.0F, -4.0F, 0.0F, 0.0F, 8.0F, 0.0F, 0.2F, false);

		top_handrail_left_3_r2 = new ModelRenderer(this);
		top_handrail_left_3_r2.setRotationPoint(-5.0F, -31.4108F, 8.5938F);
		top_handrail_head.addChild(top_handrail_left_3_r2);
		setRotationAngle(top_handrail_left_3_r2, -1.0472F, 0.0F, 0.0F);
		top_handrail_left_3_r2.setTextureOffset(0, 0).addBox(0.0F, -0.5F, 0.0F, 0.0F, 1.0F, 0.0F, 0.2F, false);

		top_handrail_left_2_r2 = new ModelRenderer(this);
		top_handrail_left_2_r2.setRotationPoint(-5.0F, -32.2938F, 9.4768F);
		top_handrail_head.addChild(top_handrail_left_2_r2);
		setRotationAngle(top_handrail_left_2_r2, -0.5236F, 0.0F, 0.0F);
		top_handrail_left_2_r2.setTextureOffset(0, 0).addBox(0.0F, -0.5F, 0.0F, 0.0F, 1.0F, 0.0F, 0.2F, false);

		handrail_strap_head = new ModelRenderer(this);
		handrail_strap_head.setRotationPoint(0.0F, 0.0F, 0.0F);
		top_handrail_head.addChild(handrail_strap_head);
		handrail_strap_head.setTextureOffset(12, 12).addBox(-6.0F, -32.0F, -6.0F, 2.0F, 4.0F, 0.0F, 0.0F, false);
		handrail_strap_head.setTextureOffset(12, 12).addBox(-6.0F, -32.0F, 0.0F, 2.0F, 4.0F, 0.0F, 0.0F, false);
		handrail_strap_head.setTextureOffset(12, 12).addBox(-6.0F, -32.0F, 6.0F, 2.0F, 4.0F, 0.0F, 0.0F, false);

		headlights = new ModelRenderer(this);
		headlights.setRotationPoint(0.0F, 24.0F, 0.0F);


		headlight_4_r1 = new ModelRenderer(this);
		headlight_4_r1.setRotationPoint(-13.6611F, -21.1867F, 43.5695F);
		headlights.addChild(headlight_4_r1);
		setRotationAngle(headlight_4_r1, 0.3491F, -0.5236F, -0.0873F);
		headlight_4_r1.setTextureOffset(16, 34).addBox(1.6611F, 5.6867F, -0.1695F, 4.0F, 6.0F, 0.0F, 0.0F, true);

		headlight_3_r1 = new ModelRenderer(this);
		headlight_3_r1.setRotationPoint(0.0F, -21.4283F, 45.8123F);
		headlights.addChild(headlight_3_r1);
		setRotationAngle(headlight_3_r1, 0.3491F, 0.0F, 0.0F);
		headlight_3_r1.setTextureOffset(18, 16).addBox(-12.0F, 5.4283F, -0.1123F, 7.0F, 6.0F, 0.0F, 0.0F, true);
		headlight_3_r1.setTextureOffset(18, 16).addBox(5.0F, 5.4283F, -0.1123F, 7.0F, 6.0F, 0.0F, 0.0F, false);

		headlight_2_r1 = new ModelRenderer(this);
		headlight_2_r1.setRotationPoint(13.6611F, -21.1867F, 43.5695F);
		headlights.addChild(headlight_2_r1);
		setRotationAngle(headlight_2_r1, 0.3491F, 0.5236F, 0.0873F);
		headlight_2_r1.setTextureOffset(16, 34).addBox(-5.6611F, 5.6867F, -0.1695F, 4.0F, 6.0F, 0.0F, 0.0F, false);

		tail_lights = new ModelRenderer(this);
		tail_lights.setRotationPoint(0.0F, 24.0F, 0.0F);


		tail_light_4_r1 = new ModelRenderer(this);
		tail_light_4_r1.setRotationPoint(-13.6611F, -21.1867F, 43.5695F);
		tail_lights.addChild(tail_light_4_r1);
		setRotationAngle(tail_light_4_r1, 0.3491F, -0.5236F, -0.0873F);
		tail_light_4_r1.setTextureOffset(16, 40).addBox(1.6611F, 5.6867F, -0.1695F, 4.0F, 6.0F, 0.0F, 0.0F, true);

		tail_light_3_r1 = new ModelRenderer(this);
		tail_light_3_r1.setRotationPoint(0.0F, -21.4283F, 45.8123F);
		tail_lights.addChild(tail_light_3_r1);
		setRotationAngle(tail_light_3_r1, 0.3491F, 0.0F, 0.0F);
		tail_light_3_r1.setTextureOffset(18, 22).addBox(-12.0F, 5.4283F, -0.1123F, 7.0F, 6.0F, 0.0F, 0.0F, true);
		tail_light_3_r1.setTextureOffset(18, 22).addBox(5.0F, 5.4283F, -0.1123F, 7.0F, 6.0F, 0.0F, 0.0F, false);

		tail_light_2_r1 = new ModelRenderer(this);
		tail_light_2_r1.setRotationPoint(13.6611F, -21.1867F, 43.5695F);
		tail_lights.addChild(tail_light_2_r1);
		setRotationAngle(tail_light_2_r1, 0.3491F, 0.5236F, 0.0873F);
		tail_light_2_r1.setTextureOffset(16, 40).addBox(-5.6611F, 5.6867F, -0.1695F, 4.0F, 6.0F, 0.0F, 0.0F, false);

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.setTextureOffset(4, 0).addBox(0.0F, -36.0F, 0.0F, 0.0F, 36.0F, 0.0F, 0.2F, false);
	}

	private static final int DOOR_MAX = 14;

	@Override
	protected void renderWindowPositions(MatrixStack matrices, IVertexBuilder vertices, RenderStage renderStage, int light, int position) {
		switch (renderStage) {
			case LIGHTS:
				renderMirror(roof_light, matrices, vertices, light, position);
				break;
			case INTERIOR:
				renderMirror(window, matrices, vertices, light, position);
				renderMirror(roof, matrices, vertices, light, position);
				renderMirror(top_handrail, matrices, vertices, light, position);
				break;
			case INTERIOR_TRANSLUCENT:
				renderMirror(side_panel, matrices, vertices, light, position - 15.9F);
				renderMirror(side_panel, matrices, vertices, light, position + 15.9F);
				break;
			case EXTERIOR:
				renderMirror(window_exterior, matrices, vertices, light, position);
				renderMirror(roof_exterior, matrices, vertices, light, position);
				break;
		}
	}

	@Override
	protected void renderDoorPositions(MatrixStack matrices, IVertexBuilder vertices, RenderStage renderStage, int light, int position, float doorLeftValue, float doorRightValue) {
		final float doorLeft = doorLeftValue * DOOR_MAX;
		final float doorRight = doorRightValue * DOOR_MAX;

		switch (renderStage) {
			case LIGHTS:
				renderMirror(roof_light, matrices, vertices, light, position);
				break;
			case INTERIOR:
				door_left.setRotationPoint(0, 0, doorRight);
				door_right.setRotationPoint(0, 0, -doorRight);
				renderOnce(door, matrices, vertices, light, position);
				door_left.setRotationPoint(0, 0, doorLeft);
				door_right.setRotationPoint(0, 0, -doorLeft);
				renderOnceFlipped(door, matrices, vertices, light, position);
				renderMirror(roof, matrices, vertices, light, position);
				if (getDoorPositions().length > 3 && (position == getDoorPositions()[1] || position == getDoorPositions()[3])) {
					renderOnce(bb_main, matrices, vertices, light, position);
				} else {
					renderOnce(tv_pole, matrices, vertices, light, position);
				}

				break;
			case EXTERIOR:
				door_left_exterior.setRotationPoint(0, 0, doorRight);
				door_right_exterior.setRotationPoint(0, 0, -doorRight);
				renderOnce(door_exterior, matrices, vertices, light, position);
				door_left_exterior.setRotationPoint(0, 0, doorLeft);
				door_right_exterior.setRotationPoint(0, 0, -doorLeft);
				renderOnceFlipped(door_exterior, matrices, vertices, light, position);
				renderMirror(roof_exterior, matrices, vertices, light, position);
				break;
		}
	}

	@Override
	protected void renderHeadPosition1(MatrixStack matrices, IVertexBuilder vertices, RenderStage renderStage, int light, int position, boolean useHeadlights) {
		switch (renderStage) {
			case LIGHTS:
				renderOnce(roof_end_light, matrices, vertices, light, position);
				renderOnceFlipped(useHeadlights ? headlights : tail_lights, matrices, vertices, light, position);
				break;
			case INTERIOR:
				renderOnceFlipped(head, matrices, vertices, light, position);
				renderOnce(roof_end, matrices, vertices, light, position);
				renderMirror(top_handrail_head, matrices, vertices, light, position + 6);
				break;
			case INTERIOR_TRANSLUCENT:
				renderMirror(side_panel, matrices, vertices, light, position + 15.9F);
				break;
			case EXTERIOR:
				renderOnceFlipped(head_exterior, matrices, vertices, light, position);
				renderOnce(roof_end_exterior, matrices, vertices, light, position + 2);
				break;
		}
	}

	@Override
	protected void renderHeadPosition2(MatrixStack matrices, IVertexBuilder vertices, RenderStage renderStage, int light, int position, boolean useHeadlights) {
		switch (renderStage) {
			case LIGHTS:
				renderOnce(roof_end_light, matrices, vertices, light, position);
				renderOnce(useHeadlights ? headlights : tail_lights, matrices, vertices, light, position);
				break;
			case INTERIOR:
				renderOnce(head, matrices, vertices, light, position);
				renderOnceFlipped(roof_end, matrices, vertices, light, position);
				renderMirror(top_handrail_head, matrices, vertices, light, position - 6);
				break;
			case INTERIOR_TRANSLUCENT:
				renderMirror(side_panel, matrices, vertices, light, position - 15.9F);
				break;
			case EXTERIOR:
				renderOnce(head_exterior, matrices, vertices, light, position);
				renderOnceFlipped(roof_end_exterior, matrices, vertices, light, position - 2);
				break;
		}
	}

	@Override
	protected void renderEndPosition1(MatrixStack matrices, IVertexBuilder vertices, RenderStage renderStage, int light, int position) {
		switch (renderStage) {
			case LIGHTS:
				renderOnce(roof_end_light, matrices, vertices, light, position);
				break;
			case INTERIOR:
				renderOnce(end, matrices, vertices, light, position);
				renderOnce(roof_end, matrices, vertices, light, position);
				renderMirror(top_handrail, matrices, vertices, light, position);
				break;
			case INTERIOR_TRANSLUCENT:
				renderMirror(side_panel, matrices, vertices, light, position + 15.9F);
				break;
			case EXTERIOR:
				renderOnce(end_exterior, matrices, vertices, light, position);
				renderOnce(roof_end_exterior, matrices, vertices, light, position);
				break;
		}
	}

	@Override
	protected void renderEndPosition2(MatrixStack matrices, IVertexBuilder vertices, RenderStage renderStage, int light, int position) {
		switch (renderStage) {
			case LIGHTS:
				renderOnceFlipped(roof_end_light, matrices, vertices, light, position);
				break;
			case INTERIOR:
				renderOnceFlipped(end, matrices, vertices, light, position);
				renderOnceFlipped(roof_end, matrices, vertices, light, position);
				renderMirror(top_handrail, matrices, vertices, light, position);
				break;
			case INTERIOR_TRANSLUCENT:
				renderMirror(side_panel, matrices, vertices, light, position - 15.9F);
				break;
			case EXTERIOR:
				renderOnceFlipped(end_exterior, matrices, vertices, light, position);
				renderOnceFlipped(roof_end_exterior, matrices, vertices, light, position);
				break;
		}
	}

	@Override
	protected void renderDoorLabels(MatrixStack matrices, IRenderTypeBuffer renderTypeBuffer, RenderStage renderStage, int light, float positionScaled,
									float doorLeftValue, float doorRightValue) {
		final int colorAdjustment = 0xFFB0B0B0;
		final int colorAdjustmentDark = 0xFF707070;
		final float doorLeft = doorLeftValue * DOOR_MAX / 16;
		final float doorRight = doorRightValue * DOOR_MAX / 16;

		matrices.push();
		matrices.translate(1.25, -0.875, 0);
		matrices.rotate(Vector3f.ZN.rotationDegrees(6.34F));
		if (renderStage == RenderStage.EXTERIOR) {
			IGui.drawTexture(matrices, renderTypeBuffer, "mtr:textures/signs/gap_left.png", 0.0625F, -1.215F, positionScaled - 0.7F, 0.0625F, -1.125F, positionScaled - 0.2375F, 0, 0, 1, 1, -1, light);
			IGui.drawTexture(matrices, renderTypeBuffer, "mtr:textures/signs/gap_right.png", 0.0625F, -1.215F, positionScaled + 0.2375F, 0.0625F, -1.125F, positionScaled + 0.7F, 0, 0, 1, 1, -1, light);
			IGui.drawTexture(matrices, renderTypeBuffer, "mtr:textures/signs/door_left.png", 0.0625F, -0.91F, positionScaled - 0.3485F - doorLeft, 0.0625F, -0.765F, positionScaled - 0.2375F - doorLeft, 0, 0, 1, 1, colorAdjustmentDark, light);
			IGui.drawTexture(matrices, renderTypeBuffer, "mtr:textures/signs/door_right.png", 0.0625F, -0.91F, positionScaled + 0.2375F + doorLeft, 0.0625F, -0.765F, positionScaled + 0.3485F + doorLeft, 0, 0, 1, 1, colorAdjustmentDark, light);
		} else {
			IGui.drawTexture(matrices, renderTypeBuffer, "mtr:textures/signs/gap_left.png", -0.013F, -1.12F, positionScaled + 0.7F + doorLeft, -0.013F, -1.03F, positionScaled + 0.2375F + doorLeft, 0, 0, 1, 1, colorAdjustment, light);
			IGui.drawTexture(matrices, renderTypeBuffer, "mtr:textures/signs/gap_right.png", -0.013F, -1.12F, positionScaled - 0.2375F - doorLeft, -0.013F, -1.03F, positionScaled - 0.7F - doorLeft, 0, 0, 1, 1, colorAdjustment, light);
			IGui.drawTexture(matrices, renderTypeBuffer, "mtr:textures/signs/door_left.png", -0.013F, -0.91F, positionScaled + 0.3485F + doorLeft, -0.013F, -0.765F, positionScaled + 0.2375F + doorLeft, 0, 0, 1, 1, colorAdjustment, light);
			IGui.drawTexture(matrices, renderTypeBuffer, "mtr:textures/signs/door_right.png", -0.013F, -0.91F, positionScaled - 0.2375F - doorLeft, -0.013F, -0.765F, positionScaled - 0.3485F - doorLeft, 0, 0, 1, 1, colorAdjustment, light);
			IGui.drawTexture(matrices, renderTypeBuffer, "mtr:textures/signs/stripe.png", -0.013F, -1.125F, positionScaled + 0.875F + doorLeft, -0.013F, 0, positionScaled + 0.8125F + doorLeft, 1, 0, 0, 24, colorAdjustment, light);
			IGui.drawTexture(matrices, renderTypeBuffer, "mtr:textures/signs/stripe.png", -0.013F, -1.125F, positionScaled - 0.8125F - doorLeft, -0.013F, 0, positionScaled - 0.875F - doorLeft, 0, 0, 1, 24, colorAdjustment, light);
			IGui.drawTexture(matrices, renderTypeBuffer, "mtr:textures/signs/stripe.png", -0.109375F, -0.0375F, positionScaled + 0.874F, -0.046875F, 0, positionScaled + 0.874F, 0, 0.4F, 1, 1.3F, colorAdjustment, light);
			IGui.drawTexture(matrices, renderTypeBuffer, "mtr:textures/signs/stripe.png", -0.046875F, -0.0375F, positionScaled - 0.874F, -0.109375F, 0, positionScaled - 0.874F, 1, 0.4F, 0, 1.3F, colorAdjustment, light);
		}
		matrices.pop();

		if (renderStage == RenderStage.INTERIOR) {
			IGui.drawTexture(matrices, renderTypeBuffer, "mtr:textures/signs/stripe.png", 1.237F, -0.88F, positionScaled + 0.875F + doorLeft, 1.237F, -0.13F, positionScaled + 0.8125F + doorLeft, 1, 0.9F, 0, 16.9F, colorAdjustment, light);
			IGui.drawTexture(matrices, renderTypeBuffer, "mtr:textures/signs/stripe.png", 1.237F, -0.88F, positionScaled - 0.8125F - doorLeft, 1.237F, -0.13F, positionScaled - 0.875F - doorLeft, 0, 0.9F, 1, 16.9F, colorAdjustment, light);
			IGui.drawTexture(matrices, renderTypeBuffer, "mtr:textures/signs/stripe.png", 1.140625F, -0.88F, positionScaled + 0.8745F, 1.203125F, -0.13F, positionScaled + 0.8745F, 0, 0, 1, 16, colorAdjustment, light);
			IGui.drawTexture(matrices, renderTypeBuffer, "mtr:textures/signs/stripe.png", 1.203125F, -0.88F, positionScaled - 0.8745F, 1.140625F, -0.13F, positionScaled - 0.8745F, 1, 0, 0, 16, colorAdjustment, light);
		}

		matrices.push();
		matrices.translate(-1.25, -0.875, 0);
		matrices.rotate(Vector3f.ZP.rotationDegrees(6.34F));
		if (renderStage == RenderStage.EXTERIOR) {
			IGui.drawTexture(matrices, renderTypeBuffer, "mtr:textures/signs/gap_left.png", -0.0625F, -1.215F, positionScaled + 0.7F, -0.0625F, -1.125F, positionScaled + 0.2375F, 0, 0, 1, 1, -1, light);
			IGui.drawTexture(matrices, renderTypeBuffer, "mtr:textures/signs/gap_right.png", -0.0625F, -1.215F, positionScaled - 0.2375F, -0.0625F, -1.125F, positionScaled - 0.7F, 0, 0, 1, 1, -1, light);
			IGui.drawTexture(matrices, renderTypeBuffer, "mtr:textures/signs/door_left.png", -0.0625F, -0.91F, positionScaled + 0.3485F + doorRight, -0.0625F, -0.765F, positionScaled + 0.2375F + doorRight, 0, 0, 1, 1, colorAdjustmentDark, light);
			IGui.drawTexture(matrices, renderTypeBuffer, "mtr:textures/signs/door_right.png", -0.0625F, -0.91F, positionScaled - 0.2375F - doorRight, -0.0625F, -0.765F, positionScaled - 0.3485F - doorRight, 0, 0, 1, 1, colorAdjustmentDark, light);
		} else {
			IGui.drawTexture(matrices, renderTypeBuffer, "mtr:textures/signs/gap_left.png", 0.013F, -1.12F, positionScaled - 0.7F - doorRight, 0.013F, -1.03F, positionScaled - 0.2375F - doorRight, 0, 0, 1, 1, colorAdjustment, light);
			IGui.drawTexture(matrices, renderTypeBuffer, "mtr:textures/signs/gap_right.png", 0.013F, -1.12F, positionScaled + 0.2375F + doorRight, 0.013F, -1.03F, positionScaled + 0.7F + doorRight, 0, 0, 1, 1, colorAdjustment, light);
			IGui.drawTexture(matrices, renderTypeBuffer, "mtr:textures/signs/door_left.png", 0.013F, -0.91F, positionScaled - 0.3485F - doorRight, 0.013F, -0.765F, positionScaled - 0.2375F - doorRight, 0, 0, 1, 1, colorAdjustment, light);
			IGui.drawTexture(matrices, renderTypeBuffer, "mtr:textures/signs/door_right.png", 0.013F, -0.91F, positionScaled + 0.2375F + doorRight, 0.013F, -0.765F, positionScaled + 0.3485F + doorRight, 0, 0, 1, 1, colorAdjustment, light);
			IGui.drawTexture(matrices, renderTypeBuffer, "mtr:textures/signs/stripe.png", 0.013F, -1.125F, positionScaled - 0.875F - doorRight, 0.013F, 0, positionScaled - 0.8125F - doorRight, 1, 0, 0, 24, colorAdjustment, light);
			IGui.drawTexture(matrices, renderTypeBuffer, "mtr:textures/signs/stripe.png", 0.013F, -1.125F, positionScaled + 0.8125F + doorRight, 0.013F, 0, positionScaled + 0.875F + doorRight, 0, 0, 1, 24, colorAdjustment, light);
			IGui.drawTexture(matrices, renderTypeBuffer, "mtr:textures/signs/stripe.png", 0.109375F, -0.0375F, positionScaled - 0.874F, 0.046875F, 0, positionScaled - 0.874F, 0, 0.4F, 1, 1.3F, colorAdjustment, light);
			IGui.drawTexture(matrices, renderTypeBuffer, "mtr:textures/signs/stripe.png", 0.046875F, -0.0375F, positionScaled + 0.874F, 0.109375F, 0, positionScaled + 0.874F, 1, 0.4F, 0, 1.3F, colorAdjustment, light);
		}
		matrices.pop();

		if (renderStage == RenderStage.INTERIOR) {
			IGui.drawTexture(matrices, renderTypeBuffer, "mtr:textures/signs/stripe.png", -1.237F, -0.88F, positionScaled - 0.875F - doorRight, -1.237F, -0.13F, positionScaled - 0.8125F - doorRight, 1, 0.9F, 0, 16.9F, colorAdjustment, light);
			IGui.drawTexture(matrices, renderTypeBuffer, "mtr:textures/signs/stripe.png", -1.237F, -0.88F, positionScaled + 0.8125F + doorRight, -1.237F, -0.13F, positionScaled + 0.875F + doorRight, 0, 0.9F, 1, 16.9F, colorAdjustment, light);
			IGui.drawTexture(matrices, renderTypeBuffer, "mtr:textures/signs/stripe.png", -1.140625F, -0.88F, positionScaled - 0.8745F, -1.203125F, -0.13F, positionScaled - 0.8745F, 0, 0, 1, 16, colorAdjustment, light);
			IGui.drawTexture(matrices, renderTypeBuffer, "mtr:textures/signs/stripe.png", -1.203125F, -0.88F, positionScaled + 0.8745F, -1.140625F, -0.13F, positionScaled + 0.8745F, 1, 0, 0, 16, colorAdjustment, light);
		}
	}

	@Override
	protected int[] getWindowPositions() {
		return new int[] { -96, -32, 32, 96 };
	}

	@Override
	protected int[] getDoorPositions() {
		return new int[] { -128, -64, 0, 64, 128 };
	}

	@Override
	protected int[] getEndPositions() {
		return new int[] { -160, 160 };
	}

}