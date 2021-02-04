package com.bettercalculator.farming.crop;

import com.bettercalculator.farming.builder.CropBuilder;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.ToString;

@ToString
public class FarmingCrop implements Crop
{
	private final CropType cropType;
	private final Seed seed;
	private final GrowthCycle growthCycle;
	private final CropExperience cropExperience;
	private final List<Harvest> harvestList = new ArrayList<>();
	private final double diseaseChance;

	public FarmingCrop(CropBuilder builder)
	{
		this.cropType = builder.getCropType();
		this.seed = builder.getSeed();
		this.growthCycle = builder.getGrowthCycle();
		this.cropExperience = builder.getCropExperience();
		this.harvestList.addAll(builder.getHarvestList());
		this.diseaseChance = builder.getDiseaseChance();
	}

	@Override
	public CropType getCropType()
	{
		return cropType;
	}

	@Override
	public SeedType getSeedType()
	{
		return seed;
	}

	@Override
	public GrowthCycle getGrowthCycle()
	{
		return growthCycle;
	}

	@Override
	public CropExperience getCropExperience()
	{
		return cropExperience;
	}

	@Override
	public Collection<Harvest> getHarvest()
	{
		return Lists.newArrayList(harvestList);
	}

	@Override
	public double getDiseaseChance()
	{
		return diseaseChance;
	}
}
