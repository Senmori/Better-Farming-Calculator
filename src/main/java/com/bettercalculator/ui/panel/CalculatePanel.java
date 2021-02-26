package com.bettercalculator.ui.panel;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import lombok.Getter;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.FontManager;

public class CalculatePanel extends JPanel
{
	@Getter
	private final JButton calculateButton;
	@Getter
	private final JButton cancelButton;
	public CalculatePanel()
	{
		super();
		setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));
		setBorder(new EmptyBorder(3, 0, 0, 0));
		setBackground(ColorScheme.DARK_GRAY_COLOR);

		calculateButton = new JButton("Calculate");
		calculateButton.setFont(FontManager.getRunescapeFont());
		calculateButton.setForeground(Color.GREEN);
		calculateButton.setOpaque(false);
		calculateButton.addActionListener(e -> onClickCalculate());
		add(calculateButton);

		cancelButton = new JButton("Cancel");
		cancelButton.setFont(FontManager.getRunescapeFont());
		cancelButton.setForeground(Color.WHITE);
		cancelButton.setOpaque(false);
		cancelButton.setEnabled(false);
		cancelButton.addActionListener(e -> onClickCancel());
		add(cancelButton);
	}

	private void onClickCancel()
	{
		calculateButton.setEnabled(true);
		calculateButton.setForeground(Color.GREEN);
		cancelButton.setEnabled(false);
	}

	private void onClickCalculate()
	{
		calculateButton.setEnabled(false);
		calculateButton.setForeground(Color.LIGHT_GRAY);
		cancelButton.setEnabled(true);
	}
}
