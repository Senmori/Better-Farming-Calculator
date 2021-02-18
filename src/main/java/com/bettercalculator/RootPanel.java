package com.bettercalculator;

import com.bettercalculator.ui.InputPanel;
import java.awt.BorderLayout;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.PluginPanel;

@Slf4j
public class RootPanel extends PluginPanel
{
	InputPanel inputPanel;
	public RootPanel()
	{
		super();
		setBackground(ColorScheme.DARK_GRAY_COLOR);
		setLayout(new BorderLayout());

		inputPanel = new InputPanel();

		add(inputPanel, BorderLayout.NORTH);
	}
}
