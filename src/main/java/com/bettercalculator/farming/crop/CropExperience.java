package com.bettercalculator.farming.crop;

import lombok.Builder;
import lombok.Value;

/**
 * Represents the experience a {@link Crop} will give.
 */
@Value
@Builder
public class CropExperience
{
	/** The experience gained when planting this crop. */
	double plantingExperience;
	/** The experience gained when harvesting this crop. This experience is per item harvested. */
	double harvestExperience;
	/**
	 * The default (lowest) number of items that can be harvested if this item has a variable yield.
	 * If this crop has no variable yield, this is the number of items harvested.
	 */
	int defaultYield;


	/**
	 * Get the amount of experience gained when harvesting a certain yield.
	 *
	 * @param yield the number of items harvested
	 * @return the total amount of experience to be gained
	 */
	public double getExperience(int yield)
	{
		return getPlantingExperience() + (yield * getHarvestExperience());
	}

	/**
	 * Get the total amount of experience gained with the default yield for a crop.
	 * <br>
	 * Most crops have a defined minimum number of crops for each yield.
	 *
	 * @return the experience gained for the default yield for this crop.
	 */
	public double getExperience()
	{
		return getExperience(getDefaultYield());
	}
}
