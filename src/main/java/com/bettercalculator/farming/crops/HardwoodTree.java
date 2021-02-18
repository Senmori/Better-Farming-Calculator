package com.bettercalculator.farming.crops;

import com.bettercalculator.farming.crop.Crop;
import com.bettercalculator.farming.crop.CropExperience;
import com.bettercalculator.farming.crop.CropType;
import com.bettercalculator.farming.crop.GrowthCycle;
import com.bettercalculator.farming.crop.GrowthTiming;
import com.bettercalculator.farming.crop.SeedType;

public enum HardwoodTree implements CropProvider
{
	TEAK(SeedType.TEAK, GrowthTiming._640_MINUTES, 6, 35, 7290, 0, 0),
	MAHOGANY(SeedType.MAHOGANY, GrowthTiming._640_MINUTES, 8, 63, 15720, 0, 0),
	;

	private final SeedType seed;
	private final GrowthCycle growthCycle;
	private final CropExperience cropExperience;
	HardwoodTree(SeedType seed, GrowthTiming timing, int cycles, double plantXP, double checkHealthXP, double harvestXP, int averageYield)
	{
		this.seed = seed;
		this.growthCycle = timing.toGrowthCycle(cycles);
		this.cropExperience = CropExperience.builder()
			.plantingExperience(plantXP)
			.harvestExperience(harvestXP)
			.checkHealthExperience(checkHealthXP)
			.defaultYield(averageYield)
			.build();
	}

	@Override
	public Crop getCrop()
	{
		return buildCrop(CropType.HARDWOOD_TREE, seed, growthCycle, cropExperience);
	}
}
