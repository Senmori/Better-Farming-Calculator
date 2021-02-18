package com.bettercalculator.ui;

import com.bettercalculator.BetterCalculator;
import com.bettercalculator.ui.panel.CalculatorScreen;
import com.bettercalculator.ui.panel.InputPanel;
import com.bettercalculator.ui.panel.TabSelector;
import java.awt.BorderLayout;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Skill;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.PluginPanel;

@Slf4j
public class RootPluginPanel extends PluginPanel
{
	private final TabSelector tabSelector;
	@Getter
	private final InputPanel inputPanel;

	private final BetterCalculator plugin;

	public RootPluginPanel(BetterCalculator plugin)
	{
		super(false);
		this.plugin = plugin;
		setBackground(ColorScheme.DARK_GRAY_COLOR);
		setLayout(new BorderLayout());
		this.tabSelector = new TabSelector(plugin);
		inputPanel = new InputPanel(this);

		add(inputPanel, BorderLayout.NORTH);
		add(tabSelector, BorderLayout.CENTER);
	}

	public void update()
	{
		CalculatorScreen currentScreen = tabSelector.getCurrentScreen();
		if (currentScreen != null)
		{
			currentScreen.update();
		}
	}

	public <T extends CalculatorScreen> T registerScreen(Skill skill, T screen)
	{
		tabSelector.addSelection(skill, screen);
		revalidate();
		repaint();
		return screen;
	}

}
