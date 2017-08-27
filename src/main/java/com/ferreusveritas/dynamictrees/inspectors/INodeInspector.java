package com.ferreusveritas.dynamictrees.inspectors;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public interface INodeInspector {

	public boolean run(World world, Block block, int x, int y, int z, ForgeDirection fromDir);

	public boolean returnRun(World world, Block block, int x, int y, int z, ForgeDirection fromDir);
}
