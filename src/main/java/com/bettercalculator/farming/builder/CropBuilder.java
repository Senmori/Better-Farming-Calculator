package com.bettercalculator.farming.builder;

import com.bettercalculator.farming.crop.Crop;
import com.bettercalculator.farming.crop.CropExperience;
import com.bettercalculator.farming.crop.CropType;
import com.bettercalculator.farming.crop.FarmingCrop;
import com.bettercalculator.farming.crop.GrowthCycle;
import com.bettercalculator.farming.crop.Harvest;
import com.bettercalculator.farming.crop.Seed;
import com.bettercalculator.farming.util.GrowthTiming;
import com.bettercalculator.farming.util.SeedType;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class CropBuilder
{
	private final CropType cropType;
	private final Seed seed;
	private final GrowthCycle growthCycle;
	private final CropExperience cropExperience;
	private final List<Harvest> harvestList = new ArrayList<>();
	private final double diseaseChance;

	private CropBuilder(Builder builder)
	{
		this.cropType = builder.getCropType();
		this.seed = builder.getSeed();
		this.growthCycle = builder.getGrowthCycle();
		this.cropExperience = builder.getCropExperience();
		this.harvestList.addAll(builder.getHarvestList());
		this.diseaseChance = builder.getDiseaseChance();
	}

	public static CropTypeBuilder builder()
	{
		return new Builder();
	}

	public interface CropTypeBuilder {
		SeedBuilder cropType(CropType cropType);
	}

	public interface SeedBuilder {
		GrowthCycleBuilder seed(Seed seed);

		GrowthCycleBuilder seed(SeedType seedType);
	}

	public interface GrowthCycleBuilder {
		CropExperienceBuilder growthCycle(GrowthCycle growthCycle);

		CropExperienceBuilder growthCycle(GrowthTiming growthTiming, int numberofGrowthCycles);
	}

	public interface CropExperienceBuilder {
		BuildStep cropExperience(CropExperience cropExperience);

		BuildStep cropExperience(int plantingExperience, int harvestExperience);

		BuildStep cropExperience(int plantingExperience, int harvestExperience, int defaultYield);
	}

	public interface BuildStep {
		Crop build();
	}

	@Getter
	public static class Builder implements CropTypeBuilder, SeedBuilder, GrowthCycleBuilder, CropExperienceBuilder, BuildStep
	{
		private CropType cropType;
		private Seed seed;
		private GrowthCycle growthCycle;
		private CropExperience cropExperience;
		private List<Harvest> harvestList = new ArrayList<>();
		private double diseaseChance;

		private Builder() {}

		@Override
		public SeedBuilder cropType(CropType cropType)
		{
			this.cropType = cropType;
			return this;
		}

		@Override
		public GrowthCycleBuilder seed(Seed seed)
		{
			this.seed = seed;
			return this;
		}

		@Override
		public GrowthCycleBuilder seed(SeedType seedType)
		{
			this.seed = new Seed(seedType.getItemID(), seedType.getRequiredFarmingLevel());
			return this;
		}

		@Override
		public CropExperienceBuilder growthCycle(GrowthCycle growthCycle)
		{
			this.growthCycle = growthCycle;
			return this;
		}

		@Override
		public CropExperienceBuilder growthCycle(GrowthTiming growthTiming, int numberofGrowthCycles)
		{
			this.growthCycle = GrowthCycle.of(growthTiming.getLength(), numberofGrowthCycles);
			return this;
		}

		@Override
		public BuildStep cropExperience(CropExperience cropExperience)
		{
			this.cropExperience = cropExperience;
			return this;
		}

		@Override
		public BuildStep cropExperience(int plantingExperience, int harvestExperience)
		{
			this.cropExperience = CropExperience.builder()
				.plantingExperience(plantingExperience)
				.harvestExperience(harvestExperience)
				.build();
			return this;
		}

		@Override
		public BuildStep cropExperience(int plantingExperience, int harvestExperience, int defaultYield)
		{
			this.cropExperience = CropExperience.builder()
				.plantingExperience(plantingExperience)
				.harvestExperience(harvestExperience)
				.defaultYield(defaultYield)
				.build();
			return this;
		}

		@Override
		public Crop build()
		{
			CropBuilder cropBuilder = new CropBuilder(this);
			return new FarmingCrop(cropBuilder);
		}
	}

}
