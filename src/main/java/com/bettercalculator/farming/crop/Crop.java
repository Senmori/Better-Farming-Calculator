package com.bettercalculator.farming.crop;

import com.bettercalculator.farming.GrowthTick;
import java.util.Collection;

/**
 * Represents a crop that is currently planted in a farming patch.
 */
public interface Crop
{
	/**
	 * @return the {@link CropType} of this crop.
	 */
	CropType getCropType();

	/**
	 * @return the {@link Seed} required to plant this crop.
	 */
	Seed getSeed();

	/**
	 * @return the {@link GrowthCycle} of this crop.
	 */
	GrowthCycle getGrowthCycle();

	/**
	 * @return the {@link CropExperience} for when this crop is planted/harvested.
	 */
	CropExperience getCropExperience();

	/**
	 * @return the potential {@link Harvest} when this crop is fully grown.
	 */
	Collection<Harvest> getHarvest();

	/**
	 * @return the standard chance is crop will become diseased each growth cycle.
	 */
	double getStandardDiseaseChance();

	/**
	 * Check if the crop is fully grown according to its {@link GrowthCycle}
	 *
	 * @param tick the {@link GrowthTick}
	 * @return true if the crop is fully grown (more time has passed than it's total growth cycle time)
	 */
	default boolean isFullyGrown(GrowthTick tick)
	{
		return getGrowthCycle().isFullyGrown(tick);
	}

	/**
	 * Get the current growth stage given the {@link GrowthTick}
	 *
	 * @param tick the {@link GrowthTick} the crop is currently on
	 * @return the current growth stage for the given {@link GrowthTick)} since the crop was planted
	 */
	default int getGrowthStage(GrowthTick tick)
	{
		return getGrowthCycle().getGrowthStage(tick);
	}
}