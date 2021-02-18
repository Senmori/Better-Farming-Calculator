package com.bettercalculator.farming.crops;

import com.bettercalculator.farming.crop.Crop;
import com.bettercalculator.farming.crop.CropExperience;
import com.bettercalculator.farming.crop.CropType;
import com.bettercalculator.farming.crop.GrowthCycle;
import com.bettercalculator.farming.crop.GrowthTiming;
import com.bettercalculator.farming.crop.SeedType;

public enum SpecialTree implements CropProvider
{
	CALQUAT(SeedType.CALQUAT_TREE, GrowthTiming._160_MINUTES, 8, 129.5, 12096, 48.5, 6),
	CRYSTAL(SeedType.CRYSTAL_TREE, GrowthTiming._80_MINUTES, 6, 126, 13240, 0, 0),
	SPIRIT(SeedType.SPIRIT_TREE, GrowthTiming._320_MINUTES, 12, 199.5, 19301.5, 0, 0),
	CELASTRUS(SeedType.CELASTRUS, GrowthTiming._160_MINUTES, 5, 204, 14130, 23.5, 6),
	REDWOOD(SeedType.REDWOOD, GrowthTiming._640_MINUTES, 10, 230, 22450, 0, 0),
	;

	private final SeedType seed;
	private final GrowthCycle growthCycle;
	private final CropExperience cropExperience;
	SpecialTree(SeedType seed, GrowthTiming timing, int cycles, double plantXP, double checkHealthXP, double harvestXP, int averageYield)
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
		return buildCrop(CropType.SPECIAL_TREE, seed, growthCycle, cropExperience);
	}
}
