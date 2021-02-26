package com.bettercalculator.ui.panel;

import com.bettercalculator.BetterCalculator;
import com.bettercalculator.ui.RootPluginPanel;
import lombok.AccessLevel;
import lombok.Getter;

public abstract class CalculatorScreen extends FixedWidthPanel
{
	@Getter(AccessLevel.PROTECTED)
	private final RootPluginPanel rootPluginPanel;
	protected CalculatorScreen(RootPluginPanel rootPanel)
	{
		super();
		this.rootPluginPanel = rootPanel;
	}

	public abstract void onHide();

	public abstract void onDisplay();

	public abstract void update(BetterCalculator plugin);
}
