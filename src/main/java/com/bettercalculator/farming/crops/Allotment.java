package com.bettercalculator.farming.crops;

import com.bettercalculator.farming.builder.CropBuilder;
import com.bettercalculator.farming.crop.Crop;
import com.bettercalculator.farming.crop.CropExperience;
import com.bettercalculator.farming.crop.CropType;
import com.bettercalculator.farming.crop.CropYield;
import com.bettercalculator.farming.crop.GrowthCycle;
import com.bettercalculator.farming.crop.GrowthTiming;
import com.bettercalculator.farming.crop.SeedType;

public enum Allotment implements CropProvider
{
	POTATO(SeedType.POTATO, 4, 8.0, 9.0),
	ONION(SeedType.ONION, 4, 9.5, 10.5),
	CABBAGE(SeedType.CABBAGE, 4, 10.0, 11.5),
	TOMATO(SeedType.TOMATO, 4, 12.5, 14),
	SWEETCORN(SeedType.SWEETCORN, 6, 17, 19),
	STRAWBERRY(SeedType.STRAWBERRY, 6, 26, 29),
	WATERMELON(SeedType.WATERMELON, 8, 48.5, 54.5),
	SNAPE_GRASS(SeedType.SNAPE_GRASS, 7, 82, 82),
	;

	private final SeedType seed;
	private final GrowthCycle growthCycle;
	private final CropExperience cropExperience;
	private final CropYield cropYield;
	Allotment(SeedType seed, int growthCycles, double plantingExp, double harvestExp)
	{
		this.seed = seed;
		this.growthCycle = GrowthTiming.TEN_MINUTES.toGrowthCycle(growthCycles);
		this.cropExperience = CropExperience.builder()
			.plantingExperience(plantingExp)
			.harvestExperience(harvestExp)
			.defaultYield(CropYield.ALLOTMENT.getAverageYield())
			.build();
		this.cropYield = CropYield.ALLOTMENT;
	}

	@Override
	public Crop getCrop()
	{
		return buildCrop(CropType.ALLOTMENT, seed, growthCycle, cropExperience);
	}
}
