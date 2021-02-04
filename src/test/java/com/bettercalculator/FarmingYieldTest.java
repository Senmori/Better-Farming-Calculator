package com.bettercalculator;

public class FarmingYieldTest
{
	private static final double levelOneChance = 6.5D;
	private static final double level99Chance = 12.5D;

	private static final double magicSecateurBonus = 0.1;
	private static final double farmingCapeBonus = 0.05;

	private static final double kandarinDiaryMed  = 0.05;
	private static final double kandarinDiaryHard  = 0.10;
	private static final double kandarinDiaryElite  = 0.15;
	private static final double kourendDiaryHard = 0.05;
	private static final double attasSeedBonus = 0.05;

	private static final int farmingLevel = 71;
	private static final double itemBonus = 0.0D;
	private static final double diaryBonus = 0.0D;
	private static final int lives = 6;

	public static void main(String[] args)
	{
		double levelOneChance = levelOneChance(farmingLevel);
		System.out.println("Level Once Chance: " + levelOneChance);
		double level99Chance = level99Chance(farmingLevel);
		System.out.println("Level 99 Chance: " + level99Chance);
		double numerator = (levelOneChance + level99Chance) * (1 + itemBonus) * (1 + diaryBonus) + 1;
		System.out.println("Numerator: " + numerator);
		double chanceToSave = numerator / 256;
		System.out.println("Chance: " + chanceToSave);
		double expectedYield = (lives / (1 - chanceToSave));
		System.out.println("Yield: " + expectedYield + " -- Chance: " + chanceToSave);
	}
	public static double getChanceToSave(int farmingLevel, double itemBonus, double diaryBonus)
	{
		double levelOneChance = levelOneChance(farmingLevel);
		double level99Chance = level99Chance(farmingLevel);
		double numerator = (levelOneChance + level99Chance) * (1 + itemBonus) * (1 + diaryBonus) + 1;
		return numerator / 256;
	}

	private static double level99Chance(int farmingLevel)
	{
		double numerator = level99Chance * (farmingLevel -1);
		return numerator / 98;
	}

	private static double levelOneChance(int farmingLevel)
	{
		double numerator = levelOneChance * (99 - farmingLevel);
		return numerator / 98;
	}

	public static double getExpectedYield(double chanceToSave, int lives)
	{
		return (lives / (1 - chanceToSave));
	}
}
