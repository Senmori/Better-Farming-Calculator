package com.bettercalculator.farming.crops;

import com.bettercalculator.farming.crop.Crop;
import com.bettercalculator.farming.crop.CropExperience;
import com.bettercalculator.farming.crop.CropType;
import com.bettercalculator.farming.crop.GrowthCycle;
import com.bettercalculator.farming.crop.GrowthTiming;
import com.bettercalculator.farming.crop.SeedType;

public enum Anima implements CropProvider
{
	KRONOS(SeedType.KRONOS),
	IASOR(SeedType.IASOR),
	ATTAS(SeedType.ATTAS),
	;

	private final SeedType seed;
	private final GrowthCycle growthCycle;
	private final CropExperience cropExperience;
	Anima(SeedType seed)
	{
		this.seed = seed;
		this.growthCycle = GrowthTiming._640_MINUTES.toGrowthCycle(8);
		this.cropExperience = CropExperience.builder()
			.plantingExperience(100)
			.harvestExperience(0)
			.checkHealthExperience(0)
			.defaultYield(0)
			.build();
	}

	@Override
	public Crop getCrop()
	{
		return buildCrop(CropType.ANIMA, seed, growthCycle, cropExperience);
	}
}
