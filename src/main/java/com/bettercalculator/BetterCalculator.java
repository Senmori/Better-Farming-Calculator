package com.bettercalculator;

import com.bettercalculator.farming.builder.CropBuilder;
import com.bettercalculator.farming.crop.Crop;
import com.bettercalculator.farming.crop.CropType;
import com.bettercalculator.farming.crop.GrowthTiming;
import com.bettercalculator.farming.crop.SeedType;
import com.google.inject.Provides;
import java.awt.image.BufferedImage;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.EventBus;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;

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

	@Inject
	private ConfigManager configManager;

	@Inject
	private ClientToolbar clientToolbar;

	@Inject
	private ClientThread clientThread;

	@Inject
	private EventBus eventBus;

	@Getter
	@Inject
	@Named("developerMode")
	private boolean developerMode;

	private RootPanel rootPanel;
	private NavigationButton navButton;

	@Override
	protected void startUp()
	{
		rootPanel = new RootPanel();

		BufferedImage icon = Icon.PANEL_ICON.getImage();
		//icon = ImageUtil.resizeImage(icon, 16, 16);

		navButton = NavigationButton.builder()
			.tooltip(PLUGIN_NAME)
			.icon(icon)
			.priority(7)
			.panel(rootPanel)
			.build();

		clientToolbar.addNavigation(navButton);

		Crop crop = CropBuilder.builder()
			.cropType(CropType.TREE)
			.seed(SeedType.YEW)
			.growthCycle(GrowthTiming._80_MINUTES, 8)
			.cropExperience(81, 7000)
			.build();
	}

	@Override
	protected void shutDown()
	{
		clientToolbar.removeNavigation(navButton);
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
