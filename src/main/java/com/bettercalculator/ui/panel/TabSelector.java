package com.bettercalculator.ui.panel;

import com.bettercalculator.BetterCalculator;
import java.awt.Component;
import java.awt.image.BufferedImage;
import javax.annotation.Nullable;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import lombok.Getter;
import net.runelite.api.Skill;

public class TabSelector extends JTabbedPane
{
	private final BetterCalculator plugin;
	@Nullable
	@Getter
	private CalculatorScreen currentScreen = null;
	public TabSelector(BetterCalculator plugin)
	{
		super();
		this.plugin = plugin;

		addChangeListener(e -> {
			JTabbedPane pane = (JTabbedPane)e.getSource();
			Component comp = pane.getSelectedComponent();
			if (comp instanceof CalculatorScreen)
			{
				CalculatorScreen screen = (CalculatorScreen) comp;
				if (screen != currentScreen)
				{
					if (currentScreen != null)
					{
						currentScreen.onHide();
					}
					screen.onDisplay();
					currentScreen = screen;
				}
			}
		});
	}

	public void update(BetterCalculator plugin)
	{
		if (currentScreen != null)
		{
			currentScreen.update(plugin);
		}
	}


	public void addSelection(Skill skill, CalculatorScreen screen)
	{
		BufferedImage img = plugin.getSkillIconManager().getSkillImage(skill, true);
		String skillName = skill.getName();
		ImageIcon icon = new ImageIcon(img);
		JScrollPane scrollPane = new JScrollPane(screen);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		addTab(skillName, icon, scrollPane);
	}
}
