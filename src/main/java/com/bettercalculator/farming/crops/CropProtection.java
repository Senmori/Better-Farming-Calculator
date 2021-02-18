package com.bettercalculator.farming.crops;

import com.bettercalculator.farming.crop.Crop;
import com.bettercalculator.farming.crop.CropType;
import com.bettercalculator.farming.crop.SeedType;
import com.google.common.collect.Sets;
import java.util.function.Predicate;

public enum CropProtection implements Predicate<Crop>
{
	MARIGOLD(CropType.ALLOTMENT, SeedType.POTATO, SeedType.ONION, SeedType.TOMATO),
	ROSEMARY(CropType.ALLOTMENT, SeedType.CABBAGE),
	SCARECROW(CropType.ALLOTMENT, SeedType.SWEETCORN),
	NASTURTIUMS(CropType.ALLOTMENT, SeedType.WATERMELON),
	WHITE_LILY(CropType.ALLOTMENT, c -> true),
	NONE(CropType.UNKNOWN, c -> false)
	;

	private final Predicate<Crop> predicate;
	CropProtection(CropType requiredType, SeedType...protectedTypes)
	{
		this.predicate = c -> c.getCropType() == requiredType && Sets.newHashSet(protectedTypes).contains(c.getSeedType());
	}

	CropProtection(CropType requiredType, Predicate<Crop> predicate)
	{
		this.predicate = c -> c.getCropType() == requiredType && predicate.test(c);
	}

	@Override
	public boolean test(Crop crop)
	{
		return predicate.test(crop);
	}
}
