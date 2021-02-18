package com.bettercalculator.farming.crops;

import com.bettercalculator.farming.builder.CropBuilder;
import com.bettercalculator.farming.crop.Crop;
import com.bettercalculator.farming.crop.CropExperience;
import com.bettercalculator.farming.crop.CropType;
import com.bettercalculator.farming.crop.CropYield;
import com.bettercalculator.farming.crop.GrowthCycle;
import com.bettercalculator.farming.crop.GrowthTiming;
import com.bettercalculator.farming.crop.SeedType;

public enum Hops implements CropProvider
{
	BARLEY(SeedType.BARLEY, 4, 8.5, 9.5),
	HAMMERSTONE(SeedType.HAMMERSTONE, 4, 9, 10),
	ASGARNIAN(SeedType.ASGARNIAN, 5, 10.9, 12),
	JUTE(SeedType.JUTE, 5, 13, 14.5),
	YANILLIAN(SeedType.YANILLIAN, 6, 14.5, 16),
	KRANDORIAN(SeedType.KRANDORIAN, 7, 17.5, 19.5),
	WILDBLOOD(SeedType.WILDBLOOD, 8, 23, 26),
	;

	private final SeedType seed;
	private final GrowthCycle growthCycle;
	private final CropExperience cropExperience;
	Hops(SeedType seed, int requiredCycles, double plantExp, double harvestExp)
	{
		this.seed = seed;
		this.growthCycle = GrowthTiming.TEN_MINUTES.toGrowthCycle(requiredCycles);
		this.cropExperience = CropExperience.builder()
			.plantingExperience(plantExp)
			.harvestExperience(harvestExp)
			.defaultYield(CropYield.HOPS.getAverageYield())
			.build();
	}

	@Override
	public Crop getCrop()
	{
		return buildCrop(CropType.HOPS, seed, growthCycle, cropExperience);
	}
}
