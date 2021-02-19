package com.bettercalculator.farming.crop;

import lombok.Getter;

//TODO: Move this to config at some point
@Getter
public enum CropYield
{
	ALLOTMENT(10),
	FLOWER(1),
	WOAD(3),
	LIMPWURT(3),
	HERB(7),
	HOPS(10),
	BUSH(4),
	FRUIT_TREE(6),
	GIANT_SEAWEED(23),
	GRAPES(10),
	MUSHROOM(6),
	CACTUS(3),
	POTATO_CACTUS(6),
	NONE(0),
	;

	private final int averageYield;
	CropYield(int averageYield)
	{
		this.averageYield = averageYield;
	}
}
