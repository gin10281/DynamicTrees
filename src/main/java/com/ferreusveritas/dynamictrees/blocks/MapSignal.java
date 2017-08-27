package com.ferreusveritas.dynamictrees.blocks;

import java.util.ArrayList;

import com.ferreusveritas.dynamictrees.inspectors.INodeInspector;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class MapSignal {

	protected ArrayList<INodeInspector> nodeInspectors;

	public int rootX;
	public int rootY;
	public int rootZ;
	public int depth;

	ForgeDirection localRootDir;

	public boolean overflow;
	public boolean found;

	public MapSignal() {
		localRootDir = ForgeDirection.UNKNOWN;
		nodeInspectors = new ArrayList<INodeInspector>();
	}

	public MapSignal(INodeInspector ... nis) {
		this();

		for(INodeInspector ni: nis) {
			nodeInspectors.add(ni);
		}
	}

	public boolean run(World world, Block block, int x, int y, int z, ForgeDirection fromDir) {
		for(INodeInspector inspector: nodeInspectors) {
			inspector.run(world, block, x, y, z, fromDir);
		}
		return false;
	}

	public boolean returnRun(World world, Block block, int x, int y, int z, ForgeDirection fromDir) {
		for(INodeInspector inspector: nodeInspectors) {
			inspector.returnRun(world, block, x, y, z, fromDir);
		}
		return false;
	}

	public ArrayList<INodeInspector> getInspectors() {
		return nodeInspectors;
	}
}
