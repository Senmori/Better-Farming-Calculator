package com.bettercalculator.farming.crops;

import com.bettercalculator.farming.builder.CropBuilder;
import com.bettercalculator.farming.crop.Crop;
import com.bettercalculator.farming.crop.CropExperience;
import com.bettercalculator.farming.crop.CropType;
import com.bettercalculator.farming.crop.GrowthCycle;
import com.bettercalculator.farming.crop.GrowthTiming;
import com.bettercalculator.farming.crop.SeedType;

public enum Tree implements CropProvider
{
	ACORN(SeedType.ACORN, 5, 14, 467.3),
	WILLOW(SeedType.WILLOW, 7, 25, 1456.5),
	MAPLE(SeedType.MAPLE, 8, 45, 3403.4),
	YEW(SeedType.YEW, 10, 81, 7069.9),
	MAGIC(SeedType.MAGIC, 12, 145.5, 13768.3),
	;

	private final SeedType seed;
	private final GrowthCycle growthCycle;
	private final CropExperience cropExperience;
	Tree(SeedType seed, int requiredCycles, double plantXP, double checkHealthXP)
	{
		this.seed = seed;
		this.growthCycle = GrowthTiming._40_MINUTES.toGrowthCycle(requiredCycles);
		this.cropExperience = CropExperience.builder()
			.plantingExperience(plantXP)
			.checkHealthExperience(checkHealthXP)
			.defaultYield(0)
			.build();
	}
	@Override
	public Crop getCrop()
	{
		return buildCrop(CropType.TREE, seed, growthCycle, cropExperience);
	}
}
