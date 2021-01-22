package com.spdfnerd.mtr.block;

import com.spdfnerd.mtr.setup.Registration;
import com.spdfnerd.mtr.tileentity.APGGlassTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.SlabType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockAPGGlass extends BlockPSDAPGGlassBase implements ITileEntityProvider, IPropagateBlock {

	@Override
	public Item asItem() {
		return Registration.APG_GLASS_ITEM.get();
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (IBlock.getStatePropertySafe(state, HALF) == SlabType.TOP) {
			return IBlock.checkHoldingBrush(world, player, () -> {
				world.setBlockState(pos, state.func_235896_a_(PROPAGATE_PROPERTY));
				propagate(world, pos, IBlock.getStatePropertySafe(state, HORIZONTAL_FACING).rotateY());
				propagate(world, pos, IBlock.getStatePropertySafe(state, HORIZONTAL_FACING).rotateYCCW());
			});
		} else {
			return super.onBlockActivated(state, world, pos, player, hand, hit);
		}
	}

	@Nullable
	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) {
		return new APGGlassTileEntity();
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(HORIZONTAL_FACING, HALF, SIDE_EXTENDED, PROPAGATE_PROPERTY);
	}

}
