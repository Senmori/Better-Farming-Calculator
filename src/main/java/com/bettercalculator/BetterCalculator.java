package com.bettercalculator;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
	name = BetterCalculator.PLUGIN_NAME,
	description = "Helps plan out your XP gains better"
)
public class BetterCalculator extends Plugin
{
	public static final String PLUGIN_NAME = "Better XP Calculator";
	public static final String CONFIG_GROUP = "betterCalc";

	@Inject
	private Client client;

	@Inject
	private BetterCalculatorConfig config;

	@Override
	protected void startUp() throws Exception
	{

	}

	@Override
	protected void shutDown() throws Exception
	{

	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{

	}

	@Provides
	BetterCalculatorConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(BetterCalculatorConfig.class);
	}
}
