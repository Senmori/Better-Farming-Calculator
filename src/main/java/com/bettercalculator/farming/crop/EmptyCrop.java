package com.bettercalculator.farming.crop;

import com.google.common.collect.ImmutableList;
import java.util.Collection;

/**
 * Represents an empty farming crop which rewards no experience nor harvest.
 * This is used to indicate nothing is planted in a farming patch.
 */
public final class EmptyCrop implements Crop
{
	@Override
	public CropType getCropType()
	{
		return CropType.UNKNOWN;
	}

	@Override
	public SeedType getSeedType()
	{
		return SeedType.UNKNOWN;
	}

	@Override
	public GrowthCycle getGrowthCycle()
	{
		return GrowthCycle.of(GrowthTiming.UNKNOWN, 1);
	}

	@Override
	public CropExperience getCropExperience()
	{
		return CropExperience.builder()
			.plantingExperience(0.0D)
			.harvestExperience(0.0D)
			.checkHealthExperience(0.0D)
			.defaultYield(0)
			.build();
	}

	@Override
	public Collection<Harvest> getHarvest()
	{
		return ImmutableList.of(Harvest.builder().itemID(-1).quantity(0).build());
	}

	@Override
	public double getDiseaseChance()
	{
		return 0.0D;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null) return false;
		return obj instanceof EmptyCrop;
	}
}
