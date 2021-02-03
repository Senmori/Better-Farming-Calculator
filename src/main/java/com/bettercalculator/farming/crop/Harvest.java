package com.bettercalculator.farming.crop;

import lombok.Value;

/**
 * Represents a single item and quantity that can be harvested from a {@link Crop}.
 * <br>
 * Some {@link Crop}s can have multiple items harvested.
 * <br>
 * (i.e. trees can result in a harvest of logs, roots, and bird nests)
 */
@Value
public class Harvest
{
	/** The Item ID of the item that was harvested. */
	int itemID;
	/** The number of items harvested. */
	int quantity;
}
