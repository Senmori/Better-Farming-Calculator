package com.bettercalculator.farming.crops;

import com.bettercalculator.farming.crop.Crop;
import com.bettercalculator.farming.crop.CropExperience;
import com.bettercalculator.farming.crop.CropType;
import com.bettercalculator.farming.crop.CropYield;
import com.bettercalculator.farming.crop.GrowthCycle;
import com.bettercalculator.farming.crop.GrowthTiming;
import com.bettercalculator.farming.crop.SeedType;

public enum Special implements CropProvider
{
	GIANT_SEAWEED(SeedType.GIANT_SEAWEED, GrowthTiming.TEN_MINUTES, 4, 19, 0, 21, CropYield.GIANT_SEAWEED.getAverageYield()),
	GRAPES(SeedType.GRAPES, GrowthTiming.FIVE_MINUTES, 7, 31.5, 625, 40, CropYield.GRAPES.getAverageYield()),
	MUSHROOM(SeedType.MUSHROOM, GrowthTiming._40_MINUTES, 6, 61.5, 0, 57.7, CropYield.MUSHROOM.getAverageYield()),
	BELLADONNA(SeedType.BELLADONNA, GrowthTiming._80_MINUTES, 4, 91, 0, 512, 1),
	HESPORI(SeedType.HESPORI, GrowthTiming._640_MINUTES, 3, 62, 0, 12600, 1),
	;

	private final SeedType seed;
	private final GrowthCycle growthCycle;
	private final CropExperience cropExperience;
	Special(SeedType seed, GrowthTiming timing, int requiredCycles, double plantXP, double checkHealthXP, double harvestXP, int averageYield)
	{
		this.seed = seed;
		this.growthCycle = timing.toGrowthCycle(requiredCycles);
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
		return buildCrop(CropType.SPECIAL, seed, growthCycle, cropExperience);
	}
}
