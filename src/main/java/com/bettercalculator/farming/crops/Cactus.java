package com.bettercalculator.farming.crops;

import com.bettercalculator.farming.crop.Crop;
import com.bettercalculator.farming.crop.CropExperience;
import com.bettercalculator.farming.crop.CropType;
import com.bettercalculator.farming.crop.CropYield;
import com.bettercalculator.farming.crop.GrowthCycle;
import com.bettercalculator.farming.crop.GrowthTiming;
import com.bettercalculator.farming.crop.SeedType;

public enum Cactus implements CropProvider
{
	CACTUS(SeedType.CACTUS, GrowthTiming._80_MINUTES, 7, 66.5, 374, 25, CropYield.CACTUS),
	POTATO_CACTUS(SeedType.POTATO_CACTUS, GrowthTiming.TEN_MINUTES, 7, 68, 230, 68, CropYield.POTATO_CACTUS)
	;


	private final SeedType seed;
	private final GrowthCycle growthCycle;
	private final CropExperience cropExperience;
	Cactus(SeedType seed, GrowthTiming timing, int cycles, double plantXp, double checkHealthXP, double harvestXP, CropYield yield)
	{
		this.seed = seed;
		this.growthCycle = timing.toGrowthCycle(cycles);
		this.cropExperience = CropExperience.builder()
			.plantingExperience(plantXp)
			.harvestExperience(harvestXP)
			.checkHealthExperience(checkHealthXP)
			.defaultYield(yield.getAverageYield())
			.build();
	}

	@Override
	public Crop getCrop()
	{
		return buildCrop(CropType.CACTUS, seed, growthCycle, cropExperience);
	}
}
