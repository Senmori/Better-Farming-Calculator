package com.bettercalculator.farming.patch;

import com.bettercalculator.farming.crop.Compost;
import com.bettercalculator.farming.crop.Crop;
import com.bettercalculator.farming.crop.CropType;
import com.bettercalculator.farming.crop.EmptyCrop;
import javax.annotation.Nonnull;

public class AbstractPatch implements FarmingPatch
{
	private final CropType cropType;
	private Crop currentCrop = new EmptyCrop();
	private Compost currentCompost = Compost.NONE;
	private boolean diseased = false;

	protected AbstractPatch(CropType cropType)
	{
		this.cropType = cropType;
	}

	@Nonnull
	@Override
	public CropType getSupportedCropType()
	{
		return cropType;
	}

	@Nonnull
	@Override
	public Crop getCurrentCrop()
	{
		return currentCrop;
	}

	@Override
	public void plantCrop(Crop crop)
	{
		if (crop == null) crop = CropType.getEmptyCrop();
		if (crop.getCropType() != getSupportedCropType())
		{
			return;
		}
		this.currentCrop = crop;
	}

	@Override
	public boolean supportsCompost(Compost compost)
	{
		return true;
	}

	@Override
	public void addCompost(Compost compost)
	{
		if (compost == null) compost = Compost.NONE;
		if (!supportsCompost(compost))
		{
			return;
		}
		this.currentCompost = compost;
	}

	@Nonnull
	@Override
	public Compost getCompostUsed()
	{
		return currentCompost;
	}

	@Override
	public boolean isCropDiseased()
	{
		return diseased;
	}

	@Override
	public void setDiseased(boolean diseased)
	{
		this.diseased = diseased;
	}

	@Override
	public double getDiseaseChance()
	{
		return getCurrentCrop().getStandardDiseaseChance() * getCompostUsed().getDiseaseReductionRate();
	}
}
