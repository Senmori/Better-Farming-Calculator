package com.bettercalculator;

import com.bettercalculator.ui.RootPluginPanel;
import com.bettercalculator.ui.panel.CalculatorScreen;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

public class HerbloreCalculatorScreen extends CalculatorScreen
{
	private final JLabel label;
	public HerbloreCalculatorScreen(RootPluginPanel rootPanel)
	{
		super(rootPanel);
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(5, 0, 0, 0));

		label = new JLabel(getClass().getSimpleName() + " TEST 123");

		add(label, BorderLayout.CENTER);
	}

	@Override
	public void onHide()
	{

	}

	@Override
	public void onDisplay()
	{
		label.setText(getClass().getSimpleName() + " ON DISPLAY ");
	}

	@Override
	public void update(BetterCalculator plugin)
	{
		label.setText(getClass().getSimpleName() + " ON UPDATE ");
	}
}
