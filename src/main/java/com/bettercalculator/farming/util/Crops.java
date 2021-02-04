package com.bettercalculator.farming.util;

import com.bettercalculator.farming.builder.CropBuilder;
import com.bettercalculator.farming.crop.Crop;
import com.bettercalculator.farming.crop.CropType;
import lombok.Getter;

@Getter
public final class Crops
{

	private static final Crop potatoCrop = CropBuilder.builder()
		.cropType(CropType.ALLOTMENT)
		.seed(SeedType.POTATO)
		.growthCycle(GrowthTiming.TEN_MINUTES, 4)
		.cropExperience(8, 9, 10)
		.build();
}
