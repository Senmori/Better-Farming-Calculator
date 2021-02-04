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
		return new GrowthCycle(1, Integer.MAX_VALUE);
	}

	@Override
	public CropExperience getCropExperience()
	{
		return new CropExperience(0, 0, 0);
	}

	@Override
	public Collection<Harvest> getHarvest()
	{
		return ImmutableList.of(new Harvest(-1, 0));
	}

	@Override
	public double getStandardDiseaseChance()
	{
		return 0.0D;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null) return false;
		if (!(obj instanceof EmptyCrop)) return false;
		EmptyCrop crop = (EmptyCrop) obj;
		return crop.getClass() == EmptyCrop.class;
	}
}
