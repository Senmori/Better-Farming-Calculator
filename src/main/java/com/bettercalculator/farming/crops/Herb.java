package com.bettercalculator.farming.crops;

import com.bettercalculator.farming.crop.Crop;
import com.bettercalculator.farming.crop.CropExperience;
import com.bettercalculator.farming.crop.CropType;
import com.bettercalculator.farming.crop.CropYield;
import com.bettercalculator.farming.crop.GrowthCycle;
import com.bettercalculator.farming.crop.GrowthTiming;
import com.bettercalculator.farming.crop.SeedType;

public enum Herb implements CropProvider
{
	GUAM(SeedType.GUAM, 4, 11, 12.5),
	MARRENTILL(SeedType.MARRENTILL, 4, 13.5, 15),
	TARROMIN(SeedType.TARROMIN, 4, 16, 18),
	HARRALANDER(SeedType.HARRALANDER, 4, 21.5, 24),
	GOUT_TUBER(SeedType.GOUT_TUBER, 4, 105, 45),
	RANARR(SeedType.RANARR, 4, 27, 30.5),
	TOADFLAX(SeedType.TOADFLAX, 4, 34, 38.5),
	IRIT(SeedType.IRIT, 4, 43, 48.5),
	AVANTOE(SeedType.AVANTOE, 4, 54.5, 61.5),
	KWUARM(SeedType.KWUARM, 4, 69, 78),
	SNAPDRAGON(SeedType.SNAPDRAGON, 4, 87.5, 98.5),
	CADANTINE(SeedType.CADANTINE, 4, 106.5, 120),
	LANTADYME(SeedType.LANTADYME, 4, 134.5, 151.5),
	DWARF_WEED(SeedType.DWARF_WEED, 4, 170.5, 192),
	TORSTOL(SeedType.TORSTOL, 4, 199.5, 224.5),
	;

	private final SeedType seed;
	private final GrowthCycle growthCycle;
	private final CropExperience cropExperience;
	Herb(SeedType seed, int requiredCycles, double plantXP, double harvestXP)
	{
		this.seed = seed;
		this.growthCycle = GrowthTiming._20_MINUTES.toGrowthCycle(requiredCycles);
		this.cropExperience = CropExperience.builder()
			.plantingExperience(plantXP)
			.harvestExperience(harvestXP)
			.defaultYield(CropYield.HERB.getAverageYield())
			.build();
	}

	@Override
	public Crop getCrop()
	{
		return buildCrop(CropType.HERB, seed, growthCycle, cropExperience);
	}
}
