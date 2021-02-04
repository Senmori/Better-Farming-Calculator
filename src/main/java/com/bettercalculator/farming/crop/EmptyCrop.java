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
	public Seed getSeed()
	{
		return new Seed(-1, Integer.MAX_VALUE);
	}

	@Override
	public GrowthCycle getGrowthCycle()
	{
		return GrowthCycle.of(Integer.MAX_VALUE, 1);
	}

	@Override
	public CropExperience getCropExperience()
	{
		return new CropExperience(0, 0, 0);
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
		if (!(obj instanceof EmptyCrop)) return false;;
		return true;
	}
}
