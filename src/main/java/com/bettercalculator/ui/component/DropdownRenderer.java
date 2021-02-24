package com.bettercalculator.ui.component;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.border.EmptyBorder;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.util.Text;

public final class DropdownRenderer extends DefaultListCellRenderer
{
	@Override
	public Component getListCellRendererComponent(JList<?> list, Object o, int i, boolean isSelected, boolean b1) {
		setBackground(ColorScheme.DARK_GRAY_COLOR);
		setForeground(Color.WHITE);
		setBorder(new EmptyBorder(0, 0, 0, 0));

		if (!isSelected)
		{
			setBackground(ColorScheme.DARK_GRAY_COLOR);
			setForeground(Color.WHITE);
		}
		else
		{
			setBackground(list.getBackground());
			setForeground(ColorScheme.LIGHT_GRAY_COLOR);
		}

		setText(Text.titleCase((Enum) o));

		if (o instanceof ToolTipProvider)
		{
			String tooltip = ((ToolTipProvider)o).getTooltip();
			list.setToolTipText(tooltip);
		}

		return this;
	}
}
