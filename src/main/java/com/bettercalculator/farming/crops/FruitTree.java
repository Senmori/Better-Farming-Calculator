package com.bettercalculator.farming.crops;

import com.bettercalculator.farming.crop.Crop;
import com.bettercalculator.farming.crop.CropExperience;
import com.bettercalculator.farming.crop.CropType;
import com.bettercalculator.farming.crop.CropYield;
import com.bettercalculator.farming.crop.GrowthCycle;
import com.bettercalculator.farming.crop.GrowthTiming;
import com.bettercalculator.farming.crop.SeedType;

public enum FruitTree implements CropProvider
{
	APPLE(SeedType.APPLE, 6, 22, 8.5, 1199.5),
	BANANA(SeedType.BANANA, 6, 28, 10.5, 1750.5),
	ORANGE(SeedType.ORANGE, 6, 35.5, 13.5, 2470.5),
	CURRY(SeedType.CURRY, 6, 40, 15, 2906.9),
	PINEAPPLE(SeedType.PINEAPPLE, 6, 57, 21.5, 4605.7),
	PAPAYA(SeedType.PAPAYA, 6, 72, 27, 6146.4),
	PALM_TREE(SeedType.PALM_TREE, 6, 110.5, 41.5, 10150.1),
	DRAGONFRUIT(SeedType.DRAGONFRUIT, 6, 140, 70, 17335),
	;

	private final SeedType seed;
	private final GrowthCycle growthCycle;
	private final CropExperience cropExperience;
	FruitTree(SeedType seed, int requiredCycles, double plantXP, double harvestXP, double checkHealthXP)
	{
		this.seed = seed;
		this.growthCycle = GrowthTiming._160_MINUTES.toGrowthCycle(requiredCycles);
		this.cropExperience = CropExperience.builder()
			.plantingExperience(plantXP)
			.harvestExperience(harvestXP)
			.checkHealthExperience(checkHealthXP)
			.defaultYield(CropYield.FRUIT_TREE.getAverageYield())
			.build();
	}


	@Override
	public Crop getCrop()
	{
		return buildCrop(CropType.FRUIT_TREE, seed, growthCycle, cropExperience);
	}
}
