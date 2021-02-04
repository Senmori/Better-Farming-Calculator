package com.bettercalculator.farming.crop;

/**
 * This represents the different types of crops that can be planted.
 * <br>
 * This does not exactly match the OSRS Wiki; however it was done this way
 * in order to give the most flexibility for categorizing crops.
 */
public enum CropType
{
	/** Crops grown in an allotment patch*/
	ALLOTMENT,
	/** Crosp grown in a Hops patch. */
	HOPS,
	/** Crops grown in a Tree patch. */
	TREE,
	/** Crops grown in a Fruit Tree patch. */
	FRUIT_TREE,
	/** Crops grown in a Bush patch. */
	BUSH,
	/** Crops grown in a flower patch. This includes scarecrows. */
	FLOWER,
	/** Crops grown in a Herb patch. */
	HERB,
	/** Crops grown in a Hardwood Tree patch. */
	HARDWOOD_TREE,
	/** Trees that don't fit the other categories but have their own respective patch. */
	SPECIAL_TREE,
	/** Crops grown in an Anima patch. */
	ANIMA,
	/** Crops grown in a cactus patch. */
	CACTUS,
	/**
	 * Crops that don't fit any other category but have their own respective patches.
	 * This category does not include special trees.
	 * */
	SPECIAL,
	/** Crops that don't fity ANY category. */
	UNKNOWN,
	;


	public boolean matchesCrop(Crop crop)
	{
		return crop.getCropType() == this;
	}

	public static EmptyCrop getEmptyCrop()
	{
		return new EmptyCrop();
	}
}
