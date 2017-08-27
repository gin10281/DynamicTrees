package com.ferreusveritas.dynamictrees.inspectors;

import com.ferreusveritas.dynamictrees.TreeHelper;
import com.ferreusveritas.dynamictrees.blocks.BlockAndMeta;
import com.ferreusveritas.dynamictrees.blocks.BlockBranch;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class NodeFreezer implements INodeInspector {

	@Override
	public boolean run(World world, Block block, int x, int y, int z, ForgeDirection fromDir) {
		BlockBranch branch = TreeHelper.getBranch(block);
		if(branch != null) {
			int radius = branch.getRadius(world, x, y, z);
			if(radius == 1) {
				freezeSurroundingLeaves(world, branch, x, y, z);
			}
		}

		return true;
	}

	@Override
	public boolean returnRun(World world, Block block, int x, int y, int z, ForgeDirection fromDir) {
		return false;
	}

	public void freezeSurroundingLeaves(World world, BlockBranch branch, int x, int y, int z) {
		BlockAndMeta primLeaves = branch.getTree().getPrimitiveLeaves();
		int noDecayBits = 0x04;

		for(int iz = z - 3; iz <= z + 3; iz++) {
			for(int iy = y - 3; iy <= y + 3; iy++) {
				for(int ix = x - 3; ix <= x + 3; ix++) {
					if(branch.getTree().isCompatibleGrowingLeaves(world, ix, iy, iz)) {
						world.setBlock(ix, iy, iz, primLeaves.getBlock(), primLeaves.getMeta() | noDecayBits, 3);
					}
				}
			}
		}
	}

}
