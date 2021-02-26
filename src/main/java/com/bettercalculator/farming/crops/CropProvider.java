package com.bettercalculator.farming.crops;

import com.bettercalculator.farming.builder.CropBuilder;
import com.bettercalculator.farming.crop.Crop;
import com.bettercalculator.farming.crop.CropExperience;
import com.bettercalculator.farming.crop.CropType;
import com.bettercalculator.farming.crop.GrowthCycle;
import com.bettercalculator.farming.crop.SeedType;

public interface CropProvider
{
	Crop getCrop();


	default CropType getCropType()
	{
		return getCrop().getCropType();
	}

	default Crop buildCrop(CropType cropType, SeedType seed, GrowthCycle growthCycle, CropExperience cropExperience)
	{
		return CropBuilder.builder()
			.cropType(cropType)
			.seed(seed)
			.growthCycle(growthCycle)
			.cropExperience(cropExperience)
			.build();
	}
}
