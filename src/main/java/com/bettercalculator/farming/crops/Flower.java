package com.bettercalculator.farming.crops;

import com.bettercalculator.farming.crop.Crop;
import com.bettercalculator.farming.crop.CropExperience;
import com.bettercalculator.farming.crop.CropType;
import com.bettercalculator.farming.crop.CropYield;
import com.bettercalculator.farming.crop.GrowthCycle;
import com.bettercalculator.farming.crop.GrowthTiming;
import com.bettercalculator.farming.crop.SeedType;
import com.google.common.collect.Sets;
import java.util.Set;
import lombok.Getter;

public enum Flower implements CropProvider
{
	MARIGOLD(SeedType.MARIGOLD, 4, 8.5, 47, CropProtection.MARIGOLD, CropProtection.WHITE_LILY),
	ROSEMARY(SeedType.ROSEMARY, 4, 12, 66.5, CropProtection.ROSEMARY, CropProtection.WHITE_LILY),
	NASTURTIUM(SeedType.NASTURTIUM, 4, 19.5, 111, CropProtection.NASTURTIUMS, CropProtection.WHITE_LILY),
	WOAD_SEED(SeedType.WOAD, 4, 20.5, 115.5, CropYield.WOAD),
	LIMPWURT(SeedType.LIMPWURT, 4, 21.5, 120, CropYield.LIMPWURT),
	WHITE_LILY(SeedType.WHITE_LILY, 4, 42, 250, CropProtection.WHITE_LILY),
	;

	private final SeedType seed;
	private final CropExperience cropExperience;
	private final GrowthCycle growthCycle;
	@Getter
	private final Set<CropProtection> protections;
	Flower(SeedType seed, int requiredCycles, double plantExp, double harvestExp)
	{
		this(seed, requiredCycles, plantExp, harvestExp, CropYield.FLOWER);
	}

	Flower(SeedType seed, int requiredCycles, double plantExp, double harvestExp, CropProtection... protections)
	{
		this(seed, requiredCycles, plantExp, harvestExp, CropYield.FLOWER, protections);
	}

	Flower(SeedType seed, int requiredCycles, double plantExp, double harvestExp, CropYield yield)
	{
		this(seed, requiredCycles, plantExp, harvestExp, yield, CropProtection.NONE);
	}

	Flower(SeedType seed, int requiredCycles, double plantExp, double harvestExp, CropYield yield, CropProtection... cropProtection)
	{
		this.seed = seed;
		this.cropExperience = CropExperience.builder()
			.plantingExperience(plantExp)
			.harvestExperience(harvestExp)
			.defaultYield(yield.getAverageYield())
			.build();
		this.growthCycle = GrowthTiming.FIVE_MINUTES.toGrowthCycle(requiredCycles);
		this.protections = Sets.newHashSet(cropProtection);
	}

	@Override
	public Crop getCrop()
	{
		return buildCrop(CropType.FLOWER, seed, growthCycle, cropExperience);
	}
}
