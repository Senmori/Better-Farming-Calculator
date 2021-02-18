package com.bettercalculator.farming.crops;

import com.bettercalculator.farming.crop.Crop;
import com.bettercalculator.farming.crop.CropExperience;
import com.bettercalculator.farming.crop.CropType;
import com.bettercalculator.farming.crop.CropYield;
import com.bettercalculator.farming.crop.GrowthCycle;
import com.bettercalculator.farming.crop.GrowthTiming;
import com.bettercalculator.farming.crop.SeedType;

public enum Bush implements CropProvider
{
	REDBERRY(SeedType.REDBERRY, 5, 11.5, 64, 4.5),
	CADAVABERRY(SeedType.CADAVABERRY, 6, 18, 102.5, 7),
	DWELLBERRY(SeedType.DWELLBERRY, 7, 31.5, 177.5, 12),
	JANGERBERRY(SeedType.JANGERBERRY, 8, 50.5, 284.5, 19),
	WHITEBERRY(SeedType.WHITEBERRY, 8, 78, 437.5, 29),
	POISON_IVY_BERRY(SeedType.POISON_IVY_BERRY, 8, 120, 675, 45)
	;

	private final SeedType seed;
	private final GrowthCycle growthCycle;
	private final CropExperience cropExperience;
	Bush(SeedType seed, int requiredCycles, double plantExp, double checkHealthExp, double harvestExp)
	{
		this.seed = seed;
		this.growthCycle = GrowthTiming._20_MINUTES.toGrowthCycle(requiredCycles);
		this.cropExperience = CropExperience.builder()
			.plantingExperience(plantExp)
			.harvestExperience(harvestExp)
			.checkHealthExperience(checkHealthExp)
			.defaultYield(CropYield.BUSH.getAverageYield())
			.build();
	}

	@Override
	public Crop getCrop()
	{
		return buildCrop(CropType.BUSH, seed, growthCycle, cropExperience);
	}
}
