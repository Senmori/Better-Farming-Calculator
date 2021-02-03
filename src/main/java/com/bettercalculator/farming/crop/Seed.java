package com.bettercalculator.farming.crop;

import lombok.Value;

/**
 * Represents the item needed in order to plant this crop.
 */
@Value
public class Seed
{
	/** The Item ID for the seed needed to plant this crop. This can also be a sapling for trees. */
	int itemID;
	/** The required {@link net.runelite.api.Skill#FARMING} level to plant this seed/sapling. */
	int requiredFarmingLevel;
}
