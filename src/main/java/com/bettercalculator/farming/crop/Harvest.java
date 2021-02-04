package com.bettercalculator.farming.crop;

import lombok.Builder;

/**
 * Represents a single item and quantity that can be harvested from a {@link Crop}.
 * <br>
 * Some {@link Crop}s can have multiple items harvested.
 * <br>
 * (i.e. trees can result in a harvest of logs, roots, and bird nests)
 */
@Builder
public class Harvest
{
	/** The Item ID of the item that was harvested. */
	int itemID;
	/** The number of items harvested. */
	int quantity;
	/** Indicates that this harvest is potentially different every time. */
	@Builder.Default
	boolean isVariableHarvest = false;
	/** The minimum amount of items that can be harvested. */
	@Builder.Default
	int minimumHarvest = 0;
	/** The maximum amount of items that can be harvested. */
	@Builder.Default
	int maximumHarvest = 0;

	public Harvest(int itemID, int quantity)
	{
		this.itemID = itemID;
		this.quantity = quantity;
		this.isVariableHarvest = false;
		this.minimumHarvest = maximumHarvest = quantity;
	}
}
