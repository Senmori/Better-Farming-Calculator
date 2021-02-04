package com.bettercalculator.farming.builder;

import com.bettercalculator.farming.crop.Crop;
import com.bettercalculator.farming.crop.CropExperience;
import com.bettercalculator.farming.crop.CropType;
import com.bettercalculator.farming.crop.FarmingCrop;
import com.bettercalculator.farming.crop.GrowthCycle;
import com.bettercalculator.farming.crop.Harvest;
import com.bettercalculator.farming.crop.Seed;
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

		GrowthCycleBuilder seed(int itemID, int requiredFarmingLevel);
	}

	public interface GrowthCycleBuilder {
		CropExperienceBuilder growthCycle(GrowthCycle growthCycle);

		CropExperienceBuilder growthCycle(int numberofGrowthCycles, int growthCycleLength);
	}

	public interface CropExperienceBuilder {
		HarvestBuilder cropExperience(CropExperience cropExperience);

		HarvestBuilder cropExperience(int plantingExperience, int harvestExperience);

		HarvestBuilder cropExperience(int plantingExperience, int harvestExperience, int defaultYield);
	}

	public interface HarvestBuilder {

		HarvestBuilder harvest(Harvest harvest);

		HarvestBuilder harvest(int itemID, int quantity);

		HarvestBuilder harvest(int itemID, int minimumHarvest, int maximumHarvest);

		DiseaseBuilder noMoreHarvests();
	}

	public interface DiseaseBuilder {
		BuildStep diseaseChance(double diseaseChance);
	}

	public interface BuildStep {
		Crop build();
	}

	@Getter
	public static class Builder implements CropTypeBuilder, SeedBuilder, GrowthCycleBuilder, CropExperienceBuilder, HarvestBuilder, DiseaseBuilder, BuildStep
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
		public GrowthCycleBuilder seed(int itemID, int requiredFarmingLevel)
		{
			this.seed = new Seed(itemID, requiredFarmingLevel);
			return this;
		}

		@Override
		public CropExperienceBuilder growthCycle(GrowthCycle growthCycle)
		{
			this.growthCycle = growthCycle;
			return this;
		}

		@Override
		public CropExperienceBuilder growthCycle(int numberofGrowthCycles, int growthCycleLength)
		{
			this.growthCycle = new GrowthCycle(growthCycleLength, numberofGrowthCycles);
			return this;
		}

		@Override
		public HarvestBuilder cropExperience(CropExperience cropExperience)
		{
			this.cropExperience = cropExperience;
			return this;
		}

		@Override
		public HarvestBuilder cropExperience(int plantingExperience, int harvestExperience)
		{
			this.cropExperience = CropExperience.builder()
				.plantingExperience(plantingExperience)
				.harvestExperience(harvestExperience)
				.build();
			return this;
		}

		@Override
		public HarvestBuilder cropExperience(int plantingExperience, int harvestExperience, int defaultYield)
		{
			this.cropExperience = CropExperience.builder()
				.plantingExperience(plantingExperience)
				.harvestExperience(harvestExperience)
				.defaultYield(defaultYield)
				.build();
			return this;
		}

		@Override
		public HarvestBuilder harvest(Harvest harvest)
		{
			this.harvestList.add(harvest);
			return this;
		}

		@Override
		public HarvestBuilder harvest(int itemID, int quantity)
		{
			Harvest harvest = Harvest.builder()
				.itemID(itemID)
				.quantity(quantity)
				.build();
			return this.harvest(harvest);
		}

		@Override
		public HarvestBuilder harvest(int itemID, int minimumHarvest, int maximumHarvest)
		{
			Harvest harvest = Harvest.builder()
				.itemID(itemID)
				.minimumHarvest(minimumHarvest)
				.maximumHarvest(maximumHarvest)
				.isVariableHarvest(true)
				.build();
			return this.harvest(harvest);
		}

		@Override
		public DiseaseBuilder noMoreHarvests()
		{
			return this;
		}

		@Override
		public BuildStep diseaseChance(double diseaseChance)
		{
			this.diseaseChance = diseaseChance;
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
