package com.bettercalculator.ui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
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
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(3, 0, 0, 0));
		setBackground(ColorScheme.DARK_GRAY_COLOR);

		JPanel container = new JPanel();
		container.setLayout(new FlowLayout(FlowLayout.CENTER));

		calculateButton = new JButton();
		//SwingUtil.removeButtonDecorations(calculateButton);
		calculateButton.setFont(FontManager.getRunescapeFont());
		calculateButton.setForeground(Color.GREEN);
		calculateButton.setOpaque(false);
		calculateButton.setText("Calculate");


		cancelButton = new JButton();
		//SwingUtil.removeButtonDecorations(cancelButton);
		cancelButton.setFont(FontManager.getRunescapeFont());
		cancelButton.setForeground(Color.WHITE);
		cancelButton.setOpaque(false);
		cancelButton.setText("Cancel");
		cancelButton.setEnabled(false);
		cancelButton.addActionListener(e -> {
			onClickCancel();
		});
		calculateButton.addActionListener(e -> {
			onClickCalculate();
		});

		container.add(calculateButton);
		container.add(cancelButton);

		add(container, BorderLayout.CENTER);
	}

	public void onClickCancel()
	{
		calculateButton.setEnabled(true);
		calculateButton.setForeground(Color.GREEN);
		cancelButton.setEnabled(false);
	}

	public void onClickCalculate()
	{
		calculateButton.setEnabled(false);
		calculateButton.setForeground(Color.LIGHT_GRAY);
		cancelButton.setEnabled(true);
	}
}
